package org.maker.levels;

import java.awt.Cursor;
import java.awt.Toolkit;

import org.maker.ui.Content;

public class Tiles implements Runnable {
	public boolean mode = true;
	String tile;
	Content p;
	Texte texte;
	int GRID = 0;

	public Tiles(Content p) {
		this.p = p;
	}

	@Override
	public void run() {
		if (GRID == 0) {
			mode = false;
			int velocity = 15;
			if (p.tilepos == 0) {
				while (p.tilepos != -800) {
					p.tilepos -= 10;
					p.repaint();
					Toolkit.getDefaultToolkit().sync();

					try {
						Thread.sleep(velocity);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				p.diversecran = 0;
				texte.stop = true;
				p.ecrire = false;
				mode = true;
				p.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				p.repaint();
				Toolkit.getDefaultToolkit().sync();
				mode = true;
				texte = new Texte(
						"Great ! You have selected a portion of tile!", p,
						false);
				texte.ecrire();
				return;
			}

			if (p.tilepos < 0) {
				while (p.tilepos != 0) {

					p.tilepos += 10;
					p.repaint();
					Toolkit.getDefaultToolkit().sync();

					try {
						Thread.sleep(velocity);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				p.diversecran = 1;
				p.repaint();
				Toolkit.getDefaultToolkit().sync();
				mode = true;
				p.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
				texte = new Texte("org/maker/ressources/tuto/tuto.txt", p, true);

				texte.ecrire();

				return;
			}
		}
		// TODO Auto-generated method stub

	}
}
