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

public class Fly extends GameObject {
	
	private Handler handler;
	private Texture tex = Game.getInstance();
	BufferedImageLoader loader = new BufferedImageLoader();
	private int number;
	private Animation flyRight;
	private Animation flyLeft;
	private Animation deathRight;
	private Animation deathLeft;
	private int flySpeed = 2;
	private boolean starting = false;

	public Fly(float x, float y, Handler handler, ObjectId id, int number) {
		super(x, y, id);
		this.handler = handler;
		this.number = number;

		flyLeft = new Animation(8, tex.Fly[0], tex.Fly[1]);
		flyRight = new Animation(8, tex.Fly[2], tex.Fly[3]);
		deathLeft = new Animation(1, tex.Fly[4]);
		deathRight = new Animation(1, tex.Fly[5]);
		
	}

	public void tick(LinkedList<GameObject> object) {
		
		if(!death){
			x += velX;
		}
		if(death && velY == 0){
			handler.addObject(new Coin((int) x, (int) y, handler, ObjectId.Coin, 3));
		}
		if(death){
			velY = 3;
			y += velY;
		}
		
		if(!starting){
			velX = flySpeed;
		}
		
		if(!attacking && number ==  1){
			if(x <= 450){
				velX = flySpeed;
			}
			if(x >= 810){
				starting = true;
				velX = -flySpeed;
			}
		}
		
		if(!attacking && number ==  2){
			if(x <= 1500){
				velX = flySpeed;
			}
			if(x >= 1900){
				starting = true;
				velX = -flySpeed;
			}
		}
		
		if(!attacking && number ==  3){
			if(x <= 230){
				velX = flySpeed;
			}
			if(x >= 740){
				starting = true;
				velX = -flySpeed;
			}
		}
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.Player){
				if(number == 1){
					if(tempObject.getX() >= 450 && tempObject.getX() <= 810 && tempObject.getY() >= 325 && tempObject.getY() <= 610){
						attacking = true;
					}
					else{
						attacking = false;
					}
				}
				if(number == 2){
					if(tempObject.getX() >= 1500 && tempObject.getX() <= 1900 && tempObject.getY() >= 490 && tempObject.getY() <= 770){
						attacking = true;
					}
					else{
						attacking = false;
					}
				}
				if(number == 3){
					if(tempObject.getX() >= 230 && tempObject.getX() <= 740 && tempObject.getY() >= -200 && tempObject.getY() <= 320){
						attacking = true;
					}
					else{
						attacking = false;
					}
				}
			}
		}
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.Fly){
				if(y > 1400 && tempObject.getDeath()){
					handler.removeObject(tempObject);
				}
			}
		}

		flyLeft.runAnimation();
		flyRight.runAnimation();
		deathLeft.runAnimation();
		deathRight.runAnimation();
		
		if(velX > 0 && !death){
			setFacingRight(true);
			setFacingLeft(false);
		}
		
		if(velX < 0 && !death){
			setFacingLeft(true);
			setFacingRight(false);
		}
		
		//calculates distance to player
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.Player){
				float dx = tempObject.getX() - getX();
				float dy = tempObject.getY() - getY();
				float distance = (float)Math.sqrt(dx*dx + dy*dy);		
				if(attacking){
					velX = (float) (dx * flySpeed / distance);
				}	
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
		
		
		if(death && facingRight){
			deathRight.drawAnimations(g,(int) x , (int) y , 92, 46);
		}
		if(death && facingLeft){
			deathLeft.drawAnimations(g,(int) x, (int) y , 92, 46);
		}
		else{
			if(facingRight && !death){
				flyRight.drawAnimations(g,(int) x, (int) y - 40, 92, 46);
			}
			if(facingLeft && !death){
				flyLeft.drawAnimations(g,(int) x, (int) y - 40, 92, 46);
			}
		}
	}
	public Rectangle getBounds() {
		return new Rectangle((int) x , (int) y - 40 , 92, 46);	
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