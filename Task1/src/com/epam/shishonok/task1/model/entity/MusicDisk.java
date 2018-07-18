package com.epam.shishonok.task1.model.entity;

import com.epam.shishonok.task1.model.entity.interfaces.MusicList;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The {@code MusicDisk} class described container for instance of
 * {@link MusicTrack} class. It contains parameter {@code String} name and some
 * action provided {@link MusicList} interface.
 * 
 * @version 1 12.07.2018
 * @author Alexander Shishonok
 */
public class MusicDisk implements MusicList {

    private String name;
    private List<MusicTrack> tracklist;

    public MusicDisk() {
	tracklist = new ArrayList<MusicTrack>();
    }

    public MusicDisk(String name) {
	this.name = name;
	tracklist = new ArrayList<MusicTrack>();
    }

    public MusicDisk(String name, List<MusicTrack> tracklist) {
	this.name = name;
	this.tracklist = tracklist;
    }

    public MusicDisk(MusicDisk disk) {
	this(disk.getName(), disk.getTracklist());
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public List<MusicTrack> getTracklist() {
	return tracklist;
    }

    public void setTracklist(List<MusicTrack> tracklist) {
	this.tracklist = tracklist;
    }

    @Override
    public boolean add(MusicTrack track) {
	return tracklist.add(track);
    }

    @Override
    public MusicTrack remove(int index) {
	return tracklist.remove(index);
    }

    @Override
    public MusicTrack get(int index) {
	return tracklist.get(index);
    }

    @Override
    public int size() {
	return tracklist.size();
    }

    @Override
    public void clear() {
	tracklist.clear();
    }

    @Override
    public void sort(Comparator<MusicTrack> comparator) {
	tracklist.sort(comparator);
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result
		+ ((tracklist == null) ? 0 : tracklist.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	MusicDisk other = (MusicDisk) obj;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (tracklist == null) {
	    if (other.tracklist != null)
		return false;
	} else if (!tracklist.equals(other.tracklist))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append(this.getClass().getSimpleName()).append("@")
		.append("name=").append(name).append(", tracklist=")
		.append(tracklist);
	return builder.toString();
    }

}
