package j4kdemo.simpleexample;

import java.awt.Color;
import java.util.Random;

public class BDtree1 {
	
	public static final Color TREE1 = Color.MAGENTA;
	
	private boolean Tree1, Tree2, Tree3;
	private char treetype;
	private double distribution;
	public int myX, myY, mySize, myAge;
	public Color myColor;
	public int myDBH, myHeight, myCRadius, myCDiameter;
	
	boolean dead = false;
	BDlocation[][] environment;
	
	public BDtree1(int pX, int pY, int pSize, int pAge) {
		super();
		myX = pX;
		myY = pY;
		mySize = pSize;
		myAge = pAge;
	}
	
	public int getMyAge() {
		return myAge;
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
	
	public int getMySize() {
		return mySize;
	}
	
	public void setMySize(int pmySize) {
		mySize = pmySize;
	}
	
	public void setSpecies() {
		distribution = 0.33;
	}
	
	public Color getTreeCol() {
		return TREE1;
	}

	
	public void getMyColor(Color pmyColor) {
		myColor = pmyColor;
	}
	
}