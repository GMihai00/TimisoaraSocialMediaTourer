package com.tourer.gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class LocationButton extends CostumButton{

    static LocationSearchField locationSearchField;
    public final static String iconPath = "Icons\\Location.png";
    public final static String darkIconPath = "Icons\\LocationDark.png";
    public LocationButton(int w, int h, LocationSearchField locationSearchField) {
        super(w, h, LocationButton.iconPath);
        LocationButton.locationSearchField = locationSearchField;
        
        this.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < SearchField.allFields.size(); i++)
                    SearchField.allFields.get(i).setVisible(false);
                LocationButton.locationSearchField.setVisible(true);
                //JTextField textLocal = (JTextField) LocationButton.locationSearchField.getEditor().getEditorComponent();
                // locationSearchField.requestFocus();
                // if(textLocal.getText().equals(""))
                //     textLocal.setText("Search a location");
                LocationButton.locationSearchField.requestFocus();
            }
            
        });
    }
    

}
