package com.tourer.gui;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CheckBox extends JCheckBox{
    final static ImageIcon unCheckedIcon = new ImageIcon(new ImageIcon("Icons\\UnChecked.png").getImage().getScaledInstance(AppSettingsMenu.w, AppSettingsMenu.h, Image.SCALE_SMOOTH));
    final static ImageIcon checkedIcon = new ImageIcon(new ImageIcon("Icons\\Checked.png").getImage().getScaledInstance(AppSettingsMenu.w, AppSettingsMenu.h, Image.SCALE_SMOOTH));
    final static ImageIcon unCheckedIconRoll = new ImageIcon(new ImageIcon("Icons\\UnCheckedRoll.png").getImage().getScaledInstance(AppSettingsMenu.w, AppSettingsMenu.h, Image.SCALE_SMOOTH));
    final static ImageIcon checkedIconRoll = new ImageIcon(new ImageIcon("Icons\\CheckedRoll.png").getImage().getScaledInstance(AppSettingsMenu.w, AppSettingsMenu.h, Image.SCALE_SMOOTH));

    public CheckBox(String name){
        
        super(name);
         // Set default icon for checkbox
        this.setIcon(unCheckedIcon);
         // Set selected icon when checkbox state is selected
        this.setSelectedIcon(checkedIcon);
         // Set disabled icon for checkbox
        this.setDisabledIcon(unCheckedIcon);
         // Set disabled-selected icon for checkbox
        this.setDisabledSelectedIcon(unCheckedIconRoll);
         // Set checkbox icon when checkbox is pressed
        this.setPressedIcon(checkedIcon);
         // Set icon when a mouse is over the checkbox
        this.setRolloverIcon(checkedIconRoll);
         // Set icon when a mouse is over a selected checkbox
        this.setRolloverSelectedIcon(checkedIconRoll);
        this.setFont(new Font(AppSettingsMenu.fontStyle, AppSettingsMenu.fontType, AppSettingsMenu.textSize));
        
        this.setOpaque(false);
        this.setBorderPainted(false);
        this.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(CheckBox.this.isSelected()){
                    //modify color scheme
                }
                
            }

        });
    }
}
