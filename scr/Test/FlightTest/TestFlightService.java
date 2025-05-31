package scr.Test.FlightTest;

import org.junit.jupiter.api.*;
import scr.Flight.*;
import java.time.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TestFlightService {
    private FlightDAO flightDAO;
    private FlightService flightService;
    @BeforeEach
    void setUp() {
        flightDAO = new FlightDAO();
        flightService = new FlightService(flightDAO);
        flightDAO.generateFlights();
    }
    @Test
    void testGetAllFlights() {
        List<FlightObject> flights = flightService.getAllFlights();
        assertFalse(flights.isEmpty(), "Список усіх рейсів не має бути порожнім");
    }
    @Test
    void testGetFlightByIdSuccess() {
        FlightObject firstFlight = flightDAO.getAllFlights().get(0);
        FlightObject flight = assertDoesNotThrow(() -> flightService.getFlightById(firstFlight.getId()));
        assertEquals(firstFlight.getId(), flight.getId());
    }
    @Test
    void testGetFlightByIdNotFound() {
        String fakeId = "NON_EXISTENT_ID";
        Exception exception = assertThrows(NotFoundException.class, () -> {
            flightService.getFlightById(fakeId);
        });
        assertTrue(exception.getMessage().contains(fakeId));
    }
    @Test
    void testSearchFlights() {
        FlightObject flight = flightDAO.getAllFlights().get(0);
        String destination = flight.getDestination().toString();
        LocalDate date = flight.getDepartureTime().toLocalDate();
        int passengers = 1;
        List<FlightObject> foundFlights = flightService.searchFlights(destination, date, passengers);
        assertFalse(foundFlights.isEmpty(), "Пошук рейсів повинен повертати результати");
        for (FlightObject f : foundFlights) {
            assertEquals(destination.toLowerCase(), f.getDestination().toString().toLowerCase());
            assertEquals(date, f.getDepartureTime().toLocalDate());
            assertTrue(f.getAvailableSeats() >= passengers);
        }
    }
    @Test
    void testUpdateFlightAndSave() {
        FlightObject flight = flightDAO.getAllFlights().get(0);
        FlightObject updatedFlight = new FlightObject(flight.getId(), flight.getDestination(), flight.getDepartureTime(), flight.getAvailableSeats() + 5);
        flightService.updateFlightAndSave(updatedFlight);
        FlightObject fromDAO = flightDAO.getFlightById(flight.getId()).orElseThrow();
        assertEquals(updatedFlight.getAvailableSeats(), fromDAO.getAvailableSeats());
    }
    @Test
    void testGetTodayFlights() {
        LocalDateTime now = LocalDateTime.now();
        FlightObject todayFlight = new FlightObject("FLTODAY", FlightObject.Destination.PARIS, now, 10);
        flightDAO.addFlight(todayFlight);
        List<FlightObject> todayFlights = flightService.getTodayFlights();
        assertTrue(todayFlights.stream().anyMatch(f -> f.getId().equals("FLTODAY")));
        for (FlightObject f : todayFlights) {
            assertEquals(LocalDate.now(), f.getDepartureTime().toLocalDate());
        }
    }
}

