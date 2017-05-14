/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;



/**
 *
 * @author Laser
 */
public class Jeux {
    private int id_jeux;
    private int id_admin;
    private String nom;
    private String genre;
    private double note;
    private String description;
    private String date_sortie;
    private String classification;
    private String mode;
    private double prix;
    private String affiche;
    private String trailer;

    public int getId_jeux() {
        return id_jeux;
    }

    public void setId_jeux(int id_jeux) {
        this.id_jeux = id_jeux;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_sortie() {
        return date_sortie;
    }

    public void setDate_sortie(String date_sortie) {
        this.date_sortie = date_sortie;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getAffiche() {
        return affiche;
    }

    public void setAffiche(String affiche) {
        this.affiche = affiche;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public Jeux(String nom, String genre, double note, String description, String date_sortie, String classification, String mode, double prix, String affiche, String trailer) {
        this.nom = nom;
        this.genre = genre;
        this.note = note;
        this.description = description;
        this.date_sortie = date_sortie;
        this.classification = classification;
        this.mode = mode;
        this.prix = prix;
        this.affiche = affiche;
        this.trailer = trailer;
    }

    @Override
    public String toString() {
        return "jeux{" + "id_jeux=" + id_jeux + ", id_admin=" + id_admin + ", nom=" + nom + ", genre=" + genre + ", note=" + note + ", description=" + description + ", date_sortie=" + date_sortie + ", classification=" + classification + ", mode=" + mode + ", prix=" + prix + ", affiche=" + affiche + ", trailer=" + trailer + '}';
    }

    public Jeux() {
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.id_jeux;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Jeux other = (Jeux) obj;
        if (this.id_jeux != other.id_jeux) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
}
