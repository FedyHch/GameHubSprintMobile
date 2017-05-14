/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;

/**
 *
 * @author Foued
 */
public class DeleteUser {
    public void delete(Membre m,String s){
                ConnectionRequest req = new ConnectionRequest();

        req.setUrl("http://localhost/GameHubMobile/delete.php?username=" + s + "");
        req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                 byte[] data = (byte[]) evt.getMetaData();
                String s = new String(data);

                if (s.equals("success")) {
                    Dialog.show("Suppression", "suppression ok", "Ok", null);
                }
            }
        });

        NetworkManager.getInstance().addToQueue(req);
    }
}
