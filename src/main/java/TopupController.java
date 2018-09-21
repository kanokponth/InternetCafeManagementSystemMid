import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


public class TopupController {
    Topup topup=new Topup();
    Connection connection;
    UserDao userDao =new UserDao(connection);
    User user;
    @FXML
    private TextField id;
    @FXML
    private TextField money;

    @FXML
    private Label totalPrice;

    @FXML
    private Button btnPrice1;

    @FXML
    private Button btnPrice5;

    @FXML
    private Button btnPrice10;

    @FXML
    private Button btnPrice50;

    @FXML
    private Button btnPrice100;
    @FXML
    private Spinner<Integer> spinner;

    @FXML
    public void clk1(ActionEvent e){

        topup.topUp(1);
        money.setText(topup.getMoney()+"");
    }
    @FXML
    public void clk5(ActionEvent e){

        topup.topUp(5);
        money.setText(topup.getMoney()+"");
    }
    @FXML
    public void clk10(ActionEvent e){

        topup.topUp(10);
        money.setText(topup.getMoney()+"");
    }@FXML
    public void clk50(ActionEvent e){

        topup.topUp(50);
        money.setText(topup.getMoney()+"");
    }@FXML
    public void clk100(ActionEvent e){

        topup.topUp(100);
        money.setText(topup.getMoney()+"");


    }
    @FXML
    public void back(ActionEvent e){
        Button b = (Button) e.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("page2.fxml"));
        try {
            stage.setScene(new Scene((Parent) loader.load(), 1100, 600));
            Page2Controller page2Controller  = (Page2Controller) loader.getController();



        } catch (IOException e1) {
            e1.printStackTrace();

        }

    }
    @FXML
    public void topUpMoney(ActionEvent e) throws SQLException {
        DbConnector dbconnect=new DbConnector();
        Connection connection=dbconnect.connect();//เชื่อมต่อดีบี
        UserDao userDao=new UserDao(connection);
        userDao.search(id.getText());

        userDao.update(id.getText(),Integer.parseInt(money.getText())+userDao.getMoney());

    }

    @FXML
    public void topUpPop(ActionEvent e) throws IOException {

        Button b = (Button) e.getSource();
        //Stage stage = (Stage) b.getScene().getWindow();
        Stage stage;
        stage = new Stage(StageStyle.DECORATED);
        stage.show();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("topup.fxml"));
        try {

            stage.setScene(new Scene((Parent) loader.load(), 474, 404));
            TopupController topupcontroller  = (TopupController) loader.getController();



        } catch (IOException e1) {
            e1.printStackTrace();

        }


    }


}
