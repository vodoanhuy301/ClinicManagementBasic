/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qliphongkham;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import static javafx.fxml.FXMLLoader.load;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
/**
 * FXML Controller class
 *
 * @author vodoa
 */
public class LoginController implements Initializable {

    @FXML
    private TextField tfUserName;
    @FXML
    private PasswordField pfPass;
    @FXML
    private Button btnLogin;
    public static Stage stageFirst;
    PreparedStatement pst;
    ResultSet rs;
    String usingNow;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfUserName.requestFocus();
        
    }    

    public Connection getConnection(){
		Connection conn;
		try{
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanliphongkham","root","");
			return conn;
		}
		catch(Exception ex){
                        JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra lại kết nối với CSDL","Đăng nhập thất bại", JOptionPane.ERROR_MESSAGE);
                        Stage thisStage = (Stage) tfUserName.getScene().getWindow();
                        thisStage.close();
			return null;
		}
	}
    
   @FXML
    private void loginEvent() throws SQLException, IOException {
        
        String user = tfUserName.getText();
        String pass = pfPass.getText();
        
        if (user.equals("")&&pass.equals("")||!user.equals("")&&pass.equals("")||user.equals("")&&!pass.equals("")){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tài khoản và mật khẩu!","Đăng nhập", JOptionPane.INFORMATION_MESSAGE);
            tfUserName.requestFocus();
        }
        else{
          Connection conn=getConnection();
          pst = conn.prepareStatement("select * from accounts where username=? and pass=?");
          pst.setString(1, user);
          pst.setString(2, pass);
          rs = pst.executeQuery();
          
          if (rs.next()){
              usingNow = rs.getString("permission");
              JOptionPane.showMessageDialog(null, "Đăng nhập thành công! Với quyền "+usingNow,"Đăng nhập", JOptionPane.INFORMATION_MESSAGE);
              if (usingNow.equals("admin")) switchAdminS();
              else switchNhanVienS();
           conn.close();
          }
          else{
               JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra lại tên tài khoản và mật khẩu!","Đăng nhập thất bại", JOptionPane.INFORMATION_MESSAGE);
               tfUserName.setText("");
               pfPass.setText("");
               tfUserName.requestFocus();
          }
        }
    }
    private void switchAdminS() throws IOException{
        Stage thisStage = (Stage) tfUserName.getScene().getWindow();
        thisStage.close();
        Parent admin = FXMLLoader.load(getClass().getResource("chu.fxml"));
        Image img = new Image(getClass().getResourceAsStream("icon/icons8_clinic_120px.png"));
                        Scene adminS = new Scene(admin);
                        Stage stage = new Stage();
                        this.stageFirst=stage;
                        stage.getIcons().add(img);
			stage.setScene(adminS);
                        stage.initStyle(StageStyle.TRANSPARENT);
                        stage.setTitle("Admin site");
                        stage.setResizable(false);
			stage.show();
                        
    }
    private void switchNhanVienS() throws IOException{
        Stage thisStage = (Stage) tfUserName.getScene().getWindow();
        String maNV =  new String(tfUserName.getText());
        thisStage.close();
        Parent nv = FXMLLoader.load(getClass().getResource("NVgui.fxml"));
        Image img = new Image(getClass().getResourceAsStream("icon/icons8_clinic_120px.png"));
                        
                        Scene nvS = new Scene(nv);
                        Stage stage = new Stage();
                        this.stageFirst=stage;
                        stage.getIcons().add(img);
			stage.setScene(nvS);
                        stage.initStyle(StageStyle.TRANSPARENT);
                        stage.setResizable(false);
                        stage.setUserData(maNV);
			stage.show();
                        
    }
    @FXML
    private void enterLogin(KeyEvent event) throws SQLException, IOException {
        if(event.getCode()==KeyCode.ENTER){
            loginEvent();
        }
    }

    
    
}
