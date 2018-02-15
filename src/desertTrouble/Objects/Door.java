package desertTrouble.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import desertTrouble.Window.BufferedImageLoader;
import desertTrouble.Window.Game;
import desertTrouble.Window.Game.STATE;
import desertTrouble.Window.Handler;
import desertTrouble.frameWorks.GameObject;
import desertTrouble.frameWorks.ObjectId;
import desertTrouble.frameWorks.Texture;

public class Door extends GameObject {
	
	private Handler handler;
	private Texture tex = Game.getInstance();
	BufferedImageLoader loader = new BufferedImageLoader();
	private int number;
	private BufferedImage greenDoor = null;
	private BufferedImage blueDoor = null;
	
	
	public Door(float x, float y, Handler handler, ObjectId id, int number) {
		super(x, y, id);
		this.handler = handler;
		this.number = number;
		
		greenDoor = loader.loadImage("/GreenDoor.png");
		blueDoor = loader.loadImage("/BlueDoor.png");
	}

	public void tick(LinkedList<GameObject> object) {

	}
	public void render(Graphics g) {
	
		/*
		g.setColor(Color.green);
		//g.fillRect((int)x,(int) y, 32, 32);
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.RED);
		g2d.draw(getBounds());
		*/
		
		if(Game.state == STATE.LEVELONE){
			g.drawImage(greenDoor, (int) x - 30, (int) y - 65, 89,134, null);
		}
		if(Game.state == STATE.LEVELTWO){
			g.drawImage(blueDoor, (int) x - 30, (int) y - 65, 89,134, null);
		}
		

	}
	public Rectangle getBounds() {
		return new Rectangle((int) x - 30 , (int) y - 65 , 89, 134);	
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