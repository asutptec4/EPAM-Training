package com.epam.textparseapp.exception;

/**
 * Root exception class of text parser application.
 * 
 * @version 1 30.07.2018
 * @author Alexander Shishonok
 */
public class TextParseAppExceprion extends Exception {

    private static final long serialVersionUID = 1L;

    public TextParseAppExceprion() {
    }

    public TextParseAppExceprion(String arg0, Throwable arg1) {
	super(arg0, arg1);
    }

    public TextParseAppExceprion(String arg0) {
	super(arg0);
    }

    public TextParseAppExceprion(Throwable arg0) {
	super(arg0);
    }

}
