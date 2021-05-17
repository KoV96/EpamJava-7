package com.epam.rd.java.basic.practice7.container;

import com.epam.rd.java.basic.practice7.students.Student;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Students implements Iterable<Student> {
    private final List<Student> studentContainer;

    public Students() {
        studentContainer = new ArrayList<>();
    }

    public Students(List<Student> list){
        studentContainer = list;
    }

    public void add(Student student){
        studentContainer.add(student);
    }

    public Student get(int i){
        return studentContainer.get(i);
    }

    public List<Student> getStudents(){
        return studentContainer;
    }

    @Override
    public Iterator<Student> iterator() {
        return studentContainer.iterator();
    }
}
