/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.miniprojet_glovo;
import com.mycompany.Controller.Commande;
import com.mycompany.Controller.Produit;
import com.mycompany.Model.Commandedao;
import com.mycompany.Model.DBcnx;
import com.mycompany.Model.Livreurdao;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rihab
 */


public class AfficheCommandeController implements Initializable {
    @FXML 
    private TableView<Commande> tableCmd;  

    @FXML 
    private TableColumn<Commande, String> tabNum;

    @FXML 
    private TableColumn<Commande, String> tabAdresse;

    @FXML 
    private TableColumn<Commande, String> tabCin;

    //**********************************************

   @FXML
private TableView<Produit> tabProduit;  

@FXML 
private TableColumn<Produit, Integer> numeroProduit;

@FXML 
private TableColumn<Produit, String> nomProduit;

Commandedao cd = new Commandedao() ;


//***************************************************
             public void toReturn(Event e) throws IOException
    {
        Scene scene;
           Node node = (Node)e.getSource(); // emplcement dyal button
           Stage stage = (Stage)node.getScene().getWindow(); //stage 
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Commande.fxml"));
         scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

   public void initialize(URL url, ResourceBundle rb) {
        Commandedao CommandeDAO = new Commandedao();

       
        ObservableList<Commande> CommandeList = null;
        try {
            CommandeList = CommandeDAO.getAll_cmd();
        } catch (SQLException ex) {
            Logger.getLogger(AfficheCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tabNum.setCellValueFactory(new PropertyValueFactory<>("id_commande"));
        tabAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse_commande"));
        tabCin.setCellValueFactory(new PropertyValueFactory<>("cin_livreur"));

        tableCmd.setItems(CommandeList);

       
        tableCmd.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                int selectedCommandeId = newSelection.getId_commande();
                ObservableList<Produit> produitList = null;
                try {
                    produitList = CommandeDAO.afficherProd(selectedCommandeId);
                } catch (SQLException ex) {
                    Logger.getLogger(AfficheCommandeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                numeroProduit.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
                nomProduit.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
                tabProduit.setItems(produitList);
            }
        });
    }

    public void supprimer_cmd(Event e) throws SQLException {
        Commande selectedCommande = tableCmd.getSelectionModel().getSelectedItem();
        if (selectedCommande != null) {
            int selectedCommandeId = selectedCommande.getId_commande();
            try {
                cd.supprimer(selectedCommandeId);
                tableCmd.getItems().remove(selectedCommande);
            } catch (SQLException ex) {
                Logger.getLogger(AfficheCommandeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}


 
    