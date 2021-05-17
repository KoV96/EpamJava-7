package com.epam.rd.java.basic.practice7.builders;

import com.epam.rd.java.basic.practice7.builders.sax_builder.SAXBuilder;
import com.epam.rd.java.basic.practice7.controllers.sax.SaxParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class SAXBuilderTest {
    private static final String INPUT = "input.xml";
    private static final String SAX_OUTPUT = "output_test.sax.xml";
    private SAXBuilder builder;

    @Before
    public void init(){
        SaxParser parser = new SaxParser(INPUT);
        parser.buildStudentsList();
        builder = new SAXBuilder(parser.getStudents(), SAX_OUTPUT);
    }

    @Test
    public void shouldCompile(){
        Assert.assertNotNull(builder);
    }

    @Test
    public void shouldCreateFile(){
        builder.buildXML();
        Assert.assertTrue(new File(SAX_OUTPUT).delete());
    }
}
