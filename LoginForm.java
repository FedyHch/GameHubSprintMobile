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
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Foued
 */
public class LoginForm {
    private Form f;
    private TextField usr,pwd;
    private Button btnOk;
    private Button btninscri;
    private String stn;
    
    public LoginForm(Resources theme){
        f = new Form("Login", BoxLayout.y()); 
        f.getUnselectedStyle().setBgColor(ColorUtil.GRAY);
        Label l = new Label("        Bienvenue à Game_Hub");
        l.getUnselectedStyle().setFgColor(ColorUtil.MAGENTA);
        f.add(l);
        usr = new TextField("", "Username",20,TextField.ANY);
         pwd = new TextField("", "Password",20,TextField.PASSWORD);
         
         f.add(usr);        
         f.add(pwd);
         btnOk = new Button("Se Connecter");
         f.add(btnOk);
         btninscri= new Button("Register");
         f.add(btninscri);
         
         Label l1 = new Label("        Elaboré par slashETC");
         
        l1.getUnselectedStyle().setFgColor(ColorUtil.MAGENTA);
        f.add(l1);
        
         ImageViewer img = new ImageViewer(theme.getImage("logo.png"));
         f.add(img);
         
         
         btnOk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println("username="+usr.getText()+"password2="+pwd.getText());
             
                 ConnectionRequest req = new ConnectionRequest();
                 req.setUrl("http://localhost/gameHubMobile/login.php?username="+usr.getText()+"&password2="+pwd.getText()
                         );
                   
                 req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
           byte[] data = (byte[]) evt.getMetaData();
         String s = new String(data);
   
         if (!s.equals("0")) {
         Dialog.show("Confirmation", "Login success", "Ok", null);
         SelectUser su = new SelectUser(theme,stn=usr.getText());
         su.getF().show();
        
        } else {
         Dialog.show("Confirmation", "Login failed", "Ok", null);
                }
         
         
         
         }
         });

          NetworkManager.getInstance().addToQueue(req);
         }
           
         });

       btninscri.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                Register r = new Register(theme);
                r.getF().show();
            }
        });
             
    }
    public Form getF(){
        return f;
    }
}
