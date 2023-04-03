/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.miniprojet_glovo;

import com.mycompany.Controller.Commande;
import com.mycompany.Model.Commandedao;
import com.mycompany.Model.Dashboarddao;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rihab
 */
public class DashboardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Button btnlivreur;
    @FXML 
    Label revenu ;
    @FXML 
    private TableView<Commande> tableCmd;  

    @FXML 
    private TableColumn<Commande, String> tabNum;

    @FXML 
    private TableColumn<Commande, String> tabAdresse;

    @FXML 
    private TableColumn<Commande, String> tabCin;
    
    public void toLivreur(Event e) throws IOException
    {
        Scene scene;
           Node node = (Node)e.getSource();
           Stage stage = (Stage)node.getScene().getWindow(); 
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Livreur.fxml"));
         scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void toProduit(Event e) throws IOException
    {
           Scene scene;
           Node node = (Node)e.getSource(); // emplcement dyal button
           Stage stage = (Stage)node.getScene().getWindow(); //stage 
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Produit.fxml"));
         scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void toCommande(Event e) throws IOException
    {  Scene scene;
           Node node = (Node)e.getSource(); // emplcement dyal button
           Stage stage = (Stage)node.getScene().getWindow(); //stage 
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Commande.fxml"));
         scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void toLogin(Event e) throws IOException
    {
          Scene scene;
           Node node = (Node)e.getSource(); // emplcement dyal button
           Stage stage = (Stage)node.getScene().getWindow(); //stage 
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
         scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       Dashboarddao d = new Dashboarddao();
        try {
            revenu.setText(Double.toString(d.calculer_rev() ));
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
      Dashboarddao DashboardDAO = new Dashboarddao();

ObservableList<Commande> CommandeList = null;
try {
    CommandeList = DashboardDAO.get3Cmd();
} catch (SQLException ex) {
    Logger.getLogger(AfficheCommandeController.class.getName()).log(Level.SEVERE, null, ex);
}

tabNum.setCellValueFactory(new PropertyValueFactory<>("id_commande"));
tabAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse_commande"));
tabCin.setCellValueFactory(new PropertyValueFactory<>("cin_livreur"));

tableCmd.setItems(CommandeList);
    }    
    
}
