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
public class Videos {
    private Integer id_pub,id_chaine;
    private String type,url,titre,desc;
    private Date uploaded;

    public Integer getId_pub() {
        return id_pub;
    }

    public void setId_pub(Integer id_pub) {
        this.id_pub = id_pub;
    }

    public Integer getId_chaine() {
        return id_chaine;
    }

    public void setId_chaine(Integer id_chaine) {
        this.id_chaine = id_chaine;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getUploaded() {
        return uploaded;
    }

    public void setUploaded(Date uploaded) {
        this.uploaded = uploaded;
    }

    public Videos(Integer id_chaine, String type, String url, String titre, String desc, Date uploaded) {
        this.id_chaine = id_chaine;
        this.type = type;
        this.url = url;
        this.titre = titre;
        this.desc = desc;
        this.uploaded = uploaded;
    }

    @Override
    public String toString() {
        return "Videos{" + "id_pub=" + id_pub + ", id_chaine=" + id_chaine + ", type=" + type + ", url=" + url + ", titre=" + titre + ", desc=" + desc + ", uploaded=" + uploaded + '}';
    }

  
    
    
    
}
