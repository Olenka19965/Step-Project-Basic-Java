package scr;

import scr.Flight.FlightController;
import scr.Flight.FlightDAO;
import scr.Flight.FlightService;
import scr.Flight.NotFoundException;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws NotFoundException, FileNotFoundException {
        FlightDAO flightDAO = new FlightDAO();
        FlightService flightService = new FlightService(flightDAO);
        FlightController flightController = new FlightController(flightService);
        flightDAO.loadFromFile();
        flightController.run();
    }
}
