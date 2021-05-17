package com.epam.rd.java.basic.practice7.parsers;

import com.epam.rd.java.basic.practice7.controllers.sax.SaxParser;
import org.junit.Assert;
import org.junit.Test;

public class SaxParserTest {

    @Test
    public void shouldReturnStudents(){
        SaxParser parser = new SaxParser("input.xml");
        parser.buildStudentsList();
        Assert.assertNotNull(parser.getStudents());
    }
}
