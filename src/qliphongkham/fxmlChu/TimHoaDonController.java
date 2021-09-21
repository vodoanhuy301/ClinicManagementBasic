/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qliphongkham.fxmlChu;

import Class.HoaDon;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import myUtil.DBconnection;
import qliphongkham.fxmlNhanVien.HoadonController;

/**
 * FXML Controller class
 *
 * @author vodoa
 */
public class TimHoaDonController implements Initializable {

    @FXML
    private TextField tfSearchHD;
    @FXML
    private JFXButton btnSearch1;
    @FXML
    private JFXButton btnReload1;
    @FXML
    private TableView<HoaDon> HoaDonTable;
    @FXML
    private TableColumn<HoaDon, Integer> maHD;
    @FXML
    private TableColumn<HoaDon, Integer> maNV;
    @FXML
    private TableColumn<HoaDon, Integer> maKH;
    @FXML
    private TableColumn<HoaDon, String> ngayMua;
    @FXML
    private TableColumn<HoaDon, Integer> tongTien;
    @FXML
    private TextField tfSearchHD1;
    @FXML
    private JFXButton btnSearch11;
    
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    ResultSet rs1 = null;
    ObservableList<HoaDon> hoaDonList = FXCollections.observableArrayList();
    @FXML
    private JFXButton btnTaoHoaDonMenu;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            initHoaDon();
        } catch (SQLException ex) {
            Logger.getLogger(TimHoaDonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    private void initHoaDon() throws SQLException {
        connection = DBconnection.getConnect();
        try {
            rs = connection.createStatement().executeQuery("select * from hoadon");

            while (rs.next()) {
                hoaDonList.add(new HoaDon(rs.getInt("idHD"), rs.getInt("idNV"), rs.getInt("idKH"), rs.getString("ngaymua"), rs.getInt("tongtien")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TimHoaDonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        maHD.setCellValueFactory(new PropertyValueFactory<>("maHD"));
        maNV.setCellValueFactory(new PropertyValueFactory<>("maNV"));
        maKH.setCellValueFactory(new PropertyValueFactory<>("maKH"));
        ngayMua.setCellValueFactory(new PropertyValueFactory<>("ngayMua"));
        tongTien.setCellValueFactory(new PropertyValueFactory<>("tongTien"));
        HoaDonTable.setItems(hoaDonList);
        connection.close();
    }
    @FXML
    private void searchHD(MouseEvent event) throws SQLException {
        if (!tfSearchHD.getText().isEmpty()) {
            int searchWord = parseInt(tfSearchHD.getText());
            HoaDonTable.getItems().clear();
            ObservableList<HoaDon> searchHoaDonList = FXCollections.observableArrayList();
            connection = DBconnection.getConnect();
            try {
                rs = connection.createStatement().executeQuery("select * from hoadon WHERE idKH=" + searchWord);

                while (rs.next()) {
                    searchHoaDonList.add(new HoaDon(rs.getInt("idHD"), rs.getInt("idNV"), rs.getInt("idKH"), rs.getString("ngaymua"), rs.getInt("tongtien")));
                }
            } catch (SQLException ex) {
                Logger.getLogger(TimHoaDonController.class.getName()).log(Level.SEVERE, null, ex);
            }
            maHD.setCellValueFactory(new PropertyValueFactory<>("maHD"));
            maNV.setCellValueFactory(new PropertyValueFactory<>("maNV"));
            maKH.setCellValueFactory(new PropertyValueFactory<>("maKH"));
            ngayMua.setCellValueFactory(new PropertyValueFactory<>("ngayMua"));
            tongTien.setCellValueFactory(new PropertyValueFactory<>("tongTien"));
            HoaDonTable.setItems(searchHoaDonList);
            connection.close();
        } else {
            JOptionPane.showMessageDialog(null, "Nhập mã khách hàng để tìm kiếm!", "Nhập thông tin", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @FXML
    private void reloadHDEvent(MouseEvent event) throws SQLException {
        HoaDonTable.getItems().clear();
        initHoaDon();
    }

    @FXML
    private void searchChiTietHD(MouseEvent event) throws IOException {
        String maHD = tfSearchHD1.getText();
        ProcessBuilder pb = new ProcessBuilder("Notepad.exe", "C:\\Users\\vodoa\\Desktop\\HoaDon\\HoaDon"+maHD+".txt");
        pb.start();
    }

    @FXML
    private void taoHoaDonMenu(MouseEvent event) throws IOException {
     Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
     String maNV = (String) thisStage.getUserData();
    System.out.println(maNV);
        Parent fxml = FXMLLoader.load(getClass().getResource("../fxmlNhanVien/hoadon.fxml"));
            Scene scene = new Scene(fxml);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Tạo Hóa Đơn");
            stage.setScene(scene);
            stage.setUserData(maNV);
            stage.show();
    }
    
}
