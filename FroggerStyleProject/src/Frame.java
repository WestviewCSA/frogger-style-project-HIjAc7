import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	//Timer related variables
	int waveTimer = 5; //each wave of enemies is 20s
	long ellapseTime = 0;
	Font timeFont = new Font("Courier", Font.BOLD, 70);
	int level = 0;
	
	
	Font myFont = new Font("Courier", Font.BOLD, 40);
	SimpleAudioPlayer backgroundMusic = new SimpleAudioPlayer("scifi.wav", false);
//	Music soundBang = new Music("bang.wav", false);
//	Music soundHaha = new Music("haha.wav", false);
	
	EndGoal end = new EndGoal();
	Background[] space = new Background[5];
	Asteroid[] row1 = new Asteroid[3];
	Asteroid[] row2 = new Asteroid[4];
	Asteroid[] row3 = new Asteroid[5];
	DockingBay[] docks = new DockingBay[4];
	Ship ship = new Ship();
	boolean[] occupied = new boolean[4];
	
	//frame width/height
	int width = 600;
	int height = 600;	
	

	public void paint(Graphics g) {
		super.paintComponent(g);
		
		for(Background obj : space) {
			obj.paint(g);
		}
		end.paint(g);
		for(DockingBay obj : docks) {
			obj.paint(g);
		}
		for(Asteroid obj : row1) {
			obj.paint(g);
		}
		for(Asteroid obj : row2) {
			obj.paint(g);
		}
		for(Asteroid obj : row3) {
			obj.paint(g);
		}
		ship.paint(g);
		if(ship.getY()<=end.getY()+150) {
			for(int i = 0;i<occupied.length;i++) {
				if(!occupied[i]) {
					docks[i].setDir(1);
					occupied[i] = true;
					ship.reset();
					break;
				}
			}
		}
	}
	
	
	
	public static void main(String[] arg) {
		Frame f = new Frame();
		
	}
	
	public Frame() {
		JFrame f = new JFrame("Duck Hunt");
		f.setSize(new Dimension(width, height));
		f.setBackground(Color.white);
		f.add(this);
		f.setResizable(false);
 		f.addMouseListener(this);
		f.addKeyListener(this);
	
		//backgroundMusic.play();

		for(int i = 0;i<row1.length;i++) {
			row1[i] = new Asteroid(i*150,400,1);
		}
		for(int i = 0;i<row2.length;i++) {
			row2[i] = new Asteroid(i*120+15,325,2);
		}
		for(int i = 0;i<row3.length;i++) {
			row3[i] = new Asteroid(i*135,230,1);
		}
		for(int i = 0;i<space.length;i++) {
			space[i] = new Background(0,i*150);
		}
		for(int i = 0;i<docks.length;i++) {
			docks[i] = new DockingBay(i*150+20,62);
		}
		for(int i = 0;i<occupied.length;i++) {
			occupied[i] = false;
		}
		
		
		//the cursor image must be outside of the src folder
		//you will need to import a couple of classes to make it fully 
		//functional! use eclipse quick-fixes
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon("torch.png").getImage(),
				new Point(0,0),"custom cursor"));	
		
		
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent m) {
		
	
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyCode());
		if(arg0.getKeyCode() == 38) {
			ship.setVy(-4);
			ship.setVx(0);
		}
		if(arg0.getKeyCode() == 40) {
			ship.setVy(4);
			ship.setVx(0);
		}
		if(arg0.getKeyCode() == 39) {
			ship.setVx(4);
			ship.setVy(0);
		}
		if(arg0.getKeyCode() == 37) {
			ship.setVx(-4);
			ship.setVy(0);
		}
		if(arg0.getKeyCode() == 82) {
			ship.reset();
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
