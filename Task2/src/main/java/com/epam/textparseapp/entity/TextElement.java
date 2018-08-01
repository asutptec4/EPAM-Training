package com.epam.textparseapp.entity;

/**
 * Basic interface for text elements.
 * 
 * @version 1 27.07.2018
 * @author Alexander Shishonok
 */
public interface TextElement {

    /**
     * Method return string representation of {@code TextElement}'s content.
     * 
     * @return string representation of element
     */
    String print();

    /**
     * Add child text element to current text element.
     * 
     * @param element
     *            {@code TextElement}'s object
     * @return true if child element was added
     */
    boolean add(TextElement element);
}
