package com.epam.rd.java.basic.practice7.controllers.sax;

import com.epam.rd.java.basic.practice7.container.Students;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.logging.Logger;

public class SaxParser {
    private Students students;
    private final StudentHandler handler;
    private static final Logger LOGGER = Logger.getLogger(SaxParser.class.getName());
    private XMLReader reader;
    private final String fileName;

    public SaxParser(String fileName){
        this.fileName = fileName;
        SAXParserFactory factory = SAXParserFactory.newInstance();
        handler = new StudentHandler();
        try {
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            SAXParser parser = factory.newSAXParser();
            reader = parser.getXMLReader();
        } catch (ParserConfigurationException | SAXException e) {
            LOGGER.severe(e.getMessage());
        }
        reader.setContentHandler(handler);
    }

    public void buildStudentsList(){
        try {
            reader.parse(fileName);
        } catch (IOException | SAXException e) {
            LOGGER.severe(e.getMessage());
        }
        students = handler.getStudents();
    }

    public Students getStudents(){
        return students;
    }
}
