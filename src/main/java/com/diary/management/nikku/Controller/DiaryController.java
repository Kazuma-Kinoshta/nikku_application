package com.diary.management.nikku.Controller;
import com.diary.management.nikku.Form.DiaryForm;
import com.diary.management.nikku.Model.DiaryModel;
import com.diary.management.nikku.Service.DiaryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/user")
public class DiaryController {

    @Autowired
    DiaryService diaryService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/diaryUpload")
    public String postDiary(@ModelAttribute DiaryForm diaryForm, Model model){

        //今日の日付登録
        var today = LocalDate.now().toString();
        diaryForm.setDiaryDate(today);

        //チェックの初期値設定
        diaryForm.setChecked(false);

        //formをModelに移す
        DiaryModel diaryModel = modelMapper.map(diaryForm,DiaryModel.class);

        //登録処理
        diaryService.insertDiary(diaryModel);
        diaryService.insertQustionnare(diaryModel);

        //日記一覧へ
        List<DiaryModel> diaries = diaryService.selectDiariesAll();
        model.addAttribute("diaries",diaries);
            return "/user/diaryList";
        }
    /*日記詳細*/
    @GetMapping("/diaryDetails")
    public String postDiaryDetails(@RequestParam("userId") String userId,
                                   @RequestParam("diaryDate") String diaryDate,
                                   Model model){
        DiaryModel diary = diaryService.selectDiary(userId, diaryDate);
        model.addAttribute("diary", diary);

        return "/user/diaryDetails";
    }

        @GetMapping("/diaryUpload")
    public String getDiaryList(){

        return "/user/diaryUpload";
        }



    }