package com.tourer.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Dialog.ModalityType;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
        longitude.setForeground(Color.orange);
        longitude.setPreferredSize(new Dimension((int) MainFrame.screenSize.getWidth() / 3 - 80, 20));
        latitude.setForeground(Color.orange);
        latitude.setPreferredSize(new Dimension((int) MainFrame.screenSize.getWidth() / 3 - 80, 20));
        locationNameLabel.setForeground(Color.orange);
        
        locationDescriptionLabel.setForeground(Color.orange);
        locationDescriptionLabel.setPreferredSize(new Dimension((int) MainFrame.screenSize.getWidth() / 3 - 80, 20));
        locationNameTextField.setPreferredSize(new Dimension((int) MainFrame.screenSize.getWidth() / 6, 20));
        locationNameTextField.setBorder(AccountCreationFrame.BLACK_BORDER);
        locationDescritpionTextField.setBorder(AccountCreationFrame.BLACK_BORDER);
        locationDescritpionTextField.setPreferredSize(new Dimension((int) MainFrame.screenSize.getWidth() / 3 - 80, 70));

        longitude.setFont(new Font(AppSettingsMenu.fontStyle, AppSettingsMenu.fontType, AppSettingsMenu.textSize));
        latitude.setFont(new Font(AppSettingsMenu.fontStyle, AppSettingsMenu.fontType, AppSettingsMenu.textSize));
        latitude.setFont(new Font(AppSettingsMenu.fontStyle, AppSettingsMenu.fontType, AppSettingsMenu.textSize));
        locationNameLabel.setFont(new Font(AppSettingsMenu.fontStyle, AppSettingsMenu.fontType, AppSettingsMenu.textSize));
        locationDescriptionLabel.setFont(new Font(AppSettingsMenu.fontStyle, AppSettingsMenu.fontType, AppSettingsMenu.textSize));
    }
    public AddLocationDialog(Frame frame){
        super(frame,"Add new location", ModalityType.APPLICATION_MODAL);
        
        this.setSize(new Dimension((int) MainFrame.screenSize.getWidth() / 3, (int) MainFrame.screenSize.getHeight() / 3));

        ColorPanel contentPane = new ColorPanel();
        contentPane.setSize(new Dimension((int) MainFrame.screenSize.getWidth() / 3, (int) MainFrame.screenSize.getHeight() / 3));
        contentPane.add(longitude);
       
        contentPane.add(latitude);
        
        contentPane.add(locationNameLabel);
        contentPane.add(locationNameTextField);
        
        contentPane.add(locationDescriptionLabel);
        JScrollPane scrollPane = new JScrollPane(locationDescritpionTextField);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
        //scrollPane.setPreferredSize(new Dimension((int) MainFrame.screenSize.getWidth() / 6, (int) MainFrame.screenSize.getHeight() / 7));
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        contentPane.add(scrollPane, c);
        contentPane.add(Box.createHorizontalStrut(1));
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                
                boolean ok = true;
                if(AddLocationDialog.locationNameTextField.getText().equals("")){
                    AddLocationDialog.locationNameTextField.setBorder(AccountCreationFrame.RED_BORDER);
                    ok = false;
                }
                else
                {
                    AddLocationDialog.locationNameTextField.setBorder(AccountCreationFrame.BLACK_BORDER);
                }
                if(AddLocationDialog.locationDescritpionTextField.getText().equals("")){
                    AddLocationDialog.locationDescritpionTextField.setBorder(AccountCreationFrame.RED_BORDER);
                    ok = false;
                }
                else
                {
                    AddLocationDialog.locationDescritpionTextField.setBorder(AccountCreationFrame.BLACK_BORDER);
                }
                if(ok == true){
                    AddLocationDialog.this.setVisible(false);
                }
            }

        });
        contentPane.add(addButton);
        this.setContentPane(contentPane);


        this.setVisible(false);
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
}
