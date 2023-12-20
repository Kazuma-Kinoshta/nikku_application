package com.diary.management.nikku.Form;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class DiaryForm {
    @Setter
    String userId;
    @Setter
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
    @Getter
    String grade;
    String studentNumber;

    public void setChecked(boolean b) {
        this.checked = b;
    }

}
