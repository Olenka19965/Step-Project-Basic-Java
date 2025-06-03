package scr.Console;

import scr.BookingDAO.BookingController;
import scr.Flight.*;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;

public class MainMenu {
    public static final BookingController bookingController = new BookingController();
    public static final FlightController flightController = new FlightController();
    public static void mainMenu () throws FileNotFoundException {

        System.out.println("-----------------------------------------------\n" +
                "|                Головне меню                  |\n" +
                "|----------------------------------------------|\n" +
                "| 0. Згенерувати рейси                         |\n" +
                "| 1. Найближчі рейси                           |\n" +
                "| 2. Інформація про рейс                       |\n" +
                "| 3. Пошук та бронювання рейсу                 |\n" +
                "| 4. Скасувати бронювання                      |\n" +
                "| 5. Мої рейси                                 |\n" +
                "| 6. Вихід                                     |\n" +
                "_______________________________________________"
        );
        run();
    }

    static Scanner scanner = new Scanner(System.in);
    static String menuItem = "";

    public static void run() throws FileNotFoundException {

        while (!menuItem.equals("6")) {
            System.out.print("Будь-ласка виберіть пункт меню: ");
            menuItem = scanner.nextLine();
            switch (menuItem.trim()){
                case "0":
                    if (!flightController.loadFromFile() && flightController.getAllFlights().isEmpty()){
                        System.out.println("Генерую польоти");
                        flightController.generateFlights();
                    } else {
                        System.out.println("У Вас вже є список згенерованих польотів");
                    }
                    returnToMainMenu();
                    break;
                case "1":
                    System.out.println("1. Найближчі рейси:");
                    flightController.showTodayFlights();
                    returnToMainMenu();
                    break;
                case "2":
                    while (true) {
                        System.out.print("Введіть ID рейсу (формат: FL + 4 цифри, наприклад FL4959): ");
                        String id = scanner.nextLine().trim();
                        if (!id.matches("^FL\\d{4}$")) {
                            System.out.println("Невірний формат ID. Спробуйте ще раз.");
                            continue;
                        }
                        try {
                            FlightObject flightObject = flightController.findFlightById(id);
                            System.out.println(flightObject);
                            break;
                        } catch (NotFoundException e) {
                            System.out.println("Рейс з ID " + id + " не знайдено.");
                        }
                    }
                    returnToMainMenu();
                    break;
                case "3":
                    String destination;
                    while (true) {
                        System.out.print("Куди летимо (місто англійською): ");
                        destination = scanner.nextLine().trim();
                        if (destination.matches("[a-zA-Z]+")) {
                            break;
                        } else {
                            System.out.println("Неправильний ввід. Введіть назву міста англійськими літерами.");
                        }
                    }
                    LocalDate date;
                    while (true) {
                        System.out.print("Дата (yyyy-mm-dd): ");
                        String dateInput = scanner.nextLine().trim();
                        try {
                            date = LocalDate.parse(dateInput);
                            break;
                        } catch (Exception e) {
                            System.out.println("Неправильний формат дати. Спробуйте ще раз (yyyy-mm-dd).");
                        }
                    }
                    int passengers;
                    while (true) {
                        System.out.print("Кількість пасажирів: ");
                        String input = scanner.nextLine().trim();
                        try {
                            passengers = Integer.parseInt(input);
                            if (passengers <= 0) {
                                System.out.println("Кількість має бути більшою за 0.");
                                continue;
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Це має бути число.");
                        }
                    }

                    List<FlightObject> results = flightController.searchFlights(destination, date, passengers);
                    if (results.isEmpty()) {
                        System.out.println("Рейсів за заданими параметрами не знайдено.");
                    } else {
                        System.out.println("Знайдено рейси: ");
                        results.forEach(System.out::println);
                    }

                    returnToMainMenu();
                    break;
                case "4":
                    System.out.print("Ведіть номер бронювання, яке бажаєте скасувати: ");
                    try {
                        int flightId = Integer.parseInt(scanner.nextLine());
                        bookingController.delBookingById(flightId);
                    } catch (NumberFormatException err) {
                        System.out.println("Номер бронювання, що Ви ввели не є числом!");
                    }
                    returnToMainMenu();
                    break;
                case  "5":
                    bookingController.displayAllBookings();
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
