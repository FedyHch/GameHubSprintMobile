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
public class Membre {
    private int id_membre;

    public Membre() {
    }

    public Membre(int id_membre) {
        this.id_membre = id_membre;
    }

    public int getId_membre() {
        return id_membre;
    }

    public void setId_membre(int id_membre) {
        this.id_membre = id_membre;
    }

    @Override
    public String toString() {
        return "membre{" + "id_membre=" + id_membre + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id_membre;
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
        final Membre other = (Membre) obj;
        if (this.id_membre != other.id_membre) {
            return false;
        }
        return true;
    }
    
    
}
