package com.epam.xmlparseapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.xmlparseapp.service.XMLValidator;
import com.epam.xmlparseapp.xmlparser.AbstractParser;
import com.epam.xmlparseapp.xmlparser.DeviceDomParser;
import com.epam.xmlparseapp.xmlparser.DeviceSaxParser;
import com.epam.xmlparseapp.xmlparser.DeviceStaxParser;

// TODO: Delete this class in future
public class App {

    private static final Logger LOGGER = LogManager.getLogger(App.class);

    public static void main(String[] args) {
	String fileNameXML = "src/main/resources/xml/computers.xml";
	String fileNameXSD = "src/main/resources/xml/computers.xsd";
	LOGGER.info("XML valid? - "
		+ XMLValidator.validateXMLwithXSD(fileNameXML, fileNameXSD));
	AbstractParser builder;
//	builder = new DeviceDomParser();
	builder = new DeviceSaxParser();
//	builder = new DeviceStaxParser();
	builder.buildDeviceSet(fileNameXML);
	builder.getDevices().forEach(LOGGER::info);
    }

}
