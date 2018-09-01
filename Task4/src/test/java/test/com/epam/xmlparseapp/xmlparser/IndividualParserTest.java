package test.com.epam.xmlparseapp.xmlparser;

import java.time.LocalDate;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.epam.xmlparseapp.entity.Device;
import com.epam.xmlparseapp.entity.Price;
import com.epam.xmlparseapp.service.ParserFactory;
import com.epam.xmlparseapp.xmlparser.AbstractParser;

public class IndividualParserTest extends Assert {

    Device device;
    AbstractParser parser;

    @BeforeClass
    public void beforeClass() {
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

    @Parameters({ "xmlFilePath" })
    @Test
    public void testDomParser(String xmlFilePath) {
	parser = ParserFactory.createDeviceParser("DOM");
	parser.buildDeviceSet(xmlFilePath);
	assertEquals(
		parser.getDevices().toArray()[parser.getDevices().size() - 1],
		device);
    }

    @Parameters({ "xmlFilePath" })
    @Test
    public void testSaxParser(String xmlFilePath) {
	parser = ParserFactory.createDeviceParser("SAX");
	parser.buildDeviceSet(xmlFilePath);
	assertEquals(
		parser.getDevices().toArray()[parser.getDevices().size() - 1],
		device);
    }

    @Parameters({ "xmlFilePath" })
    @Test
    public void testStaxParser(String xmlFilePath) {
	parser = ParserFactory.createDeviceParser("STAX");
	parser.buildDeviceSet(xmlFilePath);
	assertEquals(
		parser.getDevices().toArray()[parser.getDevices().size() - 1],
		device);
    }

}
