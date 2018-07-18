package com.epam.shishonok.task1.model.exception;

/**
 * This exception is thrown when music disk is corrupted.
 * 
 * @version 1 16.07.2018
 * @author Alexander Shishonok
 */
public class InvalidMusicDiskException extends MusicAppException {

    private static final long serialVersionUID = 1L;

    public InvalidMusicDiskException() {
    }

    public InvalidMusicDiskException(String arg0) {
	super(arg0);
    }

    public InvalidMusicDiskException(Throwable arg0) {
	super(arg0);
    }

    public InvalidMusicDiskException(String arg0, Throwable arg1) {
	super(arg0, arg1);
    }
    
}
