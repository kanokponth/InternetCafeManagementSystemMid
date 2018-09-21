
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserAccountManagementController {
    private String password,username;
    private int money;


    UserAccountManagement userAccountManagement;
    @FXML
    Label usernameLabel;
    @FXML
    Label passError;

    @FXML
    TextField oldPasswordTextfield;

    @FXML
    TextField newPasswordTextfield;

    @FXML
    TextField newPasswordConfirmationTextfield;

    @FXML
    Label balanceLabel;

    @FXML
    Label newPasswordLabel;

    @FXML
    Label confirmPassLabel;
    @FXML Label oldPasswordLabel;
    @FXML Label completeLabel;
    @FXML Label nullLabel;

    public void receiveValue(String usernameFromUserPage,String passwordFromUserPage,int moneyFromUserPage){
        this.password=passwordFromUserPage;
        this.username=usernameFromUserPage;
        this.money=moneyFromUserPage;

    }
    public void setValue(String id,String money){
        usernameLabel.setText(id);
        balanceLabel.setText(money);

    }

        @FXML
    public void handleBtnOkOnClick(ActionEvent event) throws SQLException {
           if(newPasswordTextfield.getText().length()>6&&newPasswordConfirmationTextfield.getText().length()>6){
               UserAccountManagement userAccountManagement = new UserAccountManagement();
               DbConnector dbconnect=new DbConnector();
               Connection connection=dbconnect.connect();//เชื่อมต่อดีบี
               UserDao userDao=new UserDao(connection);
               userDao.search(username);

               System.out.println(password);
               System.out.println(this.password);
               userAccountManagement.checkNewPassword(password,oldPasswordTextfield.getText(),newPasswordTextfield.getText(),newPasswordConfirmationTextfield.getText());
               if(userAccountManagement.getWarning()==0){
                   System.out.println("insert");
                   completeLabel.setText(userAccountManagement.getWarningMessage());
                   newPasswordLabel.setText(userAccountManagement.getWarningMessage1());
                   confirmPassLabel.setText(userAccountManagement.getWarningMessage2());
                   oldPasswordLabel.setText(userAccountManagement.getWarningMessage3());
                   nullLabel.setText(userAccountManagement.getWarnningMessageNull());



                   userDao.updatePass(username,newPasswordConfirmationTextfield.getText());
               }else if(userAccountManagement.getWarning()==1){
                   completeLabel.setText(userAccountManagement.getWarningMessage());
                   newPasswordLabel.setText(userAccountManagement.getWarningMessage1());
                   confirmPassLabel.setText(userAccountManagement.getWarningMessage2());
                   oldPasswordLabel.setText(userAccountManagement.getWarningMessage3());
                   nullLabel.setText(userAccountManagement.getWarnningMessageNull());
                   System.out.println("not");
               }else if(userAccountManagement.getWarning()==2){
                   completeLabel.setText(userAccountManagement.getWarningMessage());
                   newPasswordLabel.setText(userAccountManagement.getWarningMessage1());
                   confirmPassLabel.setText(userAccountManagement.getWarningMessage2());
                   oldPasswordLabel.setText(userAccountManagement.getWarningMessage3());
                   nullLabel.setText(userAccountManagement.getWarnningMessageNull());
                   System.out.println("not");
               }else if(userAccountManagement.getWarning()==3){
                   completeLabel.setText(userAccountManagement.getWarningMessage());
                   newPasswordLabel.setText(userAccountManagement.getWarningMessage1());
                   confirmPassLabel.setText(userAccountManagement.getWarningMessage2());
                   oldPasswordLabel.setText(userAccountManagement.getWarningMessage3());
                   nullLabel.setText(userAccountManagement.getWarnningMessageNull());
                   System.out.println("not");
               }
               else{
                   completeLabel.setText(userAccountManagement.getWarningMessage());
                   newPasswordLabel.setText(userAccountManagement.getWarningMessage1());
                   confirmPassLabel.setText(userAccountManagement.getWarningMessage2());
                   oldPasswordLabel.setText(userAccountManagement.getWarningMessage3());
                   nullLabel.setText(userAccountManagement.getWarnningMessageNull());

               }
               newPasswordTextfield.setText("");
               newPasswordConfirmationTextfield.setText("");
               oldPasswordTextfield.setText("");

           }
           else {
               passError.setText("กรุณาใส่ passwprd มากกว่า6หลัก");
           }





    }
}

