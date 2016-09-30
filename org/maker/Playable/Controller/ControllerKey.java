package org.maker.Playable.Controller;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.maker.levels.Gravity;
import org.maker.ui.Content;
import org.maker.ui.Window;

/**
 * Basic controller of Main character
 */

public class ControllerKey implements KeyListener {
	Window win;
	Content con;

	public ControllerKey(Window win, Content con) {
		this.win = win;
		this.con = con;
		Thread thread = new Thread(new Gravity(win));
		// thread.start() ;
	}

	public ControllerKey() {

	}

	public void keyPressed(KeyEvent arg0) {

		if (arg0.getKeyCode() == KeyEvent.VK_SPACE) {
			con.getGravity().Active = true;
		}
	

		win.getMario().setMario(true);
		con.getKeys()[arg0.getKeyCode()] = true;
		this.con.repaint();
		Toolkit.getDefaultToolkit().sync();
		
		
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		con.getKeys()[arg0.getKeyCode()] = false;
		win.getMario().actual = win.getMario().datas.rightW.get(0);
		this.con.repaint();
		Toolkit.getDefaultToolkit().sync();
		
		if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
			con.history.CONTENT += "LEFT MOVE! \n";
			con.history.setTexte(con.history.CONTENT);
		}
		if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			con.history.CONTENT += "RIGHT MOVE! \n";
			con.history.setTexte(con.history.CONTENT);
		}
		if (arg0.getKeyCode() == KeyEvent.VK_UP) {
			con.history.CONTENT += "UP MOVE! \n";
			con.history.setTexte(con.history.CONTENT);
		}
		if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
			con.history.CONTENT += "DOWN MOVE! \n";
			con.history.setTexte(con.history.CONTENT);
		}
		if (arg0.getKeyCode() == KeyEvent.VK_SPACE) {
			con.history.CONTENT += "FIREBALL! \n";
			con.history.setTexte(con.history.CONTENT);
		}
		
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
