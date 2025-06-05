//package BookingTest;
//
//import org.example.Booking;
//import org.example.BookingDAO.BookingController;
//import org.example.BookingDAO.BookingService;
//import org.example.Flight.FlightObject;
//import org.example.Passenger;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class BookingControllerTest {
//
//    private BookingController bookingController;
//    private BookingService bookingServiceMock;
//
//    @BeforeEach
//    void setUp() {
//        bookingServiceMock = mock(BookingService.class);
//        bookingController = new BookingController();
//        // reflection to inject mock (optional but useful if not using constructor injection)
//        try {
//            var field = BookingController.class.getDeclaredField("bookingService");
//            field.setAccessible(true);
//            field.set(bookingController, bookingServiceMock);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Test
//    void testGetAllBookings() {
//        List<Booking> mockList = new ArrayList<>();
//        when(bookingServiceMock.getAllBookings()).thenReturn(mockList);
//
//        List<Booking> result = bookingController.getAllBookings();
//        assertNotNull(result);
//        assertEquals(mockList, result);
//        verify(bookingServiceMock).getAllBookings();
//    }
//
//    @Test
//    void testCreateNewBooking() {
//        FlightObject mockFlight = mock(FlightObject.class);
//        Set<Passenger> passengers = new HashSet<>();
//        when(bookingServiceMock.createNewBooking(mockFlight, passengers)).thenReturn(true);
//
//        boolean result = bookingController.createNewBooking(mockFlight, passengers);
//        assertTrue(result);
//        verify(bookingServiceMock).createNewBooking(mockFlight, passengers);
//    }
//
//    @Test
//    void testGetMaxIdCounter() {
//        when(bookingServiceMock.getMaxIdCounter()).thenReturn(42);
//
//        int result = bookingController.getMaxIdCounter();
//        assertEquals(42, result);
//        verify(bookingServiceMock).getMaxIdCounter();
//    }
//
//    @Test
//    void testGetBookingById() {
//        Booking mockBooking = mock(Booking.class);
//        when(bookingServiceMock.getBookingById(1)).thenReturn(mockBooking);
//
//        Booking result = bookingController.getBookingById(1);
//        assertNotNull(result);
//        assertEquals(mockBooking, result);
//        verify(bookingServiceMock).getBookingById(1);
//    }
//
//    @Test
//    void testDelBookingById() {
//        when(bookingServiceMock.delBookingById(1)).thenReturn(true);
//
//        boolean result = bookingController.delBookingById(1);
//        assertTrue(result);
//        verify(bookingServiceMock).delBookingById(1);
//    }
//
//    @Test
//    void testLoadBookingData() {
//        when(bookingServiceMock.loadBookingData()).thenReturn(true);
//
//        boolean result = bookingController.loadBookingData();
//        assertTrue(result);
//        verify(bookingServiceMock).loadBookingData();
//    }
//
//    @Test
//    void testSaveBookingToFile() {
//        when(bookingServiceMock.saveBookingToFile()).thenReturn(true);
//
//        boolean result = bookingController.saveBookingToFile();
//        assertTrue(result);
//        verify(bookingServiceMock).saveBookingToFile();
//    }
//
//    @Test
//    void testDisplayAllBookings() {
//        bookingController.displayAllBookings();
//        verify(bookingServiceMock).displayAllBookings();
//    }
//}
