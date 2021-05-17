package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.builders.dom_builder.DOMBuilder;
import com.epam.rd.java.basic.practice7.builders.sax_builder.SAXBuilder;
import com.epam.rd.java.basic.practice7.builders.stax_builder.StAXBuilder;
import com.epam.rd.java.basic.practice7.container.Students;
import com.epam.rd.java.basic.practice7.controllers.dom.DomParser;
import com.epam.rd.java.basic.practice7.controllers.sax.SaxParser;
import com.epam.rd.java.basic.practice7.controllers.stax.StaxParser;
import com.epam.rd.java.basic.practice7.students.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class Main {
    private static final String SAX_OUTPUT_NAME = "output.sax.xml";
    private static final String STAX_OUTPUT_NAME = "output.stax.xml";
    private static final String DOM_OUTPUT_NAME = "output.dom.xml";

    public static void main(final String[] args) {
        if (args[0] != null) {
            DomParser domParser = new DomParser(args[0]);
            SaxParser saxParser = new SaxParser(args[0]);
            StaxParser staxParser = new StaxParser(args[0]);
            domParser.buildStudentsList();
            staxParser.buildStudentsList();
            saxParser.buildStudentsList();
            DOMBuilder domBuilder = new DOMBuilder(sortDOMByFirstName(domParser.getStudents()), DOM_OUTPUT_NAME);
            SAXBuilder saxBuilder = new SAXBuilder(sortSAXByAge(saxParser.getStudents()), SAX_OUTPUT_NAME);
            StAXBuilder staxBuilder = new StAXBuilder(sortStAXByLastName(staxParser.getStudents()), STAX_OUTPUT_NAME);
            domBuilder.buildXML();
            saxBuilder.buildXML();
            staxBuilder.buildXML();
        }
    }

    private static Students sortSAXByAge(Students students){
        List<Student> sortedSAX = new ArrayList<>();
        students.getStudents().stream().sorted(Comparator.comparingInt(Student::getAge)).forEach(sortedSAX::add);
        return new Students(sortedSAX);
    }

    private static Students sortStAXByLastName(Students students){
        List<Student> sortedSAX = new ArrayList<>();
        students.getStudents().stream().sorted(Comparator.comparing(Student::getLastName)).forEach(sortedSAX::add);
        return new Students(sortedSAX);
    }

    private static Students sortDOMByFirstName(Students students){
        List<Student> sortedSAX = new ArrayList<>();
        students.getStudents().stream().sorted(Comparator.comparing(Student::getFirstName)).forEach(sortedSAX::add);
        return new Students(sortedSAX);
    }
}
