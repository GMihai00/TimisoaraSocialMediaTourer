package com.tourer.gui;

import java.awt.Font;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;


public class SearchField extends JComboBox<String>{
    
    Vector <String> latestSearches;
    static Vector <SearchField> allFields = new Vector <SearchField>();
    public boolean hide_flag = false;
    JTextField search;
    public SearchField(){
        this.setVisible(false);
        this.setEditable(true);
        this.setModel(new DefaultComboBoxModel<String>());
        latestSearches = new Vector<String>();
        this.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        search = (JTextField) super.getEditor().getEditorComponent();
        search.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                    EventQueue.invokeLater(new Runnable() {

                        @Override
                        public void run() {
                            String text = SearchField.this.search.getText();
                            if(text.length() == 0){
                                SearchField.super.hidePopup();
                                SearchField.this.setModel(new DefaultComboBoxModel<String>(SearchField.this.latestSearches), "");
                            }
                            else
                            {
                                DefaultComboBoxModel<String> m = SearchField.this.getSuggestedModel(SearchField.this.latestSearches, text);
                                if(m.getSize() == 0 || SearchField.this.hide_flag){
                                    SearchField.super.hidePopup();
                                    SearchField.this.hide_flag = false;
                                }
                                else
                                {
                                    SearchField.this.setModel(m, text);
                                    SearchField.this.showPopup();
                                }
                            }
                            
                        }
                        
                    });
            }
            public void keyPressed(KeyEvent e){
                String text = SearchField.this.search.getText();
                int code = e.getKeyCode();
                if(code == KeyEvent.VK_ENTER){
                    if(!SearchField.this.latestSearches.contains(text)){
                        SearchField.this.latestSearches.addElement(text);
                        Collections.sort(SearchField.this.latestSearches);
                        setModel(SearchField.this.getSuggestedModel(SearchField.this.latestSearches, text), text);
                    }
                    hide_flag = true;
                }
                else
                if(code==KeyEvent.VK_ESCAPE){
                    hide_flag = true;
                }
                else
                if(code==KeyEvent.VK_RIGHT){
                    for(int i = 0; i < SearchField.this.latestSearches.size(); i++){
                        String str = SearchField.this.latestSearches.elementAt(i);
                        if(str.startsWith(text)){
                            SearchField.super.setSelectedIndex(-1);
                            SearchField.this.search.setText(str);
                            return;
                        }
                    }
                }
            }
        });
        this.setModel(new DefaultComboBoxModel<String>(latestSearches), "");
    }
    private void setModel(DefaultComboBoxModel<String> mdl, String str){
        SearchField.super.setModel(mdl);
        SearchField.super.setSelectedIndex(-1);
        SearchField.this.search.setText(str);
    }

    private  DefaultComboBoxModel<String> getSuggestedModel(Vector <String> list, String text){
        DefaultComboBoxModel<String> m = new DefaultComboBoxModel<String>();
        for(String s : list){
            if(s.toLowerCase().contains(text.toLowerCase()))
                m.addElement(s);
        }
        return m;
    }

    @Override
    public Dimension getMaximumSize() {
        Dimension dim = super.getMaximumSize();
        dim.height = getPreferredSize().height;
        dim.width = getPreferredSize().width;
        return dim;
    }

    @Override
    public void addItem(String item){
        latestSearches.add(item);
        this.setModel(new DefaultComboBoxModel<String>(latestSearches), "");
    }
}
