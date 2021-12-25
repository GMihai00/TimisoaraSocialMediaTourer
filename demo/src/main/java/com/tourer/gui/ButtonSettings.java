package com.tourer.gui;

import java.awt.Font;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



import javax.swing.JLabel;
import javax.swing.JPanel;

public class ButtonSettings extends GridPanel{
    
    public static CardDataDialog cardDataDialog; 
    Color c1Select;
    Color c2Select;
    static Color c3Select = Color.black;
    static Color c3 = Color.orange;
    String text;
    static CardLayout cardLayout;
    static JPanel cardPanel;
    public JLabel label;
    String name;
    static{
        
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

    }
    public ButtonSettings(String name){
        this.c1Select = Color.RED;
        this.c2Select = Color.YELLOW;
        this.name = name;
        label = new JLabel(name);
        label.setFont(new Font(AppSettingsMenu.fontStyle, AppSettingsMenu.fontType, AppSettingsMenu.textSize));
        label.setForeground(c3);
        this.addLeft(label);

        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent event) {
                ButtonSettings.this.swapColors();

            }
            @Override
            public void mouseExited(MouseEvent event){
                ButtonSettings.this.swapColors();
            }
            @Override
            public void mouseClicked(MouseEvent me){
                
                cardLayout.show(cardPanel, name);
            }
        });
    }

    public ButtonSettings(Color c1Select, Color c2Select, String name){
        this.c1Select = c1Select;
        this.c2Select = c2Select;
        this.name = name;
        label = new JLabel(name);
        label.setForeground(c3);
        label.setFont(new Font(AppSettingsMenu.fontStyle, AppSettingsMenu.fontType, AppSettingsMenu.textSize));
        this.addLeft(label);

        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent event) {
                ButtonSettings.this.swapColors();

            }
            @Override
            public void mouseExited(MouseEvent event){
                ButtonSettings.this.swapColors();
            }
            @Override
            public void mouseClicked(MouseEvent me){
               
                cardLayout.show(cardPanel, name);
            }
        });
    }

    public ButtonSettings(Color c1Select, Color c2Select, String name, Integer nr){
        this.c1Select = c1Select;
        this.c2Select = c2Select;
        this.name = name;
        label = new JLabel(name);
        label.setForeground(c3);
        label.setFont(new Font(AppSettingsMenu.fontStyle, AppSettingsMenu.fontType, AppSettingsMenu.textSize));
        this.addLeft(label);

        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent event) {
                ButtonSettings.this.swapColors();

            }
            @Override
            public void mouseExited(MouseEvent event){
                ButtonSettings.this.swapColors();
            }
            @Override
            public void mouseClicked(MouseEvent me){
                
                switch(nr){
                    case 1:
                        cardDataDialog.setVisible(true);
                        break;
                }
                
            }
        });
    }

    public void swapColors(){
        Color auxc1 = c1;
        Color auxc2 = c2;
        Color auxc3 = c3;
        c1 = c1Select;
        c2 = c2Select;
        c3 = c3Select;
        c1Select = auxc1;
        c2Select = auxc2;
        c3Select = auxc3;
        label.setForeground(c3);
        repaint();
        revalidate();
    }
}
