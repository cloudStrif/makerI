package org.maker.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import org.maker.Playable.Vue.Vue;
import org.maker.character.Goumba;
import org.maker.character.Mario;
import org.maker.levels.Grid;
import org.maker.levels.Tiles;
import org.maker.ressources.Boxe;
import org.maker.ressources.Piece;
import org.maker.ui.ani.Fenetre;
import org.maker.ui.ani.PaperCard;
import org.test.GridIso;
import org.test.InteriorGrid;

/**
 * 
 * Main window with the UI 
 *
 */
public class Window extends PaperCard implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JPanel container = new JPanel();
	JLabel label = new JLabel("Basics tiles :  ");
	JTextField jtf;
	public Content p;
	GridIso gridiso ;
	Menu menu;
	String[] tabName = { "brique", "delete", "recule", "avance", "bas", "haut",
			"brique2", "herbe", "bloc", "piece", "goumba", "box" ,"tiles" ,"play","grid"};
	
	
	ArrayList<Push> buttons = new ArrayList<Push>();
	Push p1 = new Push("brique");
	public static Grid g;
	Piece pice;
	Boxe box;
	Tiles tile ;
	private Mario mario; // main character on the piece
	JDesktopPane desktop = new JDesktopPane();
	JDesktopPane desktop2 = new JDesktopPane();
	JSplitPane split2 ;
	JSplitPane split3 ;
	JSplitPane split ;
	public JPanel north ; 
	
	/**
	 * Build basic component to the window
	 */
	public Window(String PATH) {
		for (int i = 0; i < tabName.length; i++) {
			Push pp = new Push(tabName[i]);
			buttons.add(pp);
		}
	
		// Init Grid (use it each time)
		g = new Grid(100, 100);
		setMario(new Mario(1, 6));
		p = new Content(g, getMario());
		menu = new Menu(p,this);
		JPanel south = new JPanel();

		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
		container.add(p, BorderLayout.CENTER);
		
	

		// container.add(south,BorderLayout.SOUTH);
		north = new JPanel();
		north.add(label);
		for (int i = 0; i < tabName.length; i++) {
			buttons.get(i).addActionListener(this);
			north.add(buttons.get(i));
		}
		/*north.remove(buttons.get(0)) ;
		north.updateUI(); */
		
		container.add(north, BorderLayout.NORTH);
		Menu.item3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
				System.exit(0);
			}
		});

		// setContentPane(container);

		// container.add(south,BorderLayout.SOUTH);
		pice = new Piece(1, p);
		box = new Boxe(1, p);

		/*JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				new JScrollPane(new Modele()), new JScrollPane(container));
		split.setDividerLocation(200);
		getContentPane().add(split, BorderLayout.CENTER);*/
		setJMenuBar(menu);
		/*jtf = new JTextField();
		south.add(jtf);
		jtf.setPreferredSize(new Dimension(30, 30));
		jtf.addKeyListener(new ControllerKey(this, p));
		north.add(jtf);*/
		// new Soundd().start();
		tile = new Tiles(p);
		p.history.CONTENT += "==========HISTORY ========\n";
		p.history.setTexte(p.history.CONTENT);
		gridiso = new GridIso(p.mousse) ;
		
		desktop.add(new InteriorComponent(this,p));
		
		desktop2.add(new InteriorGrid(gridiso));
		setContentPane(container);
		
		getContentPane().add(desktop, BorderLayout.CENTER);
	
		p.sh.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		p.sh.setPreferredSize(new Dimension(1100, 1500));
		JScrollPane scrollpan = new JScrollPane(p.sh) ;
		scrollpan.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	    scrollpan.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scrollpan.add(p.sh);
	    scrollpan.setViewportView(p.sh);
		Fenetre arbre = new Fenetre(p) ;
		split2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT , arbre.conte(),scrollpan);
		split2.setDividerLocation(300);
		
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,split2 , new JScrollPane(desktop));
	    //On place le séparateur
	    split.setDividerLocation(160);
	  
	    split3 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,split ,  new JScrollPane(p.history));
	    //On place le séparateur
	    split3.setDividerLocation(1100);
	    this.getContentPane().add(split3, BorderLayout.CENTER);
		setVisible(true);
		
		
		
		
	}

	public void actionPerformed(ActionEvent arg0) {
		getMario().setMario(true);
		// args here later
		if (arg0.getSource() == buttons.get(0)) {
			p.history.CONTENT += "<command >>Blue WALL selected! \n";
			p.history.setTexte(p.history.CONTENT);
			Mousse.TYPE = "B";
			/*north.setVisible(false) ; 
			north.updateUI() ;
		
			/*split.setRightComponent(new JScrollPane(desktop2));
			 split.setDividerLocation(160);
			split.updateUI();*/
		}
		if (arg0.getSource() == buttons.get(1)) {
			p.history.CONTENT += "<command >>Delete elements ! \n";
			p.history.setTexte(p.history.CONTENT);
			Mousse.TYPE = "N";
		}

		// control keys
		if (arg0.getSource() == buttons.get(2)) {
			p.history.CONTENT += "<command >>left move \n";
			p.history.setTexte(p.history.CONTENT);
			if(!menu.grid){
				if (gridiso.grid.getPosXinit() > 0) {
				/*	gridiso.grid.setPosXinit(gridiso.grid.getPosXinit() - 1);
					gridiso.grid.refresh();
					gridiso.repaint();*/
					
				}
				gridiso.deplacementX += 20 ;gridiso.repaint();
			}else{
			getMario().setMario(false);
				if (g.getPosXinit() > 0) {
					g.setPosXinit(g.getPosXinit() - 1);
					g.refresh();
					p.repaint();
				}
			}
		}
		if (arg0.getSource() == buttons.get(3)) {
			p.history.CONTENT += "<command >>Righto ove \n";
			p.history.setTexte(p.history.CONTENT);
			if(!menu.grid){
			
				if (gridiso.grid.getPosXinit() < gridiso.grid.getS2()-1) {
				/*	gridiso.grid.setPosXinit(gridiso.grid.getPosXinit() + 1);
					gridiso.grid.refresh();
					gridiso.repaint();*/
					gridiso.deplacementX -= 20 ;gridiso.repaint();
				}
			}else{
				getMario().setMario(false);
				if (g.getPosXinit() < g.getS2()-1) {
					g.setPosXinit(g.getPosXinit() + 1);
					g.refresh();
					p.repaint();
				}
			}
		}
		
		if (arg0.getSource() == buttons.get(4)) {
			p.history.CONTENT += "<command >>down move ! \n";
			p.history.setTexte(p.history.CONTENT);
			if(!menu.grid){
				if (gridiso.grid.getPosYinit() < gridiso.grid.getS()-1) {
				/*	gridiso.grid.setPosYinit(gridiso.grid.getPosYinit() + 1);
					gridiso.grid.refresh();
					gridiso.repaint();
					*/
					
					gridiso.deplacementY -= 20 ;gridiso.repaint();
				}
			}else{
			getMario().setMario(false);
			if (g.getPosYinit() < g.getS()-1) {
				g.setPosYinit(g.getPosYinit() + 1);
				g.refresh();
				p.repaint();
			}
			}
		}
		if (arg0.getSource() == buttons.get(5)) {
			p.history.CONTENT += "<command >>Top move \n";
			p.history.setTexte(p.history.CONTENT);
			if(!menu.grid){
				/*
				if (gridiso.grid.getPosYinit() > 0) {
					gridiso.grid.setPosYinit(gridiso.grid.getPosYinit() - 1);
					gridiso.grid.refresh();
					gridiso.repaint();
				}*/
				gridiso.deplacementY += 20 ;gridiso.repaint();
			}else{
			getMario().setMario(false);
			System.out.println(g.getS2() +" et " +g.getS());
			if (g.getPosYinit() > 0) {
				g.setPosYinit(g.getPosYinit() - 1);
				g.refresh();
				p.repaint();
			}
			}
			
		}
		if (arg0.getSource() == buttons.get(6)) {
			p.history.CONTENT += "<command >>Red wall \n";
			p.history.setTexte(p.history.CONTENT);
			Mousse.TYPE = "M";
		}
		if (arg0.getSource() == buttons.get(7)) {
			// buttons.get(7).setEnabled(false);
			p.history.CONTENT += "<command >>Tile selected \n";
			p.history.setTexte(p.history.CONTENT);
			Mousse.TYPE = "H";
		}
		if (arg0.getSource() == buttons.get(8)) {
			p.history.CONTENT += "<command >>Big bloc \n";
			p.history.setTexte(p.history.CONTENT);
			Mousse.TYPE = "P";
		}
		if (arg0.getSource() == buttons.get(9)) {
			p.history.CONTENT += "<command >>Piece tile !  \n";
			p.history.setTexte(p.history.CONTENT);
			Mousse.TYPE = Piece.TYPE;
			if (!pice.active) {
				pice.rune();
			}
		}
		if (arg0.getSource() == buttons.get(10)) {
			p.history.CONTENT += "<command >>enemy !  \n";
			p.history.setTexte(p.history.CONTENT);
			Mousse.TYPE = Goumba.TYPE + "";
		}
		if (arg0.getSource() == buttons.get(11)) {
			// This is a box
			Mousse.TYPE = Boxe.TYPE;
			if (!box.active) {
				box.start();
			}
		}
		if (arg0.getSource() == buttons.get(12)) {
			p.history.CONTENT += "<command >>Other Tiles selected ! \n";
			p.history.setTexte(p.history.CONTENT);

				if(tile.mode){
					tile.mode = false;
					new Thread(tile).start() ;
				
				}
		}
		if (arg0.getSource() == buttons.get(13)) {
			//button run
			Content nouveau = new Content(g,getMario()) ;
			nouveau.setGrid(g);
			new Vue(this,nouveau);
		}
		if (arg0.getSource() == buttons.get(14)) {
			if (p.isBgrid()){
				p.setBgrid(false) ;
			}else{
				p.setBgrid(true) ;
			}
			p.repaint() ;
		/*	desktop.add(new InteriorComponent(this,p));
			desktop.updateUI() ;*/
		}
	}

	public Mario getMario() {
		return mario;
	}

	public void setMario(Mario mario) {
		this.mario = mario;
	}
}
