package com.diary.management.nikku.Controller;
import com.diary.management.nikku.Form.DiaryForm;
import com.diary.management.nikku.Model.DiaryModel;
import com.diary.management.nikku.Repository.DiaryRepository;
import com.diary.management.nikku.Service.DiaryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class DiaryController {

    @Autowired
    DiaryService diaryService;

    @Autowired
    DiaryRepository diaryRepository;

    @Autowired
    ModelMapper modelMapper;

    //日記アップロード画面へ遷移
    @GetMapping("/diaryUpload")
    public String getDiary(@ModelAttribute DiaryForm diaryForm,
                           Model model){
        return "/user/diaryUpload";
    }

    //日記一覧画面へ遷移
    @GetMapping("diaryList")
    public String getDiaryList(@ModelAttribute DiaryForm diaryForm, Model model){
        List<DiaryModel> diaries = diaryService.selectDiariesAll();
        model.addAttribute("diaries",diaries);
        return "/user/diaryList";
    }


    //日記アップロード
    @PostMapping("/diaryUpload")
    public String postDiary(@ModelAttribute DiaryForm diaryForm,
                            Model model,
                            @RequestParam("userId") String userId){

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

        /*日記一覧(検索実行)*/
    @GetMapping("diaryList/search")
    public String diaryListSearch(Model model,
                                  @RequestParam("grade") String grade,
                                  @RequestParam("userClass") String userClass,
                                  @RequestParam("studentNumber") String studentNumber,
                                  @RequestParam("lastName") String lastName,
                                  @RequestParam("firstName") String firstName,
                                  @RequestParam("diaryDate") String diaryDate,
                                  @RequestParam("checked") String checked) throws ParseException {

        DiaryForm diaryForm = new DiaryForm();
        diaryForm.setGrade(grade);
        diaryForm.setUserClass(userClass);
        diaryForm.setStudentNumber(studentNumber);
        diaryForm.setLastName(lastName);
        diaryForm.setFirstName(firstName);

        //日付が入力されていた場合はSQL検索用に整形してからフォームに格納
        diaryForm.setDiaryDate(diaryDate.replace("/", "-"));

        //確認済みか否かの値が入力されていた場合は、Boolean型に変換して格納
        Boolean checkedBoolean = null;
        if(!(checked == null || checked.isEmpty())){
            checkedBoolean = Boolean.valueOf(checked);
        }
        diaryForm.setChecked(checkedBoolean);

        List<DiaryModel> diaries = diaryRepository.selectDiaryForList(diaryForm);

        //日記一覧へ

        model.addAttribute("diaries",diaries);

        return "/user/diaryList";
    }


    /*日記詳細*/
    @GetMapping("/diaryDetails")
    public String postDiaryDetails(@ModelAttribute("userId") String userId,
                                   @ModelAttribute("diaryDate") String diaryDate,
                                   Model model){
        DiaryModel diary = diaryService.selectDiary(userId, diaryDate);
        diary.setSleepTime(diary.getSleepTime().substring(0,5));
        diary.setWakeTime(diary.getWakeTime().substring(0,5));
        model.addAttribute("diary", diary);

        return "/user/diaryDetails";
    }

    @GetMapping("/diaryCheck")
    public String diaryChecked(@RequestParam("userId") String userId,
                               @RequestParam("diaryDate") String diaryDate,
                               Model model,
                               RedirectAttributes redirectAttributes){

        //チェック処理
        diaryService.checkDiary(userId, diaryDate);

        //詳細画面表示処理
        DiaryModel diary = diaryService.selectDiary(userId, diaryDate);
        diary.setSleepTime(diary.getSleepTime().substring(0,5));
        diary.setWakeTime(diary.getWakeTime().substring(0,5));
        model.addAttribute("diary", diary);
        redirectAttributes.addFlashAttribute("userId", userId);
        redirectAttributes.addFlashAttribute("diaryDate",diaryDate);
        return "redirect:/user/diaryDetails";
    }



    }