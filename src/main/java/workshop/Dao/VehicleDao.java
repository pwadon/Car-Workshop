package workshop.Dao;

import workshop.Model.Customer;
import workshop.Model.Employee;
import workshop.Model.Vehicle;
import workshop.Service.DBService;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private  static final String ALL ="SELECT * from Vehicles";
    private  static final String SELECT ="SELECT * from Vehicles where id=?";
    private  static final String ADD ="INSERT INTO Vehicles(model,brand,productionYear,rejnumber,nextCheck,customer_id) value (?,?,?,?,?,?)";
    private  static final String EDIT ="UPDATE Vehicles  SET model = ?, brand = ?, productionYear = ?, rejnumber=?, nextCheck=?, customer_id=? WHERE id =?";
    private  static final String DELETE ="DELETE FROM Vehicles where id=?";

    public static List<Vehicle> AllVehicles(){
        List<Vehicle> vehicles  = new ArrayList<>();
        try(Connection conn = DBService.getConnection("workshop");
            PreparedStatement stmt = conn.prepareStatement(ALL);
            ResultSet rs = stmt.executeQuery())
        {
            while(rs.next()){
                Vehicle vehicle = new Vehicle(rs.getString("model"),
                        rs.getString("brand"), rs.getInt("productionYear"),rs.getInt("rejnumber"), rs.getDate("nextCheck"),rs.getInt("customer_id"));
                vehicles.add(vehicle);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return vehicles;
    }
    public static Vehicle SelectVehicleById (Integer id){
        Vehicle vehicle = new Vehicle();
        try (Connection con = DBService.getConnection("workshop");
             PreparedStatement stmt = con.prepareStatement(SELECT))
        {
            stmt.setInt(1,id);
            try ( ResultSet rs = stmt.executeQuery()){

                while (rs.next()){
                    vehicle.setId(rs.getInt(1));
                    vehicle.setModel(rs.getString(2));
                    vehicle.setBrand(rs.getString(3));
                    vehicle.setProductionYear(rs.getInt(4));
                    vehicle.setRejnumber(rs.getInt(5));
                    vehicle.setNextCheck(rs.getDate(6));
                    vehicle.setCustomer_id(rs.getInt(7));

                }
            }
        }catch ( Exception e){
            e.printStackTrace();
            System.out.println("błąd");
        }
        return vehicle;
    }
    public static Vehicle create(Vehicle vehicle) {
        try (Connection con = DBService.getConnection("workshop");
             PreparedStatement stmt = con.prepareStatement(ADD,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1,vehicle.getModel());
            stmt.setString(2,vehicle.getBrand());
            stmt.setInt(3,vehicle.getProductionYear());
            stmt.setInt(4,vehicle.getRejnumber());
            stmt.setDate(5,vehicle.getNextCheck());
            stmt.setInt(6,vehicle.getCustomer_id());


            int result = stmt.executeUpdate();
            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    vehicle.setId(generatedKeys.getInt(1));
                    return vehicle;
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

    public static void editVehicle(int id, String model, String brand, int productionYear,
                                   int rejnumber, Date nextCheck, int customer_id) {
        try (Connection connection = DBService.getConnection("workshop");
             PreparedStatement statement = connection.prepareStatement(EDIT)) {
            statement.setInt(7, id);
            statement.setString(1, model);
            statement.setString(2, brand);
            statement.setInt(3, productionYear);
            statement.setInt(4, rejnumber);
            statement.setDate(5, nextCheck);
            statement.setInt(6, customer_id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cos sie nie powiodło");
        }

    }
}
