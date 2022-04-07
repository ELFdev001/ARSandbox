package j4kdemo.simpleexample;

import java.awt.Color;
import java.util.Random;

public class BDlocation {
	private static final int MAXDEPTH = 8200;
	private static final int MINDEPTH = 6800;
	private static final int SHRUBGROWTH = 1;
	private static final int FRUITGROWTH = 3;
	private static final int NUTGROWTH = 2;


	private int x, y, size;
	private int cellheight;
	private int shrubs, fruits, nuts;
	private boolean water;
	private boolean snow;
	private int temp;


	public BDlocation(int px, int py, int psize) {
		super();
		x = px;
		y = py;
		size = psize;
		cellheight = 0;
		water = false;
		snow = false;
		shrubs = fruits = nuts = 0;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int getSize() {
		return size;
	}

	public int getHeight() {
		return cellheight;
	}

	public int getShrubs() {
		return shrubs;
	}

	public void setShrubs(int shrubs) {
		this.shrubs = shrubs;
	}

	public int getFruits() {
		return fruits;
	}

	public void setFruits(int fruits) {
		this.fruits = fruits;
	}

	public void setCellheight(short pDepth, boolean pTesting) {
		if (!pTesting) {
			double range = MAXDEPTH - MINDEPTH;
			double tempheight = ((pDepth - MINDEPTH) / range) * 255;
//			this.cellheight = ((pDepth - MINDEPTH) / range) * 255;
			cellheight = constrainVal((int) tempheight);
			cellheight = 255 - cellheight;
//			System.out.println("cellheight is " + cellheight + " because pDepth is " + pDepth);
		} else {
			this.cellheight = pDepth;
		}
	}

	public int getNuts() {
		return nuts;
	}

	public void setNuts(int nuts) {
		this.nuts = nuts;
	}

	public void makeSnow() {
		snow = true;
		water = false;
		shrubs = fruits = nuts = 0;
	}

	public void makeWater() {
		water = true;
		snow = false;
		shrubs = fruits = nuts = 0;
	}

	public void makeLand() {
		water = false;
		snow = false;
	}

	public boolean getSnow() {
		return snow;
	}

	public boolean getWater() {
		return water;
	}

	public void growShrubs() {
		if (Math.random() * 100 < SHRUBGROWTH) {
			shrubs++;
			shrubs = constrainVal(shrubs);
		}
	}

	public void growFruits() {
		if (Math.random() * 100 < FRUITGROWTH) {
			fruits++;
			fruits = constrainVal(fruits);
		}
	}

	public void growNuts() {
		if (Math.random() * 100 < NUTGROWTH) {
			nuts++;
			nuts = constrainVal(nuts);
		}
	}

	public Color getCellColor(boolean pHeightmode) {
		
		if (pHeightmode) {
			if (cellheight % 50 < 5) {
				return Color.BLACK;
			} else {
				return new Color(cellheight, cellheight, cellheight);
			}
			
		} else {
			//Checking whether depth is greater than 255 effectively tests whether the depth value comes from the kinect (>255) or a test image (<255)
			if (water){
				return new Color(0, 0, cellheight);
			}	else if (snow) {
				return Color.WHITE;
			} else {
				//			return new Color(constrainVal(cellheight + (shrubs / 10)), constrainVal(cellheight + (fruits / 10)), constrainVal(cellheight + (nuts / 10)));
				if (shrubs > fruits && shrubs > nuts) {
					return new Color(cellheight, 255, cellheight);
				} else if (fruits > shrubs && fruits > nuts) {
					return new Color(255, cellheight, cellheight);
				} else if (nuts > fruits && nuts > shrubs) {
					return new Color(0, cellheight, cellheight);
				} else {
					return Color.PINK;
				}
			}
		}
	}


	public boolean isNull() {
		if (x == 0 && y == 0) {
			return true;
		} else {
			return false;
		}
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

	public boolean isAdjacent(BDlocation pLoc) {
		if (pLoc.isNull() || this.isNull()) {
			return false;
		} else if (Math.abs(x - pLoc.getX()) <= 1 && Math.abs(y - pLoc.getY()) <= 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isEqual(BDlocation pLoc) {
		if (pLoc.getX() == x && pLoc.getY() == y) {
			return true;
		} else {
			return false;
		}

	}

}
