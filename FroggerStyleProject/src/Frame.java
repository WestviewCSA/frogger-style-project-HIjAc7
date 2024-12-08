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
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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
	int score = 0;
	int f = 0;
	ArrayList<String> fonts = GFG.getfonts();
	Font myFont = new Font(fonts.get(f), Font.BOLD, 40);
	boolean collided = false;
	int mode = 0;
	boolean codeEntered = false;
	boolean[] cheatCode = new boolean[11];
	
	
	//Initializing objects for sound effects
	SimpleAudioPlayer backgroundMusic = new SimpleAudioPlayer("spaceship-cruising-ufo-7176.wav", true);
	SimpleAudioPlayer asteroidHit = new SimpleAudioPlayer("asteroid-hitting-something-152511.wav", false);
	SimpleAudioPlayer laserHit = new SimpleAudioPlayer("laser-zap-90575.wav", false);
	SimpleAudioPlayer laserSound = new SimpleAudioPlayer("edm-zap-246568 (2) (1).wav",true);
	SimpleAudioPlayer gameOver = new SimpleAudioPlayer("loss sound.wav", false);
	SimpleAudioPlayer winSound = new SimpleAudioPlayer("win sound.wav", false);
	SimpleAudioPlayer heartLost = new SimpleAudioPlayer("mixkit-8-bit-lose-2031.wav", false);
	SimpleAudioPlayer pointSound = new SimpleAudioPlayer("point sound.wav",false);
	SimpleAudioPlayer introMusic = new SimpleAudioPlayer("game-music-7408.wav",true);
	
	//	Music soundBang = new Music("bang.wav", false);
//	Music soundHaha = new Music("haha.wav", false);
	
	EndGoal end = new EndGoal(); //initializing assets
	Background[] space = new Background[6];
	Asteroid[] row1 = new Asteroid[3];
	Asteroid[] row2 = new Asteroid[4];
	Asteroid[] row3 = new Asteroid[5];
	Asteroid asteroid = new Asteroid(-278,600,1,1);
	DockingBay[] docks = new DockingBay[4];
	Ship ship = new Ship();
	DockedShip[] ships = new DockedShip[4];
	boolean[] occupied = new boolean[4];
	Platform[] platform1 = new Platform[2];
	Platform[] platform2 = new Platform[2];
	Platform[] platform7 = new Platform[2];
	Platform[] platform3 = new Platform[3];
	Platform[] platform4 = new Platform[3];
	Platform[] platform5 = new Platform[3];
	Platform[] platform6 = new Platform[3];
	ArrayList<Platform[]> platforms = new ArrayList<Platform[]>();
	Laser[] lasers = new Laser[3];
	ArrayList<Hearts> hearts = new ArrayList<Hearts>();
	Hearts[] lostHearts = new Hearts[6];
	EndScreen endScreen = new EndScreen(0,0);
	StartScreen start = new StartScreen(0,0);
	
	//for secret game
	UFO[] ufo1 = new UFO[5];
	UFO[] ufo2 = new UFO[5];
	UFO[] ufo3 = new UFO[5];
	UFO[] ufo4 = new UFO[5];
	ufoBullet bullet1 = new ufoBullet();
	ufoBullet bullet2 = new ufoBullet();
	ufoBullet bullet3 = new ufoBullet();
	ArrayList<shipBullet> bullets = new ArrayList<shipBullet>();
	
	//frame width/heights
	int width = 600;
	int height = 800;	
	

	public void paint(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.green);
		//painting all assets and tracking collision
		start.paint(g);
		introMusic.play();
		if(start.getDir()==0) {
			start.paint(g);
		}else {
			if(codeEntered) {
				introMusic.pause();
				laserSound.pause();
				backgroundMusic.play();
				for(Background obj : space) {
					obj.paint(g);
				}
				
			
				Font myFont = new Font(fonts.get(154), Font.BOLD, 40);
				g.setFont(myFont);
				g.setColor(Color.white);
				g.drawString("Score: "+score, 20, 50);
				for(Hearts obj : lostHearts) {
					obj.setDir(1);
					obj.paint(g);
				}
				for(Hearts obj : hearts) {
					obj.setDir(0);
					obj.paint(g);
				}
				for(UFO obj : ufo1) {
					obj.paint(g);
					if(obj.collided(ship)) {
						ship.reset();
						hearts.remove(hearts.size()-1);
						asteroidHit = new SimpleAudioPlayer("asteroid-hitting-something-152511.wav", false);
						asteroidHit.play();
						heartLost = new SimpleAudioPlayer("mixkit-8-bit-lose-2031.wav", false);
						heartLost.play();
					}
				}
				for(UFO obj : ufo2) {
					obj.paint(g);
					if(obj.collided(ship)) {
						ship.reset();
						hearts.remove(hearts.size()-1);
						asteroidHit = new SimpleAudioPlayer("asteroid-hitting-something-152511.wav", false);
						asteroidHit.play();
						heartLost = new SimpleAudioPlayer("mixkit-8-bit-lose-2031.wav", false);
						heartLost.play();
					}
				}
				for(UFO obj : ufo3) {
					obj.paint(g);
					if(obj.collided(ship)) {
						ship.reset();
						hearts.remove(hearts.size()-1);
						asteroidHit = new SimpleAudioPlayer("asteroid-hitting-something-152511.wav", false);
						asteroidHit.play();
						heartLost = new SimpleAudioPlayer("mixkit-8-bit-lose-2031.wav", false);
						heartLost.play();
					}
				}
				for(UFO obj : ufo4) {
					obj.paint(g);
					if(obj.collided(ship)) {
						ship.reset();
						hearts.remove(hearts.size()-1);
						asteroidHit = new SimpleAudioPlayer("asteroid-hitting-something-152511.wav", false);
						asteroidHit.play();
						heartLost = new SimpleAudioPlayer("mixkit-8-bit-lose-2031.wav", false);
						heartLost.play();
					}
				}
				ship.paint(g);
				bullet1.paint(g);
				bullet2.paint(g);
				bullet3.paint(g);
				if(bullet1.pastBorder()) {
					bullet1.reset();
				}
				if(bullet1.pastBorder()) {
					bullet2.reset();
				}
				if(bullet1.pastBorder()) {
					bullet3.reset();
				}
				if(hearts.size() == 0) {
					backgroundMusic.pause();
					laserSound.pause();
					gameOver = new SimpleAudioPlayer("loss sound.wav", false);
					gameOver.play();
					endScreen.setDir(0);
					endScreen.paint(g);
					g.drawString("Score: "+score, 100, 500);
	
				}
				if(score ==  4) {
					backgroundMusic.pause();
					laserSound.pause();
					winSound = new SimpleAudioPlayer("win sound.wav", false);
					winSound.play();
					endScreen.setDir(1);
					endScreen.paint(g);
				}
			}else {
				introMusic.pause();
				laserSound.play();
				backgroundMusic.play();
				for(Background obj : space) {
					obj.paint(g);
				}
				end.paint(g);
				for(DockingBay obj : docks) {
					obj.paint(g);
				}
				
				for(Hearts obj : lostHearts) {
					obj.setDir(1);
					obj.paint(g);
				}
				for(Hearts obj : hearts) {
					obj.setDir(0);
					obj.paint(g);
				}
				
				for(Asteroid obj : row1) {
					obj.paint(g);
					if(obj.collided(ship)) {
						ship.reset();
						hearts.remove(hearts.size()-1);
						asteroidHit = new SimpleAudioPlayer("asteroid-hitting-something-152511.wav", false);
						asteroidHit.play();
						heartLost = new SimpleAudioPlayer("mixkit-8-bit-lose-2031.wav", false);
						heartLost.play();
					}
				}
				for(Asteroid obj : row2) {
					obj.paint(g);
					if(obj.collided(ship)) {
						ship.reset();
						hearts.remove(hearts.size()-1);
						asteroidHit = new SimpleAudioPlayer("asteroid-hitting-something-152511.wav", false);
						asteroidHit.play();
						heartLost = new SimpleAudioPlayer("mixkit-8-bit-lose-2031.wav", false);
						heartLost.play();
					}
				}
				asteroid.paint(g);
				for(Asteroid obj : row3) {
					obj.paint(g);
					if(obj.collided(ship)) {
						ship.reset();
						hearts.remove(hearts.size()-1);
						asteroidHit = new SimpleAudioPlayer("asteroid-hitting-something-152511.wav", false);
						asteroidHit.play();
						heartLost = new SimpleAudioPlayer("mixkit-8-bit-lose-2031.wav", false);
						heartLost.play();
						
					}
					
				}
				for(DockedShip obj : ships) {
					obj.paint(g);
				}
				for(Laser obj : lasers) {
					obj.paint(g);
				}
				for(Platform[] list : platforms) {
					for(Platform obj : list) {
						obj.paint(g);
						if(obj.collided(ship)) {
							ship.setY(obj.getY());
							ship.setVy(0);
							ship.setVx(obj.getVx());
							collided = true;
						}
					}
				}
				
			
				//displaying score
				Font myFont = new Font(fonts.get(154), Font.BOLD, 40);
				g.setFont(myFont);
				g.setColor(Color.white);
				g.drawString("Score: "+score, 20, 50);
				
				ship.paint(g);
				
				if(end.collided(ship)) {
					for(int i = 0;i<occupied.length;i++) {
						if(!occupied[i]) {
							pointSound = new SimpleAudioPlayer("point sound.wav",false);
							pointSound.play();
							docks[i].setDir(1);
							occupied[i] = true;
							ships[i].setDir(0);
							score++;
							ship.reset();
							break;
						}
					}
				}
				for(Laser obj : lasers) {
					if(!collided && obj.collided(ship)) {
						laserHit = new SimpleAudioPlayer("laser-zap-90575.wav", false);
						laserHit.play();
						ship.reset();
						hearts.remove(hearts.size()-1);
						heartLost = new SimpleAudioPlayer("mixkit-8-bit-lose-2031.wav", false);
						heartLost.play();
					}
					
				}
				//ends game based on score or lives
				if(hearts.size() == 0) {
					backgroundMusic.pause();
					laserSound.pause();
					gameOver = new SimpleAudioPlayer("loss sound.wav", false);
					gameOver.play();
					endScreen.setDir(0);
					endScreen.paint(g);
					g.drawString("Score: "+score, 100, 500);
	
				}
				if(score ==  4) {
					backgroundMusic.pause();
					laserSound.pause();
					winSound = new SimpleAudioPlayer("win sound.wav", false);
					winSound.play();
					endScreen.setDir(1);
					endScreen.paint(g);
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
		//playing sound effect
		backgroundMusic.play();
		laserSound.play();
		
		//Initializing assets and placing them
		for(int i =0;i<ufo1.length;i++) {
			ufo1[i] = new UFO(i*100+20,200,1);
		}
		for(int i =0;i<ufo2.length;i++) {
			ufo2[i] = new UFO(i*100+20,300,2);
		}
		for(int i =0;i<ufo3.length;i++) {
			ufo3[i] = new UFO(i*100+20,400,1);
		}
		for(int i =0;i<ufo4.length;i++) {
			ufo4[i] = new UFO(i*100+20,500,2);
		}
		for(int i = 0;i<row1.length;i++) {
			row1[i] = new Asteroid(i*150,600,1);
		}
		for(int i = 0; i<lostHearts.length;i++) {
			lostHearts[i] = new Hearts(i*40+20,720);
		}
		for(int i = 0; i<6;i++) {
			Hearts temp = new  Hearts(i*40+20,720);
			hearts.add(i, temp);
		}
		for(int i = 0;i<row2.length;i++) {
			row2[i] = new Asteroid(i*120+15,525,2);
			
		}
		for(int i = 0;i<row3.length;i++) {
			row3[i] = new Asteroid(i*135-100,440,1);
			
		}
		for(int i = 0;i<space.length;i++) {
			space[i] = new Background(0,i*150);
		}
		for(int i = 0;i<docks.length;i++) {
			docks[i] = new DockingBay(i*150+20,62);
		}
		for(int i = 0;i<ships.length;i++) {
			ships[i] = new DockedShip(i*150+42,92);
		}
		for(int i = 0;i<lasers.length;i++) {
			lasers[i] = new Laser(0,i*50+170);
		}
		for(int i = 0;i<platform1.length;i++) {
			platform1[i] = new Platform(i*50,192,1);
		}
		for(int i = 0;i<platform2.length;i++) {
			platform2[i] = new Platform(i*50+300,192,1);
		}
		for(int i = 0;i<platform3.length;i++) {
			platform3[i] = new Platform(i*50+500,242,2);
		}
		for(int i = 0;i<platform4.length;i++) {
			platform4[i] = new Platform(i*50+73,242,2);
		}
		for(int i = 0;i<platform5.length;i++) {
			platform5[i] = new Platform(i*50+100,292,1);
		}
		for(int i = 0;i<platform6.length;i++) {
			platform6[i] = new Platform(i*50+400,292,1);
		}
		for(int i = 0;i<platform7.length;i++) {
			platform7[i] = new Platform(i*50+500,192,1);
		}
		//filling ArrayList for easy access to objects
		platforms.add(platform1);
		platforms.add(platform2);
		platforms.add(platform3);
		platforms.add(platform4);
		platforms.add(platform5);
		platforms.add(platform6);
		platforms.add(platform7);
		for(int i = 0;i<occupied.length;i++) {
			occupied[i] = false;
		}
		for(int i = 0;i<lasers.length;i++) {
			lasers[i] = new Laser(0,i*40+180);;
		}
		for(int i =0;i<cheatCode.length;i++) {
			cheatCode[i] = false;
		}
		ship.setDir(0);
		
		//for secret game
		
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
		//arrow keys for movement, r to reset
		//+ and - to toggle visibility of the ship - extra hard mode?
		//more keys for diagonal movement
		System.out.println(arg0.getKeyCode());
		if(arg0.getKeyCode() == 38) {
			if(cheatCode[0]==false) {
				cheatCode[0]=true;
			}else if(cheatCode[1]==false 
					&& cheatCode[0]) {
				cheatCode[1] = true;
			}else {
				for(int i=0;i<cheatCode.length;i++) {
					cheatCode[i] = false;
				}
			}
			ship.setVy(-4);
			ship.setVx(0);
			if(collided) {
				ship.setY(ship.getY()-50);
				collided = false;
			}
		}
		if(arg0.getKeyCode() == 40) {
			if(cheatCode[2]==false 
					&& cheatCode[0] 
					&& cheatCode[1]) {
				cheatCode[2]=true;
			}else if(cheatCode[3]==false 
					&& cheatCode[0] 
					&& cheatCode[1] 
					&& cheatCode[2]) {
				cheatCode[3] = true;
			}else {
				for(int i=0;i<cheatCode.length;i++) {
					cheatCode[i] = false;
				}
			}
			ship.setVy(4);
			ship.setVx(0);
			if(collided) {
				ship.setY(ship.getY()+50);
				collided = false;
			}
		}
		if(arg0.getKeyCode() == 39) {
			if(cheatCode[5]==false 
					&& cheatCode[0] 
					&& cheatCode[1] 
					&& cheatCode[2] 
					&& cheatCode[3] 
					&& cheatCode[4]) {
				cheatCode[5]=true;
			}else if(cheatCode[7]==false 
					&& cheatCode[0] 
					&& cheatCode[1] 
					&& cheatCode[2] 
					&& cheatCode[3] 
					&& cheatCode[4] 
					&& cheatCode[5] 
					&& cheatCode[6]) {
				cheatCode[7] = true;
			}else {
				for(int i=0;i<cheatCode.length;i++) {
					cheatCode[i] = false;
				}
			}
			ship.setVx(4);
			ship.setVy(0);
		}
		if(arg0.getKeyCode() == 37) {
			if(cheatCode[4]==false 
					&& cheatCode[0] 
					&& cheatCode[1]
					&& cheatCode[2] 
					&& cheatCode[3] ) {
				cheatCode[4]=true;
			}else if(cheatCode[6]==false 
					&& cheatCode[0] 
					&& cheatCode[1] 
					&& cheatCode[2] 
					&& cheatCode[3] 
					&& cheatCode[4] 
					&& cheatCode[5] ) {
				cheatCode[6] = true;
			}else {
				for(int i=0;i<cheatCode.length;i++) {
					cheatCode[i] = false;
				}
			}
			ship.setVx(-4);
			ship.setVy(0);
		}
		if(arg0.getKeyCode() == 82) {
			ship.reset();
			laserSound.pause();
			backgroundMusic.pause();
			score = 0;
			endScreen.setDir(2);
			for(int i = hearts.size()-1;i>=0;i--) {
				hearts.remove(i);
			}
			for(int i = 0; i<6;i++) {
				Hearts temp = new  Hearts(i*40+20,720);
				hearts.add(i, temp);
			}
			for(int i = 0;i<occupied.length;i++) {
				ships[i].setDir(1);
			}
			for(int i =0;i<docks.length;i++) {
				docks[i].setDir(0);
			}
			for(int i = 0;i<occupied.length;i++) {
				occupied[i] = false;
			}
			for(int i = 0;i<row2.length;i++) {
				row2[i] = new Asteroid(i*120+15,525,2);
				
			}
			
			start.setDir(0);
			laserSound = new SimpleAudioPlayer("edm-zap-246568 (2) (1).wav",true);
			laserSound.play();
			backgroundMusic = new SimpleAudioPlayer("spaceship-cruising-ufo-7176.wav", true);
			backgroundMusic.play();
		}
		if(arg0.getKeyCode() == 61) {
			ship.setDir(0);
		}
		if(arg0.getKeyCode() == 45) {
			ship.setDir(1);
		}
		if(arg0.getKeyCode() == 36) {
			ship.setVy(-4);
			ship.setVx(-4);
		}
		if(arg0.getKeyCode() == 33) {
			ship.setVy(-4);
			ship.setVx(4);
		}
		if(arg0.getKeyCode() == 35) {
			ship.setVy(4);
			ship.setVx(-4);
		}
		if(arg0.getKeyCode() == 34) {
			ship.setVy(4);
			ship.setVx(4);
		}
		if(arg0.getKeyCode() == 69) {
			mode = 1;
			start.setDir(1);
			for(Asteroid obj : row1) {
				obj.setVx(2);
			}
			//int mult=1;
			for(Asteroid obj : row2) {
				obj.setVx(2);
				//obj.setX(obj.getX()+mult*10);
				//mult++;
			}
			for(Asteroid obj : row3) {
				obj.setVx(2);
			}
			asteroid.setVx(2);
			for(Platform[] list : platforms) {
				for(Platform obj : list) {
					obj.setVx(2);
				}
			}
		}
		if(arg0.getKeyCode() == 72) {
			mode = 2;
			start.setDir(1);
			for(Asteroid obj : row1) {
				obj.setVx(5);
			}
			
			for(Asteroid obj : row2) {
				obj.setVx(5);
				
			}
			for(Asteroid obj : row3) {
				obj.setVx(5);
			}
			asteroid.setVx(5);
			for(Platform[] list : platforms) {
				for(Platform obj : list) {
					obj.setVx(5);
				}
			}
		}
		if(arg0.getKeyCode() == 87) {
			ship.setVy(-4);
			ship.setVx(-0);
		}
		if(arg0.getKeyCode() == 83) {
			ship.setVy(4);
			ship.setVx(0);
		}
		if(arg0.getKeyCode() == 65) {
			if(cheatCode[9]==false 
					&& cheatCode[0] 
					&& cheatCode[1]
					&& cheatCode[2] 
					&& cheatCode[3] 
					&& cheatCode[4] 
					&& cheatCode[5] 
					&& cheatCode[6]
					&& cheatCode[7]
					&& cheatCode[8] ) {
				cheatCode[9] = true;
			
			}else {
				for(int i=0;i<cheatCode.length;i++) {
					cheatCode[i] = false;
				}
			}
			ship.setVy(0);
			ship.setVx(-4);
		}
		if(arg0.getKeyCode() == 68) {
			ship.setVy(0);
			ship.setVx(4);
		}
		if(arg0.getKeyCode() == 66) {
			if(cheatCode[8]==false 
					&& cheatCode[0] 
					&& cheatCode[1]
					&& cheatCode[2] 
					&& cheatCode[3] 
					&& cheatCode[4] 
					&& cheatCode[5] 
					&& cheatCode[6]
					&& cheatCode[7]) {
				cheatCode[8] = true;
			}else {
				for(int i=0;i<cheatCode.length;i++) {
					cheatCode[i] = false;
				}
			}
		}
		if(arg0.getKeyCode() == 10) {
			if(cheatCode[10]==false 
					&& cheatCode[0] 
					&& cheatCode[1]
					&& cheatCode[2] 
					&& cheatCode[3] 
					&& cheatCode[4] 
					&& cheatCode[5] 
					&& cheatCode[6]
					&& cheatCode[7]
					&& cheatCode[8]
					&& cheatCode[9] ) {
				cheatCode[10] = true;
				codeEntered = true;
			}else {
				for(int i=0;i<cheatCode.length;i++) {
					cheatCode[i] = false;
				}
			}
		}
		if(arg0.getKeyCode() == 32) {
			shipBullet temp = new shipBullet(ship.getX(),ship.getY());
			bullets.add(temp);
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
