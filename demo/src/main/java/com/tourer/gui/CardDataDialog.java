package com.tourer.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.tourer.jdbc.Connector;


public class CardDataDialog extends JDialog{
 
    public static String[] cardTypes= new String[]{"Visa", "MasterCard"};
    public static String[] mounth = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
    public static String[] year = new String[]{"2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032"};
    public CardDataDialog(Frame frame){
        super(frame, "Add card data", ModalityType.APPLICATION_MODAL);
        this.setVisible(false);
        this.setSize(new Dimension((MainFrame.screenSize.width * 3) / 4 , (MainFrame.screenSize.height * 3) / 4));

        GridPanel contentPane = new GridPanel();
        JLabel label = new JLabel("Credit card");
        label.setForeground(Color.orange);
        label.setFont(new Font(AppSettingsMenu.fontStyle, AppSettingsMenu.fontType, AppSettingsMenu.textSize));
        contentPane.addLeft(label);
        SearchField cardPick = new SearchField();
        cardPick.setVisible(true);
        for(String it : cardTypes){
            cardPick.addItem(it);
        }
        contentPane.addRight(cardPick);
        contentPane.addSpacer(Box.createVerticalStrut(20));
        JLabel label2 = new JLabel("Card number");
        label2.setForeground(Color.orange);
        label2.setFont(new Font(AppSettingsMenu.fontStyle, AppSettingsMenu.fontType, AppSettingsMenu.textSize));
        contentPane.addLeft(label2);
        JTextField cardNumberTextField = new JTextField();
        cardNumberTextField.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        contentPane.addRight(cardNumberTextField);
        contentPane.addSpacer(Box.createVerticalStrut(20));
        JLabel label3 = new JLabel("Expiration date");
        label3.setFont(new Font(AppSettingsMenu.fontStyle, AppSettingsMenu.fontType, AppSettingsMenu.textSize));
        label3.setForeground(Color.orange);
        contentPane.addLeft(label3);
        SearchField datePick = new SearchField();
        datePick.setVisible(true);
        for(String it : mounth){
            for(String it2 : year){
                datePick.addItem(it + "/" + it2);
            }
        }
        contentPane.addRight(datePick);
        contentPane.addSpacer(Box.createVerticalStrut(20));
        JLabel label4 = new JLabel("Security Code");
        label4.setFont(new Font(AppSettingsMenu.fontStyle, AppSettingsMenu.fontType, AppSettingsMenu.textSize));
        label4.setForeground(Color.orange);
        contentPane.addLeft(label4);
        JTextField securityCodeTextField = new JTextField();
        securityCodeTextField.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        contentPane.addRight(securityCodeTextField);
        contentPane.addSpacer(Box.createVerticalStrut(20));
        JButton updateCardDataButton = new JButton("Updata card data");

        updateCardDataButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                String cardType = (String) cardPick.getSelectedItem();
                String cardNumber = cardNumberTextField.getText();
                String expriationDate = (String) datePick.getSelectedItem();
                String securityCode = securityCodeTextField.getText();

                Connector.insertCardData(cardType, cardNumber, expriationDate, securityCode);
            }

        });
        contentPane.addRight(updateCardDataButton);
        this.setContentPane(contentPane);
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
}
