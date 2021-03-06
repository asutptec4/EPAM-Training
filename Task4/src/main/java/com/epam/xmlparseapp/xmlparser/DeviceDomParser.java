package com.epam.xmlparseapp.xmlparser;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.epam.xmlparseapp.entity.ComputerPart;
import com.epam.xmlparseapp.entity.Device;
import com.epam.xmlparseapp.entity.Port;
import com.epam.xmlparseapp.entity.Price;
import com.epam.xmlparseapp.entity.Specification;
import com.epam.xmlparseapp.util.XmlTagBundle;

/**
 * DOM parser for creating {@code Device} instances.
 * 
 * @version 1 30.08.2018
 * @author Alexander Shishonok
 */
public class DeviceDomParser extends AbstractParser {

    private static final Logger LOGGER = LogManager
	    .getLogger(DeviceDomParser.class);

    private DocumentBuilder documentBuilder;

    public DeviceDomParser() {
	super();
	try {
	    documentBuilder = DocumentBuilderFactory.newInstance()
		    .newDocumentBuilder();
	} catch (ParserConfigurationException e) {
	    LOGGER.error("Parser configuration error", e);
	}
    }

    @Override
    public void buildDeviceSet(String fileName) {
	Document document = null;
	try {
	    document = documentBuilder.parse(fileName);
	    Element root = document.getDocumentElement();
	    NodeList list = root.getChildNodes();
	    for (int i = 0; i < list.getLength(); i++) {
		if (list.item(i).getNodeName()
			.equals(XmlTagBundle.DEVICE.getTag())) {
		    Element element = (Element) list.item(i);
		    Device device = new Device();
		    buildDevice(element, device);
		    getDevices().add(device);
		} else if (list.item(i).getNodeName()
			.equals(XmlTagBundle.COMPUTERPART.getTag())) {
		    Element element = (Element) list.item(i);
		    ComputerPart device = new ComputerPart();
		    buildComputerPart(element, device);
		    getDevices().add(device);
		}
	    }
	} catch (SAXException | IOException e) {
	    LOGGER.error("Parser configuration error", e);
	}

    }

    private void buildDevice(Element deviceElement, Device device) {
	device.setManufacturer(deviceElement
		.getElementsByTagName(XmlTagBundle.MANUFACTURER.getTag())
		.item(ZERO_ITEM).getTextContent());
	device.setModelname(deviceElement
		.getElementsByTagName(XmlTagBundle.MODELNAME.getTag())
		.item(ZERO_ITEM).getTextContent());
	device.setCategory(deviceElement
		.getElementsByTagName(XmlTagBundle.CATEGORY.getTag())
		.item(ZERO_ITEM).getTextContent());
	device.setPartnumber(deviceElement
		.getElementsByTagName(XmlTagBundle.PARTNUMBER.getTag())
		.item(ZERO_ITEM).getTextContent());
	device.setOrigin(
		deviceElement.getElementsByTagName(XmlTagBundle.ORIGIN.getTag())
			.item(ZERO_ITEM).getTextContent());
	device.setReleasedate(LocalDate.parse(deviceElement
		.getElementsByTagName(XmlTagBundle.RELEASEDATE.getTag())
		.item(ZERO_ITEM).getTextContent()));
	Price price = new Price();
	price.setCurrency(((Element) deviceElement
		.getElementsByTagName(XmlTagBundle.PRICE.getTag())
		.item(ZERO_ITEM)).getAttribute(XmlTagBundle.CURRENCY.getTag()));
	if (price.getCurrency().equals("")) {
	    price.setCurrency(XmlTagBundle.USD.getTag());
	}
	price.setValue(Float.parseFloat(
		deviceElement.getElementsByTagName(XmlTagBundle.PRICE.getTag())
			.item(ZERO_ITEM).getTextContent()));
	device.setPrice(price);
    }

    private void buildComputerPart(Element deviceElement, ComputerPart device) {
	device.setCritical(
		deviceElement.getAttribute(XmlTagBundle.CRITICAL.getTag()));
	buildDevice(deviceElement, device);
	Element element = (Element) deviceElement
		.getElementsByTagName(XmlTagBundle.SPECIFICATION.getTag())
		.item(ZERO_ITEM);
	Specification specification = new Specification();
	specification.setGroup(
		element.getElementsByTagName(XmlTagBundle.GROUP.getTag())
			.item(ZERO_ITEM).getTextContent());
	specification.setType(
		element.getElementsByTagName(XmlTagBundle.TYPE.getTag())
			.item(ZERO_ITEM).getTextContent());
	specification.setPowerconsum(Integer.parseInt(
		element.getElementsByTagName(XmlTagBundle.POWERCONSUM.getTag())
			.item(ZERO_ITEM).getTextContent()));
	specification.setCoolingsys(
		element.getElementsByTagName(XmlTagBundle.COOLINGSYS.getTag())
			.item(ZERO_ITEM).getTextContent());
	ArrayList<Port> portList = new ArrayList<Port>();
	NodeList list = element
		.getElementsByTagName(XmlTagBundle.PORT.getTag());
	for (int i = 0; i < list.getLength(); i++) {
	    element = (Element) list.item(i);
	    Port port = new Port();
	    port.setType(element.getAttribute(XmlTagBundle.TYPE.getTag()));
	    port.setCount(Integer.parseInt(element.getTextContent()));
	    portList.add(port);
	}
	specification.setPorts(portList);
	device.setSpecification(specification);
    }
}