/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qliphongkham.fxmlChu;

import Class.NhanVien;
import Class.TaiKhoan;
import Class.Thuoc;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.IntegerStringConverter;
import myUtil.DBconnection;

/**
 * FXML Controller class
 *
 * @author vodoa
 */
public class NhanvienController implements Initializable {

    @FXML
    private TextField tfSearch;
    @FXML
    private TableView<NhanVien> tableNhanVien;
    @FXML
    private TableColumn<NhanVien, Integer> idNV;
    @FXML
    private TableColumn<NhanVien, String> hoTenNV;
    @FXML
    private TableColumn<NhanVien, String> ngaySinhNV;
    @FXML
    private TableColumn<NhanVien, String> sdtNV;
    @FXML
    private TableView<TaiKhoan> tableTaiKhoan;
    @FXML
    private TableColumn<TaiKhoan, String> tenTK;
    @FXML
    private TableColumn<TaiKhoan, String> mkTK;
    @FXML
    private TableColumn<TaiKhoan, String> quyenTK;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    
    ObservableList<NhanVien> NhanVienList = FXCollections.observableArrayList();
    ObservableList<TaiKhoan> TaiKhoanList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<TaiKhoan, Integer> idNV2;
    @FXML
    private TextField tfSearchTK;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       initTableNhanVien();
       editNhanVien();
       initTableTaiKhoan();
       editTaiKhoan();
    }    
    
    private void initTableNhanVien(){
        connection = DBconnection.getConnect();
        try {
            rs= connection.createStatement().executeQuery("select * from nhanvien");
        
            while (rs.next()){
                NhanVienList.add(new NhanVien(rs.getInt("idNV"),rs.getString("hotenNV"),rs.getString("ngaysinhNV"),rs.getString("sdtNV")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanvienController.class.getName()).log(Level.SEVERE, null, ex);
        }
        idNV.setCellValueFactory(new PropertyValueFactory<>("maNV"));
        hoTenNV.setCellValueFactory(new PropertyValueFactory<>("tenNV"));
        ngaySinhNV.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
        sdtNV.setCellValueFactory(new PropertyValueFactory<>("sdtNV"));
        tableNhanVien.setItems(NhanVienList);    
    }
    private void initTableTaiKhoan(){
        connection = DBconnection.getConnect();
        try {
            rs= connection.createStatement().executeQuery("select * from accounts");
        
            while (rs.next()){
                TaiKhoanList.add(new TaiKhoan(rs.getInt("idNV"),rs.getString("username"),rs.getString("pass"),rs.getString("permission")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanvienController.class.getName()).log(Level.SEVERE, null, ex);
        }
        idNV2.setCellValueFactory(new PropertyValueFactory<>("maNV"));
        tenTK.setCellValueFactory(new PropertyValueFactory<>("userName"));
        mkTK.setCellValueFactory(new PropertyValueFactory<>("passWord"));
        quyenTK.setCellValueFactory(new PropertyValueFactory<>("permission"));
        tableTaiKhoan.setItems(TaiKhoanList);    
    }    
    @FXML
    private void searchEvent(MouseEvent event) {
        String searchWord = tfSearch.getText();
          tableNhanVien.getItems().clear();
          ObservableList<NhanVien> searchNhanVienList = FXCollections.observableArrayList();
          connection = DBconnection.getConnect();
        try {
            rs= connection.createStatement().executeQuery("select * from nhanvien WHERE MATCH(hotenNV, sdtNV) AGAINST('"+searchWord+"' IN NATURAL LANGUAGE MODE)");
        
             while (rs.next()){
                searchNhanVienList.add(new NhanVien(rs.getInt("idNV"),rs.getString("hotenNV"),rs.getString("ngaysinhNV"),rs.getString("sdtNV")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanvienController.class.getName()).log(Level.SEVERE, null, ex);
        }
        idNV.setCellValueFactory(new PropertyValueFactory<>("maNV"));
        hoTenNV.setCellValueFactory(new PropertyValueFactory<>("tenNV"));
        ngaySinhNV.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
        sdtNV.setCellValueFactory(new PropertyValueFactory<>("sdtNV"));
      
        tableNhanVien.setItems(searchNhanVienList);
    }

    @FXML
    private void insertView(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("add_NhanVien.fxml"));
        Scene scene = new Scene(fxml);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UTILITY);
        stage.setTitle("Nhập Dữ Liệu");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void reloadEvent(MouseEvent event) {
       tableNhanVien.getItems().clear();
       initTableNhanVien();
       tableTaiKhoan.getItems().clear();
       initTableTaiKhoan();
    }

    @FXML
    private void searchTKEvent(MouseEvent event) {
        String searchWord = tfSearchTK.getText();
          tableTaiKhoan.getItems().clear();
          ObservableList<TaiKhoan> searchTaiKhoanList = FXCollections.observableArrayList();
          connection = DBconnection.getConnect();
        try {
            rs= connection.createStatement().executeQuery("select * from accounts WHERE MATCH(username) AGAINST('"+searchWord+"' IN NATURAL LANGUAGE MODE)");
        
             while (rs.next()){
                searchTaiKhoanList.add(new TaiKhoan(rs.getInt("idNV"),rs.getString("username"),rs.getString("pass"),rs.getString("permission")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanvienController.class.getName()).log(Level.SEVERE, null, ex);
        }
        idNV2.setCellValueFactory(new PropertyValueFactory<>("maNV"));
        tenTK.setCellValueFactory(new PropertyValueFactory<>("userName"));
        mkTK.setCellValueFactory(new PropertyValueFactory<>("passWord"));
        quyenTK.setCellValueFactory(new PropertyValueFactory<>("permission"));
        tableTaiKhoan.setItems(searchTaiKhoanList);    
    }

    @FXML
    private void insertTK(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("add_TaiKhoan.fxml"));
        Scene scene = new Scene(fxml);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UTILITY);
        stage.setTitle("Nhập Dữ Liệu");
        stage.setScene(scene);
        stage.show();
    }
    
    private void editNhanVien(){
        connection = DBconnection.getConnect();
        tableNhanVien.setEditable(true);
        hoTenNV.setCellFactory(TextFieldTableCell.forTableColumn());
        hoTenNV.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setTenNV(e.getNewValue());
            int maNV = e.getTableView().getItems().get(e.getTablePosition().getRow()).getMaNV();
            String tenNV = e.getTableView().getItems().get(e.getTablePosition().getRow()).getTenNV();
            try {
            connection.createStatement().executeUpdate("update nhanvien set hotenNV='"+tenNV+"' where idNV="+maNV+";");
            } catch (SQLException ex) {
            Logger.getLogger(NhanvienController.class.getName()).log(Level.SEVERE, null, ex);
              }
        });
        
        ngaySinhNV.setCellFactory(TextFieldTableCell.forTableColumn());
        ngaySinhNV.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setNgaySinh(e.getNewValue());
            int maNV = e.getTableView().getItems().get(e.getTablePosition().getRow()).getMaNV();
            String ngaysinhNV = e.getTableView().getItems().get(e.getTablePosition().getRow()).getNgaySinh();
            try {
            connection.createStatement().executeUpdate("update nhanvien set ngaysinhNV='"+ngaysinhNV+"' where idNV="+maNV+";");
            } catch (SQLException ex) {
            Logger.getLogger(NhanvienController.class.getName()).log(Level.SEVERE, null, ex);
              }
        });
        
        sdtNV.setCellFactory(TextFieldTableCell.forTableColumn());
        sdtNV.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setSdtNV(e.getNewValue());
            int maNV = e.getTableView().getItems().get(e.getTablePosition().getRow()).getMaNV();
            String sdthNV = e.getTableView().getItems().get(e.getTablePosition().getRow()).getSdtNV();
            try {
            connection.createStatement().executeUpdate("update nhanvien set sdtNV='"+sdthNV+"' where idNV="+maNV+";");
            } catch (SQLException ex) {
            Logger.getLogger(NhanvienController.class.getName()).log(Level.SEVERE, null, ex);
              }
        });
    }
    private void editTaiKhoan(){
        connection = DBconnection.getConnect();
        tableTaiKhoan.setEditable(true);
        tenTK.setCellFactory(TextFieldTableCell.forTableColumn());
        tenTK.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setUserName(e.getNewValue());
            int maNV = e.getTableView().getItems().get(e.getTablePosition().getRow()).getMaNV();
            String tenTK = e.getTableView().getItems().get(e.getTablePosition().getRow()).getUserName();
            try {
            connection.createStatement().executeUpdate("update accounts set username='"+tenTK+"' where idNV="+maNV+";");
            } catch (SQLException ex) {
            Logger.getLogger(NhanvienController.class.getName()).log(Level.SEVERE, null, ex);
              }
        });
        
        mkTK.setCellFactory(TextFieldTableCell.forTableColumn());
        mkTK.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setPassWord(e.getNewValue());
            int maNV = e.getTableView().getItems().get(e.getTablePosition().getRow()).getMaNV();
            String passTK = e.getTableView().getItems().get(e.getTablePosition().getRow()).getPassWord();
            try {
            connection.createStatement().executeUpdate("update accounts set pass='"+passTK+"' where idNV="+maNV+";");
            } catch (SQLException ex) {
            Logger.getLogger(NhanvienController.class.getName()).log(Level.SEVERE, null, ex);
              }
        });
        
        quyenTK.setCellFactory(TextFieldTableCell.forTableColumn());
        quyenTK.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setPermission(e.getNewValue());
            int maNV = e.getTableView().getItems().get(e.getTablePosition().getRow()).getMaNV();
            String qTK = e.getTableView().getItems().get(e.getTablePosition().getRow()).getPermission();
            try {
            connection.createStatement().executeUpdate("update accounts set permission='"+qTK+"' where idNV="+maNV+";");
            } catch (SQLException ex) {
            Logger.getLogger(NhanvienController.class.getName()).log(Level.SEVERE, null, ex);
              }
        });
    }
}
