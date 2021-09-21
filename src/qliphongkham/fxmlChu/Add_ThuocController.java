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
public class Add_ThuocController implements Initializable {

    @FXML
    private TextField tfTenThuoc;
    @FXML
    private TextField tfGiaThuoc;
    @FXML
    private TextField tfSoLuongThuoc;
    @FXML
    private TextField tfGhiChu;

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
        String tenThuoc = tfTenThuoc.getText();
        int giaThuoc = parseInt(tfGiaThuoc.getText());
        int soLuongThuoc = parseInt(tfSoLuongThuoc.getText());
        String GhiChu = tfGhiChu.getText();

        if (tenThuoc.isEmpty() || GhiChu.isEmpty() || tfGiaThuoc.getText().isEmpty() || tfSoLuongThuoc.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ các thông tin!", "Nhập thông tin", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                connection = DBconnection.getConnect();
                preparedStatement = connection.prepareStatement("INSERT INTO `quanliphongkham`.`thuoc` (`tenThuoc`, `giaThuoc`,`soluong`,`ghichu`) VALUES (?,?,?,?);");
                preparedStatement.setString(1, tenThuoc);
                preparedStatement.setInt(2, giaThuoc);
                preparedStatement.setInt(3, soLuongThuoc);
                preparedStatement.setString(4, GhiChu);
                preparedStatement.execute();
                JOptionPane.showMessageDialog(null, "Thêm vào dữ liệu thành công!", "Nhập thông tin", JOptionPane.INFORMATION_MESSAGE);
                Stage thisStage = (Stage) tfTenThuoc.getScene().getWindow();

                thisStage.close();

            } catch (SQLException ex) {
                Logger.getLogger(Add_DichVuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void deleteFormEvent(MouseEvent event) {
        tfTenThuoc.clear();
        tfGiaThuoc.clear();
        tfSoLuongThuoc.clear();
        tfGhiChu.clear();

    }

}
