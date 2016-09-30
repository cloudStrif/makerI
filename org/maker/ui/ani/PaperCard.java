package org.maker.ui.ani;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PaperCard extends JFrame {
	public final static int tX = 980+200+150;
	public final static int tY = 640;

	private int positionX = 0;
	private int positionY = 0;

	public PaperCard() {
		setTitle("Maker I");
		setSize(tX, tY);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setUndecorated(true);
		addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				positionX = e.getX(); // Position X de la souris au clic
				positionY = e.getY(); // Position Y de la souris au clic
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				int depX = e.getX() - positionX;
				int depY = e.getY() - positionY;
				setLocation(getX() + depX, getY() + depY);
			}
		});

		setResizable(false);

	}

	public void sizee(int a, int b) {
		setSize(a, b);
		setLocationRelativeTo(null);
	}

	public void addComponent(JPanel p){
		setContentPane(p);
	}
	
	public void visible(boolean b) {
		setVisible(b);
	}

}
