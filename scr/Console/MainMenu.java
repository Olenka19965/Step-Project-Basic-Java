package scr.Console;

import scr.BookingDAO.BookingController;
import scr.Flight.FlightController;

import java.util.Scanner;

public class MainMenu {
    public static final BookingController bookingController = new BookingController();
    public static final FlightController flightController = new FlightController();

    public static void mainMenu () {
        System.out.println("-----------------------------------------------\n" +
                "|                Головне меню                  |\n" +
                "_______________________________________________\n" +
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

    public static void run(){

        while (!menuItem.equals("6")) {
            System.out.print("Будь-ласка виберіть пункт меню: ");
            menuItem = scanner.nextLine();
            switch (menuItem.trim()){
                case "1":
                    System.out.println("1. Найближчі рейси");
                    returnToMainMenu();
                    flightController.showTodayFlights();
                    break;
                case "2":
                    System.out.println("2. Інформація про рейс");
                    returnToMainMenu();
                    break;
                case "3":






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

    public static void returnToMainMenu (){
        System.out.print("Прошу натиснути Enter щоб повернутися до головного меню");
        scanner.nextLine();
        mainMenu();
    }
}
