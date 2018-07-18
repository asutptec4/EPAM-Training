package com.epam.musicapp.entity;

import java.util.Comparator;

/**
 * Interface has method for manipulation with list of {@link MusicTrack}
 * instances.
 * 
 * @version 1 12.07.2018
 * @author Alexander Shishonok
 */
public interface MusicList {

    /**
     * Add {@code MusicTrack} object to list.
     * 
     * @param track
     *            added {@code MusicTrack} object
     * @return true if element add to list
     */
    boolean add(MusicTrack track);

    /**
     * Remove {@code MusicTrack} object from list.
     * 
     * @param index
     *            index of removing object
     * @return removing object
     */
    MusicTrack remove(int index);

    /**
     * Return {@code MusicTrack} object at index.
     * 
     * @param index
     *            required object index
     * @return required {@code MusicTrack} object
     */
    MusicTrack get(int index);

    /**
     * Return size of {@code MusicTrack}'s list.
     * 
     * @return {@code int} size of {@code MusicTrack}'s list
     */
    int size();

    /**
     * Delete all track from {@code MusicTrack}'s list.
     */
    void clear();

    /**
     * Sort {@code MusicTrack}'s list used {@code Comparator<E>}.
     * 
     * @param comparator
     *            instance of {@code Comparator<E>}
     * @see Comparator
     */
    void sort(Comparator<MusicTrack> comparator);

}
