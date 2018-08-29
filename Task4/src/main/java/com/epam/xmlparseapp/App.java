package com.epam.xmlparseapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.xmlparseapp.service.ParserFactory;
import com.epam.xmlparseapp.service.XmlValidator;
import com.epam.xmlparseapp.xmlparser.AbstractParser;


// TODO: Delete this class in future
public class App {

    private static final Logger LOGGER = LogManager.getLogger(App.class);

    public static void main(String[] args) {
	String fileNameXML = "src/main/resources/xml/computers.xml";
	String fileNameXSD = "src/main/resources/xml/computers.xsd";
	LOGGER.info("XML valid? - "
		+ XmlValidator.validateXMLwithXSD(fileNameXML, fileNameXSD));
	AbstractParser builder;
	builder = ParserFactory.createDeviceParser("StAX");
	builder.buildDeviceSet(fileNameXML);
	builder.getDevices().forEach(LOGGER::info);
    }

}
