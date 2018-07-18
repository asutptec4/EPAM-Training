package com.epam.shishonok.task1.model.exception;

/**
 * Root exception class of MusicApp.
 * 
 * @version 1 16.07.2018
 * @author Alexander Shishonok
 */
public class MusicAppException extends Exception {

    private static final long serialVersionUID = 1L;

    public MusicAppException() {
    }

    public MusicAppException(String message) {
	super(message);
    }

    public MusicAppException(Throwable cause) {
	super(cause);
    }

    public MusicAppException(String message, Throwable cause) {
	super(message, cause);
    }

}
