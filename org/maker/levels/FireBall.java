package org.maker.levels;

import java.awt.Toolkit;

import org.maker.ui.Content;

public class FireBall implements Runnable{
	public int I = 0;
	public int posXX =0 ;
	public int posYY =0 ;
	Content con ; 
	public boolean Active ;
	public boolean explose = false; 
	public FireBall(Content con){
		this.con=con ;
		Active = false; 
	}
	
	public void explose(){
		for(int i =1 ; i<11 ; i++){
			this.I = i ; 
			
			con.repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		explose = false; 
	}
	@Override
	public void run() {
		
		int ex = con.getMario().getPosX() ; 
		int ex2 = con.getMario().getPosY() ; 
		int ex3 = con.getGrid().getPosYinit() ; 
		int value =24;
		for (int i = ex+2 ; i< 24 ; i++){
			//if(ex+20 > 20) return; 
			if(!con.getGrid().getField()[ex3+ex2][con.getGrid().getPosXinit()+i].equals("N")){
				
				posYY = (con.getGrid().getPosXinit()+i)*(600/18) ;
				posXX = (ex3+ex2)*(1000/24) ; 
				explose = true ;
				explose() ;
				con.getGrid().getField()[ex3+ex2][con.getGrid().getPosXinit()+i] = "N" ;
				value=i ; 
				break ; 
			}
			con.getGrid().getField()[ex3+ex2][con.getGrid().getPosXinit()+i-1] = "N" ;
			con.getGrid().getField()[ex3+ex2][con.getGrid().getPosXinit()+i] = "7" ;
			con.getGrid().refresh() ; 
			
		
			try {
				Thread.sleep(50) ;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			con.repaint() ;
			Toolkit.getDefaultToolkit().sync();
		}
		con.getGrid().getField()[ex3+ex2][con.getGrid().getPosXinit()+value-1] = "N" ;
		con.getGrid().refresh() ; 
		con.repaint() ; 
		Toolkit.getDefaultToolkit().sync();
		Active = false; 
		explose = false ;
		con.DEBUG() ;
		con.repaint() ;
		Toolkit.getDefaultToolkit().sync();
		// TODO Auto-generated method stub
		
	}

}
