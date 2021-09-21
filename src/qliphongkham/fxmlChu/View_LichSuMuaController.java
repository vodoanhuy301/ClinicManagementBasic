/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qliphongkham.fxmlChu;

import Class.LichSuKham;
import Class.LichSuMua;
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
public class View_LichSuMuaController implements Initializable {

    @FXML
    private TextField tfSearch;
    @FXML
    private TableView<LichSuMua> lichSuMua;
    @FXML
    private TableColumn<LichSuMua, Integer> maKH;
    @FXML
    private TableColumn<LichSuMua, String> ngayMua;
    @FXML
    private TableColumn<LichSuMua, String> thuocMua;
    @FXML
    private TableColumn<LichSuMua, Integer> maHD;

    /**
     * Initializes the controller class.
     */
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    ObservableList<LichSuMua> lichSuMuaList = FXCollections.observableArrayList();
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initLichSuMua();
        
    }
    private void initLichSuMua(){
        try {
            connection = DBconnection.getConnect();
            
            rs= connection.createStatement().executeQuery("select * from khmuathuoc");
            
            while (rs.next()){
                lichSuMuaList.add(new LichSuMua(rs.getInt("idKH"),rs.getString("ngaymua"),rs.getString("thuocmua"),rs.getInt("idHD")));
                
                maKH.setCellValueFactory(new PropertyValueFactory<>("maKH"));
                ngayMua.setCellValueFactory(new PropertyValueFactory<>("ngayMua"));
                thuocMua.setCellValueFactory(new PropertyValueFactory<>("thuocMua"));
                maHD.setCellValueFactory(new PropertyValueFactory<>("maHD"));
                lichSuMua.setItems(lichSuMuaList);
            }    
            
        } catch (SQLException ex) {
            Logger.getLogger(View_LichSuMuaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void searchMaKH(MouseEvent event) {
        if (tfSearch.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập mã Khách Hàng","Lỗi", JOptionPane.INFORMATION_MESSAGE);
        }
        int searchWord = parseInt(tfSearch.getText());
          lichSuMua.getItems().clear();
          ObservableList<LichSuMua> searchMaKHList = FXCollections.observableArrayList();
          connection = DBconnection.getConnect();
        try {
            rs= connection.createStatement().executeQuery("select * from khmuathuoc WHERE idKH="+searchWord);
        
             while (rs.next()){
                searchMaKHList.add(new LichSuMua(rs.getInt("idKH"),rs.getString("ngaymua"),rs.getString("thuocmua"),rs.getInt("idHD")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(View_LichSuMuaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        maKH.setCellValueFactory(new PropertyValueFactory<>("maKH"));
        ngayMua.setCellValueFactory(new PropertyValueFactory<>("ngayMua"));
        thuocMua.setCellValueFactory(new PropertyValueFactory<>("thuocMua"));
        maHD.setCellValueFactory(new PropertyValueFactory<>("maHD"));
        lichSuMua.setItems(lichSuMuaList);
         System.out.println("OK OK");
    }

    @FXML
    private void searchMaHD(MouseEvent event) {
        if (tfSearch.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập mã Khách Hàng","Lỗi", JOptionPane.INFORMATION_MESSAGE);
        }
        int searchWord = parseInt(tfSearch.getText());
          lichSuMua.getItems().clear();
          ObservableList<LichSuMua> searchMaKHList = FXCollections.observableArrayList();
          connection = DBconnection.getConnect();
        try {
            rs= connection.createStatement().executeQuery("select * from khmuathuoc WHERE idHD="+searchWord);
        
             while (rs.next()){
                searchMaKHList.add(new LichSuMua(rs.getInt("idKH"),rs.getString("ngaymua"),rs.getString("thuocmua"),rs.getInt("idHD")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(View_LichSuMuaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        maKH.setCellValueFactory(new PropertyValueFactory<>("maKH"));
        ngayMua.setCellValueFactory(new PropertyValueFactory<>("ngayMua"));
        thuocMua.setCellValueFactory(new PropertyValueFactory<>("thuocMua"));
        maHD.setCellValueFactory(new PropertyValueFactory<>("maHD"));
        lichSuMua.setItems(lichSuMuaList);
         System.out.println("OK OK");
    }

    @FXML
    private void reloadEvent(MouseEvent event) {
         lichSuMua.getItems().clear();
         initLichSuMua();
    }
    
}
