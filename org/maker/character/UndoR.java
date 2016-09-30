package org.maker.character;

import java.util.ArrayList;
import java.util.Stack;

import org.maker.levels.Grid;

public class UndoR {
	public Stack <String[][]> field = new Stack<String[][]>();

	public UndoR() {
		 
	}

	public void adde(Grid grid) {	
		field.push(grid.clonE());
	}
	
	public void affiche(String [][] tab){
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[0].length; j++) {
				System.out.print(tab[i][j]);
			}
			System.out.println();
		}
	}

	public String [][] undo() {
		System.out.println(field.size());
		if(!field.isEmpty()){
			return field.pop();
		}
		return null ;
	}

}
