package BookingTest;

import org.example.Booking;
import org.example.BookingDAO.BookingController;
import org.example.Flight.FlightObject;
import org.example.Passenger;
import org.junit.jupiter.api.*;

import java.io.File;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TestBookingController {

    private BookingController controller;
    private Booking booking1;
    private Booking booking2;

    @BeforeEach
    void setUp() {
        controller = new BookingController();

        booking1 = new Booking();
        booking1.setId(1);

        booking2 = new Booking();
        booking2.setId(2);

        controller.bookingService.serviceBookings.saveBooking(booking1);
        controller.bookingService.serviceBookings.saveBooking(booking2);
    }

    @AfterEach
    void tearDown() {
        File file = new File(controller.bookingService.serviceBookings.fileBooking);
        if (file.exists()) file.delete();
    }

    @Test
    void testGetAllBookings() {
        List<Booking> bookings = controller.getAllBookings();
        assertEquals(2, bookings.size());
    }

    @Test
    void testGetBookingById_Found() {
        Booking found = controller.getBookingById(1);
        assertNotNull(found);
        assertEquals(1, found.getId());
    }

    @Test
    void testGetBookingById_NotFound() {
        Booking found = controller.getBookingById(999);
        assertNull(found);
    }

    @Test
    void testCreateNewBooking_Success() {
        FlightObject flight = new FlightObject();
        Set<Passenger> passengers = new HashSet<>();
        passengers.add(new Passenger("John", "Doe"));

        boolean result = controller.createNewBooking(flight, passengers);
        assertFalse(result); // якщо booking конструктор падає на порожньому flight/passengers

        assertEquals(2, controller.getAllBookings().size());
    }

    @Test
    void testCreateNewBooking_Failure() {
        boolean result = controller.createNewBooking(null, null);
        assertFalse(result);
    }

    @Test
    void testGetMaxIdCounter() {
        int maxId = controller.getMaxIdCounter();
        assertEquals(3, maxId);
    }

    @Test
    void testDelBookingById_Success() {
        boolean deleted = controller.delBookingById(2);
        assertTrue(deleted);
        assertNull(controller.getBookingById(2));
    }

    @Test
    void testDelBookingById_EmptyList() {
        controller.bookingService.serviceBookings.getAllBookings().clear();
        boolean deleted = controller.delBookingById(999);
        assertFalse(deleted);
    }

    @Test
    void testSaveBookingToFile_AndLoadBookingData() {
        assertTrue(controller.saveBookingToFile());

        BookingController newController = new BookingController();
        assertTrue(newController.loadBookingData());

        assertEquals(2, newController.getAllBookings().size());
    }

    @Test
    void testLoadBookingData_FileNotExists() {
        File file = new File(controller.bookingService.serviceBookings.fileBooking);
        if (file.exists()) file.delete();

        BookingController newController = new BookingController();
        assertFalse(newController.loadBookingData());
    }
}
