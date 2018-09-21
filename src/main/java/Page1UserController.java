import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Page1UserController {

    @FXML
    private Label hour;
    @FXML
    private Label money;
    @FXML
    private Button start;
    private String id ;
    private String moneyUser;

    public String getMoneyUser() {
        return moneyUser;
    }


    public void setMoneyUser(String moneyUser) {
        this.moneyUser = moneyUser;
    }


    private String password;

    Connection connection;
    @FXML
    private  Label username;

    UserDao userDao =new UserDao();
    User user;
    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdUser() {
        return id;
    }
    public void setIdUser(String id) {
        this.id = id;

    }
    public void setlabel(String id) {
        username.setText(id);
    }

    public Page1UserController() throws SQLException {

        getIdUser();
        System.out.println(getIdUser()+"test");


    }
    @FXML
    public  void initialize() throws SQLException {
//        System.out.println(username.getText());
//        setValue();
//        money.setText(String.valueOf(userDao.getMoney()));
//        hour.setText(userDao.getHour()+"");



    }
    public void setMoney(String money1){
        money.setText(money1);

    }
    public void setHour(String hour1){
        hour.setText(hour1);

    }
    public void setValue() throws SQLException {
        DbConnector dbconnect=new DbConnector();
        Connection connection=dbconnect.connect();//เชื่อมต่อดีบี
        userDao.searchUser(username.getText());
        System.out.println(id+"dsdsdsd");


    }
    @FXML
    public void start(ActionEvent e) throws SQLException {
        setValue();
        money.setText(String.valueOf(userDao.getMoney()));
        hour.setText(userDao.getHour()+"");
        start.setText("Logout");

    }
    @FXML
    public void topUpHour(ActionEvent e) throws SQLException {
        setValue();
        javafx.scene.control.Button b = (javafx.scene.control.Button) e.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("topuphour.fxml"));
        try {
            stage.setScene(new Scene((Parent) loader.load(), 600, 600));

            TopUpHourController topUpHourController= (TopUpHourController) loader.getController();
            topUpHourController.setvalue(userDao.getMoney(),userDao.getHour(),id);




        } catch (IOException e1) {
            e1.printStackTrace();

        }
    }

    @FXML
    public void handlePopUpChangePasswordButtonOnClick(ActionEvent e) throws SQLException {
        setValue();
        javafx.scene.control.Button b = (javafx.scene.control.Button) e.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserAccountManagement.fxml"));
        try {
            stage.setScene(new Scene((Parent) loader.load(), 371, 483));
            UserAccountManagementController userAccountManagementController= (UserAccountManagementController) loader.getController();
            userAccountManagementController.receiveValue(id,password,userDao.getMoney());
            userAccountManagementController.setValue(id,money.getText());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @FXML
    public void addHourUserPopUp(ActionEvent e) throws IOException, SQLException {
        setValue();


        TopUpHourController topUphour = new TopUpHourController();
        //topUphour.setLabel(String.valueOf(10));
        //topUphour.setvalue(userDao.getMoney(),userDao.getHour(),id);
        //System.out.println(getMoneyUser()+"fwdwfw");

        topUphour.setUsername(id);


        topUphour.addHourPopUp(e);
    }
    @FXML
    public void back(ActionEvent e){
        Button b = (Button) e.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("page1.fxml"));
        try {
            stage.setScene(new Scene((Parent) loader.load(), 403, 344));
            Page1Controller page1Controller  = (Page1Controller) loader.getController();



        } catch (IOException e1) {
            e1.printStackTrace();

        }

    }
    @FXML
    public void toFoodPage(ActionEvent e){
        Button b = (Button) e.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("food.fxml"));
        try {
            stage.setScene(new Scene((Parent) loader.load(), 1200, 656));
            FoodController foodController  = (FoodController) loader.getController();



        } catch (IOException e1) {
            e1.printStackTrace();

        }

    }
}



