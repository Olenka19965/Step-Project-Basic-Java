package scr.BookingDAO;

import scr.Booking;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionBookingDao implements BookingDAO {
    private List<Booking> bookings = new ArrayList<>();
    public static final String fileBooking = "scr/DataBase/bookings.dat";

    public List<Booking> getAllBookings() { return bookings; }
    public Booking getBookingById (int id) {
        if (id >= 0 && id < bookings.size()) return bookings.get(id);
        return null;
    }

    public Boolean deleteBookingById (int id) {
        if (id >= 0 ) {
            bookings = bookings.stream()
                    .filter(booking -> id != booking.getId())
                    .collect(Collectors.toCollection(ArrayList::new));
            System.out.printf("Бронювання з ID %d скасовано!\n", id);
            saveBookingToFile();
            return true;
        } else {
            System.out.printf("Бронювання з номером %d не знайдено!\n", id);            return false;
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

    public boolean loadBookingData() {
        File file = new File(fileBooking);
        if (!file.exists()){
            System.out.println("Файлу з бронюваннями немає!");
            return false;
        } else {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
                bookings = (List<Booking>) ois.readObject();
                System.out.println("Booking file read");
                return true;
            }catch (IOException | ClassNotFoundException e) {
                System.out.println("Неможливо прочитати базу даних бронювань! " + e.getMessage());
                return false;
            }
        }
    }

    public boolean saveBooking (Booking booking) {
        if (bookings.contains(booking)) {
            for (int i = 0; i < bookings.size(); i++) {
                if (bookings.get(i).equals(booking)) {
                    bookings.set(i, booking);
                    return true;
                }
            }
        } else {
                bookings.add(booking);
                System.out.println("Бронювання додано до списку");
                return true;
            }
        return false;
    }

    public boolean saveBookingToFile(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileBooking))){
            oos.writeObject(bookings);
            System.out.println("Бронювання збережені в файл");
            return true;
        } catch (IOException e) {
            System.out.println("Помилка запису файлу: " + e.getMessage());
            return false;
        }
    }
}
