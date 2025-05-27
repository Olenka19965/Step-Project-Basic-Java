package scr.BookingDAO;

import scr.Booking;

import java.util.List;

public interface BookingDAO {
    List<Booking> getAllBooking ();
    Booking getBookingByID (int id);
    Boolean deleteBookingById (int id);
    Boolean deleteBookingByBooking (Booking booking);
    void saveBooking(Booking booking);
    void loadBooking(List<Booking> bookings);
}
