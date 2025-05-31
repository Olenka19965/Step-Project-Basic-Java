package scr.Flight;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class FlightService {
    private final FlightDAO flightDAO;
    public FlightService(FlightDAO flightDAO){
        this.flightDAO = flightDAO;
    }

    public List<FlightObject> getAllFlights(){//всі рейси
return flightDAO.getAllFlights();
    }

    public FlightObject getFlightById(String id)throws NotFoundException{// пошук рейсу по ід
 Optional<FlightObject>flightOpt= flightDAO.getAllFlights().stream()
        .filter(f->f.getId().equalsIgnoreCase(id)).findFirst();
         return flightOpt.orElseThrow(()-> new NotFoundException("Рейс з ID " + id + " не знайдено."));
    }

    public List<FlightObject> searchFlights(String destination, LocalDate date, int passengers) {//пошук рейсу за умовами
        return flightDAO.getAllFlights().stream()
                .filter(f->f.getDestination().toString().equalsIgnoreCase(destination))
                .filter(f -> f.getDepartureTime().toLocalDate().equals(date))
                .filter(f->f.getAvailableSeats()>= passengers)
                .collect(Collectors.toList());

    }
    public void updateFlightAndSave(FlightObject updatedFlight){//оновлення рейсу
        flightDAO.updateFlight(updatedFlight);
        flightDAO.saveToFile();
    }
    public List<FlightObject> getTodayFlights(){//рейс на сьогодні
LocalDate today = LocalDate.now();
return flightDAO.getAllFlights().stream()
        .filter(f->f.getDepartureTime().toLocalDate().equals(today))
        .sorted(Comparator.comparing(f -> f.getDepartureTime()))
        .collect(Collectors.toList());
    }

}
