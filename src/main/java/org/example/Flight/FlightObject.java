package org.example.Flight;
import java.io.Serializable;
import java.time.LocalDateTime;

public class FlightObject implements Serializable {
    private String id;
    private Destination destination;
    private LocalDateTime departureTime;
    private final int totalSeats = 50;
    private int availableSeats;

    public enum Destination {
        PARIS, ROME, LONDON, MADRID, BERLIN, AMSTERDAM, BARCELONA, MILAN, VIENNA, PRAGUE, LISBON, BRUSSELS,
        STOCKHOLM, MUNICH, DUBLIN, BUDAPEST, ATHENS, COPENHAGEN, ZURICH, HELSINKI
    }

    public FlightObject(){} //For test
    public FlightObject(String id, Destination destination, LocalDateTime departureTime,int availableSeats){
        this.id = id;
        this.destination = destination;
        this.departureTime = departureTime;
        this.availableSeats = availableSeats;
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

