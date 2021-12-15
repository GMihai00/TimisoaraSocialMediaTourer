package com.tourer.gui;

import java.io.File;
import java.io.IOException;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class AppSettingsMenu extends SettingsMenu{
    
    public final static int buttonHeight = 40;
    public final static int buttonWidth  = 40;
    final static int w = 40;
    final static int h = 40;
    public final static int textSize = 25;
    public final static String fontStyle = Font.DIALOG;
    public final static int fontType =  Font.PLAIN;
    final static LineBorder border = new LineBorder(Color.orange,1, true);
    final static Color PURPLE_COLOR = new Color(101, 24, 115);
    final static String[] languages = new String[]{"English", "Romana"}; 
    final static GradientColor TRANSP_GRADIENT_COLOR = new GradientColor(new Color(213, 134, 145, 123), new Color(213, 134, 145, 123));
    public AppSettingsMenu() {
        
        Dimension windowPanelSize = new Dimension(MainFrame.screenSize.width * 4 / 5, MainFrame.screenSize.height);
        String windowPanelbackgroundPath = "Icons\\AccountCreationBackground.png";
        Image windowPanelbackground = Toolkit.getDefaultToolkit().getImage(windowPanelbackgroundPath);
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(windowPanelbackgroundPath));
            windowPanelbackground  = bufferedImage.getScaledInstance(MainFrame.screenSize.width * 4 / 5, MainFrame.screenSize.height + 10, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        BackGroundPanel windowPanel = new BackGroundPanel(windowPanelbackground);
        //ColorPanel windowPanel = new ColorPanel();
        windowPanel.setPreferredSize(windowPanelSize);
        windowPanel.setLayout(new BoxLayout(windowPanel, BoxLayout.PAGE_AXIS));
        
        windowPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        Dimension fieldsGridPanelSize = new Dimension(300, MainFrame.screenSize.height);
        String fieldsGridPanelBackgroundPath = "Icons\\SettingsSideMenuBackground.jpg";
        Image fieldsGridPanelbackground = Toolkit.getDefaultToolkit().getImage(fieldsGridPanelBackgroundPath);
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(fieldsGridPanelBackgroundPath));
            fieldsGridPanelbackground  = bufferedImage.getScaledInstance(300, MainFrame.screenSize.height, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        BackGroundPanel fieldsGridPanel = new BackGroundPanel(fieldsGridPanelbackground);
        fieldsGridPanel.setSize(fieldsGridPanelSize);
        fieldsGridPanel.setPreferredSize(new Dimension(MainFrame.screenSize.width / 5 - 100, MainFrame.screenSize.height));
        
        ButtonSettings gridPanelWindow = new ButtonSettings(Color.red, Color.yellow, "Window");
        gridPanelWindow.setAlignmentX(Component.RIGHT_ALIGNMENT);
        gridPanelWindow.setPreferredSize(new Dimension((int) fieldsGridPanel.getPreferredSize().getWidth(), buttonHeight));
        gridPanelWindow.setBorder(border);
        fieldsGridPanel.add(gridPanelWindow);
        
        
       
        
        ButtonSettings gridPanelSecurity = new ButtonSettings(Color.red, Color.yellow, "Security");
        gridPanelSecurity.setAlignmentX(Component.RIGHT_ALIGNMENT);
        gridPanelSecurity.setPreferredSize(new Dimension((int) fieldsGridPanel.getPreferredSize().getWidth(), buttonHeight));
        gridPanelSecurity.setBorder(border);
        fieldsGridPanel.add(gridPanelSecurity);

        
        this.add(fieldsGridPanel, BorderLayout.WEST);
        
        GridPanel checkPanel = new GridPanel(TRANSP_GRADIENT_COLOR);
        checkPanel.setBorder(BorderFactory.createTitledBorder(border, "Settings", TitledBorder.LEADING, TitledBorder.BELOW_TOP, new Font(fontStyle, fontType, textSize), PURPLE_COLOR));
        checkPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        CheckBox darkMode = new CheckBox("Dark Mode");
        darkMode.setForeground(PURPLE_COLOR);
        checkPanel.addLeft(darkMode);
        checkPanel.addRight(Box.createHorizontalStrut(20));
        CheckBox notifications = new CheckBox("Notifications");
        notifications.setForeground(PURPLE_COLOR);
        notifications.setSelected(true);
        checkPanel.addLeft(notifications);
        checkPanel.addRight(Box.createHorizontalStrut(20));
        checkPanel.setOpaque(false);
        windowPanel.add(checkPanel);
        
        GridPanel gridPanel = new GridPanel(TRANSP_GRADIENT_COLOR);
        
        gridPanel.setBorder(BorderFactory.createTitledBorder(border, "Resizing", TitledBorder.LEADING, TitledBorder.BELOW_TOP, new Font(fontStyle, fontType, textSize), PURPLE_COLOR));
        gridPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel textResizerLabel = new JLabel("Text Size");
        textResizerLabel.setForeground(PURPLE_COLOR);
        textResizerLabel.setFont(new Font(fontStyle, fontType, textSize));
        gridPanel.addLeft(textResizerLabel);
        gridPanel.addRight(Box.createVerticalStrut(20));
        TextResizer textResizer = new TextResizer();
        gridPanel.addLeft(Box.createVerticalStrut(20));
        gridPanel.addRight(textResizer);

        gridPanel.addSpacer(Box.createVerticalStrut(20));

        JLabel iconResizerLabel = new JLabel("Icon Size");
        iconResizerLabel.setForeground(PURPLE_COLOR);
        iconResizerLabel.setFont(new Font(fontStyle, fontType, textSize));
        gridPanel.addLeft(iconResizerLabel);
        gridPanel.addRight(Box.createVerticalStrut(20));
        IconResizer iconResizer = new IconResizer();
        gridPanel.addLeft(Box.createVerticalStrut(20));
        gridPanel.addRight(iconResizer);
        gridPanel.setOpaque(false);
        windowPanel.add(gridPanel);
        GridPanel languagePanel = new GridPanel(TRANSP_GRADIENT_COLOR);
        languagePanel.setBorder(BorderFactory.createTitledBorder(border, "Language", TitledBorder.LEADING, TitledBorder.BELOW_TOP, new Font(fontStyle, fontType, textSize), PURPLE_COLOR));
        languagePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        LanguagePicker languageField = new LanguagePicker();
        
        languageField.setVisible(true);
        languageField.search.setBackground(PURPLE_COLOR);
        languageField.search.setForeground(Color.orange);
        
        
        for(int i = 0; i < languages.length; i++)
            languageField.addItem(languages[i]);
        languageField.setSelectedItem("English");
        languagePanel.addLeft(languageField);
        languagePanel.addRight(Box.createVerticalStrut(20));
        languagePanel.setOpaque(false);
        windowPanel.add(languagePanel);
        

        ColorPanel securityPanel = new ColorPanel();
        securityPanel.setPreferredSize(new Dimension(MainFrame.screenSize.width * 4 / 5, MainFrame.screenSize.height));
        securityPanel.setLayout(new BoxLayout(securityPanel, BoxLayout.PAGE_AXIS));
        securityPanel.setBorder(BorderFactory.createTitledBorder(border, "Settings", TitledBorder.LEADING, TitledBorder.BELOW_TOP, new Font(fontStyle, fontType, textSize), PURPLE_COLOR));
        securityPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        ButtonSettings cardUpdateButton = new ButtonSettings(Color.red, Color.yellow, "Update card data", 1);
        securityPanel.add(cardUpdateButton);

        ButtonSettings.cardPanel.add(windowPanel, "Window");
        ButtonSettings.cardPanel.add(securityPanel, "Security");
        ButtonSettings.cardLayout.show(ButtonSettings.cardPanel, "Window");



        this.add(ButtonSettings.cardPanel, BorderLayout.CENTER);
        this.update();
    }
    

   
}
