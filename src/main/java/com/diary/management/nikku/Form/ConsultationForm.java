package com.diary.management.nikku.Form;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Setter
@Getter
public class ConsultationForm {
    String consultationId;
    String userId;
    LocalDateTime consultationDate;
    String consultationText;
    String schoolId;
    String responseNumber;
    String responseUserId;
    String responseText;
    String checkUserId;
    Boolean checked;

    String grade;
    String userClass;
    String studentNumber;
    String lastName;
    String firstName;


}
