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

public class Buttons extends GameObject {
	
	private Handler handler;
	private Texture tex = Game.getInstance();
	BufferedImageLoader loader = new BufferedImageLoader();
	private int number;
	private Animation GreenButtonNotPressed;
	private Animation GreenButtonPressed;
	private Animation BlueButtonNotPressed;
	private Animation BlueButtonPressed;
	
	
	public Buttons(float x, float y, Handler handler, ObjectId id, String button) {
		super(x, y, id);
		this.handler = handler;
		this.number = number;
		GreenButtonNotPressed = new Animation(1, tex.Ground[21]);
		GreenButtonPressed = new Animation(1, tex.Ground[22]);
		BlueButtonNotPressed = new Animation(1, tex.Ground[25]);
		BlueButtonPressed = new Animation(1, tex.Ground[26]);
		
	}

	public void tick(LinkedList<GameObject> object) {
		GreenButtonNotPressed.runAnimation();
		GreenButtonPressed.runAnimation();
		BlueButtonNotPressed.runAnimation();
		BlueButtonPressed.runAnimation();
		
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
			if(!greenButtonPressed){
				GreenButtonNotPressed.drawAnimations(g, (int) x, (int) y - 32, 64, 64);
			}
			if(greenButtonPressed){
				GreenButtonPressed.drawAnimations(g, (int) x, (int) y - 32, 64, 64);
			}
		}
		if(Game.state == STATE.LEVELTWO){
			if(!blueButtonPressed){
				BlueButtonNotPressed.drawAnimations(g, (int) x, (int) y - 32, 64, 64);
			}
			if(blueButtonPressed){
				BlueButtonPressed.drawAnimations(g, (int) x, (int) y - 32, 64, 64);
			}
		}
	

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