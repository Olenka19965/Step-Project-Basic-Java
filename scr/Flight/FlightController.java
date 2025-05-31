package scr.Flight;

import java.time.LocalDate;
import java.util.*;
import static java.lang.System.out;

public class FlightController {
  private final FlightService flightService;
//    public FlightController (){ this.flightService = new FlightService(new FlightDAO());}

    private final Scanner scanner = new Scanner(System.in);
  public FlightController(FlightService flightService){
      this.flightService=flightService;
  }
  public void run() throws NotFoundException {
      while (true){
          out.println("\n--- Меню ---");
          out.println("1. Показати всі рейси");
          out.println("2. Пошук рейсу за ID");
          out.println("3. Пошук рейсів за умовами");
          out.println("4. Рейси на сьогодні");
          out.println("5. Вихід");
          String choice = scanner.nextLine();
          switch (choice) {
              case "1" -> showAllFlights();
              case "2" -> findFlightById();
              case "3" -> searchFlights();
              case "4" -> showTodayFlights();
              case "5" -> {
                  out.println("До побачення!");
                  return;
              }
              default -> out.println("Невірний вибір.");
          }
      }
  }
  public void showAllFlights(){
      List<FlightObject>flights = flightService.getAllFlights();
      flights.forEach(System.out :: println);
  }
private void findFlightById(){
    System.out.println("Введіть ID рейсу: ");
    String id = scanner.nextLine();
    try {
        FlightObject flightObject = flightService.getFlightById(id);
        System.out.println(flightObject);
    } catch (NotFoundException e) {
        System.out.println(e.getMessage());
    }
}
private void searchFlights(){
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
    System.out.print("Дата (yyyy-mm-dd): ");
    String dateStr = scanner.nextLine();
    LocalDate date;
    try {
        date = parseDate(dateStr);
    } catch (InvalidDateException e) {
        System.out.println("Помилка: " + e.getMessage());
        return;
    }

    System.out.print("Кількість пасажирів: ");
    int passengers;
    try {
        passengers = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
        System.out.println("Кількість пасажирів має бути числом.");
        return;
    }

    List<FlightObject> results = flightService.searchFlights(destination, date, passengers);
    if (results.isEmpty()) {
        System.out.println("Рейсів не знайдено.");
    } else {
        results.forEach(System.out::println);
    }
}

    private LocalDate parseDate(String dateStr) throws InvalidDateException {
        try {
            return LocalDate.parse(dateStr);
        } catch (Exception e) {
            throw new InvalidDateException("Неправильний формат дати. Використовуйте yyyy-mm-dd.");
        }
    }
    private void validateDestination(String destination) throws InvalidDestinationException {
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
}
