/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Command;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;

/**
 *
 * @author Fakher_XoX
 */
public class HomePage {
    Form f;
    Command AddProfile;
    Command ViewProfile;
    
   
    public HomePage(Resources theme){
            UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);
  UIBuilder ui = new UIBuilder();
    f= ui.createContainer(theme, "GUI 2").getComponentForm();
    Label labelNom =(Label) ui.findByName("Label", f);
        ImageViewer imgv = (ImageViewer)  ui.findByName("ImageViewer", f);
       AddProfile=new Command("AddProfile");
       ViewProfile=new Command("ViewProfile");
       
    f.getToolbar().addCommandToSideMenu(AddProfile);
    f.getToolbar().addCommandToSideMenu(ViewProfile);
    f.addCommandListener((evt) -> {
        if(evt.getCommand()==AddProfile){
            AddProfile ad= new AddProfile(theme);
       ad.getF().show();
        }
        
        
        else if(evt.getCommand()==ViewProfile){
            SelectProfile sp = new SelectProfile(theme);
            sp.GetF().show();
            
            }
        
    });
    
   }
    public Form getF(){
        return f;
    }
}
