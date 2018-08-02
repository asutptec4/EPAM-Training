package com.epam.textparseapp.entity;

/**
 * Basic class(leaf) implements {@code TextElement} interface, which stored
 * primitive text element, ex. words, punctuation, etc. It contains 1 field -
 * content, for string representation of a text element.
 * 
 * @version 1 27.07.2018
 * @author Alexander Shishonok
 */
public class SimpleTextElement implements TextElement {

    private String content;

    public SimpleTextElement() {
    }

    public SimpleTextElement(String content) {
	this.content = content;
    }

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

    @Override
    public String print() {
	return content;
    }

}
