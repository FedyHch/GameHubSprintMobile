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
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Laser
 */
public class ListJeuxForm {
    Form hi = new Form("Liste des Jeux", new BorderLayout());
    Command home,profile,logout;
    Tabs tab ;
    
    public ListJeuxForm (Resources theme){
    
    tab = new Tabs();
    Form f1=new Form();
    f1.getToolbar().setVisible(false);
    
    Form f2 = new Form();
    f2.getToolbar().setVisible(false);
    
    tab.addTab("Liste", f1);
    tab.addTab("top Jeux", f2);
    
    
        home= new Command("Home");
        profile = new Command("Profile"); 
        logout = new Command("Logout");
        hi.getToolbar().setTitle("Jeux-vidéo");
        hi.getToolbar().setTitleCentered(true);
        hi.getToolbar().addCommandToOverflowMenu(profile);
        hi.getToolbar().addCommandToOverflowMenu(logout);
        hi.getToolbar().addCommandToLeftBar(home);
        hi.addCommandListener((ActionListener) (ActionEvent evt) -> {
            if(evt.getCommand()==home){
                HomeForm hf = new HomeForm(theme);
                hf.getF().show();
            }
            
        });
        
        Container cont = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container cont2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/GameHubMobile/select.php");
        con.addResponseListener((NetworkEvent evt) -> {
            System.out.println(getListJeux(new String(con.getResponseData())));
//            sp.setText(getListJeux(new String(con.getResponseData()))+"");
//            hi.refreshTheme();
                    
                    for(Jeux jx : getListJeux(new String(con.getResponseData())) ){
                        cont.add(createContainer(theme,jx));
                        
                    }
//                    for(Jeux jx : topJeux(getListJeux(new String(con.getResponseData())))){
//                        cont2.add(createContainer(theme,jx));
//                    }
                    
                    
                    f1.add(cont);
//                    f2.add(cont2);
                    hi.add(BorderLayout.CENTER,tab);
                    hi.show();
                    

            
        });
        
        
        ConnectionRequest con2 = new ConnectionRequest();
        con2.setUrl("http://localhost/GameHubMobile/selectTop.php");
        con2.addResponseListener((NetworkEvent evt) -> {
            System.out.println(getListJeux(new String(con2.getResponseData())));
//            sp.setText(getListJeux(new String(con.getResponseData()))+"");
//            hi.refreshTheme();
                    
                    for(int i=0;i<3;i++ ){
                        cont2.add(createContainer(theme,getListJeux(new String(con2.getResponseData())).get(i)));
                        
                    }

                    
                    
                    
                    f2.add(cont2);
                    
                    

            
        });
        
        
        NetworkManager.getInstance().addToQueue(con);
        NetworkManager.getInstance().addToQueue(con2);
        
        
       
        
    } 
    

    public ArrayList<Jeux> getListJeux(String json) {
        ArrayList<Jeux> listJeux = new ArrayList<>();
        try{
            JSONParser j = new JSONParser();
            Map<String, Object> jeux = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println();
            List<Map<String, Object>> list = (List<Map<String, Object>>) jeux.get("jeux");
            for (Map<String, Object> obj : list) {
                Jeux je = new Jeux();
                je.setId_jeux(Integer.parseInt( obj.get("id_jeux").toString()));
                je.setNom(obj.get("nom").toString());
                je.setGenre(obj.get("genre").toString());
                je.setNote(Double.parseDouble(obj.get("note").toString()));
                je.setDescription(obj.get("description").toString());
                je.setDate_sortie(obj.get("date_sortie").toString());
                je.setClassification(obj.get("classification").toString());
                je.setMode(obj.get("mode").toString());
                je.setPrix(Double.parseDouble(obj.get("prix").toString()));
                je.setAffiche(obj.get("affiche").toString());
                je.setTrailer(obj.get("trailer").toString());
                
                listJeux.add(je);
                

            }
            
        } catch (IOException ex) {
            System.out.println("err parsing JSON");
         }
        
        return listJeux;
        
        
    }
    
    public Container createContainer(Resources theme,Jeux j){
        Label nom = new Label("Titre : "+j.getNom());
        
        
        EncodedImage encimg = EncodedImage.createFromImage(theme.getImage("round.png"), false);
        
         URLImage urlimg = URLImage.createToStorage(encimg, "FULL_" + "http://localhost/GameHub/uploads/affiches/"+j.getAffiche(), "http://localhost/GameHub/uploads/affiches/"+j.getAffiche());
         urlimg.fetch();
         

         
        
         Label img= new Label(urlimg);
         
         
//        Button bt = new Button("Détail");
       
        Label lbGenre = new Label("Genre : "+j.getGenre());
        
        Container cont = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        cont.add(nom);
        cont.add(lbGenre);
//        cont.add(bt);
        Container cont2 = BorderLayout.center(cont);
        
        cont2.add(BorderLayout.EAST,img);
        cont2.setLeadComponent(lbGenre);
        lbGenre.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
            System.out.println(j.toString());
            System.out.println(urlimg);
            DetailForm df = new DetailForm(theme, j);
            df.getF().show();
           
            
        });
        
        System.out.println(j+"test");
        
        return cont2;
    }
    
//    public ArrayList<Jeux> topJeux(ArrayList<Jeux> lst){
//        ArrayList<Jeux> lstop = new ArrayList<>();
//        Collections.sort(lst, (Jeux j2, Jeux j1) -> ((Double)j2.getNote()).compareTo((Double)j1.getNote()));
//        
//        
//        for (int i = 0; i < 3; i++) {
//            lstop.add(lst.get(i));
//            
//        }
//        
//        
//        return lstop;
//        
//    }

    public Form getF(){
        return hi;
    }
    
} 

