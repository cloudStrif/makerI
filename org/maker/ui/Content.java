package org.maker.ui ;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.maker.character.Goumba;
import org.maker.character.Mario;
import org.maker.levels.FireBall;
import org.maker.levels.Gravity;
import org.maker.levels.Grid;
import org.maker.levels.Jump;
import org.maker.levels.TilesSprite;
import org.maker.ressources.Boxe;
import org.maker.ressources.Piece;
import org.maker.ui.ani.History;
import org.maker.ui.ani.PaperCard;

/**
 * 
 * Graphical component 
 *
 */
public class Content extends JPanel{

  private  Grid grid ;
  private Mario mario ;


  int x = 0 ;
  int y = 0 ;
  public History history = new History() ;
  int [] clignote = new int[2];
  public Mousse mousse ;
  private boolean keys[] = new boolean[256];
  private Gravity gravity = new Gravity(this);
  private Jump jump = new Jump(this);
  FireBall fire =new FireBall(this) ;
  private boolean Bgrid =true ;
  public int diversecran = 0 ;
  public String TEXTE ="" ;
  public boolean ecrire = false ;
  public ArrayList <TilesSprite> sprites = new ArrayList<TilesSprite>() ;
  public ArrayList<String> numberSheet = new ArrayList<String>() ;
  public String BACKGROUND = Push.PATH+"backgrounds/Icy_Background.png" ;
  public Content(){}
  public SpriteSheet sh = new SpriteSheet(this) ;
  
  public Content(Grid grid,Mario mario){
	  
    this.setGrid(grid);
    this.setMario(mario) ;
    clignote[0]=0 ;
    clignote[1]=0 ;
    mousse = new Mousse(this,sh) ;
    setDoubleBuffered(false);
    //basic motion
    
    this.getGrid().getField()[mario.getPosY()][mario.getPosX()]= "4" ;
    this.getGrid().getField()[mario.getPosY()+1][mario.getPosX()]= "M" ;
    this.getGrid().refresh() ;
    
  }
  /*
  Default printing of the grid
   */
  

  public void paintComponent(Graphics g){
   //  super.paintComponent(g);
   
    	printing(g);
     	actionM(g);
    
     //Tiling stuffs
    	if(ecrire){
    		g.setFont(new Font("Arial", Font.BOLD, 20));
    		g.setColor(new Color(127, 127, 127,100));
    		g.fillRect(180, 180, 700, 200);
    		g.setColor(Color.WHITE);
    		drawString(g, TEXTE, 200, 200);
    	}
   
    	tiles(g);
    	
  }
  
  public int tilepos = -800 ;
  public void tiles(Graphics g){
	  ImageIcon fond = new ImageIcon(Push.PATH+"tiles/"+grid.getImageTile());
	
	  Image fond2 = fond.getImage();
	 g.drawImage(fond2, tilepos ,0,this);

	  if(diversecran == 1){
		
		ImageIcon tuto = new ImageIcon(Push.PATH+"tuto/cloud.png");
	  	Image tuto2 = tuto.getImage();
	  	g.drawImage(tuto2, 700 ,300,1000,600,200,50,500,380,this);
	
	  }
	if(diversecran == 1 && sprites.size() != 0 ){
    		g.setColor(Color.RED);
    		g.drawRect(sprites.get(sprites.size()-1).getPocX(), sprites.get(sprites.size()-1).getPocY(), sprites.get(sprites.size()-1).getPocZ()-sprites.get(sprites.size()-1).getPocX(),sprites.get(sprites.size()-1).getPocT()-sprites.get(sprites.size()-1).getPocY());
    }
	  
  }
  
  public void mariopos(){
	  
	  getMario().setPosX((int)(getMario().getPixelX()/(1000/24))) ;
  	  getMario().setPosY((int)(getMario().getPixelY()/(600/18)));
  	  if(getGrid().getField()[getGrid().getPosYinit()+getMario().getPosY()][getGrid().getPosXinit()+getMario().getPosX()].equals("N") && !getGravity().Active)
  		  getGrid().getField()[getGrid().getPosYinit()+getMario().getPosY()][getGrid().getPosXinit()+getMario().getPosX()]= "4";
  	 
  	if(getGrid().getField()[getGrid().getPosYinit()+getMario().getPosY()+1][getGrid().getPosXinit()+getMario().getPosX()].equals("4"))
  		getGrid().getField()[getGrid().getPosYinit()+getMario().getPosY()+1][getGrid().getPosXinit()+getMario().getPosX()] = "N" ;
  	  
	  for (int i=0 ; i < getGrid().getCamera().length ; i++){
		  for(int j=0 ; j< getGrid().getCamera()[0].length ;j++){
			  if(getGrid().getCamera()[i][j].equals("4") && i != getMario().getPosY() && j != getMario().getPosY()){
				  getGrid().getField()[getGrid().getPosYinit() + i][getGrid().getPosXinit()+j] = "N";
				  
			  }
		  }
	  } 
	  	//grid.refresh() ;
  	 //	repaint() ;
	   //grid.camera[mario.posY][mario.posX] = "4";
  }
  boolean bool = true ;
  int compt =0 ;
  public void actionM(Graphics g){
	  //default cases
	

	  if(getKeys()[KeyEvent.VK_RIGHT]){
		
		//  grid.field[mario.posY][mario.posX]= "4";
	    	g.setColor(new Color(100,100,100,64));
	    	g.fillRect(10,10,100,100);
	    	
	    	
	    	//Be carefull of 4 doublons ...
	    	
	    	if(getMario().getPosY()>0 && getMario().getPosX()>0){
	    		if(getJump().Active){
	    			if(getMario().getMovement() == getMario().datas.rightW.size()){
		    			getMario().setMovement(0) ;
		    		}
	    		getMario().actual = getMario().datas.rightW.get(getMario().getMovement()); 	
	    		getMario().setMovement(getMario().getMovement() + 1) ;
	    		
	    		}
	    	if((getGrid().getCamera()[getMario().getPosY()][getMario().getPosX()+1].equals("N") || getGrid().getCamera()[getMario().getPosY()][getMario().getPosX()+1].equals("4") || getGrid().getCamera()[getMario().getPosY()][getMario().getPosX()+1].equals("p")) && isMario()){
	    		if(getGravity().Active){
	    		
	    			
	    		if(getKeys()[KeyEvent.VK_B])
	    			getMario().setPixelX(getMario().getPixelX() + 5) ;
	    		else	
	    			getMario().setPixelX(getMario().getPixelX() + 3) ;
	    		
	    		
	    		}else{
	    			if(getJump().Active){
	    				getMario().actual=getMario().parse.tab[5];
	    			}
	    			if(getKeys()[KeyEvent.VK_B])
		    			getMario().setPixelX(getMario().getPixelX() + 5) ;
		    		else	
		    			getMario().setPixelX(getMario().getPixelX() + 3) ;
	    		}
	    		if(getGrid().getCamera()[getMario().getPosY()][getMario().getPosX()+1].equals("p")){
	    			getMario().setPiece(getMario().getPiece() + 1) ;
	    			getGrid().refresh();
	    		}
	    		
	    		mariopos();
	    		getGrid().getField()[getGrid().getPosYinit()+getMario().getPosY()][getGrid().getPosXinit()+getMario().getPosX()]= "4";
	    		if(getGrid().getField()[getGrid().getPosYinit()+getMario().getPosY()][getGrid().getPosXinit()+getMario().getPosX()-1].equals("4"))
	    			getGrid().getField()[getGrid().getPosYinit()+getMario().getPosY()][getGrid().getPosXinit()+getMario().getPosX()-1]= "N";
	    		
	    		
	    		//System.out.println("MAKO MAKO");
	    	}
	    	if(getMario().getPosX() >= 11){
				if (getGrid().getPosXinit() < getGrid().getS2()) {
					getGrid().setPosXinit(getGrid().getPosXinit() + 1);
					getMario().setPixelX(
							getMario().getPixelX() - (600/18)*0.5);
					mariopos() ;
					getGrid().refresh();
				}
			}
	    	}
	    	repaint() ;	
	    	//Toolkit.getDefaultToolkit().sync();    	 
	   }
	 
	  
	 /*if(keys[KeyEvent.VK_UP]){
		   // desactive la gravity 
		 if(jump.Active && !grid.getField()[grid.getPosYinit()+mario.getPosY()+1][grid.getPosXinit()+mario.getPosX()].equals("N")){
			 
			  gravity.Active = false;
			  jump.Active = false; 
			  Thread th = new Thread(jump) ;
			  th.start() ;
		  }  
	  }*/
	  

	  if(getKeys()[KeyEvent.VK_UP]){
		  getMario().setJUMP(true) ;
		  
			if(getMario().getPosY() == 4 ){
				if (getGrid().getPosYinit() > 0) {
					getGrid().setPosYinit(getGrid().getPosYinit() - 1);
					getMario().setPixelY((int) ((int)
							getMario().getPixelY() + (600/18)*0.7));
					mariopos();
					getGrid().refresh();
					
				}
			}
		  if(getMario().getPosY()>0 && getMario().getPosX()>0){
			  
			  
			  if((getGrid().getCamera()[getMario().getPosY()-1][getMario().getPosX()].equals("N") || getGrid().getCamera()[getMario().getPosY()-1][getMario().getPosX()].equals("4") || getGrid().getCamera()[getMario().getPosY()-1][getMario().getPosX()].equals("p")) && isMario()){
				  getGravity().Active = false ;
				getMario().setPixelY(getMario().getPixelY() - 4) ;
				  
				  if(getGrid().getCamera()[getMario().getPosY()-1][getMario().getPosX()].equals("p")){
					  getMario().setPiece(getMario().getPiece() + 1) ;
					  getGrid().refresh();
				  }
		    		mariopos();
		    		getGrid().getField()[getGrid().getPosYinit()+getMario().getPosY()][getGrid().getPosXinit()+getMario().getPosX()]= "4";
		    		if(getGrid().getField()[getGrid().getPosYinit()+getMario().getPosY()+1][getGrid().getPosXinit()+getMario().getPosX()].equals("4"))
		    			getGrid().getField()[getGrid().getPosYinit()+getMario().getPosY()+1][getGrid().getPosXinit()+getMario().getPosX()]= "N";
		    		
		    	}
			  
		  }
			repaint() ;
			Toolkit.getDefaultToolkit().sync();
	  }  
	  
	  if(getKeys()[KeyEvent.VK_DOWN]){
		if(!getGravity().Active){
		  if(getMario().getPosY()>0 && getMario().getPosX()>0){
			  
			  if((getGrid().getCamera()[getMario().getPosY()+1][getMario().getPosX()].equals("N") || getGrid().getCamera()[getMario().getPosY()+1][getMario().getPosX()].equals("4") || getGrid().getCamera()[getMario().getPosY()+1][getMario().getPosX()].equals("p")) && isMario()){
				
				getMario().setPixelY(getMario().getPixelY() + 4) ;
				  
				  if(getGrid().getCamera()[getMario().getPosY()+1][getMario().getPosX()].equals("p")){
					  getMario().setPiece(getMario().getPiece() + 1) ;
					  getGrid().refresh();
				  }
		    		mariopos();
		    		getGrid().getField()[getGrid().getPosYinit()+getMario().getPosY()][getGrid().getPosXinit()+getMario().getPosX()]= "4";
		    		if(getGrid().getField()[getGrid().getPosYinit()+getMario().getPosY()-1][getGrid().getPosXinit()+getMario().getPosX()].equals("4"))
		    			getGrid().getField()[getGrid().getPosYinit()+getMario().getPosY()-1][getGrid().getPosXinit()+getMario().getPosX()]= "N";
		    		
		    	}
				if(getMario().getPosY() >= 11){
					if (getGrid().getPosYinit() < getGrid().getS2()) {
						getGrid().setPosYinit(getGrid().getPosYinit() + 1);
						getMario().setPixelY(
								(int) (getMario().getPixelY() - (600/18)*0.5));
						mariopos() ;
						getGrid().refresh();
					}
				}
			  
		  }
			repaint() ;
			Toolkit.getDefaultToolkit().sync();
		}
	  }
	  
	  
	  if(getKeys()[KeyEvent.VK_SPACE]){
		//  getGravity().Active = true ;
		  if(!fire.Active){
			  fire.Active =true; 
			  Thread t = new Thread(fire);
			  t.start() ; 
		  }
	  }
	  
	
	  if(getKeys()[KeyEvent.VK_LEFT]){
		
		  if(getMario().getPosY()>0 && getMario().getPosX()>0){
			  if(getJump().Active){
				  if(getMario().getMovement() >= getMario().datas.leftW.size()){
		    			getMario().setMovement(0) ;
		    		}
				getMario().actual = getMario().datas.leftW.get(getMario().getMovement()); 	
	    		getMario().setMovement(getMario().getMovement() + 1) ;
	    		
			  }
		  if((getGrid().getCamera()[getMario().getPosY()][getMario().getPosX()-1].equals("N") || getGrid().getCamera()[getMario().getPosY()][getMario().getPosX()-1].equals("4") || getGrid().getCamera()[getMario().getPosY()][getMario().getPosX()-1].equals("p")) && isMario()){
			 
			  if(getGravity().Active){
			  if(getKeys()[KeyEvent.VK_B])
	    			getMario().setPixelX(getMario().getPixelX() - 6) ;
	    		else
	    			getMario().setPixelX(getMario().getPixelX() - 3) ;  	
			  }else{
				  if(getJump().Active){
					  getMario().actual=getMario().parse.tab[4];
				  }
				  if(getKeys()[KeyEvent.VK_B])
		    			getMario().setPixelX(getMario().getPixelX() - 7) ;
		    		else	
		    			getMario().setPixelX(getMario().getPixelX() - 4) ;
			  }
			  if(getGrid().getCamera()[getMario().getPosY()][getMario().getPosX()-1].equals("p")){
				  getMario().setPiece(getMario().getPiece() + 1) ;
				  getGrid().refresh();
			  }
	    		mariopos();
	    		getGrid().getField()[getGrid().getPosYinit()+getMario().getPosY()][getGrid().getPosXinit()+getMario().getPosX()]= "4";
	    		if(getGrid().getField()[getGrid().getPosYinit()+getMario().getPosY()][getGrid().getPosXinit()+getMario().getPosX()+1].equals("4"))
	    			getGrid().getField()[getGrid().getPosYinit()+getMario().getPosY()][getGrid().getPosXinit()+getMario().getPosX()+1]= "N";
	    		
	    		if(getMario().getPosX() == 4 ){
					if (getGrid().getPosXinit() > 0) {
						getGrid().setPosXinit(getGrid().getPosXinit() - 1);
						getMario().setPixelX(
								getMario().getPixelX() + (600/18)*0.7);
						mariopos();
						getGrid().refresh();
						
					}
				}
	    	}
		  }
		
			repaint() ;
			Toolkit.getDefaultToolkit().sync();
			
	  }
	
	  
	
	 
  }

  
  
  public void verif(){
	  for(int i=0;i<18;i++){
	        for(int j=0;j<24;j++){
	        	if(getGrid().getCamera()[i][j].equals("4") && (i != getMario().getPosY() || j != getMario().getPosX())){
	        		getGrid().getCamera()[i][j]="N";
	        	}
	        }
	  }
	
  }
  
  public void printing(Graphics g){
	 
    g.setColor(new Color(1,2,1));
    g.fillRect(0,0,PaperCard.tX ,PaperCard.tY);
    ImageIcon fond = new ImageIcon(BACKGROUND);
   // ImageIcon fond = new ImageIcon(Push.PATH+"backgrounds/gife.gif");
    Image fond2 = fond.getImage();
    g.drawImage(fond2, 0,0,this.getWidth(),this.getHeight(),this);
  //  g.drawImage(fond2, 200,200,250,290, 0,0,50,76,this);
   // g.drawImage(fond2, 0,0,this);
    for(int i=0;i<18;i++){
        for(int j=0;j<24;j++){

          if (getGrid().getClignote()[i][j]==false) {
        	  if(Bgrid){
        		  g.setColor(new Color(111,133,111,64));
        		  g.fillRect(x,y,(int)(1000/24)-1,(int)(600/18)-1);
        	  }
          }

          switch(getGrid().getCamera()[i][j]){
            case "B":
              ImageIcon imageIcon = new ImageIcon(Push.PATH+"tiles/r3.png");
              Image w = imageIcon.getImage();
              g.drawImage(w, x, y, (int)(1000/24),(int)(600/18),this);
            break ;
            case "M":
              ImageIcon imageIco = new ImageIcon(Push.PATH+"tiles/p.png");
              Image w1 = imageIco.getImage();
              g.drawImage(w1, x, y, (int)(1000/24),(int)(600/18),this);
            break ;
            case "H":
              ImageIcon imageIc = new ImageIcon(Push.PATH+"tiles/r.png");
              Image w33 = imageIc.getImage();
              g.drawImage(w33, x, y, (int)(1000/24),(int)(600/18),this);
            break ;
            case "p":
              ImageIcon imIc = new ImageIcon(Push.PATH+"items/"+Piece.name);
              Image w333 = imIc.getImage();
              g.drawImage(w333, x+5, y+5, (int)(1000/24)-5,(int)(600/18)-5,this);
            break ;
            case "9":
              ImageIcon imIco = new ImageIcon(Push.PATH+"baddies/"+Goumba.face);
              Image w23 = imIco.getImage();
              g.drawImage(w23, x-1, y-1, (int)(1000/24)+1,(int)(600/18)+1,this);
            break ;
            case "P":
              ImageIcon ic = new ImageIcon(Push.PATH+"items/Question_Block_Dead.png");
              Image ic2 = ic.getImage();
              g.drawImage(ic2, x, y, (int)(1000/24),(int)(600/18),this);
            break ;
            case "7":
               
            		if(!fire.explose){
            		/*	g.setColor(Color.YELLOW);
            			g.fillOval(x, y,  (int)(1000/24)+20,(int)(600/18)-10);*/
            			
            			Graphics2D g2d = (Graphics2D)g;
            			GradientPaint gp = new GradientPaint(0, 0, Color.RED, 30, 30,
            			Color.YELLOW, true);
            			g2d.setPaint(gp);
            			g2d.fillOval(x, y+10,  (int)(1000/24)+20,(int)(600/18)-10);

            		}else{
            			ImageIcon explose = new ImageIcon(Push.PATH+"baddies/11500"+fire.I+".png");
            			Image explose2 = explose.getImage();
            			g.drawImage(explose2,x,y,50,50,this);
            		}
               
              break ;
            case "Q":
              ImageIcon boxo = new ImageIcon(Push.PATH+"items/"+Boxe.name);
              Image boxo2 = boxo.getImage();
              g.drawImage(boxo2, x, y, (int)(1000/24),(int)(600/18),this);
            break ;
            case "4": //Mario case
            	 ImageIcon mariosprite = new ImageIcon(Push.PATH+"main/"+getMario().actual+".png");
                 Image msprite = mariosprite.getImage();
                 
                 if(getMario().isMario()){
                	
                	 g.drawImage(msprite, getMario().getPixelX()-17, getMario().getPixelY()-17, (int)(1000/24),(int)(600/18),this); 
                 }else{
                	 
                	  getMario().setPixelX(x +17); 
                      getMario().setPixelY(y +17); 
                      getMario().setPosX((int)(getMario().getPixelX()/(1000/24))) ;
                  	  getMario().setPosY((int)(getMario().getPixelY()/(600/18)));
                    
                     g.drawImage(msprite, x, y, (int)(1000/24),(int)(600/18),this); 
                 }
                
                	 
            break ;
          }
          
         for(int ii = 0 ; ii < sprites.size(); ii++){
        	 if(getGrid().getCamera()[i][j].equals(sprites.get(ii).getName())){
        		 ImageIcon boxo22 = new ImageIcon(Push.PATH+"tiles/"+numberSheet.get(ii));//+grid.getImageTile());
                 Image boxo2222 = boxo22.getImage();
                
                 g.drawImage(boxo2222, x, y, x+(int)sprites.get(ii).getDimX() *(1000/24),y+(int)sprites.get(ii).getDimY()*(600/18),sprites.get(ii).getPocX(),sprites.get(ii).getPocY(),sprites.get(ii).getPocZ(),sprites.get(ii).getPocT(),this);
           
        	 }
         }
         
          if (getGrid().getClignote()[i][j]==true) {
            g.setColor(new Color(255,255,255,100));
            g.fillRect(x,y,(int)(1000/24)-1,(int)(600/18)-1);
          }
          x+=(int)(1000/24);
        }
        x=0;
        y+=(int)(600/18);
      }
      x=y=0;
      
      getGrid().getClignote()[clignote[0]][clignote[1]]=false;
      
    //  DEBUG() ; 
      
      if((getGrid().getField()[getGrid().getPosYinit()+getMario().getPosY()+1][getGrid().getPosXinit()+getMario().getPosX()].equals("N") || getGrid().getField()[getGrid().getPosYinit()+getMario().getPosY()+1][getGrid().getPosXinit()+getMario().getPosX()].equals("p")) && isMario()){
    	  if(getGravity().Active){
    		  getGravity().Active = false; 
    		  Thread gravit = new Thread(getGravity());
    		  gravit.start() ;
    	  }
      }
       	  
      
      g.setColor(Color.BLACK);//later
      g.drawString("Piece:"+ getMario().getPiece(), 700,20);
     
  }
  
  public boolean isMario(){
	  for (int i=0 ; i < getGrid().getCamera().length ; i++){
		  for(int j=0 ; j< getGrid().getCamera()[0].length ;j++){
			  if(getGrid().getCamera()[i][j].equals("4")){
				  return true; 
			  }
		  }
	  }
	  return false; 
  }


  public void DEBUG(){
	//  System.out.println("KAMEHAMEHAAA");
		  for (int i=0 ; i < getGrid().getCamera().length ; i++){
			  for(int j=0 ; j< getGrid().getCamera()[0].length ;j++){
				  if(getGrid().getCamera()[i][j].equals("7")){
					  getGrid().getField()[getGrid().getPosYinit() + i][getGrid().getPosXinit()+j] = "N";
				  }
			  }
		  } 
		 // grid.refresh() ;
  }
  
  private void drawString(Graphics g, String text, int x, int y) {
		for (String line : text.split("\n"))
			g.drawString(line, x, y += g.getFontMetrics().getHeight());
  }

  
  //================================//
  //GETTERS AND SETTERS 			//
  //=================================//
public Grid getGrid() {
	return grid;
}

public void setGrid(Grid grid) {
	this.grid = grid;
}

public Mario getMario() {
	return mario;
}

public void setMario(Mario mario) {
	this.mario = mario;
}

public Jump getJump() {
	return jump;
}

public void setJump(Jump jump) {
	this.jump = jump;
}

public Gravity getGravity() {
	return gravity;
}

public void setGravity(Gravity gravity) {
	this.gravity = gravity;
}

public boolean[] getKeys() {
	return keys;
}

public void setKeys(boolean keys[]) {
	this.keys = keys;
}

public boolean isBgrid() {
	return Bgrid;
}

public void setBgrid(boolean bgrid) {
	Bgrid = bgrid;
}
}
