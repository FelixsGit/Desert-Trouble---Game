package desertTrouble.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import desertTrouble.Window.Animation;
import desertTrouble.Window.BufferedImageLoader;
import desertTrouble.Window.Game;
import desertTrouble.Window.Game.STATE;
import desertTrouble.Window.Handler;
import desertTrouble.frameWorks.GameObject;
import desertTrouble.frameWorks.ObjectId;
import desertTrouble.frameWorks.Texture;

public class Booster extends GameObject {
	
	private Handler handler;
	private Texture tex = Game.getInstance();
	BufferedImageLoader loader = new BufferedImageLoader();

	private Animation BoosterNotPressed;
	private Animation BoosterPressed;
	
	
	public Booster(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;	
		BoosterNotPressed = new Animation(1, tex.Ground[27]);
		BoosterPressed = new Animation(1, tex.Ground[28]);
		
	}

	public void tick(LinkedList<GameObject> object) {

		BoosterNotPressed.runAnimation();
		BoosterPressed.runAnimation();
		
	}

	public void render(Graphics g) {
	

		if(getBoosterPressed() == false){
			BoosterNotPressed.drawAnimations(g, (int) x, (int) y - 32, 64, 64);
		}
		
		if(getBoosterPressed() == true){
			BoosterPressed.drawAnimations(g, (int) x, (int) y - 32, 64, 64);
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
		return new Rectangle((int) x , (int) y - 15, 60, 40);	
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