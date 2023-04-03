/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Controller;

/**
 *
 * @author Rihab
 */
public class Livreur {
    private String nom_livreur ;
    private String cin_livreur ;
    private String telephone ;

    public Livreur() {
    }

    public String getNom_livreur() {
        return nom_livreur;
    }

    public void setNom_livreur(String nom_livreur) {
        this.nom_livreur = nom_livreur;
    }

    public String getCin_livreur() {
        return cin_livreur;
    }

    public void setCin_livreur(String cin_livreur) {
        this.cin_livreur = cin_livreur;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Livreur{" + "nom_livreur=" + nom_livreur + ", cin_livreur=" +
                cin_livreur + ", telephone=" + telephone + '}';
    }

}
