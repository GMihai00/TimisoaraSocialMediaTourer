package com.tourer.gui;

import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.border.LineBorder;


public class MainFrame extends JFrame {
    public final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static GradientColor gradientColor;
    final static LineBorder nullBorder = new LineBorder(Color.black, 0, false);
    public MainFrame(GradientColor gradientColor) {
        MainFrame.gradientColor = gradientColor;
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(MainFrame.screenSize.width / 2, MainFrame.screenSize.height / 2));
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        

    }

    public void update(){
        this.pack();
        this.revalidate();
        this.repaint();
      
        this.setSize(new Dimension(MainFrame.screenSize.width / 2, MainFrame.screenSize.height / 2));
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        this.setVisible(true);
    }

   



}
