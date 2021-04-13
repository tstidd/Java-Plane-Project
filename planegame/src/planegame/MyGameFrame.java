package planegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

public class MyGameFrame extends Frame implements MouseListener{

	Image planeImage = GameUtil.getImage("images/plane.png");
	Image bg = GameUtil.getImage("images/bg.jpg");
	Image playButtonImage = GameUtil.getImage("images/Play Button.png");
	Image tryAgainImage = GameUtil.getImage("images/Try Again.png");
	Image menuButtonImage = GameUtil.getImage("images/Main Menu.png");
	Image title = GameUtil.getImage("images/Title.png");
	Image upArrow = GameUtil.getImage("images\\Up-Arrow.png");
	Image downArrow = GameUtil.getImage("images\\Down-Arrow.png");

	Plane plane = new Plane(planeImage, 450, 450);
	Button playButton = new Button(playButtonImage, 180, 300);
	Button tryAgainButton = new Button(tryAgainImage, 150, 300);
	Button menuButton = new Button(menuButtonImage, 250, 300);
	Button upButton = new Button(upArrow, 350, 310);
	Button downButton = new Button(downArrow, 350, 340);
	Button[] banner = new Button[5];

	Explode explosion;

	Date startTime;
	Date endTime;
	static int period;
	static int level = 1 ;
	
	Bullet[] bullets;
	int numberOfBullets;
	
	static boolean crash = false;
	boolean firstTry = true;

	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.drawImage(bg, 0, 0, null);
		
		if (!playButton.isPlay()) {
			g.drawImage(title, 50, 50, null);
			playButton.drawSelf(g);
			upButton.drawSelf(g);
			downButton.drawSelf(g);
			banner[level-1].drawSelf(g);
		}
		
		if (playButton.isPlay()) {
			playGame(g, c);
		}
	}

	private void playGame(Graphics g, Color c) {
		
		if (startTime == null) {
			startTime = new Date();
			bullets = Bullet.setNumberofBullets(level * 10);
		}
		
		plane.drawSelf(g);
		
		

		for (int i = 0; i < bullets.length; i++) {

			bullets[i].draw(g);

			crash = bullets[i].getRect().intersects(plane.getRect());

			if (crash) {
				plane.live = false;
				if (explosion == null) {

					explosion = new Explode(plane.x, plane.y);

					endTime = new Date();

					period = (int) ((endTime.getTime() - startTime.getTime()) / 1000);

				}

				explosion.draw(g);
				//write to file
				writeFile.write();
				//read file
				ReadFile.readFile();
			
			}

			if (!plane.live) {
				g.setColor(Color.WHITE);
				Font f = new Font("Serif", Font.BOLD, 50);
				g.setFont(f);
				g.drawString("Time: " + period + " second", 100, 100);
				Font f2 = new Font("Serif", Font.BOLD, 30);
				g.setFont(f2);
				Font f3 = new Font("Serif", Font.BOLD, 25);
				g.setFont(f3);		
				g.drawString("TOP 3: ", 200,150);
				
				g.drawString(ReadFile.getTop1(), 20,200);
				g.drawString(ReadFile.getTop2(), 20,240);
				g.drawString(ReadFile.getTop3(), 20,280);
				
			
				
				tryAgainButton.drawSelf(g);
				menuButton.drawSelf(g);
			
			}

		}

		g.setColor(c);
	}

	class PaintThread extends Thread {
		@Override
		public void run() {
			while (true) {
				repaint(); // repaint window
				try {
					Thread.sleep(40);// fps 1s=1000ms
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	class KeyMonitor extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			plane.addDirection(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			plane.stopDirection(e);
		}
	}

	public void launchFrame() {
		// title
		setTitle("Team project");
		// set to visible to true
		setVisible(true);
		if (firstTry) {
		// set size
		setSize(500, 500);
		// set location;
		setLocation(300, 300);
		
		addMouseListener(this);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
			new PaintThread().start();// active repaint window
			addKeyListener(new KeyMonitor());// give window add key monitor

			
			
			for (int i = 0; i < 5; i++) {
				banner[i] = new Button((GameUtil.getImage("images\\Level-" + (i+1) + ".png")), 400, 315);
			}
		}
	}

	public static void main(String[] args) {
		MyGameFrame f = new MyGameFrame();
		f.launchFrame();
	

	}

	// fix flicker for Frame
	private Image offScreenImage = null;

	public void update(Graphics g) {
		if (offScreenImage == null)
			offScreenImage = this.createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);// width and height

		Graphics gOff = offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (!playButton.isPlay()) {
			if ((e.getX() >= (int)playButton.x && e.getX() <= ((int)playButton.x + playButton.width)) && (e.getY() >= (int)playButton.y && e.getY() <= ((int)playButton.y + playButton.height))) {
				playButton.setPlay(true);
			}
			
			if ((e.getX() >= (int)upButton.x && e.getX() <= ((int)upButton.x + upButton.width)) && (e.getY() >= (int)upButton.y && e.getY() <= ((int)upButton.y + upButton.height))) {
				if (level < 5) {
					level++;
				}
			}
			
			if ((e.getX() >= (int)downButton.x && e.getX() <= ((int)downButton.x + downButton.width)) && (e.getY() >= (int)downButton.y && e.getY() <= ((int)downButton.y + downButton.height))) {
				if (level > 1) {
					level--;
				}
			}
		}
		
		if (!plane.live) {
			if ((e.getX() >= (int)tryAgainButton.x && e.getX() <= ((int)tryAgainButton.x + tryAgainButton.width)) && (e.getY() >= (int)tryAgainButton.y && e.getY() <= ((int)tryAgainButton.y + tryAgainButton.height))) {
				firstTry = false;
				plane.live = true;
				plane.x = 450;
				plane.y = 450;
				explosion = null;
				startTime = null;
				for (int i = 0; i < bullets.length; i++) {
					bullets[i].reset();
				}
				launchFrame();
				writeFile.setWrite(true);
				ReadFile.setRead(true);
				ReadFile.resetTop();
				ReadFile.readFile();
				if(ReadFile.getResults().size()>10) {
					updateFile.updateFile(ReadFile.getResults());
				}
			
				
			}
			if ((e.getX() >= (int)menuButton.x && e.getX() <= ((int)menuButton.x + menuButton.width)) && (e.getY() >= (int)menuButton.y && e.getY() <= ((int)menuButton.y + menuButton.height))) {
				firstTry = false;
				plane.live = true;
				plane.x = 450;
				plane.y = 450;
				explosion = null;
				startTime = null;
				for (int i = 0; i < bullets.length; i++) {
					bullets[i].reset();
				}
				playButton.setPlay(false);
			}
		}
	}
	
	
	

	
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return the period
	 */
	public static int getPeriod() {
		return period;
	}

	/**
	 * @return the level
	 */
	public static int getLevel() {
		return level;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
