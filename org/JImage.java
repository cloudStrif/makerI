package org;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jtattoo.plaf.aero.AeroLookAndFeel;

/*
 * Image qui s'affiche au debut
 */

public class JImage extends JPanel {
	private JPanel cont = new JPanel();
	public String PATH = "";

	public JImage() {
		JFrame f = new JFrame();
		f.setSize(500, 300);
		/*
		 * Cache la barre du haut
		 */
		f.setUndecorated(true);

		cont.setBackground(Color.white);
		cont.setLayout(new BorderLayout());
		cont.add(this, BorderLayout.CENTER);
		f.setContentPane(cont);
		f.setLocationRelativeTo(null);

		f.setVisible(true);

		try {
			// UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
			Properties props = new Properties();
			props.put("logoString", "my company");
			AeroLookAndFeel.setCurrentTheme(props);
			UIManager
					.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");

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

		String[] sexe = { "Maker I", "Maker II" };
		JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
		/*
		 * String nom = (String) jop.showInputDialog(null,
		 * "quel type de projets ? ", "Differents modes",
		 * JOptionPane.QUESTION_MESSAGE, null, sexe, sexe[1]);
		 */

		File file = new File(".") ;
		String nom = jop.showInputDialog(null,
				""+file.getAbsolutePath().split("\\.")[0],
				"Building Workspace", JOptionPane.QUESTION_MESSAGE);
		if(nom == null) nom = "";
		PATH = file.getAbsolutePath().split("\\.")[0] +"work\\"+nom;
		PATH = nom ;
		System.out.println(PATH);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		f.dispose();
	}

	public void paintComponent(Graphics g) {
		try {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, 500, 300);
			Image image = ImageIO.read(new File(
					"org/maker/ressources/backgrounds/image.jpg"));

			g.drawImage(image, 0, 0, 300, 300, this);
			Image image2 = ImageIO.read(new File(
					"org/maker/ressources/backgrounds/logo.jpg"));

			g.drawImage(image2, 280, 200, this);
		} catch (IOException e) {

		}
	}
	public static void main(String [] args){
		new JImage() ;
	}

}
