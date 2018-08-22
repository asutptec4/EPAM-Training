package com.epam.xmlparseapp.xmlparser;

import java.util.ArrayDeque;
import java.util.HashSet;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.epam.xmlparseapp.entity.ComputerPart;
import com.epam.xmlparseapp.entity.Device;
import com.epam.xmlparseapp.util.XMLTagBundle;

public class DeviceSAXHandler extends DefaultHandler {

    private static final int ZERO_ITEM = 0;

    private HashSet<Device> deviceSet = new HashSet<Device>();
    private ArrayDeque<String> elementStack = new ArrayDeque<String>();
    private ArrayDeque<Object> objectStack = new ArrayDeque<Object>();

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
	    ((Device) currentObject()).setManufacturer(value);
	} else if (XMLTagBundle.MODELNAME.getTag().equals(currentElement())) {
	    ((Device) currentObject()).setModelname(value);
	} else if (XMLTagBundle.CATEGORY.getTag().equals(currentElement())) {
	    ((Device) currentObject()).setCategory(value);
	} else if (XMLTagBundle.PARTNUMBER.getTag().equals(currentElement())) {
	    ((Device) currentObject()).setPartnumber(value);
	}
    }

    private String currentElement() {
	return this.elementStack.peek();
    }

    private Object currentObject() {
	return this.objectStack.peek();
    }

}
