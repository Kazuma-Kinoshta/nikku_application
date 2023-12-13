package com.diary.management.nikku.Model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@Entity
public class DiaryModelJPA {
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
