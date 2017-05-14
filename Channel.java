/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import java.util.Date;



/**
 *
 * @author Fakher_XoX
 */
public class Channel {
private Integer id_chaine,id_membre;
private String nomC,type,console,url_pdp,url_chaine,sign;
private String membre_dep,date_modif;

    public Channel() {
    }

    public Channel(Integer id_membre, String nomC, String type, String console, String url_pdp, String url_chaine, String sign) {
        this.id_membre = id_membre;
        this.nomC = nomC;
        this.type = type;
        this.console = console;
        this.url_pdp = url_pdp;
        this.url_chaine = url_chaine;
        this.sign = sign;
    }

    

    public Channel(Integer id_chaine, Integer id_membre, String nomC, String type, String console, String url_pdp, String url_chaine, String sign, String membre_dep, String date_modif) {
        this.id_chaine = id_chaine;
        this.id_membre = id_membre;
        this.nomC = nomC;
        this.type = type;
        this.console = console;
        this.url_pdp = url_pdp;
        this.url_chaine = url_chaine;
        this.sign = sign;
        this.membre_dep = membre_dep;
        this.date_modif = date_modif;
    }

    public Integer getId_chaine() {
        return id_chaine;
    }

    public void setId_chaine(Integer id_chaine) {
        this.id_chaine = id_chaine;
    }

    public Integer getId_membre() {
        return id_membre;
    }

    public void setId_membre(Integer id_membre) {
        this.id_membre = id_membre;
    }

   

    

    public String getNomC() {
        return nomC;
    }

    public void setNomC(String nomC) {
        this.nomC = nomC;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public String getUrl_pdp() {
        return url_pdp;
    }

    public void setUrl_pdp(String url_pdp) {
        this.url_pdp = url_pdp;
    }

    public String getUrl_chaine() {
        return url_chaine;
    }

    public void setUrl_chaine(String url_chaine) {
        this.url_chaine = url_chaine;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getMembre_dep() {
        return membre_dep;
    }

    public void setMembre_dep(String membre_dep) {
        this.membre_dep = membre_dep;
    }

    public String getDate_modif() {
        return date_modif;
    }

    public void setDate_modif(String date_modif) {
        this.date_modif = date_modif;
    }

    @Override
    public String toString() {
        return "Channel{" + "id_chaine=" + id_chaine + ", id_membre=" + id_membre + ", nomC=" + nomC + ", type=" + type + ", console=" + console + ", url_pdp=" + url_pdp + ", url_chaine=" + url_chaine + ", sign=" + sign + ", membre_dep=" + membre_dep + ", date_modif=" + date_modif + '}';
    }

    

    



    
}
