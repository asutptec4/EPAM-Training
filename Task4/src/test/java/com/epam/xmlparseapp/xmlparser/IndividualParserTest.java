package com.epam.xmlparseapp.xmlparser;

import java.time.LocalDate;
import java.util.HashSet;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.epam.xmlparseapp.entity.Device;
import com.epam.xmlparseapp.entity.Price;
import com.epam.xmlparseapp.service.ParserFactory;

public class DomParserTest extends Assert {

    Device device;
    AbstractParser parser;

    @Test
    public void testDomParser() {
	String fileNameXML = "src/test/java/xml/test.xml";
	parser.buildDeviceSet(fileNameXML);
	assertEquals(new HashSet<>().add(device), parser.devices, "hello");
    }

    @BeforeMethod
    public void beforeMethod() {
	parser = ParserFactory.createDeviceParser("DOM");
	device = new Device();
	device.setManufacturer("Xiaomi");
	device.setModelname("Redmi Note 3");
	device.setCategory("Phone");
	device.setPartnumber("SN379574434");
	device.setOrigin("China");
	device.setReleasedate(LocalDate.parse("2016-02-28"));
	Price price = new Price();
	price.setCurrency("USD");
	price.setValue(179);
	device.setPrice(price);
    }

}
