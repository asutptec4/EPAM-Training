package com.epam.shishonok.task1.model.logic;

import java.util.Comparator;
import com.epam.shishonok.task1.model.entity.MusicTrack;
import com.epam.shishonok.task1.model.entity.interfaces.MusicList;
import com.epam.shishonok.task1.model.exception.InvalidMusicDiskException;

/**
 * Class provide some methods for working with tracks {@link MusicTrack} on
 * disk {@link com.epam.shishonok.task1.model.entity.MusicDisk}.
 * 
 * @version 1 13.07.2018
 * @author Alexander Shishonok
 */
public class MusicDiskManager {

    /**
     * Method evaluate total length of track on disk.
     * 
     * @param disk
     *            container for MusicTrack which provides {@code MusicList}
     *            interface
     * @return {@code int} total length
     * @throws InvalidMusicDiskException {@link InvalidMusicDiskException}
     */
    public static int evalFullLength(MusicList disk) throws InvalidMusicDiskException {
	checkDisk(disk);
	int result = 0;
	for (int i = 0; i < disk.size(); i++) {
	    result += disk.get(i).getLength();
	}
	return result;
    }

    /**
     * Method evaluate total count of track on disk.
     * 
     * @param disk
     *            container for MusicTrack which provides {@code MusicList}
     *            interface
     * @return {@code int} total count
     * @throws InvalidMusicDiskException {@link InvalidMusicDiskException}
     */
    public static int evalTrackNumber(MusicList disk) throws InvalidMusicDiskException {
	checkDisk(disk);
	return disk.size();
    }

    /**
     * Method find track with duration between {@code minLength} and
     * {@code maxLength}.
     * 
     * @param disk
     *            container for MusicTrack which provides {@code MusicList}
     *            interface
     * @param newDisk
     *            container for founded track {@code MusicTrack}
     * @param minLength
     *            {@code int} min length of track
     * @param maxLength
     *            {@code int} max lenght of track
     * @throws InvalidMusicDiskException {@link InvalidMusicDiskException}
     */
    public static void findByTrackLength(MusicList disk, MusicList newDisk,
	    int minLength, int maxLength) throws InvalidMusicDiskException {
	checkDisk(disk);
	for (int i = 0; i < disk.size(); i++) {
	    int length = disk.get(i).getLength();
	    if (length >= minLength && length <= maxLength) {
		newDisk.add(disk.get(i));
	    }
	}
    }

    /**
     * Method sort {@code MusicList} by duration in ascending order.
     * 
     * @param disk
     *            container for MusicTrack which provides {@code MusicList}
     *            interface
     * @throws InvalidMusicDiskException {@link InvalidMusicDiskException}
     */
    public static void sortByDuration(MusicList disk) throws InvalidMusicDiskException {
	checkDisk(disk);
	disk.sort(new Comparator<MusicTrack>() {
	    @Override
	    public int compare(MusicTrack o1, MusicTrack o2) {
		if (o1.getLength() == o2.getLength()) {
		    return 0;
		} else if (o1.getLength() > o2.getLength()) {
		    return 1;
		} else {
		    return -1;
		}
	    }
	});
    }

    /**
     * Method sort {@code MusicList} by style.
     * 
     * @param disk
     *            container for MusicTrack which provides {@code MusicList}
     *            interface
     * @throws InvalidMusicDiskException {@link InvalidMusicDiskException}
     */
    public static void sortByStyle(MusicList disk) throws InvalidMusicDiskException {
	checkDisk(disk);
	disk.sort(new Comparator<MusicTrack>() {
	    @Override
	    public int compare(MusicTrack o1, MusicTrack o2) {
		return o1.getStyle().compareTo(o2.getStyle());
	    }
	});
    }
    
    private static void checkDisk(MusicList disk) throws InvalidMusicDiskException {
	if (disk == null) {
	    throw new InvalidMusicDiskException("Invalid music disk");
	}
    }
}
