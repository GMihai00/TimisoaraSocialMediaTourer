package com.tourer.gui;

import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;

import java.awt.Color;

public class MyListCellRenderer implements ListCellRenderer{

    
    Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 4, true);
    Border emptyBorder = BorderFactory.createEmptyBorder(2, 2, 2, 2);

    @Override
    public Component getListCellRendererComponent(JList jList, Object value, 
            int index, boolean isSelected, boolean cellHasFocus) {
        JLabel jlblCell = new JLabel(value.toString());
        jlblCell.setFont(new Font(AppSettingsMenu.fontStyle, AppSettingsMenu.fontType, AppSettingsMenu.textSize));
       
        if (isSelected) {
            jlblCell.setOpaque(true);

            jlblCell.setForeground(jList.getSelectionForeground());
            jlblCell.setBackground(jList.getSelectionBackground());
            
        } else {
            jlblCell.setOpaque(false);

            jlblCell.setForeground(jList.getForeground());
            jlblCell.setBackground(jList.getBackground());
        }

        jlblCell.setBorder(cellHasFocus ? lineBorder : emptyBorder);

        return jlblCell;
    }
}