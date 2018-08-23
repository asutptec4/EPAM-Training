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
import com.epam.xmlparseapp.util.XMLTagBundle;

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
		    if (XMLTagBundle.DEVICE.getTag().equals(name)) {
			Device device = new Device();
			buildDevice(device, reader);
			getDevices().add(device);
		    } else if (XMLTagBundle.COMPUTERPART.getTag()
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
		if (XMLTagBundle.MANUFACTURER.getTag().equals(name)) {
		    device.setManufacturer(getTextContent(reader));
		} else if (XMLTagBundle.MODELNAME.getTag().equals(name)) {
		    device.setModelname(getTextContent(reader));
		} else if (XMLTagBundle.CATEGORY.getTag().equals(name)) {
		    device.setCategory(getTextContent(reader));
		} else if (XMLTagBundle.PARTNUMBER.getTag().equals(name)) {
		    device.setPartnumber(getTextContent(reader));
		} else if (XMLTagBundle.ORIGIN.getTag().equals(name)) {
		    device.setOrigin(getTextContent(reader));
		} else if (XMLTagBundle.RELEASEDATE.getTag().equals(name)) {
		    device.setReleasedate(
			    LocalDate.parse(getTextContent(reader)));
		}
		if (XMLTagBundle.PRICE.getTag().equals(name)) {
		    Price price = new Price();
		    price.setCurrency(reader.getAttributeValue(ZERO_ITEM));
		    price.setValue(Float.parseFloat(getTextContent(reader)));
		    if (price.getCurrency() == null) {
			price.setCurrency(XMLTagBundle.USD.getTag());
		    }
		    device.setPrice(price);
		}
		if (XMLTagBundle.SPECIFICATION.getTag().equals(name)) {
		    return;
		}
	    } else if (type == XMLStreamReader.END_ELEMENT) {
		String name = reader.getLocalName();
		if (XMLTagBundle.DEVICE.getTag().equals(name)) {
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
		if (XMLTagBundle.GROUP.getTag().equals(name)) {
		    spec.setGroup(getTextContent(reader));
		} else if (XMLTagBundle.TYPE.getTag().equals(name)) {
		    spec.setType(getTextContent(reader));
		} else if (XMLTagBundle.POWERCONSUM.getTag().equals(name)) {
		    spec.setPowerconsum(
			    Integer.parseInt(getTextContent(reader)));
		} else if (XMLTagBundle.COOLINGSYS.getTag().equals(name)) {
		    spec.setCoolingsys(getTextContent(reader));
		} else if (XMLTagBundle.PORT.getTag().equals(name)) {
		    Port port = new Port();
		    port.setType(reader.getAttributeValue(ZERO_ITEM));
		    port.setCount(Integer.parseInt(getTextContent(reader)));
		    spec.getPorts().add(port);
		}
	    } else if (type == XMLStreamReader.END_ELEMENT) {
		String name = reader.getLocalName();
		if (XMLTagBundle.COMPUTERPART.getTag().equals(name)) {
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
