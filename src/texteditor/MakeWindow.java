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

class EditorMenu extends JMenuBar{
    
}

abstract class AbstractMenu extends JMenu implements ActionListener{
    JMenuItem[] mi;

    public AbstractMenu(String menuTitle, String[] itemTitle) {
        super(menuTitle);
        for(int i = 0;i < mi.length;i++){
            mi[i] = new JMenuItem(itemTitle[i]);
            mi[i].addActionListener(this);
            super.add(mi[i]);
        }
    }
    

}

class FileMenu extends AbstractMenu{
    
}

class EditMenu extends AbstractMenu{
    
}

class ViewMenu extends AbstractMenu{
    
}

class HelpMenu extends AbstractMenu{
    
}