package j4kdemo.simpleexample;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import edu.ufl.digitalworlds.j4k.J4KSDK;

public class BDmain extends J4KSDK {

	//counter is used to calculate Frames Per Second at the end
	int counter=0;
	//time records the start time
	long time=0;
	
	private static final String FILEPATH = "c:/sandbox/";
	private static final String TESTFILENAME = "BDlandscape002.jpg";

	private static final int TOTALTICKS = 5000;
	private static final int TOTALMILLISECS = 300000;

	// These set the resolution that the sensor uses. Can either be 640x480 or 320x240
	static final int DEPTHX = 320;
	static final int DEPTHY = 240;
	// Number of agents
	static final int AGENTS = 100;
	//Number of deers
	static final int DEERS = 75;
	//Number of trees
	static final int TREES1 = 1000;
	static final int TREES2 = 1000;
	static final int TREES3 = 1000;
	//thisframe is the object that holds everything that does anything
	static BDframe thisframe;
	//If TESTING is true then instead of looking for kinect data it will use test data
	//Useful if you don't have a kinect but want to develop anyway
	static final boolean TESTING = true;
	
	//These 3 determine what happens with the kinect sensor data
	//On receiving a skeleton frame or colour frame, nothing happens
	@Override
	public void onSkeletonFrameEvent(boolean[] skeleton_tracked, float[] positions, float[] orientations, byte[] joint_status) {
		System.out.println("A new skeleton frame was received.");
	}

	@Override
	public void onColorFrameEvent(byte[] color_frame) {
		System.out.println("A new color frame was received.");
	}

	//On receiving a depth frame...
	@Override
	public void onDepthFrameEvent(short[] depth_frame, byte[] body_index, float[] xyz, float[] uv) {
		//If this is the first frame, set the start time
		if(counter==0) {
			time=new Date().getTime();
		}
		//Increment the counter so we can count how many frames we've handled
		counter+=1;
		//Send the depth information to thisframe
		thisframe.setPixels(depth_frame);
	}

	public static void main(String[] args)
	{
		BufferedImage testimg = null;

		//Check for correct version of Java
		if(System.getProperty("os.arch").toLowerCase().indexOf("64")<0)
		{
			System.out.println("WARNING: You are running a 32bit version of Java.");
			System.out.println("This may reduce significantly the performance of this application.");
			System.out.println("It is strongly adviced to exit this program and install a 64bit version of Java.\n");
		}
		
		//Setting up thisframe, whose behaviour is specified in RSj4kFrame
		thisframe = new BDframe(DEPTHX, DEPTHY, AGENTS, DEERS, TREES1, TREES2, TREES3, TESTING);
		thisframe.setUndecorated(true);
		thisframe.setVisible(true);
		thisframe.setExtendedState(Frame.MAXIMIZED_BOTH);
		thisframe.addKeyListener(thisframe);
		thisframe.setFocusable(true);
		thisframe.requestFocusInWindow();
		BDmain kinect=new BDmain();
		//Either initialises test data or starts kinect drivers, depending on setting of TESTING
		if (TESTING) {
			
			try {
				testimg = ImageIO.read(new File(FILEPATH + TESTFILENAME));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			short[] testdata = new short[DEPTHX * DEPTHY];
			
			for (int y = 0; y < DEPTHY; y++) {
				for (int x = 0; x < DEPTHX; x++) {
					//Gets RGB info from image file
					int a = testimg.getRGB(x, y);
					//Converts the blue channel to a value between 0-255. 
					//If greyscale data is used then all colour values are equal, blue is picked for no special reason. 
					short testval = (short) (a & 0x00FFL);
					//Puts that number into the data array.
					testdata[x + (DEPTHX * y)] = testval;
				}
			}
			
			for (int z = 0; z < TOTALTICKS; z++) {
				
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				thisframe.setPixels(testdata);
			}
		} else {
			kinect.setDepthResolution(DEPTHX, DEPTHY);
			kinect.start(J4KSDK.DEPTH);
			System.out.println(kinect.getElevationAngle());
		}


		//Sleep for 3000 seconds. For some reason we need this line in or it doesn't seem to work. It does mean that the programme quits 
		//after however many seconds though.
		try {Thread.sleep(TOTALMILLISECS);} catch (InterruptedException e) {}

		//After aforementioned seconds, we stop
		if (!TESTING) {
			kinect.stop();		
		}
		thisframe.dispose();
		System.out.println("FPS: "+kinect.counter*1000/(new Date().getTime()-kinect.time));

	}



}
