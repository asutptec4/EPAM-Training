package com.epam.busrouteapp.util;

import java.util.Random;

import com.epam.busrouteapp.entity.Bus;
import com.epam.busrouteapp.entity.BusStop;
import com.epam.busrouteapp.entity.Passenger;

/**
 * A utility class for random generation of passenger .
 * 
 * @version 1 30.07.2018
 * @author Alexander Shishonok
 */
public class ModelGenerator {

    public static Passenger getPassenger(BusStop[] stops) {
	return new Passenger(stops[new Random().nextInt(stops.length)]);
    }
    
    public static Bus getBus(int id, int maxPassengers) {
	return new Bus(id, maxPassengers);
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
