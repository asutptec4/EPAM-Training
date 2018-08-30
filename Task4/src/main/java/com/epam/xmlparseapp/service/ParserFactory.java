package com.epam.xmlparseapp.service;

import com.epam.xmlparseapp.xmlparser.AbstractParser;
import com.epam.xmlparseapp.xmlparser.DeviceDomParser;
import com.epam.xmlparseapp.xmlparser.DeviceSaxParser;
import com.epam.xmlparseapp.xmlparser.DeviceStaxParser;

/**
 * Parser factory contain static method which return instance of needed parser.
 * Can create SAX, DOM, StAX parser. By default create SAX parser.
 * 
 * @version 1 30.08.2018
 * @author Alexander Shishonok
 */
public class ParserFactory {
    
    private enum ParserType{
	DOM,SAX,STAX;
    }
    
    public static AbstractParser createDeviceParser(String type) {
	ParserType parserType = ParserType.valueOf(type.toUpperCase());
	switch (parserType) {
	case DOM:
	    return new DeviceDomParser();
	case SAX:
	    return new DeviceSaxParser();
	case STAX:
	    return new DeviceStaxParser();
	default:
	    return new DeviceSaxParser();
	}
    }
}
