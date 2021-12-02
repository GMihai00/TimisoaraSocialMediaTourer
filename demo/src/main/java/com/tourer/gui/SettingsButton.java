package com.tourer.gui;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SettingsButton extends CostumButton{

    public final static String iconPath = "Icons\\Settings.png";
    public final static String darkIconPath = "Icons\\SettingsDark.png";

    static AppSettingsMenu appMenuSettings;
    
    public SettingsButton(int w, int h, AppSettingsMenu appMenuSettings) {
        super(w, h, SettingsButton.iconPath);
        SettingsButton.appMenuSettings = appMenuSettings;
        this.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                UsserButton.userSettingsMenu.setVisible(false);
                SettingsButton.appMenuSettings.setVisible(true);
                
            }
            
        });
    }
    

}

