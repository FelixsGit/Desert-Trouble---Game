package desertTrouble.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import desertTrouble.Window.BufferedImageLoader;
import desertTrouble.Window.Game;
import desertTrouble.Window.Handler;
import desertTrouble.Window.Game.STATE;
import desertTrouble.frameWorks.GameObject;
import desertTrouble.frameWorks.ObjectId;
import desertTrouble.frameWorks.Texture;

public class Key extends GameObject {
	
	private Handler handler;
	private Texture tex = Game.getInstance();
	BufferedImageLoader loader = new BufferedImageLoader();
	private int number;
	private BufferedImage greenKey = null;
	private BufferedImage blueKey = null;
	
	public Key(float x, float y, Handler handler, ObjectId id, int number) {
		super(x, y, id);
		this.handler = handler;
		this.number = number;
		
		greenKey = loader.loadImage("/GreenKey.png");
		blueKey = loader.loadImage("/BlueKey.png");
	}

	public void tick(LinkedList<GameObject> object) {

	}
	public void render(Graphics g) {
	
		/*
		g.setColor(Color.green);
		//g.fillRect((int)x,(int) y, 32, 32);
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.BLACK);
		g2d.draw(getBounds());
		*/
		if(Game.state == STATE.LEVELONE){
			g.drawImage(greenKey, (int) x + 10, (int) y - 15, 44, 40 , null); 
		}
		if(Game.state == STATE.LEVELTWO){
			g.drawImage(blueKey, (int) x + 10, (int) y - 15, 44, 40 , null); 
		}
		
	}
	public Rectangle getBounds() {
		return new Rectangle((int) x + 10 , (int) y - 15 , 44, 40);
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