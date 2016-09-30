package org.maker.ui;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import org.maker.character.Goumba;
import org.maker.character.UndoR;
import org.maker.levels.TilesSprite;
import org.maker.ui.ani.Dialogs;

public class Mousse {
	UndoR undo = new UndoR();
	public static String TYPE = "N";
	public Content con ; 
	Goumba gou;
	int incre = 0;
	int incre2 = 0;
	int[] value = new int[2];// HMB (no)
	String[] TABLE = { "z", "e", "r", "t", "y", "u", "i", "o", "s", "d", "x",
			"c", "v" ,"k" ,"w","h" , "j" ,"l"};
	public int TABLE2 = 0;

	public Mousse(final Content content , final SpriteSheet s) {
		this.con = content ; 
		content.addMouseListener(new MouseAdapter() {

			public void mouseReleased(MouseEvent e) {
				
					if (e.isPopupTrigger()) {
						if(!content.sprites.isEmpty() && content.diversecran == 1){
							
							Dialogs zd = new Dialogs(null,"",true);
							Integer[] test = zd.showZDialog();
							if(test[0] != null){
								content.sprites.get(content.sprites.size() - 1).setDimX(test[0]) ;
								content.sprites.get(content.sprites.size() - 1).setDimY(test[1]) ;
							}
							
							content.history.CONTENT += "Size changed ("+test[0]+"X"+test[1]+")\n" ;
							content.history.setTexte(content.history.CONTENT);
							
						
							/*JOptionPane jop = new JOptionPane();
							jop.showMessageDialog(null, test[0] + " et " + test[1],
								"Informations personnage",
								JOptionPane.INFORMATION_MESSAGE);
							
							/*PaperCard pap = new PaperCard();
							pap.setSize(400,500);
							pap.add(new SizeTiles());
							pap.visible(true);*/
						}
					//	System.out.println(content.sprites.get(0).getDimX() +"   popop");
						System.out.println("Clic Droit");
					
					} else {
						//*****************************************************************
						//First Listener 
						//*****************************************************************
						
						if (content.diversecran == 0) {
							incre = 0;
							// Try to check a goumba ...
							if (TYPE.equals(Goumba.TYPE + "")) {
								System.out.println("Goumba move !");
								gou = new Goumba(
										(int) (content.getGrid().getPosXinit() + (e
												.getX() / (1000 / 24))),
										(int) (content.getGrid().getPosYinit() + (e
												.getY() / (600 / 18))), content);
								gou.rune();
							}

							try {
								if(content.sprites.size() != 0){
									
									int dep = (int) (e.getY() / (600 / 18)) ;
									int dep2 = (int) (e.getX() / (1000 / 24)) ;
									for(int i = 0 ; i < content.sprites.get(content.sprites.size() - 1).getDimY() ; i++){
										for(int j=0 ; j < content.sprites.get(content.sprites.size() - 1).getDimX() ; j++){
											content.getGrid().getCamera()[dep][dep2] = " ";
											content.getGrid().getField()[(int) (content.getGrid()
													.getPosYinit() + dep )][(int) (content
															.getGrid().getPosXinit()+ dep2)] =" " ;
											dep2 ++ ; 
										}
										dep2 =  (int) (e.getX() / (1000 / 24)) ;
										dep ++ ; 
									}
								}
									
								content.getGrid().getCamera()[(int) (e.getY() / (600 / 18))][(int) (e
										.getX() / (1000 / 24))] = TYPE;
								content.getGrid().getField()[(int) (content.getGrid()
										.getPosYinit() + (e.getY() / (600 / 18)))][(int) (content
										.getGrid().getPosXinit() + (e.getX() / (1000 / 24)))] = TYPE;
								content.repaint();
								Toolkit.getDefaultToolkit().sync();
								content.history.CONTENT += "Tile positioned!\n";
								content.history.setTexte(content.history.CONTENT);
							} catch (Exception ex) {
								System.out.println("Out of ring !");
							}
							System.out.println(e.getX() + " " + e.getY());

							undo.adde(content.getGrid());
						}
						if (content.diversecran == 1) {
							System.out.println("ffff");
							content.getGrid().setImageTile("iso.png") ; 
							incre++;
							if (incre == 2) {
								content.history.CONTENT += "Tile registred database \n";
								content.history.setTexte(content.history.CONTENT);
								content.sprites.add(new TilesSprite(value[0], value[1],
										value[0] + Math.abs(value[0] - e.getX()),
										value[1] + Math.abs(value[1] - e.getY()),
										TABLE[TABLE2]));
								value[0] = e.getX();
								value[1] = e.getY();
								content.numberSheet.add(content.getGrid().getImageTile());
								incre = 0;
								TYPE = TABLE[TABLE2];
								TABLE2++;
								content.repaint();
								Toolkit.getDefaultToolkit().sync();
							}
							if (incre == 1) {
								value[0] = e.getX();
								value[1] = e.getY();
							}

						}

					
						System.out.println("Clic Gauche "+TABLE2);
						
					}
				
			}
		});

		content.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				//*****************************************************************
				//First Listener 
				//*****************************************************************
				/*
				if (content.diversecran == 0) {
					incre = 0;
					// Try to check a goumba ...
					if (TYPE.equals(Goumba.TYPE + "")) {
						System.out.println("Goumba move !");
						gou = new Goumba(
								(int) (content.getGrid().getPosXinit() + (e
										.getX() / (1000 / 24))),
								(int) (content.getGrid().getPosYinit() + (e
										.getY() / (600 / 18))), content);
						gou.rune();
					}

					try {
						if(content.sprites.size() != 0){
							
							int dep = (int) (e.getY() / (600 / 18)) ;
							int dep2 = (int) (e.getX() / (1000 / 24)) ;
							for(int i = 0 ; i < content.sprites.get(content.sprites.size() - 1).getDimY() ; i++){
								for(int j=0 ; j < content.sprites.get(content.sprites.size() - 1).getDimX() ; j++){
									content.getGrid().getCamera()[dep][dep2] = " ";
									content.getGrid().getField()[(int) (content.getGrid()
											.getPosYinit() + dep )][(int) (content
													.getGrid().getPosXinit()+ dep2)] =" " ;
									dep2 ++ ; 
								}
								dep2 =  (int) (e.getX() / (1000 / 24)) ;
								dep ++ ; 
							}
						}
							
						content.getGrid().getCamera()[(int) (e.getY() / (600 / 18))][(int) (e
								.getX() / (1000 / 24))] = TYPE;
						content.getGrid().getField()[(int) (content.getGrid()
								.getPosYinit() + (e.getY() / (600 / 18)))][(int) (content
								.getGrid().getPosXinit() + (e.getX() / (1000 / 24)))] = TYPE;
						content.repaint();
						Toolkit.getDefaultToolkit().sync();
						content.history.CONTENT += "Tile positioned!\n";
						content.history.setTexte(content.history.CONTENT);
					} catch (Exception ex) {
						System.out.println("Out of ring !");
					}
					System.out.println(e.getX() + " " + e.getY());

					undo.adde(content.getGrid());
				}
				if (content.diversecran == 1) {
					System.out.println("ffff");
					content.getGrid().setImageTile("iso.png") ; 
					incre++;
					if (incre == 2) {
						content.history.CONTENT += "Tile registred database \n";
						content.history.setTexte(content.history.CONTENT);
						content.sprites.add(new TilesSprite(value[0], value[1],
								value[0] + Math.abs(value[0] - e.getX()),
								value[1] + Math.abs(value[1] - e.getY()),
								TABLE[TABLE2]));
						value[0] = e.getX();
						value[1] = e.getY();
						content.numberSheet.add(content.getGrid().getImageTile());
						incre = 0;
						TYPE = TABLE[TABLE2];
						TABLE2++;
						content.repaint();
						Toolkit.getDefaultToolkit().sync();
					}
					if (incre == 1) {
						value[0] = e.getX();
						value[1] = e.getY();
					}

				}*/

			}
		});

		content.addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
				if (content.diversecran == 0) {
					try {
						content.getGrid().getCamera()[(int) (e.getY() / (600 / 18))][(int) (e
								.getX() / (1000 / 24))] = TYPE;
						content.getGrid().getField()[(int) (content.getGrid()
								.getPosYinit() + (e.getY() / (600 / 18)))][(int) (content
								.getGrid().getPosXinit() + (e.getX() / (1000 / 24)))] = TYPE;
						content.repaint();
						Toolkit.getDefaultToolkit().sync();
					} catch (Exception ex) {
						System.out.println("Out of ring !");
					}

				}
			}

			public void mouseMoved(MouseEvent e) {
				if (content.diversecran == 0) {
					try {
						content.getGrid().getClignote()[(int) (e.getY() / (600 / 18))][(int) (e
							.getX() / (1000 / 24))] = true;
					// content.grid.clignote[(int)(e.getY()/(600/18))][(int)(e.getX()/(1000/24))]=false;
						content.repaint();
						Toolkit.getDefaultToolkit().sync();
						content.clignote[0] = (int) (e.getY() / (600 / 18));
						content.clignote[1] = (int) (e.getX() / (1000 / 24));
					} catch (Exception ex) {
						System.out.println("Out of ring !");
					}
				}
			}
		});
		
		//*****************************************************************
		//Mousse listener for the second tiles tab 
		//*****************************************************************
		
		s.addMouseListener(new MouseAdapter() {

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					if(!content.sprites.isEmpty()){
						Dialogs zd = new Dialogs(null,"",true);
						Integer[] test = zd.showZDialog();
						if(test[0] != null){
							content.sprites.get(content.sprites.size() - 1).setDimX(test[0]) ;
							content.sprites.get(content.sprites.size() - 1).setDimY(test[1]) ;
						}
						
						content.history.CONTENT += "Size changed ("+test[0]+"X"+test[1]+")\n" ;
						content.history.setTexte(content.history.CONTENT);
					}
					System.out.println("Clic Droit");
				
				} else {
					System.out.println(e.getX()+"      "+TABLE2);
					incre2++;
					if (incre2 == 2) {
						content.history.CONTENT += "Tile registred database \n";
						content.history.setTexte(content.history.CONTENT);
						content.sprites.add(new TilesSprite(value[0], value[1],
								value[0] + Math.abs(value[0] - e.getX()),
								value[1] + Math.abs(value[1] - e.getY()),
								TABLE[TABLE2]));
						value[0] = e.getX();
						value[1] = e.getY();
						content.numberSheet.add(content.sh.names[content.sh.inc]);
						incre2 = 0;
						TYPE = TABLE[TABLE2];
						TABLE2++;
						s.repaint();
						Toolkit.getDefaultToolkit().sync();
					}
					if (incre2 == 1) {
						value[0] = e.getX();
						value[1] = e.getY();
					}
					System.out.println("Clic Gauche "+TABLE2);
				}
				
				
			}
		});

		s.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
			//	content.getGrid().setImageTile(content.sh.names[content.sh.inc]) ; 

			
			}
		});

		s.addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
				

				
			}

			public void mouseMoved(MouseEvent e) {
				
			}
		});
	}
	
	
}
