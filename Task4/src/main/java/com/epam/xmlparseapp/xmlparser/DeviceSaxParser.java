package com.epam.xmlparseapp.xmlparser;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class DeviceSaxParser extends AbstractParser {
    
    private static final Logger LOGGER = LogManager
	    .getLogger(DeviceSaxParser.class);

    private XMLReader reader;
    private DeviceSAXHandler handler;
    
    public DeviceSaxParser() {
	super();
	try {
	    reader = XMLReaderFactory.createXMLReader();
	    handler = new DeviceSAXHandler();
	    reader.setContentHandler(handler);
	}catch (SAXException e) {
	    LOGGER.error("Parser configuration error", e);
	}
    }

    @Override
    public void buildDeviceSet(String fileName) {
	try {
	    reader.parse(fileName);
	    getDevices().addAll(handler.getDeviceSet());
	} catch (SAXException | IOException e) {
	    LOGGER.error("SAX parse error", e);
	}
    }

}
