package org;

import java.awt.Color;
import java.util.Properties;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.maker.ui.Window;

import com.jtattoo.plaf.bernstein.BernsteinLookAndFeel;

/**
 * 
 * @author ACER03 Main class : this class just call window
 */

public class Main {

	public static void main(String[] args) {
		//JImage im = new JImage();
		try {
			// UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
			Properties props = new Properties();
			props.put("logoString", "my company");
	
			BernsteinLookAndFeel.setCurrentTheme(props);
		
	
            
			UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
			
			
			//UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
			// UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		new Window("");
	}
}
