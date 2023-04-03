/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.miniprojet_glovo;

import com.mycompany.Controller.Livreur;
import com.mycompany.Model.Livreurdao;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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
public class LivreurController implements Initializable {

    @FXML
    private TextField txtNom_liv ;
    @FXML
    private TextField txtCin_liv ;
    @FXML
    private TextField txtTele_liv ;
    @FXML
    private Label cor ;
    @FXML
    private Button btnModify_liv ;
     @FXML
    private Button btnDelete_liv;
   
    @FXML 
private TableView<Livreur> tableLiv;  

@FXML 
private TableColumn<Livreur, String> tabCin;

@FXML 
private TableColumn<Livreur, String> tabNom;

@FXML 
private TableColumn<Livreur, String> tabTele;
    Livreur livreur = new Livreur();
    Livreurdao lv = new Livreurdao();
    
    public void ajouter_liv(Event e) throws SQLException
    {
        livreur.setNom_livreur(txtNom_liv.getText());
        livreur.setCin_livreur(txtCin_liv.getText());
        livreur.setTelephone(txtTele_liv.getText());
        lv.ajouter(livreur); 
        UpdateTable();
        cor.setText("enregistrer avec succees !");
    }

    public void supprimer_liv(Event e) {
    btnDelete_liv.setOnMouseClicked(event -> {
        if (event.getClickCount() == 1) {
            Livreur selectedLivreur = tableLiv.getSelectionModel().getSelectedItem();
            try {
                lv.supprimer(selectedLivreur);
                tableLiv.getItems().remove(selectedLivreur); // Supprime la ligne correspondante de la table
            } catch (SQLException ex) {
                Logger.getLogger(LivreurController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    });
    cor.setText("Supprimé avec succès !");
}
    public void modifier_liv(Event e)
    {
         btnModify_liv.setOnMouseClicked(event->{
      if(event.getClickCount()==1)
              
             {
                 Livreur selectedLivreur = tableLiv.getSelectionModel().getSelectedItem() ;
                 Livreur newLivreur = new Livreur();
                 newLivreur.setCin_livreur(txtCin_liv.getText());
                 newLivreur.setNom_livreur(txtNom_liv.getText());
                 newLivreur.setTelephone(txtTele_liv.getText());
          try {
              //cor.setText( selectedLivreur.getNom_livreur());
              lv.modifier(selectedLivreur, newLivreur);
                UpdateTable();
              
          } catch (SQLException ex) {
              Logger.getLogger(LivreurController.class.getName()).log(Level.SEVERE, null, ex);
          }
              }
      });
         cor.setText("modifier avec succees !");
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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Livreurdao livreurDAO = new Livreurdao();
    
    // récupérer tous les livreurs de la base de données
    ObservableList<Livreur> livreurList = null;
        try {
            livreurList = livreurDAO.getAll();
        } catch (SQLException ex) {
            Logger.getLogger(LivreurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    // associer les colonnes à des propriétés de la classe Livreur
    tabCin.setCellValueFactory(new PropertyValueFactory<>("cin_livreur"));
    tabNom.setCellValueFactory(new PropertyValueFactory<>("nom_livreur"));
    tabTele.setCellValueFactory(new PropertyValueFactory<>("telephone"));
    
    // ajouter les données au TableView
    tableLiv.setItems(livreurList);
    }    

    private void UpdateTable() {
               Livreurdao livreurDAO = new Livreurdao();
    
    // récupérer tous les livreurs de la base de données
    ObservableList<Livreur> livreurList = null;
        try {
            livreurList = livreurDAO.getAll();
        } catch (SQLException ex) {
            Logger.getLogger(LivreurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        tabCin.setCellValueFactory(new PropertyValueFactory<>("cin_livreur"));
        tabNom.setCellValueFactory(new PropertyValueFactory<>("nom_livreur"));
        tabTele.setCellValueFactory(new PropertyValueFactory<>("telephone"));
       tableLiv.setItems(livreurList);
              }
    

  
}
