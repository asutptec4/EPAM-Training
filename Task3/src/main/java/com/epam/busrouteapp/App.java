package com.epam.busrouteapp;

import com.epam.busrouteapp.controller.BusSimulationController;

/**
 * Main class used for starting bus route simulation.
 * 
 * @version 1 15.08.2018
 * @author Alexander Shishonok
 */
public class App {
    public static void main(String[] args) {
	new BusSimulationController().start();
    }
}
