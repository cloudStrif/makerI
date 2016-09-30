package org.test;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import org.maker.ui.Mousse;

public class MousseIso {
	String [] TABLE ;    // Pointeur sur la source
	int[] value = new int[2];
	public int TABLE2 = 0;
	GridIso gridiso ; 
	Mousse mou ; 
	
	
	public MousseIso( final GridIso gridiso , final Mousse mou){
		this.gridiso = gridiso ; 
		this.mou = mou ; 
		
		
		gridiso.addMouseListener(new MouseAdapter() {

			public void mouseReleased(MouseEvent e) {
				
			}
		});

		gridiso.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
			//	System.out.println(" ll");
			try{
				float [] ta = gridiso.coordonnée(e.getX()-gridiso.deplacementX, e.getY()-gridiso.deplacementY);
				gridiso.grid.getCamera()[(int)ta[0]][(int)ta[1]] = mou.TYPE ; 
				gridiso.grid.getField()[gridiso.grid.getPosYinit() + (int)ta[0]][gridiso.grid.getPosXinit()+(int)ta[1]] = mou.TYPE ; 
				System.out.println(mou.TYPE);
				gridiso.repaint();
				Toolkit.getDefaultToolkit().sync();
			}catch(Exception exp){
				
			}
			}
		});

		gridiso.addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
				try{
					float [] ta1 = gridiso.coordonnée(e.getX()-gridiso.deplacementX, e.getY()-gridiso.deplacementY);
					gridiso.grid.getCamera()[(int)ta1[0]][(int)ta1[1]] = mou.TYPE ; 
					gridiso.grid.getField()[gridiso.grid.getPosYinit() + (int)ta1[0]][gridiso.grid.getPosXinit()+(int)ta1[1]] = mou.TYPE ; 
					gridiso.repaint();
					Toolkit.getDefaultToolkit().sync();
				}catch(Exception exp){
					
				}
				
			}

			public void mouseMoved(MouseEvent e) {
				float [] ta = gridiso.coordonnée(e.getX()-gridiso.deplacementX, e.getY()-gridiso.deplacementY);
				
				System.out.println(ta[0] + "   "+ ta[1]);
				try{
					gridiso.grid.getClignote()[(int) ta[0]][(int) ta[1]] = true; 
					gridiso.repaint();
					Toolkit.getDefaultToolkit().sync();
					gridiso.clignote[0] = ta[0] ; 
					gridiso.clignote[1] = ta[1] ;
				}catch(Exception ex){
					
				}
				System.out.println(mou.TABLE2 + "Madame mlmlmll") ;
			}
		});
	}
}
