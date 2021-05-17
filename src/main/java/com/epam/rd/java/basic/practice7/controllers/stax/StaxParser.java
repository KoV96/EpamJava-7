package com.epam.rd.java.basic.practice7.controllers.stax;

import com.epam.rd.java.basic.practice7.constants.StudentTags;
import com.epam.rd.java.basic.practice7.container.Students;
import com.epam.rd.java.basic.practice7.students.Params;
import com.epam.rd.java.basic.practice7.students.Student;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Logger;

public class StaxParser {
    private static final Logger LOGGER = Logger.getLogger(StaxParser.class.getName());
    private final Students students;
    private final XMLInputFactory factory = XMLInputFactory.newFactory();
    private final String fileName;

    public StaxParser(String fileName){
        students = new Students();
        this.fileName = fileName;
    }

    public Students getStudents(){
        return students;
    }

    public void buildStudentsList(){
        XMLStreamReader reader;
        String name;
        try(FileInputStream inputStream = new FileInputStream(new File(fileName))) {
            reader = factory.createXMLStreamReader(inputStream);
            while (reader.hasNext()){
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT){
                    name = reader.getLocalName();
                    if (name.equals(StudentTags.STUDENT.getValue())){
                        Student student = buildStudent(reader);
                        students.add(student);
                    }
                } else if (type == XMLStreamConstants.END_ELEMENT){
                    name = reader.getLocalName();
                    if (name.equals(StudentTags.STUDENT.getValue())){
                        break;
                    }
                }
            }
        } catch (IOException | XMLStreamException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    private Student buildStudent(XMLStreamReader reader) throws XMLStreamException {
        Student student = new Student();
        String name;
        while (reader.hasNext()){
            int type = reader.next();
            if (type == XMLStreamConstants.START_ELEMENT){
                name = reader.getLocalName();
                if (name.equals(StudentTags.FIRSTNAME.getValue())){
                    student.setFirstName(getXMLText(reader));
                } else if (name.equals(StudentTags.LASTNAME.getValue())){
                    student.setLastName(getXMLText(reader));
                } else if (name.equals(StudentTags.AGE.getValue())){
                    student.setAge(Integer.parseInt(getXMLText(reader)));
                } else if (name.equals(StudentTags.PHONENUMBER.getValue())){
                    student.setPhoneNumber(Long.parseLong(getXMLText(reader)));
                } else if (name.equals(StudentTags.COUNTRY.getValue())){
                    student.setCountry(getXMLText(reader));
                } else if (name.equals(StudentTags.PARAMS.getValue())){
                    student.setParams(getXMLParams(reader));
                }
            } else if (type == XMLStreamConstants.END_ELEMENT){
                name = reader.getLocalName();
                if (name.equals(StudentTags.STUDENT.getValue())){
                    return student;
                }
            }
        }
        throw new XMLStreamException("Unknown element in tag <Student>");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()){
            reader.next();
            text = reader.getText();
        }
        return text;
    }

    private Params getXMLParams(XMLStreamReader reader) throws XMLStreamException {
        Params params = new Params();
        String name;
        int type;
            while (reader.hasNext()){
                type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT){
                    name = reader.getLocalName();
                    if (name.equals(StudentTags.HEIGHT.getValue())){
                        params.setHeight(Double.parseDouble(getXMLText(reader)));
                    } else if (name.equals(StudentTags.WEIGHT.getValue())){
                        params.setWeight(Double.parseDouble(getXMLText(reader)));
                    } else if (name.equals(StudentTags.COLOUR.getValue())){
                        params.setColour(getXMLText(reader));
                    }
            } else if (type == XMLStreamConstants.END_ELEMENT){
                    name = reader.getLocalName();
                    if (name.equals(StudentTags.PARAMS.getValue())){
                        return params;
                    }
                }
        }
            throw new XMLStreamException("Unknown element in tag <params>");
    }
}
