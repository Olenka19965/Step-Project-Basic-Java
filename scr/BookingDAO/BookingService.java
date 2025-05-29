package scr.BookingDAO;

import scr.Booking;
import scr.Passenger;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class BookingService {

    public CollectionBookingDao serviceBookings = new CollectionBookingDao();
    public List<Booking> getAllBookings() {
        return serviceBookings.getAllBookings();
    }

    public void displayAllBookings() {
        List<Booking> bookings = getAllBookings();
        if (bookings.isEmpty()){
            System.out.println("Ви ще не бронювали жодного рейсу");
        } else {
            System.out.println("Список ваших бронювань:");
            getAllBookings()
                    .forEach(System.out::println);
        }
    }

    public void createNewBooking (String destination, int flightId, LocalDate date, Set<Passenger> passengers){
        Booking booking = new Booking(destination, flightId, date, passengers);
        serviceBookings.saveBooking(booking);
    }

    public Booking getBookingById(int id) { return serviceBookings.getBookingById(id); }

    public boolean delBookingById (int id){
        if (getAllBookings().isEmpty()) {
            System.out.println("У вас відсутні заброньовані польоти!");
            return false;
        } else {
            serviceBookings.deleteBookingById(id);
            return true;
        }
    }

    public void loadBookingData(List<Booking> bookings) {
        serviceBookings.loadBooking(bookings);
    }
}
