package com.epam.shishonok.task1.view;

import com.epam.shishonok.task1.model.entity.MusicDisk;

/**
 * Display {@link MusicDisk} object in a special way through a console.
 * 
 * @version 1 15.07.2018
 * @author Alexander Shishonok
 */
public class MusicDiskView extends View {
    public static final String SPLITERATOR = "|";

    /**
     * Print {@link MusicDisk} object to a console.
     * 
     * @param msg
     *            object of message
     * 
     * @see View#print(Object)
     */
    @Override
    public void print(Object msg) {
	if (msg instanceof MusicDisk) {
	    MusicDisk disk = (MusicDisk) msg;
	    if (disk.getName() != null) {
		super.print(disk.getName());
	    }
	    for (int i = 0; i < disk.size(); i++) {
		StringBuilder builder = new StringBuilder();
		builder.append(i + 1).append(SPLITERATOR)
			.append(disk.get(i).getName()).append(SPLITERATOR)
			.append(disk.get(i).getAuthor()).append(SPLITERATOR)
			.append(disk.get(i).getStyle()).append(SPLITERATOR)
			.append(disk.get(i).getLength());
		super.print(builder);
	    }
	} else {
	    super.print(msg);
	}
    }

}
