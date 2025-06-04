package scr.Flight;

import scr.Exeption.InvalidDateException;
import scr.Exeption.InvalidDestinationException;
import scr.Exeption.NotFoundException;

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

//    public FlightObject findFlightById(String id) {
//
//        try {FlightObject flightObject = flightService.getFlightById(id);
//            System.out.println(flightObject);
//            return  flightObject;
//        } catch (NotFoundException e) {
//            System.out.println("Рейс з ID " + id + " не знайдено.");
//            return  null;
//        }
//    }
//    public List<FlightObject> searchFlights(String destination, LocalDate date, int passengers) {
//        List<FlightObject> results = flightService.searchFlights(destination, date, passengers);
//        if (results.isEmpty()) {
//            System.out.println("Рейсів не знайдено.");
//        } else {
//            results.forEach(System.out::println);
//        }
//        return results;
//    }

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