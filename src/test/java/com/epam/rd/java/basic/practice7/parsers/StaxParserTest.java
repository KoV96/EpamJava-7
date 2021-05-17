package com.epam.rd.java.basic.practice7.parsers;

import com.epam.rd.java.basic.practice7.controllers.stax.StaxParser;
import org.junit.Assert;
import org.junit.Test;

public class StaxParserTest {

    @Test
    public void shouldReturnStudents(){
        StaxParser parser = new StaxParser("input.xml");
        parser.buildStudentsList();
        Assert.assertNotNull(parser.getStudents());
    }
}
