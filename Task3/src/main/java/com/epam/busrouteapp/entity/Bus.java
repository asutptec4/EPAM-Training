package com.epam.busrouteapp.entity;

import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Class describe bus for route simulation.
 * 
 * @version 1 15.08.2018
 * @author Alexander Shishonok
 */
public class Bus implements Callable<Bus> {

    private int id;
    private Route route;
    private BusStop currentStop;
    private Semaphore places;
    private boolean isMoving;

    public Bus(int id, int maxPassengers, Route route) {
	this.id = id;
	this.route = route;
	currentStop = null;
	places = new Semaphore(maxPassengers);
	isMoving = true;
    }

    public boolean isMoving() {
	return isMoving;
    }
    
    public void setMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    public Route getRoute() {
        return route;
    }
    
    public BusStop getCurrentStop() {
        return currentStop;
    }

    public boolean getPlace() throws InterruptedException {
        return places.tryAcquire(1, TimeUnit.SECONDS);
    }

    public void leavePlace() {
	places.release();
    }

    @Override
    public Bus call() {
	while (true) {
	    try {
		// go to next stop
		driveToNextStop();
		// try to get place
		currentStop.getStopPlace(this);
		// waiting passengers
		System.out.println(this + " waiting passengers.");
		TimeUnit.SECONDS.sleep(10);
		// go from bus stop
		currentStop.freeStopPlace(this);
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
    }

    private void driveToNextStop() throws InterruptedException {
	currentStop = route.getNextStop();
	System.out.println(this + " moving to " + currentStop);
	TimeUnit.SECONDS.sleep(30);
    }

    @Override
    public String toString() {
	return "Bus [id=" + id + "]";
    }

}
