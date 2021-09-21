/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qliphongkham.fxmlChu;

import Class.DichVu;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.IntegerStringConverter;
import myUtil.DBconnection;

public class DichvuController implements Initializable {

    @FXML
    private TableView<DichVu> tableDichVu;
    @FXML
    private TableColumn<DichVu, Integer> maDichVu;
    @FXML
    private TableColumn<DichVu, String> tenDichVu;
    @FXML
    private TableColumn<DichVu, Integer> giaDichVu;
    @FXML
    private TableColumn<DichVu, String> ghiChu;
    
    
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    
    ObservableList<DichVu> DichVuList = FXCollections.observableArrayList();
    @FXML
    private TextField tfSearch;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
        editTable();
    }    
    private void initTable(){
        connection = DBconnection.getConnect();
        try {
            rs= connection.createStatement().executeQuery("select * from dichvu");
        
            while (rs.next()){
                DichVuList.add(new DichVu(rs.getInt("idDV"),rs.getString("tenDV"),rs.getInt("giaDV"),rs.getString("ghichu")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DichvuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        maDichVu.setCellValueFactory(new PropertyValueFactory<>("maDV"));
        tenDichVu.setCellValueFactory(new PropertyValueFactory<>("tenDV"));
        giaDichVu.setCellValueFactory(new PropertyValueFactory<>("giaDV"));
        ghiChu.setCellValueFactory(new PropertyValueFactory<>("ghiChu"));
        tableDichVu.setItems(DichVuList);
    }
    @FXML
    private void searchEvent(MouseEvent event) {
          String searchWord = tfSearch.getText();
          tableDichVu.getItems().clear();
          ObservableList<DichVu> searchDichVuList = FXCollections.observableArrayList();
          connection = DBconnection.getConnect();
        try {
            rs= connection.createStatement().executeQuery("select * from dichvu WHERE MATCH(tenDV) AGAINST('"+searchWord+"' IN NATURAL LANGUAGE MODE)");
        
            while (rs.next()){
                searchDichVuList.add(new DichVu(rs.getInt("idDV"),rs.getString("tenDV"),rs.getInt("giaDV"),rs.getString("ghichu")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DichvuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        maDichVu.setCellValueFactory(new PropertyValueFactory<>("maDV"));
        tenDichVu.setCellValueFactory(new PropertyValueFactory<>("tenDV"));
        giaDichVu.setCellValueFactory(new PropertyValueFactory<>("giaDV"));
        ghiChu.setCellValueFactory(new PropertyValueFactory<>("ghiChu"));
        tableDichVu.setItems(searchDichVuList);
        
    }
    
    @FXML
    private void reloadEvent(MouseEvent event) {
        tableDichVu.getItems().clear();
        connection = DBconnection.getConnect();
        try {
            rs= connection.createStatement().executeQuery("select * from dichvu");
        
            while (rs.next()){
                DichVuList.add(new DichVu(rs.getInt("idDV"),rs.getString("tenDV"),rs.getInt("giaDV"),rs.getString("ghichu")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DichvuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        maDichVu.setCellValueFactory(new PropertyValueFactory<>("maDV"));
        tenDichVu.setCellValueFactory(new PropertyValueFactory<>("tenDV"));
        giaDichVu.setCellValueFactory(new PropertyValueFactory<>("giaDV"));
        ghiChu.setCellValueFactory(new PropertyValueFactory<>("ghiChu"));
        tableDichVu.setItems(DichVuList);
    }

    @FXML
    private void insertView(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("add_DichVu.fxml"));
        Scene scene = new Scene(fxml);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UTILITY);
        stage.setTitle("Nhập Dữ Liệu");
        stage.setScene(scene);
        stage.show();
        
    }
    private void editTable(){
        connection = DBconnection.getConnect();
        tableDichVu.setEditable(true);
        tenDichVu.setCellFactory(TextFieldTableCell.forTableColumn());
        tenDichVu.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setTenDV(e.getNewValue());
            int mDV = e.getTableView().getItems().get(e.getTablePosition().getRow()).getMaDV();
            String tDV = e.getTableView().getItems().get(e.getTablePosition().getRow()).getTenDV();
            try {
            connection.createStatement().executeUpdate("update dichvu set tenDV='"+tDV+"' where idDV="+mDV+";");
            } catch (SQLException ex) {
            Logger.getLogger(DichvuController.class.getName()).log(Level.SEVERE, null, ex);
              }
        });
        
        giaDichVu.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        giaDichVu.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setGiaDV(e.getNewValue());
            int mDV = e.getTableView().getItems().get(e.getTablePosition().getRow()).getMaDV();
            int gDV = e.getTableView().getItems().get(e.getTablePosition().getRow()).getGiaDV();
            try {
            connection.createStatement().executeUpdate("update dichvu set giaDV='"+gDV+"' where idDV="+mDV+";");
            } catch (SQLException ex) {
            Logger.getLogger(DichvuController.class.getName()).log(Level.SEVERE, null, ex);
              }
        });
        
        ghiChu.setCellFactory(TextFieldTableCell.forTableColumn());
        ghiChu.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setGhiChu(e.getNewValue());
            int mDV = e.getTableView().getItems().get(e.getTablePosition().getRow()).getMaDV();
            String gChu = e.getTableView().getItems().get(e.getTablePosition().getRow()).getGhiChu();
            try {
            connection.createStatement().executeUpdate("update dichvu set ghichu='"+gChu+"' where idDV="+mDV+";");
            } catch (SQLException ex) {
            Logger.getLogger(DichvuController.class.getName()).log(Level.SEVERE, null, ex);
              }
        });
        
    }

}