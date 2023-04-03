/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.miniprojet_glovo;

import com.mycompany.Controller.Produit;
import com.mycompany.Model.Produitdao;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rihab
 */
   
public class ProduitController implements Initializable {
    
    
        @FXML
       private TextField txtNom_pr;
        @FXML
       private TextField txtPrix_pr ;
         @FXML
        private Label cor ;
             @FXML 
        private TableView<Produit> tableprod;  

            @FXML 
        private TableColumn<Produit, String> tabId;

            @FXML 
          private TableColumn<Produit, String> tabNom;

            @FXML 
                private TableColumn<Produit, String> tabPrix;
            @FXML 
                    private Button btnSupprimer ;
            
            @FXML 
                    private Button btnModify_pr;
            
         Produit produit = new Produit();
        Produitdao pr = new Produitdao();
         
     public void toReturn(Event e) throws IOException
    {
        Scene scene;
           Node node = (Node)e.getSource(); // emplcement dyal button
           Stage stage = (Stage)node.getScene().getWindow(); //stage 
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Dashboard.fxml"));
         scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
     //****************************************************************
       public void ajouter_pr(Event e) throws SQLException
    {
        produit.setNom_produit(txtNom_pr.getText());
        produit.setPrix_produit(Float.parseFloat(txtPrix_pr.getText()));
       
        pr.ajouter(produit); 
        UpdateTable();
        cor.setText("enregistrer avec succees !");
    }
       //**********************************************************
       public void supprimer_pr()
   {
       
      btnSupprimer.setOnMouseClicked(event->{
          
      if(event.getClickCount()==1)
              
             {
                 Produit selectedProduit = tableprod.getSelectionModel().getSelectedItem() ;
          try {
              //cor.setText( selectedLivreur.getNom_livreur());
              pr.supprimer(selectedProduit);
                tableprod.getItems().remove(selectedProduit);
              
          } catch (SQLException ex) {
              Logger.getLogger(LivreurController.class.getName()).log(Level.SEVERE, null, ex);
          }
              }
      });
      cor.setText("supprimer avec succees !");
   
   }
     //*******************************************************
         public void modifier_pr()
    {
         btnModify_pr.setOnMouseClicked(event->{
      if(event.getClickCount()==1)
              
             {
                 Produit selectedProduit = tableprod.getSelectionModel().getSelectedItem() ;
                 Produit newProduit = new Produit();
               newProduit.setNom_produit(txtNom_pr.getText());
               newProduit.setPrix_produit(Float.parseFloat(txtPrix_pr.getText()));
                 
          try {
              //cor.setText( selectedLivreur.getNom_livreur());
              pr.modifier(selectedProduit, newProduit);
               UpdateTable();
              
          } catch (SQLException ex) {
              Logger.getLogger(LivreurController.class.getName()).log(Level.SEVERE, null, ex);
          }
              }
      });
         cor.setText("modifier avec succees !");
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            Produitdao livreurDAO = new Produitdao();
    
    ObservableList<Produit> produitList = null;
        try {
           produitList = livreurDAO.getAll();
        } catch (SQLException ex) {
            Logger.getLogger(LivreurController.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    tabId.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
    tabNom.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
    tabPrix.setCellValueFactory(new PropertyValueFactory<>("prix_produit"));

    tableprod.setItems(produitList);
    }    

    private void UpdateTable() {
                   Produitdao livreurDAO = new Produitdao();
   
    ObservableList<Produit> produitList = null;
        try {
           produitList = livreurDAO.getAll();
        } catch (SQLException ex) {
            Logger.getLogger(LivreurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    // associer les colonnes à des propriétés de la classe Livreur
    tabId.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
    tabNom.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
    tabPrix.setCellValueFactory(new PropertyValueFactory<>("prix_produit"));
    
    // ajouter les données au TableView
    tableprod.setItems(produitList);
    }
    
}
