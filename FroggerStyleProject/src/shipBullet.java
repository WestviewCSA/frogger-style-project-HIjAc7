import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class shipBullet{
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

	public shipBullet() {
		forward 	= getImage("/imgs/"+"ship bullet.gif");
		 //load the image for Tree
		

		//alter these
		width = 20;
		height = 20;
		
		x = -width;
		y = 300;
		
		vx = 0;
		vy = -5;
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}
	public shipBullet(int a, int b) {
		this();
		this.x = a;
		this.y = b;
		vx = 0;
		vy = -5;
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		init(x, y);
		
	}
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		x+=vx;
		y+=vy;		
		
		init(x,y);
		g2.drawImage(forward, tx, null);
		//g2.drawRect(x+25, y+25, width/2, height/2);
	}
	public boolean collided(UFO ufo) {
		Rectangle main = new Rectangle(ufo.getX(),ufo.getY(),ufo.getHeight(),ufo.getWidth());
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
			URL imageURL = shipBullet.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	public boolean pastBorder() {
		if(y<0) {
			return true;
		}
		return  false;
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
