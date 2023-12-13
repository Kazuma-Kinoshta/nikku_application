package com.diary.management.nikku.Model;

import lombok.Data;

import java.util.Date;
@Data
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

    public String getSleepTime() {
        return this.sleepTime;
    }

    public String getWakeTime() {
        return this.wakeTime;
    }

    public void setSleepTime(String sleepTime) {
        this.sleepTime = sleepTime;
    }

    public void setWakeTime(String wakeTime) {
        this.wakeTime = wakeTime;
    }
}
