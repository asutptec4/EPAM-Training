package com.epam.textparseapp.util;

/**
 * Enum used for storing regex string.
 * 
 * @version 1 31.07.2018
 * @author Alexander Shishonok
 */
public enum RegExpBundle {

    BLOCK(".*?\\n+"), PARAGRAPH("^\\s*[\\p{Alpha}].+[\\.\\!\\?\\...]\n+"),
    LISTING(""), SENTENCE(".*?[.!?]"), WORD("\\w+"),
    BLOCK_SEPARATOR("\n"), WORD_SEPARATOR(" ");

    private String expression;

    private RegExpBundle(String expression) {
	this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }
    
}
