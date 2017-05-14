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
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;

/**
 *
 * @author lenovo
 */
public class AjoutReclamationForm {
    private Form f;
    private Container ct;
    private TextField pseudo,desc,url;
    private Button bt1;
    private Command cm1,cm2;
    
    public AjoutReclamationForm(Resources theme,int id){
        UIBuilder uib = new UIBuilder();
        ct=uib.createContainer(theme, "GUI 4");
        f=(Form) ct;
        f.setTitle("Ajouter reclamation");
        f.getUnselectedStyle().setBgColor(ColorUtil.GRAY);
        pseudo = (TextField) uib.findByName("TextField", ct);
        desc = (TextField) uib.findByName("TextField1", ct);
        bt1 = (Button) uib.findByName("Button", ct);
        
        
        cm1 = new Command("retour");
        cm2 = new Command("logout");
        f.getToolbar().addCommandToSideMenu(cm1);
        f.getToolbar().addCommandToOverflowMenu(cm2);
        f.addCommandListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent evt) {
            if (evt.getCommand()==cm2) {
                ListUtilisateurForm luf = new ListUtilisateurForm(theme);
                luf.GetF().show();
                
            }else if (evt.getCommand()==cm1) {
                ListReclamationForm lrf = new ListReclamationForm(theme, id);
                lrf.GetF().show();
                
            }
        }
    });
        bt1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {                
                String spseudo = pseudo.getText();
                String sdesc=desc.getText();
                if (spseudo == null || spseudo.equals("")) {
                    Dialog.show("erreur", "saisie un pseudo","ok",null);
                    
                }else if (sdesc == null|| sdesc.equals("")) {
                    Dialog.show("erreur", "saisie une description","ok",null);
                    
                }else{
                    ConnectionRequest req = new ConnectionRequest();
                    req.setUrl("http://localhost/GameHubMobile/insert.php?x="+id+"&y="+spseudo+"&z="+sdesc);
                    req.addResponseListener(new ActionListener<NetworkEvent>() {
                        
                        @Override
                        public void actionPerformed(NetworkEvent evt) {
                            byte[] data = (byte[]) evt.getMetaData();
                            String s = new String(data);
                            if (s.equals("success")) {
                                Dialog.show("Confirmation", "ajout ok", "Ok", null);
                            }else{
                                Dialog.show("erreur", "pseudo invalide ou deja reclamer","ok",null);
                            }
                        }
                    });
                
                    NetworkManager.getInstance().addToQueue(req);
                
                  Message m = new Message("<html><body>nouvelle reclamation de la part de l'utilisateur de l'id = "+id+"</body></html>");
                  m.setMimeType(Message.MIME_HTML);
                  
//                  ***************************
//                  m.sendMessageViaCloud("badr.taamallah@gmail.com", "badreddinne.taamallah@esprit.tn", "Badr", "new reclamation",
//                              "Check out Codename One at https://www.codenameone.com/");
//                  ******************************** 
//                  String[] rese = new String[5];
//                  rese[0]="badidou49@live.fr";
//                  rese[1]="badr.taamallah@gmail.com";
//                  rese[2]="badreddinne.taamallah@esprit.tn";
//                  Message.sendMessage(rese, "ne reclamation", m);
                }
            }
        });
    }
   
    public Form GetF(){
        return f;
    }
}