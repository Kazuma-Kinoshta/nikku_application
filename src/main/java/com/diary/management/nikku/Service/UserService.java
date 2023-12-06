package com.diary.management.nikku.Service;

import com.diary.management.nikku.Form.UserForm;
import com.diary.management.nikku.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserForm getUser(String id){
        //検索
        Map<String, Object> map = userRepository.findById(id);

        //Mapから値を取得
        String userId = (String) map.get("user_id");
        String firstName = (String) map.get("first_name");
        String lastName = (String) map.get("last_name");

        //UserFormにセット
        UserForm user = new UserForm();
        user.setUserId(userId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return user;
    }

}
