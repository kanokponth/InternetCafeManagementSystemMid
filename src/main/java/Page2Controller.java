import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Page2Controller {
    UserDao userDao =new UserDao();
    @FXML
    private TableView table     ;
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

//        username.setCellValueFactory(new PropertyValueFactory<Produec, String>("username"));
//        password.setCellValueFactory(new PropertyValueFactory<Produec, String>("password"));
//        email.setCellValueFactory(new PropertyValueFactory<Produec, String>("email"));
//        access_lavel.setCellValueFactory(new PropertyValueFactory<Produec, String>("access_Level"));
//        balance.setCellValueFactory(new PropertyValueFactory<Produec,Integer>("balance"));
//        hour.setCellValueFactory(new PropertyValueFactory<Produec,Integer>("hour"));
//        table1.setItems(userDao.getProduce());
    }

    @FXML
    public void topUp(ActionEvent e) throws IOException {
        TopupController tp = new TopupController();
        tp.topUpPop(e);
    }

    @FXML
    public void regUp(ActionEvent e) throws IOException{
        RegisterController rg = new RegisterController();
        rg.regisPop(e);
    }

    /*@FXML
    public void topUp(ActionEvent e) {
        Button b = (Button) e.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("topup.fxml"));
        try {
            stage.setScene(new Scene((Parent) loader.load(), 450, 350));
            TopupController topupcontroller  = (TopupController) loader.getController();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    @FXML
    public void register(ActionEvent e) {
        Button b = (Button) e.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
        try {
            stage.setScene(new Scene((Parent) loader.load(), 650, 550));
            RegisterController registerController  = (RegisterController) loader.getController();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }*/
    @FXML
    public void manager(ActionEvent e) {
        Button b = (Button) e.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("manage.fxml"));
        try {
            stage.setScene(new Scene((Parent) loader.load(), 800, 750));
            ManageUserController manageUserController  = (ManageUserController) loader.getController();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");
        stage.setWidth(300);
        stage.setHeight(500);
        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));
        table.setEditable(true);
        TableColumn firstNameCol = new TableColumn("First Name");
        TableColumn lastNameCol = new TableColumn("Last Name");
        TableColumn emailCol = new TableColumn("Email");
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }
//    public ObservableList<Produec> getProduce(){
////        ObservableList<Produec>produec= FXCollections.observableArrayList();
////        produec.add(new Produec("tttttt","ddd",5));
////        produec.add(new Produec("yyyy","ddd",5));
////        produec.add(new Produec("yyeeeeyy","ddd",5));
////        return  produec;
////
////    }
    @FXML
    public  void rePage(ActionEvent e){
        javafx.scene.control.Button b = (javafx.scene.control.Button) e.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("page2.fxml"));
        try {
            stage.setScene(new Scene((Parent) loader.load(), 1100, 750));
            Page2Controller page2Controller= (Page2Controller) loader.getController();
        } catch (IOException e1) {
            e1.printStackTrace();

        }

    }

}
