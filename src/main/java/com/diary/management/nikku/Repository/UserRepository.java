package com.diary.management.nikku.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Map<String, Object> findById(String userId){
        String query = "SELECT *" +
                        "FROM users "+
                        "WHERE user_id=?";
        //検索実行
        Map<String, Object> user = jdbcTemplate.queryForMap(query,userId);
        return user;
    }
}
