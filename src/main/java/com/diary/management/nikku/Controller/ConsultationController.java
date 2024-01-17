package com.diary.management.nikku.Controller;
import com.diary.management.nikku.Form.ConsultationForm;
import com.diary.management.nikku.Form.DiaryForm;
import com.diary.management.nikku.Model.DiaryModel;
import com.diary.management.nikku.Repository.ConsultationRepository;
import com.diary.management.nikku.Repository.DiaryRepository;
import com.diary.management.nikku.Service.DiaryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class ConsultationController {

    @Autowired
    ConsultationRepository consultationRepository;

    @Autowired
    DiaryRepository diaryRepository;

    @Autowired
    ModelMapper modelMapper;

    boolean success = false;

    //相談投稿画面へ遷移
    @GetMapping("/consultationUpload")
    public String getConsultationUpload(Model model,
                                        @ModelAttribute ConsultationForm consultationForm){
        return "user/consultationUpload";
    }

    //相談投稿実行
    @PostMapping("/consultationUpload")
    public String postConsultationUpload(@ModelAttribute ConsultationForm consultationForm,
                                         @RequestParam("userId") String userId,
                                         Model model){

        consultationForm.setUserId(userId);
        consultationForm.setConsultationDate(LocalDateTime.now().withNano(0));
        consultationForm.setSchoolId("1");
        consultationRepository.insertConsultation(consultationForm);

        success = true;
        model.addAttribute("success",success);
        return "/user/consultationUpload";
    }

    //相談一覧画面へ遷移
    @GetMapping("/consultationList")
    public String getConsultationList(Model model,
                                      @ModelAttribute ConsultationForm consultationForm){

        List<ConsultationForm> consultations = consultationRepository.selectConsultationAll();

        model.addAttribute("consultations",consultations);
        return "user/consultationList";
    }

    //相談一覧画面で検索実行
    @GetMapping("/consultationList/search")
    public String searchConsultationList(Model model,
                                         @ModelAttribute ConsultationForm consultationForm,
                                         @RequestParam("consultationDate") String consultationDate){
        if(!(consultationDate == null || consultationDate.isEmpty())) {
            consultationDate.replace("/", "-");
            consultationDate += " 00:00";
            DateTimeFormatter dtfm = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
            consultationForm.setConsultationDate(LocalDateTime.parse(consultationDate, dtfm));
        }

        List<ConsultationForm> consultations = consultationRepository.searchConsultations(consultationForm);

        model.addAttribute("consultations",consultations);
        return "user/consultationList";
    }

    }