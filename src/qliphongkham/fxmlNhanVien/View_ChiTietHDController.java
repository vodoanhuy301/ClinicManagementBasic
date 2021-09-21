/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qliphongkham.fxmlNhanVien;

import Class.ChiTietHD;
import Class.MatHang;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.ModuleLayer.Controller;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author vodoa
 */
public class View_ChiTietHDController implements Initializable {

    @FXML
    private TextArea tfChiTietHD;
    @FXML
    private VBox vb;
    ChiTietHD ct;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            vb.setOnMouseMoved(e ->{
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            ct =new ChiTietHD((ChiTietHD)stage.getUserData());
            InHoaDon();
            });
            
            
    }
    private void InHoaDon(){
        String mathang="";
        for (MatHang i : ct.getMatHang()){
         mathang = mathang+ i.toString();
        }
        String string ="Mã Hóa Đơn: "+String.valueOf(ct.getHoaDon().getMaHD())+"\n"+ ct.getHoaDon().toString()+"\n"+mathang;
        tfChiTietHD.setText(string);
    }    
    @FXML
    private void saveFileHD(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        ChiTietHD ct = new ChiTietHD((ChiTietHD)stage.getUserData());
        File myObj = new File("C:\\Users\\vodoa\\Desktop\\HoaDon\\"+"HoaDon"+String.valueOf(ct.getHoaDon().getMaHD())+".txt");
        FileWriter myWriter = new FileWriter("C:\\Users\\vodoa\\Desktop\\HoaDon\\"+"HoaDon"+String.valueOf(ct.getHoaDon().getMaHD())+".txt");
        myWriter.write(tfChiTietHD.getText());
        myWriter.close();
    }
    @FXML
    private void exitViewHD(MouseEvent event) {
        Stage stage = (Stage) tfChiTietHD.getScene().getWindow();
        stage.close();
    }
    
}
