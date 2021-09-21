/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qliphongkham.fxmlChu;

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
public class Add_TaiKhoanController implements Initializable {

    @FXML
    private TextField tfMaNV;
    @FXML
    private TextField tfTenTK;
    @FXML
    private TextField tfMkTK;
    @FXML
    private TextField tfQTK;

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
    private void insertTKEvent(MouseEvent event) {
        String maNV = tfMaNV.getText();
        String tenTK = tfTenTK.getText();
        String mkTK = tfMkTK.getText();
        String qTK = tfQTK.getText();
        
        if (maNV.isEmpty()||tenTK.isEmpty()||mkTK.isEmpty()||qTK.isEmpty()){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ các thông tin!","Nhập thông tin", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            try {
                connection = DBconnection.getConnect();
                preparedStatement = connection.prepareStatement("INSERT INTO `quanliphongkham`.`accounts` (`idNV`, `username`,`pass`,`permission`) VALUES (?,?,?,?);");
                preparedStatement.setString(1, maNV);
                preparedStatement.setString(2, tenTK);
                preparedStatement.setString(3, mkTK);
                preparedStatement.setString(4, qTK);
                preparedStatement.execute();
                JOptionPane.showMessageDialog(null, "Thêm vào dữ liệu thành công!","Nhập thông tin", JOptionPane.INFORMATION_MESSAGE);
                Stage thisStage = (Stage) tfTenTK.getScene().getWindow();
                
                thisStage.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(Add_DichVuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    @FXML
    private void deleteFormTKEvent(MouseEvent event) {
        tfMaNV.clear();
        tfTenTK.clear();
        tfMkTK.clear();
        tfQTK.clear();
    }
    
}
