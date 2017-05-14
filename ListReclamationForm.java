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
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
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
public class ListReclamationForm {
    private Form f;
    private Container ct;
    private String h;
    private Command cm1,cm2;
    private Button btajout;
    
    public ListReclamationForm(Resources theme,int id){
     
    UIBuilder uib = new UIBuilder();
    ct=uib.createContainer(theme, "GUI 2");
    f=(Form) ct;
    f.getUnselectedStyle().setBgColor(ColorUtil.GRAY);
    f.setTitle("Reclamations de "+id);
    cm2 = new Command("logout");
    btajout = (Button) uib.findByName("Button", ct);
    f.getToolbar().addCommandToOverflowMenu(cm2);
    
    f.addCommandListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent evt) {
            if (evt.getCommand()==cm2) {
                ListUtilisateurForm luf = new ListUtilisateurForm(theme);
                luf.GetF().show();
                
            }
        }
    });
    
    
    ConnectionRequest con2 = new ConnectionRequest();
            con2.setUrl("http://localhost/GameHubMobile/ListReclamation.php?id="+id);
            con2.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                for( Reclamation r : getListReclamations(new String(con2.getResponseData()))){
                    String pseudo = r.getPseudo();
                    String description = r.getDescription();
                    String url = r.getUrl();
                    ConnectionRequest con3 = new ConnectionRequest();
                    con3.setUrl("http://localhost/GameHubMobile/pseudoId.php?pseudo="+pseudo);
                    System.out.println("http://localhost/GameHubMobile/pseudoId.php?pseudo="+pseudo);
                    con3.addResponseListener(new ActionListener<NetworkEvent>() {

                        @Override
                        public void actionPerformed(NetworkEvent evt) {
                            byte[] data = (byte[])evt.getMetaData();
                            h = new String(data) ;
                            System.out.println(h);
                            ct.add(createContainer(theme,r,h,id));
                            ct.refreshTheme();
                        }
                    });
                    NetworkManager.getInstance().addToQueue(con3);        
                }
                //Button btajout = new Button("ajouter une reclamation");
                //ct.add(btajout);
                ct.refreshTheme();
                btajout.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        AjoutReclamationForm ar = new AjoutReclamationForm(theme, id);
                        ar.GetF().show();
                    }
                });
            }
        });
        NetworkManager.getInstance().addToQueue(con2);        
    }
    
    public Form GetF(){
        return f;
    }
    public ArrayList<Reclamation> getListReclamations(String json) {
        ArrayList<Reclamation> listReclamations = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();
            Map<String, Object> reclamations = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println();
            List<Map<String, Object>> list = (List<Map<String, Object>>) reclamations.get("reclamation");

            for (Map<String, Object> obj : list) {
                Reclamation r = new Reclamation();
                r.setPseudo(obj.get("pseudo").toString());
                r.setId_m(Integer.parseInt(obj.get("id_m").toString()));
                r.setDescription(obj.get("description").toString());
                r.setUrl(obj.get("url").toString());
                listReclamations.add(r);
            }
        } catch (IOException ex) {
         }
        return listReclamations;
    } 
    
    public Container createContainer(Resources theme,Reclamation c,String hh,int id){
        Label pseudo = new Label(c.getPseudo());
        EncodedImage encimg = EncodedImage.createFromImage(theme.getImage("round.png"), false);    
        URLImage urlimg = URLImage.createToStorage(encimg, "FULL_" + "http://localhost/GameHub/web/uploads/urls/"+c.getUrl(), "http://localhost/GameHub/web/uploads/urls/"+c.getUrl());        
        urlimg.fetch();
        Label test1= new Label(urlimg);
        Button bt = new Button("Plus d' information");
        Container cont = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        cont.add(bt);
        cont.add(pseudo);
        Container cont2 = BorderLayout.center(cont);
        cont2.add(BorderLayout.WEST,test1);
        cont2.setLeadComponent(bt);
        bt.addActionListener((ActionListener) (ActionEvent evt) -> {
            System.out.println(hh);
            InformationDetailler idt = new InformationDetailler(theme, c,hh,id);
            idt.getF().show();
        });
        return cont2;
    }
}