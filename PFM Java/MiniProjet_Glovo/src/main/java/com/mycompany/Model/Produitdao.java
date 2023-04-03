/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Model;

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
public class Produitdao {
     Statement state ;
    PreparedStatement preparedStatement ;
    
    
    public void ajouter(Produit p) throws SQLException
    {
    String request = "insert into produit (nom_produit, prix) values (? , ? )";
    Connection connection= DBcnx.getConnection();
      preparedStatement = connection.prepareStatement(request);
      
      preparedStatement.setString(1 ,p.getNom_produit());
      preparedStatement.setFloat(2 ,p.getPrix_produit() );
      
       
       preparedStatement.execute();
    
     }
    //***********************************************************
       public void supprimer(Produit p) throws SQLException
    {
        String request = "delete from produit where id_produit = ? ";
         Connection connection= DBcnx.getConnection();
         preparedStatement = connection.prepareStatement(request);
            preparedStatement.setInt(1, p.getId_produit());
         
           preparedStatement.execute();
    }
       //*****************************************************
           public void modifier(Produit p,Produit pNew) throws SQLException
    {
        
        String request = "UPDATE produit SET nom_produit=? , prix = ?  WHERE id_produit = ? ";
          Connection connection= DBcnx.getConnection();
         preparedStatement = connection.prepareStatement(request);
        preparedStatement.setString(1, pNew.getNom_produit());
         preparedStatement.setFloat(2, pNew.getPrix_produit());
         preparedStatement.setInt(3, p.getId_produit());
        preparedStatement.execute();
    }
    //************************************************************
        public ObservableList<Produit> getAll() throws SQLException
    {
        ObservableList produit = FXCollections.observableArrayList();
        state =  DBcnx.getConnection().createStatement() ;
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
    
}
