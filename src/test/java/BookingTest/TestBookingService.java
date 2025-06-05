//package BookingTest;
//
//import org.example.Booking;
//import org.example.BookingDAO.BookingService;
//import org.example.BookingDAO.CollectionBookingDao;
//import org.example.Flight.FlightObject;
//import org.example.Passenger;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class TestBookingService {
//
//    private BookingService service;
//    private CollectionBookingDao mockDao;
//    private Booking booking;
//
//    @BeforeEach
//    void setUp() {
//        service = new BookingService();
//        mockDao = mock(CollectionBookingDao.class);
//        service.serviceBookings = mockDao;
//
//        booking = new Booking();
//        booking.setId(1);
//    }
//
//    @Test
//    void testGetAllBookings() {
//        List<Booking> fakeList = List.of(booking);
//        when(mockDao.getAllBookings()).thenReturn(fakeList);
//
//        List<Booking> result = service.getAllBookings();
//
//        assertEquals(1, result.size());
//        verify(mockDao).getAllBookings();
//    }
//
//    @Test
//    void testDisplayAllBookings_NotEmpty() {
//        List<Booking> bookings = List.of(booking);
//        when(mockDao.getAllBookings()).thenReturn(bookings);
//
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(out));
//
//        service.displayAllBookings();
//
//        String output = out.toString();
//        assertTrue(output.contains("Список ваших бронювань:"));
//        assertTrue(output.contains("(1)"));
//    }
//
//    @Test
//    void testDisplayAllBookings_Empty() {
//        when(mockDao.getAllBookings()).thenReturn(Collections.emptyList());
//
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(out));
//
//        service.displayAllBookings();
//
//        String output = out.toString();
//        assertTrue(output.contains("Ви ще не бронювали жодного рейсу"));
//    }
//
//    @Test
//    void testCreateNewBooking_Success() {
//        FlightObject flight = mock(FlightObject.class);
//        Passenger passenger = mock(Passenger.class);
//        Set<Passenger> passengers = Set.of(passenger);
//
//        assertTrue(service.createNewBooking(flight, passengers));
//        verify(mockDao).saveBooking(any(Booking.class));
//    }
//
//    @Test
//    void testCreateNewBooking_Failure() {
//        Set<Passenger> passengers = null;
//        FlightObject flight = mock(FlightObject.class);
//
//        assertFalse(service.createNewBooking(flight, passengers));
//    }
//
//    @Test
//    void testGetMaxIdCounter_WithBookings() {
//        Booking b1 = new Booking(); b1.setId(2);
//        Booking b2 = new Booking(); b2.setId(7);
//        when(mockDao.getAllBookings()).thenReturn(List.of(b1, b2));
//
//        int result = service.getMaxIdCounter();
//
//        assertEquals(8, result);
//    }
//
//    @Test
//    void testGetMaxIdCounter_Empty() {
//        when(mockDao.getAllBookings()).thenReturn(Collections.emptyList());
//
//        int result = service.getMaxIdCounter();
//
//        assertEquals(1, result);
//    }
//
//    @Test
//    void testGetBookingById() {
//        when(mockDao.getBookingById(1)).thenReturn(booking);
//
//        Booking result = service.getBookingById(1);
//
//        assertEquals(booking, result);
//        verify(mockDao).getBookingById(1);
//    }
//
//    @Test
//    void testDelBookingById_Success() {
//        when(mockDao.getAllBookings()).thenReturn(List.of(booking));
//        when(mockDao.deleteBookingById(1)).thenReturn(true);
//
//        assertTrue(service.delBookingById(1));
//        verify(mockDao).deleteBookingById(1);
//    }
//
//    @Test
//    void testDelBookingById_EmptyList() {
//        when(mockDao.getAllBookings()).thenReturn(Collections.emptyList());
//
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(out));
//
//        assertFalse(service.delBookingById(1));
//
//        String output = out.toString();
//        assertTrue(output.contains("У вас відсутні заброньовані польоти!"));
//    }
//
//    @Test
//    void testLoadBookingData() {
//        when(mockDao.loadBookingData()).thenReturn(true);
//        assertTrue(service.loadBookingData());
//    }
//
//    @Test
//    void testSaveBookingToFile() {
//        when(mockDao.saveBookingToFile()).thenReturn(true);
//        assertTrue(service.saveBookingToFile());
//    }
//}