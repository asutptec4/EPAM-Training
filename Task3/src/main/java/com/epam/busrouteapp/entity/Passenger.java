package com.epam.busrouteapp.entity;

/**
 * Class describe passenger for route simulation. Contain three field: count, id
 * and destination. Static field use for counting instances of class.
 * 
 * @version 2 16.08.2018
 * @author Alexander Shishonok
 */
public class Passenger {

    private static int count = 0;
    private int id;
    private BusStop destination;

    public Passenger(BusStop destination) {
	this.id = count++;
	this.destination = destination;
    }

    public BusStop getDestination() {
	return destination;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((destination == null) ? 0 : destination.hashCode());
	result = prime * result + id;
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
	Passenger other = (Passenger) obj;
	if (destination == null) {
	    if (other.destination != null)
		return false;
	} else if (!destination.equals(other.destination))
	    return false;
	if (id != other.id)
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Passenger [id=" + id + "]";
    }
}
