package scr;

public class Passenger {
    private String name;
    private String surname;

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
        return "Passenger {" +
                " name = " + name +
                ", surname = " + surname +
                " }";
    }
}
