package com.epam.xmlparseapp.xmlparser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.xmlparseapp.entity.ComputerPart;
import com.epam.xmlparseapp.entity.Device;
import com.epam.xmlparseapp.entity.Port;
import com.epam.xmlparseapp.entity.Price;
import com.epam.xmlparseapp.entity.Specification;
import com.epam.xmlparseapp.util.XmlTagBundle;

public class DeviceStaxParser extends AbstractParser {

    private static final Logger LOGGER = LogManager
	    .getLogger(DeviceStaxParser.class);

    private XMLInputFactory factory;

    public DeviceStaxParser() {
	factory = XMLInputFactory.newInstance();
    }

    @Override
    public void buildDeviceSet(String fileName) {
	XMLStreamReader reader = null;
	FileInputStream inputStream = null;
	try {
	    inputStream = new FileInputStream(new File(fileName));
	    reader = factory.createXMLStreamReader(inputStream);
	    while (reader.hasNext()) {
		int type = reader.next();
		if (type == XMLStreamReader.START_ELEMENT) {
		    String name = reader.getLocalName();
		    if (XmlTagBundle.DEVICE.getTag().equals(name)) {
			Device device = new Device();
			buildDevice(device, reader);
			getDevices().add(device);
		    } else if (XmlTagBundle.COMPUTERPART.getTag()
			    .equals(name)) {
			ComputerPart device = new ComputerPart();
			buildComputerPart(device, reader);
			getDevices().add(device);
		    }
		}
	    }
	} catch (FileNotFoundException e) {
	    LOGGER.error("XML file not found", e);
	} catch (XMLStreamException e) {
	    LOGGER.error("StAX parser error", e);
	} finally {
	    if (inputStream != null) {
		try {
		    inputStream.close();
		} catch (IOException e) {
		    LOGGER.error("Fail to close file", e);
		}
	    }
	}
    }

    private void buildDevice(Device device, XMLStreamReader reader)
	    throws XMLStreamException {
	while (reader.hasNext()) {
	    int type = reader.next();
	    if (type == XMLStreamReader.START_ELEMENT) {
		String name = reader.getLocalName();
		if (XmlTagBundle.MANUFACTURER.getTag().equals(name)) {
		    device.setManufacturer(getTextContent(reader));
		} else if (XmlTagBundle.MODELNAME.getTag().equals(name)) {
		    device.setModelname(getTextContent(reader));
		} else if (XmlTagBundle.CATEGORY.getTag().equals(name)) {
		    device.setCategory(getTextContent(reader));
		} else if (XmlTagBundle.PARTNUMBER.getTag().equals(name)) {
		    device.setPartnumber(getTextContent(reader));
		} else if (XmlTagBundle.ORIGIN.getTag().equals(name)) {
		    device.setOrigin(getTextContent(reader));
		} else if (XmlTagBundle.RELEASEDATE.getTag().equals(name)) {
		    device.setReleasedate(
			    LocalDate.parse(getTextContent(reader)));
		}
		if (XmlTagBundle.PRICE.getTag().equals(name)) {
		    Price price = new Price();
		    price.setCurrency(reader.getAttributeValue(ZERO_ITEM));
		    price.setValue(Float.parseFloat(getTextContent(reader)));
		    if (price.getCurrency() == null) {
			price.setCurrency(XmlTagBundle.USD.getTag());
		    }
		    device.setPrice(price);
		}
		if (XmlTagBundle.SPECIFICATION.getTag().equals(name)) {
		    return;
		}
	    } else if (type == XMLStreamReader.END_ELEMENT) {
		String name = reader.getLocalName();
		if (XmlTagBundle.DEVICE.getTag().equals(name)) {
		    return;
		}
	    }
	}
    }

    private void buildComputerPart(ComputerPart device, XMLStreamReader reader)
	    throws XMLStreamException {
	device.setCritical(reader.getAttributeValue(ZERO_ITEM));
	buildDevice(device, reader);
	Specification spec = new Specification();
	spec.setPorts(new ArrayList<Port>());
	while (reader.hasNext()) {
	    int type = reader.next();
	    if (type == XMLStreamReader.START_ELEMENT) {
		String name = reader.getLocalName();
		if (XmlTagBundle.GROUP.getTag().equals(name)) {
		    spec.setGroup(getTextContent(reader));
		} else if (XmlTagBundle.TYPE.getTag().equals(name)) {
		    spec.setType(getTextContent(reader));
		} else if (XmlTagBundle.POWERCONSUM.getTag().equals(name)) {
		    spec.setPowerconsum(
			    Integer.parseInt(getTextContent(reader)));
		} else if (XmlTagBundle.COOLINGSYS.getTag().equals(name)) {
		    spec.setCoolingsys(getTextContent(reader));
		} else if (XmlTagBundle.PORT.getTag().equals(name)) {
		    Port port = new Port();
		    if (reader.getAttributeValue(ZERO_ITEM) != null) {
			port.setType(reader.getAttributeValue(ZERO_ITEM));
		    } else {
			port.setType("");
		    }
		    port.setCount(Integer.parseInt(getTextContent(reader)));
		    spec.getPorts().add(port);
		}
	    } else if (type == XMLStreamReader.END_ELEMENT) {
		String name = reader.getLocalName();
		if (XmlTagBundle.COMPUTERPART.getTag().equals(name)) {
		    device.setSpecification(spec);
		    return;
		}
	    }
	}
    }

    private String getTextContent(XMLStreamReader reader)
	    throws XMLStreamException {
	String text = null;
	if (reader.hasNext()) {
	    reader.next();
	    text = reader.getText();
	}
	return text;
    }
}
