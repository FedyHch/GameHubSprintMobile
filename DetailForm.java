
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
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
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
public class DetailForm {
    
    Form f;
    Command back,profile,logout;
    Label labelRate;
    
    
    Container c1;
    public DetailForm(Resources theme, Jeux j){
        
    UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);
        
    UIBuilder ui = new UIBuilder();
    
    
    f= ui.createContainer(theme, "Detail").getComponentForm();
    
    
        BrowserComponent br = new BrowserComponent();
        
    Label labelNom =(Label) ui.findByName("labelNom", f);
    ImageViewer imgv = (ImageViewer)  ui.findByName("ImageViewer", f);
    Label labelGenre =(Label) ui.findByName("labelGenre", f);
    Label labelNote =(Label) ui.findByName("labelNote", f);
    Label labelDate =(Label) ui.findByName("labelDate", f);
    Label labelClassification =(Label) ui.findByName("labelClassification", f);
    Label labelMode =(Label) ui.findByName("labelMode", f);
    Label labelPrix =(Label) ui.findByName("labelPrix", f);
    Label labelDescription =(Label) ui.findByName("labelDescription", f);
    Button btRate = (Button)  ui.findByName("ButtonRate", f);
    labelRate = (Label) ui.findByName("labelRate", f);
    Slider starSlider = createStarRankSlider();
    c1 = (Container) ui.findByName("Container8", f);
    
    Button btTrailer = new Button("Trailer");
    
    btTrailer.addActionListener((ActionListener) (ActionEvent evt) -> {
        
        Display.getInstance().execute(j.getTrailer());
        
    });
    
    
    labelNom.setText(j.getNom());
    EncodedImage encimg = EncodedImage.createFromImage(theme.getImage("round.png"), false);
        
         URLImage urlimg = URLImage.createToStorage(encimg, "FULL_" + "http://localhost/GameHub/uploads/affiches/"+j.getAffiche(), "http://localhost/GameHub/uploads/affiches/"+j.getAffiche());
         urlimg.fetch(); 
         imgv.setImage(urlimg);
    labelGenre.setText(j.getGenre());
    labelNote.setText(String.valueOf(j.getNote())+" /20");
    labelDate.setText(j.getDate_sortie());
    labelClassification.setText(j.getClassification());
    labelMode.setText(j.getMode());
    labelPrix.setText(String.valueOf(j.getPrix())+" $");
    labelDescription.setText(j.getDescription());
    br.setURL(j.getTrailer());
    
    
    ConnectionRequest req = new ConnectionRequest();
    
    
    
                req.setUrl("http://localhost/GameHubMobile/selectRating.php?id_jeux="+j.getId_jeux());
                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        
                        if (s.trim().equals("nothing")) {
                         labelRate.setText("aucune note de lecteurs");
                        }else{
                            if(s.length()>4){
                                s=s.substring(0,4);
                            
                        }
                        labelRate.setText(s+" /20");
                        
                        starSlider.setProgress((Float.valueOf(s)).intValue());
                            System.out.println((Float.valueOf(s)).intValue());
                            
                        }
                        
                    }
                });
    
         
                
    back = new Command("Back",theme.getImage("back-command.png"));
    profile = new Command("Profile"); 
    logout = new Command("Logout");
    
    f.getToolbar().setTitle("Details");
    f.getToolbar().setTitleCentered(true);
    f.getToolbar().addCommandToOverflowMenu(profile);
    f.getToolbar().addCommandToOverflowMenu(logout);
    f.getToolbar().addCommandToLeftBar(back);
    starSlider.setEditable(false);
    c1.add(FlowLayout.encloseCenter(starSlider));
    c1.add(btTrailer);
    
    c1.add(br);
    
//    f.add(FlowLayout.encloseCenter(starSlider));
    
    
    
    f.addCommandListener((ActionListener) (ActionEvent evt) ->
    {   
        if(evt.getCommand()==back){
        ListJeuxForm ljf = new ListJeuxForm(theme);
        ljf.getF().show();
        }
        
    });
    
    btRate.addActionListener((ActionListener) (ActionEvent evt) -> {
        RateForm rf = new RateForm(theme, j);
        rf.getF().show();
                });
    
    NetworkManager.getInstance().addToQueue(req);
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
