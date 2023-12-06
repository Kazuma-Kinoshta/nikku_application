package com.diary.management.nikku.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DiaryRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void insertDiary(String userId){
        String query = "INSERT INTO diary(" +
                "user_id," +
                "diary_date," +
                "diary_text," +
                "checked," +
                "school_id) values("+
                "'0000000001'," +
                "'2023-11-23'," +
                "'今日は良い天気でしたので外で遊ぼうと思ったけど何かダルくてやめたけど夕方は散歩した'," +
                "false," +
                "'1')";
        jdbcTemplate.update(query);

    }

}
