package com.diary.management.nikku.Repository;

import com.diary.management.nikku.Form.DiaryForm;
import com.diary.management.nikku.Model.DiaryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class DiaryRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<DiaryModel> selectDiaryForList(DiaryForm diaryForm) throws ParseException {

        String sql = """
                select * from diary inner join questionnare on diary.user_id = questionnare.user_id and diary.diary_date = questionnare.diary_date inner join users on diary.user_id = users.user_id""";

        //検索の入力項目の数を格納する
        int contentsCounter = 0;

        //SQL文に組み込んだ検索項目の数を格納する
        int queryCounter = 0;

        //一つ以上あれば whereをつける
        if(!(diaryForm.getGrade() == null || diaryForm.getGrade().isEmpty())){
            contentsCounter++;
        }

        if(!(diaryForm.getUserClass() == null || diaryForm.getUserClass().isEmpty())){
            contentsCounter++;
        }
        if(!(diaryForm.getStudentNumber() == null || diaryForm.getStudentNumber().isEmpty())){
            contentsCounter++;
        }
        if(!(diaryForm.getLastName() == null || diaryForm.getLastName().isEmpty())){
            contentsCounter++;
        }
        if(!(diaryForm.getFirstName() == null || diaryForm.getFirstName().isEmpty())){
            contentsCounter++;
        }
        if(!(diaryForm.getDiaryDate() == null || diaryForm.getDiaryDate().isEmpty())){
            contentsCounter++;
        }
        if(!(diaryForm.getChecked() == null)){
            contentsCounter++;
        }

        //検索項目の入力を1個以上確認できればwhereを挿入
        if(contentsCounter > 0) {
            sql = sql + " where";
        }

            if(!(diaryForm.getGrade() == null || diaryForm.getGrade().isEmpty())){
                //この検索項目が2つ目以上なら冒頭にandをつける
                if(queryCounter > 0){
                    sql = sql + " and";
                }
                sql = sql + " users.grade = '" + diaryForm.getGrade() + "'";
                queryCounter++;
            }

            if(!(diaryForm.getUserClass() == null || diaryForm.getUserClass().isEmpty())){
                //この検索項目が2つ目以上なら冒頭にandをつける
                if(queryCounter > 0){
                    sql = sql + " and";
                }
                sql = sql + " users.user_class = '" + diaryForm.getUserClass() + "'";
                queryCounter++;
            }
            if(!(diaryForm.getStudentNumber() == null || diaryForm.getStudentNumber().isEmpty())){
                //この検索項目が2つ目以上なら冒頭にandをつける
                if(queryCounter > 0){
                    sql = sql + " and";
                }
                sql = sql + " users.student_number = '" + diaryForm.getStudentNumber() + "'";
                queryCounter++;
            }
            if(!(diaryForm.getLastName() == null || diaryForm.getLastName().isEmpty())){
                //この検索項目が2つ目以上なら冒頭にandをつける
                if(queryCounter > 0){
                    sql = sql + " and";
                }
                sql = sql + " users.last_name = '" + diaryForm.getLastName() + "'";
                queryCounter++;
            }
            if(!(diaryForm.getFirstName() == null || diaryForm.getFirstName().isEmpty())){
                //この検索項目が2つ目以上なら冒頭にandをつける
                if(queryCounter > 0){
                    sql = sql + " and";
                }
                sql = sql + " users.first_name = '" + diaryForm.getFirstName() + "'";
                queryCounter++;
            }
            if(!(diaryForm.getDiaryDate() == null || diaryForm.getDiaryDate().isEmpty())){
                //この検索項目が2つ目以上なら冒頭にandをつける
                if(queryCounter > 0){
                    sql = sql + " and";
                }
                sql = sql + " diary.diary_date = '" + diaryForm.getDiaryDate() + "'";
                queryCounter++;
            }
            if(!(diaryForm.getChecked() == null)){
                //この検索項目が2つ目以上なら冒頭にandをつける
                if(queryCounter > 0){
                    sql = sql + " and";
                }
                sql = sql + " diary.checked = " + diaryForm.getChecked();
                queryCounter++;
            }
            //末尾にセミコロン付与
        sql = sql + ";";

        //検索結果を格納するモデルのリスト
        List<DiaryModel> diaries = new ArrayList<>();
        List<Map<String,Object>> diaryList = jdbcTemplate.queryForList(sql);
        for(Map<String, Object> map: diaryList){
            //検索結果を格納するモデル
            DiaryModel diaryModel = new DiaryModel();

            diaryModel.setGrade(map.get("grade").toString());
            diaryModel.setUserClass(map.get("user_class").toString());
            diaryModel.setStudentNumber(map.get("student_number").toString());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            diaryModel.setDiaryDate(simpleDateFormat.parse(map.get("diary_date").toString()));
            diaryModel.setLastName(map.get("last_name").toString());
            diaryModel.setFirstName(map.get("first_name").toString());
            diaryModel.setChecked(Boolean.parseBoolean(map.get("checked").toString()));
            diaryModel.setDiaryText(map.get("diary_text").toString());

            diaries.add(diaryModel);

        }
        return diaries;
    }

}
