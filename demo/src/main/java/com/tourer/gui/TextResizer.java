package com.tourer.gui;

import java.lang.Math;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TextResizer extends Resizer{
 
    public static void changeFont ( Component component, Font font )
    {
        component.setFont ( font );
        if ( component instanceof Container )
        {
            for ( Component child : ( ( Container ) component ).getComponents () )
            {
                changeFont ( child, font );
            }
        }
    }

    public TextResizer(){
        this.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                
                int newTextSize = (int) (AppSettingsMenu.textSize *  (double)TextResizer.this.getValue() / 100);
                Font newFont = new Font(AppSettingsMenu.fontStyle, AppSettingsMenu.fontType, newTextSize);
                changeFont(ButtonBox.userSettingsMenu, newFont);
                changeFont(ButtonBox.appMenuSettings, newFont);
            }

            
            
        });
    }
}
