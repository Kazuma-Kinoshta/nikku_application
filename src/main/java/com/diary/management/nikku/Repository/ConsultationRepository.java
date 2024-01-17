package com.diary.management.nikku.Repository;

import com.diary.management.nikku.Form.ConsultationForm;
import com.diary.management.nikku.Form.DiaryForm;
import com.diary.management.nikku.Model.DiaryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Repository
public class ConsultationRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void insertConsultation(ConsultationForm consultationForm){
        String selectSql = "select count(*) from consultation where consultation_id = ";

        boolean checkDistinct = false;
        Random rand = new Random();
        String consultationId = "";
        // ID重複チェック処理
        do{
           for(int i = 0; i < 16; i++){
            consultationId += Integer.valueOf(rand.nextInt(10)).toString();
           }
           selectSql += consultationId + ";";

           try{
               int resultNum = jdbcTemplate.queryForObject(selectSql, Integer.class);
               if(resultNum == 0){
                   checkDistinct = true;
               }

           }catch (IncorrectResultSizeDataAccessException e){
           }

        }while(!checkDistinct);

        //ここからインサート処理
        String insertSql = "insert into consultation(consultation_id, user_id, consultation_date, consultation_text, school_id) values(?,?,?,?,?)";
        jdbcTemplate.update(insertSql, consultationId,consultationForm.getUserId(),consultationForm.getConsultationDate(),consultationForm.getConsultationText(),consultationForm.getSchoolId());

    }

    //全件検索(相談一覧初期表示)
    public List<ConsultationForm> selectConsultationAll(){
        String selectAllSql = "select * from consultation inner join users on users.user_id = consultation.user_id left join consultation_check on consultation.consultation_id = consultation_check.consultation_id;";
        List<Map<String, Object>> results = jdbcTemplate.queryForList(selectAllSql);
        List<ConsultationForm> consultations = new ArrayList<>();
        for(Map<String, Object> result:results){
            ConsultationForm consultation = new ConsultationForm();
            consultation.setConsultationId(result.get("consultation_id").toString());
            consultation.setUserId(result.get("user_id").toString());
            DateTimeFormatter dtFt = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
            consultation.setConsultationDate(LocalDateTime.parse(result.get("consultation_date").toString().replace("-","/").substring(0,16),dtFt));
            consultation.setConsultationText(result.get("consultation_text").toString());
            if(!(result.get("check_user_id") == null)){
                consultation.setChecked(true);
            }
            consultation.setGrade(result.get("grade").toString());
            consultation.setUserClass(result.get("user_class").toString());
            consultation.setStudentNumber(result.get("student_number").toString());
            consultation.setLastName(result.get("last_name").toString());
            consultation.setFirstName(result.get("first_name").toString());

            consultations.add(consultation);
        }
        return consultations;
    }

    //検索実行
    public List<ConsultationForm> searchConsultations(ConsultationForm consultationForm){
        String searchSql = "select * from consultation inner join users on users.user_id = consultation.user_id left join consultation_check on consultation.consultation_id = consultation_check.consultation_id";
        int contentsCounter = 0;
        int queryCounter = 0;

        if(!(consultationForm.getGrade() == null || consultationForm.getGrade().isEmpty())){
            contentsCounter++;
        }

        if(!(consultationForm.getUserClass() == null || consultationForm.getUserClass().isEmpty())){
            contentsCounter++;
        }

        if(!(consultationForm.getStudentNumber() == null || consultationForm.getStudentNumber().isEmpty())){
            contentsCounter++;
        }

        if(!(consultationForm.getLastName() == null || consultationForm.getLastName().isEmpty())){
            contentsCounter++;
        }

        if(!(consultationForm.getFirstName() == null || consultationForm.getFirstName().isEmpty())){
            contentsCounter++;
        }

        //検索項目の入力を1個以上確認できればwhereを挿入
        if(contentsCounter > 0) {
            searchSql = searchSql + " where";
        }

        if(!(consultationForm.getGrade() == null || consultationForm.getGrade().isEmpty())){
            //この検索項目が2つ目以上なら冒頭にandをつける
            if(queryCounter > 0){
                searchSql = searchSql + " and";
            }
            searchSql = searchSql + " users.grade = '" + consultationForm.getGrade() + "'";
            queryCounter++;
        }

        if(!(consultationForm.getUserClass() == null || consultationForm.getUserClass().isEmpty())){
            //この検索項目が2つ目以上なら冒頭にandをつける
            if(queryCounter > 0){
                searchSql = searchSql + " and";
            }
            searchSql = searchSql + " users.user_class = '" + consultationForm.getUserClass() + "'";
            queryCounter++;
        }

        if(!(consultationForm.getStudentNumber() == null || consultationForm.getStudentNumber().isEmpty())){
            //この検索項目が2つ目以上なら冒頭にandをつける
            if(queryCounter > 0){
                searchSql = searchSql + " and";
            }
            searchSql = searchSql + " users.student_number = '" + consultationForm.getStudentNumber() + "'";
            queryCounter++;
        }

        if(!(consultationForm.getLastName() == null || consultationForm.getLastName().isEmpty())){
            //この検索項目が2つ目以上なら冒頭にandをつける
            if(queryCounter > 0){
                searchSql = searchSql + " and";
            }
            searchSql = searchSql + " users.last_name = '" + consultationForm.getLastName() + "'";
            queryCounter++;
        }

        if(!(consultationForm.getFirstName() == null || consultationForm.getFirstName().isEmpty())){
            //この検索項目が2つ目以上なら冒頭にandをつける
            if(queryCounter > 0){
                searchSql = searchSql + " and";
            }
            searchSql = searchSql + " users.first_name = '" + consultationForm.getFirstName() + "'";
            queryCounter++;
        }

        //SQL文の末尾に;をつける
        searchSql = searchSql + ";" ;

        List<Map<String, Object>> results = jdbcTemplate.queryForList(searchSql);
        List<ConsultationForm> consultations = new ArrayList<>();
        for(Map<String, Object> result:results){
            ConsultationForm consultation = new ConsultationForm();
            consultation.setConsultationId(result.get("consultation_id").toString());
            consultation.setUserId(result.get("user_id").toString());
            DateTimeFormatter dtFt = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
            consultation.setConsultationDate(LocalDateTime.parse(result.get("consultation_date").toString().replace("-","/").substring(0,16),dtFt));
            consultation.setConsultationText(result.get("consultation_text").toString());
            if(!(result.get("check_user_id") == null)){
                consultation.setChecked(true);
            }
            consultation.setGrade(result.get("grade").toString());
            consultation.setUserClass(result.get("user_class").toString());
            consultation.setStudentNumber(result.get("student_number").toString());
            consultation.setLastName(result.get("last_name").toString());
            consultation.setFirstName(result.get("first_name").toString());

            consultations.add(consultation);
        }
        return consultations;
    }

}
