/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;


/**
 *
 * @author Fakher_XoX
 */
public class AddProfile {
    private Form current,f1,f2,f3;
    private Resources theme;
    private ImageViewer img;
    private TextField txt1,txt2;
    private Label lb1,lb2,lb3,lb4,lb5,lb6;
    private CheckBox ch1,ch2,ch3,ch4,ch5,ch6;
    private ComboBox cb1;
    private TextArea txta;
    private Container ct;
    private Button btadd;
    private String type="",pdp="";
    private Command back;
    
    public AddProfile(Resources theme){
     UIBuilder ui = new UIBuilder();
        
        UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);
        UIBuilder.registerCustomComponent("ComponentGroup", ComponentGroup.class);
        ct= ui.createContainer(theme, "GUI 1");
        f1=(Form) ct; 
back = new Command("Back",theme.getImage("back-command.png"));
f1.getToolbar().addCommandToLeftBar(back);
         f1.addCommandListener((evt) -> {
    
             if (evt.getCommand()==back){
                 HomePage hp = new HomePage(theme);
                 hp.getF().show();
             }
         });
        
        
        
        f1.getToolbar().addCommandToOverflowMenu("Changer Avatar",null,new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent evt) {
            pdp=Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
         }});
        
         txt1= (TextField) ui.findByName("TextField", ct);
         txt2= (TextField) ui.findByName("TextField1", ct);
         lb1= (Label) ui.findByName("Label", ct);
         lb2= (Label) ui.findByName("Label1", ct);
         lb3= (Label) ui.findByName("Label2", ct);
         lb4= (Label) ui.findByName("Label3", ct);
         lb5= (Label) ui.findByName("Label4", ct);
         lb6=(Label) ui.findByName("Label5", ct);
         img=(ImageViewer) ui.findByName("ImageViewr", ct);
          btadd=(Button) ui.findByName("Button", ct);
         ch1=(CheckBox) ui.findByName("CheckBox", ct);
         ch2=(CheckBox) ui.findByName("CheckBox1", ct);
         ch3=(CheckBox) ui.findByName("CheckBox2", ct);
         ch4=(CheckBox) ui.findByName("CheckBox3", ct);
         ch5=(CheckBox) ui.findByName("CheckBox4", ct);
         ch6=(CheckBox) ui.findByName("CheckBox5", ct);
         txta=(TextArea) ui.findByName("TextArea",ct);
         cb1=(ComboBox) ui.findByName("ComboBox", ct);
        
       
         
            btadd.addActionListener((evt) -> {
              if(ch1.isSelected()) type+=ch1.getText();
          if(ch2.isSelected())type+=","+ch2.getText();
         if(ch3.isSelected()) type+=","+ch3.getText();
          if(ch4.isSelected()) type+=","+ch4.getText();
           if(ch5.isSelected()) type+=","+ch5.getText();
            if(ch6.isSelected()) type+=","+ch6.getText();
             
                 Channel ch = new Channel(1,txt1.getText(),type, cb1.getSelectedItem().toString(),pdp,txt2.getText(),txta.getText());
                 System.out.println(pdp);
                 new ChannelDAO().addChannel(ch);
                 
                AddProfile add = new AddProfile(theme);
              add.getF().show();
                 
                 
             type="";
           });
            
            
    } public Form getF(){
        return f1;
    }
}    

