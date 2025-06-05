package FlightTest;//package scr.Test.FlightTest;
//
//import org.junit.jupiter.api.*;
//import scr.Exeption.NotFoundException;
//import scr.Flight.*;
//import java.time.*;
//import java.util.List;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class TestFlightService {
//    private FlightService flightService;
//    private FlightDAO flightDAO;
//
//    @BeforeEach
//    void setUp() {
//        flightDAO = new FlightDAO();
//        flightService = new FlightService(flightDAO); // передаємо DAO в сервіс
//    }
//
//    @Test
//    void testGetAllFlights() {
//        FlightObject flight = new FlightObject("FL001", FlightObject.Destination.BERLIN, LocalDateTime.now().plusDays(1), 100);
//        flightDAO.addFlight(flight);
//
//        List<FlightObject> flights = flightService.getAllFlights();
//        assertFalse(flights.isEmpty(), "Список усіх рейсів не має бути порожнім");
//    }
//
//    @Test
//    void testGetFlightByIdSuccess() {
//        FlightObject flight = new FlightObject("FL002", FlightObject.Destination.LONDON, LocalDateTime.now().plusDays(1), 50);
//        flightDAO.addFlight(flight);
//
//        FlightObject result = assertDoesNotThrow(() -> flightService.getFlightById("FL002"));
//        assertEquals("FL002", result.getId());
//    }
//
//    @Test
//    void testGetFlightByIdNotFound() {
//        String fakeId = "NON_EXISTENT_ID";
//        Exception exception = assertThrows(NotFoundException.class, () -> {
//            flightService.getFlightById(fakeId);
//        });
//        assertTrue(exception.getMessage().contains(fakeId));
//    }
//
//    @Test
//    void testSearchFlights() {
//        LocalDateTime dateTime = LocalDateTime.now().plusDays(2);
//        FlightObject flight = new FlightObject("FL003", FlightObject.Destination.PARIS, dateTime, 20);
//        flightDAO.addFlight(flight);
//
//        String destination = "PARIS";
//        LocalDate date = dateTime.toLocalDate();
//        int passengers = 1;
//
//        List<FlightObject> foundFlights = flightService.searchFlights(destination, date, passengers);
//        assertFalse(foundFlights.isEmpty(), "Пошук рейсів повинен повертати результати");
//
//        for (FlightObject f : foundFlights) {
//            assertEquals(destination.toLowerCase(), f.getDestination().toString().toLowerCase());
//            assertEquals(date, f.getDepartureTime().toLocalDate());
//            assertTrue(f.getAvailableSeats() >= passengers);
//        }
//    }
//
//    @Test
//    void testUpdateFlightAndSave() throws NotFoundException {
//        FlightObject flight = new FlightObject("FL004", FlightObject.Destination.ROME, LocalDateTime.now().plusDays(3), 30);
//        flightDAO.addFlight(flight);
//
//        FlightObject updatedFlight = new FlightObject("FL004", FlightObject.Destination.ROME, flight.getDepartureTime(), 35);
//        flightService.updateFlightAndSave(updatedFlight);
//
//        FlightObject result = flightService.getFlightById("FL004");
//        assertEquals(35, result.getAvailableSeats());
//    }
//
//    @Test
//    void testGetTodayFlights() {
//        LocalDateTime now = LocalDateTime.now();
//        FlightObject todayFlight = new FlightObject("FL1001", FlightObject.Destination.PARIS, now, 10);
//        flightDAO.addFlight(todayFlight);
//
//        List<FlightObject> todayFlights = flightService.getTodayFlights();
//        assertTrue(todayFlights.stream().anyMatch(f -> f.getId().equals("FL1001")));
//        for (FlightObject f : todayFlights) {
//            assertEquals(LocalDate.now(), f.getDepartureTime().toLocalDate());
//        }
//    }
//}
//
