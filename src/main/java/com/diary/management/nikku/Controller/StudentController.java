package com.diary.management.nikku.Controller;

import com.diary.management.nikku.Form.UserForm;
import com.diary.management.nikku.Repository.DiaryRepository;
import com.diary.management.nikku.Repository.StudentRepository;
import com.diary.management.nikku.Service.DiaryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class StudentController {

    @Autowired
    DiaryService diaryService;

    @Autowired
    DiaryRepository diaryRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    StudentRepository studentRepository;

   //生徒一覧画面初期表示
    @GetMapping("/studentList")
    public String getStudentListSearch(Model model) throws ParseException {
        UserForm userForm = new UserForm();
        List<UserForm> userList = studentRepository.selectStudentForList(userForm);
        model.addAttribute("userList",userList);
        return "user/studentList";
    }

    //検索を実行して生徒一覧画面を表示
    @GetMapping("/studentList/search")
    public String getStudentList(Model model,
                                 @RequestParam("grade") String grade,
                                 @RequestParam("userClass") String userClass,
                                 @RequestParam("studentNumber") String studentNumber,
                                 @RequestParam("lastName") String lastName,
                                 @RequestParam("firstName") String firstName
    ) throws ParseException {
        UserForm userForm = new UserForm();
        userForm.setGrade(grade);
        userForm.setUserClass(userClass);
        userForm.setStudentNumber(studentNumber);
        userForm.setLastName(lastName);
        userForm.setFirstName(firstName);


        List<UserForm> userList = studentRepository.selectStudentForList(userForm);

        model.addAttribute("userList",userList);
        return "user/studentList";
    }


    }