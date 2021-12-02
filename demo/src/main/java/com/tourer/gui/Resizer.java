package com.tourer.gui;


import javax.swing.JSlider;

import java.awt.Font;

public class Resizer extends JSlider{
    
    public Resizer(){
        super(25, 175, 100);
        this.setPaintTicks(true);
        this.setMinorTickSpacing(5);
        this.setMajorTickSpacing(25);
        this.setPaintLabels(true);
        this.setOpaque(false);
        this.setBorder(MainFrame.nullBorder);
        this.setFont(new Font(AppSettingsMenu.fontStyle, AppSettingsMenu.fontType, 20));
        this.setUI(new MySliderUI(this));
    }
}
