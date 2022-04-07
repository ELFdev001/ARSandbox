package j4kdemo.simpleexample;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.awt.Color;

public class BDenvironment {
	private int temperature = 200;

	private static final int LIVINGRANGE = 200;
	private static final int SHRUBLINE = 20;
	private static final int FRUITLINE = 60;
	private static final int NUTLINE = 25;
	private static final int MAXFOOD = 255;
	private static final int LIFEOFCORPSE = 500;
	private static final int AGENTSPAWNCHANCE = 1;
	private static final int DEERSPAWNCHANCE = 1;
	private static final int MAXAGENTS = 1000;
	private static final int MAXDEERS = 1000;
	private static final int MAXTREES1 = 2000;
	private static final int MAXTREES2 = 2000;
	private static final int MAXTREES3 = 2000;

	
	private static BDlocation[][] cells;
//	private static BDagent[] agentlist;
//	private static BDdeer[] deerlist;
	List<BDagent> agentlist = new ArrayList<BDagent>();
	List<BDdeer> deerlist = new ArrayList<BDdeer>();
	List<BDtree1> treelist1 = new ArrayList<BDtree1>();
	List<BDtree2> treelist2 = new ArrayList<BDtree2>();
	List<BDtree3> treelist3 = new ArrayList<BDtree3>();
	List<BDagent> newagentlist = new ArrayList<BDagent>();
	List<BDdeer> newdeerlist = new ArrayList<BDdeer>();
	List<BDtree1> newtreelist1 = new ArrayList<BDtree1>();
	List<BDtree2> newtreelist2 = new ArrayList<BDtree2>();
	List<BDtree3> newtreelist3 = new ArrayList<BDtree3>();

	
	private int agents, deers, trees1, trees2, trees3, widthX, heightY, size;
	private static boolean testing, heightmode;

	public BDenvironment(int pWidth, int pHeight, int pAgents, int pDeer, int pTrees1, int pTrees2, int pTrees3, boolean pTesting) {
		widthX = pWidth;
		heightY = pHeight;
		testing = pTesting;
		heightmode = false;
		cells = new BDlocation[widthX][heightY];
		for (int x = 0; x < widthX; x++) {
			for (int y = 0; y < heightY; y++) {
				cells[x][y] = new BDlocation(x, y, size);
			}
		}

		agents = pAgents;
		deers = pDeer;
		trees1 = pTrees1;
		trees2 = pTrees2;
		trees3 = pTrees3;

		for (int x = 0; x < agents; x++) {
			agentlist.add(new BDagent(pickX(), pickY()));
		}

		for (int x = 0; x < deers; x++) {
			deerlist.add(new BDdeer(pickX(), pickY()));
		}

		for (int x = 0; x < trees1; x++) {
			treelist1.add(new BDtree1(pickX(), pickY(), pickSize(), pickAge()));
		}
		for (int x = 0; x < trees2; x++) {
			treelist2.add(new BDtree2(pickX(), pickY(), pickSize(), pickAge()));
		}
		for (int x = 0; x < trees3; x++) {
			treelist3.add(new BDtree3(pickX(), pickY(), pickSize(), pickAge()));
		}
	}

	public BDlocation getCell(int pX, int pY) {
		return cells[pX][pY];
	}

	public void incTemp() {
		if (temperature - LIVINGRANGE < 255) {
			temperature++;			
		}

	}

	public void decTemp() {
		if (temperature + LIVINGRANGE > 0) {
			temperature--;
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
	
	public ArrayList<BDagent> getAgents() {
		return (ArrayList<BDagent>) agentlist;
	}
	
	public ArrayList<BDdeer> getDeer() {
		return (ArrayList<BDdeer>) deerlist;
	}
	
	public ArrayList<BDtree1> getTree1() {
		return (ArrayList<BDtree1>) treelist1;
	}
	
	public ArrayList<BDtree2> getTree2() {
		return (ArrayList<BDtree2>) treelist2;
	}
	
	public ArrayList<BDtree3> getTree3() {
		return (ArrayList<BDtree3>) treelist3;
	}
	
	
	private int pickX() {
		return (int) ((Math.random() * widthX));
	}

	private int pickY() {
		return (int) ((Math.random() * heightY));
	}
	
	private int pickSize() {
		return (int) ((Math.random() * 3 + 1));
	}
	
	private int pickAge() {
		return (int) ((Math.random() * 300));
	}

	/*private Color pickColor() {
		Random rand = new Random();
		int i;
		
		i = rand.nextInt(3);
		
		if (i == 0) {
			return Color.MAGENTA;
		}
		if (i == 1) {
			return Color.ORANGE;
		} else {
			return Color.PINK;
	  }
	  }
	*/

	public void stepCells() {
		for (int x = 0; x < widthX; x++) {
			for (int y = 0; y < heightY; y++) {

				if (cells[x][y].getHeight() > temperature) {
					cells[x][y].makeSnow();
				} else if (cells[x][y].getHeight() < temperature - LIVINGRANGE) {
					cells[x][y].makeWater();
				} else {
					cells[x][y].makeLand();
					if (cells[x][y].getHeight() > temperature - LIVINGRANGE + SHRUBLINE) {
						cells[x][y].growShrubs();
//						System.out.println("Shrubs in " + x + "," + y + " is now " + cells[x][y].getShrubs());
					}

					if (cells[x][y].getHeight() > temperature - LIVINGRANGE + FRUITLINE && cells[x][y].getHeight() < temperature - FRUITLINE ) {
						cells[x][y].growFruits();
//						System.out.println("Fruits in " + x + "," + y + " is now " + cells[x][y].getFruits());
					}

					if (cells[x][y].getHeight() > temperature - LIVINGRANGE + NUTLINE && cells[x][y].getHeight() < temperature - NUTLINE ) {
						cells[x][y].growNuts();
//						System.out.println("Nuts in " + x + "," + y + " is now " + cells[x][y].getNuts());
					}

				}
			}
		}
	}

	public void stepDeer() {

		for (Iterator<BDdeer> it = deerlist.iterator(); it.hasNext();) {
			BDdeer thisdeer = it.next();
			if (!thisdeer.isDead()) {

				//movement
				int targetX, targetY;
				if (thisdeer.getDestX() > 0) {

					if (thisdeer.getMyX() > thisdeer.getDestX()) {
						targetX = thisdeer.getMyX() - 1;
					} else if (thisdeer.getMyX() < thisdeer.getDestX()) {
						targetX = thisdeer.getMyX() + 1;
					} else {
						targetX = thisdeer.getMyX();
					}

					if (thisdeer.getMyY() > thisdeer.getDestY()) {
						targetY = thisdeer.getMyY() - 1;
					} else if (thisdeer.getMyY() < thisdeer.getDestY()) {
						targetY = thisdeer.getMyY() + 1;
					} else {
						targetY = thisdeer.getMyY();
					}

					//what to do in snow or water
					if ((!cells[targetX][targetY].getSnow() && !cells[targetX][targetY].getWater()) || (!cells[thisdeer.getDestX()][thisdeer.getDestY()].getSnow() && !cells[thisdeer.getDestX()][thisdeer.getDestY()].getWater())) {
						thisdeer.setMyX(targetX);
						thisdeer.setMyY(targetY);
					} else {
						thisdeer.setDestX(pickX());
						thisdeer.setDestY(pickY());
					}

				} else {
					thisdeer.setDestX(pickX());
					thisdeer.setDestY(pickY());
				}

				if (thisdeer.getMyX() == thisdeer.getDestX() && thisdeer.getMyY() == thisdeer.getDestY()) {
					thisdeer.setDestX(pickX());
					thisdeer.setDestY(pickY());
				} 

				//food
				if (thisdeer.getFood() < MAXFOOD) {

					int thesefruit = cells[thisdeer.getMyX()][thisdeer.getMyY()].getFruits();
					int theseshrubs = cells[thisdeer.getMyX()][thisdeer.getMyY()].getShrubs();

					if (theseshrubs > 0) {
						thisdeer.setFood(thisdeer.getFood() + theseshrubs);
						theseshrubs = 0;
					} else if (thesefruit > 0) {
						thisdeer.setFood(thisdeer.getFood() + thesefruit);
						thesefruit = 0;
					}

					cells[thisdeer.getMyX()][thisdeer.getMyY()].setFruits(thesefruit);
					cells[thisdeer.getMyX()][thisdeer.getMyY()].setShrubs(theseshrubs);
				}

				//spawn
				if (thisdeer.getFood() == MAXFOOD) {
					if ((Math.random() * 100) < DEERSPAWNCHANCE && deerlist.size() < MAXDEERS) {
						newdeerlist.add(new BDdeer(thisdeer.getMyX(), thisdeer.getMyY()));
					}
				}
				
				thisdeer.setFood(thisdeer.getFood() - 1);
				if (thisdeer.getFood() <= 0) {
					thisdeer.makeDead();
				}
			} else {
				thisdeer.incDeadtimer();
				if (thisdeer.getDeadtimer() > LIFEOFCORPSE) {
					it.remove();
				}
			}
		}
		deerlist.addAll(newdeerlist);
		newdeerlist.clear();
	}


	public void stepAgents() {
		for (Iterator<BDagent> it = agentlist.iterator(); it.hasNext();) {
			BDagent thisagent = it.next();
			if (!thisagent.isDead()) {

				//movement
				int targetX, targetY;
				if (thisagent.getDestX() > 0) {

					if (thisagent.getMyX() > thisagent.getDestX()) {
						targetX = thisagent.getMyX() - 1;
					} else if (thisagent.getMyX() < thisagent.getDestX()) {
						targetX = thisagent.getMyX() + 1;
					} else {
						targetX = thisagent.getMyX();
					}

					if (thisagent.getMyY() > thisagent.getDestY()) {
						targetY = thisagent.getMyY() - 1;
					} else if (thisagent.getMyY() < thisagent.getDestY()) {
						targetY = thisagent.getMyY() + 1;
					} else {
						targetY = thisagent.getMyY();
					}

					//what to do in snow or water
					if (!cells[targetX][targetY].getSnow() && !cells[targetX][targetY].getWater()) {
						thisagent.setMyX(targetX);
						thisagent.setMyY(targetY);
					} else if (cells[thisagent.getDestX()][thisagent.getDestY()].getSnow() || cells[thisagent.getDestX()][thisagent.getDestY()].getWater()) {
						thisagent.setDestX(pickX());
						thisagent.setDestY(pickY());
					} else {
						if (thisagent.getWait()) {
							thisagent.setMyX(targetX);
							thisagent.setMyY(targetY);
							thisagent.toggleWait();
						} else {
							thisagent.toggleWait();
						}
					}

				} else {
					thisagent.setDestX(pickX());
					thisagent.setDestY(pickY());
				}

				if (thisagent.getMyX() == thisagent.getDestX() && thisagent.getMyY() == thisagent.getDestY()) {
					thisagent.setDestX(pickX());
					thisagent.setDestY(pickY());
				} 

				//food
				if (thisagent.getFood() < MAXFOOD) {

					int thesefruit = cells[thisagent.getMyX()][thisagent.getMyY()].getFruits();
					int desenuts = cells[thisagent.getMyX()][thisagent.getMyY()].getNuts();

					if (thisagent.getFruittaken() < thisagent.getNutstaken() && thesefruit > 0) {
						thisagent.setFood(thisagent.getFood() + thesefruit);
						thisagent.setFruittaken(thisagent.getFruittaken() + thesefruit);
						thesefruit = 0;
					} else if (thisagent.getNutstaken() < thisagent.getFruittaken() && desenuts > 0) {
						thisagent.setFood(thisagent.getFood() + desenuts);
						thisagent.setNutstaken(thisagent.getNutstaken() + desenuts);
						desenuts = 0;
					} else if (thesefruit > 0) {
						thisagent.setFood(thisagent.getFood() + thesefruit);
						thisagent.setFruittaken(thisagent.getFruittaken() + thesefruit);
						thesefruit = 0;
					} else if (desenuts > 0) {
						thisagent.setFood(thisagent.getFood() + desenuts);
						thisagent.setNutstaken(thisagent.getNutstaken() + desenuts);
						desenuts = 0;
					}

					cells[thisagent.getMyX()][thisagent.getMyY()].setFruits(thesefruit);
					cells[thisagent.getMyX()][thisagent.getMyY()].setNuts(desenuts);
					
					//Fishing
					if (cells[thisagent.getMyX()][thisagent.getMyY()].getWater()) {
						if ((Math.random() * 100) < thisagent.getFishing()) {
							thisagent.setFood(MAXFOOD);
						}
						thisagent.setFishing(thisagent.getFishing() + 1);
					}
					
					//Hunting
					for (BDdeer huntdeer : deerlist) {
						if (thisagent.getMyX() == huntdeer.getMyX() && thisagent.getMyY() == huntdeer.getMyY()) {
							if (Math.random() * 100 < thisagent.getHunting()) {
								thisagent.setFood(MAXFOOD);
								huntdeer.makeDead();
							}
							thisagent.setHunting(thisagent.getHunting() + 5);
						}
					}
				}
				
				//spawn
				if (thisagent.getFood() == MAXFOOD) {
					if ((Math.random() * 100) < AGENTSPAWNCHANCE && agentlist.size() < MAXAGENTS) {
						newagentlist.add(new BDagent(thisagent.getMyX(), thisagent.getMyY()));
					}
				}


				thisagent.setFood(thisagent.getFood() - 1);
				if (thisagent.getFood() <= 0) {
					thisagent.makeDead();
				}
				
				thisagent.reduceExp();
			} else {
				thisagent.incDeadtimer();
				if (thisagent.getDeadtimer() > LIFEOFCORPSE) {
					it.remove();
				}
			}
		}
		agentlist.addAll(newagentlist);
		newagentlist.clear();

	}
	
	public void growTree() {
		for (Iterator<BDtree1> it = treelist1.iterator(); it.hasNext();) {
			BDtree1 thistree1 = it.next();
		    thistree1.getTreeCol();
		    thistree1.getMySize();
		    thistree1.getMyAge();

		}
		for (Iterator<BDtree2> it = treelist2.iterator(); it.hasNext();) {
			BDtree2 thistree2 = it.next();
		    thistree2.getTreeCol();
		    thistree2.getMySize();
		    thistree2.getMyAge();

		}
		for (Iterator<BDtree3> it = treelist3.iterator(); it.hasNext();) {
			BDtree3 thistree3 = it.next();
		    thistree3.getTreeCol();
		    thistree3.getMySize();
		    thistree3.getMyAge();

		}
		//increment scalers every tick
	}

	public void step(short[] pPixels, boolean pSwapX, boolean pSwapY) {
		for (int x = 0; x < widthX; x++) {
			for (int y = 0; y < heightY; y++) {
				int t, u;
				//This is where the X and/or Y axes are swapped depending on the position of the kinect sensor 
				if (pSwapX) {
					t = widthX - x - 1;
				} else {
					t = x;
				}
				if (pSwapY) {
					u = (heightY - y - 1) * widthX;
				} else {
					u = y * widthX;
				}

				cells[x][y].setCellheight(pPixels[t + u], testing);
				//				cells = cells[x][y].step(pPixels[t + u], temperature, cells, testing);

			}
		}

		/*		for (int x = 0; x < agents; x++) {
			cells = agentlist[x].step(cells);
		}
		 */
		stepCells();
		stepAgents();
		stepDeer();
		growTree();
	}
}
