package Flight;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;
public class FlightObject {
    private String id; // Унікальний ID рейсу
    private Destination destination; // Місто призначення (енам)
    private LocalDateTime departureTime; // Дата та час вильоту (рандом 1-30)
    private final int totalSeats = 50; // Загальна кількість місць (50 файнл)
    private int availableSeats; // Кількість доступних місць(рандом 2-6)

    public enum Destination {
        PARIS, ROME, LONDON, MADRID, BERLIN, AMSTERDAM, BARCELONA, MILAN, VIENNA, PRAGUE, LISBON, BRUSSELS,
        STOCKHOLM, MUNICH, DUBLIN, BUDAPEST, ATHENS, COPENHAGEN, ZURICH, HELSINKI
    }
    public FlightObject(String id, Destination destination, LocalDateTime departureTime){
        this.id = id;
        this.destination = destination;
        this.departureTime = departureTime;
        this.availableSeats = generateRandomAvailableSeats();
    }
    public String getId() {
        return id;
    }
    public Destination getDestination() {
        return destination;
    }
    public LocalDateTime getDepartureTime() {
        return departureTime;
    }
    public int getTotalSeats() {
        return totalSeats;
    }
    public int getAvailableSeats() {
        return availableSeats;
    }
    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
    private int generateRandomAvailableSeats() {
        return ThreadLocalRandom.current().nextInt(2, 7); // 2-6 включно
    }
    @Override
    public String toString(){
        return "Flight{" +
                "id: " + id +
                ", destination: " + destination +
                ", departureTime: " + departureTime +
                ", totalSeats: " + totalSeats +
                ", availableSeats: " + availableSeats +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightObject flight = (FlightObject) o;
        return id != null && id.equals(flight.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}

