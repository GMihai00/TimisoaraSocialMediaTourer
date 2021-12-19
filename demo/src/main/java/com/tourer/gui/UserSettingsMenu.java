package com.tourer.gui;

import com.tourer.App;
import com.tourer.gui.map.Location;
import com.tourer.jdbc.Connector;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Image;

public class UserSettingsMenu extends SettingsMenu{
    
    public ColorPanel contentPanel;
    public final static int USERICON_WIDTH = 200;
    public final static int USERICON_HEIGHT = 200;
    public final static String profileImagePath = "Icons\\DefaultUserProfileImage.jpg";
    public static JLabel userIcon = new JLabel();
    public static JScrollPane listScroller;
    public JList list;
    public final static MyListCellRenderer myListCellRenderer = new MyListCellRenderer();
    public  LocationDescriptionDialog locationDescriptionDialog;
    static{
        Dimension backgroundDimension = new Dimension((MainFrame.screenSize.width * 5) / 6 - 20, (((MainFrame.screenSize.height * 5) / 6) * 2) / 3 );
        userIcon.setSize(USERICON_WIDTH,  USERICON_HEIGHT);
        
        listScroller = new JScrollPane();
        listScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        listScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        listScroller.getVerticalScrollBar().setUI(new MyScrollbarUI(App.gradientColor, Color.white));
        listScroller.getVerticalScrollBar().setUnitIncrement(20);
        listScroller.getHorizontalScrollBar().setUI(new MyScrollbarUI(App.gradientColor, Color.white));
        listScroller.getHorizontalScrollBar().setUnitIncrement(20);
        
        listScroller.setPreferredSize(backgroundDimension);
        listScroller.setSize(backgroundDimension);
    }
    
    public UserSettingsMenu(){
        locationDescriptionDialog = new LocationDescriptionDialog(this);
        contentPanel = new ColorPanel();
        contentPanel.setPreferredSize(this.getSize());
        contentPanel.setSize(this.getSize());
        
        addUserProfileImage();
       
        
        contentPanel.add(listScroller);

        this.add(contentPanel, BorderLayout.CENTER);
        this.update();
    }

    public void addUserProfileImage(){
        Image profileImage = Toolkit.getDefaultToolkit().getImage(profileImagePath);
        Dimension backgroundDimension = new Dimension((int) this.getSize().getWidth(),(int) this.getSize().getHeight() / 3);
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(profileImagePath));
            profileImage  = bufferedImage.getScaledInstance((int)backgroundDimension.getWidth(),(int) backgroundDimension.getHeight(), Image.SCALE_SMOOTH);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        BackGroundPanel backGroundPanel = new BackGroundPanel(profileImage);
        
        backGroundPanel.setPreferredSize(backgroundDimension);
        backGroundPanel.setSize(backgroundDimension);
        
        String iconPath = UsserButton.iconPath;
        ImageIcon icon = new ImageIcon(new ImageIcon(iconPath).getImage().getScaledInstance(USERICON_WIDTH, USERICON_HEIGHT, Image.SCALE_SMOOTH));
        userIcon.setIcon(icon);

        backGroundPanel.add(userIcon);
        
        contentPanel.add(backGroundPanel);
    }


    public void updateVisited() throws SQLException{

        Vector <Location> visitedLocations = Connector.getVisitedLocations(); 

       
        
        JList <Location> locationList = new JList(visitedLocations.toArray());
        locationList.setOpaque(false);
        locationList.setCellRenderer(myListCellRenderer);
        locationList.setForeground(Color.ORANGE);
        locationList.setBackground(AppSettingsMenu.PURPLE_COLOR);
        locationList.setSelectionForeground(AppSettingsMenu.PURPLE_COLOR);
        locationList.setSelectionBackground(Color.ORANGE);
        locationList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                    UserSettingsMenu.this.locationDescriptionDialog.updateLocation(locationList.getSelectedValue());
                    UserSettingsMenu.this.locationDescriptionDialog.setVisible(true);
                }
            }
        });
        list = locationList;
        
        ViewPort viewPort = new ViewPort(App.gradientColor);
        viewPort.setView(list);
        listScroller.setViewportView(viewPort);
        listScroller.revalidate();
        listScroller.repaint();
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}
