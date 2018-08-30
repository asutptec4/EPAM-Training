package com.epam.xmlparseapp.xmlparser;

import java.util.HashSet;
import java.util.Set;

import com.epam.xmlparseapp.entity.Device;

/**
 * Basic class of XML parser. Contain {@code Set} field of {@code Device}
 * instances for parsing results.
 * 
 * @version 1 30.08.2018
 * @author Alexander Shishonok
 */
public abstract class AbstractParser {

    protected static final int ZERO_ITEM = 0;

    protected Set<Device> devices;

    public AbstractParser() {
	devices = new HashSet<Device>();
    }

    public Set<Device> getDevices() {
	return devices;
    }

    /**
     * Method for parsing XML file. Must generate {@code Device} or his subclass
     * instances and put into {@code Set} collection.
     * 
     * @param fileName
     */
    abstract public void buildDeviceSet(String fileName);
}
