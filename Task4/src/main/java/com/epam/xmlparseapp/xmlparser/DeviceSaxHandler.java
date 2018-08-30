package com.epam.xmlparseapp.xmlparser;

import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.epam.xmlparseapp.entity.ComputerPart;
import com.epam.xmlparseapp.entity.Device;
import com.epam.xmlparseapp.entity.Port;
import com.epam.xmlparseapp.entity.Price;
import com.epam.xmlparseapp.entity.Specification;
import com.epam.xmlparseapp.util.XmlTagBundle;

/**
 * Handler for SAX parser. Overload three basic element: startElement,
 * endElement and characters.
 * 
 * @version 1 30.08.2018
 * @author Alexander Shishonok
 */
public class DeviceSaxHandler extends DefaultHandler {

    private static final int ZERO_ITEM = 0;

    private HashSet<Device> deviceSet = new HashSet<Device>();
    private ArrayDeque<String> elementStack = new ArrayDeque<String>();
    private ArrayDeque<Device> objectStack = new ArrayDeque<Device>();

    public HashSet<Device> getDeviceSet() {
	return deviceSet;
    }

    @Override
    public void startElement(String uri, String localName, String qName,
	    Attributes attributes) throws SAXException {
	this.elementStack.push(qName);
	if (XmlTagBundle.DEVICE.getTag().equals(qName)) {
	    Device device = new Device();
	    this.objectStack.push(device);
	    this.deviceSet.add(device);
	} else if (XmlTagBundle.COMPUTERPART.getTag().equals(qName)) {
	    ComputerPart device = new ComputerPart();
	    device.setCritical(attributes.getValue(ZERO_ITEM));
	    this.objectStack.push(device);
	    this.deviceSet.add(device);
	} else if (XmlTagBundle.SPECIFICATION.getTag().equals(qName)) {
	    Specification spec = new Specification();
	    spec.setPorts(new ArrayList<Port>());
	    ((ComputerPart) currentObject()).setSpecification(spec);
	} else if (XmlTagBundle.PRICE.getTag().equals(qName)) {
	    Price price = new Price();
	    String currency;
	    if ((currency = attributes
		    .getValue(XmlTagBundle.CURRENCY.getTag())) != null) {
		price.setCurrency(currency);
	    } else {
		price.setCurrency(XmlTagBundle.USD.getTag());
	    }
	    currentObject().setPrice(price);
	} else if (XmlTagBundle.PORT.getTag().equals(qName)) {
	    Port port = new Port();
	    if (attributes.getValue(ZERO_ITEM) != null) {
		port.setType(attributes.getValue(ZERO_ITEM));
	    } else {
		port.setType("");
	    }
	    ((ComputerPart) currentObject()).getSpecification().getPorts()
		    .add(port);
	}
    }

    @Override
    public void endElement(String uri, String localName, String qName)
	    throws SAXException {
	this.elementStack.pop();
    }

    @Override
    public void characters(char[] ch, int start, int length)
	    throws SAXException {
	String value = new String(ch, start, length);
	if (value.length() == 0) {
	    return;
	}
	if (XmlTagBundle.MANUFACTURER.getTag().equals(currentElement())) {
	    currentObject().setManufacturer(value);
	} else if (XmlTagBundle.MODELNAME.getTag().equals(currentElement())) {
	    currentObject().setModelname(value);
	} else if (XmlTagBundle.CATEGORY.getTag().equals(currentElement())) {
	    currentObject().setCategory(value);
	} else if (XmlTagBundle.PARTNUMBER.getTag().equals(currentElement())) {
	    currentObject().setPartnumber(value);
	} else if (XmlTagBundle.ORIGIN.getTag().equals(currentElement())) {
	    currentObject().setOrigin(value);
	} else if (XmlTagBundle.RELEASEDATE.getTag().equals(currentElement())) {
	    currentObject().setReleasedate(LocalDate.parse(value));
	} else if (XmlTagBundle.PRICE.getTag().equals(currentElement())) {
	    currentObject().getPrice().setValue(Float.parseFloat(value));
	} else if (XmlTagBundle.GROUP.getTag().equals(currentElement())) {
	    ((ComputerPart) currentObject()).getSpecification().setGroup(value);
	} else if (XmlTagBundle.TYPE.getTag().equals(currentElement())) {
	    ((ComputerPart) currentObject()).getSpecification().setType(value);
	} else if (XmlTagBundle.POWERCONSUM.getTag().equals(currentElement())) {
	    ((ComputerPart) currentObject()).getSpecification()
		    .setPowerconsum(Integer.parseInt(value));
	} else if (XmlTagBundle.COOLINGSYS.getTag().equals(currentElement())) {
	    ((ComputerPart) currentObject()).getSpecification()
		    .setCoolingsys(value);
	} else if (XmlTagBundle.PORT.getTag().equals(currentElement())) {
	    int last = ((ComputerPart) currentObject()).getSpecification()
		    .getPorts().size() - 1;
	    ((ComputerPart) currentObject()).getSpecification().getPorts()
		    .get(last).setCount(Integer.parseInt(value));
	}

    }

    private String currentElement() {
	return this.elementStack.peek();
    }

    private Device currentObject() {
	return this.objectStack.peek();
    }

}
