import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class DockedShip{
	private Image forward, backward, left, right; 	
	private AffineTransform tx;
	
	private int dir = 1; 					//0-forward, 1-backward, 2-left, 3-right
	int width, height;
	private int x, y;						//position of the object
							//movement variables
	double scaleWidth = 0.5;		//change to scale image
	double scaleHeight = 0.5; 		//change to scale image

	public DockedShip() {
		forward 	= getImage("/imgs/"+"shipgifnoback.gif"); //load the image for Tree
		backward 	= getImage("/imgs/"+"Empty.png"); //load the image for Tree
		//left 		= getImage("/imgs/"+"left.png"); //load the image for Tree
		//right 		= getImage("/imgs/"+"right.png"); //load the image for Tree

		//alter these
		width = 50;
		height = 50;
		x = 275;
		y = 500;
	
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}
	public DockedShip(int x1, int y1) {
		forward 	= getImage("/imgs/"+"shipgifnoback.gif"); //load the image for Tree
		backward 	= getImage("/imgs/"+"Empty.png"); //load the image for Tree
		
		width = 50;
		height = 50;
		x = x1;
		y = y1;
		
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y);
	}
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
			
		
		init(x,y);
		
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
			URL imageURL = DockedShip.class.getResource(path);
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
