package com.tourer;

import java.awt.Image;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.tourer.gui.*;
import com.tourer.gui.map.GoogleMaps;
import com.tourer.jdbc.*;
import com.tourer.jdbc.JdbcRunner;

import java.awt.CardLayout;

import java.util.*;

import javafx.application.Platform;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.application.Application;

//"vmArgs": "--module-path C:\\Java\\JavaFX\\javafx-sdk-17.0.1\\lib --add-modules javafx.controls,javafx.fxml",

public class App extends Application 
{
    static GradientColor gradientColor = new GradientColor(new Color(100, 232, 222), new Color(138, 84, 235));
    static String logoPath = "C:\\Java\\PI\\demo\\Icons\\Logo.jpg";
    static String errorPath = "C:\\Java\\PI\\demo\\Icons\\Error.png";
    static final ImageIcon errorIcon = new ImageIcon(new ImageIcon(errorPath).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
    
    public static MainFrame mainFrame = new MainFrame(gradientColor);
    static{
        mainFrame.setVisible(false);
        UIManager.put("OptionPane.errorIcon", errorIcon);
        UIManager.put("OptionPane.background", gradientColor.getSecondaryColor());
        UIManager.put("Panel.background", gradientColor.getSecondaryColor());
        UIManager.put("Button.background", Color.orange);
        UIManager.put("Button.foreground", Color.white);
        UIManager.put("OptionPane.messageForeground", Color.orange);
    }

    public static boolean checkUserExistance(String username, String password){

        
        if(username.equals(""))
            return false;

        if(password.equals(""))
            return false;
        
        try {
            return Connector.checkUserExistance(username, password);
        } catch (SQLException e) {
            return false;
            
        }  
        
    }

    public static ButtonBox buttonBox;
    public static void initAndShowGUI(){
        ColorPanel menu = new ColorPanel();

        ColorPanel searchPanel = new ColorPanel();
        searchPanel.setLayout(new CardLayout());
        LocationSearchField locationSearchField = new LocationSearchField();
        searchPanel.add(locationSearchField);
        UserSearchField userSearchField = new UserSearchField();
        userSearchField.addItem("Ana");
        userSearchField.addItem("Andreea");
        searchPanel.add(userSearchField);

        buttonBox = new ButtonBox(locationSearchField, userSearchField);
        
        JFXPanel fxpanel = new JFXPanel();
        menu.add(buttonBox);
        mainFrame.add(searchPanel, BorderLayout.NORTH);
        mainFrame.add(menu, BorderLayout.WEST);
        mainFrame.add(fxpanel, BorderLayout.CENTER);

        Platform.runLater(new Runnable() {
            @Override
            public void run()
                {
                    WebEngine engine;
                    WebView view = new WebView();
                    engine = view.getEngine();
                    fxpanel.setScene(new Scene(view));
                    engine.load("file:///C:\\Java\\PI\\demo\\JavaScript\\test.html");
                
                    mainFrame.add(fxpanel, BorderLayout.CENTER);
                }
        });

    }
    
    public static void main( String[] args ) throws IOException{
        
        try{
            JdbcRunner.buildJdbc();
            System.out.println("Successfully conected to server");
        }catch(SQLException e1){
            e1.printStackTrace();
            System.exit(1);
        }
        
        JFrame loginFrame = new JFrame();
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SpringLayout springLayout = new SpringLayout();
        ColorPanel contentPane = new ColorPanel();

        contentPane.setLayout(springLayout);
        loginFrame.setSize(new Dimension((MainFrame.screenSize.width * 3) / 4 , (MainFrame.screenSize.height * 3) / 4));
        loginFrame.setContentPane(contentPane);

        
        JLabel label = new JLabel("UserName");
        label.setForeground(Color.ORANGE);
        label.setFont(new Font(Font.DIALOG, Font.BOLD, 50));
        JTextField userNameTextField = new JTextField();
        userNameTextField.setBorder(AccountCreationFrame.BLACK_BORDER);
        label.setFont(new Font(AppSettingsMenu.fontStyle, AppSettingsMenu.fontType, AppSettingsMenu.textSize));
        contentPane.add(label);
        contentPane.add(userNameTextField);
        ImageIcon logo = new ImageIcon(new ImageIcon(logoPath).getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH));
        JLabel logoLabel = new JLabel();
        logoLabel.setIcon(logo);
        JLabel titleLabel = new JLabel("Timisoara Tourer");
        titleLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 100));
        titleLabel.setForeground(Color.ORANGE);
        contentPane.add(titleLabel);
        contentPane.add(logoLabel);
        springLayout.putConstraint(SpringLayout.NORTH, titleLabel, 40, SpringLayout.NORTH, contentPane);
        springLayout.putConstraint(SpringLayout.WEST, titleLabel, 50, SpringLayout.WEST, contentPane);
        springLayout.putConstraint(SpringLayout.NORTH, logoLabel, 10, SpringLayout.NORTH, contentPane);
        springLayout.putConstraint(SpringLayout.EAST, logoLabel, -10, SpringLayout.EAST, contentPane);

        springLayout.putConstraint(SpringLayout.NORTH, label, 250, SpringLayout.NORTH, contentPane);
        springLayout.putConstraint(SpringLayout.WEST, label, 200, SpringLayout.WEST, contentPane);
        
        springLayout.putConstraint(SpringLayout.WEST, userNameTextField, 25, SpringLayout.EAST, label);
        springLayout.putConstraint(SpringLayout.NORTH, userNameTextField, 260, SpringLayout.NORTH, contentPane);
        springLayout.putConstraint(SpringLayout.EAST, userNameTextField, -200, SpringLayout.EAST, contentPane);
       

        JLabel label2 = new JLabel("Password");
        label2.setForeground(Color.ORANGE);
        label2.setFont(new Font(Font.DIALOG, Font.BOLD, 200));
        JPasswordField passwordTextField = new JPasswordField();
        passwordTextField.setBorder(AccountCreationFrame.BLACK_BORDER);
        contentPane.add(label2);
        contentPane.add(passwordTextField);
        label2.setFont(new Font(AppSettingsMenu.fontStyle, AppSettingsMenu.fontType, AppSettingsMenu.textSize));
        springLayout.putConstraint(SpringLayout.WEST, label2, 200, SpringLayout.WEST, contentPane);
        springLayout.putConstraint(SpringLayout.NORTH, label2, 100, SpringLayout.NORTH, label);
        springLayout.putConstraint(SpringLayout.WEST, passwordTextField, 25, SpringLayout.EAST, label2);
        springLayout.putConstraint(SpringLayout.EAST,  passwordTextField, -200, SpringLayout.EAST, contentPane);
        springLayout.putConstraint(SpringLayout.NORTH,  passwordTextField, 80, SpringLayout.SOUTH, label);
        loginFrame.setLocationRelativeTo(null);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(Color.orange);
        loginButton.setForeground(Color.white);
        loginButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userNameTextField.getText();
                String password = new String(passwordTextField.getPassword());

                boolean ok = App.checkUserExistance(username, password);
                if(ok == true){
                    loginFrame.setVisible(false);
                    mainFrame.setVisible(true);
                    mainFrame.update(); 
                }
                else
                {
                    userNameTextField.setBorder(AccountCreationFrame.RED_BORDER);
                    passwordTextField.setBorder(AccountCreationFrame.RED_BORDER);
                    JOptionPane.showMessageDialog(loginFrame, "Wrong username or password", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }

        });


        userNameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    passwordTextField.requestFocus();
                }
            }
    
        });

        passwordTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    loginButton.doClick();
                }
            }
    
        });

        AccountCreationFrame accountCreationFrame = new AccountCreationFrame();
        ButtonSettings.cardDataDialog = new CardDataDialog(accountCreationFrame);

        JButton createAccountButton = new JButton("Create account");
        createAccountButton.setBackground(Color.orange);
        createAccountButton.setForeground(Color.white);
        createAccountButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                    accountCreationFrame.setVisible(true);

            }

        });

        contentPane.add(createAccountButton);
        contentPane.add(loginButton);

        springLayout.putConstraint(SpringLayout.NORTH, loginButton, 75, SpringLayout.SOUTH, label2);
        springLayout.putConstraint(SpringLayout.NORTH, createAccountButton, 75, SpringLayout.SOUTH, label2);
        springLayout.putConstraint(SpringLayout.EAST, loginButton, -600, SpringLayout.EAST, contentPane);
        springLayout.putConstraint(SpringLayout.WEST, createAccountButton, 10, SpringLayout.EAST, loginButton);
        
        JLabel passwordReset = new JLabel("Forgot Password");
        contentPane.add(passwordReset);
        springLayout.putConstraint(SpringLayout.NORTH, passwordReset, 25, SpringLayout.SOUTH, loginButton);
        springLayout.putConstraint(SpringLayout.EAST, passwordReset, -450, SpringLayout.EAST, contentPane);
        springLayout.putConstraint(SpringLayout.WEST, passwordReset,  520, SpringLayout.WEST, contentPane);

        loginFrame.setVisible(true);
        launch(App.class, args);
        

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initAndShowGUI();
            }
        });
        
    }

   
}
