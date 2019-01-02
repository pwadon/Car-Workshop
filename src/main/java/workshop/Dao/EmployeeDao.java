package workshop.Dao;

import workshop.Model.Customer;
import workshop.Model.Employee;
import workshop.Service.DBService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeDao {
    private  static final String ALL ="SELECT * from Employees";
    private  static final String SELECT ="SELECT * from Employees where id=?";
    private  static final String SELECT_Employee_work ="SELECT * from Employees join Orders O on Employees.id = O.employee_id where employee_id = ? and status = 'inRepair'";
    private  static final String ADD ="INSERT INTO Employees(name,surname,adress,phone,note,WorkingHourCost) value (?,?,?,?,?,?)";
    private  static final String EDIT ="UPDATE Employees  SET name = ?, surname = ?, adress = ?, phone=?, note=?, WorkingHourCost=? WHERE id =?";
    private  static final String DELETE ="DELETE FROM Employees where id=?";

    public static List<Employee> AllEmployees(){
        List<Employee> employees = new ArrayList<>();
        try(Connection conn = DBService.getConnection("workshop");
            PreparedStatement stmt = conn.prepareStatement(ALL);
            ResultSet rs = stmt.executeQuery())
        {
            while(rs.next()){
                Employee employee = new Employee(rs.getString("name"),
                        rs.getString("surname"), rs.getString("adress"),rs.getString("phone"),
                        rs.getString("note"),rs.getDouble("WorkingHourCost"));
                employees.add(employee);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return employees;
    }
    public static Employee SelectEmployeeById (Integer id){
        Employee employee = new Employee();
        try (Connection con = DBService.getConnection("workshop");
             PreparedStatement stmt = con.prepareStatement(SELECT))
        {
            stmt.setInt(1,id);
            try ( ResultSet rs = stmt.executeQuery()){

                while (rs.next()){
                    employee.setId(rs.getInt(1));
                    employee.setName(rs.getString(2));
                    employee.setSurname(rs.getString(3));
                    employee.setAdress(rs.getString(4));
                    employee.setTelNumber(rs.getString(5));
                    employee.setNote(rs.getString(6));
                    employee.setWorkingHourCost(rs.getDouble(7));
                }
            }
        }catch ( Exception e){
            e.printStackTrace();
            System.out.println("błąd");
        }
        return employee;
    }
    public static Employee SelectEmployeeByIdAndOrders (Integer id){
        Employee employee = new Employee();
        try (Connection con = DBService.getConnection("workshop");
             PreparedStatement stmt = con.prepareStatement(SELECT))
        {
            stmt.setInt(1,id);
            try ( ResultSet rs = stmt.executeQuery()){

                while (rs.next()){
                    employee.setId(rs.getInt(1));
                    employee.setName(rs.getString(2));
                    employee.setSurname(rs.getString(3));
                    employee.setAdress(rs.getString(4));
                    employee.setTelNumber(rs.getString(5));
                    employee.setNote(rs.getString(6));
                    employee.setWorkingHourCost(rs.getDouble(7));
                }
            }
        }catch ( Exception e){
            e.printStackTrace();
            System.out.println("błąd");
        }
        return employee;
    }
    public static Employee create(Employee employee) {
        try (Connection con = DBService.getConnection("workshop");
             PreparedStatement stmt = con.prepareStatement(ADD,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1,employee.getName());
            stmt.setString(2,employee.getSurname());
            stmt.setString(3,employee.getAdress());
            stmt.setString(4,employee.getTelNumber());
            stmt.setString(5,employee.getNote());
            stmt.setDouble(6,employee.getWorkingHourCost());


            int result = stmt.executeUpdate();
            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    employee.setId(generatedKeys.getInt(1));
                    return employee;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cos sie nie powiodło");
        }
        return null;
    }

    public static void del(Integer id){
        try {
            DBService.delete(id, "workshop", DELETE);
        }catch (Exception e) {e.printStackTrace();}
    }

    public static void editEmployee(int id, String name, String surname, String adress,
                                    String phone, String note, double WorkingHourCost) {
        try (Connection connection = DBService.getConnection("workshop");
             PreparedStatement statement = connection.prepareStatement(EDIT)) {
            statement.setInt(7, id);
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, adress);
            statement.setString(4, phone);
            statement.setString(5, note);
            statement.setDouble(6, WorkingHourCost);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cos sie nie powiodło");
        }

    }

}
