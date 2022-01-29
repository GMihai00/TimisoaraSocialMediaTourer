package com.tourer.jdbc;

import javax.mail.PasswordAuthentication;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
    protected static String MAIL = "mihai.gherghinescu18@gmail.com";
    protected static String PASSWORD = "Calebbb1234567890";
    protected static Properties properties = new Properties();
    protected static Session session;
    static{
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(MAIL, PASSWORD);
            } 
     });
    }
    
    /** 
     * @param recipient
     * @throws MessagingException
     */
    public static void sendEmail(String recipient) throws MessagingException{
       
        Message message = createMessage(recipient);
        Transport.send(message);
    }
    
    /** 
     * @param recipient
     * @return Message
     */
    private static Message createMessage(String recipient) {
       
        try {
            String template = "Hello, thank you for creating a new account for Timisoara Tourer\nWe hope that the app will give you a better look of Timisoara and you'll enjoy using it\n";
            try {
                StringBuilder stringBuilder = new StringBuilder("");
                File myObj = new File("Email\\Template.mail.html");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    stringBuilder.append(data);
                }
                myReader.close();
                template = stringBuilder.toString();
                } catch (FileNotFoundException e) {
                    
                    e.printStackTrace();
                }
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(MAIL));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("New account for Timisoara Tourer");
            message.setContent(template, "text/html; charset=utf-8");

            return message;
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return null;
    }
}
