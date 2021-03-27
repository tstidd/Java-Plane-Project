package planegame;
 
import javax.swing.JFrame;



import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;

public class MyGameFrame extends Frame {
	
	Image planeImage = GameUtil.getImage("images/plane.png");
	Image bg=GameUtil.getImage("images/bg.jpg");
	
	
	Plane plane = new Plane(planeImage,450,450);
	Bullet[] bullets = new Bullet[15];
	
	
	Explode explosion;
	
	Date startTime = new Date();
	Date endTime;
	int period;
	
	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.drawImage(bg,0,0,null);
		plane.drawSelf(g);
		
		
		for(int i = 0; i<bullets.length;i++) {
			
			bullets[i].draw(g);
			
			boolean crash  = bullets[i].getRect().intersects(plane.getRect());
			
			if(crash) {
				plane.live = false;
				if(explosion == null) {
					
					explosion = new Explode(plane.x,plane.y);
					
					endTime = new Date();
					
					period = (int)((endTime.getTime()-startTime.getTime())/1000);
					
				}
				
				explosion.draw(g);
			}
			
			if(!plane.live) {
				g.setColor(Color.WHITE);
				Font f = new Font("Serif",Font.BOLD,50);
				g.setFont(f);
				g.drawString("Time: " +period+" second",100,200);
				Font f2 = new Font("Serif",Font.BOLD,30);
				g.setFont(f2);
				
			}
		
		}
		g.setColor(c);
	}
	
	
	class PaintThread extends Thread{
		@Override
		public void run() {			
			while(true) {
				repaint(); //repaint window
				try {
					Thread.sleep(40);//fps  1s=1000ms
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
			}
		}
	}
	
	
	
	class KeyMonitor extends KeyAdapter{
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
		//title
		setTitle("Team project");
		//set to visible to true
		setVisible(true);
		//set size
		setSize(500,500);
		//set location;
		setLocation(300,300);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		new PaintThread().start();//active repaint window
		addKeyListener(new KeyMonitor());//give window add key monitor
		
		for(int i = 0; i<bullets.length;i++) {
			bullets[i] = new Bullet();
		}

	}
		
	
	
	
	public static void main(String[] args) {
		MyGameFrame f = new MyGameFrame();
		f.launchFrame();
		
	}
	
	// fix flicker for Frame
		private Image offScreenImage = null;

		public void update(Graphics g) {
			if(offScreenImage == null)
				offScreenImage = this.createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);// width and height
			
			Graphics gOff = offScreenImage.getGraphics();
			paint(gOff);
			g.drawImage(offScreenImage, 0, 0, null);
		}
	
	
}
