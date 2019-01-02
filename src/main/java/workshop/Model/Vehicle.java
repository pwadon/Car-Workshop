package workshop.Model;

import java.sql.Date;

public class Vehicle {
    private int id, customer_id;
    private String model, brand;
    private Integer productionYear, rejnumber;
    private java.sql.Date nextCheck;

    public Vehicle(String brand, String model, Integer productionYear, Integer rejnumber, java.sql.Date nextCheck, int customer_id) {
        this.model = model;
        this.brand = brand;
        this.productionYear = productionYear;
        this.rejnumber = rejnumber;
        this.nextCheck = nextCheck;
        this.customer_id = customer_id;
    }
    public Vehicle(){}
    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                ", productionYear=" + productionYear +
                ", rejnumber=" + rejnumber +
                ", nextCheck=" + nextCheck +
                ", customer_id=" + customer_id +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public Integer getRejnumber() {
        return rejnumber;
    }

    public void setRejnumber(Integer rejnumber) {
        this.rejnumber = rejnumber;
    }

    public Date getNextCheck() {
        return nextCheck;
    }

    public void setNextCheck(java.sql.Date nextCheck) {
        this.nextCheck = nextCheck;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }
}