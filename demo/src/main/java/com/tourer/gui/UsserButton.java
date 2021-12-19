package com.tourer.gui;

import java.awt.event.ActionListener;
import java.sql.SQLException;
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
                try {
                    UsserButton.userSettingsMenu.updateVisited();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                UsserButton.userSettingsMenu.setVisible(true);
                
            }
            
        });
    }
}
