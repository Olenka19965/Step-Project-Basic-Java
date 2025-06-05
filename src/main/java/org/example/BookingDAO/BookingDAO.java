package org.example.BookingDAO;

import org.example.Booking;

import java.util.List;

public interface BookingDAO {
    List<Booking> getAllBookings ();
    Booking getBookingById (int id);
    Boolean deleteBookingById (int id);
    Boolean deleteBookingByBooking (Booking booking);
    boolean saveBooking(Booking booking);
//    boolean loadBooking();
}
