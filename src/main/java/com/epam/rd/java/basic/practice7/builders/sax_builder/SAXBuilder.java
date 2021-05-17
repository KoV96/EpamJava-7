package com.epam.rd.java.basic.practice7.builders.sax_builder;

import com.epam.rd.java.basic.practice7.constants.StudentTags;
import com.epam.rd.java.basic.practice7.container.Students;
import com.epam.rd.java.basic.practice7.students.Student;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

public class SAXBuilder {
    private static final Logger LOGGER = Logger.getLogger(SAXBuilder.class.getName());
    private final Students students;
    private final String fileName;
    private final XMLOutputFactory factory;

    public SAXBuilder(Students students, String fileName) {
        this.students = students;
        this.fileName = fileName;
        factory = XMLOutputFactory.newInstance();
    }

    public void buildXML(){
        try(FileOutputStream fio = new FileOutputStream(fileName)) {
            XMLStreamWriter writer = factory.createXMLStreamWriter(fio, "UTF8");
            writer.writeStartDocument("UTF8", "1.0");
            writer.writeStartElement(StudentTags.STUDENTS.getValue());
            for (Student student : students){
                writer.writeStartElement(StudentTags.STUDENT.getValue());
                createNode(writer, student.getFirstName(), StudentTags.FIRSTNAME.getValue());
                createNode(writer, student.getLastName(), StudentTags.LASTNAME.getValue());
                createNode(writer, Integer.toString(student.getAge()), StudentTags.AGE.getValue());
                createNode(writer, student.getCountry(), StudentTags.COUNTRY.getValue());
                createNode(writer, Long.toString(student.getPhoneNumber()), StudentTags.PHONENUMBER.getValue());
                writer.writeStartElement(StudentTags.PARAMS.getValue());
                createNode(writer, Double.toString(student.getParams().getHeight()), StudentTags.HEIGHT.getValue());
                createNode(writer, Double.toString(student.getParams().getWeight()), StudentTags.WEIGHT.getValue());
                createNode(writer, student.getParams().getColour(), StudentTags.COLOUR.getValue());
                writer.writeEndElement();
                writer.writeEndElement();
            }
            writer.writeEndElement();
            writer.writeEndDocument();
            writer.flush();
            writer.close();
        }
        catch (XMLStreamException | IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    private void createNode(XMLStreamWriter writer, String name, String tagName) throws XMLStreamException {
        writer.writeStartElement(tagName);
        writer.writeCharacters(name);
        writer.writeEndElement();
    }

}
