import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class UserDao {
    private Connection connection;
    private boolean ckeck;
    private String access;
    private int money;
    private  int hour;
    private String password;

    public int getHour() {
        return hour;
    }

    public int getMoney() {
        return money;
    }

    public String getAccess() {
        return access;
    }

    public boolean isCkeck() {
        return ckeck;
    }

    public UserDao() {
    }

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<User> findAll() throws SQLException {
        ArrayList<User> users=new ArrayList<>();

        User user =null;

        String sqlText ="select * from users ";



        Statement statement=this.connection.createStatement();
        ResultSet resultSet=statement.executeQuery(sqlText);


        while (resultSet.next()){
            user=new User();
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
            user.setBalance(resultSet.getInt("balance"));
            user.setHour(resultSet.getInt("hour"));
            user.setAccessLevel(resultSet.getString("access_level"));


            users.add(user);
        }

        resultSet.close();///ต้องปิดเสมอ
        statement.close();//ต้องปิดเสมอ
        return users;
    }

    public void add(User user) throws SQLException {

        User newUser=user; //เด็กที่จะเพิ้่มเข้าดาต้าเบส

        String sqlText ="insert into users(username,password,email,access_level,balance,hour) values (?,?,?,?,?,?)";//?คือตัวแทนค่า ที่จะโยนเข้ามาในนนี้

        PreparedStatement preparedStatement=this.connection.prepareStatement(sqlText);

        preparedStatement.setString(1,newUser.getUsername()); //คอลัม1เปนไอดี ที่เป็นสตริง  ถ้าเปนint ก็จะเป็นsetint
        preparedStatement.setString(2,newUser.getPassword());
        preparedStatement.setString(3,newUser.getEmail()); //ค่าsalary เป็นเลข เลย setInt
        preparedStatement.setString(4,newUser.getAccessLevel()); //ค่าsalary เป็นเลข เลย setInt
        preparedStatement.setInt(5,newUser.getBalance()); //ค่าsalary เป็นเลข เลย setInt
        preparedStatement.setInt(6,newUser.getHour()); //ค่าsalary เป็นเลข เลย setInt

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



    public boolean checkID(String id,String pass) throws SQLException {
        DbConnector dbconnect=new DbConnector();
        Connection connection=dbconnect.connect();//เชื่อมต่อดีบี
        UserDao userDao=new UserDao(connection);
        ArrayList<User> users=userDao.findAll();
        for (User user :users){
            if(id.equals(user.getUsername())&& pass.equals(user.getPassword())){
                ckeck=true;
                this.access=user.getAccessLevel();

                break;
            }else{
                ckeck=false;
            }
        }

        return ckeck;
    }
    public void addMoney(int money){


    }
    public void update(String username, int balance) throws SQLException {
//       DbConnector dbconnect = new DbConnector();
//       Connection connection = dbconnect.connect();//เชื่อมต่อดีบี
//        UserDao userDao = new UserDao(connection);
        //User updatedUser=user; //เด็กที่จะเพิ้่มเข้าดาต้าเบส
        String sqlText  = "update users set balance=? where username=?  ";


        PreparedStatement preparedStatement=this.connection.prepareStatement(sqlText);

        preparedStatement.setInt(1,balance); //คอลัม1เปนไอดี ที่เป็นสตริง  ถ้าเปนint ก็จะเป็นsetint
        preparedStatement.setString(2,username);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    public void search(String username)throws  SQLException{
        String sqlText  = "select balance from users where username="+username+"";
        DbConnector dbconnect=new DbConnector();
        Connection connection=dbconnect.connect();//เชื่อมต่อดีบี
        UserDao userDao=new UserDao(connection);
        ArrayList<User> users=userDao.findAll();
        for (User user :users){
            if(username.equals(user.getUsername())){


                this.money=user.getBalance();
                break;
            }else{
                ckeck=false;
            }
        }

    }
    public void insert(String username,String pass,String mail) throws SQLException {
        String sqlText ="insert into users(username,password,email,access_level,balance,hour) values (?,?,?,?,?,?)";//?คือตัวแทนค่า ที่จะโยนเข้ามาในนนี้

        PreparedStatement preparedStatement=this.connection.prepareStatement(sqlText);

        preparedStatement.setString(1,username);//คอลัม1เปนไอดี ที่เป็นสตริง  ถ้าเปนint ก็จะเป็นsetint
        preparedStatement.setString(2,pass);
        preparedStatement.setString(3,mail); //ค่าsalary เป็นเลข เลย setInt
        preparedStatement.setString(4,"admin"); //ค่าsalary เป็นเลข เลย setInt
        preparedStatement.setInt(5,0); //ค่าsalary เป็นเลข เลย setInt
        preparedStatement.setInt(6,0); //ค่าsalary เป็นเลข เลย setInt

        preparedStatement.executeUpdate(); //มันอัพเดทค่าที่เราอินเสิทเข้าไปให้เลย  บรรทัดนนี้มันจะรีเทินค่ากลับมาเป็นจำนวนเรคคอร์ด ที่แอดเข้าไป คือ นับเป็นแถว1เเถว
        preparedStatement.close();

    }
    public ObservableList<Produec> getProduce() throws SQLException {
        DbConnector dbconnect=new DbConnector();
        Connection connection=dbconnect.connect();//เชื่อมต่อดีบี
        UserDao userDao=new UserDao(connection);
        ArrayList<User> users=userDao.findAll();
        ObservableList<Produec>produec= FXCollections.observableArrayList();
        for (User user :users){
            produec.add(new Produec(user.getUsername()
                    ,user.getPassword()
                    ,user.getEmail(),user.getAccessLevel(),user.getBalance()
                    ,user.getHour()));
        }
        return produec;

    }
    public void searchPass(String username)throws  SQLException{
        String sqlText  = "select password from users where username="+username+"";
        DbConnector dbconnect=new DbConnector();
        Connection connection=dbconnect.connect();//เชื่อมต่อดีบี
        UserDao userDao=new UserDao(connection);
        ArrayList<User> users=userDao.findAll();
        for (User user :users){
            if(username.equals(user.getUsername())){
                this.password=user.getPassword();
                break;
            }else{
                ckeck=false;
            }
        }
    }

    public void searchUser(String username)throws  SQLException{
        String sqlText  = "select balance,hour from users where username="+username+"";
        DbConnector dbconnect=new DbConnector();
        Connection connection=dbconnect.connect();//เชื่อมต่อดีบี
        UserDao userDao=new UserDao(connection);
        ArrayList<User> users=userDao.findAll();
        for (User user :users){
            if(username.equals(user.getUsername())){


                this.money=user.getBalance();
                this.hour=user.getHour();
                System.out.println(money);
                System.out.println(hour);
                break;
            }else{
                ckeck=false;
            }
        }

    }
    public void updateHour(String username, int hour,int money) throws SQLException {
//       DbConnector dbconnect = new DbConnector();
//       Connection connection = dbconnect.connect();//เชื่อมต่อดีบี
//        UserDao userDao = new UserDao(connection);
        //User updatedUser=user; //เด็กที่จะเพิ้่มเข้าดาต้าเบส
        String sqlText  = "update users set hour=?,balance=? where username=?  ";


        PreparedStatement preparedStatement=this.connection.prepareStatement(sqlText);

        preparedStatement.setInt(1,hour); //คอลัม1เปนไอดี ที่เป็นสตริง  ถ้าเปนint ก็จะเป็นsetint
        preparedStatement.setInt(2,money);
        preparedStatement.setString(3,username);

        preparedStatement.executeUpdate();
        preparedStatement.close();

    }
    public void updatePass(String username, String password) throws SQLException {
//       DbConnector dbconnect = new DbConnector();
//       Connection connection = dbconnect.connect();//เชื่อมต่อดีบี
//        UserDao userDao = new UserDao(connection);
        //User updatedUser=user; //เด็กที่จะเพิ้่มเข้าดาต้าเบส
        String sqlText  = "update users set password=? where username=?  ";


        PreparedStatement preparedStatement=this.connection.prepareStatement(sqlText);

        preparedStatement.setString(1,password); //คอลัม1เปนไอดี ที่เป็นสตริง  ถ้าเปนint ก็จะเป็นsetint
        preparedStatement.setString(2,username);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }


}
