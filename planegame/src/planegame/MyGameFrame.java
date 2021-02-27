package planegame;
 
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
 
public class MyGameFrame extends JFrame {
	
	Image planeImage = GameUtil.getImage("images/plane.png");
	Image bg=GameUtil.getImage("images/bg.jpg");
	
	Plane plane = new Plane(planeImage,300,300);
	Plane plane2 = new Plane(planeImage,300,400);
	
	@Override
	public void paint(Graphics g) {
	
		g.drawImage(bg,0,0,null);
		plane.drawSelf(g);
		plane2.drawSelf(g);
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
			System.out.println("Pdown"+e.getKeyCode());
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			System.out.println("Rup"+e.getKeyCode());
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
	}
	
	
	
	public static void main(String[] args) {
		MyGameFrame f = new MyGameFrame();
		f.launchFrame();
	}
	
}
