import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ManageUserController {
    UserDao userDao =new UserDao();
    @FXML
    private TextField input;
    @FXML
    private TableView<Produec> table1;
    @FXML
    private TextArea textArea;
    @FXML
    private TableColumn<Produec,String>username  ;
    @FXML
    private TableColumn<Produec,String>password  ;
    @FXML
    private TableColumn<Produec,String>email  ;
    @FXML
    private TableColumn<Produec,String>access_lavel  ;
    @FXML
    private TableColumn<Produec,Integer>balance  ;
    @FXML
    private TableColumn<Produec,Integer>hour  ;
    @FXML
    public  void initialize() throws SQLException {

        username.setCellValueFactory(new PropertyValueFactory<Produec, String>("username"));

        email.setCellValueFactory(new PropertyValueFactory<Produec, String>("email"));
        access_lavel.setCellValueFactory(new PropertyValueFactory<Produec, String>("access_Level"));
        balance.setCellValueFactory(new PropertyValueFactory<Produec,Integer>("balance"));
        hour.setCellValueFactory(new PropertyValueFactory<Produec,Integer>("hour"));
        table1.setItems(userDao.getProduce());
    }
    @FXML
    public void back(ActionEvent e){
        Button b = (Button) e.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("page2.fxml"));
        try {
            stage.setScene(new Scene((Parent) loader.load(), 1100, 750));
            Page2Controller page2Controller  = (Page2Controller) loader.getController();



        } catch (IOException e1) {
            e1.printStackTrace();

        }

    }
    @FXML
    public void setDefult(ActionEvent e) throws SQLException {
        DbConnector dbconnect=new DbConnector();
        Connection connection=dbconnect.connect();//เชื่อมต่อดีบี
        UserDao userDao=new UserDao(connection);

        userDao.updatePass(input.getText(),"123456789");





    }

}
