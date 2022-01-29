package com.tourer.gui;



import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class GridPanel extends ColorPanel{
    public static GridBagConstraints left;
    public static GridBagConstraints right;
    static{
        left = new GridBagConstraints();
        left.anchor = GridBagConstraints.EAST;
        right = new GridBagConstraints();
        right.weightx = 2.0;
        right.fill = GridBagConstraints.HORIZONTAL;
        right.gridwidth = GridBagConstraints.REMAINDER;
    }
    public GridPanel(){
        this.setLayout(new GridBagLayout());
    }

    public GridPanel(GradientColor gColor){
        super(gColor);
        this.setLayout(new GridBagLayout());
    }

    
    /** 
     * @param c
     */
    public void addLeft(Component c){
        this.add(c, left);
    }

    
    /** 
     * @param c
     */
    public void addRight(Component c){
        this.add(c, right);
    }
    
    /** 
     * @param c
     */
    public void addSpacer(Component c){
        this.addLeft(c);
        this.addRight(c);
    }
}
