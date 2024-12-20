import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Platform{
	private Image forward; 	
	private AffineTransform tx;
	
					//0-forward, 1-backward, 2-left, 3-right
	int width, height;
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	double scaleWidth = 1.0;		//change to scale image
	double scaleHeight = 1.0; 		//change to scale image
	int mode = 1;

	public Platform() {
		forward 	= getImage("/imgs/"+"platform.png"); //load the image for Tree
		

		//alter these
		width = 50;
		height = 50;
		
		x = -width;
		y = 300;
		
		vx = 5;
		vy = 0;
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
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

	public int getVx() {
		
		return vx;
	}

	public void setVx(int vx) {
		if(mode == 2) {
			vx*= -1;
		}
		this.vx = vx;
	}

	public int getVy() {
		return vy;
	}

	public void setVy(int vy) {
		this.vy = vy;
	}
	public boolean collided(Ship ship) {
		Rectangle main = new Rectangle(ship.getX()+1,ship.getY(),ship.getHeight()-5,ship.getWidth()-5);
		Rectangle thisObject = new Rectangle(x,y,width,height);
		return main.intersects(thisObject);
	}
	public Platform(int a, int b, int m) {
		this();
		this.x = a;
		this.y = b;
		mode = m;
		if(mode != 1) {
			vy*=-1;
			vx*=-1;
		}
	}

	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		
		x+=vx;
		y+=vy;	
		if(x>650) {
			x = -150;
		}
		
		if(x<-150) {
			x = 650;
		}
		
		
		init(x,y);
		g2.drawImage(forward, tx, null);
		//g2.drawRect(x, y, width, height);
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Platform.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
