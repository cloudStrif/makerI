package org.maker.levels;

import java.io.Serializable;

public class TilesSprite implements Serializable {
	/**
	 * Stockage des donnes
	 */
	private int pocX;
	private int pocY;

	private int pocZ;
	private int pocT;

	private int dimX = 1;
	private int dimY = 1;

	private String name = ""; // Value on the TABLE

	public TilesSprite(int a, int b, int c, int d, String name) {
		setPocX(a);
		setPocY(b);
		setPocZ(c);
		setPocT(d);
		this.name = name;
	}

	public int getPocX() {
		return pocX;
	}

	public void setPocX(int pocX) {
		this.pocX = pocX;
	}

	public int getPocY() {
		return pocY;
	}

	public void setPocY(int pocY) {
		this.pocY = pocY;
	}

	public int getPocZ() {
		return pocZ;
	}

	public void setPocZ(int pocZ) {
		this.pocZ = pocZ;
	}

	public int getPocT() {
		return pocT;
	}

	public void setPocT(int pocT) {
		this.pocT = pocT;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDimX() {
		return dimX;
	}

	public void setDimX(int dimX) {
		this.dimX = dimX;
	}

	public int getDimY() {
		return dimY;
	}

	public void setDimY(int dimY) {
		this.dimY = dimY;
	}
}
