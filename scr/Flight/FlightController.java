package scr.Flight;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;
import static scr.Console.MainMenu.scanner;

public class FlightController {
    FlightService flightService;
//    private final Scanner scanner = new Scanner(System.in);
    public FlightController(){
        this.flightService=new FlightService();
    }
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }
//    public void run() throws NotFoundException {
//        while (true){
//            out.println("\n--- Меню ---");
//            out.println("1. Показати всі рейси");
//            out.println("2. Пошук рейсу за ID");
//            out.println("3. Пошук рейсів за умовами");
//            out.println("4. Рейси на сьогодні");
//            out.println("5. Вихід");
//            out.print("Введіть пункт меню який вас цікавить: ");
//            String choice = scanner.nextLine();
//            switch (choice) {
//                case "1" -> showAllFlights();
//                case "2" -> findFlightById();
//                case "3" -> searchFlights();
//                case "4" -> showTodayFlights();
//                case "5" -> {
//                    out.println("До побачення!");
//                    return;
//                }
//                default -> out.println("Невірний вибір.");
//            }
//        }
//    }
    public List<FlightObject> getAllFlights() {
        return flightService.getAllFlights();
    }

    public void showAllFlights(){
        List<FlightObject>flights = flightService.getAllFlights();
        flights.forEach(System.out :: println);
    }
    public FlightObject findFlightById(String id) {

            try {FlightObject flightObject = flightService.getFlightById(id);
                System.out.println(flightObject);
                return  flightObject;
                } catch (NotFoundException e) {
                System.out.println("Рейс з ID " + id + " не знайдено.");
                return  null;
            }
    }

    public List<FlightObject> searchFlights(String destination, LocalDate date, int passengers) {



        List<FlightObject> results = flightService.searchFlights(destination, date, passengers);
        if (results.isEmpty()) {
            System.out.println("Рейсів не знайдено.");
        } else {
            results.forEach(System.out::println);
        }
        return results;
    }
    public static LocalDate parseDate(String dateStr) throws InvalidDateException {
        try {
            return LocalDate.parse(dateStr);
        } catch (Exception e) {
            throw new InvalidDateException("Неправильний формат дати. Використовуйте yyyy-mm-dd.");
        }
    }
    public static void validateDestination(String destination) throws InvalidDestinationException {
        if (!destination.matches("[a-zA-Z]+")) {
            throw new InvalidDestinationException("Ви ввели назву міста не вірно, введіть назву міста англійською.");
        }
    }
    public void showTodayFlights(){
        List <FlightObject> todayFlights = flightService.getTodayFlights();
        if (todayFlights.isEmpty()){
            System.out.println("На сьогодні немає рейсів.");
        }else todayFlights.forEach(System.out::println);
    }
    public void generateFlights() throws FileNotFoundException {
        flightService.generateFlights();
    }

    public boolean loadFromFile() throws FileNotFoundException {
        return flightService.loadFromFile();
    }
}