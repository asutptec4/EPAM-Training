package com.epam.xmlparseapp.xmlparser;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.xmlparseapp.service.ParserFactory;

public class ComplexParserTest {

    DeviceSaxParser saxParser;
    DeviceStaxParser staxParser;
    DeviceDomParser domParser;

    final String fileNameXML = "src/main/resources/xml/computers.xml";

    @BeforeMethod
    public void beforeMethod() {
	saxParser = (DeviceSaxParser) ParserFactory.createDeviceParser("SAX");
	saxParser.buildDeviceSet(fileNameXML);
	staxParser = (DeviceStaxParser) ParserFactory
		.createDeviceParser("STAX");
	staxParser.buildDeviceSet(fileNameXML);
	domParser = (DeviceDomParser) ParserFactory.createDeviceParser("DOM");
	domParser.buildDeviceSet(fileNameXML);
    }

    @Test
    public void testParserResultEquality() {
	Assert.assertEquals(saxParser.devices, domParser.devices);
	Assert.assertEquals(saxParser.devices, staxParser.devices);
    }
}
