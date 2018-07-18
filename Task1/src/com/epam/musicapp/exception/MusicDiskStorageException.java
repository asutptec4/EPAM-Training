package com.epam.musicapp.exception;

/**
 * This exception is thrown when there is a problem saving to a file.
 * 
 * @version 1 16.07.2018
 * @author Alexander Shishonok
 */
public class MusicDiskStorageException extends MusicAppException{

    private static final long serialVersionUID = 1L;

    public MusicDiskStorageException() {
    }

    public MusicDiskStorageException(String message, Throwable cause) {
	super(message, cause);
    }

    public MusicDiskStorageException(String message) {
	super(message);
    }

    public MusicDiskStorageException(Throwable cause) {
	super(cause);
    }  

}
