package FlightTest;//package scr.Test.FlightTest;
//
//import org.junit.jupiter.api.*;
//import scr.Exeption.NotFoundException;
//import scr.Flight.*;
//import java.io.*;
//import java.time.*;
//import java.util.List;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class TestFlightController {
//    private FlightDAO flightDAO;
//    private FlightService flightService;
//    private FlightController flightController;
//    private final PrintStream originalOut = System.out;
//    private ByteArrayOutputStream outContent;
//
//    @BeforeEach
//    void setUp() throws Exception {
//        flightDAO = new FlightDAO();
//        flightDAO.loadFromFile(); // Якщо файлу нема, згенерує рейси
//        flightService = new FlightService(flightDAO);
//        flightController = new FlightController(flightService);
//        outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//    }
//
//    @AfterEach
//    void tearDown() {
//        System.setOut(originalOut);
//    }
//
//    @Test
//    void testShowAllFlights() {
//        flightController.showAllFlights();
//        String output = outContent.toString();
//        assertTrue(output.contains("FL"), "Вивід повинен містити ID рейсів");
//    }
//
//    @Test
//    void testFindFlightByIdFound() throws Exception {
//        FlightObject testFlight = new FlightObject("FL1001", FlightObject.Destination.LONDON, LocalDateTime.of(2025, 6, 1, 12, 0), 10);
//        flightDAO.addFlight(testFlight);
//        FlightObject found = flightController.findFlightById("FL1001");
//        assertNotNull(found);
//        assertEquals("FL1001", found.getId());
//    }
//
//    @Test
//    void testFindFlightByIdNotFound() {
//        assertThrows(NotFoundException.class, () -> {
//            flightController.findFlightById("FL0000");
//        });
//    }
//
//    @Test
//    void testSearchFlightsFound() {
//        FlightObject testFlight = new FlightObject("FL1002", FlightObject.Destination.PARIS, LocalDateTime.of(2025, 6, 10, 15, 30), 5);
//        flightDAO.addFlight(testFlight);
//        LocalDate date = LocalDate.of(2025, 6, 10);
//        List<FlightObject> results = flightController.searchFlights("PARIS", date, 3);
//        assertFalse(results.isEmpty(), "Повинні знайти рейс");
//        assertTrue(results.stream().anyMatch(f -> f.getId().equals("FL1002")));
//    }
//
//    @Test
//    void testSearchFlightsNotFound() {
//        LocalDate date = LocalDate.of(2025, 1, 1);
//        List<FlightObject> results = flightController.searchFlights("NOWHERE", date, 1);
//        assertTrue(results.isEmpty(), "Рейси не повинні бути знайдені");
//    }
//
//    @Test
//    void testShowTodayFlights() {
//        flightController.showTodayFlights();
//        String output = outContent.toString();
//        assertTrue(output.contains("FL") || output.contains("На сьогодні немає рейсів."));
//    }
//}