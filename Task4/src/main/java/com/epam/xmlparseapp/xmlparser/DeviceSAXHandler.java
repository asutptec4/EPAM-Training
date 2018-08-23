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
import com.epam.xmlparseapp.util.XMLTagBundle;

public class DeviceSAXHandler extends DefaultHandler {

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
	if (XMLTagBundle.DEVICE.getTag().equals(qName)) {
	    Device device = new Device();
	    this.objectStack.push(device);
	    this.deviceSet.add(device);
	} else if (XMLTagBundle.COMPUTERPART.getTag().equals(qName)) {
	    ComputerPart device = new ComputerPart();
	    device.setCritical(attributes.getValue(ZERO_ITEM));
	    this.objectStack.push(device);
	    this.deviceSet.add(device);
	} else if (XMLTagBundle.SPECIFICATION.getTag().equals(qName)) {
	    Specification spec = new Specification();
	    spec.setPorts(new ArrayList<Port>());
	    ((ComputerPart) currentObject()).setSpecification(spec);
	} else if (XMLTagBundle.PRICE.getTag().equals(qName)) {
	    Price price = new Price();
	    String currency;
	    if ((currency = attributes
		    .getValue(XMLTagBundle.CURRENCY.getTag())) != null) {
		price.setCurrency(currency);
	    } else {
		price.setCurrency(XMLTagBundle.USD.getTag());
	    }
	    currentObject().setPrice(price);
	} else if (XMLTagBundle.PORT.getTag().equals(qName)) {
	    Port port = new Port();
	    port.setType(attributes.getValue(ZERO_ITEM));
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
	if (XMLTagBundle.MANUFACTURER.getTag().equals(currentElement())) {
	    currentObject().setManufacturer(value);
	} else if (XMLTagBundle.MODELNAME.getTag().equals(currentElement())) {
	    currentObject().setModelname(value);
	} else if (XMLTagBundle.CATEGORY.getTag().equals(currentElement())) {
	    currentObject().setCategory(value);
	} else if (XMLTagBundle.PARTNUMBER.getTag().equals(currentElement())) {
	    currentObject().setPartnumber(value);
	} else if (XMLTagBundle.ORIGIN.getTag().equals(currentElement())) {
	    currentObject().setOrigin(value);
	} else if (XMLTagBundle.RELEASEDATE.getTag().equals(currentElement())) {
	    currentObject().setReleasedate(LocalDate.parse(value));
	} else if (XMLTagBundle.PRICE.getTag().equals(currentElement())) {
	    currentObject().getPrice().setValue(Float.parseFloat(value));
	} else if (XMLTagBundle.GROUP.getTag().equals(currentElement())) {
	    ((ComputerPart) currentObject()).getSpecification().setGroup(value);
	} else if (XMLTagBundle.TYPE.getTag().equals(currentElement())) {
	    ((ComputerPart) currentObject()).getSpecification().setType(value);
	} else if (XMLTagBundle.POWERCONSUM.getTag().equals(currentElement())) {
	    ((ComputerPart) currentObject()).getSpecification()
		    .setPowerconsum(Integer.parseInt(value));
	} else if (XMLTagBundle.COOLINGSYS.getTag().equals(currentElement())) {
	    ((ComputerPart) currentObject()).getSpecification()
		    .setCoolingsys(value);
	} else if (XMLTagBundle.PORT.getTag().equals(currentElement())) {
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
