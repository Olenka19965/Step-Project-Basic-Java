package scr;

import scr.Flight.FlightObject;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static scr.Console.MainMenu.bookingController;

public class Booking implements Serializable {
    private final int id;
    private FlightObject.Destination destination;
    private String flightId;
    private LocalDate date;
    private Set<Passenger> passengers;

    public Booking (FlightObject flight, Set<Passenger> passengers) {
        this.id = bookingController.getMaxIdCounter();
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
    public String toString() {
        List<Passenger> passengerList = new ArrayList<>(passengers);
        String passengerInfo = IntStream.range(0, passengerList.size())
                .mapToObj(i -> String.format("%d) %s", i + 1, passengerList.get(i)))
                .collect(Collectors.joining("\n "));

        return "Booking\n" +
                " Booking ID = " + id +
                ", destination = " + destination +
                ", date = " + date +
                ",\n Passengers:\n " + passengerInfo;
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
