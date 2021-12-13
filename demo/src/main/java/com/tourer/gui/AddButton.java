package com.tourer.gui;

import com.tourer.App;
import com.tourer.jdbc.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;

public class AddButton extends CostumButton{

    public final static String iconPath = "Icons\\Add.png";
    public final static String darkIconPath = "Icons\\AddDark.png";
    public static AddLocationDialog addLocationDialog = new AddLocationDialog(App.mainFrame);
    public AddButton(int w, int h) {
        super(w, h, AddButton.iconPath);
        
        this.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                //this 2 need to be taken from the curent location on the map
                Integer latitude = 0; //method to get this
                Integer longitude = 0; //method to get this
                
                AddLocationDialog.latitude.setText("Latitude: " + latitude.toString());
                AddLocationDialog.longitude.setText("Longitude: " + longitude.toString()  + "  ");
                addLocationDialog.setVisible(true);

                while(addLocationDialog.isVisible() == true){

                }

                String locationDescription = AddLocationDialog.locationDescritpionTextField.getText();
                String locationName = AddLocationDialog.locationNameTextField.getText();
                if(locationDescription.equals("") == false && locationName.equals("") == false){
                    //insert into database
                    
                    try {
                        Connector.createLocation(latitude, longitude, locationName, locationDescription);
                    } catch (SQLException e1) {
                        
                        JOptionPane.showMessageDialog(App.mainFrame, "Failde to add location to database", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
                
                AddLocationDialog.locationDescritpionTextField.setText("");
                AddLocationDialog.locationNameTextField.setText("");
 
            }
            
        });
        
    }
    

}
