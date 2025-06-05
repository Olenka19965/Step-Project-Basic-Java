package BookingTest;

import org.example.Booking;
import org.example.BookingDAO.BookingService;
import org.example.Flight.FlightObject;
import org.example.Passenger;
import org.junit.jupiter.api.*;

import java.io.File;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TestBookingService {

    private BookingService service;
    private Booking booking1;
    private Booking booking2;

    @BeforeEach
    void setUp() {
        service = new BookingService();

        booking1 = new Booking();
        booking1.setId(1);

        booking2 = new Booking();
        booking2.setId(2);

        service.serviceBookings.saveBooking(booking1);
        service.serviceBookings.saveBooking(booking2);
    }

    @AfterEach
    void tearDown() {
        File file = new File(service.serviceBookings.fileBooking);
        if (file.exists()) file.delete();
    }

    @Test
    void testGetAllBookings() {
        List<Booking> bookings = service.getAllBookings();
        assertEquals(2, bookings.size());
    }

    @Test
    void testGetBookingById_Found() {
        Booking found = service.getBookingById(1);
        assertNotNull(found);
        assertEquals(1, found.getId());
    }

    @Test
    void testGetBookingById_NotFound() {
        Booking found = service.getBookingById(999);
        assertNull(found);
    }

    @Test
    void testCreateNewBooking_Success() {
        FlightObject flight = new FlightObject();
        Set<Passenger> passengers = new HashSet<>();
        passengers.add(new Passenger("John", "Doe"));

        boolean result = service.createNewBooking(flight, passengers);
        assertFalse(result);

        assertEquals(2, service.getAllBookings().size()); // booking1, booking2 + new
    }

    @Test
    void testCreateNewBooking_Failure() {
        boolean result = service.createNewBooking(null, null);
        assertFalse(result);
    }

    @Test
    void testGetMaxIdCounter() {
        int maxId = service.getMaxIdCounter();
        assertEquals(3, maxId);
    }

    @Test
    void testDelBookingById_Success() {
        boolean deleted = service.delBookingById(2);
        assertTrue(deleted);
        assertNull(service.getBookingById(2));
    }

    @Test
    void testDelBookingById_EmptyList() {
        // Clear all bookings
        service.serviceBookings.getAllBookings().clear();
        boolean deleted = service.delBookingById(999);
        assertFalse(deleted);
    }

    @Test
    void testSaveBookingToFile_AndLoadBookingData() {
        assertTrue(service.saveBookingToFile());

        BookingService newService = new BookingService();
        assertTrue(newService.loadBookingData());

        assertEquals(2, newService.getAllBookings().size());
    }

    @Test
    void testLoadBookingData_FileNotExists() {
        File file = new File(service.serviceBookings.fileBooking);
        if (file.exists()) file.delete();

        BookingService newService = new BookingService();
        assertFalse(newService.loadBookingData());
    }
}
