package org.maker.ui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import org.maker.levels.Grid;
import org.maker.levels.Importt;
import org.maker.levels.TilesSprite;

/**
 * Menu at the top
 */

public class Menu extends JMenuBar {
	JMenu menu1 = new JMenu("Parameters");
	static JMenuItem item1 = new JMenuItem("import");
	static JMenuItem item2 = new JMenuItem("export");
	static JMenuItem item4 = new JMenuItem("Global view");
	static JMenuItem item5 = new JMenuItem("clear all");
	static JMenuItem item3 = new JMenuItem("Quit the programm");

	JMenu menu2 = new JMenu("Setting");
	static JMenuItem item11 = new JMenuItem("Change BackGround Image ");
	static JMenuItem item22 = new JMenuItem("Grid setting ? ");

	static JMenuItem item33 = new JMenuItem("Animation Gestion");
	static JMenuItem item44 = new JMenuItem("Tile Spreet SHEET");
	static JMenuItem item55 = new JMenuItem("Grid Isometric (other project)");

	JMenu menu3 = new JMenu("Undo/redo");
	static JMenuItem item111 = new JMenuItem("UNDO");
	static JMenuItem item222 = new JMenuItem("REDO");

	public boolean grid = true;

	public Menu() {

	}

	public Menu(final Content p, final Window win) {
		super();
		setBorder(BorderFactory.createCompoundBorder(this.getBorder(),
				BorderFactory.createEmptyBorder(4, 0, 4, 0)));

		menu1.setMnemonic(KeyEvent.VK_P);
		item1.setMnemonic(KeyEvent.VK_I);
		item2.setMnemonic(KeyEvent.VK_E);
		item3.setMnemonic(KeyEvent.VK_Q);
		menu1.add(item1);

		menu1.add(item2);
		menu1.add(item5);
		menu1.addSeparator();
		menu1.add(item3);

		menu2.add(item11);
		menu2.add(item22);
		menu2.add(item33);
		menu2.add(item44);
		menu2.add(item55);

		menu3.add(item111);
		menu3.add(item222);
		/*
		 * try { // UIManager.setLookAndFeel(
		 * "com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
		 * UIManager.setLookAndFeel
		 * ("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel"); } catch
		 * (ClassNotFoundException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); } catch (InstantiationException e1) { // TODO
		 * Auto-generated catch block e1.printStackTrace(); } catch
		 * (IllegalAccessException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); } catch (UnsupportedLookAndFeelException e1) {
		 * // TODO Auto-generated catch block e1.printStackTrace(); }
		 */
		/*
		 * try { UIManager.setLookAndFeel("Nimbus"); } catch
		 * (InstantiationException e) { } catch (ClassNotFoundException e) { }
		 * catch (UnsupportedLookAndFeelException e) { } catch
		 * (IllegalAccessException e) { } try { for (LookAndFeelInfo info :
		 * UIManager.getInstalledLookAndFeels()) { if
		 * ("Nimbus".equals(info.getName())) {
		 * UIManager.setLookAndFeel(info.getClassName()); break; } } } catch
		 * (Exception e) { // If Nimbus is not available, you can set the GUI to
		 * another look and feel. }
		 */

		this.add(menu1);
		this.add(menu2);
		this.add(menu3);

		item22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (p.isBgrid()) {
					p.setBgrid(false);
				} else {
					p.setBgrid(true);
				}
				p.repaint();
				Toolkit.getDefaultToolkit().sync();
				p.history.CONTENT += "<command >>Grid update! \n";
				p.history.setTexte(p.history.CONTENT);
			}
		});

		item55.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				if (grid) {
					p.sh.inc = p.sh.names.length -1 ; 
					p.history.CONTENT += "Isometric view  \n";
					p.history.setTexte(p.history.CONTENT);
					win.split.setRightComponent(new JScrollPane(win.desktop2));
					win.split2.setDividerLocation(200);
					win.split.setDividerLocation(260);
					//win.north.setVisible(false) ; 
					win.split.updateUI();
					grid = false ;
					
				} else {
					p.sh.inc = 0; 
					p.history.CONTENT += "Grid view ! \n";
					p.history.setTexte(p.history.CONTENT);
					win.split.setRightComponent(new JScrollPane(win.desktop));
					win.split.setDividerLocation(160);
					win.north.setVisible(true) ; 
					win.split.updateUI();
					grid = true; 
				}
			}
		});

		item11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JFileChooser JFC = new JFileChooser("org/maker/ressources/backgrounds");
				int returnVal = JFC.showOpenDialog(null);
				String chemin = "";
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					chemin = JFC.getSelectedFile().getPath();
					System.out.println(chemin);// PATHfor the image
					p.BACKGROUND = chemin;
					p.repaint();
					Toolkit.getDefaultToolkit().sync();
				}
				p.history.CONTENT += "<command >>BACK update! \n" + chemin
						+ "\n";
				p.history.setTexte(p.history.CONTENT);
			}
		});

		item111.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
				KeyEvent.CTRL_MASK));
		item111.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				/*
				 * Grid g = p.mousse.undo.undo(); g.debug(g.getField());
				 * System.out
				 * .println("\n\n"+p.mousse.undo.liste.size()+"   "+p.mousse
				 * .undo.VALUE); p.setGrid(g);
				 */
				p.history.CONTENT += "<command >>UNDO!  \n";
				p.history.setTexte(p.history.CONTENT);

				String[][] tab = new String[100][100];
				if (!p.mousse.undo.field.isEmpty()) {
					tab = p.mousse.undo.undo();

					p.getGrid().setField(tab);
					p.getGrid().refresh();
					p.repaint();
					Toolkit.getDefaultToolkit().sync();
				}
			}
		});

		item1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				charger(p);
			}
		});
		item2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				p.history.CONTENT += "<command >>Loading MAP!  \n";
				p.history.setTexte(p.history.CONTENT);
				JOptionPane jop = new JOptionPane();
				Importt impo = new Importt();

				String nom = jop.showInputDialog(null, "Nom du fichier : ",
						"Information", JOptionPane.QUESTION_MESSAGE);
				if (new File(nom + ".lvl").exists()) {
					new File(nom + ".lvl").delete();
				}
				if (new File(nom + ".tl").exists()) {
					new File(nom + ".tl").delete();
				}

				impo.record(Window.g, nom + ".lvl");
				try {
					ObjectOutputStream o = new ObjectOutputStream(
							new FileOutputStream(nom + ".tl"));
					o.writeObject(p.sprites);
					o.close();
					ObjectOutputStream o2 = new ObjectOutputStream(
							new FileOutputStream(nom + ".tl2"));
					o2.writeObject(p.numberSheet);
					o2.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		
		item55.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T ,
				KeyEvent.CTRL_MASK));
		
		item5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				KeyEvent.CTRL_MASK));
		item3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
				KeyEvent.CTRL_MASK));
		item4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// Later ...
			}
		});
		item5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (p.mousse.gou != null)
					p.mousse.gou.stopAction();

				p.getGrid().clearAll();
				int dev = p.getMario().getPosY();
				int dev2 = p.getMario().getPosX();
				p.getGrid().getField()[dev][dev2] = "4";
				p.getGrid().getField()[dev2 + 1][dev2] = "M";
				p.getGrid().refresh();
				p.repaint();
			}
		});

	}

	public void charger(Content p) {
		Importt impo = new Importt();
		Grid g = impo.charger(Window.g);
		String[] spli = impo.chemin
				.split("." + File.separator + File.separator);
		System.out.println(spli[spli.length - 1].substring(0,
				spli[spli.length - 1].length() - 4) + ".tl" + "     5");

		p.getGrid().setCamera(g.getCamera());
		p.getGrid().setField(g.getField());
		p.getGrid().refresh();
		ArrayList<TilesSprite> liste = new ArrayList<TilesSprite>();
		ArrayList<String> liste2 = new ArrayList<String> () ; 
		try {
			FileChannel inChannel = new RandomAccessFile(impo.chemin.substring(
					0, impo.chemin.length() - 4) + ".tl", "r").getChannel();
			MappedByteBuffer buffer = inChannel.map(
					FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
			byte[] tab = new byte[buffer.remaining()];
			buffer.get(tab);
			ObjectInputStream o = new ObjectInputStream(
					new ByteArrayInputStream(tab));
			liste = (ArrayList<TilesSprite>) (o.readObject());
			inChannel.close();
			o.close();
			
			FileChannel inChannel2 = new RandomAccessFile(impo.chemin.substring(
					0, impo.chemin.length() - 4) + ".tl2", "r").getChannel();
			MappedByteBuffer buffer2 = inChannel2.map(
					FileChannel.MapMode.READ_ONLY, 0, inChannel2.size());
			byte[] tab2 = new byte[buffer2.remaining()];
			buffer2.get(tab2);
			ObjectInputStream o2 = new ObjectInputStream(
					new ByteArrayInputStream(tab2));
			liste2 = (ArrayList<String>) (o2.readObject());
			inChannel2.close();
			o2.close();
		} catch (Exception e) {
			System.out.println(spli[spli.length - 1].substring(0,
					spli.length - 4) + ".tl");
			System.exit(0);
		}
		p.sprites = liste;
		p.numberSheet = liste2 ;
		p.repaint();
		p.mousse.TABLE2 = p.sprites.size();
	}
}
