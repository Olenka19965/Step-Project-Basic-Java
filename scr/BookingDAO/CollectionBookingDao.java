package scr.BookingDAO;

import scr.Booking;

import java.util.ArrayList;
import java.util.List;

public class CollectionBookingDao implements BookingDAO {
    public List<Booking> bookings = new ArrayList<>();

    public List<Booking> getAllBookings() { return bookings; }
    public Booking getBookingById (int id) {
        if (id >= 0 && id < bookings.size()) return bookings.get(id);
        return null;
    }

    public Boolean deleteBookingById (int id) {
        if (id >= 0 && id < bookings.size()) {
            bookings.remove(id);
            return true;
        } else {
            return false;
        }
    }

    public Boolean deleteBookingByBooking (Booking booking) {
        if (bookings.contains(booking)){
            bookings.remove(booking);
            return true;
        } else {
            return false;
        }
    }

    public void saveBooking (Booking booking) {
        if (bookings.contains(booking)) {
            for (int i = 0; i < bookings.size(); i++) {
                if (bookings.get(i).equals(booking)) bookings.set(i, booking);
            }
            bookings.add(booking);
        }
    }

    public void loadBooking(List<Booking> bookings){
        this.bookings = bookings;
    }
}
