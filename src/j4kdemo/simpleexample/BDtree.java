package j4kdemo.simpleexample;

import java.awt.Color;
import java.util.Random;

public class BDtree {
	
	public static final Color TREE1 = Color.MAGENTA;
	public static final Color TREE2 = Color.ORANGE;
	public static final Color TREE3 = Color.PINK;
	
	private boolean Tree1, Tree2, Tree3;
	private char treetype;
	private double distribution;
	public int myX, myY;
	public Color myColor;
	public int myDBH, myHeight, myCRadius, myCDiameter;
	
	boolean dead = false;
	BDlocation[][] environment;
	
	public BDtree(int pX, int pY) {
		super();
		myX = pX;
		myY = pY;
	}
	
	public int getMyX() {
		return myX;
	}


	public void setMyX(int pmyX) {
		myX = pmyX;
	}


	public int getMyY() {
		return myY;
	}
	
	public void setMyY(int pmyY) {
		myY = pmyY;
	}
	
	public void setSpecies() {
		distribution = 0.33;
	}
	
	public Color getTreeCol() {

		Random rand = new Random();
		int i;
		i = rand.nextInt(3);
		
		if (i == 0) {;
			return TREE1;
		}
		if (i == 1) {;
			return TREE2;
		} else {
			return TREE3;	
	}
}
	
	public void getMyColor(Color pmyColor) {
		myColor = pmyColor;
	}
	
}


