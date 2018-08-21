package com.epam.busrouteapp.entity;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class describe bus for route simulation.
 * 
 * @version 2 17.08.2018
 * @author Alexander Shishonok
 */
public class Bus implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(Bus.class);
    private static final int MAX_BUS_SPEED = 30;
    private static Exchanger<Route> exchanger = new Exchanger<>();
    private static final int BUS_WAITING_TIME = 10;

    private int id;
    private Route route;
    private int currentStop;
    private int maxPassengers;
    private ArrayDeque<Passenger> passengers;
    private boolean isAscendDirect;
    private int unloadCounter = 0;

    public Bus(int id, int maxPassengers) {
	this.id = id;
	this.maxPassengers = maxPassengers;
	this.route = Route.getInstance();
	currentStop = new Random().nextInt(route.getBusStops().size());
	isAscendDirect = true;
	passengers = new ArrayDeque<Passenger>(maxPassengers);
    }

    public Route getRoute() {
	return route;
    }

    /**
     * Start logic cycle of bus.
     * 
     * @see java.lang.Runnable#run()
     * 
     */
    @Override
    public void run() {
	while (true) {
	    try {
		// go to next stop
		driveToNextStop();
		TimeUnit.SECONDS.sleep(new Random().nextInt(MAX_BUS_SPEED));
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

    /**
     * Method chose next bus stop.
     */
    private void driveToNextStop() {
	if (currentStop == 0 && !isAscendDirect) {
	    isAscendDirect = !isAscendDirect;
	}
	if (currentStop == route.getBusStops().size() - 1 && isAscendDirect) {
	    isAscendDirect = !isAscendDirect;
	}
	if (currentStop < route.getBusStops().size() - 1 && isAscendDirect) {
	    currentStop++;
	} else {
	    currentStop--;
	}
	LOGGER.info(
		this + " moving to " + route.getBusStops().get(currentStop));
    }

    /**
     * Method get parking place on bus stop. After granted place, bus unload and
     * load passenger.
     */
    private void getBusStopPlace() {
	try {
	    route.getBusStops().get(currentStop).getStopPlace().acquire();
	    LOGGER.info(route.getBusStops().get(currentStop) + " : " + this
		    + " parking at stop.");
	    int unloadCount = unloadPassengers();
	    LOGGER.info(route.getBusStops().get(currentStop) + " : " + this
		    + " unload - " + unloadCount);
	    // if count of unsuccessful attempts of unload passenger more than
	    // stops in route use exchangeRoute
	    if (unloadCount == 0
		    && unloadCounter++ >= route.getBusStops().size()) {
		exchangeRoute();
		unloadCounter = 0;
	    }
	    loadPassengers();
	} catch (InterruptedException e) {
	    e.printStackTrace();
	    LOGGER.error("Bus can not parking at bus stop.");
	} finally {
	    route.getBusStops().get(currentStop).getStopPlace().release();
	    LOGGER.info(route.getBusStops().get(currentStop) + " : " + this
		    + " go away from stop with " + passengers.size()
		    + " passengers");
	}
    }

    /**
     * Exchange route between two bus.
     * 
     * @throws InterruptedException
     */
    private void exchangeRoute() throws InterruptedException {
	LOGGER.info(this + " trying to change route.");
	try {
	    route = exchanger.exchange(route, BUS_WAITING_TIME,
		    TimeUnit.SECONDS);
	} catch (TimeoutException e) {
	    LOGGER.info(this + " - route not changed.");
	}
	LOGGER.info(this + " - route changed.");
    }

    /**
     * Load passenger in bus.
     * 
     * @return count passenger in bus.
     */
    private void loadPassengers() {
	while (passengers.size() < maxPassengers && !route.getBusStops()
		.get(currentStop).getPassengerOnStop().isEmpty()) {
	    passengers.add(route.getBusStops().get(currentStop)
		    .getPassengerOnStop().pop());
	}
    }

    /**
     * Unload passengers from bus.
     * 
     * @return count of unloaded passengers
     */
    private int unloadPassengers() {
	int count = 0;
	for (Iterator<Passenger> iterator = passengers.iterator(); iterator
		.hasNext();) {
	    Passenger passenger = iterator.next();
	    if (passenger.getDestination()
		    .equals(route.getBusStops().get(currentStop))) {
		iterator.remove();
		count++;
	    }
	}
	return count;
    }
}
