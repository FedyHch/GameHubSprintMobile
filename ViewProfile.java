/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.ImageViewer;
import com.codename1.components.WebBrowser;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Fakher_XoX
 */
public class ViewProfile {
    Form f;
    Command back;
    public ViewProfile(Resources theme, int id){
    UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);
    
    UIBuilder ui = new UIBuilder();
    
    f= ui.createContainer(theme, "GUI 4").getComponentForm();
    Label labelNom =(Label) ui.findByName("Label", f);
    ImageViewer imgv = (ImageViewer)  ui.findByName("ImageViewer", f);
    Label labeltype =(Label) ui.findByName("Label1", f);
    Label labelconsole =(Label) ui.findByName("Label2", f);
    Label labelsign =(Label) ui.findByName("Label3", f);
    Label labeldate_cr =(Label) ui.findByName("Label4", f);
    Label labeldate_modif =(Label) ui.findByName("Label5", f);
    Button btn=(Button) ui.findByName("Button",f);
     back = new Command("Back",theme.getImage("back-command.png"));
         f.getToolbar().addCommandToLeftBar(back);
         f.addCommandListener((evt) -> {
    
             if (evt.getCommand()==back){
                 HomePage hp = new HomePage(theme);
                 hp.getF().show();
             }
         });
      
    ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIMobile/getprofil.php?id="+id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                Channel ch=getChannel(new String(con.getResponseData())).get(0);
                       labelNom.setText("Nom Channel :"+ch.getNomC());
                       labeltype.setText("Type de Jeux :"+ch.getType());
                       labelconsole.setText("Console Utilisé : "+ch.getConsole());
                       labelsign.setText("Votre Signature : "+ch.getSign());
                       labeldate_cr.setText("Date de Creation du Profil"+ch.getMembre_dep());
                       labeldate_modif.setText("Date de Modification :"+ch.getDate_modif());
                        EncodedImage encimg = EncodedImage.createFromImage(theme.getImage("loading-gear.gif"), false);
        
         URLImage urlimg = URLImage.createToStorage(encimg, "L_" + "http://localhost/PIMobile/"+ch.getUrl_pdp(),"http://localhost/PIMobile/"+ ch.getUrl_pdp());
         urlimg.fetch(); 
         imgv.setImage(urlimg);
   
               btn.addActionListener((evt2) -> {
                   ChannelStreaming cs = new ChannelStreaming(theme,ch.getUrl_chaine());
                   cs.GetF().show();
               });
                }
               
                

            });
        
    
        NetworkManager.getInstance().addToQueue(con);
    }
    
        
        
   
    
         
         
         public ArrayList<Channel> getChannel(String json) {
        ArrayList<Channel> profil = new ArrayList<>();
         try {

            JSONParser j = new JSONParser();

            Map<String, Object> channel = j.parseJSON(new CharArrayReader(json.toCharArray()));

            System.out.println();
            List<Map<String, Object>> list = (List<Map<String, Object>>) channel.get("ProfileChannel");

            for (Map<String, Object> obj : list) {
                Channel ch = new Channel();
                
                ch.setNomC(obj.get("nomC").toString());
                ch.setType(obj.get("type").toString());
                ch.setUrl_pdp(obj.get("url_pdp").toString());
                ch.setUrl_chaine(obj.get("url_chaine").toString());
                ch.setConsole(obj.get("console").toString());
                ch.setSign(obj.get("signature").toString());
                ch.setMembre_dep(obj.get("membre_depuis").toString());
                ch.setDate_modif(obj.get("date_modif").toString());
                
                profil.add(ch);

            }

        } catch (IOException ex) {
         }

        return profil;

    }
 public Form GetF(){
        return f;
    }



    
    }
    

