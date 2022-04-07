package j4kdemo.simpleexample;

import java.awt.Color;

public class BDdeer {

	private static final int STARTFOOD = 50;
	private static final Color LIVE = Color.CYAN;
	private static final Color DEAD = Color.DARK_GRAY;

	
	private int myX, myY, destX, destY, food, deadtimer;
	boolean dead = false;
	BDlocation[][] environment;

	public BDdeer(int pX, int pY) {
		super();
		myX = pX;
		myY = pY;
		food = STARTFOOD;
		deadtimer = 0;
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


	public int getDeadtimer() {
		return deadtimer;
	}


	public void incDeadtimer() {
		this.deadtimer++;
	}


	public void setMyY(int pmyY) {
		myY = pmyY;
	}


	public int getDestX() {
		return destX;
	}


	public void setDestX(int pdestX) {
		destX = pdestX;
	}


	public int getDestY() {
		return destY;
	}


	public void setDestY(int pdestY) {
		destY = pdestY;
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public void makeDead() {
		dead = true;
	}

	public Color getAgentCol() {
		if (dead) {
			return DEAD;
		} else {
			return LIVE;
		}
	}

	public int getFood() {
		return food;
	}


	public void setFood(int food) {
		this.food = food;
	}

	private int constrainVal(int pVal) {
		int thisval = pVal;
		if (thisval > 255) {
			thisval = 255;
		} else if (thisval < 0) {
			thisval = 0;
		}
		return thisval;
	}


	
}
