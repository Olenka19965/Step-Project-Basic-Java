package org.example.Flight;

import org.example.Exeption.NotFoundException;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class FlightService {
    FlightDAO flightDAO;
    public FlightService(){
        this.flightDAO = new FlightDAO();
    }
    public FlightService(FlightDAO flightDAO){
        this.flightDAO = flightDAO;
    }
    public void generateFlights() throws FileNotFoundException {
        flightDAO.generateFlights();
        flightDAO.saveToFile();
        flightDAO.loadFromFile();
    }
    public List<FlightObject> getAllFlights(){//всі рейси
        return flightDAO.getAllFlights();
    }

    public boolean loadFromFile() throws FileNotFoundException {
        return flightDAO.loadFromFile();
    }

    public FlightObject getFlightById(String id)throws NotFoundException {
        return flightDAO.getFlightById(id)
                .orElseThrow(() -> new NotFoundException("Рейс з ID " + id + " не знайдено."));
    }

    public List<FlightObject> searchFlights(String destination, LocalDate date, int passengers) {//пошук рейсу за умовами
        return flightDAO.getAllFlights().stream()
                .filter(f->f.getDestination().toString().equalsIgnoreCase(destination))
                .filter(f -> f.getDepartureTime().toLocalDate().equals(date))
                .filter(f->f.getAvailableSeats()>= passengers)
                .collect(Collectors.toList());

    }
    public void updateFlightAndSave(FlightObject updatedFlight){
        flightDAO.updateFlight(updatedFlight);
        flightDAO.saveToFile();
    }
    public List<FlightObject> getTodayFlights(){
        LocalDate today = LocalDate.now();
        return flightDAO.getAllFlights().stream()
                .filter(f->f.getDepartureTime().toLocalDate().equals(today))
                .sorted(Comparator.comparing(f -> f.getDepartureTime()))
                .collect(Collectors.toList());
    }

}