package com.diary.management.nikku.Form;

import lombok.Data;

@Data
public class UserForm {
    String userId;
    String password;
    String lastName;
    String firstName;
    String mail;
    String schoolId;
    String authority;
    String userClass;
    String grade;
    String studentNumber;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
