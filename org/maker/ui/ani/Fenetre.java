package org.maker.ui.ani;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import org.maker.levels.Grid;
import org.maker.levels.Importt;
import org.maker.levels.TilesSprite;
import org.maker.ui.Content;
import org.maker.ui.Window;

/**
 * 
 * @author ACER03 Test file for E-learning
 */

public class Fenetre extends JFrame implements TreeSelectionListener {
	private JTree arbre;
	private DefaultMutableTreeNode racine;
	private DefaultTreeModel model;
	private JButton bouton = new JButton("Add a new");
	private JButton bouton2 = new JButton("Generate");
	private JButton bouton3 = new JButton("<<");
	private JButton bouton4 = new JButton(">>");
	private JButton bouton5 = new JButton("New");
	
	public String PATH = System.getProperty("user.dir") + File.separator + "work";
	public Content p ; 

	public Fenetre(Content con) {
		this.p=con; 
		this.setSize(800, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setTitle("JTree");
		// On invoque la méthode de construction de l'arbre

		listRoot();

		// On crée, avec notre hiérarchie, un arbre

		bouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(!new File(PATH).isFile()){
				if (arbre.getLastSelectedPathComponent() != null) {
					JOptionPane jop = new JOptionPane();
					String nodeName = jop
							.showInputDialog("Saisir un nom de noeud");

					p.history.CONTENT += "<command >>new level created! \n";
					p.history.setTexte(p.history.CONTENT);
					
					if(PATH.equals("work")){
						if (nodeName != null && !nodeName.trim().equals("")) {
							System.out.println("touche");
							DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) arbre
									.getLastSelectedPathComponent();
							DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(
									nodeName);
							parentNode.add(childNode);
							model.insertNodeInto(childNode, parentNode,
									parentNode.getChildCount() - 1);
							model.nodeChanged(parentNode);
						}
						File file = new File("work"+File.separator+nodeName);
						file.mkdir() ; 
					}else{
						DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) arbre
								.getLastSelectedPathComponent();
						DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(
								nodeName+".lvl");
						if (!new File(PATH+File.separator+nodeName + ".lvl").exists()) {
						parentNode.add(childNode);
						model.insertNodeInto(childNode, parentNode,
								parentNode.getChildCount() - 1);
						model.nodeChanged(parentNode);
						}
						/*
						DefaultMutableTreeNode parentNode2 = (DefaultMutableTreeNode) arbre
								.getLastSelectedPathComponent();
						DefaultMutableTreeNode childNode2 = new DefaultMutableTreeNode(
								nodeName+".tl");
						parentNode.add(childNode2);
						model.insertNodeInto(childNode2, parentNode2,
								parentNode2.getChildCount() - 1);
						model.nodeChanged(parentNode2);*/
						
						System.out.println("TESTTEST");
						Importt impo = new Importt();
						if (new File(PATH+File.separator+nodeName + ".lvl").exists()) {
							new File(PATH+File.separator+nodeName + ".lvl").delete();
						}
						if (new File(PATH+File.separator+nodeName + ".tl").exists()) {
							new File(PATH+File.separator+nodeName + ".tl").delete();
						}
						impo.record(Window.g, PATH+File.separator+nodeName + ".lvl");
						try {
							ObjectOutputStream o = new ObjectOutputStream(
									new FileOutputStream(PATH+File.separator+nodeName + ".tl"));
							o.writeObject(p.sprites);
							o.close();
							ObjectOutputStream o2 = new ObjectOutputStream(
									new FileOutputStream(PATH+File.separator+nodeName + ".tl2"));
							o2.writeObject(p.numberSheet);
							o2.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} else {
					System.out.println("Aucune sélection !");
				}
				}else{
					JOptionPane jop = new JOptionPane();
					jop.showMessageDialog(null, "Must select a file ! ",
							"Informations",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		bouton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				p.history.CONTENT += "<command >>loading MAP!\n";
				p.history.setTexte(p.history.CONTENT);
				if(new File(PATH).isFile()){
					Importt impo = new Importt();
					Grid g =new Grid(100,100);
					
					String recup = new Importt().af(PATH);
					
					
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
					
					  p.getGrid().setCamera(g.getCamera());
					  p.getGrid().setField(g.getField()); p.getGrid().refresh();
					  ArrayList<TilesSprite> liste = new ArrayList<TilesSprite>();
					  ArrayList<String> liste2 = new ArrayList<String>() ; 
					  try { 
						  FileChannel inChannel = new
								  RandomAccessFile(PATH.substring(0,PATH.length()-4)+".tl", "r").getChannel(); MappedByteBuffer buffer =
								  inChannel.map( FileChannel.MapMode.READ_ONLY, 0,
										  inChannel.size()); byte[] tab = new byte[buffer.remaining()];
										  buffer.get(tab); ObjectInputStream o = new ObjectInputStream(
												  new ByteArrayInputStream(tab)); liste =
												  (ArrayList<TilesSprite>) (o.readObject()); inChannel.close();
												  o.close();
					  
													FileChannel inChannel2 = new RandomAccessFile(PATH.substring(0,PATH.length()-4)+".tl2", "r").getChannel();
													MappedByteBuffer buffer2 = inChannel2.map(
															FileChannel.MapMode.READ_ONLY, 0, inChannel2.size());
													byte[] tab2 = new byte[buffer2.remaining()];
													buffer2.get(tab2);
													ObjectInputStream o2 = new ObjectInputStream(
															new ByteArrayInputStream(tab2));
													liste2 = (ArrayList<String>) (o2.readObject());
													inChannel2.close();
													o2.close();
					  } 
					  catch (Exception e) {
						  
					  
					  } 
					  p.sprites = liste;
					  p.numberSheet = liste2 ; 
					  p.repaint();Toolkit.getDefaultToolkit().sync();
					  p.mousse.TABLE2 = p.sprites.size() ;
				}else{
					JOptionPane jop = new JOptionPane();
					jop.showMessageDialog(null, "Must select a file ! ",
							"Informations",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		bouton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(p.sh.inc < p.sh.names.length-1){
					p.sh.inc ++ ; 
					p.sh.repaint();
					Toolkit.getDefaultToolkit().sync();
				}
			}
		});
		bouton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(p.sh.inc > 0){
					p.sh.inc -- ; 
					p.sh.repaint();
					Toolkit.getDefaultToolkit().sync();
				}
			}
		});
		
		JPanel c = new JPanel() ;
		c.add(bouton);
		
		c.add(bouton2);
		JPanel c2 = new JPanel(); 
		c2.add(bouton3);
		c2.add(bouton4);
		c2.add(bouton5);
	
		//this.getContentPane().add(bouton, BorderLayout.SOUTH);
		this.getContentPane().add(c, BorderLayout.NORTH);
		this.getContentPane().add(c2, BorderLayout.SOUTH);

		arbre.setRootVisible(true);
		arbre.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		arbre.setToggleClickCount(1);
		arbre.addTreeSelectionListener((TreeSelectionListener) this);

		// this.setVisible(true);
	}

	public java.awt.Container conte() {
		return this.getContentPane();
	}

	public void find(String chemin) {
		String s = "C:\\";
		File fichier = new File(chemin);
		String list[];
		DefaultMutableTreeNode rep = new DefaultMutableTreeNode(
				fichier.getName());
		if (fichier.isDirectory()) {

			list = fichier.list();
			try {

				for (int i = 0; i < list.length; i++) {
					// m = p.matcher(list[i]);
					// if (m.matches()) {
					// pp = pp + "\n" + chemin + File.separator + list[i];
					// }

					// if (new File(chemin + "\\" + list[i]).isDirectory()) {
					DefaultMutableTreeNode rep2 = new DefaultMutableTreeNode(
							list[i]);
					if(!list[i].endsWith(".tl") && !list[i].endsWith(".tl2"))
						rep.add(rep2);

					// }
					if (!new File(chemin + "\\" + list[i]).isDirectory()) {

						racine.add(rep);
					}
					find(chemin + File.separator + list[i]);

					// DefaultMutableTreeNode node = new
					// DefaultMutableTreeNode(chemin + "\\" + list[i]);

				}
			} catch (Exception exp) {

			}
		}
	}

	private void listRoot() {
		this.racine = new DefaultMutableTreeNode("work");
		// DefaultMutableTreeNode t=new DefaultMutableTreeNode("work");
		// this.racine.add(t);
		int count = 0;

		find("work");

		String s = "C:\\";

		// Nous créons, avec notre hiérarchie, un arbre
		arbre = new JTree();
		this.model = new DefaultTreeModel(this.racine);
		arbre.setModel(model);
		arbre.setRootVisible(true);
		arbre.setEditable(true);
		arbre.setDragEnabled(true);

		arbre.getModel().addTreeModelListener(new TreeModelListener() {
			public void treeNodesChanged(TreeModelEvent evt) {
			/*	System.out.println("Changement dans l'arbre");
				System.out.println(evt.getPath());
				Object[] listNoeuds = evt.getChildren();
				int[] listIndices = evt.getChildIndices();
				for (int i = 0; i < listNoeuds.length; i++) {
					System.out.println("Index " + listIndices[i]
							+ ", noeud déclencheur : " + listNoeuds[i]);
				}*/
			}

			public void treeNodesInserted(TreeModelEvent event) {
				System.out.println("Un noeud a été inséré !");
			}

			public void treeNodesRemoved(TreeModelEvent event) {
				System.out.println("Un noeud a été retiré !");
			}

			public void treeStructureChanged(TreeModelEvent event) {
				System.out.println("La structure d'un noeud a changé !");
			}
		});

		this.getContentPane().add(new JScrollPane(arbre), BorderLayout.CENTER);
	}

	private DefaultMutableTreeNode listFile(File file,
			DefaultMutableTreeNode node) {
		int count = 0;
		if (file.isFile())
			return new DefaultMutableTreeNode(file.getName());
		else {
			File[] list = file.listFiles();
			if (list == null)
				return new DefaultMutableTreeNode(file.getName());

			for (File nom : list) {
				count++;
				// Pas plus de 5 enfants par noeud
				if (count < 3) {
					DefaultMutableTreeNode subNode;
					if (nom.isDirectory()) {
						subNode = new DefaultMutableTreeNode(nom.getName()
								+ File.separator);
						node.add(this.listFile(nom, subNode));
					} else {
						subNode = new DefaultMutableTreeNode(nom.getName());
					}
					node.add(subNode);
				}
			}
			return node;
		}
	}



	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
		// TODO Auto-generated method stub
		if (arbre.getLastSelectedPathComponent() != null) {
			// System.out.println(new
			// File(arbre.getLastSelectedPathComponent().toString()).getAbsolutePath());
			// System.out.println(PATH+"\\"+arbre.getLastSelectedPathComponent().toString());
			System.out.println(arbre.getSelectionPath().getPath()[0]);
			String chemin = "work";
			for (int i = 1; i < arbre.getSelectionPath().getPath().length; i++) {
				chemin += File.separator+ arbre.getSelectionPath().getPath()[i]
						;
			}
			System.out.println(chemin) ;
			PATH = chemin  ; 
			p.history.CONTENT += "selected: "+PATH +"\n";
			p.history.setTexte(p.history.CONTENT);
		}
	}
}