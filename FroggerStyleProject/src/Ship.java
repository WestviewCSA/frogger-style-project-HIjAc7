import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Ship{
	private Image forward, backward, left, right; 	
	private AffineTransform tx;
	
	private int dir = 1; 					//0-forward, 1-backward, 2-left, 3-right
	int width, height;
	private int x, y;						//position of the object
	private int vx, vy;						//movement variables
	double scaleWidth = 0.5;		//change to scale image
	double scaleHeight = 0.5; 		//change to scale image

	public Ship() {
		forward 	= getImage("/imgs/"+"shipgifnoback.gif"); //load the image for Tree
		backward 	= getImage("/imgs/"+"Empty.png"); //load the image for Tree
		//left 		= getImage("/imgs/"+"left.png"); //load the image for Tree
		//right 		= getImage("/imgs/"+"right.png"); //load the image for Tree

		//alter these
		width = 50;
		height = 50;
		x = 275;
		y = 700;
		vx = 0;
		vy = 0;
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
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
		//g2.drawRect(x+1, y, width-5, height-5);
		switch(dir) {
		case 0:
			g2.drawImage(forward, tx, null);
			break;
		case 1:
			g2.drawImage(backward, tx, null);
//
			break;
	//	case 2:
	//		g2.drawImage(left, tx, null);

	//		break;
	//	case 3:
	//		g2.drawImage(right, tx, null);
		//	break;
		
		}

	}
	public boolean collided(ufoBullet bullet) {
		Rectangle main = new Rectangle(bullet.getX(),bullet.getY(),bullet.getHeight(),bullet.getWidth());
		Rectangle thisObject = new Rectangle(x+25, y+25, width/2, height/2);
		return main.intersects(thisObject);
	}
	public void reset() {
		x = 275;
		y = 700;
		vx = 0;
		vy = 0;
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
		this.vx = vx;
	}

	public int getVy() {
		return vy;
	}

	public void setVy(int vy) {
		this.vy = vy;
	}

	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Ship.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	public void setDir(int n) {
		// TODO Auto-generated method stub
		dir = n;
	}

	

}
