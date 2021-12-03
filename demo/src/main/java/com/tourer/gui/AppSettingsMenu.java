package com.tourer.gui;


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
    public AppSettingsMenu() {
        
       

        ColorPanel windowPanel = new ColorPanel();
        windowPanel.setPreferredSize(new Dimension(MainFrame.screenSize.width * 4 / 5, MainFrame.screenSize.height));
        windowPanel.setLayout(new BoxLayout(windowPanel, BoxLayout.PAGE_AXIS));
        windowPanel.setBorder(BorderFactory.createTitledBorder(border, "Settings", TitledBorder.LEADING, TitledBorder.BELOW_TOP, new Font(fontStyle, fontType, textSize), PURPLE_COLOR));
        windowPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        
        ColorPanel fieldsGridPanel = new ColorPanel();
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
        
        CheckBox darkMode = new CheckBox("Dark Mode");
        darkMode.setForeground(PURPLE_COLOR);
        windowPanel.add(darkMode);
        CheckBox notifications = new CheckBox("Notifications");
        notifications.setForeground(PURPLE_COLOR);
        notifications.setSelected(true);
        windowPanel.add(notifications);

        GridPanel gridPanel = new GridPanel();
        
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
        windowPanel.add(gridPanel);
        GridPanel languagePanel = new GridPanel();
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
