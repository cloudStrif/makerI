package org.maker.levels;
/**
 * this class is a grid , it contains a field and a basic camera with 
 * 18X24 
 */

public class Grid {
	//bacis field
	private String[][] field;
	private String[][] camera;
	private boolean[][] clignote;
	// sizes of fields
	private static int s;
	private static int s2;

	private String ImageTile = "iso.png";

	private int posXinit = 0;
	private int posYinit = 0;
	
	/**
	 * init a grid with his camera 
	 * @param s
	 * @param s2
	 */
	public Grid(int s, int s2) {
		this.s=s ;
		this.s2=s2 ;
		
		setField(new String[s][s2]);
		setCamera(new String[18][24]);// arbitrary
		setClignote(new boolean[18][24]);// all in false at the begining
		init(18, 24, getCamera());
		init(s, s2, getField());
	}
	
	public Grid(int s1){
		this.s = s1;
		this.s2 = s1 ; 
		setField(new String[s][s2]);
		setCamera(new String[s][s2]);// arbitrary
		setClignote(new boolean[s][s2]);// all in false at the begining
		init(s, s2, getCamera());
		init(s, s2, getField());
	}
	

	/**
	 * Redimention of camera size
	 */
	public void setPendule(int s, int s2) {
		setCamera(new String[s][s2]);
	}

	/*
	 * refresh the camera from the current field
	 */
	public void refresh() {
		// System.out.println("========="+posYinit +" "+posXinit);
		int ii = 0;
		int jj = 0;
		for (int i = getPosYinit(); i < getPosYinit() + 18; i++) {
			for (int j = getPosXinit(); j < getPosXinit() + 24; j++) {
				getCamera()[ii][jj] = getField()[i][j];
				System.out.print(getCamera()[ii][jj]);
				if (jj < 24 - 1)
					jj++;

			}
			if (ii < 18 - 1)
				ii++;
			jj = 0;
			System.out.println();
		}
		ii = jj = 0;
		System.out.println();
		// debug(camera);
	}

	public void refreshclone(int s, int t) {
		int ii = 0;
		int jj = 0;
		for (int i = getPosYinit(); i < getPosYinit() + s; i++) {
			for (int j = getPosXinit(); j < getPosXinit() + t; j++) {
				getCamera()[ii][jj] = getField()[i][j];
			//	System.out.print(getCamera()[ii][jj]);
				if (jj < 24 - 1)
					jj++;

			}
			if (ii < 18 - 1)
				ii++;
			jj = 0;
			//System.out.println();
		}
		ii = jj = 0;
	}

	public void init(int v, int y, String[][] str) {
		for (int i = getPosYinit(); i < v; i++) {
			for (int j = getPosXinit(); j < y; j++) {
				str[i][j] = "N";
			}
		}
	}

	public void debug(String[][] tab) {
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[0].length; j++) {
				System.out.print(tab[i][j]);
			}
			System.out.println();
		}
	}

	public void clearAll() {
		for (int i = 0; i < s; i++) {
			for (int j = 0; j < s2; j++) {
				field[i][j] = "N";
			}
		}
		for (int i = 0; i < 18; i++) {
			for (int j = 0; j < 24; j++) {
				camera[i][j] = "N";
			}
		}
		refresh();

	}
	
	public String [][] clonE() {
		String [][] t = new String[100][100 ] ; 
		for(int  i =0 ; i< 100 ; i++){
			for(int j =0 ; j< 100; j++){
				t[i][j] = field[i][j];
			}
		}
		return t ; 
	}

	//====================================//
	//GETTERS  AND  SETTERS               //
	//====================================//
	public static int getS() {
		return s;
	}

	public static void setS(int s) {
		Grid.s = s;
	}

	public int getPosXinit() {
		return posXinit;
	}

	public void setPosXinit(int posXinit) {
		this.posXinit = posXinit;
	}

	public String[][] getField() {
		return field;
	}

	public void setField(String[][] field) {
		this.field = field;
	}

	public int getPosYinit() {
		return posYinit;
	}

	public void setPosYinit(int posYinit) {
		this.posYinit = posYinit;
	}

	public static int getS2() {
		return s2;
	}

	public static void setS2(int s2) {
		Grid.s2 = s2;
	}

	public boolean[][] getClignote() {
		return clignote;
	}

	public void setClignote(boolean[][] clignote) {
		this.clignote = clignote;
	}

	public String[][] getCamera() {
		return camera;
	}

	public void setCamera(String[][] camera) {
		this.camera = camera;
	}

	public String getImageTile() {
		return ImageTile;
	}

	public void setImageTile(String imageTile) {
		ImageTile = imageTile;
	}

}
