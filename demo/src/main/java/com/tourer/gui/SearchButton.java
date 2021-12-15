package com.tourer.gui;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
public class SearchButton extends CostumButton{

    public final static String iconPath = "Icons\\Search.png";
    public final static String darkIconPath = "Icons\\SearchDark.png";

    static UserSearchField userSearchField;

    public SearchButton(int w, int h, UserSearchField userSearchField){
        super(w, h, SearchButton.iconPath);
        SearchButton.userSearchField = userSearchField;

        this.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < SearchField.allFields.size(); i++)
                    SearchField.allFields.get(i).setVisible(false);
                SearchButton.userSearchField.setVisible(true);
                // JTextField textLocal = (JTextField) SearchButton.userSearchField.getEditor().getEditorComponent();
                // if(textLocal.getText().equals(""))
                //     textLocal.setText("Search a user");
                SearchButton.userSearchField.requestFocus();    
            }
            
        });

      
    }
    

}
