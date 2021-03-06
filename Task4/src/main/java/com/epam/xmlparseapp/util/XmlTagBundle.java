package com.epam.xmlparseapp.util;

/**
 * Enumeration-container that have String representation of XML tag.
 * 
 * @version 1 30.08.2018
 * @author Alexander Shishonok
 */
public enum XmlTagBundle {

    DEVICES("devices"), DEVICE("device"), COMPUTERPART("computerpart"),
    CRITICAL("critical"), MANUFACTURER("manufacturer"), MODELNAME("modelname"),
    CATEGORY("category"), PARTNUMBER("partnumber"), ORIGIN("origin"),
    RELEASEDATE("releasedate"), PRICE("price"), CURRENCY("currency"),
    SPECIFICATION("specification"), GROUP("group"), TYPE("type"),
    POWERCONSUM("powerconsum"), COOLINGSYS("coolingsys"), PORT("port"),
    USD("USD");

    private String tag;

    private XmlTagBundle(String tag) {
	this.tag = tag;
    }

    public String getTag() {
	return tag;
    }

}
