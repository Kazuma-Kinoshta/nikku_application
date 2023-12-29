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
import java.util.List;

@Controller
@RequestMapping("/user")
public class ConsultationController {

    @Autowired
    DiaryService diaryService;

    @Autowired
    DiaryRepository diaryRepository;

    @Autowired
    ModelMapper modelMapper;

    //相談投稿画面へ遷移
    @GetMapping("/consultationUpload")
    public String getConsultationUpload(Model model){
        return "user/consultationUpload";
    }

    //相談一覧画面へ遷移
    @GetMapping("/consultationList")
    public String getConsultationList(Model model){
        return "user/consultationList";
    }

    //生徒胃一覧画面へ遷移
    @GetMapping("/studentList")
    public String getStudentList(Model model){
        return "user/studentList";
    }
    }