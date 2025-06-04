package scr.Test.FlightTest;

import org.junit.jupiter.api.*;
import scr.Flight.*;
import java.io.*;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class TestFlightController {
    private FlightDAO flightDAO;
    private FlightService flightService;
    private FlightController flightController;

    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() throws Exception {
        flightDAO = new FlightDAO();
        flightDAO.loadFromFile(); // Якщо файлу нема, згенерує рейси
        flightService = new FlightService(flightDAO);
        flightController = new FlightController(flightService);
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }
    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }
    @Test
    void testShowAllFlights() {
        flightController.showAllFlights();
        String output = outContent.toString();
        assertTrue(output.contains("FL"), "Вивід повинен містити ID рейсів");
    }

//    @Test
//    void testFindFlightByIdInvalidFormatThenValid() throws Exception {
//        FlightObject testFlight = new FlightObject("FL9999", FlightObject.Destination.LONDON, LocalDateTime.of(2025, 6, 1, 12, 0), 10);
//        flightDAO.addFlight(testFlight);
//        String input = "WRONG_ID\nFL9999\n";
//        System.setIn(new ByteArrayInputStream(input.getBytes()));
//        FlightController controller = new FlightController(flightService);
//        controller.findFlightById();
//        String output = outContent.toString();
//        assertTrue(output.contains("Невірний формат ID"), "Повинно бути повідомлення про неправильний формат");
//        assertTrue(output.contains("FL9999"), "Повинно виводитись правильне ID рейсу");
//    }
//    @Test
//    void testSearchFlights() throws Exception {
//        FlightObject testFlight = new FlightObject("FL8888", FlightObject.Destination.PARIS, LocalDateTime.of(2025, 6, 10, 15, 30), 5);
//        flightDAO.addFlight(testFlight);
//        String input = "PARIS\n2025-06-10\n3\n";
//        System.setIn(new ByteArrayInputStream(input.getBytes()));
//        FlightController controller = new FlightController(flightService);
//        controller.searchFlights();
//        String output = outContent.toString();
//        assertTrue(output.contains("PARIS"), "Вивід має містити напрямок");
//        assertTrue(output.contains("FL8888"), "Вивід має містити ID рейсу");
//    }
    @Test
    void testShowTodayFlights() {
        flightController.showTodayFlights();
        String output = outContent.toString();
        boolean hasFlights = output.contains("FL");
        boolean noFlightsMessage = output.contains("На сьогодні немає рейсів.");
        assertTrue(hasFlights || noFlightsMessage, "Вивід має містити рейси на сьогодні або повідомлення про їх відсутність");
    }
}
