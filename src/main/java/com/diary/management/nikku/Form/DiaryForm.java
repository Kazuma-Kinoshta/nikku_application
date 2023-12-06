package com.diary.management.nikku.Form;

import lombok.Data;

@Data
public class DiaryForm {
    String userId;
    String diaryDate;
    String diaryText;
    Boolean checked;
    String schoolId;
    String feelings;
    Boolean breakfast;
    String sleepTime;
    String wakeTime;
    Boolean notSleep;

    String lastName;
    String firstName;
    String userClass;
    String grade;
    String studentNumber;
}
