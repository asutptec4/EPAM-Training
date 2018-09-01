package test.com.epam.xmlparseapp.service;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.epam.xmlparseapp.service.XmlValidator;

public class XmlValidatorTest {

    final String fileNameXML = "src/main/resources/xml/computers.xml";
    final String fileNameXSD = "src/main/resources/xml/computers.xsd";

    @Test
    public void testValidateXMLwithXSD() {
	Assert.assertEquals(
		XmlValidator.validateXMLwithXSD(fileNameXML, fileNameXSD),
		true);
    }
}
