package com.epam.musicapp.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import com.epam.musicapp.entity.MusicDisk;
import com.epam.musicapp.exception.MusicDiskStorageException;

/**
 * Class provide method to save for a disk {@link MusicDisk} in file.
 * 
 * @version 1 15.07.2018
 * @author Alexander Shishonok
 */
public class MusicDiskStorageManager {

    static final String DEST_EXTENSION = ".txt";
    static final String SPLITERATOR = "|";

    /**
     * Method save {@code MusicDisk} object to file.
     * 
     * @param disk
     *            saved disk
     * @param destFolder
     *            destination folder
     * @throws MusicDiskStorageException {@link MusicDiskStorageException}
     */
    public static void save(MusicDisk disk, String destFolder)
	    throws MusicDiskStorageException {
	String outputFileName = destFolder + disk.getName().toLowerCase()
		+ DEST_EXTENSION;
	try (PrintWriter pw = new PrintWriter(new File(outputFileName))) {
	    pw.println(disk.getName());
	    for (int i = 0; i < disk.size(); i++) {
		StringBuilder builder = new StringBuilder();
		builder.append(disk.get(i).getName()).append(SPLITERATOR)
			.append(disk.get(i).getAuthor()).append(SPLITERATOR)
			.append(disk.get(i).getStyle()).append(SPLITERATOR)
			.append(disk.get(i).getLength());
		pw.println(builder);
	    }
	} catch (FileNotFoundException e) {
	    throw new MusicDiskStorageException(
		    "Couldnt create file " + outputFileName, e);
	}
    }

}
