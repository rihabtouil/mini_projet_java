/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Controller;

import java.util.ArrayList;

/**
 *
 * @author Rihab
 */
public class Commande {
    private int id_commande ;
    private String adresse_commande ;
    private String cin_livreur;
 
 
    public Commande() {
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public String getAdresse_commande() {
        return adresse_commande;
    }

    public String getCin_livreur() {
        return cin_livreur;
    }

    public void setCin_livreur(String cin_livreur) {
        this.cin_livreur = cin_livreur;
    }

    public void setAdresse_commande(String adresse_commande) {
        this.adresse_commande = adresse_commande;
    }

 

 

   

 

   
}
