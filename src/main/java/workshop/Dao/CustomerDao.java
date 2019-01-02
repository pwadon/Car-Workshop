package workshop.Dao;

import workshop.Model.Customer;
import workshop.Service.DBService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerDao {

    private  static final String SELECT_ALL_Customers ="SELECT * from Customers";
    private  static final String SELECT_Customer ="SELECT * from Customers where id=?";
    private  static final String SELECT_Customer_by_Surname ="SELECT * from Customers where surname=?";
    private  static final String ADD_CUSTOMER ="INSERT INTO Customers(name,surname,BirthDate) value (?,?,?)";
    private  static final String EDIT_CUSTOMER ="UPDATE Customers  SET name = ?, surname = ?, BirthDate = ? WHERE id =?";
    private  static final String DELETE_CUSTOMER ="DELETE FROM Customers where id=?";

    public static List<Customer> AllCustomers(){
        List<Customer> customers = new ArrayList<>();
        try(Connection conn = DBService.getConnection("workshop");
            PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_Customers);
            ResultSet rs = stmt.executeQuery())
        {

            while(rs.next()){
                Customer customer = new Customer(rs.getString("name"),
                        rs.getString("surname"), rs.getDate("BirthDate"));
                customers.add(customer);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
            return customers;
    }
    public static Customer SelectCustomerById (Integer id){
        Customer customer = new Customer();
        try (Connection con = DBService.getConnection("workshop");
             PreparedStatement stmt = con.prepareStatement(SELECT_Customer))
        {
            stmt.setInt(1,id);
            try ( ResultSet rs = stmt.executeQuery()){

                while (rs.next()){
                    customer.setId(rs.getInt(1));
                    customer.setName(rs.getString(2));
                    customer.setSurname(rs.getString(3));
                    customer.setBirthDate(rs.getDate(4));
                }
            }
        }catch ( Exception e){
            e.printStackTrace();
            System.out.println("błąd");
        }

        return customer;
    }
    public static List<Customer> SelectCustomerBySurname (String surname){
        List<Customer> customers = new ArrayList<>();
        try (Connection con = DBService.getConnection("workshop");
             PreparedStatement stmt = con.prepareStatement(SELECT_Customer_by_Surname)){
             stmt.setString(1,surname);
             ResultSet rs = stmt.executeQuery();
                while (rs.next()){
                    Customer customer = new Customer();
                    customer.setId(rs.getInt(1));
                    customer.setName(rs.getString(2));
                    customer.setSurname(rs.getString(3));
                    customer.setBirthDate(rs.getDate(4));
                    customers.add(customer);
                }
        }catch ( Exception e){
            e.printStackTrace();
            System.out.println("błąd");
        }

        return customers;
    }
    public static Customer create(Customer customer) {
        try (Connection con = DBService.getConnection("workshop");
             PreparedStatement stmt = con.prepareStatement(ADD_CUSTOMER,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getSurname());
            stmt.setString(3, customer.getBirthDate().toString());

            int result = stmt.executeUpdate();
            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    customer.setId(generatedKeys.getInt(1));
                    return customer;
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

    public static void delete(Integer id) {
        try (Connection connection = DBService.getConnection("workshop");
             PreparedStatement statement = connection.prepareStatement(DELETE_CUSTOMER)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cos sie nie powiodło");
        }
    }
    public static void del(Integer id){
        try {
            DBService.delete(id, "workshop", DELETE_CUSTOMER);
        }catch (Exception e) {e.printStackTrace();}
    }

    public static void editCustomer(int id, String name, String surname, Date BirthDate) {
        try (Connection connection = DBService.getConnection("workshop");
             PreparedStatement statement = connection.prepareStatement(EDIT_CUSTOMER)) {
            statement.setInt(4, id);
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, BirthDate.toString());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cos sie nie powiodło");
        }

    }

}
