package scr.BookingDAO;

import scr.Booking;
import scr.Flight.FlightObject;
import scr.Passenger;

import java.util.List;
import java.util.Set;

import static scr.Console.MainMenu.bookingController;

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

    public boolean createNewBooking (FlightObject flight, Set<Passenger> passengers){
        try {
            Booking booking = new Booking(flight, passengers);
            serviceBookings.saveBooking(booking);
            return true;
        } catch (RuntimeException e) {
            System.out.println("Неможливо створити бронювання");;
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
//        File file = new File(fileBooking);
//        if (!file.exists()){
//            System.out.println("Файлу з бронюваннями немає!");
//            return false;
//        } else {
//            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
//                List<Booking> bookings = (List<Booking>) ois.readObject();
//                System.out.println("Booking file read");
//                return true;
//            }catch (IOException | ClassNotFoundException e) {
//                System.out.println("Помилка читання файлу: " + e.getMessage());
//                System.out.println("Неможливо знайти базу даних бронювань!");
//                return false;
//            }
//        }
    }
    public boolean saveBookingToFile(){
        return serviceBookings.saveBookingToFile();
    }
}
