/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qliphongkham.fxmlChu;

import Class.KhachHang;
import Class.Thuoc;
import java.io.IOException;
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
import javafx.util.converter.IntegerStringConverter;
import myUtil.DBconnection;

/**
 * FXML Controller class
 *
 * @author vodoa
 */
public class KhachhangController implements Initializable {

    @FXML
    private TextField tfSearch;
    @FXML
    private TableView<KhachHang> tableKhachHang;
    @FXML
    private TableColumn<KhachHang, Integer> maKH;
    @FXML
    private TableColumn<KhachHang, String> hoTenKH;
    @FXML
    private TableColumn<KhachHang, String> sdtKH;
    @FXML
    private TableColumn<KhachHang, Integer> diemKH;

    
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    
    ObservableList<KhachHang> KhachHangList = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       initTable();
       editKH();
    }    
    
    private void initTable(){
        try {
            connection = DBconnection.getConnect();
            
            rs= connection.createStatement().executeQuery("select * from khachhang");
            
                while (rs.next()){
                    KhachHangList.add(new KhachHang(rs.getInt("idKH"),rs.getString("hotenKH"),rs.getString("sdtKH"),rs.getInt("diemKM")));
                }
            maKH.setCellValueFactory(new PropertyValueFactory<>("maKH"));
            hoTenKH.setCellValueFactory(new PropertyValueFactory<>("hoTenKH"));
            sdtKH.setCellValueFactory(new PropertyValueFactory<>("sdtKH"));
            diemKH.setCellValueFactory(new PropertyValueFactory<>("diemKM"));
            tableKhachHang.setItems(KhachHangList);
        } catch (SQLException ex) {
            Logger.getLogger(KhachhangController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void searchEvent(MouseEvent event) {
        String searchWord = tfSearch.getText();
          tableKhachHang.getItems().clear();
          ObservableList<Thuoc> searchKhachHangList = FXCollections.observableArrayList();
          connection = DBconnection.getConnect();
        try {
            rs= connection.createStatement().executeQuery("select * from khachhang WHERE MATCH(hotenKH, sdtKH) AGAINST('"+searchWord+"' IN NATURAL LANGUAGE MODE)");
        
             while (rs.next()){
                KhachHangList.add(new KhachHang(rs.getInt("idKH"),rs.getString("hotenKH"),rs.getString("sdtKH"),rs.getInt("diemKM")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachhangController.class.getName()).log(Level.SEVERE, null, ex);
        }
         maKH.setCellValueFactory(new PropertyValueFactory<>("maKH"));
        hoTenKH.setCellValueFactory(new PropertyValueFactory<>("hoTenKH"));
        sdtKH.setCellValueFactory(new PropertyValueFactory<>("sdtKH"));
        diemKH.setCellValueFactory(new PropertyValueFactory<>("diemKM"));
        tableKhachHang.setItems(KhachHangList);
    }

    @FXML
    private void insertKH(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("add_KhachHang.fxml"));
        Scene scene = new Scene(fxml);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UTILITY);
        stage.setTitle("Nhập Dữ Liệu");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void reloadEvent(MouseEvent event) {
        tableKhachHang.getItems().clear();
        initTable();
        editKH();
        
    }
    private void editKH(){
        connection = DBconnection.getConnect();
        tableKhachHang.setEditable(true);
        hoTenKH.setCellFactory(TextFieldTableCell.forTableColumn());
        hoTenKH.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setHoTenKH(e.getNewValue());
            int maKh = e.getTableView().getItems().get(e.getTablePosition().getRow()).getMaKH();
            String tenKh = e.getTableView().getItems().get(e.getTablePosition().getRow()).getHoTenKH();
            try {
                connection.createStatement().executeUpdate("update khachhang set hotenKH='"+tenKh+"' where idKH="+maKh+";");
            } catch (SQLException ex) {
                Logger.getLogger(KhachhangController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
       
        sdtKH.setCellFactory(TextFieldTableCell.forTableColumn());
        sdtKH.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setSdtKH(e.getNewValue());
            int maKh = e.getTableView().getItems().get(e.getTablePosition().getRow()).getMaKH();
            String sdtKh = e.getTableView().getItems().get(e.getTablePosition().getRow()).getSdtKH();
            try {
                connection.createStatement().executeUpdate("update khachhang set sdtKH='"+sdtKh+"' where idKH="+maKh+";");
            } catch (SQLException ex) {
                Logger.getLogger(KhachhangController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        diemKH.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        diemKH.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setDiemKM(e.getNewValue());
            int maKh = e.getTableView().getItems().get(e.getTablePosition().getRow()).getMaKH();
            int diemKh = e.getTableView().getItems().get(e.getTablePosition().getRow()).getDiemKM();
            try {
                connection.createStatement().executeUpdate("update khachhang set diemKM='"+diemKh+"' where idKH="+maKh+";");
            } catch (SQLException ex) {
                Logger.getLogger(KhachhangController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @FXML
    private void lichSuKham(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("view_LichSuKham.fxml"));
        Scene scene = new Scene(fxml);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UTILITY);
        stage.setTitle("Xem Dữ Liệu");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void lichSuMua(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("view_LichSuMua.fxml"));
        Scene scene = new Scene(fxml);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UTILITY);
        stage.setTitle("Xem Dữ Liệu");
        stage.setScene(scene);
        stage.show();
    }

}
