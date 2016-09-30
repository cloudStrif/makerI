package org.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.maker.levels.Grid;
import org.maker.levels.TilesSprite;
import org.maker.ui.Mousse;
import org.maker.ui.Push;
	
public class GridIso extends JPanel {
	/**
	 * Main class for the isometrci view .this is the main scene take place.
	 */
	int dh = 15;
	int dl = 26;

	MousseIso mou  ;
	
	float [] clignote  = new float[2];
	
	public int deplacementX = 370 ; 
	public int deplacementY = 150 ; 
	public Grid grid = new Grid(15);
	public int ZOOM =1 ; 
	//an array of tile sprite  
	public ArrayList <TilesSprite> sprites = new ArrayList<TilesSprite>() ;
	public ArrayList<String> sheet  = new ArrayList<String>() ; 
	Mousse mou1 ; 
	
	
	public GridIso(Mousse mou1 ){
		this.mou1=mou1 ;
		mou = new MousseIso(this ,mou1) ; 
	}
	
	public void paintComponent(Graphics g) {
	//	grid.getCamera()[0][1] = "l";
		affiche(grid.getCamera(), g);
	
	}

	public void affiche(String[][] t, Graphics g) {

		g.setColor(Color.BLACK);
		ImageIcon imIco2 = new ImageIcon(
				"org/maker/ressources/backgrounds/background2.png");
		Image w232 = imIco2.getImage();
		g.drawImage(w232,0, 0, this.getWidth(), this.getHeight(), this);
		
	//	g.fillRect(0, 0, this.getWidth(), this.getHeight());
		for (int i = 0; i <15; i++) {
			for (int j = 0; j < 15; j++) {
	
				switch(t[i][j]){
					case "P" : 
						ImageIcon imIco = new ImageIcon(
								"org/maker/ressources/tiles/cube.png");
						Image w23 = imIco.getImage();
						
						g.drawImage(w23, ZOOM*left(i, j), ZOOM* top(i, j)-15,  ZOOM*64, ZOOM* 64, this);
						
					default: 	ImageIcon imIco1 = new ImageIcon(
							"org/maker/ressources/tiles/cad.png");
					Image w231 = imIco1.getImage();
					g.drawImage(w231, ZOOM*left(i, j), ZOOM*top(i, j), ZOOM*52,ZOOM* 30, this);
				}
				
			    for(int ii = 0 ; ii < mou1.con.sprites.size(); ii++){
		        	 if(grid.getCamera()[i][j].equals( mou1.con.sprites.get(ii).getName())){
		        		 ImageIcon boxo22 = new ImageIcon(Push.PATH+"tiles/"+mou1.con.numberSheet.get(ii));//+grid.getImageTile());
		                 Image boxo2222 = boxo22.getImage();
		                
		                 g.drawImage(boxo2222, left(i, j), top(i, j), left(i, j)+(int)mou1.con.sprites.get(ii).getDimX() *(1000/24),top(i, j)+(int)mou1.con.sprites.get(ii).getDimY()*(600/18),mou1.con.sprites.get(ii).getPocX(),mou1.con.sprites.get(ii).getPocY(),mou1.con.sprites.get(ii).getPocZ(),mou1.con.sprites.get(ii).getPocT(),this);
		               
		        	 }
		         }
				
			   if(grid.getClignote()[i][j] == true){
					ImageIcon imIco = new ImageIcon(
							"org/maker/ressources/tiles/cad2.png");
					Image w23 = imIco.getImage();
					
					g.drawImage(w23, left(i, j), top(i, j)-10, 52, 45, this);
				}
			}

		}
		grid.getClignote()[(int) clignote[0]][(int) clignote[1]] = false ;
		coordonnée(200 + deplacementX  - 370, 300);
		g.setColor(Color.BLUE);
		//g.fillRect(200 + deplacementX , 300+deplacementY, 10, 10);
	}
	
	private Dimension getImageDim(final String path) {
	    Dimension result = null;
	    String suffix = this.getFileSuffix(path);
	    Iterator<ImageReader> iter = ImageIO.getImageReadersBySuffix(suffix);
	    if (iter.hasNext()) {
	        ImageReader reader = iter.next();
	        try {
	            ImageInputStream stream = new FileImageInputStream(new File(path));
	            reader.setInput(stream);
	            int width = reader.getWidth(reader.getMinIndex());
	            int height = reader.getHeight(reader.getMinIndex());
	            result = new Dimension(width, height);
	        } catch (IOException e) {
	           
	        } finally {
	            reader.dispose();
	        }
	    } else {
	       
	    }
	    return result;
	}
	private String getFileSuffix(final String path) {
	    String result = null;
	    if (path != null) {
	        result = "";
	        if (path.lastIndexOf('.') != -1) {
	            result = path.substring(path.lastIndexOf('.'));
	            if (result.startsWith(".")) {
	                result = result.substring(1);
	            }
	        }
	    }
	    return result;
	}
	
	public int top(int x, int y) {
		return (dh * y + dh * x) + deplacementY;

	}

	public int left(int x, int y) {
		return (dl * x - dl * y) + deplacementX;
	}

	public float [] coordonnée(int x1, int y1) {
		float[] tab = new float[2];
	//	tab[0] = (float) Math.floor((y1 / 30) + ((x1) / 52)) ;
	//	tab[1] = (float) Math.ceil((y1 / 30) - ((x1) / 52))+1; 
		
		tab[0] =((y1 + x1/2)/30 ) ; 
		tab[1] = ((y1 - x1/2)/30)+1;
		//System.out.println((int) Math.floor((y1 / 30) + ((x1) / 52)));
	//	System.out.println((int) Math.ceil((y1 / 30) - ((x1) / 52)) - 1 );
		return tab ; 
	}

}
