import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Laser{
	private Image forward; 	
	private AffineTransform tx;
	
					//0-forward, 1-backward, 2-left, 3-right
	int width, height;
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	double scaleWidth = 6.0;		//change to scale image
	double scaleHeight = 1.0; 		//change to scale image

	public Laser() {
		forward 	= getImage("/imgs/"+"laser-gif.gif"); //load the image for Tree
		

		//alter these
		width = 600;
		height = 100;
		
		x = 0;
		y = 300;
		
		vx = 0;
		vy = 0;
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}
	public Laser(int a, int b) {
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
		g2.drawImage(forward, tx, null);
		g2.drawRect(x, y+35, width, 30);
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Laser.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	public boolean collided(Ship ship) {
		Rectangle main = new Rectangle(ship.getX()+1,ship.getY(),ship.getHeight()-5,ship.getWidth()-5);
		Rectangle thisObject = new Rectangle(x, y+35, width, 30);
		return main.intersects(thisObject) ;
	}

}