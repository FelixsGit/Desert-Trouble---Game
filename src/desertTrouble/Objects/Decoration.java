package desertTrouble.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import desertTrouble.Window.Animation;
import desertTrouble.Window.BufferedImageLoader;
import desertTrouble.Window.Game;
import desertTrouble.Window.Game.STATE;
import desertTrouble.Window.Handler;
import desertTrouble.frameWorks.GameObject;
import desertTrouble.frameWorks.ObjectId;
import desertTrouble.frameWorks.Texture;

public class Decoration extends GameObject {
	
	private Handler handler;
	private Texture tex = Game.getInstance();
	private String type;
	BufferedImageLoader loader = new BufferedImageLoader();
	private BufferedImage cactusOne = null;
	private BufferedImage cactusTwo = null;
	private BufferedImage bushOne = null;
	private BufferedImage bushTwo = null;
	private BufferedImage stone = null;
	private BufferedImage arrow = null;
	private BufferedImage skeleton = null;
	private BufferedImage grassOne = null;
	private BufferedImage grassTwo = null;
	private BufferedImage tree = null;

	
	public Decoration(float x, float y, Handler handler, ObjectId id, String type) {
		super(x, y, id);
		this.handler = handler;
		this.type = type;
		cactusOne = loader.loadImage("/cactusOne.png");
		cactusTwo = loader.loadImage("/cactusTwo.png");
		bushOne = loader.loadImage("/bushOne.png");
		bushTwo = loader.loadImage("/bushTwo.png");
		stone = loader.loadImage("/Stone.png");
		arrow = loader.loadImage("/Sign.png");
		skeleton = loader.loadImage("/Skeleton.png");
		grassOne = loader.loadImage("/grassOne.png");
		grassTwo = loader.loadImage("/grassTwo.png");
		tree = loader.loadImage("/Tree.png");
		

	}

	public void tick(LinkedList<GameObject> object) {

	}

	public void render(Graphics g) {
	
	
		if(type.equals("cactusOne")){
			g.drawImage(cactusOne, (int) x , (int) y -20 , 64, 64 , null); 
		}
		if(type.equals("cactusTwo")){
			g.drawImage(cactusTwo, (int) x , (int) y - 20, 64, 64 , null); 
		}
		if(type.equals("bushOne")){
			g.drawImage(bushOne, (int) x, (int) y -20, 64, 64 , null); 
		}
		if(type.equals("bushTwo")){
			g.drawImage(bushTwo, (int) x , (int) y -20, 64, 64 , null); 
		}
		if(type.equals("stone")){
			g.drawImage(stone, (int) x , (int) y -20, 64, 64 , null); 
		}
		if(type.equals("arrow")){
			g.drawImage(arrow, (int) x , (int) y -20 , 64, 64 , null); 
		}
		if(type.equals("skeleton")){
			g.drawImage(skeleton, (int) x , (int) y -20, 64, 64 , null); 
		}
		if(type.equals("grassOne")){
			g.drawImage(grassOne, (int) x , (int) y -20, 64, 64 , null); 
		}
		if(type.equals("grassTwo")){
			g.drawImage(grassTwo, (int) x , (int) y -20, 64, 64 , null); 
		}
		if(type.equals("tree")){
			g.drawImage(tree, (int) x , (int) y - 110, 160, 160 , null); 
		}
		

		/*
		g.setColor(Color.green);
		//g.fillRect((int)x,(int) y, 32, 32);
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.BLACK);
		g2d.draw(getBounds());
		*/
		
		

	}
	public Rectangle getBounds() {

		return new Rectangle((int) x , (int) y - 50 , 70, 148);	
		

		
	}
	public Rectangle getBoundsTop(){
		return new Rectangle((int) x + 5, (int) y, 32 - 10, 10);
		
	}
	public Rectangle getBoundsBottom(){
		return new Rectangle((int) x + 5, (int) y + 22, 32 - 10 , 10);
		
	}
	public Rectangle getBoundsLeft(){
		return new Rectangle((int) x, (int) y + 5, 5, 32 - 10);	
		
	}
	public Rectangle getBoundsRight(){
		return new Rectangle((int) x + 27, (int) y + 5, 5, 32 - 10);	
	}
}