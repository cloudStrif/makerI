package org.maker.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.maker.levels.Grid;

public class SpriteSheet extends JPanel {
	/**
	 * Tiles Gestion
	 */
	Content p;
	String name = "Icy_Tiles.png";
	public String [] names = {"Icy_Tiles.png" , "Plain_Tiles.png","tiles3.png",/*thing to do here*/ "iso.png","Terrain_tileset.png","grass_and_water.png"} ; 
	public int inc = 0 ; 
	public SpriteSheet(Content p) {
		this.p = p;
	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight()) ; 
		ImageIcon fond = new ImageIcon(Push.PATH + "tiles/"+names[inc]);
		// ImageIcon fond = new ImageIcon("web/b.jpg");
		Image fond2 = fond.getImage();
		g.drawImage(fond2, 0, 0, this);

		// Draw Selected tile
		if (p.sprites.size() != 0) {
			g.setColor(Color.RED);
			g.drawRect(p.sprites.get(p.sprites.size() - 1).getPocX(), p.sprites
					.get(p.sprites.size() - 1).getPocY(),
					p.sprites.get(p.sprites.size() - 1).getPocZ()
							- p.sprites.get(p.sprites.size() - 1).getPocX(),
					p.sprites.get(p.sprites.size() - 1).getPocT()
							- p.sprites.get(p.sprites.size() - 1).getPocY());
		}
	}
	
	
}
