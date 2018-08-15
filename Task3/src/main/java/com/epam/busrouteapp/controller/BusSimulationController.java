package com.epam.busrouteapp.controller;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.epam.busrouteapp.entity.Bus;
import com.epam.busrouteapp.entity.BusStop;
import com.epam.busrouteapp.entity.Passenger;
import com.epam.busrouteapp.entity.Route;

/**
 * Class controller for simulation bus moving.
 * 
 * @version 1 15.08.2018
 * @author Alexander Shishonok
 */
public class BusSimulationController {

    private static final int BUSSTOP_COUNT = 4;
    private static final int BUS_COUNT = 3;
    private static final int PASSENGER_COUNT = 40;

    public void start() {
	BusStop[] busStops = new BusStop[BUSSTOP_COUNT];
	busStops[0] = new BusStop("red", 2);
	busStops[1] = new BusStop("yellow", 2);
	busStops[2] = new BusStop("green", 2);
	busStops[3] = new BusStop("black", 2);
	Route route = new Route();
	for (int i = 0; i < busStops.length; i++) {
	    route.addBusStop(busStops[i]);
	}
	ExecutorService busService = Executors.newFixedThreadPool(BUS_COUNT);
	for (int i = 0; i < BUS_COUNT; i++) {
	    busService.submit(new Bus(i, 5, route.copy()));
	}
	ExecutorService passengerService = Executors.newCachedThreadPool();
	for (int i = 0; i < PASSENGER_COUNT; i++) {
	    int j = new Random().nextInt(BUSSTOP_COUNT);
	    passengerService.submit(new Passenger(i, busStops[j],
		    busStops[(j + BUSSTOP_COUNT - 1) % BUSSTOP_COUNT]));
	}
    }

}
