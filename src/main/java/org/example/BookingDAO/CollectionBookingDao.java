package org.example.BookingDAO;

import org.example.Booking;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CollectionBookingDao implements BookingDAO {
    private List<Booking> bookings = new ArrayList<>();
    public static final String fileBooking = "bookings.dat";

    public List<Booking> getAllBookings() { return bookings; }
    public Booking getBookingById (int id) {
        return bookings.stream()
                .filter(booking -> booking.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Boolean deleteBookingById (int id) {
        boolean  isDeleted = bookings.removeIf(booking -> booking.getId() == id);

        if (isDeleted) {
            System.out.printf("Бронювання з ID %d скасовано!\n", id);
            saveBookingToFile();
            return true;
        } else {
            System.out.printf("Бронювання з ID %d не знайдено!\n", id);
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
