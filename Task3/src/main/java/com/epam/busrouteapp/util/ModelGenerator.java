package com.epam.busrouteapp.util;

import java.util.List;
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
    public static int passengerCount = 0;

    public static Passenger getPassenger(List<BusStop> allStops) {
	return new Passenger(passengerCount++,
		allStops.get(new Random().nextInt(allStops.size())));
    }

    public static Bus getBus(int id, int maxPassengers) {
	return new Bus(id, maxPassengers);
    }

    public static void addPassengersOnRoute(List<BusStop> allStops,
	    int numberPerStop) {
	allStops.forEach((busStop) -> {
	    for (int j = 0; j < numberPerStop; j++) {
		busStop.getPassengerOnStop()
			.add(ModelGenerator.getPassenger(allStops));
	    }
	});
    }
}
