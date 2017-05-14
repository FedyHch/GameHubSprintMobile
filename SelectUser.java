/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.charts.util.ColorUtil;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Foued
 */
public class SelectUser {
    private Form profil;
    private Container ct,ct1,ct2,ct3,ct4,ct5;
    Command profile,logout;
    Membre ma;
    public SelectUser(Resources theme,String s){
        profil= new Form("Profil", BoxLayout.y());
        profil.getUnselectedStyle().setBgColor(ColorUtil.GRAY);
        ct = new Container(new BoxLayout(BoxLayout.X_AXIS));
        ct1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        ct2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        ct3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        ct4 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        ct5 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                 profile = new Command("Profile"); 
                  logout = new Command("Logout");
                  profil.getToolbar().addCommandToOverflowMenu(profile);
                  profil.getToolbar().addCommandToOverflowMenu(logout);
    
    
      profil.addCommandListener((ActionListener) (ActionEvent evt1) ->
    {   
        if(evt1.getCommand()==logout){
        LoginForm lf = new LoginForm(theme);
        lf.getF().show();
        }
        
        
        
    });
        
         
        
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl("http://localhost/gameHubMobile/select.php?username="+s);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ma=getListMembre(new String(req.getResponseData())).get(0); 
                 
                 
                 Label ll = new Label("Username :");
                 ct.add(ll);
                 ll.getUnselectedStyle().setFgColor(ColorUtil.MAGENTA);

                 Label l = new Label(ma.getUsername());
                 ct.add(l);
                 l.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
                 Label ll1 = new Label("E_mail :");
                 ct1.add(ll1);
                 ll1.getUnselectedStyle().setFgColor(ColorUtil.MAGENTA);
                 Label l1= new Label(ma.getEmail());
                 ct1.add(l1);
                 l1.getUnselectedStyle().setFgColor(ColorUtil.BLUE);

                 Label l22 = new Label("Pseudo :");
                 ct2.add(l22);
                 l22.getUnselectedStyle().setFgColor(ColorUtil.MAGENTA);
                 Label l2= new Label(ma.getPseudo());
                 ct2.add(l2);
                 l2.getUnselectedStyle().setFgColor(ColorUtil.BLUE);

                 Label l33=new Label("Nom :");
                 ct3.add(l33);
                 l33.getUnselectedStyle().setFgColor(ColorUtil.MAGENTA);
                 Label l3= new Label(ma.getNom());
                 ct3.add(l3);
                 l3.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
                 Label l44 = new Label("Prenom :");
                 ct4.add(l44);  
                 l44.getUnselectedStyle().setFgColor(ColorUtil.MAGENTA);
                 Label l4= new Label(ma.getPrenom());
                 ct4.add(l4);
                 l4.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
                 Label l55 = new Label("Telephone :");
                 ct5.add(l55);
                 l55.getUnselectedStyle().setFgColor(ColorUtil.MAGENTA);
                 Label l5= new Label(ma.getTel());
                 l5.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
                 ct5.add(l5);
                 profil.add(ct);
                 profil.add(ct1);
                 profil.add(ct2);
                 profil.add(ct3);
                 profil.add(ct4);
                 profil.add(ct5);
         
                 Button delete = new Button("Delete");
                 profil.refreshTheme();
        profil.add(delete);
        delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                DeleteUser ds = new DeleteUser();
                Membre m=new Membre();
               ds.delete(m,s);
            }
        });
                 
            }
            
        });
        Button Edit = new Button("Edit");
        profil.add(Edit);
        Edit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                UpdateMembre um = new UpdateMembre(theme,s);
                um.getF().show();
                
            }
        });
        NetworkManager.getInstance().addToQueue(req);
        
  }
    public ArrayList<Membre> getListMembre(String json) {
        ArrayList<Membre> listMembres = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();

            Map<String, Object> membres = j.parseJSON(new CharArrayReader(json.toCharArray()));

            System.out.println();
            List<Map<String, Object>> list = (List<Map<String, Object>>) membres.get("membre");

            for (Map<String, Object> obj : list) {
                Membre e = new Membre();
                e.setUsername(obj.get("username").toString());
                e.setEmail(obj.get("email").toString());
                e.setPseudo(obj.get("pseudo").toString());
                e.setNom(obj.get("nom").toString());
                e.setPrenom(obj.get("prenom").toString());
                e.setTel(obj.get("tel").toString());


              
                listMembres.add(e);

            }

        } catch (IOException ex) {
         }
        return listMembres;

    }
    
    public Form getF(){
        return profil;
    }
}
