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
}
