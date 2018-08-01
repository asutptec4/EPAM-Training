package com.epam.textparseapp.service.parser;

import com.epam.textparseapp.entity.SimpleTextElement;
import com.epam.textparseapp.entity.TextElement;

/**
 * Base class for text parser.
 * 
 * @version 1 31.07.2018
 * @author Alexander Shishonok
 */
public abstract class BaseTextParser {

    private BaseTextParser next;

    public BaseTextParser() {
    }

    public BaseTextParser(BaseTextParser next) {
	this.next = next;
    }

    public void setNext(BaseTextParser next) {
	this.next = next;
    }

    /**
     * Parse {@code String} content to {@link TextElement} blocks.
     * 
     * @param content
     *            input string for parsing.
     * @return an instance of a class that implements {@code TextElement}
     *         interface.
     */
    public abstract TextElement parse(String content);

    /**
     * Check if current parser has next parser, will used next parser, else
     * create {@link SimpleTextElement} instance.
     * 
     * @param content
     *            input string for parsing.
     * @return a {@code SimpleTextElement} instance (leaf elements of
     *         {@code TextElement} hierarchy).
     */
    protected TextElement parseNext(String content) {
	if (next != null) {
	    return next.parse(content);
	} else {
	    return new SimpleTextElement(content);
	}
    }

}
