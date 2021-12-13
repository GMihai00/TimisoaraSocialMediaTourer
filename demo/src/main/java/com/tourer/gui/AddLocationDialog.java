package com.tourer.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Dialog.ModalityType;
import java.awt.GridBagConstraints;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javafx.scene.control.ScrollPane;

public class AddLocationDialog extends JDialog{
    public static JLabel longitude = new JLabel("Longitude: ");
    public static JLabel latitude = new JLabel("Latitude: ");
    public static JLabel locationNameLabel = new JLabel("Location name: ");
    public static JTextField locationNameTextField = new JTextField("");
    public static JLabel locationDescriptionLabel = new JLabel("Description: ");
    public static JTextArea locationDescritpionTextField = new JTextArea("");
    static{
        Dimension size =new Dimension((int) MainFrame.screenSize.getWidth() / 6, (int) MainFrame.screenSize.getHeight() / 7);
        locationDescritpionTextField.setPreferredSize(size);
        locationDescritpionTextField.setMinimumSize(size);
        locationDescriptionLabel.setMaximumSize(size);
    }
    static{
        longitude.setFont(new Font(AppSettingsMenu.fontStyle, AppSettingsMenu.fontType, AppSettingsMenu.textSize));
        latitude.setFont(new Font(AppSettingsMenu.fontStyle, AppSettingsMenu.fontType, AppSettingsMenu.textSize));
        latitude.setFont(new Font(AppSettingsMenu.fontStyle, AppSettingsMenu.fontType, AppSettingsMenu.textSize));
        locationNameLabel.setFont(new Font(AppSettingsMenu.fontStyle, AppSettingsMenu.fontType, AppSettingsMenu.textSize));
        locationDescriptionLabel.setFont(new Font(AppSettingsMenu.fontStyle, AppSettingsMenu.fontType, AppSettingsMenu.textSize));
    }
    public AddLocationDialog(Frame frame){
        super(frame,"Add new location", ModalityType.APPLICATION_MODAL);
        
        this.setSize(new Dimension((int) MainFrame.screenSize.getWidth() / 3, (int) MainFrame.screenSize.getHeight() / 3));

        GridPanel contentPane = new GridPanel();
        contentPane.setSize(new Dimension((int) MainFrame.screenSize.getWidth() / 3, (int) MainFrame.screenSize.getHeight() / 3));
        contentPane.addLeft(longitude);
        contentPane.addRight(latitude);
        
        contentPane.addLeft(locationNameLabel);
        contentPane.addRight(locationNameTextField);
        
        contentPane.addLeft(locationDescriptionLabel);
        JScrollPane scrollPane = new JScrollPane(locationDescritpionTextField);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
        
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        contentPane.add(scrollPane, c);
        contentPane.addSpacer(Box.createHorizontalStrut(1));
        contentPane.addLeft(new JButton("Add"));
        this.setContentPane(contentPane);


        this.setVisible(false);
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
}
