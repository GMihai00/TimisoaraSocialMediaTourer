package com.tourer.gui;

import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class AccountCreationFrame extends JFrame{
    
    public AccountCreationFrame(){

        
        this.setVisible(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension((MainFrame.screenSize.width * 2) / 3 , (MainFrame.screenSize.height * 2) / 3));

        ColorPanel contentPane = new ColorPanel();
        SpringLayout springLayout = new SpringLayout();
        contentPane.setLayout(springLayout);
        this.setContentPane(contentPane);

        // ussername, mail, password, reenter password, mail

        JLabel userIcon = new JLabel();
        ImageIcon icon = new ImageIcon(new ImageIcon(UsserButton.iconPath).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
        userIcon.setIcon(icon);
        contentPane.add(userIcon);
        springLayout.putConstraint(SpringLayout.NORTH, userIcon, 50, SpringLayout.NORTH, contentPane);
        springLayout.putConstraint(SpringLayout.WEST, userIcon, 420, SpringLayout.WEST, contentPane);

        JLabel usernameLabel = new JLabel("UsserName");
        usernameLabel.setForeground(Color.ORANGE);
        usernameLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        contentPane.add(usernameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, usernameLabel, 20, SpringLayout.SOUTH, userIcon);
        springLayout.putConstraint(SpringLayout.WEST, usernameLabel, 120, SpringLayout.WEST, contentPane);

        JTextField usernameTextField = new JTextField();
        contentPane.add(usernameTextField);
        springLayout.putConstraint(SpringLayout.WEST, usernameTextField, 20, SpringLayout.EAST, usernameLabel);
        springLayout.putConstraint(SpringLayout.EAST, usernameTextField, -100, SpringLayout.EAST, contentPane);
        springLayout.putConstraint(SpringLayout.NORTH, usernameTextField, 35, SpringLayout.SOUTH, userIcon);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.ORANGE);
        passwordLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        contentPane.add(passwordLabel);
        springLayout.putConstraint(SpringLayout.NORTH, passwordLabel, 20, SpringLayout.SOUTH, usernameLabel);
        springLayout.putConstraint(SpringLayout.WEST, passwordLabel, 120, SpringLayout.WEST, contentPane);

        JTextField passwordTextField = new JTextField();
        contentPane.add(passwordTextField);
        springLayout.putConstraint(SpringLayout.WEST, passwordTextField, 20, SpringLayout.EAST, passwordLabel);
        springLayout.putConstraint(SpringLayout.EAST, passwordTextField, -100, SpringLayout.EAST, contentPane);
        springLayout.putConstraint(SpringLayout.NORTH, passwordTextField, 35, SpringLayout.SOUTH, usernameLabel);


        JLabel reenterpasswordLabel = new JLabel("Reenter Password");
        reenterpasswordLabel.setForeground(Color.ORANGE);
        reenterpasswordLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        contentPane.add(reenterpasswordLabel);
        springLayout.putConstraint(SpringLayout.NORTH, reenterpasswordLabel, 20, SpringLayout.SOUTH, passwordLabel);
        springLayout.putConstraint(SpringLayout.WEST, reenterpasswordLabel, 120, SpringLayout.WEST, contentPane);

        JTextField reenterpasswordTextField = new JTextField();
        contentPane.add(reenterpasswordTextField);
        springLayout.putConstraint(SpringLayout.WEST, reenterpasswordTextField, 20, SpringLayout.EAST, reenterpasswordLabel);
        springLayout.putConstraint(SpringLayout.EAST, reenterpasswordTextField, -100, SpringLayout.EAST, contentPane);
        springLayout.putConstraint(SpringLayout.NORTH, reenterpasswordTextField, 35, SpringLayout.SOUTH, passwordLabel);


        JLabel mailLabel = new JLabel("Mail");
        mailLabel.setForeground(Color.ORANGE);
        mailLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        contentPane.add(mailLabel);
        springLayout.putConstraint(SpringLayout.NORTH, mailLabel, 20, SpringLayout.SOUTH, reenterpasswordLabel);
        springLayout.putConstraint(SpringLayout.WEST, mailLabel, 120, SpringLayout.WEST, contentPane);

        JTextField mailTextField = new JTextField();
        contentPane.add(mailTextField);
        springLayout.putConstraint(SpringLayout.WEST, mailTextField, 20, SpringLayout.EAST, mailLabel);
        springLayout.putConstraint(SpringLayout.EAST, mailTextField, -100, SpringLayout.EAST, contentPane);
        springLayout.putConstraint(SpringLayout.NORTH, mailTextField, 35, SpringLayout.SOUTH, reenterpasswordLabel);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBackground(Color.orange);
        signUpButton.setForeground(Color.white);
        signUpButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                AccountCreationFrame.this.setVisible(false);
                
            }

        });
        contentPane.add(signUpButton);
        springLayout.putConstraint(SpringLayout.NORTH, signUpButton, 35, SpringLayout.SOUTH, mailLabel);
        springLayout.putConstraint(SpringLayout.WEST, signUpButton, 430, SpringLayout.WEST, contentPane);
        springLayout.putConstraint(SpringLayout.EAST, signUpButton, -450, SpringLayout.EAST, contentPane);

        this.setLocationRelativeTo(null);
    }
}
