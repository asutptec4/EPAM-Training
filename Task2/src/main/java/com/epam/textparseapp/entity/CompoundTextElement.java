package com.epam.textparseapp.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Class(container) implements {@code TextElement} interface, which can consist
 * of any number other {@code TextElement} object.
 * 
 * @version 1 27.07.2018
 * @author Alexander Shishonok
 */
public class CompoundTextElement implements TextElement {

    private List<TextElement> childs;

    public CompoundTextElement() {
	childs = new ArrayList<TextElement>();
    }

    public List<TextElement> getChilds() {
	return childs;
    }

    public void setChilds(List<TextElement> childs) {
	this.childs = childs;
    }

    @Override
    public String print() {
	StringBuilder result = new StringBuilder();
	for (TextElement element : childs) {
	    result.append(element.print());
	}
	return result.toString();
    }

    /**
     * Add child text element to current text element.
     * 
     * @param element
     *            {@code TextElement}'s object
     * @return true if child element was added
     */
    public boolean add(TextElement element) {
	return childs.add(element);
    }

}
