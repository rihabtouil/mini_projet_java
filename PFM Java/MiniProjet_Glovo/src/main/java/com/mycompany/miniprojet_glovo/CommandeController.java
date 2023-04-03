/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.miniprojet_glovo;

import com.mycompany.Controller.Commande;
import com.mycompany.Controller.Livreur;
import com.mycompany.Controller.Produit;
import com.mycompany.Model.Commandedao;
import com.mycompany.Model.Livreurdao;
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
public class CommandeController implements Initializable {
    @FXML
    private Button btn_remplir ;
    @FXML
    private Button btn_vider ;
         @FXML 
        private TableView<Produit> tableprod;  

            @FXML 
        private TableColumn<Produit, String> tabId;

            @FXML 
          private TableColumn<Produit, String> tabNom;

            @FXML 
                private TableColumn<Produit, String> tabPrix;
            @FXML
            private TextField adresse_cmd;
             @FXML
            private TextField cin_cmd;
             @FXML
            private Label cor ;
              @FXML
            private Label msg ;
              @FXML
            private TextField numero_cmd ;
              
                  @FXML 
        private TableView<Livreur> tabLiv;  

            @FXML 
        private TableColumn<Livreur, String> tabNomLiv;

            @FXML 
          private TableColumn<Livreur, String> tabcinLiv;
             @FXML 
          private TableColumn<Livreur, String> tabtelLiv;  
              
              
             Commande commande = new Commande();
             Commandedao cd = new Commandedao() ;
             
             //******************************************    
      
             public void ajouter_cmd(Event e) throws SQLException {
    Livreur selectedLivreur = tabLiv.getSelectionModel().getSelectedItem();
    
    if (selectedLivreur == null) {
        
        msg.setText("Veuillez sélectionner un livreur.");
    } else {
        commande.setAdresse_commande(adresse_cmd.getText());
        commande.setCin_livreur(selectedLivreur.getCin_livreur());
        cd.ajouter(commande);
        cor.setText(Integer.toString(commande.getId_commande()));
        msg.setText("Commande enregistrée avec succès !");
        
        //cin_cmd.setText(selectedLivreur.getCin_livreur());
    }
}
            

             
//***********************************************************************

             
            /*   public void modifier_cmd(Event e) throws SQLException
             {
                 int id_sup = Integer.parseInt(numero_cmd.getText());
                  commande.setAdresse_commande(adresse_cmd.getText());
                  commande.setCin_livreur(cin_cmd.getText());
                 cd.modifier(id_sup, commande);
                 msg.setText("modifier avec succees !");
             }*/
               
public void remplir_prod() {
    int num = Integer.parseInt(cor.getText());

    btn_remplir.setOnAction(event -> {
        Produit selectedProduit = tableprod.getSelectionModel().getSelectedItem();

        try {
            cd.remplir(num, selectedProduit.getId_produit(), selectedProduit.getNom_produit());
        } catch (SQLException ex) {
            Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    });
}
        public void vider_prod() 
         {
              int num = Integer.parseInt(cor.getText());
                 btn_vider.setOnMouseClicked(event -> {
        if (event.getClickCount() == 1) {
            Produit selectedProduit = tableprod.getSelectionModel().getSelectedItem();

            try {
            
              cd.vider(selectedProduit.getId_produit(),num);
            } catch (SQLException ex) {
                Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    });
         }
             
             
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
         public void display_cmd(Event e) throws IOException
         {
           Scene scene;
           Node node = (Node)e.getSource(); // emplcement dyal button
           Stage stage = (Stage)node.getScene().getWindow(); //stage 
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/afficheCommande.fxml"));
         scene = new Scene(root);
        stage.setScene(scene);
        stage.show();  
         }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
               Produitdao ProduitDAO = new Produitdao();
    
 
    ObservableList<Produit> produitList = null;
        try {
           produitList = ProduitDAO.getAll();
        } catch (SQLException ex) {
            Logger.getLogger(LivreurController.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    tabId.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
    tabNom.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
    tableprod.setItems(produitList);
    
    //**************************************************************
       
         Livreurdao livreurDAO = new Livreurdao();
    
    ObservableList<Livreur> livreurList = null;
        try {
            livreurList = livreurDAO.getAll();
        } catch (SQLException ex) {
            Logger.getLogger(LivreurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    tabcinLiv.setCellValueFactory(new PropertyValueFactory<>("cin_livreur"));
    tabNomLiv.setCellValueFactory(new PropertyValueFactory<>("nom_livreur"));
    tabtelLiv.setCellValueFactory(new PropertyValueFactory<>("telephone"));
    
    tabLiv.setItems(livreurList);
    }  
    
    }    
    

