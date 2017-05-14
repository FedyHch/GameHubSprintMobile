/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Command;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;

/**
 *
 * @author Fakher_XoX
 */
public class ChannelStreaming {
    Form f;
    Command back;

    public ChannelStreaming(Resources theme, String url) {
     
        
         f = new Form("Streaming");
            back = new Command("Back",theme.getImage("back-command.png"));
        f.getToolbar().addCommandToLeftBar(back);
         f.addCommandListener((evt) -> {
    
             if (evt.getCommand()==back){
                 HomePage hp = new HomePage(theme);
                 hp.getF().show();
             }
         });
        f.setLayout(new BorderLayout());
         BrowserComponent bc = new BrowserComponent();
       bc.setURL(url);      
       f.addComponent(BorderLayout.CENTER,bc);
    }
    public Form GetF(){
        return f;
    }
    
    
}
