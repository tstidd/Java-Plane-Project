# Java-Plane-Project
### CS1410 final project 
### Team Awesome JB,CL,TS,QC



Test your hand at avoiding the floating bombs while flying through space. The bombs move in random directions and bounce off the walls to return to haunt you again and again. Avoid the bombs for as long as you can and achieve the high score.


Created with:  Eclipse IDE

Written in:  JAVA ver. 8.1

### Programmers:

Qi Cao – alanqicao@gmail.com
Jonathan Bodily – jonathan888b@gmail.com
Terry Stidd – t.stidd28237@gmail.com


### Playing the Game:

1. Select the level by clicking the up or down buttons. (Each level adds 10 more bombs.)
2. Click on Play.
3. Move the spaceship by using the arrow keys.
4. Avoid being hit by or hitting the bombs for as long as you can.


### The menu to start the game:

```
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
```

### The code to Play the Game:

```
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
				period = (int) ((endTime.getTime() - 								startTime.getTime()) / 1000);
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
```

### Variables List:

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
static int level = 1;
Bullet[] bullets;


## Possible Additions / Future Upgrades:

In the future we are looking to:
-	Make the Spaceship able to shoot the bombs with a laser cannon.
-	Enable a short duration shield or force field that will make the bombs bounce off the ship in the proper trajectory. You would only get to use it a limited number of times per level. If you don’t use it, they would carry over to the next level.
-	Make the levels timed (or maybe you must eliminate/shoot all the bombs) before automatically advancing to the next level.
-	Set up a point system that is a combination of time and points for shooting bombs. Higher levels would achieve higher points.
-	Add a player name to the High Score list.


### Conclusion:

We hope you have fun playing the game. Feel free to make additions or updates to this program. All we ask is that you send us a copy of your updates. We are new programmers and would love your input and suggestions. Thanks

t.stidd28237@gmail.com

alanqicao@gmail.com

jonathan888b@gmail.com

