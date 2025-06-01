package scr.Flight;
import java.io.*;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

public class FlightDAO {
    private List <FlightObject> flights = new ArrayList<>();
    private static final int TOTAL_SEATS = 50;
    private static final String fileFlight = "scr/DataBase/flights.dat";
    public void generateFlights(){
        Random random = new Random();
        FlightObject.Destination[]destinations = FlightObject.Destination.values();
        int idCounter = 1;
        LocalDate today = LocalDate.now();
        for (int day = 0; day < 180; day++){
            LocalDate flightDay = today.plusDays(day);
            int flightsPerDay = 25 + random.nextInt(6);
            for (int i = 0; i < flightsPerDay; i++){
                String id = String.format("FL%04d", idCounter++);
                FlightObject.Destination destination = destinations[random.nextInt(destinations.length)];
                int hour = random.nextInt(24);
                int minute = random.nextInt(60);
                LocalDateTime departureTime = LocalDateTime.of(flightDay, LocalTime.of(hour,minute));
                int availableSeats = 2 + random.nextInt(6);
                FlightObject flightObject = new FlightObject(id,destination,departureTime,availableSeats);
                flights.add(flightObject);
            }
        }
        System.out.println("Рейси згенеровано");
    }
    public boolean saveToFile(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileFlight))){
            oos.writeObject(flights);
            return true;
        } catch (IOException e) {
            System.out.println("Помилка запису файлу: " + e.getMessage());
            return false;
        }
    }
    @SuppressWarnings("unchecked")
    public boolean loadFromFile() throws FileNotFoundException {
        File file = new File(fileFlight);
        if (!file.exists()){
            System.out.println("Файлу немає");
            return false;
//            generateFlights();
//            saveToFile();
        } else {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
                flights = (List<FlightObject>) ois.readObject();
                System.out.println("Flight file read");
                return true;
            }catch (IOException | ClassNotFoundException e) {
                System.out.println("Помилка читання файлу: " + e.getMessage());
//                generateFlights();
                System.out.println("Неможливо знайти базу даних польотів!");
                return false;
            }
        }
    }

    public List<FlightObject> getAllFlights() {
        return flights.stream()
                .sorted(Comparator.comparing(FlightObject::getDepartureTime))
                .collect(Collectors.toList());
    }
    public Optional<FlightObject> getFlightById(String id) {
        return flights.stream()
                .filter(flight -> flight.getId().equalsIgnoreCase(id))
                .findFirst();
    }
    public void updateFlight(FlightObject updatedFlight){
        for (int i = 0; i < flights.size(); i++){
            if (flights.get(i).getId().equalsIgnoreCase(updatedFlight.getId())){
                flights.set(i,updatedFlight);
                return;
            }
        }
    }
    public void addFlight(FlightObject flight) {
        flights.add(flight);
    }
}