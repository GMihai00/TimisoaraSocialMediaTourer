package com.tourer.gui;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UsserButton extends CostumButton{
    public final static String iconPath = "Icons\\UserIcon.png";
    public final static String darkIconPath = "Icons\\UserIconDark.png";

    static UserSettingsMenu userSettingsMenu;

    public  UsserButton(int w, int h, UserSettingsMenu userSettingsMenu) {
        super(w, h, UsserButton.iconPath);
        UsserButton.userSettingsMenu = userSettingsMenu;
        this.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsButton.appMenuSettings.setVisible(false);
                UsserButton.userSettingsMenu.setVisible(true);
            }
            
        });
    }
}
