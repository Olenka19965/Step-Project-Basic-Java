package FlightTest;//package scr.Test.FlightTest;
//
//import org.junit.jupiter.api.*;
//import scr.Flight.*;
//import java.io.*;
//import java.time.LocalDateTime;
//import java.util.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class TestFlightDAO {
//    private FlightDAO flightDAO;
//    @BeforeEach
//    void setUp() {
//        flightDAO = new FlightDAO();
//    }
//    @Test
//    void testGenerateFlights() {
//        flightDAO.generateFlights();
//        List<FlightObject> flights = flightDAO.getAllFlights();
//        assertFalse(flights.isEmpty(), "Список рейсів не має бути порожнім після генерації");
//        long uniqueIds = flights.stream().map(FlightObject::getId).distinct().count();
//        assertEquals(flights.size(), uniqueIds, "ID рейсів мають бути унікальними");
//    }
//    @Test
//    void testAddAndGetFlightById() {
//        FlightObject flight = new FlightObject("FL9999", FlightObject.Destination.BERLIN, LocalDateTime.now(), 5);
//        flightDAO.addFlight(flight);
//        Optional<FlightObject> found = flightDAO.getFlightById("FL9999");
//        assertTrue(found.isPresent());
//        assertEquals("FL9999", found.get().getId());
//    }
//    @Test
//    void testUpdateFlight() {
//        FlightObject flight = new FlightObject("FL1234", FlightObject.Destination.LONDON, LocalDateTime.now(), 3);
//        flightDAO.addFlight(flight);
//        FlightObject updatedFlight = new FlightObject("FL1234", FlightObject.Destination.LONDON, flight.getDepartureTime(), 10);
//        flightDAO.updateFlight(updatedFlight);
//        Optional<FlightObject> found = flightDAO.getFlightById("FL1234");
//        assertTrue(found.isPresent());
//        assertEquals(10, found.get().getAvailableSeats());
//    }
//    @Test
//    void testSaveAndLoadFromFile() throws Exception {
//        File file = new File("scr/DataBase/flights.dat");
//        if (file.exists()) file.delete();
//        flightDAO.generateFlights();
//        flightDAO.saveToFile();
//        FlightDAO loadedDAO = new FlightDAO();
//        loadedDAO.loadFromFile();
//        List<FlightObject> flightsOriginal = flightDAO.getAllFlights();
//        List<FlightObject> flightsLoaded = loadedDAO.getAllFlights();
//        assertEquals(flightsOriginal.size(), flightsLoaded.size());
//        assertEquals(flightsOriginal.get(0).getId(), flightsLoaded.get(0).getId());
//    }
//    @Test
//    void testGetAllFlightsSorted() {
//        flightDAO.generateFlights();
//        List<FlightObject> flights = flightDAO.getAllFlights();
//        for (int i = 1; i < flights.size(); i++) {
//            assertFalse(flights.get(i).getDepartureTime().isBefore(flights.get(i-1).getDepartureTime()),
//                    "Рейси повинні бути відсортовані за часом вильоту");
//        }
//    }
//}