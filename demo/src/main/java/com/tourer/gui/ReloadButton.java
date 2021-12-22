package com.tourer.gui;

import java.awt.event.ActionListener;

import com.tourer.App;

import javafx.application.Platform;

import java.awt.event.ActionEvent;

public class ReloadButton extends CostumButton{

    public final static String iconPath = "Icons\\Reload.png";
    public final static String darkIconPath = "Icons\\ReloadDark.png";

    
    
    public ReloadButton(int w, int h) {
        super(w, h, ReloadButton.iconPath);
        
        this.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        App.engine.reload();
                    }
                    
                });
                
                
            }

        });
    }
    

}

