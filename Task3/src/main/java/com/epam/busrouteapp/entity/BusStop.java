package com.epam.busrouteapp.entity;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * Class describe bus stop for route simulation.
 * 
 * @version 1 15.08.2018
 * @author Alexander Shishonok
 */
public class BusStop {

    private String name;
    private Semaphore stopPlace;
    private ArrayList<Bus> busList;

    public BusStop(String name, int placeCount) {
	this.name = name;
	stopPlace = new Semaphore(placeCount, true);
	busList = new ArrayList<Bus>(placeCount);
    }

    public String getName() {
	return name;
    }

    public ArrayList<Bus> getBusList() {
	return busList;
    }

    public void getStopPlace(Bus bus) throws InterruptedException {
	stopPlace.acquire();
	bus.setMoving(false);
	System.out.println(
		this + " : bus #" + bus + " parking at stop.");
	busList.add(bus);
    }

    public void freeStopPlace(Bus bus) {
	busList.remove(bus);
	System.out.println(
		this + " : bus #" + bus + " go away from stop.");
	bus.setMoving(true);
	stopPlace.release();
    }
        
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
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
	BusStop other = (BusStop) obj;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "BusStop [" + (name != null ? "name=" + name : "") + "]";
    }

}
