package scr;

import scr.Passenger;

import java.time.LocalDate;
import java.util.Set;

public class Booking {
    private int id;
    private String destination;
    private int flightId;
    private LocalDate date;
    private Set<Passenger> passengers;

    public void setId(int id) { this.id = id; }
    public int getId() { return id; }

    public void setDestination(String destination) { this.destination = destination; }
    public String getDestination() { return destination; }

    public void setFlightId(int flightId) { this.flightId = flightId; }
    public int getFlightId() { return flightId; }

    public void setDate(LocalDate date) { this.date = date; }
    public LocalDate getDate() { return date; }

    public void setPassengers(Set<Passenger> passengers) { this.passengers = passengers; }
    public Set<Passenger> getPassengers() { return passengers; }

    @Override
    public String toString(){
        return "Booking {" +
                " id = " + id +
                ", destination = " + destination +
                ", date = " + date +
                ", Passengers { " + passengers +
                " }";
    }
}
