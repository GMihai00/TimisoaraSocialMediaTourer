package com.tourer.gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class SettingsMenu extends JFrame{
    static BorderLayout layout = new BorderLayout();
    public SettingsMenu() {
        
        this.setSize(new Dimension((MainFrame.screenSize.width * 5) / 6 , (MainFrame.screenSize.height * 5) / 6));
        this.setLayout(layout);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(false);

    }

    public void update(){
        this.pack();
        this.revalidate();
        this.repaint();
        this.setSize(new Dimension((MainFrame.screenSize.width * 5) / 6 , (MainFrame.screenSize.height * 5) / 6));
        this.setLocationRelativeTo(null);

    }

}
