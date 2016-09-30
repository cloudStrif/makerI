package org.maker.levels;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javax.swing.JFileChooser;

public class Importt {
	
	public String chemin ;
	
	public static ArrayList<TilesSprite> liste = new ArrayList<TilesSprite>() ;
	public Importt() {
	}

	public  Grid charger(Grid g) {
		JFileChooser JFC = new JFileChooser(".");
		int returnVal = JFC.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				chemin = JFC.getSelectedFile().getPath();
				System.out.println(chemin);
				File fw = new File(chemin);
				String recup = af(chemin);
				
				
				int inc = 0;

				for (int i = 0; i < Grid.getS(); i++) {
					for (int j = 0; j < Grid.getS2() + 1; j++) {
						if (recup.charAt(inc) != '\n') {
							g.getField()[i][j] = recup.charAt(inc) + "";

							System.out.print(g.getField()[i][j]);
						}
						inc++;
					}
					System.out.println();
				}
			//	g.setPosXinit(g.getPosXinit() + 1);
			//	g.refresh();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
		return g;
	}

	public  String af(String ss) {
		String chaine = "";
		String fichier = ss;
		try {
			InputStream ips = new FileInputStream(fichier);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			while ((ligne = br.readLine()) != null) {
				// System.out.println(ligne);
				chaine += ligne + "\n";
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			return "";
		}
		System.out.println(chaine);
		return chaine;
	}

	public  void record(Grid g, String name) {
		String recording = "";
		for (int i = 0; i < g.getS(); i++) {
			for (int j = 0; j < g.getS2(); j++) {
				recording += g.getField()[i][j];
			}
			recording += "\n";
		}
		write(name, recording);
	}

	public  void write(String nomFic, String texte) {
		// on va chercher le chemin et le nom du fichier et on me tout ca dans
		// un String
		String adressedufichier = System.getProperty("user.dir") + "/" + nomFic;

		// on met try si jamais il y a une exception
		try {
			FileWriter fw = new FileWriter(adressedufichier, true);
			BufferedWriter output = new BufferedWriter(fw);
			output.write(texte);
			output.flush();

			System.out.println("fichier cr");
		} catch (IOException ioe) {
			System.out.print("Erreur : ");
			ioe.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new Importt();
	}
}
