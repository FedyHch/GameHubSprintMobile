/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Command;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;

/**
 *
 * @author Laser
 */
public class HomeForm {
    Form f;
    ImageViewer imgv;
    Command Jeux,events,reclamations,profile,astuces,logout;
    
    public HomeForm(Resources theme){
    UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);
        UIBuilder ui = new UIBuilder();
        f= ui.createContainer(theme, "Home").getComponentForm();
        
        imgv = (ImageViewer)  ui.findByName("ImageViewer", f);
        
//        EncodedImage encimg = EncodedImage.createFromImage(theme.getImage("round.png"), false);
//        
//        URLImage urlimg = URLImage.createToStorage(encimg, "logo", "http://localhost/GameHub/uploads/affiches/logo%20game%20hub.png");
//         urlimg.fetch(); 
//         imgv.setImage(urlimg);
         Jeux = new Command("Jeux");
         events = new Command("Events");
         reclamations = new Command("Reclamer");
         astuces = new Command("Astuces");
         profile = new Command("Profile"); 
         logout = new Command("Logout");
         
         
         f.getToolbar().setTitle("Menu");
         f.getToolbar().setTitleCentered(true);
         f.getToolbar().addCommandToSideMenu(Jeux);
         f.getToolbar().addCommandToSideMenu(events);
         f.getToolbar().addCommandToSideMenu(reclamations);
         f.getToolbar().addCommandToSideMenu(astuces);
         
         f.getToolbar().addCommandToOverflowMenu(profile);
         f.getToolbar().addCommandToOverflowMenu(logout);
         
         
    
         
         f.addCommandListener((ActionListener) (ActionEvent evt) -> {
             
             if (evt.getCommand()==Jeux) {
             ListJeuxForm ljf = new ListJeuxForm(theme);
             ljf.getF().show();
             }
    
         
         });
    
    }
    
    public Form getF(){
        return f;
    }
    
}
