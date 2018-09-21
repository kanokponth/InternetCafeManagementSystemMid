import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class RegisterController {
    @FXML
    private PasswordField passwordField ;

    @FXML
    public  void setDefult(){
        pass.setText("123456");
    }
    @FXML
    private ScrollBar scrollBar;
    @FXML
    private TextField id;
    @FXML
    private PasswordField pass;
    @FXML
    private TextField mail;
    @FXML
    private Label idError;

    @FXML
    public  void register(ActionEvent e) throws SQLException {
        if(id.getText().length()>6&&pass.getText().length()>6){
            DbConnector dbconnect=new DbConnector();
            Connection connection=dbconnect.connect();//เชื่อมต่อดีบี
            UserDao userDao=new UserDao(connection);
            userDao.insert(id.getText(),pass.getText(),mail.getText());
            idError.setText("");
            id.setText("");
            pass.setText("");
            mail.setText("");

        }
        else {
            idError.setText("กรุณาใส่ username หรือ password มากกว่า6หลัก");
        }


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
    public void regisPop(ActionEvent e) throws IOException {

        Button b = (Button) e.getSource();
        //Stage stage = (Stage) b.getScene().getWindow();
        Stage stage;
        stage = new Stage(StageStyle.DECORATED);
        stage.show();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
        try {

            stage.setScene(new Scene((Parent) loader.load(), 630, 560));
            TopupController topupcontroller = (TopupController) loader.getController();


        } catch (IOException e1) {
            e1.printStackTrace();

        }
    }

}
