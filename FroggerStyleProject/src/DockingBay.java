import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class DockingBay{
	private Image start; 
	private Image end; 
	private Image transition; 
	private AffineTransform tx;
	private boolean hasPlayed = false;
	private boolean play = false;
	
	private int dir = 0;				//0-forward, 1-backward, 2-left, 3-right
	private long startTime;
	private long endTime;
	private long diff;
	int width, height;
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	double scaleWidth = 0.3;		//change to scale image
	double scaleHeight = 0.3; 		//change to scale image

	public DockingBay() {
		start= getImage("/imgs/"+"pixil-frame-24.png"); //load the image for Tree
		end = getImage("/imgs/"+"end-frame.png");
		
		//alter these
		width = 90;
		height = 90;
		
		x = 20;
		y = 62;
		
		vx = 0;
		vy = 0;
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}
	public DockingBay(int a, int b) {
		this();
		this.x = a;
		this.y = b;
	}

	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		x+=vx;
		y+=vy;	
		
		init(x,y);
		if(dir == 1) {
			endTime = System.currentTimeMillis();
			diff = endTime - startTime;
			if(diff>2500) {
				dir = 2;
			}
		}
		switch(dir) {
		case 0:
			g2.drawImage(start, tx, null);
			break;
		case 1:
			transition=getImage("/imgs/"+"start-frame.gif");
			g2.drawImage(transition, tx, null);
			break;
		case 2:
			
			g2.drawImage(end, tx, null);

			break;
		}
		
		

	}
	
	public int getDir() {
		return dir;
	}
	public void setDir(int dir) {
		this.dir = dir;
		startTime = System.currentTimeMillis();
		endTime = System.currentTimeMillis();
	}
	public boolean isHasPlayed() {
		return hasPlayed;
	}
	public void setHasPlayed(boolean hasPlayed) {
		this.hasPlayed = hasPlayed;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = DockingBay.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
