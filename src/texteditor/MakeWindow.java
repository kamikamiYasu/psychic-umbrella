/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

/**
 *
 * @author god
 */
public class MakeWindow extends JFrame{
    private Container container = new Container();
    
    MakeWindow(){
        super("名無しのテキスト");
        container.setLayout(new BorderLayout());
    }
    
    
    public void createWindow(){
        
    }
}

class Myframe extends JFrame{
    
    Myframe(){
        this("名無しのテキスト");
        
    }
    Myframe(String title){
        super(title);
        
    }
    public Container getContainer(){
        return this. container;
    }        
}
