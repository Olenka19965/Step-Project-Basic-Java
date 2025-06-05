package org.example.BookingDAO;

import org.example.Booking;
import org.example.Flight.FlightObject;
import org.example.Passenger;

import java.util.List;
import java.util.Set;

public class BookingController {
    public BookingService bookingService;

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
