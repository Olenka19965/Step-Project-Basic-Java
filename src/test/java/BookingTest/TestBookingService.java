package BookingTest;

import org.example.Booking;
import org.example.BookingDAO.BookingService;
import org.example.BookingDAO.CollectionBookingDao;
import org.example.Flight.FlightObject;
import org.example.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestBookingService {

    @Mock
    private CollectionBookingDao mockBookingDao;

    @InjectMocks
    private BookingService bookingService;

    private FlightObject testFlight;
    private Set<Passenger> testPassengers;
    private Booking testBooking;

    @BeforeEach
    void setUp() {
        testFlight = mock(FlightObject.class);
        testPassengers = Collections.singleton(mock(Passenger.class));
        testBooking = new Booking(testFlight, testPassengers);
    }

    @Test
    void getAllBookings_ShouldReturnAllBookingsFromDao() {
        List<Booking> expectedBookings = Collections.singletonList(testBooking);
        when(mockBookingDao.getAllBookings()).thenReturn(expectedBookings);

        List<Booking> result = bookingService.getAllBookings();

        assertEquals(expectedBookings, result);
        verify(mockBookingDao).getAllBookings();
    }

    @Test
    void displayAllBookings_ShouldPrintMessageWhenNoBookings() {
        when(mockBookingDao.getAllBookings()).thenReturn(Collections.emptyList());

        assertDoesNotThrow(() -> bookingService.displayAllBookings());
    }

    @Test
    void createNewBooking_ShouldReturnTrueWhenSuccessful() {
        when(mockBookingDao.saveBooking(any(Booking.class))).thenReturn(true);

        boolean result = bookingService.createNewBooking(testFlight, testPassengers);

        assertTrue(result);
        verify(mockBookingDao).saveBooking(any(Booking.class));
    }

    @Test
    void createNewBooking_ShouldReturnFalseWhenExceptionOccurs() {
        when(mockBookingDao.saveBooking(any(Booking.class))).thenThrow(new RuntimeException());

        boolean result = bookingService.createNewBooking(testFlight, testPassengers);

        assertFalse(result);
    }

    @Test
    void getMaxIdCounter_ShouldReturnOneWhenNoBookingsExist() {
        when(mockBookingDao.getAllBookings()).thenReturn(Collections.emptyList());

        int result = bookingService.getMaxIdCounter();

        assertEquals(1, result);
    }

    @Test
    void getMaxIdCounter_ShouldReturnMaxIdPlusOne() {
        Booking booking1 = new Booking(testFlight, testPassengers);
        booking1.setId(5);
        Booking booking2 = new Booking(testFlight, testPassengers);
        booking2.setId(10);

        when(mockBookingDao.getAllBookings()).thenReturn(List.of(booking1, booking2));

        int result = bookingService.getMaxIdCounter();

        assertEquals(11, result);
    }

    @Test
    void getBookingById_ShouldReturnBookingFromDao() {
        int testId = 1;
        when(mockBookingDao.getBookingById(testId)).thenReturn(testBooking);

        Booking result = bookingService.getBookingById(testId);

        assertEquals(testBooking, result);
        verify(mockBookingDao).getBookingById(testId);
    }

    @Test
    void delBookingById_ShouldReturnFalseWhenNoBookingsExist() {
        when(mockBookingDao.getAllBookings()).thenReturn(Collections.emptyList());

        boolean result = bookingService.delBookingById(1);

        assertFalse(result);
        verify(mockBookingDao, never()).deleteBookingById(anyInt());
    }

    @Test
    void delBookingById_ShouldReturnTrueAndCallDaoWhenBookingsExist() {
        when(mockBookingDao.getAllBookings()).thenReturn(List.of(testBooking));

        boolean result = bookingService.delBookingById(1);

        assertTrue(result);
        verify(mockBookingDao).deleteBookingById(1);
    }

    @Test
    void loadBookingData_ShouldReturnResultFromDao() {
        when(mockBookingDao.loadBookingData()).thenReturn(true);

        boolean result = bookingService.loadBookingData();

        assertTrue(result);
        verify(mockBookingDao).loadBookingData();
    }

    @Test
    void saveBookingToFile_ShouldReturnResultFromDao() {
        when(mockBookingDao.saveBookingToFile()).thenReturn(true);

        boolean result = bookingService.saveBookingToFile();

        assertTrue(result);
        verify(mockBookingDao).saveBookingToFile();
    }
}