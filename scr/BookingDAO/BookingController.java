package scr.BookingDAO;

import scr.Booking;
import scr.Flight.FlightObject;
import scr.Passenger;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class BookingController {
    BookingService bookingService;

    public BookingController() { this.bookingService = new BookingService();}

    public List<Booking> getAllBookings () { return bookingService.getAllBookings();}

    public void displayAllBookings() { bookingService.displayAllBookings();}

    public boolean createNewBooking (FlightObject flight, Set<Passenger> passengers) {
        return bookingService.createNewBooking(flight, passengers);
    }

    public int getMaxIdCounter () { return bookingService.getMaxIdCounter();}

    public Booking getBookingById(int id) { return bookingService.getBookingById(id); }

    public boolean delBookingById (int id){ return bookingService.delBookingById(id); }

    public boolean loadBookingData() { return bookingService.loadBookingData();}

    public boolean saveBookingToFile(){ return bookingService.saveBookingToFile(); }
}
