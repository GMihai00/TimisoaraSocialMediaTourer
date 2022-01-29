package com.tourer.gui;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

public class BackGroundPanel extends JPanel{
    
    Image img;
    public BackGroundPanel(Image img) {
        super();
        this.img = img;
    }

    
    /** 
     * @return Image
     */
    public Image getImg() {
        return this.img;
    }

    
    /** 
     * @param img
     */
    public void setImg(Image img) {
        this.img = img;
    }


    
    /** 
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
        
    }
}
