package com.epam.rd.java.basic.practice7.builders.dom_builder;

import com.epam.rd.java.basic.practice7.constants.StudentTags;
import com.epam.rd.java.basic.practice7.container.Students;
import com.epam.rd.java.basic.practice7.students.Student;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

public class DOMBuilder {
    private static final Logger LOGGER = Logger.getLogger(DOMBuilder.class.getName());
    private final Students students;
    private final String fileName;

    public DOMBuilder(Students students, String fileName) {
        this.students = students;
        this.fileName = fileName;
    }

    public void buildXML() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element root = document.createElement(StudentTags.STUDENTS.getValue());
            document.appendChild(root);
            for (Student student : students) {
                Element obj = document.createElement(StudentTags.STUDENT.getValue());
                Element firstName = document.createElement(StudentTags.FIRSTNAME.getValue());
                firstName.appendChild(document.createTextNode(student.getFirstName()));
                Element lastName = document.createElement(StudentTags.LASTNAME.getValue());
                lastName.appendChild(document.createTextNode(student.getLastName()));
                Element age = document.createElement(StudentTags.AGE.getValue());
                age.appendChild(document.createTextNode(Integer.toString(student.getAge())));
                Element country = document.createElement(StudentTags.COUNTRY.getValue());
                country.appendChild(document.createTextNode(student.getCountry()));
                Element phoneNumber = document.createElement(StudentTags.PHONENUMBER.getValue());
                phoneNumber.appendChild(document.createTextNode(Long.toString(student.getPhoneNumber())));
                Element params = document.createElement(StudentTags.PARAMS.getValue());
                Element height = document.createElement(StudentTags.HEIGHT.getValue());
                height.appendChild(document.createTextNode(Double.toString(student.getParams().getHeight())));
                Element weight = document.createElement(StudentTags.WEIGHT.getValue());
                weight.appendChild(document.createTextNode(Double.toString(student.getParams().getWeight())));
                Element colour = document.createElement(StudentTags.COLOUR.getValue());
                colour.appendChild(document.createTextNode(student.getParams().getColour()));
                params.appendChild(height);
                params.appendChild(weight);
                params.appendChild(colour);
                obj.appendChild(firstName);
                obj.appendChild(lastName);
                obj.appendChild(age);
                obj.appendChild(country);
                obj.appendChild(phoneNumber);
                obj.appendChild(params);
                root.appendChild(obj);
                saveDocument(document);
            }

        } catch (ParserConfigurationException | TransformerException | IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    private void saveDocument(Document document) throws TransformerException, IOException {
        TransformerFactory factory = TransformerFactory.newInstance();
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        Transformer transformer = factory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        FileOutputStream fio = new FileOutputStream(fileName);
        transformer.transform(new DOMSource(document), new StreamResult(fio));
        fio.close();
    }
}
