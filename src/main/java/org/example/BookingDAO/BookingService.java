package org.example.BookingDAO;

import org.example.Booking;
import org.example.Flight.FlightObject;
import org.example.Passenger;

import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

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
            IntStream.range(0, bookings.size())
                    .forEach(i -> System.out.printf("(%d) %s\n", i + 1, bookings.get(i)));
        }
    }

    public boolean createNewBooking (FlightObject flight, Set<Passenger> passengers){
        try {
            Booking booking = new Booking(flight, passengers);
            serviceBookings.saveBooking(booking);
            return true;
        } catch (RuntimeException e) {
            System.out.println("Неможливо створити бронювання");
            return false;
        }
    }

    public int getMaxIdCounter(){
        return serviceBookings.getAllBookings().stream()
                .mapToInt(Booking::getId)
                .max()
                .orElse(0) + 1;
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

    public boolean loadBookingData() {
        return serviceBookings.loadBookingData();

    }
    public boolean saveBookingToFile(){
        return serviceBookings.saveBookingToFile();
    }
}
