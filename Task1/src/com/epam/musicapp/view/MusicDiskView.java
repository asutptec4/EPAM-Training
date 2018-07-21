package com.epam.musicapp.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.epam.musicapp.entity.MusicDisk;

/**
 * Display {@link MusicDisk} object in a special way through a console.
 * 
 * @version 1 15.07.2018
 * @author Alexander Shishonok
 */
public class MusicDiskView {
    
    public static final String SPLITERATOR = "|";
    private static final Logger LOGGER = LogManager.getLogger(MusicDiskView.class);

    /**
     * Print {@link MusicDisk} object to a console.
     * 
     * @param msg
     *            object of message
     */
    public void print(Object msg) {
	if (msg instanceof MusicDisk) {
	    MusicDisk disk = (MusicDisk) msg;
	    if (disk.getName() != null) {
		LOGGER.info(disk.getName());
	    }
	    for (int i = 0; i < disk.size(); i++) {
		StringBuilder builder = new StringBuilder();
		builder.append(i + 1).append(SPLITERATOR)
			.append(disk.get(i).getName()).append(SPLITERATOR)
			.append(disk.get(i).getAuthor()).append(SPLITERATOR)
			.append(disk.get(i).getStyle()).append(SPLITERATOR)
			.append(disk.get(i).getLength());
		LOGGER.info(builder);
	    }
	} else {
	    LOGGER.info(msg);
	}
    }

}
