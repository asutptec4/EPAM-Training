package com.epam.shishonok.task1.util;

import java.util.Random;
import com.epam.shishonok.task1.model.entity.MusicStyle;
import com.epam.shishonok.task1.model.entity.MusicTrack;

/**
 * Class used for randomly generating instances of {@link MusicTrack} class.
 * 
 * @version 1 14.07.2018
 * @author Alexander Shishonok
 */
public class RandomTrackGenerator {

    static final int MIN_LENGTH = 100;
    static final int MAX_LENGTH = 300;

    /**
     * Create new instance of {@link MusicTrack} class.
     * @return {@link MusicTrack} object
     */
    public static MusicTrack createTrack() {
	MusicTrack track = new MusicTrack();
	MusicStyle style = genStyle();
	track.setStyle(style);
	track.setAuthor(genAuthor(style.toString()));
	track.setName(genName(style.toString()));
	track.setLength(genLength(MIN_LENGTH, MAX_LENGTH));
	return track;
    }

    private static MusicStyle genStyle() {
	MusicStyle[] styles = MusicStyle.values();
	return styles[new Random().nextInt(styles.length)];
    }

    private static String genAuthor(String str) {
	String[] author = { "singer", "group", "band" };
	return str.toLowerCase() + " "
		+ author[new Random().nextInt(author.length)];
    }

    private static String genName(String str) {
	String[] names = { "excellent", "cool", "sad", "beautiful",
		"greatest" };
	return names[new Random().nextInt(names.length)] + " "
		+ str.toLowerCase() + " song";
    }

    private static int genLength(int min, int max) {
	return new Random().nextInt(max - min + 1) + min;
    }
}
