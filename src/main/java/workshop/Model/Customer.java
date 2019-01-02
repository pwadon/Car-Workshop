package workshop.Model;

import java.util.Date;

public class Customer {
    private int id;
    private String name, surname;
    private Date BirthDate;

    public Customer( String name, String surname, Date birthDate) {
        this.name = name;
        this.surname = surname;
        BirthDate = birthDate;
    }
    public Customer(){}

    public Customer(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
