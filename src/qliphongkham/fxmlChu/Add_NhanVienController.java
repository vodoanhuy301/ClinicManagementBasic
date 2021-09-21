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
public class Add_NhanVienController implements Initializable {

    @FXML
    private TextField tfTenNV;
    @FXML
    private TextField tfNgaySinhNV;
    @FXML
    private TextField tfSdtNV;

    /**
     * Initializes the controller class.
     */
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void insertNVEvent(MouseEvent event) {
        String tenNV = tfTenNV.getText();
        String ngaySinhNV = tfNgaySinhNV.getText();
        String sdtNV = tfSdtNV.getText();
        
        if (tenNV.isEmpty()||ngaySinhNV.isEmpty()||sdtNV.isEmpty()){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ các thông tin!","Nhập thông tin", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            try {
                connection = DBconnection.getConnect();
                preparedStatement = connection.prepareStatement("INSERT INTO `quanliphongkham`.`nhanvien` (`hotenNV`, `ngaysinhNV`,`sdtNV`) VALUES (?,?,?);");
                preparedStatement.setString(1, tenNV);
                preparedStatement.setString(2, ngaySinhNV);
                preparedStatement.setString(3, sdtNV);
                preparedStatement.execute();
                JOptionPane.showMessageDialog(null, "Thêm vào dữ liệu thành công!","Nhập thông tin", JOptionPane.INFORMATION_MESSAGE);
                Stage thisStage = (Stage) tfTenNV.getScene().getWindow();
                
                thisStage.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(Add_DichVuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    @FXML
    private void deleteFormNVEvent(MouseEvent event) {
        tfTenNV.clear();
        tfNgaySinhNV.clear();
        tfSdtNV.clear();
    }
    
}
