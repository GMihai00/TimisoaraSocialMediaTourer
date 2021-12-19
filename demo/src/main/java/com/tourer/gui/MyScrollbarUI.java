package com.tourer.gui;


import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Image;
import java.awt.GradientPaint;
import java.awt.RenderingHints;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.metal.MetalScrollBarUI;

/** 
    * 
    * @since 2.0
    * Main class of the app that contains the main method
    */
class MyScrollbarUI extends MetalScrollBarUI {
    
    /**
     * Custom image 
     */
    static class FauxImage {
        
        static public Image create(int w, int h, GradientColor gradient) {
            BufferedImage bi = new BufferedImage(
                w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bi.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            
            GradientPaint gp = new GradientPaint(0, 0, gradient.getMainColor(), 0, h, gradient.getSecondaryColor());
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, w, h);
            g2d.dispose();
            return bi;
        }
    }
    private Image imageThumb, imageTrack;
    private JButton b = new JButton() {

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(0, 0);
        }

    };
    
    

    /**
     * Constructor of the class
     * @param c1 main color
     * @param c2 secondary color
     * @param c3 third color
     */
    public MyScrollbarUI(GradientColor gradient, Color c3) {
        imageThumb = FauxImage.create(32, 32, gradient);
        imageTrack = FauxImage.create(32, 32, new GradientColor(c3, c3));
    }

    
    /** 
     * @param g
     * @param c
     * @param r
     */
    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
       
        ((Graphics2D) g).drawImage(imageThumb,
            r.x, r.y, r.width, r.height, null);
    }

    
    /** 
     * @param g
     * @param c
     * @param r
     */
    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
        ((Graphics2D) g).drawImage(imageTrack,
            r.x, r.y, r.width, r.height, null);
    }

    
    /** 
     * @param orientation
     * @return JButton
     */
    @Override
    protected JButton createDecreaseButton(int orientation) {
       
        return b;
    }

    
    /** 
     * @param orientation
     * @return JButton
     */
    @Override
    protected JButton createIncreaseButton(int orientation) {
        
        return b;
    }
}