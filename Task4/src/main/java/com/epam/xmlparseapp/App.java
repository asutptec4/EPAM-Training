package com.epam.xmlparseapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.xmlparseapp.service.XMLValidator;
import com.epam.xmlparseapp.xmlparser.AbstractBuilder;
import com.epam.xmlparseapp.xmlparser.DeviceDOMBuilder;
import com.epam.xmlparseapp.xmlparser.DeviceSAXBuilder;

public class App {

    private static final Logger LOGGER = LogManager.getLogger(App.class);

    public static void main(String[] args) {
	String fileNameXML = "src/main/resources/xml/computers.xml";
	String fileNameXSD = "src/main/resources/xml/computers.xsd";
	LOGGER.info("XML valid? - "
		+ XMLValidator.validateXMLwithXSD(fileNameXML, fileNameXSD));
	AbstractBuilder builder;
//	builder = new DeviceDOMBuilder();
	builder = new DeviceSAXBuilder();
	builder.buildDeviceSet(fileNameXML);
	builder.getDevices().forEach(LOGGER::info);
    }

}
