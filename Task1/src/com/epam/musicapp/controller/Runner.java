package com.epam.musicapp.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Used to start music app.
 * 
 * @version 1 13.07.2018
 * @author Alexander Shishonok
 */
public class Runner {
    
    private static final Logger LOGGER = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {
	LOGGER.debug("Application started ...");
	new MusicAppController().start();
	LOGGER.debug("Application closed.");
    }

}
