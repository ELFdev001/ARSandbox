package j4kdemo.simpleexample;

import java.awt.Color;

public class BDagent {
	private static final int STARTFOOD = 100;
	private static final Color FISHER = Color.BLUE;
	private static final Color HUNTER = Color.RED;
	private static final Color GATHERER = Color.YELLOW;
	private static final Color DEAD = Color.BLACK;


	private int myX, myY, destX, destY, food, fruittaken, nutstaken, deadtimer;
	private boolean dead = false;
	private boolean wait = false;
	private int fishing = 1;
	private int hunting = 1;

	public BDagent(int pX, int pY) {
		super();
		myX = pX;
		myY = pY;
		food = STARTFOOD;
		fruittaken = nutstaken = 0;
		deadtimer = 0;
	}

	public boolean getWait() {
		return wait;
	}

	public void toggleWait() {
		if (wait) {
			wait = false;
		} else {
			wait = true;
		}
	}

	
	public int getHunting() {
		return hunting;
	}

	public void setHunting(int hunting) {
		this.hunting = hunting;
		if (hunting > 100) {
			hunting = 100;
		} else {
			if (hunting < 1) {
				hunting = 1;
			}
		}

	}

	public int getGathering() {
		int gath = fruittaken + nutstaken / 100;
		if (gath > 100) {
			gath = 100;
		}
		return gath;
	}
	
	public int getFishing() {
		return fishing;
	}

	public void setFishing(int fishing) {
		this.fishing = fishing;
		if (fishing > 100) {
			fishing = 100;
		} else {
			if (fishing < 1) {
				fishing = 1;
			}
		}
	}

	public int getMyX() {
		return myX;
	}


	public void setMyX(int pmyX) {
		myX = pmyX;
	}

	public int getDeadtimer() {
		return deadtimer;
	}


	public void incDeadtimer() {
		this.deadtimer++;
	}



	public int getMyY() {
		return myY;
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
			if (fishing > hunting && fishing > getGathering()) {
				return FISHER;
			} else if (hunting > fishing && hunting > getGathering()) {
				return HUNTER;
			} else {
				return GATHERER;
			}
		}
	}

	public void reduceExp() {
		if (fishing > 10) {
			if (Math.random() < 0.3) {
				fishing--;
			}
		}
		
		if (hunting > 10) {
			if (Math.random() < 0.1) {
				hunting--;
			}
		}
		
		if (getGathering() > 10) {
			fruittaken = fruittaken - 10;
			nutstaken = nutstaken - 10;
		}
	}
	
	public int getFood() {
		return food;
	}


	public void setFood(int food) {
		this.food = food;
	}


	public int getFruittaken() {
		return fruittaken;
	}


	public void setFruittaken(int fruittaken) {
		this.fruittaken = fruittaken;
	}


	public int getNutstaken() {
		return nutstaken;
	}


	public void setNutstaken(int nutstaken) {
		this.nutstaken = nutstaken;
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
