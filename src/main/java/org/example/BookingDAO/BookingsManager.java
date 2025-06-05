package org.example.BookingDAO;

import org.example.Booking;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookingsManager {
    public static final String DATA_PATH = "scr/DataBase/bookings.dat";


    public static List<Booking> loadBookings() {
        File bookingsData = new File(DATA_PATH);
        if (!(bookingsData).exists()) {
            System.out.println("База даних бронювань не знайдена");
            return new ArrayList<>();
        } else {
            try {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(DATA_PATH));
                List<Booking> bookingList = (List<Booking>) in.readObject();
                System.out.println("Список ваших бронювань:");
                bookingList.forEach(booking -> System.out.println(bookingList.indexOf(booking) +1 +") " + booking));
                return bookingList;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Помилка завантаження: " + e.getMessage());
                return new ArrayList<>();
            }
        }
    }

    public static void saveBookings(List<Booking> bookings) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATA_PATH));
            out.writeObject(bookings);
            System.out.println("Список бронювань збережено до бази даних");
        } catch (IOException e) {
            System.out.println("Неможливо зберігти бронювання до бази даних" + e);;
        }
    }
}
