package workshop.Service;

public class CreateDB {
    private final String createDb = "Create SCHEMA workshop";

    private final String createTableClients = "CREATE TABLE Customers\n" +
            "(\n" +
            "    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
            "    name varchar(255),\n" +
            "    surname varchar(255),\n" +
            "    BirthDate date\n" +
            ");";

    private final String createTableEmployee ="CREATE TABLE Employees\n" +
            "(\n" +
            "    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
            "    name varchar(255),\n" +
            "    surname varchar(255),\n" +
            "    adress varchar(255),\n" +
            "    phone varchar(255),\n" +
            "    note varchar(255),\n" +
            "    WorkingHourCost double(7,2)\n" +
            ");";

    private final String createTableVehicles = "CREATE TABLE Vehicles\n" +
            "(\n" +
            "    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
            "    model varchar(255),\n" +
            "    brand varchar(255),\n" +
            "    productionYear int,\n" +
            "    rejnumber int,\n" +
            "    nextCheck date,\n" +
            "    customer_id int,\n" +
            "    CONSTRAINT Vehicles_Customers_id_fk FOREIGN KEY (customer_id) REFERENCES Customers (id)\n" +
            ");";

    private final String createTableOrders ="CREATE TABLE Orders\n" +
            "(\n" +
            "    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
            "    orderDate date,\n" +
            "    plannedRepairDate date,\n" +
            "    employee_id int,\n" +
            "    problemDesc varchar(255),\n" +
            "    repairDesc varchar(255),\n" +
            "    status varchar(255),\n" +
            "    vehicle_id int,\n" +
            "    clientCost double,\n" +
            "    partsCost double,\n" +
            "    WorkingHoursCost double,\n" +
            "    WorkingHours int,\n" +
            "    CONSTRAINT Orders_Employees_id_fk FOREIGN KEY (employee_id) REFERENCES Employees (id),\n" +
            "    CONSTRAINT Orders_Vehicles_id_fk FOREIGN KEY (vehicle_id) REFERENCES Vehicles (id)\n" +
            ");";

}
