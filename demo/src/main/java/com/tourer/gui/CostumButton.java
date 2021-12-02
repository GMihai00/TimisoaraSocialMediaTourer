package com.tourer.gui;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.Image;

public class CostumButton extends JButton{
    
    int w;
    int h;
    ImageIcon icon;
    ImageIcon selectIcon;
    public  CostumButton(int w, int h, String iconPath) {
        this.w = w;
        this.h = h;
        icon = new ImageIcon(new ImageIcon(iconPath).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
        selectIcon = new ImageIcon(new ImageIcon(iconPath).getImage().getScaledInstance(w + 20, h + 20, Image.SCALE_SMOOTH));
        this.setIcon(icon);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent event) {
                CostumButton.this.setIcon(CostumButton.this.selectIcon);

            }
            @Override
            public void mouseExited(MouseEvent event){
                CostumButton.this.setIcon(CostumButton.this.icon);
            }
        });
    }

    public void updateIcon(int w, int h, String iconPath){
        ImageIcon icon = new ImageIcon(new ImageIcon(iconPath).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
        this.setIcon(icon);
    }
}
