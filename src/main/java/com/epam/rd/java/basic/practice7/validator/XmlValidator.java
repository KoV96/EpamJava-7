package com.epam.rd.java.basic.practice7.validator;

import org.xml.sax.SAXException;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.io.StringWriter;
import java.util.logging.Logger;

public class XmlValidator {
    private static final Logger LOGGER = Logger.getAnonymousLogger();

    private XmlValidator(){}

    public static boolean isValid(String fileName, String schemaName) {
        StreamSource xsdStreamSource = new StreamSource(schemaName);
        StreamSource xmlStreamSource = new StreamSource(fileName);
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory schemaFactory = SchemaFactory.newInstance(language);
        try {
            Schema schema = schemaFactory.newSchema(xsdStreamSource);
            schemaFactory.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
            schemaFactory.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            Validator validator = schema.newValidator();
            validator.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            validator.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
            StringWriter writer = new StringWriter();
            validator.validate(xmlStreamSource, new StreamResult(writer));
        } catch (SAXException | IOException e) {
            LOGGER.severe(e.getMessage());
            return false;
        }
        return true;
    }
}
