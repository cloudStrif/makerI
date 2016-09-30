package org.maker.Playable.Modele;

import java.awt.Color;
import java.awt.Graphics;

import org.maker.ui.Content;

public class Modele extends Content {
	/*
	 * Example JPanel test
	 */
	public void paintComponent(Graphics g) {
		g.setColor(new Color(100, 120, 200));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
}
