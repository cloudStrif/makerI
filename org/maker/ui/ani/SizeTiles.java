package org.maker.ui.ani;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class SizeTiles extends JPanel {

	int x = 0;
	int y = 0;

	public void paintComponent(Graphics g) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {

				g.setColor(new Color(25, 25, 255, 100));
				g.fillRect(x, y, (int) (400 / 10) - 1, (int) (400 / 10) - 1);

				x += (int) (400 / 10);
			}
			x = 0;
			y += (int) (400 / 10);
		}
		x = y = 0;

		g.setColor(new Color(210, 100 , 57, 100));
		g.fillOval(290, 420, 50, 50);
	}
}
