package com.tourer.gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import com.tourer.App;
import com.tourer.gui.map.Location;
import com.tourer.jdbc.Connector;

import java.awt.Window;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LocationDescriptionDialog extends JDialog{
    
    public final static Dimension DIALOG_SIZE = new Dimension((int) MainFrame.screenSize.getWidth() * 2 / 3, (int) MainFrame.screenSize.getHeight() * 2 / 3);
    public final static Dimension TEXTAREA_SIZE = new Dimension((int) MainFrame.screenSize.getWidth() * 2 / 3, ((int) MainFrame.screenSize.getHeight() * 2 / 3) * 2 / 3);
    public ColorPanel contentPane;
    public Location location;
    public JTextArea descriptionTextArea;
    public JScrollPane textScrollPane;
    public JButton updateLocation;
    public LocationDescriptionDialog(Window window){
        super(window);

        this.setSize(DIALOG_SIZE);
        this.setPreferredSize(DIALOG_SIZE);
        this.setResizable(false);
        this.setVisible(false);
        contentPane = new ColorPanel();
        this.setContentPane(contentPane);
        this.setLocationRelativeTo(null);
        JLabel description = new JLabel("Description");
        description.setForeground(Color.orange);
        description.setFont(new Font(AppSettingsMenu.fontStyle, AppSettingsMenu.fontType, AppSettingsMenu.textSize));
        
        this.add(description);
        descriptionTextArea = new JTextArea("");
        descriptionTextArea.setFont(new Font(Font.DIALOG, Font.PLAIN, 30));
        descriptionTextArea.setEditable(false);
        textScrollPane = new JScrollPane(descriptionTextArea);
        textScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        textScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        textScrollPane.getVerticalScrollBar().setUI(new MyScrollbarUI(App.gradientColor, Color.white));
        textScrollPane.getVerticalScrollBar().setUnitIncrement(20);
        textScrollPane.getHorizontalScrollBar().setUI(new MyScrollbarUI(App.gradientColor, Color.white));
        textScrollPane.getHorizontalScrollBar().setUnitIncrement(20);
        
        textScrollPane.setPreferredSize(TEXTAREA_SIZE);
        
        this.add(textScrollPane);

        JButton showOnMapButton = new JButton("Show on Map");
       
        this.add(showOnMapButton);
        updateLocation = new JButton("Update location");
        updateLocation.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if(Connector.modifyLocation( location.getLatitude(), location.getlongitude(), location.getName(), descriptionTextArea.getText()) == false){
                    JOptionPane.showMessageDialog(UsserButton.userSettingsMenu, "Failed to update location", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                
            }

            
        });
        

        updateLocation.setVisible(false);
        this.add(updateLocation);
        
    }

    public void updateLocation(Location location){
        this.location = location;
        this.descriptionTextArea.setText(location.getDescription());
        this.setTitle(location.getName());

    }
}
