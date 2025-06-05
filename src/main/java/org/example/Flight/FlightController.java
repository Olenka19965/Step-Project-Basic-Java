package org.example.Flight;

import org.example.Exeption.InvalidDateException;
import org.example.Exeption.InvalidDestinationException;
import org.example.Exeption.NotFoundException;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;

public class FlightController {
    FlightService flightService;
    public FlightController(){
        this.flightService=new FlightService();
    }
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    public List<FlightObject> getAllFlights() {
        return flightService.getAllFlights();
    }

    public void showAllFlights(){
        List<FlightObject>flights = flightService.getAllFlights();
        flights.forEach(System.out :: println);
    }

    public FlightObject findFlightById(String id) throws NotFoundException {
        return flightService.getFlightById(id);
    }

    public List<FlightObject> searchFlights(String destination, LocalDate date, int passengers) {
        return flightService.searchFlights(destination, date, passengers);
    }

    public static LocalDate parseDate(String dateStr) throws InvalidDateException {
        try {
            return LocalDate.parse(dateStr);
        } catch (Exception e) {
            throw new InvalidDateException("Неправильний формат дати. Використовуйте yyyy-mm-dd.");
        }
    }

    public static void validateDestination(String destination) throws InvalidDestinationException {
        if (!destination.matches("[a-zA-Z]+")) {
            throw new InvalidDestinationException("Ви ввели назву міста не вірно, введіть назву міста англійською.");
        }
    }

    public void showTodayFlights(){
        List <FlightObject> todayFlights = flightService.getTodayFlights();
        if (todayFlights.isEmpty()){
            System.out.println("На сьогодні немає рейсів.");
        }else todayFlights.forEach(System.out::println);
    }
    public void generateFlights() throws FileNotFoundException {
        flightService.generateFlights();
    }

    public boolean loadFromFile() throws FileNotFoundException {
        return flightService.loadFromFile();
    }
}