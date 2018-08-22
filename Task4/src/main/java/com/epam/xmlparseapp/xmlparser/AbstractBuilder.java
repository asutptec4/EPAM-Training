package com.epam.xmlparseapp.xmlparser;

import java.util.HashSet;
import java.util.Set;

import com.epam.xmlparseapp.entity.Device;

public abstract class AbstractBuilder {
    
    protected Set<Device> devices;

    public AbstractBuilder() {
	devices = new HashSet<Device>();
    }

    public Set<Device> getDevices() {
        return devices;
    }
    
    abstract public void buildDeviceSet(String fileName);
}
