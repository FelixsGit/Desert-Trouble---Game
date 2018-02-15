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

public class Coin extends GameObject {
	
	private Handler handler;
	private Texture tex = Game.getInstance();
	BufferedImageLoader loader = new BufferedImageLoader();
	private Animation coinSpin;
	private boolean starting = true;
	
	
	public Coin(float x, float y, Handler handler, ObjectId id, int number) {
		super(x, y, id);
		this.handler = handler;
		this.number = number;
		coinSpin = new Animation(2, tex.Ground[20]);
		
		
	}

	public void tick(LinkedList<GameObject> object) {
		coinSpin.runAnimation();

		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.Buttons){
				if(Game.state == STATE.LEVELONE){
					if(tempObject.getGreenButtonPressed()){
						falling = true;
					}
				}
				if(Game.state == STATE.LEVELTWO){
					if(tempObject.getBlueButtonPressed()){
						falling = true;
					}
				}
			}
		}
		
		if(Game.state == STATE.LEVELONE){
			if(number == 2 && falling){
				if(y <= 257){
					velY = 2;
				}
				if(y >= 257){
					velY = 0;
				}
				y +=velY;
			}
			if(number == 3 && starting){
				velY = - 17;
				starting = false;
			}
			if(number == 3 && !starting){
				velY += 0.5;
				
			}
			if(number == 3){
				y += velY;
			}
		}
		
		if(Game.state == STATE.LEVELTWO){
			if(number == 3 && falling){
				if(y > 170){
					velY = -0.5f;
				}
				if(y <= 170){
					velY = 0;
				}
				y +=velY;
			}
			if(number == 5 && starting){
				velY = - 17;
				starting = false;
			}
			if(number == 5 && !starting){
				velY += 0.5;
				
			}
			if(number == 5){
				y += velY;
			}
		}
	}

	public void render(Graphics g) {
	
		/*
		g.setColor(Color.green);
		//g.fillRect((int)x,(int) y, 32, 32);
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.BLACK);
		g2d.draw(getBounds());
		*/
		
		coinSpin.drawAnimations(g, (int) x - 16, (int) y - 32, 64, 64);
		

	}
	public Rectangle getBounds() {
		return new Rectangle((int) x , (int) y - 16, 32, 32);	
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