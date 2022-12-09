package j4kdemo.simpleexample;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.geom.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

//BDFrame handles the window which displays everything
public class BDframe extends JFrame implements KeyListener {
	private BDcellpanel contentPane;

	private static final int CELLWIDTH = 4;
	private static final int CELLHEIGHT = 4;
	
	private int j = 0, i = 0, a = 0, b = 0, c = 0;
	private static final int MAXX = 1280; //1280
	private static final int MAXY = 960; //960
	//Actual width and height of display
	private static int widthX, heightY, agents, deers, counter;
	private static BDenvironment theenvironment;
	private static final boolean SWAPX = true;
	private static final boolean SWAPY = false;
	private static boolean testing, heightmode, agentmode, alreadyExecuted;

	public Graphics GClass;
	
	//Constructor class, specifies data on startup. Initialises frame and sets arrays to 0
	public BDframe(int pwidthX, int pheightY, int pAgents, int pDeers, boolean pTesting) {

		//Calls superclass constructor. Doesn't do anything
		super("RSj4kFrame");

		//Sets width and height as values passed from 
		widthX = pwidthX;
		heightY = pheightY;
		agents = pAgents;
		deers = pDeers;
		testing = pTesting;
		heightmode = false;
		agentmode = true;
		counter = 0;

		theenvironment = new BDenvironment(widthX, heightY, agents, deers, testing);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int boundx, boundy;

		//Checks whether size is greater than max and if so, makes it max
		if ((widthX * CELLWIDTH) < MAXX) {
			boundx = (widthX * CELLWIDTH);
		} else {
			boundx = MAXX;
		}
		System.out.println("x = " + boundx);
		if ((heightY * CELLHEIGHT) < MAXY) {
			boundy = (heightY * CELLHEIGHT);
		} else {
			boundy = MAXY;
		}
		System.out.println("y = " + boundy);
		setBounds(100, 100, boundx, boundy);

		//initialise content pane
		contentPane = new BDcellpanel();

		//Makes contentpane the content pane
		setContentPane(contentPane);

		//Repaint refreshes the display when anything changes. Good to do every now and then
		contentPane.repaint();

	}

	public void toggleHeight() {
		if (heightmode) {
			heightmode = false;
		} else {
			heightmode = true;
		}
	}
	
	public void toggleAgents() {
		if (agentmode) {
			agentmode = false;
		} else {
			agentmode = true;
		}
	}


	//Turns the single-dimensioned array that comes from the kinect into a 2D array so that it's easier to 
	//manipulate and display
	public void setPixels(short[] depthPixels) {
		theenvironment.step(depthPixels, SWAPX, SWAPY);
		contentPane.repaint();
	}
	

	//Anything starting with @Override is an inherited method from the superclass, in this case JPanel.
	//These have to be here by law, but if they're empty they don't do anything, just shunt everything 
	//up to the superclass' method of the same name, which in this case also doesn't do anything. It's a Java thing.
	@Override
	public void keyPressed(KeyEvent arg0) {
	}

	//See above. As we're only interested in keys that are pressed and released (typed, see below)
	//we can ignore specific methods for pressing and releasing keys
	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	//This method handles anything that you want to happen when a key is pressed (and released).
	//Currently only switches between modes but you can do anything with this.
	@Override
	public void keyTyped(KeyEvent arg0) {
		char keypress = arg0.getKeyChar();
		switch (keypress) {
		case 'q':
			theenvironment.incTemp();
			break;
		case 'a':
			theenvironment.decTemp();
			break;
		case 'h':
			toggleHeight();
			break;
		}
	}
	
	public class BDcellpanel extends JPanel{

		private BufferedImage image;
		

	    public void ImagePanel() {
	       try {                
	          image = ImageIO.read(new File("D:/AR Sandbox/Tree_Imports/" + "elm2.png"));
	       } catch (IOException ex) {
	            // handle exception...
	       }
	    }

		//Constructor's empty as all startup tasks are dealt with in RSj4kFrame's constructor
		public BDcellpanel() {
			alreadyExecuted = true;
		}

		//Each cell that is displayed is actually a filled rectangle of CELLWIDTHxCELLHEIGHT pixels
		//This is where the actual displaying happens
		public void paintComponent (Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			if (j % 100 == 0) {
				i = i + 2;
		   }
			//Clear display
			g2.clearRect(0, 0, 5000, 5000);
			//Draw a rectangle for each cell. Does positioning here and gets colour from getCol
			for (int x = 0; x < widthX; x++) {
				for (int y = 0; y < heightY; y++) {
					g2.setPaint(theenvironment.getCell(x, y).getCellColor(heightmode));
					g2.fillRect((x) * CELLWIDTH, (y) * CELLHEIGHT, CELLWIDTH, CELLHEIGHT);
				}
			}
			
			//Go through list of agents
			if (agentmode) {
				for (BDagent thisag : theenvironment.getAgents()) {
					g2.setPaint(thisag.getAgentCol());
					g2.fillRect(thisag.getMyX() * CELLWIDTH, thisag.getMyY() * CELLHEIGHT, CELLWIDTH, CELLHEIGHT);
				}
			}

			//Go through list of deers
			if (agentmode) {
				for (BDdeer thisdr : theenvironment.getDeer()) {
					g2.setPaint(thisdr.getAgentCol());
					g2.fillRect(thisdr.getMyX() * CELLWIDTH, thisdr.getMyY() * CELLHEIGHT, CELLWIDTH, CELLHEIGHT);
				}
			}
			
			j++;
		}
	}
}
