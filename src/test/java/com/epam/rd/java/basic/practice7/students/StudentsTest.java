package com.epam.rd.java.basic.practice7.students;

import com.epam.rd.java.basic.practice7.container.Students;
import com.epam.rd.java.basic.practice7.controllers.stax.StaxParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentsTest {
    private Students students;

    @Before
    public void init(){
        StaxParser parser = new StaxParser("input.xml");
        parser.buildStudentsList();
        students = parser.getStudents();
    }

    @Test
    public void shouldBeList(){
        Assert.assertNotNull(students.getStudents());
    }

    @Test
    public void shouldContainStudent(){
        Assert.assertNotNull(students.get(0));
    }
}
