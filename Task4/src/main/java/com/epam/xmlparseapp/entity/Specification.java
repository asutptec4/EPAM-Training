package com.epam.xmlparseapp.entity;

import java.util.ArrayList;
import java.util.List;

public class Specification {

    private String group;
    private String type;
    private int powerconsum;
    private String coolingsys;
    private List<Port> ports;

    public String getGroup() {
	return group;
    }

    public void setGroup(String group) {
	this.group = group;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public int getPowerconsum() {
	return powerconsum;
    }

    public void setPowerconsum(int powerconsum) {
	this.powerconsum = powerconsum;
    }

    public String getCoolingsys() {
	return coolingsys;
    }

    public void setCoolingsys(String coolingsys) {
	this.coolingsys = coolingsys;
    }

    public List<Port> getPorts() {
	if (ports == null) {
	    ports = new ArrayList<Port>();
	}
	return ports;
    }

    public void setPorts(List<Port> ports) {
	this.ports = ports;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((coolingsys == null) ? 0 : coolingsys.hashCode());
	result = prime * result + ((group == null) ? 0 : group.hashCode());
	result = prime * result + ((ports == null) ? 0 : ports.hashCode());
	result = prime * result + powerconsum;
	result = prime * result + ((type == null) ? 0 : type.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Specification other = (Specification) obj;
	if (coolingsys == null) {
	    if (other.coolingsys != null)
		return false;
	} else if (!coolingsys.equals(other.coolingsys))
	    return false;
	if (group == null) {
	    if (other.group != null)
		return false;
	} else if (!group.equals(other.group))
	    return false;
	if (ports == null) {
	    if (other.ports != null)
		return false;
	} else if (!ports.equals(other.ports))
	    return false;
	if (powerconsum != other.powerconsum)
	    return false;
	if (type == null) {
	    if (other.type != null)
		return false;
	} else if (!type.equals(other.type))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return getClass().getSimpleName() + "@"
		+ (group != null ? "group=" + group + ", " : "")
		+ (type != null ? "type=" + type + ", " : "") + "powerconsum="
		+ powerconsum + ", "
		+ (coolingsys != null ? "coolingsys=" + coolingsys + ", " : "")
		+ (ports != null ? "ports=" + ports : "") + "]";
    }

}
