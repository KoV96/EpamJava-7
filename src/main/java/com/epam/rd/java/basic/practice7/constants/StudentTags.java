package com.epam.rd.java.basic.practice7.constants;

public enum StudentTags {
    FIRSTNAME("firstName"),
    LASTNAME("lastName"),
    AGE("age"),
    WEIGHT("weight"),
    HEIGHT("height"),
    PHONENUMBER("phoneNumber"),
    COUNTRY("country"),
    COLOUR("colour"),
    PARAMS("params"),
    STUDENT("Student"),
    STUDENTS("Students");

    private String value;

    StudentTags(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
