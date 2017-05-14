/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;

/**
 *
 * @author lenovo
 */
public class InformationDetailler {
    
    private Form f;
    private Container ct;
    private Command cm1,cm2;
    private int banned = 0;
    
    public InformationDetailler(Resources theme, Reclamation r, String hh,int id){
        System.out.println(hh);
        UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);
        UIBuilder ui = new UIBuilder();
        f= ui.createContainer(theme, "GUI 3").getComponentForm();
        cm1 = new Command("retour");
        cm2 = new Command("logout"); 
        f.getUnselectedStyle().setBgColor(ColorUtil.GRAY);
        f.getToolbar().addCommandToSideMenu(cm1);
        f.getToolbar().addCommandToOverflowMenu(cm2);
        f.addCommandListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            if (evt.getCommand()==cm2) {
                ListUtilisateurForm luf = new ListUtilisateurForm(theme);
                luf.GetF().show();
                
            }else if (evt.getCommand()==cm1) {
                ListReclamationForm lrf = new ListReclamationForm(theme, id);
                lrf.GetF().show();           
            }
        }
    });
        Label label1 =(Label) ui.findByName("Label1", f);
        Label label2 =(Label) ui.findByName("Label3", f);    
        Label label3 =(Label) ui.findByName("Label4", f);
        ImageViewer imgv = (ImageViewer)  ui.findByName("ImageViewer", f);
        
        EncodedImage encimg = EncodedImage.createFromImage(theme.getImage("round.png"), false);
        
        URLImage urlimg = URLImage.createToStorage(encimg, "FULL_" + "http://localhost/GameHub/web/uploads/urls/"+r.getUrl(), "http://localhost/GameHub/web/uploads/urls/"+r.getUrl());
        urlimg.fetch();
        imgv.setImage(urlimg);
        label1.setText(r.getPseudo());
        label2.setText(r.getDescription());
        ConnectionRequest con4 = new ConnectionRequest();
                    con4.setUrl("http://localhost/GameHubMobile/banned.php?id="+hh);
                    System.out.println("http://localhost/GameHubMobile/banned.php?id="+hh);
                    con4.addResponseListener(new ActionListener<NetworkEvent>() {

                        @Override
                        public void actionPerformed(NetworkEvent evt) {
                            byte[] data = (byte[])evt.getMetaData();
                            String s = new String(data) ;
                            System.out.println(s.trim());
                            if (s.trim().equals("1")) {
                                banned = 1;
                                label3.setText("BANNED");
                                label3.getUnselectedStyle().setFgColor(ColorUtil.GREEN);
                                
                            }else{
                                banned = 0;
                                label3.setText("NOT BANNED ");
                                label3.getUnselectedStyle().setFgColor(ColorUtil.MAGENTA);
                                
                            }
                        }
                    });
                    NetworkManager.getInstance().addToQueue(con4);
        f.refreshTheme();  
    }
    
    public Form getF(){
        return f;
    }   
}