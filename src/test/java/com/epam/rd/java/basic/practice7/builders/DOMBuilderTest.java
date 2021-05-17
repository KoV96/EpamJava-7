package com.epam.rd.java.basic.practice7.builders;

import com.epam.rd.java.basic.practice7.builders.dom_builder.DOMBuilder;
import com.epam.rd.java.basic.practice7.controllers.dom.DomParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class DOMBuilderTest {
    private static final String INPUT = "input.xml";
    private static final String DOM_OUTPUT = "output_test.dom.xml";
    private DOMBuilder builder;

    @Before
    public void init(){
        DomParser parser = new DomParser(INPUT);
        parser.buildStudentsList();
        builder = new DOMBuilder(parser.getStudents(), DOM_OUTPUT);
    }

    @Test
    public void shouldCompile(){
        Assert.assertNotNull(builder);
    }

    @Test
    public void shouldCreateFile(){
        builder.buildXML();
        Assert.assertTrue(new File(DOM_OUTPUT).delete());
    }
}
