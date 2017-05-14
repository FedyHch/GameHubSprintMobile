/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.File;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Style;
import java.io.IOException;
import java.util.Random;
/**
 *
 * @author Fakher_XoX
 */
public class ChannelDAO {
    private ConnectionRequest connectionRequest;
    public void addChannel(Channel ch){
    
        /*connectionRequest.setUrl("http://localhost/PIMobile/insert.php?nomC=" + ch.getNomC() + "&type=" + ch.getType() + "&console=" + ch.getConsole() + "&url_pdp=" + ch.getUrl_pdp() + "&url_chaine=" +ch.getUrl_chaine() + "&sign=" + ch.getSign());*/
         MultipartRequest request = new MultipartRequest() {
             @Override
            protected void postResponse() {
            ToastBar.Status status = ToastBar.getInstance().createStatus();
            status.setMessage("Add Succefully to Channel BD");
            status.setIcon(createIcon(FontImage.MATERIAL_WORK));
            status.setShowProgressIndicator(true);
            status.setExpires(3000);
            status.show();
            
            }
         };
         Random rand = new Random();
         
        try {
             request.setUrl("http://localhost/PiMobile/insert.php");
             request.addArgument("nomC",ch.getNomC());
             request.addArgument("type",ch.getType());
             request.addArgument("console",ch.getConsole());
             request.addArgument("url_chaine",ch.getUrl_chaine());
             request.addArgument("sign",ch.getSign());
             request.addArgument("url_pdp",ch.getUrl_pdp());
             if(!ch.getUrl_pdp().equals(""))
                 
             request.addData("image",ch.getUrl_pdp(),"text/plain");
             request.addArgument("id", "ImageProfil-"+String.valueOf(rand.nextInt(1000)+1));
            // request.setFilename("fileUpload", "myPicture.jpg");
          
         } catch (IOException ex) {
             System.out.println(ex.getMessage());
         }
        NetworkManager.getInstance().addToQueue(request);
     
        
        
    }
    private Image createIcon(char charcode) {
        int iconWidth = Display.getInstance().convertToPixels(8, true);
        Style iconStyle = new Style();
        Font iconFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        if (Font.isNativeFontSchemeSupported()) {
            iconFont = Font.createTrueTypeFont("native:MainBold", null).derive((int)(iconWidth * 0.5), Font.STYLE_BOLD);
        }
        iconStyle.setFont(iconFont);
        iconStyle.setFgColor(0xffffff);
        iconStyle.setBgTransparency(0);

        FontImage completeIcon = FontImage.createMaterial(charcode, iconStyle);
        return completeIcon;
    }
    
}
