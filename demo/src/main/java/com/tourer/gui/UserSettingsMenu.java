package com.tourer.gui;

import java.awt.BorderLayout;

import java.awt.Container;
public class UserSettingsMenu extends SettingsMenu{
    
    public UserSettingsMenu(){

        ColorPanel colorPanel = new ColorPanel();

        Container contentPane = this.getContentPane();
     
        this.add(colorPanel,  BorderLayout.CENTER);
    }
}
