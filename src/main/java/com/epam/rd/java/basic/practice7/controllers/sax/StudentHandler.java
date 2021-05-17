package com.epam.rd.java.basic.practice7.controllers.sax;

import com.epam.rd.java.basic.practice7.constants.StudentTags;
import com.epam.rd.java.basic.practice7.container.Students;
import com.epam.rd.java.basic.practice7.students.Student;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.EnumSet;

public class StudentHandler extends DefaultHandler {
    private final Students students;
    private Student currentStudent;
    private StudentTags currentTag;
    private final EnumSet<StudentTags> tags;
    private static final String ELEMENT_START_POSITION = "Student";

    public StudentHandler(){
        this.tags = EnumSet.range(StudentTags.FIRSTNAME, StudentTags.COLOUR);
        students = new Students();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals(ELEMENT_START_POSITION)){
            currentStudent = new Student();
            return;
        }
        try {
            StudentTags temp = StudentTags.valueOf(qName.toUpperCase());
            if (tags.contains(temp)){
                currentTag = temp;
            }
        }catch (IllegalArgumentException e){
            currentTag = null;
        }


    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentTag != null){
            String value = new String(ch, start, length).trim();
            if (!value.isEmpty()) {
                if (currentTag.equals(StudentTags.FIRSTNAME)) {
                    currentStudent.setFirstName(value);
                } else if (currentTag.equals(StudentTags.LASTNAME)) {
                    currentStudent.setLastName(value);
                } else if (currentTag.equals(StudentTags.AGE)) {
                    currentStudent.setAge(Integer.parseInt(value));
                } else if (currentTag.equals(StudentTags.PHONENUMBER)) {
                    currentStudent.setPhoneNumber(Long.parseLong(value));
                } else if (currentTag.equals(StudentTags.COLOUR)) {
                    currentStudent.getParams().setColour(value);
                } else if (currentTag.equals(StudentTags.HEIGHT)) {
                    currentStudent.getParams().setHeight(Double.parseDouble(value));
                } else if (currentTag.equals(StudentTags.WEIGHT)) {
                    currentStudent.getParams().setWeight(Double.parseDouble(value));
                } else if (currentTag.equals(StudentTags.COUNTRY)){
                    currentStudent.setCountry(value);
                } else {
                    throw new EnumConstantNotPresentException(currentTag.getDeclaringClass(), currentTag.name());
                }
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals(ELEMENT_START_POSITION)){
            students.add(currentStudent);
        }
    }

    public Students getStudents(){
        return students;
    }
}
