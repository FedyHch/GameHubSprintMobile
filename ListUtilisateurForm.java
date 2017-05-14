/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lenovo
 */
public class ListUtilisateurForm {
    
    private Form f;
    private Container ct1;
    private ComboBox combobox ;
    
    public ListUtilisateurForm(Resources theme){
        UIBuilder uib = new UIBuilder();
        ct1 = uib.createContainer(theme, "GUI 1"); 
        f= (Form) ct1;
        combobox = (ComboBox) uib.findByName("ComboBox", ct1);
        
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/GameHubMobile/ListUtilisateur.php");
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                for(Membre m : getIdMembres(new String(con.getResponseData())) ){
                    combobox.addItem(m.getId_membre());
                }
               
                f.refreshTheme();

            }
        });
        NetworkManager.getInstance().addToQueue(con);
        try {
            combobox.addActionListener((ActionListener) (ActionEvent evt)->{
            int id = (Integer)combobox.getSelectedItem();
            System.out.println(id);
            
            ListReclamationForm lrf = new ListReclamationForm(theme, id);
            lrf.GetF().show();
            
            
        });
            
        } catch (NullPointerException e) {
            Dialog.show("erreur", "pas de reclamation", "ok",null);
        }
       
        

    }
    
    public ArrayList<Membre> getIdMembres(String json) {
        ArrayList<Membre> idmembres = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();

            Map<String, Object> membres = j.parseJSON(new CharArrayReader(json.toCharArray()));

            System.out.println();
            List<Map<String, Object>> list = (List<Map<String, Object>>) membres.get("membre");

            for (Map<String, Object> obj : list) {
                Membre m = new Membre();
                m.setId_membre(Integer.parseInt(obj.get("id_membre").toString()));
             
                idmembres.add(m);

            }

        } catch (IOException ex) {
         }
        return idmembres;

    }  
    
    public Form GetF(){
        return f;
    }
}
