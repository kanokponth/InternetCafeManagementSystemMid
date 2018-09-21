import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class FoodDao {
    private Connection connection;


    public FoodDao(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<Food> findAll() throws SQLException {
        ArrayList<Food> foods=new ArrayList<>();

        Food food =null;

        String sqlText ="select * from food ";
        //String password="select password from user where username=?"



        Statement statement=this.connection.createStatement();
        ResultSet resultSet=statement.executeQuery(sqlText);


        while (resultSet.next()){
            food=new Food();
            food.setFoodID(resultSet.getString("food_id"));
            food.setFoodName(resultSet.getString("food_name"));
            food.setFoodPrice(resultSet.getInt("price"));



            foods.add(food);
        }

        resultSet.close();///ต้องปิดเสมอ
        statement.close();//ต้องปิดเสมอ
        return foods;
    }

    public void add(Food food) throws SQLException {

        Food newFood=food; //เด็กที่จะเพิ้่มเข้าดาต้าเบส

        String sqlText ="insert into food(food_id,food_name,price) values (?,?,?)";//?คือตัวแทนค่า ที่จะโยนเข้ามาในนนี้

        PreparedStatement preparedStatement=this.connection.prepareStatement(sqlText);

        preparedStatement.setString(1,newFood.getFoodID()); //คอลัม1เปนไอดี ที่เป็นสตริง  ถ้าเปนint ก็จะเป็นsetint
        preparedStatement.setString(2,newFood.getFoodName());
        preparedStatement.setInt(3,newFood.getFoodPrice()); //ค่าsalary เป็นเลข เลย setInt

        preparedStatement.executeUpdate(); //มันอัพเดทค่าที่เราอินเสิทเข้าไปให้เลย  บรรทัดนนี้มันจะรีเทินค่ากลับมาเป็นจำนวนเรคคอร์ด ที่แอดเข้าไป คือ นับเป็นแถว1เเถว
        preparedStatement.close();

    }

    public void delete(String index) throws SQLException {
        String sqlText= "delete from product where id=? " ;


        PreparedStatement preparedStatement=this.connection.prepareStatement(sqlText);

        preparedStatement.setString(1,index);



        preparedStatement.executeUpdate();
        preparedStatement.close();

    }
    //    public void calculateAverageAmount() throws SQLException {
//        String sqlText ="select sum(amount) from product order by id desc";
//
//
//        Statement statement=this.connection.createStatement();
//        ResultSet resultSet=statement.executeQuery(sqlText);
//
//        while (resultSet.next()) {
//            Product product=new Product();
//
//           product.setAmount(resultSet.getInt("amount"));
//        }
//
//
//
//        resultSet.close();///ต้องปิดเสมอ
//        statement.close();//ต้องปิดเสมอ
//
//    }





}
