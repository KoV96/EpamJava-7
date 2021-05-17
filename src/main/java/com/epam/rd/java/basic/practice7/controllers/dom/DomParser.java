package com.epam.rd.java.basic.practice7.controllers.dom;

import com.epam.rd.java.basic.practice7.constants.StudentTags;
import com.epam.rd.java.basic.practice7.container.Students;
import com.epam.rd.java.basic.practice7.students.Student;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.logging.Logger;

public class DomParser {
    private static final Logger LOGGER = Logger.getLogger(DomParser.class.getName());
    private final Students students;
    private DocumentBuilder documentBuilder;
    private final String fileName;

    public DomParser(String fileName){
        this.fileName = fileName;
        students = new Students();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    public Students getStudents(){
        return students;
    }

    public void buildStudentsList(){
        Document doc;
        try {
            doc = documentBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            NodeList studentList = root.getElementsByTagName(StudentTags.STUDENT.getValue());
            for (int i = 0; i < studentList.getLength(); i++){
                Element studentElement = (Element) studentList.item(i);
                Student student = buildStudent(studentElement);
                students.add(student);
            }
        } catch (SAXException |  IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    private Student buildStudent(Element studentElement) {
        if (studentElement != null){
            Student student = new Student();
            student.setFirstName(getElementTexContent(studentElement, StudentTags.FIRSTNAME.getValue()));
            student.setLastName(getElementTexContent(studentElement, StudentTags.LASTNAME.getValue()));
            student.setAge(Integer.parseInt(getElementTexContent(studentElement, StudentTags.AGE.getValue())));
            student.setCountry(getElementTexContent(studentElement, StudentTags.COUNTRY.getValue()));
            student.setPhoneNumber(Long.parseLong(getElementTexContent(studentElement, StudentTags.PHONENUMBER.getValue())));
            Element paramsElement = (Element) studentElement.getElementsByTagName(StudentTags.PARAMS.getValue()).item(0);
            student.getParams().setHeight(Double.parseDouble(getElementTexContent(paramsElement, StudentTags.HEIGHT.getValue())));
            student.getParams().setWeight(Double.parseDouble(getElementTexContent(paramsElement, StudentTags.WEIGHT.getValue())));
            student.getParams().setColour(getElementTexContent(paramsElement, StudentTags.COLOUR.getValue()));
            return student;
        }
        return null;
    }

    private String getElementTexContent(Element studentElement, String value) {
        NodeList nodeList = studentElement.getElementsByTagName(value);
        Node node = nodeList.item(0);
        return node.getTextContent();
    }
}
