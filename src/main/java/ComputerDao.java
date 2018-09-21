import java.sql.*;
import java.util.ArrayList;

public class ComputerDao {
    private Connection connection;

    public ComputerDao(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<Computer> findAll() throws SQLException {
        ArrayList<Computer> computers = new ArrayList<>();
        Computer computer = null;

        String sqlText = "select * from computer order by computer_number";

        Statement statement = this.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlText);

        while (resultSet.next()) {
            computer = new Computer();
            computer.setComputerNumber(resultSet.getInt("computer_number"));
            computer.setStatus(resultSet.getString("status"));


            computers.add(computer);
        }
        resultSet.close();///ต้องปิดเสมอ
        statement.close();//ต้องปิดเสมอ

        return computers;
    }

    public void add(Computer computer) throws SQLException {

        Computer newComputer = computer; //เด็กที่จะเพิ้่มเข้าดาต้าเบส

        String sqlText = "insert into computer(computer_number,status) values (?,?)";//?คือตัวแทนค่า ที่จะโยนเข้ามาในนนี้

        PreparedStatement preparedStatement = this.connection.prepareStatement(sqlText);

        preparedStatement.setInt(1, newComputer.getComputerNumber()); //คอลัม1เปนไอดี ที่เป็นสตริง  ถ้าเปนint ก็จะเป็นsetint
        preparedStatement.setString(2, newComputer.getStatus());


        preparedStatement.executeUpdate(); //มันอัพเดทค่าที่เราอินเสิทเข้าไปให้เลย  บรรทัดนนี้มันจะรีเทินค่ากลับมาเป็นจำนวนเรคคอร์ด ที่แอดเข้าไป คือ นับเป็นแถว1เเถว
        preparedStatement.close();

    }
//    public void update(Order order) throws SQLException {
//        Order updatedOrder=order; //เด็กที่จะเพิ้่มเข้าดาต้าเบส
//        String sqlText  = "update order set computer_number=?,detail=?,amount=?,total_price=? where computer=?  ";
//
//        PreparedStatement preparedStatement=this.connection.prepareStatement(sqlText);
//
//        preparedStatement.setString(1,updatedOrder.getComputerNumber()); //คอลัม1เปนไอดี ที่เป็นสตริง  ถ้าเปนint ก็จะเป็นsetint
//        preparedStatement.setString(2,updatedOrder.getDetail());
//
//
//
//        preparedStatement.executeUpdate();
//        preparedStatement.close();
//    }



//    public static void main(String[] args) throws SQLException {
//        DbConnector dbconnect = new DbConnector();
//        Connection connection = dbconnect.connect();//เชื่อมต่อดีบี
//        OrderDao orderDao = new OrderDao(connection);
//
//        ArrayList<Order> orders = orderDao.findAll();
//
//        for (Order order : orders) {
//            System.out.println(String.format(order.getComputerNumber() + "|" + order.getDetail() + order.getAmount() + "|" + order.getTotalPrice()));
//        }
//
//    }
}