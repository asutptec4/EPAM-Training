package com.epam.xmlparseapp.entity;

/**
 * The class described computer port. Has two field: type and count of same
 * ports.
 * 
 * @version 1 30.08.2018
 * @author Alexander Shishonok
 */
public class Port {

    private String type;
    private int count;

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public int getCount() {
	return count;
    }

    public void setCount(int count) {
	this.count = count;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + count;
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
	Port other = (Port) obj;
	if (count != other.count)
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
		+ (type != null ? "type=" + type + ", " : "") + "count="
		+ count;
    }

}
