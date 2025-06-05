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
//import java.util.List;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class TestBookingController {
//
//    private BookingService bookingServiceMock;
//    private BookingController controller;
//
//    @BeforeEach
//    void setUp() {
//        bookingServiceMock = mock(BookingService.class);
//        controller = new BookingController() {
//            {
//                this.bookingService = bookingServiceMock;
//            }
//        };
//    }
//
//    @Test
//    void testGetAllBookings() {
//        Booking booking = new Booking();
//        booking.setId(1);
//        when(bookingServiceMock.getAllBookings()).thenReturn(List.of(booking));
//
//        List<Booking> result = controller.getAllBookings();
//
//        assertEquals(1, result.size());
//        assertEquals(1, result.getFirst().getId());
//        verify(bookingServiceMock).getAllBookings();
//    }
//
//    @Test
//    void testDisplayAllBookings() {
//        controller.displayAllBookings();
//        verify(bookingServiceMock).displayAllBookings();
//    }
//
//    @Test
//    void testCreateNewBooking() {
//        FlightObject flight = new FlightObject();
//        Passenger passenger = new Passenger();
//        Set<Passenger> passengers = Set.of(passenger);
//
//        when(bookingServiceMock.createNewBooking(flight, passengers)).thenReturn(true);
//
//        boolean result = controller.createNewBooking(flight, passengers);
//
//        assertTrue(result);
//        verify(bookingServiceMock).createNewBooking(flight, passengers);
//    }
//
//    @Test
//    void testGetMaxIdCounter() {
//        when(bookingServiceMock.getMaxIdCounter()).thenReturn(42);
//
//        int result = controller.getMaxIdCounter();
//
//        assertEquals(42, result);
//        verify(bookingServiceMock).getMaxIdCounter();
//    }
//
//    @Test
//    void testGetBookingById() {
//        Booking booking = new Booking();
//        booking.setId(5);
//        when(bookingServiceMock.getBookingById(5)).thenReturn(booking);
//
//        Booking result = controller.getBookingById(5);
//
//        assertNotNull(result);
//        assertEquals(5, result.getId());
//        verify(bookingServiceMock).getBookingById(5);
//    }
//
//    @Test
//    void testDelBookingById() {
//        when(bookingServiceMock.delBookingById(7)).thenReturn(true);
//
//        boolean result = controller.delBookingById(7);
//
//        assertTrue(result);
//        verify(bookingServiceMock).delBookingById(7);
//    }
//
//    @Test
//    void testLoadBookingData() {
//        when(bookingServiceMock.loadBookingData()).thenReturn(true);
//
//        boolean result = controller.loadBookingData();
//
//        assertTrue(result);
//        verify(bookingServiceMock).loadBookingData();
//    }
//
//    @Test
//    void testSaveBookingToFile() {
//        when(bookingServiceMock.saveBookingToFile()).thenReturn(true);
//
//        boolean result = controller.saveBookingToFile();
//
//        assertTrue(result);
//        verify(bookingServiceMock).saveBookingToFile();
//    }
//}