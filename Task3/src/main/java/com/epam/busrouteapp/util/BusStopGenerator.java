package com.epam.busrouteapp.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.epam.busrouteapp.entity.BusStop;


/**
 * A utility class for random generation bus stops set for route.
 * 
 * @version 1 16.07.2018
 * @author Alexander Shishonok
 */
public class BusStopGenerator {

    private static final String[] BUSSTONAME = { "yellow", "red", "green",
	    "black", "orange", "blue", "white", "brown", "cyan", "magenta" };
    private static final int MAXPLACESBYSTOPS = 4;
    private static final int MINPLACESBYSTOPS = 2;

    public static ArrayList<BusStop> getRoute(int stopsCount) {
	if (stopsCount > BUSSTONAME.length) {
	    stopsCount = BUSSTONAME.length;
	}
	Set<BusStop> set = new HashSet<BusStop>(stopsCount);
	Random random = new Random();
	while (set.size() < stopsCount) {
	    set.add(new BusStop(BUSSTONAME[random.nextInt(BUSSTONAME.length)],
		    random.nextInt(MAXPLACESBYSTOPS - MINPLACESBYSTOPS + 1)
			    + MINPLACESBYSTOPS));
	}
	return new ArrayList<BusStop>(set);
    }

}
