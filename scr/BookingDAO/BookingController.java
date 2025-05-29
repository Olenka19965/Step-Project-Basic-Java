package scr.BookingDAO;

import scr.Booking;
import scr.Passenger;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class BookingController {
    BookingService bookingService;

    public BookingController() { this.bookingService = new BookingService();}

    public List<Booking> getAllBookings () { return bookingService.getAllBookings();}

    public void displayAllBookings() { bookingService.displayAllBookings();}

    public void createNewBooking (String destination, int flightId, LocalDate date, Set<Passenger> passengers) {
        bookingService.createNewBooking(destination, flightId, date, passengers);
    }

    public Booking getBookingById(int id) { return bookingService.getBookingById(id); }

    public Boolean delBookingById (int id){ return bookingService.delBookingById(id); }

    public void loadBookingData(List<Booking> bookings) { bookingService.loadBookingData(bookings);}
}
