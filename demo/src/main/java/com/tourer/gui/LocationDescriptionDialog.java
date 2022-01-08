package com.tourer.gui;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;

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
    public static final Integer SPACER_SIZE = 10;
    public JLabel likeCount;
    public JLabel dislikeCount;
    public LocationDescriptionDialog(Window window){
        super(window);

        this.setSize(DIALOG_SIZE);
        this.setPreferredSize(DIALOG_SIZE);
        this.setResizable(false);
        this.setVisible(false);
        
        contentPane = new ColorPanel();
        SpringLayout springLayout = new SpringLayout();
        contentPane.setLayout(springLayout);
        contentPane.setPreferredSize(new Dimension(this.getWidth() - 100,this.getHeight() * 2 ));
        this.setContentPane(contentPane);
        this.setLocationRelativeTo(null);
        JLabel description = new JLabel("Description");
        description.setForeground(Color.orange);
        description.setFont(new Font(AppSettingsMenu.fontStyle, AppSettingsMenu.fontType, AppSettingsMenu.textSize));
        
        this.add(description);
        springLayout.putConstraint(SpringLayout.NORTH, description, SPACER_SIZE, SpringLayout.NORTH, contentPane);
        springLayout.putConstraint(SpringLayout.WEST, description, SPACER_SIZE, SpringLayout.WEST, contentPane);
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
        springLayout.putConstraint(SpringLayout.NORTH, textScrollPane, SPACER_SIZE, SpringLayout.SOUTH, description);
        springLayout.putConstraint(SpringLayout.WEST, textScrollPane, SPACER_SIZE, SpringLayout.WEST, contentPane);
        springLayout.putConstraint(SpringLayout.EAST, textScrollPane, -SPACER_SIZE, SpringLayout.EAST, contentPane);
        JButton showOnMapButton = new JButton("Show on Map");
        Font buttonTextFont = new Font("Serif", Font.BOLD, 18);
        showOnMapButton.setFont(buttonTextFont);
        showOnMapButton.setPreferredSize(new Dimension(this.getWidth() - 50, 40));
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
        springLayout.putConstraint(SpringLayout.NORTH, showOnMapButton, SPACER_SIZE, SpringLayout.SOUTH, textScrollPane);
        springLayout.putConstraint(SpringLayout.WEST, showOnMapButton, SPACER_SIZE, SpringLayout.WEST, contentPane);
        updateLocation = new JButton("Update location");
        updateLocation.setFont(buttonTextFont);
        updateLocation.setPreferredSize(new Dimension(this.getWidth() - 50, 40));
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
        
        springLayout.putConstraint(SpringLayout.NORTH, updateLocation, SPACER_SIZE, SpringLayout.SOUTH, showOnMapButton);
        springLayout.putConstraint(SpringLayout.WEST, updateLocation, SPACER_SIZE, SpringLayout.WEST, contentPane);
        deleteLocation = new JButton("Delete location");
        deleteLocation.setFont(buttonTextFont);
        deleteLocation.setPreferredSize(new Dimension(this.getWidth() - 50, 40));
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
        
        springLayout.putConstraint(SpringLayout.NORTH, deleteLocation, SPACER_SIZE, SpringLayout.SOUTH, updateLocation);
        springLayout.putConstraint(SpringLayout.WEST, deleteLocation, SPACER_SIZE, SpringLayout.WEST, contentPane);
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
                    LocationDescriptionDialog.this.likeCount.setText("" + location.getLikes());
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
                int dislikes = location.getDislikes();
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
                   
                    LocationDescriptionDialog.this.dislikeCount.setText("" + location.getDislikes());
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(App.accountCreationFrame, Connector.ERROR_LIKE_UPDATE, "ERROR", JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();
                }

               
                
            }

        });
        dislikeButton.setVisible(false);
        this.add(likeButton);
        this.add(dislikeButton);
        springLayout.putConstraint(SpringLayout.NORTH, likeButton, SPACER_SIZE, SpringLayout.SOUTH, showOnMapButton);
        springLayout.putConstraint(SpringLayout.NORTH, dislikeButton, SPACER_SIZE + 25, SpringLayout.SOUTH, showOnMapButton);
        springLayout.putConstraint(SpringLayout.WEST, likeButton, this.getWidth() / 3 + 40, SpringLayout.WEST, contentPane);
        springLayout.putConstraint(SpringLayout.WEST, dislikeButton, 0, SpringLayout.EAST, likeButton);
        
        likeCount = new JLabel("-");
        likeCount.setForeground(Color.orange);
        likeCount.setFont(new Font(AppSettingsMenu.fontStyle, AppSettingsMenu.fontType, AppSettingsMenu.textSize));
        dislikeCount = new JLabel("-");
        dislikeCount.setForeground(Color.orange);
        dislikeCount.setFont(new Font(AppSettingsMenu.fontStyle, AppSettingsMenu.fontType, AppSettingsMenu.textSize));
        this.add(likeCount);
        this.add(dislikeCount);
        springLayout.putConstraint(SpringLayout.NORTH, likeCount, SPACER_SIZE, SpringLayout.SOUTH, likeButton);
        springLayout.putConstraint(SpringLayout.NORTH, dislikeCount, SPACER_SIZE, SpringLayout.SOUTH, likeButton);
        springLayout.putConstraint(SpringLayout.WEST, likeCount, this.getWidth() / 3 + 40 + 70, SpringLayout.WEST, contentPane);
        springLayout.putConstraint(SpringLayout.WEST, dislikeCount, 80, SpringLayout.EAST, likeCount);
        JScrollPane scrolableContentPane = new JScrollPane(this.getContentPane());
        
        this.setContentPane(scrolableContentPane);
    }

    public void updateLocation(Location location){
        this.location = location;
        this.descriptionTextArea.setText(location.getDescription());
        this.setTitle(location.getName());
        this.likeCount.setText("" + location.getLikes());
        this.dislikeCount.setText("" + location.getDislikes());
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
