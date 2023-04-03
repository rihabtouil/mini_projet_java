/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.miniprojet_glovo;

import com.mycompany.Controller.Admin;
import com.mycompany.Model.Logindao;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rihab
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    TextField txtuser ;
    @FXML
    PasswordField txtpassword ;
    @FXML
    Button btnsignin ;
    @FXML
    Label error ;
    
    Admin admin = new Admin() ;
    Logindao lg = new Logindao();
    
    public void signin(Event e) throws SQLException, IOException
    {
        admin.setUsername(txtuser.getText());
        admin.setPassword(txtpassword.getText());
        if(lg.isLogin(admin))
        {
              Scene scene;
           Node node = (Node)e.getSource(); // emplcement dyal button
           Stage stage = (Stage)node.getScene().getWindow(); //stage 
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Dashboard.fxml"));
         scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
            
        }
        else
        {
           
            error.setText("username or password is incorrect");
           
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
