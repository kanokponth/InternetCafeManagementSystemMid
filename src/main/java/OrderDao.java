import java.sql.*;
import java.util.ArrayList;

public class OrderDao {
    private Connection connection;

    public OrderDao(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<Order> findAll() throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();
        Order order = null;

        String sqlText = "select * from order order by id desc limit 5";

        Statement statement = this.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlText);

        while (resultSet.next()) {
            order = new Order();
            order.setComputerNumber(resultSet.getInt("computer_number"));
            order.setDetail(resultSet.getString("detail"));
            order.setTotalPrice(resultSet.getInt("total_price"));

            orders.add(order);
        }
        resultSet.close();///ต้องปิดเสมอ
        statement.close();//ต้องปิดเสมอ

        return orders;
    }

    public void add(Order order) throws SQLException {

        Order newOrder = order; //เด็กที่จะเพิ้่มเข้าดาต้าเบส

        String sqlText = "insert into order(computer_number,detail,total_price) values (?,?,?)";//?คือตัวแทนค่า ที่จะโยนเข้ามาในนนี้

        PreparedStatement preparedStatement = this.connection.prepareStatement(sqlText);

        preparedStatement.setInt(1, newOrder.getComputerNumber()); //คอลัม1เปนไอดี ที่เป็นสตริง  ถ้าเปนint ก็จะเป็นsetint
        preparedStatement.setString(2, newOrder.getDetail());
        preparedStatement.setInt(3, newOrder.getTotalPrice());

        preparedStatement.executeUpdate(); //มันอัพเดทค่าที่เราอินเสิทเข้าไปให้เลย  บรรทัดนนี้มันจะรีเทินค่ากลับมาเป็นจำนวนเรคคอร์ด ที่แอดเข้าไป คือ นับเป็นแถว1เเถว
        preparedStatement.close();

    }
//
//    public void update(Order order) throws SQLException {
//        Order updatedOrder=order; //เด็กที่จะเพิ้่มเข้าดาต้าเบส
//        String sqlText  = "update order set computer_number=?,detail=?,amount=?,total_price=? where computer=?  ";
//
//        PreparedStatement preparedStatement=this.connection.prepareStatement(sqlText);
//
//        preparedStatement.setString(1,updatedOrder.getComputerNumber()); //คอลัม1เปนไอดี ที่เป็นสตริง  ถ้าเปนint ก็จะเป็นsetint
//        preparedStatement.setString(2,updatedOrder.getDetail());
//        preparedStatement.setInt(3,updatedOrder.getAmount());
//        preparedStatement.setInt(4, updatedOrder.getTotalPrice());
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