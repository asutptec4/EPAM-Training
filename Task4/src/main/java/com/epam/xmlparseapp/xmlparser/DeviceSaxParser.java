package com.epam.xmlparseapp.xmlparser;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * SAX parser for creating {@code Device} instances.
 * 
 * @version 1 30.08.2018
 * @author Alexander Shishonok
 */
public class DeviceSaxParser extends AbstractParser {
    
    private static final Logger LOGGER = LogManager
	    .getLogger(DeviceSaxParser.class);

    private XMLReader reader;
    private DeviceSaxHandler handler;
    
    public DeviceSaxParser() {
	super();
	try {
	    reader = XMLReaderFactory.createXMLReader();
	    handler = new DeviceSaxHandler();
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
