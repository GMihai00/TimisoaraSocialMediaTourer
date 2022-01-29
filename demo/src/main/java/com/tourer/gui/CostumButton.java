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
    String iconPath;
    public  CostumButton(int w, int h, String iconPath) {
        this.w = w;
        this.h = h;
        this.iconPath = iconPath;
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

    
    /** 
     * @param w
     * @param h
     */
    public void updateIconSize(int w, int h){
        ImageIcon icon = new ImageIcon(new ImageIcon(iconPath).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
        this.setIcon(icon);
    }
    
    /** 
     * @param path
     */
    public void updateIcon(String path){
        icon = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
        selectIcon = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(w + 20, h + 20, Image.SCALE_SMOOTH));
        this.setIcon(icon);
    }
}
