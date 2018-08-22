package com.epam.xmlparseapp.entity;

import java.time.LocalDate;

public class Device {

    private String manufacturer;
    private String modelname;
    private String category;
    private String partnumber;
    private String origin;
    private LocalDate releasedate;
    private Price price;

    public String getManufacturer() {
	return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
	this.manufacturer = manufacturer;
    }

    public String getModelname() {
	return modelname;
    }

    public void setModelname(String modelname) {
	this.modelname = modelname;
    }

    public String getCategory() {
	return category;
    }

    public void setCategory(String category) {
	this.category = category;
    }

    public String getPartnumber() {
	return partnumber;
    }

    public void setPartnumber(String partnumber) {
	this.partnumber = partnumber;
    }

    public String getOrigin() {
	return origin;
    }

    public void setOrigin(String origin) {
	this.origin = origin;
    }

    public LocalDate getReleasedate() {
	return releasedate;
    }

    public void setReleasedate(LocalDate releasedate) {
	this.releasedate = releasedate;
    }

    public Price getPrice() {
	return price;
    }

    public void setPrice(Price price) {
	this.price = price;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((category == null) ? 0 : category.hashCode());
	result = prime * result
		+ ((manufacturer == null) ? 0 : manufacturer.hashCode());
	result = prime * result
		+ ((modelname == null) ? 0 : modelname.hashCode());
	result = prime * result + ((origin == null) ? 0 : origin.hashCode());
	result = prime * result
		+ ((partnumber == null) ? 0 : partnumber.hashCode());
	result = prime * result + ((price == null) ? 0 : price.hashCode());
	result = prime * result
		+ ((releasedate == null) ? 0 : releasedate.hashCode());
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
	Device other = (Device) obj;
	if (category == null) {
	    if (other.category != null)
		return false;
	} else if (!category.equals(other.category))
	    return false;
	if (manufacturer == null) {
	    if (other.manufacturer != null)
		return false;
	} else if (!manufacturer.equals(other.manufacturer))
	    return false;
	if (modelname == null) {
	    if (other.modelname != null)
		return false;
	} else if (!modelname.equals(other.modelname))
	    return false;
	if (origin == null) {
	    if (other.origin != null)
		return false;
	} else if (!origin.equals(other.origin))
	    return false;
	if (partnumber == null) {
	    if (other.partnumber != null)
		return false;
	} else if (!partnumber.equals(other.partnumber))
	    return false;
	if (price == null) {
	    if (other.price != null)
		return false;
	} else if (!price.equals(other.price))
	    return false;
	if (releasedate == null) {
	    if (other.releasedate != null)
		return false;
	} else if (!releasedate.equals(other.releasedate))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return getClass().getSimpleName() + "@"
		+ (manufacturer != null ? "manufacturer=" + manufacturer + ", "
			: "")
		+ (modelname != null ? "modelname=" + modelname + ", " : "")
		+ (category != null ? "category=" + category + ", " : "")
		+ (partnumber != null ? "partnumber=" + partnumber + ", " : "")
		+ (origin != null ? "origin=" + origin + ", " : "")
		+ (releasedate != null ? "releasedate=" + releasedate + ", "
			: "")
		+ (price != null ? "price=" + price : "");
    }
    
}
