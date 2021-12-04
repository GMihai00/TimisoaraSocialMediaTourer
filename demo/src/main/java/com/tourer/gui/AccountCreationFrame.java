package com.tourer.gui;

import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultEditorKit.PasteAction;

public class AccountCreationFrame extends JFrame{
    
    
    public final static LineBorder BLACK_BORDER = new LineBorder(Color.BLACK, 4);
    public final static LineBorder RED_BORDER = new LineBorder(Color.RED, 4);
    public final static Pattern USERNAME_PATTERN = Pattern.compile("^.{6,}$");
    //Minimum eight characters, at least one letter, one number and one special character
    public final static Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");
    public final static Pattern MAIL_PATTERN = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

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
        

        JTextField usernameTextField = new JTextField();
        usernameTextField.setBorder(BLACK_BORDER);
        contentPane.add(usernameTextField);
        springLayout.putConstraint(SpringLayout.WEST, usernameTextField, 400, SpringLayout.WEST, contentPane);
        springLayout.putConstraint(SpringLayout.EAST, usernameTextField, -100, SpringLayout.EAST, contentPane);
        springLayout.putConstraint(SpringLayout.NORTH, usernameTextField, 35, SpringLayout.SOUTH, userIcon);
        springLayout.putConstraint(SpringLayout.EAST, usernameLabel, -20, SpringLayout.WEST, usernameTextField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.ORANGE);
        passwordLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        contentPane.add(passwordLabel);
        springLayout.putConstraint(SpringLayout.NORTH, passwordLabel, 20, SpringLayout.SOUTH, usernameLabel);

        JPasswordField passwordTextField = new JPasswordField();
        passwordTextField.setBorder(BLACK_BORDER);
        contentPane.add(passwordTextField);
        springLayout.putConstraint(SpringLayout.WEST, passwordTextField, 400, SpringLayout.WEST, contentPane);
        springLayout.putConstraint(SpringLayout.EAST, passwordTextField, -100, SpringLayout.EAST, contentPane);
        springLayout.putConstraint(SpringLayout.NORTH, passwordTextField, 35, SpringLayout.SOUTH, usernameLabel);
        springLayout.putConstraint(SpringLayout.EAST, passwordLabel, -20, SpringLayout.WEST, passwordTextField);

        JLabel reenterpasswordLabel = new JLabel("Reenter Password");
        reenterpasswordLabel.setForeground(Color.ORANGE);
        reenterpasswordLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        contentPane.add(reenterpasswordLabel);
        springLayout.putConstraint(SpringLayout.NORTH, reenterpasswordLabel, 20, SpringLayout.SOUTH, passwordLabel);

        JPasswordField reenterpasswordTextField = new JPasswordField();
        reenterpasswordTextField.setBorder(BLACK_BORDER);
        contentPane.add(reenterpasswordTextField);
        springLayout.putConstraint(SpringLayout.WEST, reenterpasswordTextField, 400, SpringLayout.WEST, contentPane);
        springLayout.putConstraint(SpringLayout.EAST, reenterpasswordTextField, -100, SpringLayout.EAST, contentPane);
        springLayout.putConstraint(SpringLayout.NORTH, reenterpasswordTextField, 35, SpringLayout.SOUTH, passwordLabel);
        springLayout.putConstraint(SpringLayout.EAST, reenterpasswordLabel, -20, SpringLayout.WEST, reenterpasswordTextField);

        JLabel mailLabel = new JLabel("Mail");
        mailLabel.setForeground(Color.ORANGE);
        mailLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        contentPane.add(mailLabel);
        springLayout.putConstraint(SpringLayout.NORTH, mailLabel, 20, SpringLayout.SOUTH, reenterpasswordLabel);
        

        JTextField mailTextField = new JTextField();
        mailTextField.setBorder(BLACK_BORDER);
        
        contentPane.add(mailTextField);
        springLayout.putConstraint(SpringLayout.WEST, mailTextField, 400, SpringLayout.WEST, contentPane);
        springLayout.putConstraint(SpringLayout.EAST, mailTextField, -100, SpringLayout.EAST, contentPane);
        springLayout.putConstraint(SpringLayout.NORTH, mailTextField, 35, SpringLayout.SOUTH, reenterpasswordLabel);
        springLayout.putConstraint(SpringLayout.EAST,  mailLabel, -20, SpringLayout.WEST, mailTextField);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBackground(Color.orange);
        signUpButton.setForeground(Color.white);
        signUpButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean ok = true;
                String username = usernameTextField.getText();
                Matcher userMatcher = USERNAME_PATTERN.matcher(username);
                if(!userMatcher.find())
                {
                    usernameTextField.setBorder(RED_BORDER);
                    ok = false;
                }
                else
                {
                    usernameTextField.setBorder(BLACK_BORDER);
                }

                String password = new String(passwordTextField.getPassword());

                Matcher passwordMatcher = PASSWORD_PATTERN.matcher(password);

                if(!passwordMatcher.find()){
                    passwordTextField.setBorder(RED_BORDER);
                    reenterpasswordTextField.setBorder(RED_BORDER);
                    ok = false;
                }
                else
                {
                    passwordTextField.setBorder(BLACK_BORDER);
                    reenterpasswordTextField.setBorder(BLACK_BORDER);
                }

                String reenterpassword =  new String(reenterpasswordTextField.getPassword());

                if(reenterpassword.equals(password) == false){
                    passwordTextField.setBorder(RED_BORDER);
                    reenterpasswordTextField.setBorder(RED_BORDER);
                    ok = false;
                }
                
                String mail = mailTextField.getText();
                Matcher mailMatcher = MAIL_PATTERN.matcher(mail);

                if(!mailMatcher.find()){
                    mailTextField.setBorder(RED_BORDER);
                    ok = false;
                }
                else
                {
                    mailTextField.setBorder(BLACK_BORDER);
                }


                if(ok == true)
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
