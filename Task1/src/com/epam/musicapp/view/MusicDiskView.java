package com.epam.musicapp.view;

import com.epam.musicapp.entity.MusicDisk;

/**
 * Display {@link MusicDisk} object in a special way through a console.
 * 
 * @version 1 15.07.2018
 * @author Alexander Shishonok
 */
public class MusicDiskView {
    public static final String SPLITERATOR = "|";

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
		System.out.println(disk.getName());
	    }
	    for (int i = 0; i < disk.size(); i++) {
		StringBuilder builder = new StringBuilder();
		builder.append(i + 1).append(SPLITERATOR)
			.append(disk.get(i).getName()).append(SPLITERATOR)
			.append(disk.get(i).getAuthor()).append(SPLITERATOR)
			.append(disk.get(i).getStyle()).append(SPLITERATOR)
			.append(disk.get(i).getLength());
		System.out.println(builder);
	    }
	} else {
	    System.out.println(msg);
	}
    }

}
