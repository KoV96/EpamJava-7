package com.epam.rd.java.basic.practice7.builders.stax_builder;

import com.epam.rd.java.basic.practice7.constants.StudentTags;
import com.epam.rd.java.basic.practice7.container.Students;
import com.epam.rd.java.basic.practice7.students.Student;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

public class StAXBuilder {
    private static final Logger LOGGER = Logger.getLogger(StAXBuilder.class.getName());
    private final Students students;
    private final String fileName;
    private final XMLOutputFactory factory;

    public StAXBuilder(Students students, String fileName) {
        this.students = students;
        this.fileName = fileName;
        this.factory = XMLOutputFactory.newInstance();
    }

    public void buildXML(){
        try(FileOutputStream fio = new FileOutputStream(fileName)) {
            XMLEventWriter writer = factory.createXMLEventWriter(fio);
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();

            StartDocument document = eventFactory.createStartDocument("UTF8", "1.0");
            writer.add(document);

            StartElement startElement = eventFactory.createStartElement("", "", StudentTags.STUDENTS.getValue());
            writer.add(startElement);
            for (Student student : students){
                createStudent(student, eventFactory, writer);
            }
            EndElement endElement = eventFactory.createEndElement("", "", StudentTags.STUDENTS.getValue());
            writer.add(endElement);

            EndDocument endDocument = eventFactory.createEndDocument();
            writer.add(endDocument);
            writer.flush();
            writer.close();

        } catch (XMLStreamException | IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    private void createStudent(Student element, XMLEventFactory eventFactory, XMLEventWriter writer)
            throws XMLStreamException {
        StartElement student = eventFactory.createStartElement("","", StudentTags.STUDENT.getValue());
        writer.add(student);

        createNode(element.getFirstName(),writer, eventFactory, StudentTags.FIRSTNAME.getValue());
        createNode(element.getLastName(), writer, eventFactory, StudentTags.LASTNAME.getValue());
        createNode(Integer.toString(element.getAge()), writer, eventFactory, StudentTags.AGE.getValue());
        createNode(element.getCountry(), writer, eventFactory, StudentTags.COUNTRY.getValue());
        createNode(Long.toString(element.getPhoneNumber()), writer, eventFactory, StudentTags.PHONENUMBER.getValue());

        StartElement params = eventFactory.createStartElement("", "", StudentTags.PARAMS.getValue());
        writer.add(params);

        createNode(Double.toString(element.getParams().getHeight()), writer, eventFactory, StudentTags.HEIGHT.getValue());
        createNode(Double.toString(element.getParams().getWeight()), writer, eventFactory, StudentTags.WEIGHT.getValue());
        createNode(element.getParams().getColour(), writer, eventFactory, StudentTags.COLOUR.getValue());

        EndElement endParams = eventFactory.createEndElement("", "", StudentTags.PARAMS.getValue());
        EndElement endStudent = eventFactory.createEndElement("", "", StudentTags.STUDENT.getValue());
        writer.add(endParams);
        writer.add(endStudent);
    }

    private void createNode(String value, XMLEventWriter writer, XMLEventFactory eventFactory, String tag)
            throws XMLStreamException {
        StartElement startElement = eventFactory.createStartElement("", "", tag);
        Characters ch = eventFactory.createCharacters(value);
        EndElement endElement = eventFactory.createEndElement("", "", tag);
        writer.add(startElement);
        writer.add(ch);
        writer.add(endElement);
    }
}
