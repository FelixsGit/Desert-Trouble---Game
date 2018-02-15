package desertTrouble.Window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import desertTrouble.Objects.Booster;
import desertTrouble.Objects.Buttons;
import desertTrouble.Objects.Coin;
import desertTrouble.Objects.Decoration;
import desertTrouble.Objects.Flag;
import desertTrouble.Objects.FlatGroundBlock;
import desertTrouble.Objects.Fly;
import desertTrouble.Objects.Door;
import desertTrouble.Objects.Key;
import desertTrouble.Objects.MovingPlatformOne;
import desertTrouble.Objects.Player;
import desertTrouble.Objects.Slime;
import desertTrouble.Objects.Spikes;
import desertTrouble.frameWorks.GameObject;
import desertTrouble.frameWorks.KeyInput;
import desertTrouble.frameWorks.MouseInput;
import desertTrouble.frameWorks.ObjectId;
import desertTrouble.frameWorks.Texture;

public class Game extends Canvas implements Runnable{
	

	private static final long serialVersionUID = 4746192028055354877L;
	public static int WIDTH;
	public static int HEIGHT; 
	private boolean running = false;
	private boolean loadingLevel = true;
	public static boolean pausedCheck = false;
	private Thread thread;
	private Handler handler;
	static Texture tex;
	public static BufferedImage levelOne = null;
	public static BufferedImage levelTwo = null;
	public static BufferedImage levelThree = null;
	private Camera cam;
	
	private BufferedImage backgroundGameOne = null;
	private BufferedImage backgroundGameTwo = null;
	private BufferedImage backgroundMenu = null;
	private BufferedImage backgroundMenuHelp = null;
	private BufferedImage backgroundPauseMenu = null;
	private BufferedImage backgroundDefeatMenu = null;
	private BufferedImage backgroundVictoryMenu = null;

	public static Clip MusicClip;
	public static Clip SoundClip;
	
	private BufferedImageLoader loader = new BufferedImageLoader();
	
	public static enum STATE{
		
		LEVELONE,
		LEVELTWO,
		LEVELTHREE,
		ENDOFLEVELSTATE,
		MAINMENU,
		HELPMENU,
		PAUSEDMENU,
		GAMEEND,
		VICTORY,
		BLACKSCREEN,
		DEFEAT,
		
	}
		
	public static STATE state = STATE.MAINMENU;

	
	public void init() {
		
		WIDTH = getWidth();
		HEIGHT = getHeight();
		tex = new Texture();
		cam = new Camera(0, 0);
		
		
		BufferedImageLoader loader = new BufferedImageLoader();
		backgroundMenu = loader.loadImage("/MenuBackground.png");
		backgroundMenuHelp = loader.loadImage("/MenuBackgroundHelp.png");
		backgroundGameOne = loader.loadImage("/GameBackgroundOne.png");
		backgroundGameTwo = loader.loadImage("/GameBackgroundTwo.png");
		backgroundPauseMenu = loader.loadImage("/PauseBackground.png");
		backgroundDefeatMenu = loader.loadImage("/BackgroundDefeat.png");
		backgroundVictoryMenu = loader.loadImage("/BackgroundVictory.png");
		handler = new Handler();

		
		this.addMouseListener(new MouseInput());
		this.addKeyListener(new KeyInput(handler));	
	}

	public synchronized void start(){
		if (running){
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void run() {
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 45;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		//int updates = 0;
		//int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				//updates++;
				delta--;
			}
			render();
			//frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println("FPS: " + frames + " TICKS: " + updates);
				//frames = 0;
				//updates = 0;
			}
		}
	}
	private void tick(){
		
		if(state == STATE.GAMEEND){
			for(int i = 0; i < handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() == ObjectId.Player){
					handler.removeObject(tempObject);
				}
			}
			handler = new Handler();
			loadingLevel = true;
			cam = new Camera(0, 0);
			state = STATE.MAINMENU;
			pausedCheck = false;
			Player.COINSCOLLECTED = 0;
			
		}
		
		if(state == STATE.PAUSEDMENU && !pausedCheck){
			cam = new Camera(0, 0);
			pausedCheck = true;
		}
		if(state == STATE.DEFEAT && !pausedCheck){
			cam = new Camera(0, 0);
			pausedCheck = true;
		}
		if(state == STATE.VICTORY && !pausedCheck){
			cam = new Camera(0, 0);
			pausedCheck = true;
		}
	
		/**
		 * Loading Level 1
		 */
		if(state == STATE.LEVELONE && loadingLevel){
			handler = new Handler();
			WIDTH = getWidth();
			HEIGHT = getHeight();
			tex = new Texture();
			cam = new Camera(0, 0);			
			levelOne = loader.loadImage("/LevelOne.png"); //loading the level
			loadImageLevel(levelOne);
			tex = new Texture();
			this.addKeyListener(new KeyInput(handler));	
			this.addMouseListener(new MouseInput());
			loadingLevel = false;
		}
		
		/**
		 *  Loading Level 2
		 */
		
		if(state == STATE.LEVELTWO && !loadingLevel){
			handler = new Handler();
			WIDTH = getWidth();
			HEIGHT = getHeight();
			tex = new Texture();
			cam = new Camera(0, 0);			
			levelTwo = loader.loadImage("/LevelTwo.png"); //loading the level
			loadImageLevel(levelTwo);
			tex = new Texture();
			this.addKeyListener(new KeyInput(handler));	
			this.addMouseListener(new MouseInput());
			loadingLevel = true;
		}
		
		/**
		 * Loading Level 3
		 */
		if(state == STATE.LEVELTHREE && !loadingLevel){
			handler = new Handler();
			WIDTH = getWidth();
			HEIGHT = getHeight();
			tex = new Texture();
			cam = new Camera(0, 0);			
			levelTwo = loader.loadImage("/LevelThree.png"); //loading the level
			loadImageLevel(levelThree);
			tex = new Texture();
			this.addKeyListener(new KeyInput(handler));	
			loadingLevel = true;
		}
		
		
		if(state == STATE.LEVELONE || state == STATE.LEVELTWO || state == STATE.LEVELTHREE || state == STATE.ENDOFLEVELSTATE || state == STATE.BLACKSCREEN){
			
			for(int i = 0; i < handler.object.size(); i++){
				if(handler.object.get(i).getId() == ObjectId.Player){
					cam.tick(handler.object.get(i));
				}
			}
			handler.tick();
		}
	}
	
	private void render(){

		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		
		//Draw here
	
		g2d.translate(cam.getX(), cam.getY()); //Begin of cam
		g.setColor(Color.BLACK);
		g.fillRect(-1000, 0, 10000, 960);
		
		if(Game.state == STATE.LEVELONE){
			g.drawImage(backgroundGameOne, 0 , 0, null);
			handler.render(g);
		}
		if(Game.state == STATE.LEVELTWO){
			g.drawImage(backgroundGameTwo, 0 , 0, null);
			handler.render(g);
		}
		if(Game.state == STATE.MAINMENU){
			g.drawImage(backgroundMenu, 0 , 0, null);
			g.setColor(Color.black);
			g.drawString("By Felix Toppar 2018-01-14", 5, Game.HEIGHT - 7);
		}
		if(Game.state == STATE.HELPMENU){
			g.drawImage(backgroundMenuHelp, 0 , 0, null);
			Font fnt3 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 20);
			g.setFont(fnt3);
			g.drawString("You are an Alien! Fight your way", 180, 405);
			g.drawString("through the nasty desert and make", 180, 430);
			g.drawString("it out alive! Your people's lives", 180, 455);
			g.drawString("depends on it!", 180, 480);
			
			g.drawString("Left arrow - move left", 830, 405);
			g.drawString("Right arrow - move right", 830, 430);
			g.drawString("Space - jump", 830, 455);
			g.drawString("Escape - pause", 830, 480);
			
		}
		if(Game.state == STATE.PAUSEDMENU){
			g.drawImage(backgroundPauseMenu, 0, 0, null);
		}
		if(Game.state == STATE.DEFEAT){
			g.drawImage(backgroundDefeatMenu, 0, 0, null);
		}
		if(Game.state == STATE.VICTORY){
			g.drawImage(backgroundVictoryMenu, 0, 0, null);
			Font fnt3 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 20);
			g.setFont(fnt3);
			g.drawString("Congratulations! You have now defeated the desert infastation.", 380, 330);
			g.drawString("Your people can now safley enter and populate the wast sanddunes", 380, 355);
			g.drawString("and fruitfull rivers.", 380, 380);
			g.setColor(Color.green);
			g.drawString("You collected " + Player.COINSCOLLECTED + "/39 coins", 380, 405);
			
		}
		g2d.translate(-cam.getX(), -cam.getY() ); //End of cam
		
		//Stop Drawing here
		g.dispose();
		bs.show();
	}
	
	public void loadImageLevel(BufferedImage image){
		
		int w = image.getWidth();
		int h = image.getHeight();
		
		for(int xx = 0; xx < h; xx++){
			for(int yy = 0; yy < w ; yy++){
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green =(pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				/**
				 * Decorations
				 */
				
				if(red == 170 && green == 170 && blue == 10){
					handler.addObject(new Decoration(xx*32, yy*32, handler, ObjectId.Decoration, "cactusOne"));
				}
				if(red == 170 && green == 170 && blue == 11){
					handler.addObject(new Decoration(xx*32, yy*32, handler, ObjectId.Decoration, "cactusTwo"));
				}
				if(red == 170 && green == 170 && blue == 12){
					handler.addObject(new Decoration(xx*32, yy*32, handler, ObjectId.Decoration, "bushOne"));
				}
				if(red == 170 && green == 170 && blue == 13){
					handler.addObject(new Decoration(xx*32, yy*32, handler, ObjectId.Decoration, "bushTwo"));
				}
				if(red == 170 && green == 170 && blue == 14){
					handler.addObject(new Decoration(xx*32, yy*32, handler, ObjectId.Decoration, "stone"));
				}
				if(red == 170 && green == 170 && blue == 15){
					handler.addObject(new Decoration(xx*32, yy*32, handler, ObjectId.Decoration, "arrow"));
				}
				if(red == 170 && green == 170 && blue == 16){
					handler.addObject(new Decoration(xx*32, yy*32, handler, ObjectId.Decoration, "skeleton"));
				}
				if(red == 170 && green == 170 && blue == 17){
					handler.addObject(new Decoration(xx*32, yy*32, handler, ObjectId.Decoration, "grassOne"));
				}
				if(red == 170 && green == 170 && blue == 18){
					handler.addObject(new Decoration(xx*32, yy*32, handler, ObjectId.Decoration, "grassTwo"));
				}
				if(red == 170 && green == 170 && blue == 19){
					handler.addObject(new Decoration(xx*32, yy*32, handler, ObjectId.Decoration, "tree"));
				}
				
				//Player
				if(red == 0 && green == 0 && blue == 255){
					handler.addObject(new Player(xx*32, yy*32, handler, ObjectId.Player));
				}
				
				/**
				 * OBJECTS & ENEMIES
				 */	
				
				//Slime one
				if(red == 60 && green == 70 && blue == 81){
					handler.addObject(new Slime(xx*32, yy*32, handler, ObjectId.Slime, 1));
				}
				//Slime two
				if(red == 60 && green == 70 && blue == 82){
					handler.addObject(new Slime(xx*32, yy*32, handler, ObjectId.Slime, 2));
				}			
				//Slime three
				if(red == 60 && green == 70 && blue == 83){
					handler.addObject(new Slime(xx*32, yy*32, handler, ObjectId.Slime, 3));
				}	
				//Slime four
				if(red == 60 && green == 70 && blue == 84){
					handler.addObject(new Slime(xx*32, yy*32, handler, ObjectId.Slime, 4));
				}
				//Slime five
				if(red == 60 && green == 70 && blue == 85){
					handler.addObject(new Slime(xx*32, yy*32, handler, ObjectId.Slime, 5));
				}			
				//Slime six
				if(red == 60 && green == 70 && blue == 86){
					handler.addObject(new Slime(xx*32, yy*32, handler, ObjectId.Slime, 6));
				}
				
				
				
				//Booster
				if(red == 1 && green == 15 && blue == 150){
					handler.addObject(new Booster(xx*32, yy*32, handler, ObjectId.Booster));
				}
				//Coins
				if(red == 255 && green == 216 && blue == 0){
					handler.addObject(new Coin(xx*32, yy*32, handler, ObjectId.Coin, 1));
				}
				//left Spikes
				if(red == 25 && green == 25 && blue == 25){
					handler.addObject(new Spikes(xx*32, yy*32, handler, ObjectId.Spikes, "rightSpike"));
				}
				//right spikes
				if(red == 35 && green == 35 && blue == 35){
					handler.addObject(new Spikes(xx*32, yy*32, handler, ObjectId.Spikes, "leftSpike"));
				}
				//button
				if(red == 0 && green == 200 && blue == 0){
					if(state == STATE.LEVELONE){
						handler.addObject(new Buttons(xx*32, yy*32, handler, ObjectId.Buttons, "green"));
					}
					if(state == STATE.LEVELTWO){
						handler.addObject(new Buttons(xx*32, yy*32, handler, ObjectId.Buttons, "blue"));
					}
				}
				//Key

				if(red == 0 && green == 225 && blue == 0){
					handler.addObject(new Key(xx*32, yy*32, handler, ObjectId.Key, 1));
				}

				//Door
				if(red == 0 && green == 250 && blue == 0){
					handler.addObject(new Door(xx*32, yy*32, handler, ObjectId.Door, 1));
				}
				//flyOne
				if(red == 235 && green == 0 && blue == 0){
					handler.addObject(new Fly(xx*32, yy*32, handler, ObjectId.Fly, 1));
				}
				//flyTwo
				if(red == 245 && green == 0 && blue == 0){
					handler.addObject(new Fly(xx*32, yy*32, handler, ObjectId.Fly, 2));
				}
				//flyThree
				if(red == 255 && green == 0 && blue == 0){
					handler.addObject(new Fly(xx*32, yy*32, handler, ObjectId.Fly, 3));
				}
				//Flag
				if(state == STATE.LEVELONE){
					if(red == 0 && green == 50 && blue == 0){
						handler.addObject(new Flag(xx*32, yy*32, handler, ObjectId.Flag));
					}
					
				}
				if(state == STATE.LEVELTWO){
					if(red == 0 && green == 50 && blue == 0){
						handler.addObject(new Flag(xx*32, yy*32, handler, ObjectId.Flag));
					}
				}
				
		
				
				/**
				 * SAND
				 */

				//sand center
				if(red == 255 && green == 255 && blue == 255){
					handler.addObject(new FlatGroundBlock(xx*32, yy*32, handler, ObjectId.FlatGroundBlock, "sandCenter"));
				}
				//sand right
				if(red == 127 && green == 0 && blue == 0){
					handler.addObject(new FlatGroundBlock(xx*32, yy*32, handler, ObjectId.FlatGroundBlock, "sandRight"));
				}
				//sand left
				if(red == 127 && green == 106 && blue == 0){
					handler.addObject(new FlatGroundBlock(xx*32, yy*32, handler, ObjectId.FlatGroundBlock, "sandLeft"));
				}
				//sand mid
				if(red == 127 && green == 0 && blue == 55){
					handler.addObject(new FlatGroundBlock(xx*32, yy*32, handler, ObjectId.FlatGroundBlock, "sandMid"));
				}
				
				//sand Bottom Right
				if(red == 15 && green == 15 && blue == 0){
					handler.addObject(new FlatGroundBlock(xx*32, yy*32, handler, ObjectId.FlatGroundBlock, "sandBottomRight"));
				}
				//sand Bottom Left
				if(red == 25 && green == 25 && blue == 0){
					handler.addObject(new FlatGroundBlock(xx*32, yy*32, handler, ObjectId.FlatGroundBlock, "sandBottomLeft"));
				}
				//sand Bottom
				if(red == 20 && green == 20 && blue == 0){
					handler.addObject(new FlatGroundBlock(xx*32, yy*32, handler, ObjectId.FlatGroundBlock, "sandBottom"));
				}
				//sand Small Right
				if(red == 0 && green == 15 && blue == 15){
					handler.addObject(new FlatGroundBlock(xx*32, yy*32, handler, ObjectId.FlatGroundBlock, "sandSmallRight"));
				}
				//sand Small Mid
				if(red == 0 && green == 20 && blue == 20){
					handler.addObject(new FlatGroundBlock(xx*32, yy*32, handler, ObjectId.FlatGroundBlock, "sandSmallMid"));
				}
				//sand Small Left
				if(red == 0 && green == 25 && blue == 25){
					handler.addObject(new FlatGroundBlock(xx*32, yy*32, handler, ObjectId.FlatGroundBlock, "sandSmallLeft"));
				}
				//sand left side
				if(red == 15 && green == 0 && blue == 15){
					handler.addObject(new FlatGroundBlock(xx*32, yy*32, handler, ObjectId.FlatGroundBlock, "sandLeftSide"));
				}
				//sand right side
				if(red == 25 && green == 0 && blue == 25){
					handler.addObject(new FlatGroundBlock(xx*32, yy*32, handler, ObjectId.FlatGroundBlock, "sandRightSide"));
				}
				
				
				/**
				 * MOVING OBJECTS & SAND
				 */
				//moving Platform1
				if(red == 72 && green == 0 && blue == 255){
					handler.addObject(new MovingPlatformOne(xx*32, yy*32, handler, ObjectId.MovingPlatformOne, "sandMid", 1));
				}
				if(state == STATE.LEVELONE){
					//sand mid moving
					if(red == 20 && green == 20 && blue == 20){
						handler.addObject(new MovingPlatformOne(xx*32, yy*32, handler, ObjectId.MovingPlatformOne, "sandSmallMid", 2));
					}
					//sand left moving
					if(red == 30 && green == 30 && blue == 30){
						handler.addObject(new MovingPlatformOne(xx*32, yy*32, handler, ObjectId.MovingPlatformOne, "sandSmallLeft", 2));
					}
					//sand right moving
					if(red == 40 && green == 40 && blue == 40){
						handler.addObject(new MovingPlatformOne(xx*32, yy*32, handler, ObjectId.MovingPlatformOne, "sandSmallRight", 2));
					}
					//coin moving
					if(red == 50 && green == 50 && blue == 50){
						handler.addObject(new Coin(xx*32, yy*32, handler, ObjectId.Coin, 2));
					}
				}	
				if(state == STATE.LEVELTWO){
					//sand mid moving
					if(red == 20 && green == 20 && blue == 20){
						handler.addObject(new MovingPlatformOne(xx*32, yy*32, handler, ObjectId.MovingPlatformOne, "sandSmallMid", 3));
					}
					//sand left moving
					if(red == 30 && green == 30 && blue == 30){
						handler.addObject(new MovingPlatformOne(xx*32, yy*32, handler, ObjectId.MovingPlatformOne, "sandSmallLeft", 3));
					}
					//sand right moving
					if(red == 40 && green == 40 && blue == 40){
						handler.addObject(new MovingPlatformOne(xx*32, yy*32, handler, ObjectId.MovingPlatformOne, "sandSmallRight", 3));
					}
					//coin moving
					if(red == 50 && green == 50 && blue == 50){
						handler.addObject(new Coin(xx*32, yy*32, handler, ObjectId.Coin, 3));
					}
				}
			}
		}
	}
	
	public static Texture getInstance(){
		return tex;
	}
	
	public static void main(String[] args){
		File MenuMusic = new File("res/MenuMusic.wav");
		Game.playMusic(MenuMusic, true);
		new Window(1280, 960, "Desert Trouble", new Game());

		
	}
	public static void playSound(File Sound, boolean run){
		if(run){
			try{
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Sound);
				SoundClip = AudioSystem.getClip();
				SoundClip.open(audioInputStream);
				SoundClip.start();
				
			}catch(Exception e){
				
			}
		}
		else{
			SoundClip.stop();
		}
			
	}
	public static void playMusic(File Sound, boolean run){
		if(run){
			try{
	
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Sound);
				MusicClip = AudioSystem.getClip();
				MusicClip.open(audioInputStream);
				MusicClip.start();
				MusicClip.loop(3);
				
			}catch(Exception e){
				
			}
		}
		else
			MusicClip.stop();
	}
}