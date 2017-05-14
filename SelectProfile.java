  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Fakher_XoX
 */
public class SelectProfile {
    
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


    
    private Form f,f2;
    private Container ct1;
    private ComboBox Combobox ;
    
    public SelectProfile(Resources theme){
        UIBuilder uib = new UIBuilder();
        ct1 = uib.createContainer(theme, "GUI 3"); 
        f= (Form) ct1;
        Combobox = (ComboBox) uib.findByName("ComboBox", ct1);
        
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIMobile/Listchaines.php");
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                for(Channel ch: getIdChaine(new String(con.getResponseData())) ){
                    Combobox.addItem(ch.getId_chaine());
                }
               
                f.refreshTheme();

            }
        });
        NetworkManager.getInstance().addToQueue(con);
       
        Combobox.addActionListener((ActionListener) (ActionEvent evt)->{
            int id = (Integer)Combobox.getSelectedItem();
            
            
                   ViewProfile vp = new ViewProfile(theme, id);
                   vp.GetF().show();
            
            
        });

    }
    
    public ArrayList<Channel> getIdChaine(String json) {
        ArrayList<Channel> idchaines = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();

            Map<String, Object> chaines = j.parseJSON(new CharArrayReader(json.toCharArray()));

       
            List<Map<String, Object>> list = (List<Map<String, Object>>) chaines.get("chaine");

            for (Map<String, Object> obj : list) {
                Channel ch = new Channel();
                ch.setId_chaine(Integer.parseInt(obj.get("id_chaine").toString()));
             
                idchaines.add(ch);

            }

        } catch (IOException ex) {
         }
        return idchaines;

    } 
     public Form GetF(){
        return f;
    }
    
   
}


