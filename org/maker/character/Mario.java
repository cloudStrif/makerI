package org.maker.character;

import org.maker.MiniParsing.ParserX;

public class Mario {
	public final static int value = 4;
	/**
	 * positions re-calculate
	 */
	// coordonate of mario pixel and on the table
	private int posX;
	private int posY;
	private int pixelX;
	private int pixelY;
	
	private int piece = 0 ; //Number of pieces of mario

	// position of mario on the table and other.
	private int movement;
	private boolean mario = 									false;
	private boolean JUMP =      								false;
	public MData datas = new MData();
	
	

	public String actual = ""; // Actual srpite of the character

	public ParserX parse;

	public Mario(int x, int y) {
		setPosX(x);
		setPosY(y);
		this.setMovement(0);
		// first position for test (change)
	/*	pixelX = 59;
		pixelY = 215;
		*/
		
		setPixelX(getPosX()*(600/18)+10);
		setPixelY(getPosY()*(1000/24)+10);
		
		parse = new ParserX("org/maker/MiniParsing/remerber");
		parse.tabSprite();// stock datas
		rangement2();
		// this.actual = parse.tab[parse.tab.length - 2];
		
		this.actual = datas.rightW.get(0);
	
	}

	public void rangement() {
		// datas.rightW = new ArrayList<String>() ;
		datas.rightW.add(parse.tab[parse.tab.length - 2]);
		for (int ii = 13; ii < 16; ii++) {
			datas.rightW.add(parse.tab[ii]);

		}
		for (int ii = 12; ii > 7; ii--) {
			datas.leftW.add(parse.tab[ii]);
			System.out.println(parse.tab[ii] + " 2121");
		}
	}
	
	public void rangement2(){
		for(int ii = 0 ; ii < 3 ; ii++){
			datas.leftW.add(parse.tab[ii]);
		}
		for(int ii = 3 ; ii < 6 ; ii++){
			datas.rightW.add(parse.tab[ii]);
		}
	}

	public void mario(int life, int sens) {
		// Later gestion mario

	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getPiece() {
		return piece;
	}

	public void setPiece(int piece) {
		this.piece = piece;
	}

	public int getPixelY() {
		return pixelY;
	}

	public void setPixelY(int pixelY) {
		this.pixelY = pixelY;
	}

	public boolean isMario() {
		return mario;
	}

	public void setMario(boolean mario) {
		this.mario = mario;
	}

	public int getMovement() {
		return movement;
	}

	public void setMovement(int movement) {
		this.movement = movement;
	}

	public int getPixelX() {
		return pixelX;
	}

	public void setPixelX(double d) {
		this.pixelX = (int) d;
	}

	public boolean isJUMP() {
		return JUMP;
	}

	public void setJUMP(boolean jUMP) {
		JUMP = jUMP;
	}

}
