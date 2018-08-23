package com.epam.xmlparseapp.xmlparser;

import java.util.HashSet;
import java.util.Set;

import com.epam.xmlparseapp.entity.Device;

public abstract class AbstractParser {
    
    protected static final int ZERO_ITEM = 0;
    
    protected Set<Device> devices;

    public AbstractParser() {
	devices = new HashSet<Device>();
    }

    public Set<Device> getDevices() {
        return devices;
    }
    
    abstract public void buildDeviceSet(String fileName);
}
