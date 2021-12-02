package com.tourer.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ColorPanel extends JPanel{
    
    Color c1;
    Color c2;

    public ColorPanel() {
        super();
        c1 =  MainFrame.gradientColor.getMainColor();
        c2 = MainFrame.gradientColor.getSecondaryColor();
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public ColorPanel(GradientColor gc) {
        super();
        c1 =  gc.getMainColor();
        c2 = gc.getSecondaryColor();
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();

        GradientPaint gp = new GradientPaint(0, 0, c1, 0, h, c2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }
}
