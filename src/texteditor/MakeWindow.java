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
    public abstract void actionPerformed(ActionEvent e);
    
    public int MenuSwitch(Object o){
        for(int i;i < mi.length;i++){
            if(o.equals(mi[i])) return i;
        }
    } 

}

class FileMenu extends AbstractMenu{
    JTextArea ta = new JTextArea();
     static final String[] itemTitle = {"新規作成","開く","上書き保存","名前をつけて保存","閉じる"};

    public FileMenu(JTextArea ta) {
        super("ファイル", itemTitle);
        this.ta = ta;
        
    }
    public void actionPerformed(ActionEvent e){
        EditFileAccess efa = new EditFileAccess();
        Object o = e.getSource();
        switch(MenuSwitch(o)){
            case 0:
                ta.setText("");
                break;
            case 1:
                ta.setText("");
		efa.fileOpen(ta);
                break;
            case 2:
                if (EditorStatus.FILENAME.equals("")){
				efa.fileSave(ta);
			}else{
				efa.overWrite(ta);
			}
                break;
            case 3:
                efa.fileSave(ta);
                break;
            case 4:
                System.exit(0);
                break;
            default:
                break;
    }
    }
}

class EditMenu extends AbstractMenu{
    
}

class ViewMenu extends AbstractMenu{
    
}

class HelpMenu extends AbstractMenu{
    
}

class EditFileAccess {
	//フィールド
	JFileChooser fc;	//ファイル選択ダイアログ
	
	//コンストラクタ
	EditFileAccess(){
		//ファイル選択ダイアログを作成
		fc = new JFileChooser();
	}
	
	//ファイルを開く処理
	public void fileOpen(JTextArea ta){
		//ファイル選択ダイアログをファイルを開くモードで表示
		fc.showOpenDialog(null);
		//ダイアログで選択されたファイルオブジェクトを取得
		File f = fc.getSelectedFile();
		
		try(BufferedReader br = new BufferedReader(new FileReader(f));){
			
			//ファイルの読み込みに使うStringインスタンス
			String s;
			//読み込み内容がなくなるまで繰り返す
			while ((s = br.readLine()) != null){
				//読み込んだ文字列をテキストエリアに追加
				ta.append(s + '\n');
			}
                        
		}
		
		//例外処理
		catch(IOException e){
			return;
		}
		
		//ファイルのパスを保存用クラスのフィールドに保存
		EditorStatus.FILENAME = f.getPath();
	}
	
	//「名前をつけて保存」処理
	public void fileSave(JTextArea ta){
		//ファイル選択ダイアログをファイルを保存モードで表示
		fc.showSaveDialog(null);
		////ダイアログで選択されたファイルオブジェクトを取得
		File f = fc.getSelectedFile();
		
		try(PrintWriter pw = new PrintWriter(new FileWriter(f,false));){
			//テキストエリアの文字列を取得する
			String s = ta.getText();
			//テキストエリアの文字列を改行で分割する
			String st[] = s.split("\n");
			//分割して出来た配列の個数を取得
			int limit = st.length;
			
			//ループで配列の個数分繰り返す
			for (int i = 0;i < limit;i++){
				//ファイルに一行ずつ書き込む
				pw.println(st[i]);
			}
			
		}
		//例外処理
		catch(IOException e){
			return;
		}
		
		//ファイルのパスを保存用クラスのフィールドに保存
		EditorStatus.FILENAME = f.getPath();
	}
	
	//上書き保存処理
	public void overWrite(JTextArea ta){
		//保存されているファイルのパスからファイルオブジェクトを作成
		File f = new File(EditorStatus.FILENAME);
		
		try{
			//ファイ書き込みのストリームを開く
			PrintWriter pw = new PrintWriter(new FileWriter(f,false));
			
			//テキストエリアの文字列を取得する
			String s = ta.getText();
			//テキストエリアの文字列を改行で分割する
			String st[] = s.split("\n");
			//分割して出来た配列の個数を取得
			int limit = st.length;
			
			//ループで配列の個数分繰り返す
			for (int i = 0;i < limit;i++){
				//ファイルに一行ずつ書き込む
				pw.println(st[i]);
			}
			
			//ストリームを閉じる
			pw.close();
		}
		//例外処理
		catch(IOException e){
			return;
		}
	}
}

class EditorStatus {
	static String FILENAME = "";
}
