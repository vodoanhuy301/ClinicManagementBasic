/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qliphongkham.fxmlChu;

import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import myUtil.DBconnection;

/**
 * FXML Controller class
 *
 * @author vodoa
 */
public class Add_KhachHangController implements Initializable {

    @FXML
    private TextField tfTenKH;
    @FXML
    private TextField tfSdtKH;

    
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void insertEvent(MouseEvent event) {
        String tenKH = tfTenKH.getText();
        String sdtKH = tfSdtKH.getText();
        
        if (tenKH.isEmpty()||tfSdtKH.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ các thông tin!","Nhập thông tin", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            try {
                connection = DBconnection.getConnect();
                preparedStatement = connection.prepareStatement("INSERT INTO khachhang(hotenKH,sdtKH) values(?,?);");
                preparedStatement.setString(1, tenKH);
                preparedStatement.setString(2, sdtKH);
                preparedStatement.execute();
                JOptionPane.showMessageDialog(null, "Thêm vào dữ liệu thành công!","Nhập thông tin", JOptionPane.INFORMATION_MESSAGE);
                Stage thisStage = (Stage) tfTenKH.getScene().getWindow();
                
                thisStage.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(Add_KhachHangController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void deleteFormEvent(MouseEvent event) {
        tfTenKH.clear();
        tfSdtKH.clear();
    }
    
}
