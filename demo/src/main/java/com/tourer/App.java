package com.tourer;

import java.awt.Desktop;
import java.awt.BorderLayout;
import java.awt.Color;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import com.tourer.gui.*;
import com.tourer.gui.map.GoogleMaps;
import com.tourer.jdbc.*;
import com.tourer.jdbc.JdbcRunner;

import java.awt.CardLayout;

import java.util.*;

import javafx.application.Platform;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

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
        
    static MainFrame mainFrame = new MainFrame(gradientColor);
    public static ButtonBox buttonBox;
    public static void initAndShowGUI(){
        ColorPanel menu = new ColorPanel();

        ColorPanel searchPanel = new ColorPanel();
        searchPanel.setLayout(new CardLayout());
        LocationSearchField locationSearchField = new LocationSearchField();
        locationSearchField.addItem("Centru");
        locationSearchField.addItem("Primarie");
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
       
        mainFrame.update();     
    }
    
    public static void main( String[] args ) throws IOException{
        
        launch(args);
        // SwingUtilities.invokeLater(new Runnable() {
        //     @Override
        //     public void run() {
        //         initAndShowGUI();
        //     }
        // });
        // try{
        //     JdbcRunner.buildJdbc();
        // }catch(SQLException e1){
        //     e1.printStackTrace();
        //     System.exit(1);
        // }
        
            
        
       
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
