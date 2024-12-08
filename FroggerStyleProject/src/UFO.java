import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class UFO{
	private Image forward; 	
	private AffineTransform tx;
	
					//0-forward, 1-backward, 2-left, 3-right
	int width, height;
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	double scaleWidth = 1.0;		//change to scale image
	double scaleHeight = 1.0; 		//change to scale image
	int mode = 1;
	int phase = 0;

	public UFO() {
		forward 	= getImage("/imgs/"+"ufo gif.gif");
		 //load the image for Tree
		

		//alter these
		width = 100;
		height = 100;
		
		x = -width;
		y = 300;
		
		vx = 5;
		vy = 0;
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}
	public UFO(int a, int b, int m) {
		this();
		this.x = a;
		this.y = b;
		mode = m;
		if(mode != 1) {
			vx*=1;
		}
	}
	public UFO(int a, int b, int m, int f) {
		this();
		this.x = a;
		this.y = b;
		mode = m;
		forward 	= getImage("/imgs/"+"ufo gif.gif");
	}

	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		
		x+=vx;
		y+=vy;	
		if(x>500) {
			vx*=-1;
		}	
		if(x<0) {
			vx*=-1;
		}
	
		init(x,y);
		g2.drawImage(forward, tx, null);
		//g2.drawRect(x+25, y+25, width/2, height/2);
	}
	public boolean collided(Ship ship) {
		Rectangle main = new Rectangle(ship.getX()+1,ship.getY(),ship.getHeight()-5,ship.getWidth()-5);
		Rectangle thisObject = new Rectangle(x, y, width, height);
		return main.intersects(thisObject);
	}
	public boolean collided(shipBullet bullet) {
		Rectangle main = new Rectangle(bullet.getX(),bullet.getY(),bullet.getHeight(),bullet.getWidth());
		Rectangle thisObject = new Rectangle(x, y, width, height);
		return main.intersects(thisObject);
	}
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = UFO.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	public void setVx(int vx) {
	
		this.vx = vx;
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
	public int getVy() {
		return vy;
	}
	public void setVy(int vy) {
		this.vy = vy;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getVx() {
		return vx;
	}
	
}
