/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Model;

import com.mycompany.Controller.Livreur;
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
public class Livreurdao  {
 
    Statement state ;
    PreparedStatement preparedStatement ;
    
    
    public void ajouter(Livreur l) throws SQLException
    {
   String request = "insert into livreur (nom , cin ,telephone) values (? , ? ,?)";
    Connection connection= DBcnx.getConnection();
      preparedStatement = connection.prepareStatement(request);
      
      preparedStatement.setString(1 , l.getNom_livreur());
      preparedStatement.setString(2 , l.getCin_livreur());
       preparedStatement.setString(3 , l.getTelephone() );
       
       preparedStatement.execute();
    
     }
    
    //************************************************************************
    public void supprimer(Livreur l) throws SQLException
    {
        String request = "delete from livreur where cin = ? ";
         Connection connection= DBcnx.getConnection();
         preparedStatement = connection.prepareStatement(request);
         
           preparedStatement.setString(1 , l.getCin_livreur());
           preparedStatement.execute();
    }
    
    //***********************************************************************
    public void modifier(Livreur l,Livreur lNew) throws SQLException
    {
        
        String request = "UPDATE livreur SET nom = ? , cin = ? , telephone=?  WHERE cin = ? ";
          Connection connection= DBcnx.getConnection();
         preparedStatement = connection.prepareStatement(request);
           preparedStatement.setString(1 , lNew.getNom_livreur());
      preparedStatement.setString(2 , lNew.getCin_livreur());
       preparedStatement.setString(3 , lNew.getTelephone() );
        preparedStatement.setString(4 , l.getCin_livreur());
        preparedStatement.execute();
    }
    

    //**********************************************************************
    public ObservableList<Livreur> getAll() throws SQLException
    {
        ObservableList livreur = FXCollections.observableArrayList();
        state =  DBcnx.getConnection().createStatement() ;
        ResultSet result = state.executeQuery("select * from livreur");
        while(result.next())
        {
            Livreur liv = new Livreur();
            liv.setNom_livreur(result.getString(1));
            liv.setCin_livreur(result.getString(2));
            liv.setTelephone(result.getString(3));
            livreur.add(liv);
        }
        DBcnx.closeConnection();
        
          return livreur ;
    }

    
    
    
    
}
