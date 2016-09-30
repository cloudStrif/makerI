package org.maker.Playable.Controller;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.test.GridIso;

//Another controller for mario (if use the remote Controller)
public class FirstController implements KeyListener {
	GridIso gridiso;

	public FirstController(GridIso gridiso) {
		this.gridiso = gridiso;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		/*if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			System.out.println("<<<<<");
			gridiso.deplacementX += 50;
			gridiso.repaint();
			Toolkit.getDefaultToolkit().sync();
		}*/

		/*if (arg0.getKeyCode() == KeyEvent.VK_UP) {
			gridiso.ZOOM = 2 ; 
			gridiso.repaint();
			Toolkit.getDefaultToolkit().sync();
		}*/
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
