package com.epam.textparseapp.exception;

/**
 * This exception is thrown when there is a problem saving or loading a text
 * file.
 * 
 * @version 1 30.07.2018
 * @author Alexander Shishonok
 */
public class TextFileException extends TextParseAppExceprion {

    private static final long serialVersionUID = 1L;

    public TextFileException() {
    }

    public TextFileException(String arg0, Throwable arg1) {
	super(arg0, arg1);
    }

    public TextFileException(String arg0) {
	super(arg0);
    }

    public TextFileException(Throwable arg0) {
	super(arg0);
    }

}
