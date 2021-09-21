/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qliphongkham;

import static com.sun.webkit.graphics.WCImage.getImage;
import java.io.File;
import static java.lang.Thread.sleep;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.StageStyle;


public class Main extends Application {
	@Override
	public void start(Stage login) throws Exception {
			Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                        Image img = new Image(getClass().getResourceAsStream("icon/icons8_clinic_120px.png"));
			Scene scene1 = new Scene(root);
                        login.getIcons().add(img);
			login.setScene(scene1);
                        login.setResizable(false);
                        login.setTitle("Đăng Nhập");
			login.show();
                        

	}

	public static void main(String[] args) {
		launch(args);
	}
}