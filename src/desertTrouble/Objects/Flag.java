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

public class Flag extends GameObject {
	
	private Handler handler;
	private Texture tex = Game.getInstance();
	BufferedImageLoader loader = new BufferedImageLoader();
	private Animation greenFlagWave;
	private Animation blueFlagWave;

	
	
	public Flag(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		this.type = type;
		greenFlagWave= new Animation(5, tex.Flag[0], tex.Flag[1]);
		blueFlagWave = new Animation(5, tex.Flag[2], tex.Flag[3]);
	
		
	}

	public void tick(LinkedList<GameObject> object) {
		greenFlagWave.runAnimation();
		blueFlagWave.runAnimation();
	
	}

	public void render(Graphics g) {
	
	
		if(Game.state == STATE.LEVELONE){
			greenFlagWave.drawAnimations(g, (int) x, (int) y - 50 , 70, 148);
		}
		if(Game.state == STATE.LEVELTWO){
			blueFlagWave.drawAnimations(g, (int) x, (int) y - 50 , 70, 148);
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