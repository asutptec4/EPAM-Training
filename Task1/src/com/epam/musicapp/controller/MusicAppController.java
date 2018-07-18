package com.epam.musicapp.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.musicapp.entity.MusicDisk;
import com.epam.musicapp.exception.InvalidMusicDiskException;
import com.epam.musicapp.exception.MusicDiskStorageException;
import com.epam.musicapp.service.MusicDiskManager;
import com.epam.musicapp.service.MusicDiskStorageManager;
import com.epam.musicapp.util.RandomTrackGenerator;
import com.epam.musicapp.view.MusicDiskView;

/**
 * Main controller used to show solving of task.
 * 
 * @version 1 16.07.2018
 * @author Alexander Shishonok
 */
public class MusicAppController {
    
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int N = 10; // Number of track
    private static final int MIN_TIME = 130;
    private static final int MAX_TIME = 200;
    private static String NAME = "Tracks with duration between " + MIN_TIME
	    + " and " + MAX_TIME;
    private static final String DEST_FOLDER = "output/";
    
    public void start() {
	MusicDiskView view = new MusicDiskView();
	LOGGER.info("Create view " + view.getClass());
	// Create MusicDisk instance with N track
	MusicDisk disk = new MusicDisk("Cool disk");
	LOGGER.info("Create disk " + disk.getName());
	for (int i = 0; i < N; i++) {
	    disk.add(RandomTrackGenerator.createTrack());
	    LOGGER.info("Add track " + disk.get(i));
	}
	// Show tracks on disk
	view.print(disk);
	// Evaluation total length of disk's tracks
	try {
	    view.print(
		    "Total length is " + MusicDiskManager.evalFullLength(disk));
	    // Print track with duration between MIN_TIME and MAX_TIME
	    MusicDisk filteredDisk = new MusicDisk(NAME);
	    MusicDiskManager.findByTrackLength(disk, filteredDisk, MIN_TIME,
		    MAX_TIME);
	    view.print(filteredDisk);
	    // Sort tracks on disk by style
	    MusicDiskManager.sortByStyle(disk);
	    // Save music disk in a file
	} catch (InvalidMusicDiskException e) {
	    LOGGER.error(e.getMessage());
	}
	try {
	    MusicDiskManager.evalFullLength(null);
	} catch (InvalidMusicDiskException e) {
	    LOGGER.error(e.getMessage());
	} 
	try {
	    MusicDiskStorageManager.save(disk, DEST_FOLDER);
	} catch (MusicDiskStorageException e) {
	    LOGGER.error(e.getMessage());
	}
    }

}
