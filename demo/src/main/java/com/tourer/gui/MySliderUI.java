package com.tourer.gui;

import javax.swing.JSlider;
import javax.swing.plaf.basic.BasicSliderUI;

import java.awt.Dimension;
import java.awt.Shape;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.LinearGradientPaint;
import java.awt.geom.Point2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
public class MySliderUI extends BasicSliderUI {

    private static final int TRACK_HEIGHT = 8;
    private static final int TRACK_WIDTH = 8;
    private static final int TRACK_ARC = 5;
    private static final Dimension THUMB_SIZE = new Dimension(20, 20);
    private final RoundRectangle2D.Float trackShape = new RoundRectangle2D.Float();
    private static float[] fracs = {0.0f, 1.0f};
    private LinearGradientPaint p;

    public MySliderUI(JSlider slider) {
        super(slider);
    }

    private boolean isHorizontal() {
        return slider.getOrientation() == JSlider.HORIZONTAL;
    }
    @Override
    protected void calculateTrackRect() {
        super.calculateTrackRect();
        if (isHorizontal()) {
            trackRect.y = trackRect.y + (trackRect.height - TRACK_HEIGHT) / 2;
            trackRect.height = TRACK_HEIGHT;
        } else {
            trackRect.x = trackRect.x + (trackRect.width - TRACK_WIDTH) / 2;
            trackRect.width = TRACK_WIDTH;
        }
        trackShape.setRoundRect(trackRect.x, trackRect.y, trackRect.width, trackRect.height, TRACK_ARC, TRACK_ARC);
    }

    @Override
    protected void calculateThumbLocation() {
        super.calculateThumbLocation();
        if (isHorizontal()) {
            thumbRect.y = trackRect.y + (trackRect.height - thumbRect.height) / 2;
        } else {
            thumbRect.x = trackRect.x + (trackRect.width - thumbRect.width) / 2;
        }
    }

    @Override
        public void paintTrack(final Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            Shape clip = g2.getClip();

            boolean horizontal = isHorizontal();
            boolean inverted = slider.getInverted();

            // Paint shadow.
            g2.setColor(new Color(170, 170 ,170));
            g2.fill(trackShape);

            // Paint track background.
            g2.setColor(new Color(200, 200 ,200));
            g2.setClip(trackShape);
            trackShape.y += 1;
            g2.fill(trackShape);
            trackShape.y = trackRect.y;

            g2.setClip(clip);

            // Paint selected track.
            if (horizontal) {
                boolean ltr = slider.getComponentOrientation().isLeftToRight();
                if (ltr) inverted = !inverted;
                int thumbPos = thumbRect.x + thumbRect.width / 2;
                if (inverted) {
                    g2.clipRect(0, 0, thumbPos, slider.getHeight());
                } else {
                    g2.clipRect(thumbPos, 0, slider.getWidth() - thumbPos, slider.getHeight());
                }

            } else {
                int thumbPos = thumbRect.y + thumbRect.height / 2;
                if (inverted) {
                    g2.clipRect(0, 0, slider.getHeight(), thumbPos);
                } else {
                    g2.clipRect(0, thumbPos, slider.getWidth(), slider.getHeight() - thumbPos);
                }
            }
            g2.setColor(Color.ORANGE);
            g2.fill(trackShape);
            g2.setClip(clip);
        }

   
    // @Override
    // public void paintThumb(Graphics g) {
        
    //     Rectangle knobBounds = thumbRect;
    //     int w = knobBounds.width;
    //     int h = knobBounds.height;      
        
    //     // Create graphics copy.
    //     Graphics2D g2d = (Graphics2D) g.create();

    //     // Create default thumb shape.
    //     Shape thumbShape = new Ellipse2D.Double(0, 0, w-1, h-1);

    //     // Draw thumb.
    //     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
    //         RenderingHints.VALUE_ANTIALIAS_ON);
    //     g2d.translate(knobBounds.x, knobBounds.y);

    //     g2d.setColor(AppSettingsMenu.PURPLE_COLOR);
    //     g2d.fill(thumbShape);

    //     g2d.setColor(MainFrame.gradientColor.getSecondaryColor());
    //     g2d.draw(thumbShape);
        
    //     // Dispose graphics.
    //     g2d.dispose();
    // }
}