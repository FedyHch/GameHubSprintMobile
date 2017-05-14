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
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Foued
 */
public class UpdateMembre {
    public Form f;
    Command back,profile,logout;
    public UpdateMembre(Resources theme,String s){
        f = new Form("Update", BoxLayout.y());
        f.getUnselectedStyle().setBgColor(ColorUtil.GRAY);
         TextField username=new TextField("", "Username");
         TextField email=new TextField("", "E_mail");
         TextField password = new TextField("", "Password",20,TextField.PASSWORD);
         TextField confpassword = new TextField("", "Password",20,TextField.PASSWORD);
         TextField pseudo=new TextField("", "Pseudo");
         TextField nom=new TextField("", "nom");
         TextField prenom=new TextField("", "Prenom");
         TextField tel=new TextField("", "Telephone");
//            username.setText(m.getUsername());
//            email.setText(m.getEmail());
//            password.setText(m.getPassword2());
//            confpassword.setText(m.getPassword2());
//            pseudo.setText(m.getPseudo());
//            nom.setText(m.getNom());
//            prenom.setText(m.getPrenom());
//            tel.setText(String.valueOf(m.getTel()));
                    
   
         f.add(username);
         f.add(email);
         f.add(password);
         f.add(confpassword);
         f.add(pseudo);
         f.add(nom);
         f.add(prenom);
         f.add(tel);
         Button btnedit = new Button("Modifier");
         f.add(btnedit);
                 back = new Command("Back",theme.getImage("back-command.png"));
    profile = new Command("Profile"); 
    logout = new Command("Logout");
      f.getToolbar().addCommandToOverflowMenu(profile);
    f.getToolbar().addCommandToOverflowMenu(logout);
    f.getToolbar().addCommandToLeftBar(back);
     f.addCommandListener((ActionListener) (ActionEvent evt) ->
    {   
        if(evt.getCommand()==back){
        SelectUser su = new SelectUser(theme,s);
        su.getF().show();
        }
        
        
        
    });
      f.addCommandListener((ActionListener) (ActionEvent evt) ->
    {   
        if(evt.getCommand()==logout){
        LoginForm lf = new LoginForm(theme);
        lf.getF().show();
        }
        
        
        
    });
         btnedit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                if(password.getText().toString().equals(confpassword.getText().toString())){
                    ConnectionRequest req = new ConnectionRequest();
                   req.setUrl("http://localhost/gameHubMobile/update.php?username="+username.getText()+"&email="+email.getText()+"&password2="+password.getText()+"&pseudo="+pseudo.getText()+"&nom="+nom.getText()+"&prenom="+prenom.getText()+"&tel="+tel.getText()+"&user="+s);
                   req.addResponseListener(new ActionListener<NetworkEvent>() {

                        @Override
                        public void actionPerformed(NetworkEvent evt) {
                            byte[] data = (byte[]) evt.getMetaData();
                            String s = new String(data);
                            if (s.equals("success")) {
                    Dialog.show("Confirmation", "Edit ok", "Ok", null);
                }
                        }
                    });
                           NetworkManager.getInstance().addToQueue(req);

                }
            }
        });
         
    }
    public Form getF(){
        return f;
    }
    
}
