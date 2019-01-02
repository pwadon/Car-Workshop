package workshop.Model;

import java.sql.Date;

public class Order {

    private int id;
    private Date dateOforder, plannedRepairDate, repairStartDate;
    private Employee repairEmployee;
    private int Employeeid;
    private String problemDesc, repairDesc;
    private Status status;
    private String status1;
    private Vehicle vehicle;
    private int Vehiclenumber;
    private double clientRepairCost, partsCost, workingHourCost;
    private int workingHours;

    public Order (){}

    public Order( int id, Date dateOforder, Date plannedRepairDate, Date repairStartDate, int employeeid, String problemDesc, String repairDesc, String status1, int vehiclenumber, double clientRepairCost, double partsCost, double workingHourCost, int workingHours) {
        this.dateOforder = dateOforder;
        this.plannedRepairDate = plannedRepairDate;
        this.repairStartDate = repairStartDate;
        this.Employeeid = employeeid;
        this.problemDesc = problemDesc;
        this.repairDesc = repairDesc;
        this.status1 = status1;
        this.Vehiclenumber = vehiclenumber;
        this.clientRepairCost = clientRepairCost;
        this.partsCost = partsCost;
        this.workingHourCost = workingHourCost;
        this.workingHours = workingHours;
        this.id = id;
    }

    public Order(Date dateOforder, Date plannedRepairDate, Date repairStartDate, Employee repairEmployee,
                 String problemDesc, String repairDesc, Status status, Vehicle vehicle,
                 double clientRepairCost, double partsCost, double workingHourCost, int workingHours) {
        this.dateOforder = dateOforder;
        this.plannedRepairDate = plannedRepairDate;
        this.repairStartDate = repairStartDate;
        this.repairEmployee = repairEmployee;
        this.problemDesc = problemDesc;
        this.repairDesc = repairDesc;
        this.status = status;
        this.vehicle = vehicle;
        this.clientRepairCost = clientRepairCost;
        this.partsCost = partsCost;
        this.workingHourCost = workingHourCost;
        this.workingHours = workingHours;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", dateOforder=" + dateOforder +
                ", plannedRepairDate=" + plannedRepairDate +
                ", repairStartDate=" + repairStartDate +
                ", repairEmployee=" + repairEmployee +
                ", problemDesc='" + problemDesc + '\'' +
                ", repairDesc='" + repairDesc + '\'' +
                ", status=" + status +
                ", vehicle=" + vehicle +
                ", clientRepairCost=" + clientRepairCost +
                ", partsCost=" + partsCost +
                ", workingHourCost=" + workingHourCost +
                ", workingHours=" + workingHours +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateOforder() {
        return dateOforder;
    }

    public void setDateOforder(Date dateOforder) {
        this.dateOforder = dateOforder;
    }

    public Date getPlannedRepairDate() {
        return plannedRepairDate;
    }

    public void setPlannedRepairDate(Date plannedRepairDate) {
        this.plannedRepairDate = plannedRepairDate;
    }

    public Date getRepairStartDate() {
        return repairStartDate;
    }

    public void setRepairStartDate(Date repairStartDate) {
        this.repairStartDate = repairStartDate;
    }

    public Employee getRepairEmployee() {
        return repairEmployee;
    }

    public void setRepairEmployee(Employee repairEmployee) {
        this.repairEmployee = repairEmployee;
    }

    public String getProblemDesc() {
        return problemDesc;
    }

    public void setProblemDesc(String problemDesc) {
        this.problemDesc = problemDesc;
    }

    public String getRepairDesc() {
        return repairDesc;
    }

    public void setRepairDesc(String repairDesc) {
        this.repairDesc = repairDesc;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public double getClientRepairCost() {
        return clientRepairCost;
    }

    public void setClientRepairCost(double clientRepairCost) {
        this.clientRepairCost = clientRepairCost;
    }

    public double getPartsCost() {
        return partsCost;
    }

    public void setPartsCost(double partsCost) {
        this.partsCost = partsCost;
    }

    public double getWorkingHourCost() {
        return workingHourCost;
    }

    public void setWorkingHourCost() {
        this.workingHourCost = this.repairEmployee.getWorkingHourCost();
    }

    public void setEmployeeid(int employeeid) {
        Employeeid = employeeid;
    }

    public void setStatus1(String status1) {
        this.status1 = status1;
    }

    public void setVehiclenumber(int vehiclenumber) {
        Vehiclenumber = vehiclenumber;
    }

    public void setWorkingHourCost(double workingHourCost) {
        this.workingHourCost = workingHourCost;
    }

    public int getEmployeeid() {
        return Employeeid;
    }

    public int getVehiclenumber() {
        return Vehiclenumber;
    }

    public String getStatus1() {
        return status1;
    }

    public int getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }
}
