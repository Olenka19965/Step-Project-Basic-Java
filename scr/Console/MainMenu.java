package scr.Console;

import scr.BookingDAO.BookingController;
import scr.Flight.FlightController;
import scr.Flight.FlightObject;
import scr.Flight.InvalidDateException;
import scr.Flight.InvalidDestinationException;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

import static scr.Flight.FlightController.*;

public class MainMenu {
    public static final BookingController bookingController = new BookingController();
    public static final FlightController flightController = new FlightController();
    public static void mainMenu () throws FileNotFoundException {

        System.out.println("-----------------------------------------------\n" +
                "|                Головне меню                  |\n" +
                "_______________________________________________\n" +
                "| 1. Найближчі рейси                           |\n" +
                "| 2. Інформація про рейс                       |\n" +
                "| 3. Пошук та бронювання рейсу                 |\n" +
                "| 4. Скасувати бронювання                      |\n" +
                "| 5. Мої рейси                                 |\n" +
                "| 6. Вихід                                     |\n" +
                "| 0. Згенерувати рейси                         |\n" +
                "_______________________________________________"
        );
        run();
    }

    public static Scanner scanner = new Scanner(System.in);
    static String menuItem = "";

    public static void run() throws FileNotFoundException {

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
                        }
                    }
                    flightController.findFlightById(id);
                    returnToMainMenu();
                    break;
                case "3":
                    System.out.println("Знайти рейс за заданими умовами");
                    String destination = null;
                    while (true) {
                        try {
                            System.out.print("Куди летимо (місто англійською): ");
                            destination = scanner.nextLine().trim();
                            validateDestination(destination);
                            break;
                        } catch (InvalidDestinationException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    LocalDate date;
                    while (true) {
                        System.out.print("Дата (yyyy-mm-dd): ");
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
                        System.out.print("Кількість пасажирів: ");
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
                    flightController.searchFlights(destination, date, passengers);
                    returnToMainMenu();
                    break;
                case "4":
                    bookingController.displayAllBookings();
                    if (bookingController.getAllBookings().isEmpty()) {
                        System.out.println("Наразі у вас немає заброньованих рейсів для скасування");
                    } else {
                        System.out.print("Ведіть номер бронювання, яке бажаєте скасувати: ");
                        try {
                            int flightId = Integer.parseInt(scanner.nextLine());
                            bookingController.delBookingById(flightId);
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

    public static void returnToMainMenu () throws FileNotFoundException {
        System.out.print("Прошу натиснути Enter щоб повернутися до головного меню");
        scanner.nextLine();
        mainMenu();
    }
}
