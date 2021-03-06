/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qliphongkham;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.robot.Robot;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class ChuController implements Initializable {
    
    @FXML
    private ImageView exit;
    @FXML
    private ImageView mini;
    private boolean isFull;
    double x,y;
    @FXML
    private AnchorPane parent;
    @FXML
    private HBox titleBar;
    @FXML
    private Pane myPane;
    @FXML
    private HBox logOut;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            exit.setOnMouseClicked(event ->{
                System.exit(0);
            });
            Parent fxml = FXMLLoader.load(getClass().getResource("fxmlChu/dichvu.fxml"));
            myPane.getChildren().removeAll();
            myPane.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(ChuController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    

    @FXML
    private void logOutEvent(MouseEvent event) throws IOException, Exception {
        Stage thisStage = (Stage) logOut.getScene().getWindow();
        thisStage.close();
        Main main1 = new Main();
        Stage stage = new Stage();
        main1.start(stage);
//        Parent login = FXMLLoader.load(getClass().getResource("Login.fxml"));
//                        Scene loginS = new Scene(login);
//                        Stage stage = new Stage();
//			stage.setScene(loginS);
//                        stage.initStyle(StageStyle.UTILITY);
//                        stage.setTitle("????ng Nh???p");
//                        stage.setResizable(false);
//			stage.show();
    }

    @FXML
    private void drag() {
        titleBar.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        titleBar.setOnMouseDragged(event -> {
            LoginController.stageFirst.setX(event.getScreenX() - x);
            LoginController.stageFirst.setY(event.getScreenY() - y);
        });
        
       
    }

    @FXML
    private void minimize(MouseEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    private void maximum(MouseEvent event) {
        
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        if (!isFull){
        isFull = true;
        stage.setFullScreen(isFull);
        }
        else{
        isFull = false;
        stage.setFullScreen(isFull);
        }
    }

    @FXML
    private void dichvu(MouseEvent event) throws IOException {
        
        Parent fxml = FXMLLoader.load(getClass().getResource("fxmlChu/dichvu.fxml"));
               myPane.getChildren().removeAll();
               myPane.getChildren().setAll(fxml);
                        
    }

    @FXML
    private void thuoc(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("fxmlChu/thuoc.fxml"));
               myPane.getChildren().removeAll();
               myPane.getChildren().setAll(fxml);
    }

    @FXML
    private void hoadon(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("fxmlChu/timHoaDon.fxml"));
               myPane.getChildren().removeAll();
               myPane.getChildren().setAll(fxml);
    }

    @FXML
    private void khachhang(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("fxmlChu/khachhang.fxml"));
               myPane.getChildren().removeAll();
               myPane.getChildren().setAll(fxml);
    }

    @FXML
    private void nhanvien(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("fxmlChu/nhanvien.fxml"));
               myPane.getChildren().removeAll();
               myPane.getChildren().setAll(fxml);
    }

    
}
