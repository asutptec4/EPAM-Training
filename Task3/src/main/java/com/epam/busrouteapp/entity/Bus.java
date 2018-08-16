package com.epam.busrouteapp.entity;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class describe bus for route simulation.
 * 
 * @version 1 15.08.2018
 * @author Alexander Shishonok
 */
public class Bus implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(Bus.class);
    private static final int BUS_SPEED = 10;

    private int id;
    private BusStop[] route;
    private int currentStop;
    private int maxPassengers;
    private ArrayDeque<Passenger> passengers;
    private boolean isAscendDirect;
    // private Lock lock = new ReentrantLock();

    public Bus(int id, int maxPassengers, BusStop[] route) {
	this.id = id;
	this.maxPassengers = maxPassengers;
	this.route = route;
	currentStop = 0;
	isAscendDirect = true;
	passengers = new ArrayDeque<Passenger>(maxPassengers);
    }

    @Override
    public void run() {
	while (true) {
	    try {
		// go to next stop
		driveToNextStop();
		TimeUnit.SECONDS.sleep(BUS_SPEED);
		// try to get place
		getBusStopPlace();
	    } catch (InterruptedException e) {
		Thread.currentThread().interrupt();
		LOGGER.error(this + " out of route!");
	    }
	}
    }

    @Override
    public String toString() {
	return "Bus [id=" + id + "]";
    }

    private void driveToNextStop() {
	if (currentStop == 0 && !isAscendDirect) {
	    isAscendDirect = !isAscendDirect;
	}
	if (currentStop == route.length - 1 && isAscendDirect) {
	    isAscendDirect = !isAscendDirect;
	}
	if (currentStop < route.length - 1 && isAscendDirect) {
	    currentStop++;
	} else {
	    currentStop--;
	}
	LOGGER.info(this + " moving to " + route[currentStop]);

    }

    private void getBusStopPlace() {
	try {
	    route[currentStop].getStopPlace().acquire();
	    LOGGER.info(
		    route[currentStop] + " : " + this + " parking at stop.");
	    LOGGER.info(route[currentStop] + " : " + this + " unload - "
		    + unloadPassengers());
	    LOGGER.info(route[currentStop] + " : " + this + " load - "
		    + loadPassengers());
	} catch (InterruptedException e) {
	    e.printStackTrace();
	    LOGGER.error("Bus can not parking at bus stop.");
	} finally {
	    route[currentStop].getStopPlace().release();
	    LOGGER.info(
		    route[currentStop] + " : " + this + " go away from stop.");
	}
    }

    private int loadPassengers() {
	int count = 0;
	while (passengers.size() < maxPassengers
		&& !route[currentStop].getPassengerOnStop().isEmpty()) {
	    passengers.add(route[currentStop].getPassengerOnStop().pop());
	    count++;
	}
	return count;
    }

    private int unloadPassengers() {
	int count = 0;
	for (Iterator<Passenger> iterator = passengers.iterator(); iterator
		.hasNext();) {
	    Passenger passenger = iterator.next();
	    if (passenger.getDestination().equals(route[currentStop])) {
		iterator.remove();
		count++;
	    }
	}
	return count;
    }
}
