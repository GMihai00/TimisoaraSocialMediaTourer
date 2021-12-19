package com.tourer.gui;


import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JViewport;

/** 
    * 
    * @since 2.0
    * Class that is used to change the bacground of the logArea
    */
public @SuppressWarnings("serial") class ViewPort extends JViewport {
    private Color c1;
    private Color c2;

    /**
     * Constructor of the class
     * @param c1 main color
     * @param c2 secondary color
     */
    public ViewPort(GradientColor g) {
        this.c1 = g.mainColor;
        this.c2 = g.secondaryColor;
    }

    
    /** 
     * Creates gradient background using the 2 colors that were given in the constructor
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        GradientPaint gPaint = new GradientPaint(0, 0, c1, getWidth(), getHeight(), c2, false);
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(gPaint);
        g2.fillRect(0, 0, getWidth(), getHeight());
    }

}