import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class EndScreen{
	private Image start; 
	private Image end; 
	private Image playing; 
	private AffineTransform tx;
	private boolean hasPlayed = false;
	private boolean play = false;
	
	private int dir = 0;				//0-forward, 1-backward, 2-left, 3-right
	private long startTime;
	private long endTime;
	private long diff;
	private int mode;
	int width, height;
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	double scaleWidth = 10.0;		//change to scale image
	double scaleHeight = 13.0; 		//change to scale image

	public EndScreen() {
		start= getImage("/imgs/"+"game-over.png"); //load the image for Tree
		end = getImage("/imgs/"+"win-screen.png");
		playing = getImage("/imgs/" + "Empty.png");
		
		//alter these
		width = 600;
		height = 800;
		
		x = 0;
		y = 0;
		
		vx = 0;
		vy = 0;
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}
	public EndScreen(int a, int b) {
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
		
		switch(dir) {
		case 0:
			g2.drawImage(start, tx, null);
			break;
		case 1:
			
			g2.drawImage(end, tx, null);

			break;
		case 2:
			g2.drawImage(playing,tx,null);
			break;
		}
	
			
		
		

	}

	public int getDir() {
		return dir;
	}
	public void setDir(int dir) {
		this.dir = dir;
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
			URL imageURL = EndScreen.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
