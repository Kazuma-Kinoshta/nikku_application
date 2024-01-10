package com.diary.management.nikku.Repository;

import com.diary.management.nikku.Form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<UserForm> selectStudentForList(UserForm userForm) throws ParseException {

        String sql = """
                select * from users where authority = '1'""";

            if(!(userForm.getGrade() == null || userForm.getGrade().isEmpty())){
                sql = sql + " and users.grade = '" + userForm.getGrade() + "'";
            }

        if(!(userForm.getUserClass() == null || userForm.getUserClass().isEmpty())){
            sql = sql + " and users.user_class = '" + userForm.getUserClass() + "'";
        }

        if(!(userForm.getStudentNumber() == null || userForm.getStudentNumber().isEmpty())){
            sql = sql + " and users.student_number = '" + userForm.getStudentNumber() + "'";
        }
        if(!(userForm.getLastName() == null || userForm.getLastName().isEmpty())){
            sql = sql + " and users.last_name = '" + userForm.getLastName() + "'";
        }
        if(!(userForm.getFirstName() == null || userForm.getFirstName().isEmpty())){
            sql = sql + " and users.first_name = '" + userForm.getFirstName() + "'";
        }

            //末尾にセミコロン付与
        sql = sql + ";";

        //検索結果を格納するモデルのリスト
        List<UserForm> userList = new ArrayList<>();

        //SQL実行
        List<Map<String,Object>> userMap = jdbcTemplate.queryForList(sql);

        for(Map<String, Object> map: userMap){
            //検索結果を格納するモデル
            UserForm user = new UserForm();

            user.setUserId(map.get("user_id").toString());
            user.setGrade(map.get("grade").toString());
            user.setUserClass(map.get("user_class").toString());
            user.setStudentNumber(map.get("student_number").toString());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            user.setLastName(map.get("last_name").toString());
            user.setFirstName(map.get("first_name").toString());

            userList.add(user);

        }
        return userList;
    }

}
