package com.epam.busrouteapp.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.busrouteapp.entity.Bus;
import com.epam.busrouteapp.entity.BusStop;
import com.epam.busrouteapp.util.ModelGenerator;

/**
 * Class controller for simulation bus moving.
 * 
 * @version 1 15.08.2018
 * @author Alexander Shishonok
 */
public class BusSimulationController {

    private static final Logger LOGGER = LogManager
	    .getLogger(BusSimulationController.class);
    private static final int BUSSTOP_COUNT = 4;
    private static final int BUS_COUNT = 7;
    private static final int BUS_CAPACITY = 20;
    private static final int PASSENGER_COUNT_PER_STOPS = 100;

    public void start() {
	LOGGER.debug("Simulation start...");
	LOGGER.debug("Bus stop count - " + BUSSTOP_COUNT);
	LOGGER.debug("Bus count - " + BUS_COUNT);
	HashSet<BusStop> set = new HashSet<BusStop>();
	ExecutorService busService = Executors.newFixedThreadPool(BUS_COUNT);
	for (int i = 0; i < BUS_COUNT; i++) {
	    Bus bus = ModelGenerator.getBus(i, BUS_CAPACITY);
	    set.addAll(bus.getRoute().getBusStops());
	    busService.execute(bus);
	}
	ModelGenerator.addPassengersOnRoute(new ArrayList<BusStop>(set),
		PASSENGER_COUNT_PER_STOPS);
	while (true) {
	    set.forEach((el) -> LOGGER
		    .info("On " + el.getName() + " bus stop there are "
			    + el.getPassengerOnStop().size() + " passengers."));
	    try {
		TimeUnit.SECONDS.sleep(30);
	    } catch (InterruptedException e) {
		Thread.currentThread().interrupt();
		LOGGER.error("Main thread is down!");
	    }
	}
    }

}
