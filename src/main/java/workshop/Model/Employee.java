package workshop.Model;

public class Employee {

    private String name, surname, adress,telNumber, note;
    private int  id;
    private double WorkingHourCost;

    public Employee(String name, String surname, String adress, String telNumber, String note, double workingHourCost) {
        this.name = name;
        this.surname = surname;
        this.adress = adress;
        this.telNumber = telNumber;
        this.note = note;
        this.WorkingHourCost = workingHourCost;
    }

    public Employee() {
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", adress='" + adress + '\'' +
                ", telNumber=" + telNumber +
                ", id=" + id +
                ", note='" + note + '\'' +
                ", WorkingHourCost=" + WorkingHourCost +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getWorkingHourCost() {
        return WorkingHourCost;
    }

    public void setWorkingHourCost(double workingHourCost) {
        WorkingHourCost = workingHourCost;
    }
}
