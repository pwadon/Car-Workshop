package workshop.Dao;

import workshop.Model.Customer;
import workshop.Model.Employee;
import workshop.Model.Order;
import workshop.Model.Status;
import workshop.Service.DBService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class OrderDao {
    private static final String ALL = "SELECT * from Orders";
    private static final String SELECT = "SELECT * from Orders where id=?";
    private static final String SELECT_Employees_orders = "SELECT * from Orders where employee_id=?";
    private static final String ADD = "INSERT INTO Orders(orderDate,plannedRepairDate,repairStartDate,employee_id,problemDesc," +
            "repairDesc,status,vehicle_id,clientCost,partsCost,workingHourCost,WorkingHours) value (?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String EDIT = "UPDATE Orders  SET orderDate = ?, plannedRepairDate = ?, repairStartDate = ?, employee_id=?," +
            " problemDesc=?, repairDesc=?, status=?,vehicle_id=?,clientCost=?,partsCost=?,workingHourCost=?,WorkingHours=?  WHERE id =?";
    private static final String DELETE = "DELETE FROM Orders where id=?";
    private static final String EDIT_STATUS = "UPDATE Orders SET status =? WHERE id=?";

    public static List<Order> AllOrders() {
        List<Order> orders = new ArrayList<>();
        try (Connection conn = DBService.getConnection("workshop");
             PreparedStatement stmt = conn.prepareStatement(ALL);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setDateOforder(rs.getDate("orderDate"));
                order.setPlannedRepairDate(rs.getDate("plannerRepairDate"));
                order.setRepairStartDate(rs.getDate("repairStartDate"));
                order.setEmployeeid(rs.getInt("employee_id"));
                order.setProblemDesc(rs.getString("problemDesc"));
                order.setRepairDesc(rs.getString("repairDesc"));
                order.setStatus1(rs.getString("status"));
                order.setVehiclenumber(rs.getInt("vehicle_id"));
                order.setClientRepairCost(rs.getDouble("clientCost"));
                order.setPartsCost(rs.getDouble("partsCost"));
                order.setWorkingHourCost(rs.getDouble("workingHourCost"));
                order.setWorkingHours(rs.getInt("WorkingHours"));
                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }
    public static List<Order> AllOrdersOfEmployee( Integer id) {
        List<Order> orders = new ArrayList<>();
        try (Connection conn = DBService.getConnection("workshop");
             PreparedStatement stmt = conn.prepareStatement(SELECT_Employees_orders)){
            stmt.setInt(1,id);
             ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setDateOforder(rs.getDate("orderDate"));
                order.setPlannedRepairDate(rs.getDate("plannerRepairDate"));
                order.setRepairStartDate(rs.getDate("repairStartDate"));
                order.setEmployeeid(rs.getInt("employee_id"));
                order.setProblemDesc(rs.getString("problemDesc"));
                order.setRepairDesc(rs.getString("repairDesc"));
                order.setStatus1(rs.getString("status"));
                order.setVehiclenumber(rs.getInt("vehicle_id"));
                order.setClientRepairCost(rs.getDouble("clientCost"));
                order.setPartsCost(rs.getDouble("partsCost"));
                order.setWorkingHourCost(rs.getDouble("workingHourCost"));
                order.setWorkingHours(rs.getInt("WorkingHours"));
                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
        }

    public static Order SelectOrderById(Integer id) {
        Order order = new Order();
        try (Connection con = DBService.getConnection("workshop");
             PreparedStatement stmt = con.prepareStatement(SELECT)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    order.setId(rs.getInt(1));
                    order.setDateOforder(rs.getDate(2));
                    order.setPlannedRepairDate(rs.getDate(3));
                    order.setRepairStartDate(rs.getDate(4));
                    order.setEmployeeid(rs.getInt(5));
                    order.setProblemDesc(rs.getString(6));
                    order.setRepairDesc(rs.getString(7));
                    order.setStatus1(rs.getString(8));
                    order.setVehiclenumber(rs.getInt(9));
                    order.setClientRepairCost(rs.getDouble(10));
                    order.setPartsCost(rs.getDouble(11));
                    order.setWorkingHourCost(rs.getDouble(12));
                    order.setWorkingHours(rs.getInt(13));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("błąd");
        }
        return order;
    }

    public static Order create(Order order) {
        try (Connection con = DBService.getConnection("workshop");
             PreparedStatement stmt = con.prepareStatement(ADD,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setDate(1, order.getDateOforder());
            stmt.setDate(2, order.getPlannedRepairDate());
            stmt.setDate(3, order.getRepairStartDate());
            stmt.setInt(4, order.getEmployeeid());
            stmt.setString(5, order.getProblemDesc());
            stmt.setString(6, order.getRepairDesc());
            stmt.setString(7, order.getStatus1());
            stmt.setInt(8, order.getVehiclenumber());
            stmt.setDouble(9, order.getClientRepairCost());
            stmt.setDouble(10, order.getPartsCost());
            stmt.setDouble(11, order.getWorkingHourCost());
            stmt.setInt(12, order.getWorkingHours());


            int result = stmt.executeUpdate();
            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    order.setId(generatedKeys.getInt(1));
                    return order;
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

    public static void del(Integer id) {
        try {
            DBService.delete(id, "workshop", DELETE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void changeStatus (int id,String stat){
        int counter =0;
        Status [] statuses = Status.values();
        for (Status s : statuses) {
            if (s.toString().equals(stat)) {
                counter++;
                break;
            }
        }
            if (counter == 1){
                try (Connection connection = DBService.getConnection("workshop");
                     PreparedStatement statement = connection.prepareStatement(EDIT_STATUS)) {
                    statement.setInt(2, id);
                    statement.setString(1, stat);

                    statement.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Cos sie nie powiodło");
                }
            }
            else System.out.println("wrong status");
        }


    public static void editOrder(int id, Date dateOforder, Date plannedRepairDate, Date repairStartDate, int employeeid,
                                 String problemDesc, String repairDesc, String status1, int vehiclenumber, double clientRepairCost,
                                 double partsCost, double workingHourCost, int workingHours) {
        try (Connection connection = DBService.getConnection("workshop");
             PreparedStatement statement = connection.prepareStatement(EDIT)) {
            statement.setInt(13, id);
            statement.setDate(1, dateOforder);
            statement.setDate(2, plannedRepairDate);
            statement.setDate(3, repairStartDate);
            statement.setInt(4, employeeid);
            statement.setString(5, problemDesc);
            statement.setString(6, repairDesc);
            statement.setString(7, status1);
            statement.setInt(8, vehiclenumber);
            statement.setDouble(9, clientRepairCost);
            statement.setDouble(10, partsCost);
            statement.setDouble(11, workingHourCost);
            statement.setInt(12, workingHours);

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cos sie nie powiodło");
        }

    }
}
