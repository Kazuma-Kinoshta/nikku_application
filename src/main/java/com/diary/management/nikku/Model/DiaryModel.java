package com.diary.management.nikku.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Data
@Setter
@Getter
public class DiaryModel {
private String userId;
private Date diaryDate;
private String diaryText;
private boolean checked;
private  String schoolId;
private String feelings;
private boolean breakfast;
private String sleepTime;
private String wakeTime;
private boolean notSleep;

private String lastName;
private String firstName;
private String userClass;
private String grade;
private String studentNumber;
private String schoolName;

}
