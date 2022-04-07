package j4kdemo.simpleexample;

import java.awt.Graphics2D;

public class BDdrawtree {
	
	public void drawTree(Graphics2D pg, int x, int y, int pr) {
		  x = x-(pr/2);
		  y = y-(pr/2);
		  pg.fillOval(x,y,pr,pr);
		}
}
