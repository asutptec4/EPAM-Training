package com.epam.busrouteapp.entity;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Class describe passenger for route simulation.
 * 
 * @version 1 15.08.2018
 * @author Alexander Shishonok
 */
public class Passenger implements Callable<Passenger> {

    private int id;
    private BusStop destination;
    private BusStop currentStop;
    private Bus currentBus;

    public Passenger(int id, BusStop destination, BusStop currentStop) {
	this.id = id;
	this.destination = destination;
	this.currentStop = currentStop;
	this.currentBus = null;
    }

    @Override
    public Passenger call() throws Exception {
	System.out.println(this + " appear at " + currentStop + " and want to "
		+ destination);
	while (!currentStop.getName().equals(destination.getName())) {
	    if (currentBus == null) {
		// check bus route
		if (currentStop.getBusList().isEmpty()) {
		    TimeUnit.SECONDS.sleep(3);
		}
		for (Bus bus : currentStop.getBusList()) {
		    if (bus.getRoute().contain(destination)
			    && !bus.isMoving()) {
			// go into bus
			if (bus.getPlace()) {
			    currentBus = bus;
			    System.out
				    .println(this + " enter in " + currentBus);
			    break;
			}
		    }
		}
	    } else {
		// move to destination
		if (currentBus.isMoving()) {
		    TimeUnit.SECONDS.sleep(1);
		}
		// leave the bus
		if (currentBus.getCurrentStop().equals(destination)
			&& !currentBus.isMoving()) {
		    currentBus.leavePlace();
		    currentStop = currentBus.getCurrentStop();
		    System.out.println(this + " leave " + currentBus);
		    currentBus = null;
		}
	    }
	}
	System.out.println(this + " arrive to destination.");
	return null;
    }

    @Override
    public String toString() {
	return "Passenger [id=" + id + "]";
    }
}
