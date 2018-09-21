import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Page1Controller {
    UserDao userDao =new UserDao();
    @FXML
    private TextField id;
    @FXML
    private PasswordField pass;
    @FXML
    private Label status;



    @FXML
    public void logIn(ActionEvent e) throws SQLException {
        userDao.checkID(id.getText(),pass.getText());
        if(userDao.isCkeck()){
            if(userDao.getAccess().equals("admin")){
                javafx.scene.control.Button b = (javafx.scene.control.Button) e.getSource();
                Stage stage = (Stage) b.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("page2.fxml"));
                try {
                    stage.setScene(new Scene((Parent) loader.load(), 1100, 750));
                    Page2Controller page2Controller= (Page2Controller) loader.getController();
                } catch (IOException e1) {
                    e1.printStackTrace();

                }
            }else if(userDao.getAccess().equals("guest")){

                javafx.scene.control.Button b = (javafx.scene.control.Button) e.getSource();
                Stage stage = (Stage) b.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("userpage.fxml"));
                try {
                    stage.setScene(new Scene((Parent) loader.load(), 408, 464));
                    Page1UserController page1UserController= (Page1UserController) loader.getController();
                    DbConnector dbconnect=new DbConnector();
                    Connection connection=dbconnect.connect();//เชื่อมต่อดีบี
                    userDao.searchUser(id.getText());
                    page1UserController.setMoney(userDao.getMoney()+"");
                    page1UserController.setHour(userDao.getHour()+"");
                    page1UserController.setlabel(id.getText());
                    page1UserController.setIdUser(id.getText());
                    page1UserController.setPassword(pass.getText());
                    page1UserController.setMoneyUser(userDao.getMoney()+"");




                } catch (IOException e1) {
                    e1.printStackTrace();

                }
            }


        }
        else {
            status.setText("username or password fail");
        }




    }

}
