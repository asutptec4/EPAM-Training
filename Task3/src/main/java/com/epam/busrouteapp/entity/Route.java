package com.epam.busrouteapp.entity;

import java.util.ArrayDeque;
import java.util.Iterator;

/**
 * Class describe bus route which consist any number of bus stops
 * {@link BusStop}.
 * 
 * @version 1 15.08.2018
 * @author Alexander Shishonok
 */
public class Route {

    private ArrayDeque<BusStop> routeList;
    private Iterator<BusStop> iterator;
    private boolean isDirectRoute;

    public Route() {
	this.routeList = new ArrayDeque<>();
	this.isDirectRoute = true;
    }

    public void addBusStop(BusStop busStop) {
	routeList.addLast(busStop);
    }

    public BusStop getNextStop() {
	if (iterator == null) {
	    iterator = getIterator();
	}
	if (iterator.hasNext()) {
	    return iterator.next();
	} else {
	    isDirectRoute = !isDirectRoute;
	    iterator = getIterator();
	    iterator.next(); // skip final bus stop in route
	    return iterator.next();
	}
    }
    
    public boolean contain(BusStop busStop) {
	return routeList.contains(busStop);
    }
    
    public Route copy() {
	Route copy = new Route();
	copy.routeList = this.routeList.clone();
	return copy;
    }

    private Iterator<BusStop> getIterator() {
	if (isDirectRoute) {
	    return routeList.iterator();
	} else {
	    return routeList.descendingIterator();
	}
    }
}
