package com.epam.rd.java.basic.practice7.student;

import com.epam.rd.java.basic.practice7.controllers.sax.SaxParser;
import com.epam.rd.java.basic.practice7.students.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentTest {
    private Student student;

    @Before
    public void init(){
        SaxParser parser = new SaxParser("input.xml");
        parser.buildStudentsList();
        student = parser.getStudents().get(0);
    }

    @Test
    public void shouldCreateStudents(){
        Assert.assertNotNull(student.getFirstName());
    }

    @Test
    public  void shouldReturnString(){
        Assert.assertNotNull(student.getFirstName());
        Assert.assertNotNull(student.getLastName());
        Assert.assertNotNull(student.getCountry());
    }

    @Test
    public void shouldReturnPositiveInteger(){
        Assert.assertTrue(student.getAge() > 0);
    }

    @Test
    public void shouldReturnCorrectPhoneNumber(){
        Assert.assertTrue(Long.toString(student.getPhoneNumber()).length() > 4);
    }

    @Test
    public void shouldBeDouble(){
        Assert.assertTrue(Double.toString(student.getParams().getWeight()).contains("."));
        Assert.assertTrue(Double.toString(student.getParams().getHeight()).contains("."));
    }
}
