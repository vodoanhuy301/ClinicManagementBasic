/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qliphongkham.fxmlChu;

import Class.KhachHang;
import Class.LichSuKham;
import Class.Thuoc;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import myUtil.DBconnection;

/**
 * FXML Controller class
 *
 * @author vodoa
 */
public class View_LichSuKhamController implements Initializable {

    @FXML
    private TextField tfSearch;
    @FXML
    private TableView<LichSuKham> lichSuKham;
    @FXML
    private TableColumn<LichSuKham, Integer> maKH;
    @FXML
    private TableColumn<LichSuKham, String> ngayKham;
    @FXML
    private TableColumn<LichSuKham, String> noiDungKham;
    @FXML
    private TableColumn<LichSuKham, Integer> maHD;

    /**
     * Initializes the controller class.
     */
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
     ObservableList<LichSuKham> lichSuKhamList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initLichSuKham();
    }    
    private void initLichSuKham(){
        try {
            connection = DBconnection.getConnect();
            
            rs= connection.createStatement().executeQuery("select * from khkhambenh");
            
            while (rs.next()){
                lichSuKhamList.add(new LichSuKham(rs.getInt("idKH"),rs.getString("ngaykham"),rs.getString("noidungkham"),rs.getInt("idHD")));
                
                maKH.setCellValueFactory(new PropertyValueFactory<>("maKH"));
                ngayKham.setCellValueFactory(new PropertyValueFactory<>("ngayKham"));
                noiDungKham.setCellValueFactory(new PropertyValueFactory<>("noiDungKham"));
                maHD.setCellValueFactory(new PropertyValueFactory<>("maHD"));
                lichSuKham.setItems(lichSuKhamList);
            }   } catch (SQLException ex) {
            Logger.getLogger(View_LichSuKhamController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void searchMaKH(MouseEvent event) {
        if (tfSearch.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập mã Khách Hàng","Lỗi", JOptionPane.INFORMATION_MESSAGE);
        }
        int searchWord = parseInt(tfSearch.getText());
          lichSuKham.getItems().clear();
          ObservableList<LichSuKham> searchMaKHList = FXCollections.observableArrayList();
          connection = DBconnection.getConnect();
        try {
            rs= connection.createStatement().executeQuery("select * from khkhambenh WHERE idKH="+searchWord);
        
             while (rs.next()){
                searchMaKHList.add(new LichSuKham(rs.getInt("idKH"),rs.getString("ngaykham"),rs.getString("noidungkham"),rs.getInt("idHD")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(View_LichSuKhamController.class.getName()).log(Level.SEVERE, null, ex);
        }
         maKH.setCellValueFactory(new PropertyValueFactory<>("maKH"));
         ngayKham.setCellValueFactory(new PropertyValueFactory<>("ngayKham"));
         noiDungKham.setCellValueFactory(new PropertyValueFactory<>("noiDungKham"));
         maHD.setCellValueFactory(new PropertyValueFactory<>("maHD"));
         lichSuKham.setItems(searchMaKHList);
         System.out.println("OK OK");
    }

    @FXML
    private void searchMaHD(MouseEvent event) {
        if (tfSearch.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập mã Hóa Đơn","Lỗi", JOptionPane.INFORMATION_MESSAGE);
        }
        int searchWord = parseInt(tfSearch.getText());
          lichSuKham.getItems().clear();
          ObservableList<LichSuKham> searchMaHDList = FXCollections.observableArrayList();
          connection = DBconnection.getConnect();
        try {
            rs= connection.createStatement().executeQuery("select * from khkhambenh WHERE idHD="+searchWord);
        
             while (rs.next()){
                searchMaHDList.add(new LichSuKham(rs.getInt("idKH"),rs.getString("ngaykham"),rs.getString("noidungkham"),rs.getInt("idHD")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(View_LichSuKhamController.class.getName()).log(Level.SEVERE, null, ex);
        }
         maKH.setCellValueFactory(new PropertyValueFactory<>("maKH"));
         ngayKham.setCellValueFactory(new PropertyValueFactory<>("ngayKham"));
         noiDungKham.setCellValueFactory(new PropertyValueFactory<>("noiDungKham"));
         maHD.setCellValueFactory(new PropertyValueFactory<>("maHD"));
         lichSuKham.setItems(searchMaHDList);
          System.out.println("OK OK");
    }

    @FXML
    private void reloadEvent(MouseEvent event) {
        lichSuKham.getItems().clear();
        initLichSuKham();
    }
    
}
