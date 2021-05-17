package com.epam.rd.java.basic.practice7.parsers;

import com.epam.rd.java.basic.practice7.controllers.dom.DomParser;
import org.junit.Assert;
import org.junit.Test;

public class DomParserTest {

    @Test
    public void shouldReturnStudents(){
        DomParser parser = new DomParser("input.xml");
        parser.buildStudentsList();
        Assert.assertNotNull(parser.getStudents());
    }
}
