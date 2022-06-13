package com.example.TeacherManagement.entity;

public enum Gender {
    MALE(1),
    FEMALE(2),
    OTHER(3);

    private Integer value;


    Gender(Integer value) {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }
}
