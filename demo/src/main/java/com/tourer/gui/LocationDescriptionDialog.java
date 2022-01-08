package com.tourer.gui;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.tourer.App;
import com.tourer.gui.map.Location;
import com.tourer.jdbc.Connector;

import javafx.application.Platform;


import java.awt.Window;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class LocationDescriptionDialog extends JDialog{
    
    public final static Dimension DIALOG_SIZE = new Dimension((int) MainFrame.screenSize.getWidth() * 2 / 3, (int) MainFrame.screenSize.getHeight() * 2 / 3);
    public final static Dimension TEXTAREA_SIZE = new Dimension((int) MainFrame.screenSize.getWidth() * 2 / 3, ((int) MainFrame.screenSize.getHeight() * 2 / 3) * 2 / 3);
    public ColorPanel contentPane;
    public Location location;
    public JTextArea descriptionTextArea;
    public JScrollPane textScrollPane;
    public JButton updateLocation;
    public JButton deleteLocation;
    public CostumButton likeButton;
    public static String username;
    public CostumButton dislikeButton;
    public static final String likePath = "Icons\\Like.png";
    public static final String dislikePath = "Icons\\DisLike.png";
    public static final String likeSelectedPath = "Icons\\LikeSelected.png";
    public static final String dislikeSelectedPath = "Icons\\DisLikeSelected.png";
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
        showOnMapButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        Double lat = location.getLatitude();
                        Double lng = location.getLongitude();
                        App.engine.executeScript("setTargetMarker(" + lat + ", " + lng + ");");
                        App.mainFrame.requestFocus();
                    }
                });
               

            }
        });
        //setTargetMarker
        this.add(showOnMapButton);
        updateLocation = new JButton("Update location");
        updateLocation.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                JTextField nameTextField = new JTextField();
                nameTextField.setText(location.getName());
                Object[] message = {
                    "New name:", nameTextField
                };
                int result = JOptionPane.showConfirmDialog(LocationDescriptionDialog.this, message, "Save changes", JOptionPane.OK_CANCEL_OPTION);

                if(result == JOptionPane.OK_OPTION){
                    if(Connector.modifyLocation( location.getLatitude(), location.getLongitude(), nameTextField.getText(), descriptionTextArea.getText()) == false){
                        JOptionPane.showMessageDialog(UsserButton.userSettingsMenu, "Failed to update location", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        UsserButton.userSettingsMenu.setVisible(false);
                        try {
                            UsserButton.userSettingsMenu.updateVisited();
                        } catch (SQLException e2) {
                            // TODO Auto-generated catch block
                            e2.printStackTrace();
                        }
                        UsserButton.userSettingsMenu.setVisible(true);
                    }
                }
                
            }

            
        });
        

        updateLocation.setVisible(false);
        

        this.add(updateLocation);

        deleteLocation = new JButton("Delete location");
        deleteLocation.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                int rez = JOptionPane.showConfirmDialog(LocationDescriptionDialog.this, "Do you really want to delete this location?", "", JOptionPane.YES_NO_OPTION);

                if(rez == JOptionPane.OK_OPTION){
                    try {
                        Connector.deleteLocation(location.getName());
                        UsserButton.userSettingsMenu.setVisible(false);
                        try {
                            UsserButton.userSettingsMenu.updateVisited();
                        } catch (SQLException e2) {
                            // TODO Auto-generated catch block
                            e2.printStackTrace();
                        }
                        UsserButton.userSettingsMenu.setVisible(true);
                        LocationDescriptionDialog.this.setVisible(false);
                    } catch (SQLException e1) {
                        JOptionPane.showMessageDialog(UsserButton.userSettingsMenu, "Failed to delete location", "ERROR", JOptionPane.ERROR_MESSAGE);
                        e1.printStackTrace();
                    }
                }
                
            }

        });

        deleteLocation.setVisible(false);
        this.add(deleteLocation);
        
        
        likeButton = new CostumButton(80, 80, likePath);
       
       
        
        likeButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = location.getName();
                int like = location.getLikes();
                try {
                    Connector.modifylikeState(name, username, true);
                    if(location.getUserlikes().contains(Connector.USERNAME) == false)
                    {
                        like++;
                        likeButton.updateIcon(likeSelectedPath);
                        location.getUserlikes().add(Connector.USERNAME);
                    }
                    else
                    {
                        like--;
                        likeButton.updateIcon(likePath);
                        location.getUserlikes().remove(Connector.USERNAME);
    
                    }
                    
                    Connector.like(name, username, like);
                    
                    location.setLikes(like);
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(App.accountCreationFrame, Connector.ERROR_LIKE_UPDATE, "ERROR", JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();
                }

               
                
            }

        });
        likeButton.setVisible(false);
        
        dislikeButton = new CostumButton(80, 80, dislikePath);
        
        
        dislikeButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String name = location.getName();
                int dislikes = location.getLikes();
                try {
                    Connector.modifylikeState(name, username, false);
                    if(location.getUserdislikes().contains(Connector.USERNAME) == false)
                    {
                        dislikes++;
                        dislikeButton.updateIcon(dislikeSelectedPath);
                        location.getUserdislikes().add(Connector.USERNAME);
                    }
                    else
                    {
                        dislikes--;
                        dislikeButton.updateIcon(dislikePath);
                        location.getUserdislikes().remove(Connector.USERNAME);
    
                    }
                    
                    Connector.dislike(name, username, dislikes);
                    location.setDislikes(dislikes);
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(App.accountCreationFrame, Connector.ERROR_LIKE_UPDATE, "ERROR", JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();
                }

               
                
            }

        });
        dislikeButton.setVisible(false);
        this.add(likeButton);
        this.add(dislikeButton);
    }

    public void updateLocation(Location location){
        this.location = location;
        this.descriptionTextArea.setText(location.getDescription());
        this.setTitle(location.getName());

    }

    public void updatelikedislikeicons(){
        if(location.getUserlikes().contains(Connector.USERNAME) == false){
            likeButton.updateIcon(likePath);
        }
        else
        {
            likeButton.updateIcon(likeSelectedPath);
        }

        if(location.getUserdislikes().contains(Connector.USERNAME) == false){
            dislikeButton.updateIcon(dislikePath);
        }
        else
        {
            dislikeButton.updateIcon(dislikeSelectedPath);
        }
    }
}
