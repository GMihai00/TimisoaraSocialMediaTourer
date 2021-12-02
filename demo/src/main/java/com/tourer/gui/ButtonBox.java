package com.tourer.gui;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.BoxLayout;

public class ButtonBox extends Box{

    public final static int buttonHeight = 80;
    public final static int buttonWidth  = 80;

    public ButtonBox(LocationSearchField locationSearchField, UserSearchField userSearchField) {
        super(BoxLayout.PAGE_AXIS);

        SearchButton searchButton = new SearchButton(ButtonBox.buttonWidth, ButtonBox.buttonHeight, userSearchField);
        this.addWithSpacer(searchButton, ButtonBox.buttonWidth / 2);
        
        LocationButton locationButton = new LocationButton(ButtonBox.buttonWidth, ButtonBox.buttonHeight, locationSearchField);
        this.addWithSpacer(locationButton, ButtonBox.buttonWidth / 2);

        AddButton addButton = new AddButton(ButtonBox.buttonWidth,  ButtonBox.buttonHeight);
        this.addWithSpacer(addButton, ButtonBox.buttonWidth / 2);

        UserSettingsMenu userSettingsMenu = new UserSettingsMenu();
        UsserButton usserButton = new UsserButton(ButtonBox.buttonWidth,  ButtonBox.buttonHeight, userSettingsMenu);
        this.addWithSpacer(usserButton, ButtonBox.buttonWidth / 2);
        AppSettingsMenu appMenuSettings = new AppSettingsMenu();
        SettingsButton settingsButton = new SettingsButton(ButtonBox.buttonWidth,  ButtonBox.buttonHeight, appMenuSettings);
        this.addWithSpacer(settingsButton, ButtonBox.buttonWidth / 2);
    }
    public void addWithSpacer(Component comp, int size){
        this.add(comp);
        this.add(Box.createVerticalStrut(size));
    }
}

  
