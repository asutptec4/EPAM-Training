package com.epam.musicapp.entity;

/**
 * The {@code MusicTrack} class described musical composition. It contains 4
 * fields: name of song, song's author, style and duration.
 * 
 * @version 1 12.07.2018
 * @author Alexander Shishonok
 */
public class MusicTrack {

    private String name;
    private String author;
    private MusicStyle style;
    private int length;

    public MusicTrack() {
    }

    public MusicTrack(String name, String author, MusicStyle style,
	    int length) {
	this.name = name;
	this.author = author;
	this.style = style;
	this.length = length;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getAuthor() {
	return author;
    }

    public void setAuthor(String author) {
	this.author = author;
    }

    public MusicStyle getStyle() {
	return style;
    }

    public void setStyle(MusicStyle style) {
	this.style = style;
    }

    public int getLength() {
	return length;
    }

    public void setLength(int length) {
	this.length = length;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((author == null) ? 0 : author.hashCode());
	result = prime * result + length;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + ((style == null) ? 0 : style.hashCode());
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
	MusicTrack other = (MusicTrack) obj;
	if (author == null) {
	    if (other.author != null)
		return false;
	} else if (!author.equals(other.author))
	    return false;
	if (length != other.length)
	    return false;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (style != other.style)
	    return false;
	return true;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append(this.getClass().getSimpleName()).append("@")
		.append("name=").append(name).append(", author=").append(author)
		.append(", style=").append(style).append(", length=")
		.append(length);
	return builder.toString();
    }

}
