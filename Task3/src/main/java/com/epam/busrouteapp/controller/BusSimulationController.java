package com.epam.busrouteapp.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private static final int BUS_COUNT = 5;
    private static final int BUS_CAPACITY = 20;
    private static final int PASSENGER_COUNT_PER_STOPS = 200;
    private static final int MONITOR_TIME_DELAY = 30; 

    public void start() {
	LOGGER.debug("Simulation start...");
	LOGGER.debug("Bus stop count - " + BUSSTOP_COUNT);
	LOGGER.debug("Bus count - " + BUS_COUNT);
	BusStop[] route = ModelGenerator.getRoute(BUSSTOP_COUNT);
	ModelGenerator.addPassengersOnRoute(route, PASSENGER_COUNT_PER_STOPS);
	ExecutorService busService = Executors.newFixedThreadPool(BUS_COUNT);
	for (int i = 0; i < BUS_COUNT; i++) {
	    busService.submit(ModelGenerator.getBus(i, BUS_CAPACITY, route));
	}
	while (true) {
	    for (int i = 0; i < route.length; i++) {
		LOGGER.info("Number - " + route[i].getPassengerOnStop().size()
			+ " on " + route[i]);
	    }
	    try {
		TimeUnit.SECONDS.sleep(MONITOR_TIME_DELAY);
	    } catch (InterruptedException e) {
		Thread.currentThread().interrupt();
		LOGGER.error("Simulation is end.");
	    }
	}
    }

}
