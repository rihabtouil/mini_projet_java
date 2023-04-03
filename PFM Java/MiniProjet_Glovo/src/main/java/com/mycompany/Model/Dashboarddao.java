/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Model;

import com.mycompany.Controller.Commande;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Rihab
 */
public class Dashboarddao {
public float recPrix(int n) throws SQLException {
    float p=0;
    Statement state = DBcnx.getConnection().createStatement();
    ResultSet result = state.executeQuery("SELECT prix FROM produit WHERE id_produit = " + n);
    while (result.next()) {
        p = result.getFloat("prix");
    }
    DBcnx.closeConnection();
    return p;
}

public  double calculer_rev() throws SQLException {
    Statement state = DBcnx.getConnection().createStatement();
    ResultSet result = state.executeQuery("SELECT * FROM commande_produit");
     double revenue =0 ;
    while (result.next()) {
        int idProd = result.getInt("idProd");
        revenue += recPrix(idProd);
    }
    DBcnx.closeConnection();
    return revenue;
}

public ObservableList<Commande> get3Cmd() throws SQLException {
    ObservableList<Commande> commande = FXCollections.observableArrayList();
    Statement state = DBcnx.getConnection().createStatement();
    ResultSet result = state.executeQuery("SELECT * FROM commande ORDER BY num_cmd DESC LIMIT 3");
    while(result.next()) {
        Commande cmd = new Commande();
        cmd.setId_commande(result.getInt(1));
        cmd.setAdresse_commande(result.getString(2));
        cmd.setCin_livreur(result.getString(3));
        commande.add(cmd);
    }
    DBcnx.closeConnection();
    return commande;
}
}
         
     