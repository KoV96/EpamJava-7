package com.epam.rd.java.basic.practice7.builders;

import com.epam.rd.java.basic.practice7.builders.stax_builder.StAXBuilder;
import com.epam.rd.java.basic.practice7.controllers.stax.StaxParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class StAXBuilderTest {
    private static final String INPUT = "input.xml";
    private static final String STAX_OUTPUT = "output_test.sax.xml";
    private StAXBuilder builder;

    @Before
    public void init(){
        StaxParser parser = new StaxParser(INPUT);
        parser.buildStudentsList();
        builder = new StAXBuilder(parser.getStudents(), STAX_OUTPUT);
    }

    @Test
    public void shouldCompile(){
        Assert.assertNotNull(builder);
    }

    @Test
    public void shouldCreateFile(){
        builder.buildXML();
        Assert.assertTrue(new File(STAX_OUTPUT).delete());
    }
}
