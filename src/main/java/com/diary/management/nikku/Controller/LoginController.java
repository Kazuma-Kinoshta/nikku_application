package com.diary.management.nikku.Controller;
import com.diary.management.nikku.Form.DiaryForm;
import com.diary.management.nikku.Form.UserForm;
import com.diary.management.nikku.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String getLogin(Model model){
        UserForm form = new UserForm();
        model.addAttribute("form",form);

        return "/user/login";
    }

    @PostMapping("/login")
    public String postLogin(@RequestParam("userId") String userId,
                            @RequestParam("password") String password,
                            Model model,
                            @ModelAttribute DiaryForm form1){
        UserForm form = userService.getUser(userId);
        if(form.getUserId() != null){
            return "/user/diaryUpload";

        }else{
            return "/user/login";
        }



    }

}
