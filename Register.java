/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.charts.util.ColorUtil;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import com.twilio.rest.lookups.v1.PhoneNumber;

import java.io.IOException;



/**
 *
 * @author Foued
 */
public class Register {
    private Form register;
    Command back,profile,logout;
    public static final String ACCOUNT_SID = "AC2fac373e3deccf2c7bb3d0054e8f6db1";
    public static final String AUTH_TOKEN = "ad19818314f42b80f93c35de1180a653";
    
    public Register(Resources theme){
         register = new Form("Register", BoxLayout.y());
         register.getUnselectedStyle().setBgColor(ColorUtil.GRAY);
//           Toolbar tb = new Toolbar(true);
//        register.setToolbar(tb);
//        tb.setUIID("Container");
//        register.getTitleArea().setUIID("Container");
//        Form previous = Display.getInstance().getCurrent();
//        tb.setBackCommand("", e -> previous.showBack());
//        register.setUIID("SignIn");
        
         TextField username=new TextField("", "Username");
         TextField email=new TextField("", "E_mail");
         TextField password = new TextField("", "Password",20,TextField.PASSWORD);
         TextField confpassword = new TextField("", "Password",20,TextField.PASSWORD);
         TextField pseudo=new TextField("", "Pseudo");
         TextField nom=new TextField("", "nom");
         TextField prenom=new TextField("", "Prenom");
         TextField tel=new TextField("", "Telephone");
         
         register.add(username);
         register.add(email);
         register.add(password);
         register.add(confpassword);
         register.add(pseudo);
         register.add(nom);
         register.add(prenom);
         register.add(tel);
         Button btninscri = new Button("Register");
         register.add(btninscri);
         
         back = new Command("Back",theme.getImage("back-command.png"));
    profile = new Command("Profile"); 
    logout = new Command("Logout");
      register.getToolbar().addCommandToOverflowMenu(profile);
    register.getToolbar().addCommandToOverflowMenu(logout);
    register.getToolbar().addCommandToLeftBar(back);
     register.addCommandListener((ActionListener) (ActionEvent evt) ->
    {   
        if(evt.getCommand()==back){
        LoginForm lf = new LoginForm(theme);
        lf.getF().show();
        }
        
        
        
    });
      register.addCommandListener((ActionListener) (ActionEvent evt) ->
    {   
        if(evt.getCommand()==logout){
        LoginForm lf = new LoginForm(theme);
        lf.getF().show();
        }
        
        
        
    });
         btninscri.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent evt) {
                 if(password.getText().toString().equals(confpassword.getText().toString())){
                     ConnectionRequest req = new ConnectionRequest();
                     req.setUrl("http://localhost/gameHubMobile/register.php?username="+username.getText()+"&email="+email.getText()+"&password2="+password.getText()+"&pseudo="+pseudo.getText()+"&nom="+nom.getText()+"&prenom="+prenom.getText()+"&tel="+tel.getText());
                req.addResponseListener(new ActionListener<NetworkEvent>() {

                         @Override
                         public void actionPerformed(NetworkEvent evt) {
                             byte[] data = (byte[]) evt.getMetaData();
                            String s = new String(data);

                            if (s.equals("success")) {
                                
                                Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                                
                                Message message = Message.creator(new com.twilio.type.PhoneNumber(tel.getText()),
                                        new com.twilio.type.PhoneNumber("(509) 774-2795"),
                                        " : Félicitations !"+ username.getText()+ " "+ prenom.getText()+ " Vous etes maintenant inscrit à Game_Hub !" ).create();
                                System.out.println(message.getSid());
                                Dialog.show("Confirmation", "ajout ok", "Ok", null);
                            } else {
                                System.out.println(s);
                                 Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                                 
                                Message message = Message.creator(new com.twilio.type.PhoneNumber(tel.getText()),
                                        new com.twilio.type.PhoneNumber("(509) 774-2795"),
                                        " : Félicitations !"+ username.getText()+ " "+ prenom.getText()+ " Vous etes maintenant inscrit à Game_Hub !" ).create();
                                System.out.println(message.getSid());
                                Dialog.show("Confirmation", "Inscri failed", "Ok", null);
                            }
                        }
                    });

                      
                NetworkManager.getInstance().addToQueue(req);
                 }else {
                    Dialog.show("Error", "Password and confirm password does not match", "Ok", null);
                }
             }
         });
    }
    
   
     public Form getF(){
        return register;
    }
}
