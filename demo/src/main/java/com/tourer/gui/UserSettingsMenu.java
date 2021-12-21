package com.tourer.gui;

import com.tourer.App;
import com.tourer.gui.map.Location;
import com.tourer.jdbc.Connector;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Image;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
public class UserSettingsMenu extends SettingsMenu{
    
    public ColorPanel contentPanel;
    public final static int USERICON_WIDTH = 200;
    public final static int USERICON_HEIGHT = 200;
    public final static String defaultProfileImagePath = "Icons\\DefaultUserProfileImage.jpg";
    public static JLabel userIcon = new JLabel();
    public static JScrollPane listScroller;
    public JList list;
    public final static String changeUserProfileImagePath = "Icons\\ChangeUserProfileImage.jpg";
    public final static String changeUserImagePath = "Icons\\CreateUserIcon.png";
    public BackGroundPanel backGroundPanel;
    public final static MyListCellRenderer myListCellRenderer = new MyListCellRenderer();
    public  LocationDescriptionDialog locationDescriptionDialog;
    public static boolean owned = false;
    public JLabel name;
    static{
        Dimension backgroundDimension = new Dimension((MainFrame.screenSize.width * 5) / 6 - 20, (((MainFrame.screenSize.height * 5) / 6) * 2) / 3 - 120 );
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
        userIcon.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent event) {
                if(UserSettingsMenu.owned == true){
                    
                    ImageIcon icon = new ImageIcon(new ImageIcon(changeUserImagePath).getImage().getScaledInstance(USERICON_WIDTH, USERICON_HEIGHT, Image.SCALE_SMOOTH));
                    userIcon.setIcon(icon);
                }
            }
            @Override
            public void mouseExited(MouseEvent event){
                if(UserSettingsMenu.owned == true){
                    UserSettingsMenu.this.updateUserProfileImage(Connector.USERNAME);
                }
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                if(UserSettingsMenu.owned == true){
                    File photoDir = new File("UserPhotos\\" + Connector.USERNAME);
                    if (!photoDir.exists()){
                        photoDir.mkdirs();
                    }
                   
                    JFileChooser chooser = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "JPEG file", "jpg", "jpeg", "png", "PNG file");
                    chooser.setFileFilter(filter);
                    int returnVal = chooser.showOpenDialog(UsserButton.userSettingsMenu);
                    if(returnVal == JFileChooser.APPROVE_OPTION) {
                    
                            String filename = chooser.getSelectedFile().getAbsolutePath();
                            File source = new File(filename);
                            String extension = "." + FilenameUtils.getExtension(filename);
                            File dest = new File("UserPhotos\\" + Connector.USERNAME + "\\ProfilePicture" + extension);

                            File curpng = new File("UserPhotos\\" + Connector.USERNAME + "\\ProfilePicture.png");
                            if(curpng.exists()){
                                curpng.delete();
                            }
                            File curjpg = new File("UserPhotos\\" + Connector.USERNAME + "\\ProfilePicture.jpg");
                            if(curjpg.exists()){
                                curjpg.delete();
                            }
                            try {
                                FileUtils.copyFile(source, dest);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                    }
                    

                }
            }
            
        });
        
        addUserProfileImage();
        backGroundPanel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent event) {
                if(UserSettingsMenu.owned == true){
                    
                    Image profileImage = Toolkit.getDefaultToolkit().getImage(changeUserProfileImagePath);
                    Dimension backgroundDimension = new Dimension((int) UserSettingsMenu.this.getSize().getWidth(),(int) UserSettingsMenu.this.getSize().getHeight() / 3);
                    try {
                        BufferedImage bufferedImage = ImageIO.read(new File(changeUserProfileImagePath));
                        profileImage  = bufferedImage.getScaledInstance((int)backgroundDimension.getWidth(),(int) backgroundDimension.getHeight(), Image.SCALE_SMOOTH);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    UserSettingsMenu.this.backGroundPanel.setImg(profileImage);
                    UserSettingsMenu.this.contentPanel.revalidate();
                    UserSettingsMenu.this.contentPanel.repaint();

                    UserSettingsMenu.this.update();
                }

            }
            @Override
            public void mouseExited(MouseEvent event){
                if(UserSettingsMenu.owned == true){
                    UserSettingsMenu.this.updateUserProfileImage(Connector.USERNAME);
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if(UserSettingsMenu.owned == true){
                    File photoDir = new File("UserPhotos\\" + Connector.USERNAME);
                    if (!photoDir.exists()){
                        photoDir.mkdirs();
                    }
                   
                    JFileChooser chooser = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "JPEG file", "jpg", "jpeg", "png", "PNG file");
                    chooser.setFileFilter(filter);
                    int returnVal = chooser.showOpenDialog(UsserButton.userSettingsMenu);
                    if(returnVal == JFileChooser.APPROVE_OPTION) {
                    
                            String filename = chooser.getSelectedFile().getAbsolutePath();
                            File source = new File(filename);
                            String extension = "." + FilenameUtils.getExtension(filename);
                            File dest = new File("UserPhotos\\" + Connector.USERNAME + "\\Background" + extension);

                            File curpng = new File("UserPhotos\\" + Connector.USERNAME + "\\Background.png");
                            if(curpng.exists()){
                                curpng.delete();
                            }
                            File curjpg = new File("UserPhotos\\" + Connector.USERNAME + "\\Background.jpg");
                            if(curjpg.exists()){
                                curjpg.delete();
                            }
                            try {
                                FileUtils.copyFile(source, dest);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                    }
                    

                }
            }

        });
        name = new JLabel("");
        name.setForeground(Color.yellow);
        name.setFont(new Font(Font.DIALOG, Font.ITALIC, 40));
        contentPanel.add(name);
        contentPanel.add(listScroller);
        contentPanel.revalidate();
        contentPanel.repaint();
        this.add(contentPanel, BorderLayout.CENTER);
        
        this.update();
    }

    public void addUserProfileImage(){
        Image profileImage = Toolkit.getDefaultToolkit().getImage(defaultProfileImagePath);
        Dimension backgroundDimension = new Dimension((int) this.getSize().getWidth(),(int) this.getSize().getHeight() / 3);
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(defaultProfileImagePath));
            profileImage  = bufferedImage.getScaledInstance((int)backgroundDimension.getWidth(),(int) backgroundDimension.getHeight(), Image.SCALE_SMOOTH);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        backGroundPanel = new BackGroundPanel(profileImage);
        
        backGroundPanel.setPreferredSize(backgroundDimension);
        backGroundPanel.setSize(backgroundDimension);
        
        String iconPath = UsserButton.iconPath;
        ImageIcon icon = new ImageIcon(new ImageIcon(iconPath).getImage().getScaledInstance(USERICON_WIDTH, USERICON_HEIGHT, Image.SCALE_SMOOTH));
        userIcon.setIcon(icon);

        backGroundPanel.add(userIcon);
        
        contentPanel.add(backGroundPanel);
    }


    public void updateVisited() throws SQLException{
        owned = true;
        name.setText(Connector.USERNAME);
        this.updateUserProfileImage(Connector.USERNAME);
       
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
                    UserSettingsMenu.this.locationDescriptionDialog.descriptionTextArea.setEditable(true);
                    UserSettingsMenu.this.locationDescriptionDialog.updateLocation.setVisible(true);
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
        this.update();
    }

    public void showOtherUser(String username) throws SQLException{
        name.setText(username);
        owned = false;
        this.updateUserProfileImage(username);
        

        //upload other user profile picture and so on
        Vector <Location> visitedLocations = Connector.getOtherVisitedLocations(username); 

       
        
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
                    UserSettingsMenu.this.locationDescriptionDialog.descriptionTextArea.setEditable(false);
                    UserSettingsMenu.this.locationDescriptionDialog.updateLocation.setVisible(false);
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
        this.update();
        this.setVisible(true);
    }

    public void updateUserProfileImage(String username){

            if((new File("UserPhotos\\" + username + "\\Background.jpg")).exists())
            {
                String profileImagePath = "UserPhotos\\" + username + "\\Background.jpg";
                Image profileImage = Toolkit.getDefaultToolkit().getImage(profileImagePath);
                Dimension backgroundDimension = new Dimension((int) this.getSize().getWidth(),(int) this.getSize().getHeight() / 3);
                try {
                    BufferedImage bufferedImage = ImageIO.read(new File(profileImagePath));
                    profileImage  = bufferedImage.getScaledInstance((int)backgroundDimension.getWidth(),(int) backgroundDimension.getHeight(), Image.SCALE_SMOOTH);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                backGroundPanel.setImg(profileImage);
            }
            else
            if((new File("UserPhotos\\" + username + "\\Background.png")).exists())
            {
                String profileImagePath = "UserPhotos\\" + username + "\\Background.png";
                Image profileImage = Toolkit.getDefaultToolkit().getImage(profileImagePath);
                Dimension backgroundDimension = new Dimension((int) this.getSize().getWidth(),(int) this.getSize().getHeight() / 3);
                try {
                    BufferedImage bufferedImage = ImageIO.read(new File(profileImagePath));
                    profileImage  = bufferedImage.getScaledInstance((int)backgroundDimension.getWidth(),(int) backgroundDimension.getHeight(), Image.SCALE_SMOOTH);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                backGroundPanel.setImg(profileImage);
            }
            else
            {
                Image profileImage = Toolkit.getDefaultToolkit().getImage(defaultProfileImagePath);
                Dimension backgroundDimension = new Dimension((int) this.getSize().getWidth(),(int) this.getSize().getHeight() / 3);
                try {
                    BufferedImage bufferedImage = ImageIO.read(new File(defaultProfileImagePath));
                    profileImage  = bufferedImage.getScaledInstance((int)backgroundDimension.getWidth(),(int) backgroundDimension.getHeight(), Image.SCALE_SMOOTH);
                } catch (IOException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }
                backGroundPanel.setImg(profileImage);
            }
            if((new File("UserPhotos\\" + username + "\\ProfilePicture.jpg")).exists())
            {  
                String iconPath = "UserPhotos\\" + username + "\\ProfilePicture.jpg";
                ImageIcon icon = new ImageIcon(new ImageIcon(iconPath).getImage().getScaledInstance(USERICON_WIDTH, USERICON_HEIGHT, Image.SCALE_SMOOTH));
                userIcon.setIcon(icon);
            }
            else
            if((new File("UserPhotos\\" + username + "\\ProfilePicture.png")).exists())
            {
                String iconPath = "UserPhotos\\" + username + "\\ProfilePicture.png";
                ImageIcon icon = new ImageIcon(new ImageIcon(iconPath).getImage().getScaledInstance(USERICON_WIDTH, USERICON_HEIGHT, Image.SCALE_SMOOTH));
                userIcon.setIcon(icon);
            }
            else
            {
                String iconPath = UsserButton.iconPath;
                ImageIcon icon = new ImageIcon(new ImageIcon(iconPath).getImage().getScaledInstance(USERICON_WIDTH, USERICON_HEIGHT, Image.SCALE_SMOOTH));
                userIcon.setIcon(icon);  
            }
            userIcon.revalidate();
            userIcon.repaint();

            contentPanel.revalidate();
            contentPanel.repaint();
            this.update();
 
        
    }
}
