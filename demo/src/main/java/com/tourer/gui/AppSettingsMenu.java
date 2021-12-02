package com.tourer.gui;



import java.awt.Font;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class AppSettingsMenu extends SettingsMenu{
    
    public final static int buttonHeight = 40;
    public final static int buttonWidth  = 40;
    final static int w = 40;
    final static int h = 40;
    final static int textSize = 25;
    final static String fontStyle = Font.DIALOG;
    final static int fontType =  Font.PLAIN;
    final static LineBorder border = new LineBorder(Color.orange,1, true);
    final static Color PURPLE_COLOR = new Color(101, 24, 115);
    final static String[] languages = new String[]{"English", "Romana"}; 
    public AppSettingsMenu() {
        
        // GradientColor gradientColor = new GradientColor(Color.MAGENTA, Color.red);
        ColorPanel colorPanel = new ColorPanel();
        colorPanel.setPreferredSize(new Dimension(MainFrame.screenSize.width * 4 / 5, MainFrame.screenSize.height));
        colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.PAGE_AXIS));
        colorPanel.setBorder(BorderFactory.createTitledBorder(border, "Settings", TitledBorder.LEADING, TitledBorder.BELOW_TOP, new Font(fontStyle, fontType, textSize), PURPLE_COLOR));
        colorPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        
        ColorPanel fieldsGridPanel = new ColorPanel();
        fieldsGridPanel.setPreferredSize(new Dimension(MainFrame.screenSize.width / 5 - 100, MainFrame.screenSize.height));
        
        ButtonSettings gridPanelWindow = new ButtonSettings(Color.red, Color.yellow);
        gridPanelWindow.setAlignmentX(Component.RIGHT_ALIGNMENT);
        gridPanelWindow.setPreferredSize(new Dimension((int) fieldsGridPanel.getPreferredSize().getWidth(), buttonHeight));
        gridPanelWindow.setBorder(border);
        JLabel labeWindow = new JLabel("Window");
        labeWindow.setFont(new Font(fontStyle, fontType, textSize));
        gridPanelWindow.addLeft(labeWindow);
        fieldsGridPanel.add(gridPanelWindow);
        
        
       
        
        ButtonSettings gridPanelSecurity = new ButtonSettings(Color.red, Color.yellow);
        gridPanelSecurity.setAlignmentX(Component.RIGHT_ALIGNMENT);
        gridPanelSecurity.setPreferredSize(new Dimension((int) fieldsGridPanel.getPreferredSize().getWidth(), buttonHeight));
        gridPanelSecurity.setBorder(border);
        JLabel labelSecurity = new JLabel("Security");
        labelSecurity.setFont(new Font(fontStyle, fontType, textSize));
        gridPanelSecurity.addLeft(labelSecurity);
        fieldsGridPanel.add(gridPanelSecurity);

        ButtonSettings gridPanelText = new ButtonSettings(Color.red, Color.yellow);
        gridPanelText.setAlignmentX(Component.RIGHT_ALIGNMENT);
        gridPanelText.setPreferredSize(new Dimension((int) fieldsGridPanel.getPreferredSize().getWidth(), buttonHeight));
        gridPanelText.setBorder(border);
        JLabel labelText = new JLabel("Text");
        labelText.setFont(new Font(fontStyle, fontType, textSize));
        gridPanelText.addLeft(labelText);

        fieldsGridPanel.add(gridPanelText);
        this.add(fieldsGridPanel, BorderLayout.WEST);
        
        CheckBox darkMode = new CheckBox("Dark Mode");
        darkMode.setForeground(PURPLE_COLOR);
        colorPanel.add(darkMode);
        CheckBox notifications = new CheckBox("Notifications");
        notifications.setForeground(PURPLE_COLOR);
        notifications.setSelected(true);
        colorPanel.add(notifications);

        GridPanel gridPanel = new GridPanel();
        
        gridPanel.setBorder(BorderFactory.createTitledBorder(border, "Window", TitledBorder.LEADING, TitledBorder.BELOW_TOP, new Font(fontStyle, fontType, textSize), PURPLE_COLOR));
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
        colorPanel.add(gridPanel);
        GridPanel languagePanel = new GridPanel();
        languagePanel.setBorder(BorderFactory.createTitledBorder(border, "Language", TitledBorder.LEADING, TitledBorder.BELOW_TOP, new Font(fontStyle, fontType, textSize), PURPLE_COLOR));
        languagePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        SearchField languageField = new SearchField();
        languageField.setVisible(true);
        languageField.search.setBackground(PURPLE_COLOR);
        languageField.search.setForeground(Color.orange);
        
        
        for(int i = 0; i < languages.length; i++)
            languageField.addItem(languages[i]);
        languageField.setSelectedItem("English");
        languagePanel.addLeft(languageField);
        languagePanel.addRight(Box.createVerticalStrut(20));
        colorPanel.add(languagePanel);
        this.add(colorPanel, BorderLayout.CENTER);
        this.update();
    }
    
}
