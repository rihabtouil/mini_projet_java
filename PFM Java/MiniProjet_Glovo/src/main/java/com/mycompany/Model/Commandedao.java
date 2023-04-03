/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Model;

import com.mycompany.Controller.Commande;
import com.mycompany.Controller.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Rihab
 */
public class Commandedao {
   
    
        public ObservableList<Produit> getAll() throws SQLException
    {
        ObservableList produit = FXCollections.observableArrayList();
            Statement state = DBcnx.getConnection().createStatement();
        ResultSet result = state.executeQuery("select * from produit");
        while(result.next())
        {
            Produit prd = new Produit();
            prd.setId_produit(result.getInt(1));
            prd.setNom_produit(result.getString(2));
            prd.setPrix_produit(result.getFloat(3));
            produit.add(prd);
        }
        DBcnx.closeConnection();
        
          return produit ;
    }
       //*******************************************************
           public void ajouter(Commande c) throws SQLException
    {
        String request = "insert into commande (adresse_cmd, cin_livreur) values (? , ? )";
       Connection connection= DBcnx.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
      
      preparedStatement.setString(1 ,c.getAdresse_commande());
      preparedStatement.setString(2 ,c.getCin_livreur() );
      preparedStatement.execute();
      
      ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
    if (generatedKeys.next()) {
        int id = generatedKeys.getInt(1);
        c.setId_commande(id);
    }
    } 
//*******************************************************************

           public void supprimer(int p) throws SQLException {
    Connection connection = DBcnx.getConnection();
    try {
        connection.setAutoCommit(false); 

        String deleteCommandeProduitQuery = "DELETE FROM commande_produit WHERE numCmd=?";
        PreparedStatement deleteCommandeProduitStatement = connection.prepareStatement(deleteCommandeProduitQuery);
        deleteCommandeProduitStatement.setInt(1, p);
        deleteCommandeProduitStatement.executeUpdate();

        String deleteCommandeQuery = "DELETE FROM commande WHERE num_cmd=?";
        PreparedStatement deleteCommandeStatement = connection.prepareStatement(deleteCommandeQuery);
        deleteCommandeStatement.setInt(1, p);
        deleteCommandeStatement.executeUpdate();

        connection.commit(); 
    } catch (SQLException ex) {
        connection.rollback(); 
        throw ex;
    } finally {
        connection.setAutoCommit(true); 
    }
}
//**************************************************************
    public void modifier(int id,Commande cNew) throws SQLException
    {
        
        String request = "UPDATE commande SET adresse_cmd=? , cin_livreur = ?  WHERE num_cmd = ? ";
          Connection connection= DBcnx.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(request);
        preparedStatement.setString(1, cNew.getAdresse_commande());
         preparedStatement.setString(2, cNew.getCin_livreur());
         preparedStatement.setInt(3, id);
        preparedStatement.execute();
    }     
 //*******************************************************
    public void remplir(int num ,int id , String nom) throws SQLException
    {
     String request = "insert into commande_produit (numCmd, idProd , nomProd) values (? , ?,? )";  
            Connection connection= DBcnx.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(request);
                preparedStatement.setInt(1, num);
                preparedStatement.setInt(2, id);
                preparedStatement.setString(3, nom);
                preparedStatement.execute();
    }
 //************************************************************
    public void vider(int p , int c) throws SQLException
    {
        String request = "delete from commande_produit where numCmd = ? AND idProd = ?";
         Connection connection= DBcnx.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setInt(1, c);
            preparedStatement.setInt(2, p);
           preparedStatement.execute();
        
    }
    //******************************************
          public ObservableList<Commande> getAll_cmd() throws SQLException
    {
        ObservableList commande = FXCollections.observableArrayList();
            Statement state = DBcnx.getConnection().createStatement();
        ResultSet result = state.executeQuery("select * from commande");
        while(result.next())
        {
            Commande prd = new Commande();
            prd.setId_commande(result.getInt(1));
            prd.setAdresse_commande(result.getString(2));
            prd.setCin_livreur(result.getString(3));
            commande .add(prd);
        }
        DBcnx.closeConnection();
        
          return commande  ;
    }
          //**************************************************

          public ObservableList<Produit> afficherProd(int numCmd) throws SQLException {
    ObservableList<Produit> commande_prod = FXCollections.observableArrayList();
    Statement state = DBcnx.getConnection().createStatement();
    ResultSet result = state.executeQuery("select idProd, nomProd from commande_produit where numCmd = " + numCmd);

    while (result.next()) {
        Produit prd = new Produit();
        prd.setId_produit(result.getInt("idProd"));
        prd.setNom_produit(result.getString("nomProd"));
        commande_prod.add(prd);
    }

    DBcnx.closeConnection();

    return commande_prod;
}
//************************************************************  
 

}
