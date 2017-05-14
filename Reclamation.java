/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;



/**
 *
 * @author lenovo
 */

public class Reclamation {
    
    private String pseudo ;
    private int id_m ;
    private String description ;
    private String url ;
 

    public Reclamation() {
    }

    public Reclamation(String pseudo, int id_m, String description, String url) {
        this.pseudo = pseudo;
        this.id_m = id_m;
        this.description = description;
        this.url = url;
        
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getId_m() {
        return id_m;
    }

    public void setId_m(int id_m) {
        this.id_m = id_m;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

   

   


    @Override
    public String toString() {
        return "Reclamation{" + "pseudo=" + pseudo + ", id_m=" + id_m + ", description=" + description + ", url=" + url + '}';
    }
        
}
