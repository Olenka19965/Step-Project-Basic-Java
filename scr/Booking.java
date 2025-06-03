package scr;

import scr.Flight.FlightObject;
import scr.Passenger;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import static scr.Console.MainMenu.bookingController;

public class Booking implements Serializable {
    private final int id;
    private FlightObject.Destination destination;
    private String flightId;
    private LocalDate date;
    private Set<Passenger> passengers;

    private static int idCounter = bookingController.getAllBookings().stream()
        .mapToInt(Booking::getId)
        .max()
        .orElse(0) + 1;

    public Booking (FlightObject flight, Set<Passenger> passengers) {
        this.id = idCounter++;
        this.destination = flight.getDestination();
        this.flightId = flight.getId();
        this.date = flight.getDepartureTime().toLocalDate();
        this.passengers = passengers;
    }

    public int getId() { return id; }

    public void setDestination(FlightObject.Destination destination) { this.destination = destination; }
    public FlightObject.Destination getDestination() { return destination; }

    public void setFlightId(String flightId) { this.flightId = flightId; }
    public String getFlightId() { return flightId; }

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return id == booking.id && flightId == booking.flightId && Objects.equals(destination, booking.destination) && Objects.equals(date, booking.date) && Objects.equals(passengers, booking.passengers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, destination, flightId, date, passengers);
    }
}
