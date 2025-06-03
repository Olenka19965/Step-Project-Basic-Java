package scr.Console;

import scr.Booking;
import scr.BookingDAO.BookingController;
import scr.Flight.FlightController;
import scr.Flight.FlightObject;
import scr.Flight.InvalidDateException;
import scr.Flight.InvalidDestinationException;
import scr.Passenger;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import static scr.Flight.FlightController.*;

public class MainMenu {
    public static final BookingController bookingController = new BookingController();
    public static final FlightController flightController = new FlightController();
    public static void mainMenu () throws FileNotFoundException {

        System.out.println("________________________________________________\n" +
                "|                Головне меню                  |\n" +
                "|______________________________________________|\n" +
                "| 0. Згенерувати рейси                         |\n" +
                "| 1. Найближчі рейси                           |\n" +
                "| 2. Інформація про рейс                       |\n" +
                "| 3. Пошук та бронювання рейсу                 |\n" +
                "| 4. Скасувати бронювання                      |\n" +
                "| 5. Мої рейси                                 |\n" +
                "| 6. Вихід                                     |\n" +
                "|______________________________________________|"
        );
        run();
    }

    public static Scanner scanner = new Scanner(System.in);
    static String menuItem = "";

    public static void run() throws FileNotFoundException {
        if (flightController.loadFromFile()) {
            List<FlightObject> flightObjectList = flightController.getAllFlights();
        }
        if (bookingController.loadBookingData()) {
            List<Booking> bookingList = bookingController.getAllBookings();
        }

        while (!menuItem.equals("6")) {
            System.out.print("Будь-ласка виберіть пункт меню: ");
            menuItem = scanner.nextLine();
            switch (menuItem.trim()) {
                case "1":
                    System.out.println("1. Найближчі рейси");
                    flightController.showTodayFlights();
                    returnToMainMenu();
                    break;
                case "2":
                    System.out.println("2. Інформація про рейс");
                    String id;
                    while (true) {
                        System.out.println("Введіть ID рейсу (формат: FL + 4 цифри, наприклад FL4959): ");
                        id = scanner.nextLine().trim();
                        if (!id.matches("^FL\\d{4}$")) {
                            System.out.println("Невірний формат ID. Будь ласка, введіть 'FL' і 4 цифри (наприклад FL4959).");
                            continue;
                        } else break;
                    }
                    flightController.findFlightById(id);
                    returnToMainMenu();
                    break;
                case "3":
                    System.out.println("Пошук та бронювання рейсу:");
                    String destination = null;
                    while (true) {
                        try {
                            System.out.print("Місто призначення (англійською мовою): ");
                            destination = scanner.nextLine().trim();
                            validateDestination(destination);
                            break;
                        } catch (InvalidDestinationException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    LocalDate date;
                    while (true) {
                        System.out.print("Введіть дату польоту в заданому форматі (yyyy-mm-dd): ");
                        String dateStr = scanner.nextLine();
                        try {
                            date = parseDate(dateStr);
                            break;
                        } catch (InvalidDateException e) {
                            System.out.println("Помилка: " + e.getMessage());
                        }
                    }
                    int passengers;
                    while (true) {
                        System.out.print("Введіть кількість квитків, бажаєте забронювати: ");
                        String passengersStr = scanner.nextLine();
                        try {
                            passengers = Integer.parseInt(passengersStr);
                            if (passengers <= 0) {
                                System.out.println("Кількість пасажирів має бути додатнім числом.");
                                continue;
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Кількість пасажирів має бути числом.");
                        }
                    }
                    List<FlightObject> flightsForBookingList = flightController.searchFlights(destination, date, passengers);
                    if (flightsForBookingList.isEmpty()) {
                        System.out.println("Рейсів за вашими умовами не знайдено");
                        returnToMainMenu();
                        break;
                    } else {
                        System.out.print("Введіть ID рейсу, на який бажаєте забронювати квитки: ");
                        String flightId = scanner.nextLine();
                        FlightObject flightForBooking = flightController.findFlightById(flightId);

                        Set<Passenger> passengersForBooking = new HashSet<>();
                        for (int i = 0; i < passengers; i++){
                            System.out.printf("Введіть ім'я %d-го пасажира: ",  i + 1);
                            String name = scanner.nextLine();
                            System.out.printf("Введіть прізвище %d-го пасажира: ",  i + 1);
                            String surname = scanner.nextLine();
                            passengersForBooking.add(new Passenger(name, surname));
                        }
                        bookingController.createNewBooking(flightForBooking,passengersForBooking);
                        bookingController.saveBookingToFile();
                    }
                    returnToMainMenu();
                    break;
                case "4":
                    bookingController.displayAllBookings();
                    if (bookingController.getAllBookings().isEmpty()) {
                        System.out.println("Наразі у вас немає заброньованих рейсів для скасування");
                    } else {
                        System.out.print("Ведіть номер бронювання, яке бажаєте скасувати: ");
                        try {
                            int bookingId = Integer.parseInt(scanner.nextLine());
                            bookingController.delBookingById(bookingId);
                        } catch (NumberFormatException err) {
                            System.out.println("Номер бронювання, що Ви ввели не є числом!");
                        }
                    }

                    returnToMainMenu();
                    break;
                case "5":
                    bookingController.displayAllBookings();
                    returnToMainMenu();
                    break;
                case "0":
                    flightController.generateFlights();
                    returnToMainMenu();
                    break;
                case "6":
                    break;
                default:
                    System.out.println("Ви ввели невірний номер меню! Будь-ласка введіть уважно!");

            }
        }
    }

    public static void returnToMainMenu() throws FileNotFoundException {
        System.out.print("Прошу натиснути Enter щоб повернутися до головного меню ");
        scanner.nextLine();
        mainMenu();
    }
}
