package com.tourer.gui;

import javax.swing.border.TitledBorder;
import javax.swing.border.Border;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
public class LanguagePicker extends SearchField{
    
    static Map <String, String> englishToRomanian;
    static Map <String, String> romanianToEnglish;

    static{
        englishToRomanian = new HashMap<String, String>();
        englishToRomanian.put("Window", "Fereastra");
        englishToRomanian.put("Security", "Securitate");
        englishToRomanian.put("Text", "Text");
        englishToRomanian.put("Settings", "Setari");
        englishToRomanian.put("Text Size", "Dimensiunea Textului");
        englishToRomanian.put("Icon Size", "Dimensiunea Iconitelor");
        englishToRomanian.put("Language", "Limba");
        englishToRomanian.put("Dark Mode", "Mod intunecat");
        englishToRomanian.put("Notifications", "Notificari");
        englishToRomanian.put("Resizing", "Redimensionare");
        englishToRomanian.put("Longitude:", "Longitudine:");
        englishToRomanian.put("Latitude:", "Latitudine:");
        englishToRomanian.put("Location name:", "Numele locatiei");
        englishToRomanian.put("Description:", "Descriere:");
        romanianToEnglish = new HashMap<String, String>();
        for(Map.Entry<String, String> entry : englishToRomanian.entrySet()){
            romanianToEnglish.put(entry.getValue(), entry.getKey());
        }

    }
    
    /** 
     * @param text
     * @param language
     * @return String
     */
    public static String getTranslatedVersion(String text, String language){
        
        if(language == null)
            return "-";
        switch(language){
            case "English":
                try{
                    return romanianToEnglish.get(text);
                }catch(Exception e){
                    return "-";
                }
            case "Romana":
                try{
                    return englishToRomanian.get(text);
                }catch (Exception e){
                    return "-";
                }
            default:
                return "-";
        }
    }

    
    /** 
     * @param component
     * @param language
     */
    public static void updateLanguage ( Component component, String language)
    {
        if(component instanceof JComponent){

        
            Border border = ((JComponent) component).getBorder();

            if(border instanceof TitledBorder){
                    String currentText = ((TitledBorder) border).getTitle();
                    String translatedText = getTranslatedVersion(currentText, language);
                    ((TitledBorder) border).setTitle(translatedText);
            }
        }

        if(component instanceof JTextField){
            String currentText = ((JTextField) component).getText();
            String translatedText = getTranslatedVersion(currentText, language);
            ((JTextField) component).setText(translatedText);
        }
        else
        if(component instanceof JButton)
        {
            String currentText = ((JButton) component).getText();
            String translatedText = getTranslatedVersion(currentText, language);
            ((JButton) component).setText(translatedText);
        }
        else
        if(component instanceof JLabel)
        {   
            String currentText = ((JLabel) component).getText();
            String translatedText = getTranslatedVersion(currentText, language);
            ((JLabel) component).setText(translatedText);
        }
        else
        if(component instanceof CheckBox){
            String currentText = ((CheckBox) component).getText();
            String translatedText = getTranslatedVersion(currentText, language);
            ((CheckBox) component).setText(translatedText);
        }
        else
        if ( component instanceof Container )
        {
            for ( Component child : ( ( Container ) component ).getComponents () )
            {
                updateLanguage ( child, language );
            }
        }
    }
    public LanguagePicker(){
        
        this.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                String language = (String) LanguagePicker.this.getSelectedItem();
                updateLanguage(ButtonBox.userSettingsMenu, language);
                updateLanguage(ButtonBox.appMenuSettings, language);
                //bug when initializing windows
                //updateLanguage(AddButton.addLocationDialog, language);
            }

        });
    }
}
