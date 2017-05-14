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
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;

/**
 *
 * @author Laser
 */
public class RateForm {
    Form f;
    Slider slRate;
    Label labelNom;
    Label labelRate;
    Button btRate;
    Command back,profile,logout;
    public RateForm(Resources theme, Jeux j){
        
        
        UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);
        UIBuilder ui = new UIBuilder();
        f= ui.createContainer(theme, "Rate").getComponentForm();
        f.getToolbar().setTitle("Notez!");
        f.getToolbar().setTitleCentered(true);
//        slRate = (Slider) ui.findByName("SliderRate", f);
        slRate = createStarRankSlider();
        labelNom =(Label) ui.findByName("LabelNom", f);
        labelRate =(Label) ui.findByName("LabelRate", f);
        btRate = new Button("Notez !");
        f.add(FlowLayout.encloseCenter(slRate));
        f.add(btRate);
        ImageViewer imgv = (ImageViewer)  ui.findByName("ImageViewer", f);
        
        
        EncodedImage encimg = EncodedImage.createFromImage(theme.getImage("round.png"), false);
        
         URLImage urlimg = URLImage.createToStorage(encimg, "FULL_" + "http://localhost/GameHub/uploads/affiches/"+j.getAffiche(), "http://localhost/GameHub/uploads/affiches/"+j.getAffiche());
         urlimg.fetch(); 
         imgv.setImage(urlimg);
         labelNom.setText(j.getNom());
        
            
            
            slRate.addActionListener((ActionListener) (ActionEvent evt1) -> {
               labelRate.setText(String.valueOf(slRate.getProgress())+" /20");
               
            });
            back = new Command("Back",theme.getImage("back-command.png"));
            profile = new Command("Profile"); 
            logout = new Command("Logout");
    f.getToolbar().addCommandToLeftBar(back);
    
    f.getToolbar().addCommandToOverflowMenu(profile);
    f.getToolbar().addCommandToOverflowMenu(logout);
    f.addCommandListener((ActionListener) (ActionEvent evt) ->
    {   if(evt.getCommand()==back){
        DetailForm df = new DetailForm(theme, j);
        df.getF().show();
    }
        
        
        
    });
            btRate.addActionListener((ActionListener) (ActionEvent evnt)->{
                //Dialog.show("Confirmation", "Vous avez attribué la note "+slRate.getProgress()+" au jeu "+labelNom.getText(),"ok",null);
                ConnectionRequest req = new ConnectionRequest();
                req.setUrl("http://localhost/GameHubMobile/insertRating.php?id_membre="+3+"&id_jeux="+j.getId_jeux()+"&rate="+slRate.getProgress());
                req.addResponseListener((NetworkEvent evt) -> {
                    byte[] data = (byte[]) evt.getMetaData();
                    String s = new String(data);
                    if (s.equals("success")) {
                        if(Dialog.show("Merci", "Vous avez attribué la note "+slRate.getProgress()+" au jeu "+labelNom.getText(),"ok",null)){
                            DetailForm df = new DetailForm(theme, j);
                            df.getF().show();
                        }

                        
                    }
                });
               
                NetworkManager.getInstance().addToQueue(req);
        });
            
        
        
        
    }
    private Slider createStarRankSlider() {
    Slider starRank = new Slider();
    starRank.setEditable(true);
    starRank.setMinValue(0);
    starRank.setMaxValue(20);
    Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
            derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
    Style s = new Style(0xffff33, 0, fnt, (byte)0);
    Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    s.setOpacity(100);
    s.setFgColor(0);
    Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
    initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
    starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
    return starRank;
}
    private void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star);
    s.setBgTransparency(0);
}
    
    public Form getF(){
        return f;
    }
    
    
}
