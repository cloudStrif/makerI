package org.maker.levels;

import java.awt.Toolkit;

import org.maker.ui.Content;

public class Texte {
	String fichier;
	Content pan;
	boolean stop =false ;
	Importt impor=new Importt();
	public Texte(String str, Content pan,boolean bool) {
		
		this.pan = pan;
		if(bool)
			fichier=impor.af(str);
		else
			fichier = str;
	}

	public void ecrire() {
		String f = this.fichier;
		pan.ecrire = true;

		// Texte a droite

		pan.TEXTE = "";
		String cont = "";

		for (int i = 0; i < f.length(); i++) {
			cont += f.charAt(i);
			pan.TEXTE = cont;
			pan.repaint();
			Toolkit.getDefaultToolkit().sync();
			if(this.stop){
				pan.TEXTE = "" ;
				return ;
			}

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		pan.ecrire = false;

	}
}
