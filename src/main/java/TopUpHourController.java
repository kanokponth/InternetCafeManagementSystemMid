import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class TopUpHourController {
    private int money;
    private int hour;
    TopUpHour topUpHour;
    Connection connection;
    //UserDao userDao =new UserDao(connection);
    UserDao userDao =new UserDao();
    private String id;
    @FXML
    private TextField textField;
    @FXML
    private Label textField1;
    private  String username;
    private Timeline timeline;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setvalue(int money, int hour, String id) {
        textField1.setText(money +"");
        topUpHour =new TopUpHour(money,hour);
        textField.setEditable(false);
        this.id=id;
    }
    public void setLabel(String input ){
            textField1.setText(input+"");

    }
    @FXML
    public  void add(ActionEvent e){
        topUpHour.increseHour();
        textField.setText(topUpHour.getHour()+"");
        textField1.setText(topUpHour.getMoney()+"");


    }
    @FXML
    public  void del(ActionEvent e){
        topUpHour.decrese();
        textField.setText(topUpHour.getHour()+"");
        textField1.setText(topUpHour.getMoney()+"");

    }
    public void setTextField(TextField textField) {
        this.textField = textField;
    }

    public void setTextField1(Label textField1) {
        this.textField1 = textField1;
    }

    @FXML
    public  void addHour(ActionEvent e) throws SQLException {
        DbConnector dbconnect=new DbConnector();
        Connection connection=dbconnect.connect();//เชื่อมต่อดีบี
        UserDao userDao=new UserDao(connection);
        //userDao.search(getUsername());
        System.out.println(getUsername()+"rorro");

        userDao.updateHour(id,topUpHour.sumHour(),topUpHour.getMoney());
        textField.setText("");

    }

    @FXML
    public void addHourPopUp(ActionEvent e) throws IOException, SQLException {
        setValue1();


//        Page1UserController p1user = new Page1UserController();
//        p1user.setValue();

        Button b = (Button) e.getSource();
        //Stage stage = (Stage) b.getScene().getWindow();
        Stage stage;
        stage = new Stage(StageStyle.DECORATED);
        stage.show();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("topuphour.fxml"));
        try {

            stage.setScene(new Scene((Parent) loader.load(), 490, 410));
            TopUpHourController topUpHourController  = (TopUpHourController) loader.getController();
            topUpHourController.setLabel(userDao.getMoney()+"");


            topUpHourController.setvalue(userDao.getMoney(),userDao.getHour(),username);
            System.out.println(username+"rrururu");
            //topUpHourController.



        } catch (IOException e1) {
            e1.printStackTrace();

        }


    }
    public void setValue1() throws SQLException {
        DbConnector dbconnect=new DbConnector();
        Connection connection=dbconnect.connect();//เชื่อมต่อดีบี
        userDao.searchUser(username);
        System.out.println(id+"dsdsdsd");



    }



}
