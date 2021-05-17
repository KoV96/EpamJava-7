package com.epam.rd.java.basic.practice7.students;

import java.util.Objects;

public class Student{
    private String firstName;
    private String lastName;
    private String country;
    private int age;
    private long phoneNumber;
    private Params params;
    private static final String EOL = System.lineSeparator();

    public Student(){
        this.params = new Params();
    }

    public Student(String firstName, String lastName, int age, String country, long phoneNumber, Params params) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.params = params;
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public Params getParams() {
        return params;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getAge(), getPhoneNumber(), getParams());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()){
            return false;
        }
        Student student = (Student) obj;
        return student.getFirstName().hashCode() == getFirstName().hashCode() &&
                student.getLastName().hashCode() == getLastName().hashCode() &&
                student.getAge() == getAge() &&
                student.getPhoneNumber() == getPhoneNumber() &&
                student.getParams().equals(getParams());
    }

    @Override
    public String toString() {
        return "Student firs name: " + getFirstName() + EOL +
                "Student last name: " + getLastName() + EOL +
                "Age: " + getAge() + EOL +
                "Country: " + getCountry() + EOL +
                "Phone number: " + getPhoneNumber() + EOL +
                "-----Params-----:" + EOL +
                getParams().toString();
    }
}
