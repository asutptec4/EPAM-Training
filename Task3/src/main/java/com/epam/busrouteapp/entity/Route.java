package com.epam.busrouteapp.entity;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.epam.busrouteapp.util.BusStopGenerator;

/**
 * Class describe bus route. Thread safe singleton with ROUTE_NUMBERS instances.
 * 
 * @version 1 17.08.2018
 * @author Alexander Shishonok
 */
public class Route {

    private static final int ROUTE_NUMBERS = 3;
    private static ArrayList<Route> instance;
    private ArrayList<BusStop> busStops;
    private static Lock lock = new ReentrantLock();
    private static int count = 0;

    private Route(ArrayList<BusStop> busStops) {
	this.busStops = busStops;
    }

    /**
     * Get unique route for bus.
     * @return random route instance
     */
    public static Route getInstance() {
	lock.lock();
	try {
	    if (instance == null) {
		instance = new ArrayList<Route>(ROUTE_NUMBERS);
		for (int i = 0; i < ROUTE_NUMBERS; i++) {
		    instance.add(new Route(BusStopGenerator.getRoute(5)));
		}
	    }
	} finally {
	    lock.unlock();
	}
	return instance.get((count++) % ROUTE_NUMBERS);
    }

    /**
     * Get array of {@code BusStop} instances.
     * @return
     */
    public ArrayList<BusStop> getBusStops() {
	return busStops;
    }
}
