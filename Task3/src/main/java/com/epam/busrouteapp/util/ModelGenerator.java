package com.epam.busrouteapp.util;

import java.util.Random;

import com.epam.busrouteapp.entity.Bus;
import com.epam.busrouteapp.entity.BusStop;
import com.epam.busrouteapp.entity.Passenger;

public class ModelGenerator {

    public static final BusStop[] ALLSTOPS;
    private static final String[] BUSSTONAME = { "yellow", "red", "green",
	    "black", "orange", "blue", "white", "brown", "cyan", "magenta" };
    private static final int MAXPLACESBYSTOPS = 5;
    
    static {
	ALLSTOPS = new BusStop[BUSSTONAME.length];
	for (int i = 0; i < ALLSTOPS.length; i++) {
	    ALLSTOPS[i] = new BusStop(BUSSTONAME[i],
		    new Random().nextInt(MAXPLACESBYSTOPS));
	}
    }

    public static BusStop[] getRoute(int stopsCount) {
	BusStop[] route = new BusStop[stopsCount];
	for (int i = 0; i < stopsCount; i++) {
	    route[i] = ALLSTOPS[i];
	}
	return route;
    }
    
    public static Passenger getPassenger(BusStop[] stops) {
	return new Passenger(stops[new Random().nextInt(stops.length)]);
    }
    
    public static Bus getBus(int id, int maxPassengers, BusStop[] route) {
	return new Bus(id, maxPassengers, route);
    }
    
    public static void addPassengersOnRoute(BusStop[] route, int numberPerStop) {
	for (int i = 0; i < route.length; i++) {
	    for (int j = 0; j < numberPerStop; j++) {
		route[i].getPassengerOnStop()
			.add(ModelGenerator.getPassenger(route));
	    }
	}
    }
}
