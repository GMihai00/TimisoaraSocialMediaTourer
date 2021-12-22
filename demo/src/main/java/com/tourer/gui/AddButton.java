package com.tourer.gui;

import com.tourer.App;
import com.tourer.jdbc.*;

import javafx.application.Platform;

import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;

import netscape.javascript.JSObject;

public class AddButton extends CostumButton{

    public final static String iconPath = "Icons\\Add.png";
    public final static String darkIconPath = "Icons\\AddDark.png";
    public static AddLocationDialog addLocationDialog = new AddLocationDialog(App.mainFrame);
    public static Double latitude = 0d;
    public static Double longitude = 0d;
    public AddButton(int w, int h) {
        super(w, h, AddButton.iconPath);
        
        this.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                //this 2 need to be taken from the curent location on the map
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        JSObject location = (JSObject) App.engine.executeScript("getLocationOfCommit();");
                        latitude =((Double) location.getMember("lat"));
                        longitude =((Double) location.getMember("lng"));
                    }
                });
                
                
                AddLocationDialog.latitude.setText("Latitude: " + latitude.toString());
                AddLocationDialog.longitude.setText("Longitude: " + longitude.toString()  + "  ");
                addLocationDialog.revalidate();
                addLocationDialog.repaint();
                addLocationDialog.setVisible(true);
                
                while(addLocationDialog.isVisible() == true){

                }

                String locationDescription = AddLocationDialog.locationDescritpionTextField.getText();
                String locationName = AddLocationDialog.locationNameTextField.getText();
                if(locationDescription.equals("") == false && locationName.equals("") == false){
                    //insert into database
                    
                    try {
                        Connector.createLocation(latitude, longitude, locationName, locationDescription);
                        Platform.runLater(new Runnable() {

                            @Override
                            public void run() {
                                App.engine.executeScript("commitMarker();");
                            }
                        });
                       
                        
                    } catch (SQLException e1) {
                        
                        JOptionPane.showMessageDialog(App.mainFrame, "Failde to add location to database", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
                
                AddLocationDialog.locationDescritpionTextField.setText("");
                AddLocationDialog.locationNameTextField.setText("");
 
            }
            
        });
        
    }
    
    public static void updateLocation(Double newLatitude, Double newLongitude){
        latitude = newLatitude;
        longitude = newLongitude;
    }

}
