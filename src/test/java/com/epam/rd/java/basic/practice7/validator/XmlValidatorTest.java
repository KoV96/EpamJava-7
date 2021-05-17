package com.epam.rd.java.basic.practice7.validator;

import com.epam.rd.java.basic.practice7.builders.dom_builder.DOMBuilder;
import com.epam.rd.java.basic.practice7.builders.sax_builder.SAXBuilder;
import com.epam.rd.java.basic.practice7.builders.stax_builder.StAXBuilder;
import com.epam.rd.java.basic.practice7.controllers.dom.DomParser;
import com.epam.rd.java.basic.practice7.controllers.sax.SaxParser;
import com.epam.rd.java.basic.practice7.controllers.stax.StaxParser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class XmlValidatorTest {
    private static final String DOM_OUTPUT = "output_test.dom.xml";
    private static final String SAX_OUTPUT = "output_test.sax.xml";
    private static final String STAX_OUTPUT = "output_test.stax.xml";
    private static final String INPUT = "input.xml";
    private static final String SCHEMA = "no_target_input.xsd";

    @Test
    public void checkValidator(){
        Assert.assertTrue(XmlValidator.isValid("input.xml", "input.xsd"));
    }

    @Test
    public void validateOutputDom(){
        DomParser domParser = new DomParser(INPUT);
        domParser.buildStudentsList();
        DOMBuilder builder = new DOMBuilder(domParser.getStudents(), DOM_OUTPUT);
        builder.buildXML();
        Assert.assertTrue(XmlValidator.isValid(DOM_OUTPUT, SCHEMA));
    }

    @Test
    public void validateOutputSax(){
        SaxParser saxParser = new SaxParser(INPUT);
        saxParser.buildStudentsList();
        SAXBuilder builder = new SAXBuilder(saxParser.getStudents(), SAX_OUTPUT);
        builder.buildXML();
        Assert.assertTrue(XmlValidator.isValid(SAX_OUTPUT, SCHEMA));
    }

    @Test
    public void validateOutputStax(){
        StaxParser domParser = new StaxParser(INPUT);
        domParser.buildStudentsList();
        StAXBuilder builder = new StAXBuilder(domParser.getStudents(), STAX_OUTPUT);
        builder.buildXML();
        Assert.assertTrue(XmlValidator.isValid(STAX_OUTPUT, SCHEMA));
    }

    @After
    public void deleteTestFiles(){
        try {
            Files.deleteIfExists(Paths.get(DOM_OUTPUT));
            Files.deleteIfExists(Paths.get(STAX_OUTPUT));
            Files.deleteIfExists(Paths.get(SAX_OUTPUT));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
