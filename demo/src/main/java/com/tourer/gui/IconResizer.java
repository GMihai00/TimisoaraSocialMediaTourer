package com.tourer.gui;

import java.awt.Component;
import java.awt.Container;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.tourer.App;

public class IconResizer extends Resizer{
    
    public static void changeIconSize ( Container container, int newIconHight, int newIconWidth)
    {
        
        
        for ( Component child :  container.getComponents () )
        {
            if ( child instanceof CostumButton ){
               ((CostumButton) child).updateIconSize(newIconWidth, newIconHight);
            }
        }
        
    }
    public IconResizer(){
        this.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                int newIconHight = (int) (ButtonBox.buttonHeight *  (double)IconResizer.this.getValue() / 100);
                int newIconWidth = (int) (ButtonBox.buttonWidth *  (double)IconResizer.this.getValue() / 100);
                changeIconSize(App.buttonBox, newIconHight, newIconWidth);

            }

            
            
        });
    }
}
