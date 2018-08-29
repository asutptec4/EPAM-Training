package com.epam.xmlparseapp.xmlparser;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.xmlparseapp.entity.ComputerPart;
import com.epam.xmlparseapp.service.ParserFactory;

public class DomParserTest extends Assert{
    
    ComputerPart device;
    AbstractParser parser;
    
    @Test
    public void f() {
	String fileNameXML = "src/test/java/xml/test.xml";
	
    }

    @BeforeMethod
    public void beforeMethod() {
	parser = ParserFactory.createDeviceParser("DOM");
	device = new ComputerPart();
	
    }

}
