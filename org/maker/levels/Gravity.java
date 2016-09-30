package org.maker.levels;

import java.awt.Toolkit;

import org.maker.ui.Content;
import org.maker.ui.Window;
/**
 * 
 * this class simulate the gravity on the field ,it takes a velocity and calculate 
 * the speed of fall
 */
public class Gravity implements Runnable {
	//basic intensity of the period repaint of the scene
	int intensity=30;
	int x;
	int y;
	public boolean Active = false; 
	Window win;
	Content con;

	public Gravity(int intensity, int x, int y) {
		this.intensity = intensity;
		this.x = x;
		this.y = y;
	}
	/**
	 * Build a gravity with the following window then notice if it is active or not
	 * @param win
	 */
	public Gravity(Window win) {
		this.win = win;;
		this.Active =true; 
	}

	public Gravity(Content con) {
		this.con = con;
		this.Active =true; 
	}
	/**
	 * simulatition of gravity on the grid and the field behind the scene
	 */
	public void run() {
		
		// Try to simulate the gravity

		//System.out.println("SISSO"+con.getGrid().getField()[con.getGrid().getPosYinit()+con.getMario().getPosY() + 1][con.getGrid().getPosXinit()+con.getMario().getPosX()]);
		while ((con.getGrid().getField()[con.getGrid().getPosYinit()+con.getMario().getPosY() + 1][con.getGrid().getPosXinit()+con.getMario().getPosX()].equals("N") || con.getGrid().getField()[con.getGrid().getPosYinit()+con.getMario().getPosY() + 1][con.getGrid().getPosXinit()+con.getMario().getPosX()].equals("p") || con.getGrid().getField()[con.getGrid().getPosYinit()+con.getMario().getPosY() + 1][con.getGrid().getPosXinit()+con.getMario().getPosX()].equals("4")) && con.getMario().getPosY() <14) {
			
			if(con.getGrid().getField()[con.getGrid().getPosYinit()+con.getMario().getPosY() + 1][con.getGrid().getPosXinit()+con.getMario().getPosX()].equals("p")){
				con.getMario().setPiece(con.getMario().getPiece() + 1) ;
			}
			if(!con.getGrid().getField()[con.getGrid().getPosYinit()+con.getMario().getPosY()+1][con.getGrid().getPosXinit()+con.getMario().getPosX()].equals("N")  && !con.getGrid().getField()[con.getGrid().getPosYinit()+con.getMario().getPosY()+1][con.getGrid().getPosXinit()+con.getMario().getPosX()].equals("p")){
				break ;
			}

			
			//con.mario.pixelY += (1000/24)-5;
			con.getMario().setPixelY(con.getMario().getPixelY() + 20);
			con.mariopos();
			
			if(con.getGrid().getField()[con.getGrid().getPosYinit()+con.getMario().getPosY()-1][con.getGrid().getPosXinit()+con.getMario().getPosX()].equals("4") || con.getGrid().getField()[con.getGrid().getPosYinit()+con.getMario().getPosY()-1][con.getGrid().getPosXinit()+con.getMario().getPosX()].equals("p")){
				con.getGrid().getField()[con.getGrid().getPosYinit() +con.getMario().getPosY()-1][con.getGrid().getPosXinit()+con.getMario().getPosX()] = "N";
			}
			con.repaint();
			Toolkit.getDefaultToolkit().sync();
			

			try {
				Thread.sleep(intensity);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			if(con.getMario().getPosY() >= 10){
				if (con.getGrid().getPosYinit() <con.getGrid().getS()) {			
					con.getMario().setPixelY(
							con.getMario().getPixelY() - ((1000/24)-5)); 
					con.mariopos() ;
					con.getGrid()
							.setPosYinit(con.getGrid().getPosYinit() + 1);
					con.getGrid().refresh();
					con.repaint();
					Toolkit.getDefaultToolkit().sync();
				}
			}
	
		}
	
		con.getGrid().getField()[con.getGrid().getPosYinit() +con.getMario().getPosY()][con.getGrid().getPosXinit()+con.getMario().getPosX()] = "4";
		con.getGrid().refresh();
		System.out.println("forum des halles");
	
		
	
		int value = ((600/18)*(con.getMario().getPosY()+1) )- con.getMario().getPixelY() ;
	
		con.getMario().setPixelY(con.getMario().getPixelY() + (value -15)) ;//maybe change 15 on linux ..
		con.mariopos();
		//con.DEBUG(); 
		

		con.repaint() ;
		Toolkit.getDefaultToolkit().sync();
		
		this.Active=true;
		
		
		//if(!con.grid.field[con.grid.posYinit +con.mario.posY+1][con.grid.posXinit+con.mario.posX].equals("N"))
		
		
		//con.getJump().Active=true; 
		con.mariopos();
		Thread.currentThread().interrupt();
	}
}
