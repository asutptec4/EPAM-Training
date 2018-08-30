package com.epam.xmlparseapp.service;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

/**
 * The class has one static method for XML validation with XSD scheme.
 * 
 * @version 1 30.08.2018
 * @author Alexander Shishonok
 */
public class XmlValidator {

    private static final Logger LOGGER = LogManager
	    .getLogger(XmlValidator.class);

    /**
     * Verifies that the XML-file is valid.
     * 
     * @param pathXML
     *            path of XML source file
     * @param pathXSD
     *            path of XSD file
     * @return true if XML file is valid
     */
    public static boolean validateXMLwithXSD(String pathXML, String pathXSD) {
	try {
	    SchemaFactory factory = SchemaFactory
		    .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	    Schema schema = factory.newSchema(new File(pathXSD));
	    Validator validator = schema.newValidator();
	    validator.validate(new StreamSource(new File(pathXML)));
	} catch (SAXException | IOException e) {
	    LOGGER.error(e);
	    return false;
	}
	return true;
    }
}
