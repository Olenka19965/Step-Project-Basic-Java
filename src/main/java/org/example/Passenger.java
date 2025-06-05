package org.example;

import java.io.Serializable;
import java.util.Objects;

public class Passenger implements Serializable {
    private String name;
    private String surname;

    public Passenger(){} // For tests
    public Passenger (String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public void setName (String name) { this.name = name; }
    public String getName() { return name; }

    public void setSurname(String surname) { this.surname = surname; }
    public String getSurname() { return surname; }

    @Override
    public String toString(){
        return "Passenger: " + name + " " + surname;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return Objects.equals(name, passenger.name) && Objects.equals(surname, passenger.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }
}
