package com.tourer.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;

import javafx.scene.paint.Color;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Image;

public class UserSettingsMenu extends SettingsMenu{
    
    public final static int USERICON_WIDTH = 200;
    public final static int USERICON_HEIGHT = 200;
    public final static String profileImagePath = "Icons\\DefaultUserProfileImage.jpg";
    public static JLabel userIcon = new JLabel();
    static{
        userIcon.setSize(USERICON_WIDTH,  USERICON_HEIGHT);
    }
    public UserSettingsMenu(){

        ColorPanel contentPanel = new ColorPanel();
        contentPanel.setPreferredSize(this.getSize());
        contentPanel.setSize(this.getSize());
        
        Image profileImage = Toolkit.getDefaultToolkit().getImage(profileImagePath);
        Dimension backgroundDimension = new Dimension((int) this.getSize().getWidth(),(int) this.getSize().getHeight() / 3);
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(profileImagePath));
            profileImage  = bufferedImage.getScaledInstance((int)backgroundDimension.getWidth(),(int) backgroundDimension.getHeight(), Image.SCALE_SMOOTH);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        BackGroundPanel backGroundPanel = new BackGroundPanel(profileImage);
        
        backGroundPanel.setPreferredSize(backgroundDimension);
        backGroundPanel.setSize(backgroundDimension);
        
        String iconPath = UsserButton.iconPath;
        ImageIcon icon = new ImageIcon(new ImageIcon(iconPath).getImage().getScaledInstance(USERICON_WIDTH, USERICON_HEIGHT, Image.SCALE_SMOOTH));
        userIcon.setIcon(icon);

        backGroundPanel.add(userIcon);
        
        contentPanel.add(backGroundPanel);
        this.add(contentPanel, BorderLayout.CENTER);
        this.update();
    }
}
