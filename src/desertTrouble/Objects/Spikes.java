package desertTrouble.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import desertTrouble.Window.Animation;
import desertTrouble.Window.BufferedImageLoader;
import desertTrouble.Window.Game;
import desertTrouble.Window.Handler;
import desertTrouble.frameWorks.GameObject;
import desertTrouble.frameWorks.ObjectId;
import desertTrouble.frameWorks.Texture;

public class Spikes extends GameObject {
	
	private Handler handler;
	private Texture tex = Game.getInstance();
	BufferedImageLoader loader = new BufferedImageLoader();
	private Animation RightSpikes;
	private Animation LeftSpikes;
	
	
	public Spikes(float x, float y, Handler handler, ObjectId id, String type) {
		super(x, y, id);
		this.handler = handler;
		this.type = type;
		RightSpikes = new Animation(1, tex.Ground[23]);
		LeftSpikes = new Animation(1, tex.Ground[24]);
		
	}

	public void tick(LinkedList<GameObject> object) {
		RightSpikes.runAnimation();
		LeftSpikes.runAnimation();
	}

	public void render(Graphics g) {
	
		if(type.equals("rightSpike")){
			RightSpikes.drawAnimations(g, (int) x, (int) y , 32, 32);
		}
		if(type.equals("leftSpike")){
			LeftSpikes.drawAnimations(g, (int) x, (int) y , 32, 32);
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
		if(type.equals("rightSpike")){
			return new Rectangle((int) x , (int) y , 15, 32);	
		}
		if(type.equals("leftSpike")){
			return new Rectangle((int) x + 16, (int) y , 15, 32);	
		}
		else{
			return null;
		}
		
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