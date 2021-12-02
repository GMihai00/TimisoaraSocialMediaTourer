package com.tourer.gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonSettings extends GridPanel{
    
    Color c1Select;
    Color c2Select;
    public ButtonSettings(){
        this.c1Select = Color.RED;
        this.c2Select = Color.YELLOW;
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent event) {
                ButtonSettings.this.swapColors();

            }
            @Override
            public void mouseExited(MouseEvent event){
                ButtonSettings.this.swapColors();
            }
        });
    }

    public ButtonSettings(Color c1Select, Color c2Select){
        this.c1Select = c1Select;
        this.c2Select = c2Select;
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent event) {
                ButtonSettings.this.swapColors();

            }
            @Override
            public void mouseExited(MouseEvent event){
                ButtonSettings.this.swapColors();
            }
        });
    }

    public void swapColors(){
        Color auxc1 = c1;
        Color auxc2 = c2;
        c1 = c1Select;
        c2 = c2Select;
        c1Select = auxc1;
        c2Select = auxc2;
        repaint();
        revalidate();
    }
}
