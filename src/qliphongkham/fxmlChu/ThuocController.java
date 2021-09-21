/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qliphongkham.fxmlChu;

import Class.DichVu;
import Class.Thuoc;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
public class ThuocController implements Initializable {

    @FXML
    private JFXButton btnReload;
    @FXML
    private TextField tfSearch;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private TableColumn<Thuoc, Integer> maThuoc;
    @FXML
    private TableColumn<Thuoc, String> tenThuoc;
    @FXML
    private TableColumn<Thuoc, Integer> giaThuoc;
    @FXML
    private TableColumn<Thuoc, Integer> soLuong;
    @FXML
    private TableColumn<Thuoc, String> ghiChu;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;

    ObservableList<Thuoc> ThuocList = FXCollections.observableArrayList();
    @FXML
    private TableView<Thuoc> tableThuoc;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
        editThuoc();
    }

    private void initTable() {
        connection = DBconnection.getConnect();
        try {
            rs = connection.createStatement().executeQuery("select * from thuoc");

            while (rs.next()) {
                ThuocList.add(new Thuoc(rs.getInt("idThuoc"), rs.getString("tenThuoc"), rs.getInt("giaThuoc"), rs.getInt("soluong"), rs.getString("ghichu")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThuocController.class.getName()).log(Level.SEVERE, null, ex);
        }
        maThuoc.setCellValueFactory(new PropertyValueFactory<>("maThuoc"));
        tenThuoc.setCellValueFactory(new PropertyValueFactory<>("tenThuoc"));
        giaThuoc.setCellValueFactory(new PropertyValueFactory<>("giaThuoc"));
        soLuong.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
        ghiChu.setCellValueFactory(new PropertyValueFactory<>("ghiChu"));
        tableThuoc.setItems(ThuocList);
    }

    @FXML
    private void searchThuoc(MouseEvent event) {
        String searchWord = tfSearch.getText();
        tableThuoc.getItems().clear();
        ObservableList<Thuoc> searchThuocList = FXCollections.observableArrayList();
        connection = DBconnection.getConnect();
        try {
            rs = connection.createStatement().executeQuery("select * from thuoc WHERE MATCH(tenThuoc) AGAINST('" + searchWord + "' IN NATURAL LANGUAGE MODE)");

            while (rs.next()) {
                searchThuocList.add(new Thuoc(rs.getInt("idThuoc"), rs.getString("tenThuoc"), rs.getInt("giaThuoc"), rs.getInt("soluong"), rs.getString("ghichu")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThuocController.class.getName()).log(Level.SEVERE, null, ex);
        }
        maThuoc.setCellValueFactory(new PropertyValueFactory<>("maThuoc"));
        tenThuoc.setCellValueFactory(new PropertyValueFactory<>("tenThuoc"));
        giaThuoc.setCellValueFactory(new PropertyValueFactory<>("giaThuoc"));
        soLuong.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
        ghiChu.setCellValueFactory(new PropertyValueFactory<>("ghiChu"));

        tableThuoc.setItems(searchThuocList);
    }

    @FXML
    private void addThuoc(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("add_Thuoc.fxml"));
        Scene scene = new Scene(fxml);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UTILITY);
        stage.setTitle("Nhập Dữ Liệu");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void reloadEvent(MouseEvent event) {
        tableThuoc.getItems().clear();
        connection = DBconnection.getConnect();
        try {
            rs = connection.createStatement().executeQuery("select * from thuoc");

            while (rs.next()) {
                ThuocList.add(new Thuoc(rs.getInt("idThuoc"), rs.getString("tenThuoc"), rs.getInt("giaThuoc"), rs.getInt("soluong"), rs.getString("ghichu")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThuocController.class.getName()).log(Level.SEVERE, null, ex);
        }
        maThuoc.setCellValueFactory(new PropertyValueFactory<>("maThuoc"));
        tenThuoc.setCellValueFactory(new PropertyValueFactory<>("tenThuoc"));
        giaThuoc.setCellValueFactory(new PropertyValueFactory<>("giaThuoc"));
        soLuong.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
        ghiChu.setCellValueFactory(new PropertyValueFactory<>("ghiChu"));
        tableThuoc.setItems(ThuocList);
    }

    private void editThuoc() {
        connection = DBconnection.getConnect();
        tableThuoc.setEditable(true);
        tenThuoc.setCellFactory(TextFieldTableCell.forTableColumn());
        tenThuoc.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setTenThuoc(e.getNewValue());
            int maTh = e.getTableView().getItems().get(e.getTablePosition().getRow()).getMaThuoc();
            String tenTh = e.getTableView().getItems().get(e.getTablePosition().getRow()).getTenThuoc();
            try {
                connection.createStatement().executeUpdate("update thuoc set tenThuoc='" + tenTh + "' where idThuoc=" + maTh + ";");
            } catch (SQLException ex) {
                Logger.getLogger(ThuocController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        giaThuoc.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        giaThuoc.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setGiaThuoc(e.getNewValue());
            int maTh = e.getTableView().getItems().get(e.getTablePosition().getRow()).getMaThuoc();
            int giaTh = e.getTableView().getItems().get(e.getTablePosition().getRow()).getGiaThuoc();
            try {
                connection.createStatement().executeUpdate("update thuoc set giaThuoc='" + giaTh + "' where idThuoc=" + maTh + ";");
            } catch (SQLException ex) {
                Logger.getLogger(ThuocController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        soLuong.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        soLuong.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setSoLuong(e.getNewValue());
            int maTh = e.getTableView().getItems().get(e.getTablePosition().getRow()).getMaThuoc();
            int sLuong = e.getTableView().getItems().get(e.getTablePosition().getRow()).getSoLuong();
            try {
                connection.createStatement().executeUpdate("update thuoc set soluong='" + sLuong + "' where idThuoc=" + maTh + ";");
            } catch (SQLException ex) {
                Logger.getLogger(ThuocController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        ghiChu.setCellFactory(TextFieldTableCell.forTableColumn());
        ghiChu.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setGhiChu(e.getNewValue());
            int maTh = e.getTableView().getItems().get(e.getTablePosition().getRow()).getMaThuoc();
            String gChu = e.getTableView().getItems().get(e.getTablePosition().getRow()).getGhiChu();
            try {
                connection.createStatement().executeUpdate("update thuoc set ghichu='" + gChu + "' where idThuoc=" + maTh + ";");
            } catch (SQLException ex) {
                Logger.getLogger(ThuocController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
