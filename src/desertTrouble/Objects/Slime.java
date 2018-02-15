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

public class Slime extends GameObject {
	
	private Handler handler;
	private Texture tex = Game.getInstance();
	BufferedImageLoader loader = new BufferedImageLoader();
	private int number;
	private Animation slimeRight;
	private Animation slimeLeft;
	private Animation deathRight;
	private Animation deathLeft;
	private int flySpeed = 3;
	private boolean starting = false;

	public Slime(float x, float y, Handler handler, ObjectId id, int number) {
		super(x, y, id);
		this.handler = handler;
		this.number = number;
		slimeLeft = new Animation(8, tex.Slime[0], tex.Slime[1]);
		slimeRight = new Animation(8, tex.Slime[4], tex.Slime[5]);
		deathLeft = new Animation(1, tex.Slime[2]);
		deathRight = new Animation(1, tex.Slime[3]);
		
	}

	public void tick(LinkedList<GameObject> object) {
		
		if(!death){
			x += velX;
		}
		if(death && velY == 0){
			handler.addObject(new Coin((int) x, (int) y, handler, ObjectId.Coin, 5));
		}
		if(death){
			velY = 3;
			y += velY;
		}
		
		if(!starting){
			velX = flySpeed;
		}
		
		/**
		 * Slime patrolls
		 */
		//slime 1
		if(!attacking && (number ==  1 || number == 2)){
			if(x <= 875){
				velX = flySpeed;
			}
			if(x >= 1340){
				starting = true;
				velX = -flySpeed;
			}
		}
		//slime 3
		if(!attacking && number ==  3){
			if(x <= 2824){
				velX = flySpeed;
			}
			if(x >= 3200){
				starting = true;
				velX = -flySpeed;
			}
		}
		//slime 4
		if(!attacking && number ==  4){
			if(x <= 3612){
				velX = flySpeed;
			}
			if(x >= 4080){
				starting = true;
				velX = -flySpeed;
			}
		}
		//slime 5
		if(!attacking && number ==  5){
			if(x <= 2173){
				velX = flySpeed;
			}
			if(x >= 2700){
				starting = true;
				velX = -flySpeed;
			}
		}
		//slime 6
		if(!attacking && number ==  6){
			if(x <= 1564){
				velX = flySpeed;
			}
			if(x >= 1920){
				starting = true;
				velX = -flySpeed;
			}
		}
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.Player){
				//Slime 1 & 2 attacking
				if(number == 1 || number == 2){
					if((tempObject.getX() > 896 && tempObject.getX() < 1440) && (tempObject.getY() > 520 && tempObject.getY() < 680)){
						attacking = true;
					}else{
						attacking = false;
					}
				}
				//Slime 3 attacking
				if(number == 3){
					if(tempObject.getX() > 2824 && tempObject.getX() < 3200){
						attacking = true;
					}else{
						attacking = false;
					}
				}
				//Slime 4 attacking
				if(number == 4){
					if(tempObject.getX() > 3612 && tempObject.getX() < 4080){
						attacking = true;
					}else{
						attacking = false;
					}
				}
				//Slime 5 attacking
				if(number == 5){
					if((tempObject.getX() > 2173 && tempObject.getX() < 2700) && (tempObject.getY() < 130)){
						attacking = true;
					}else{
						attacking = false;
					}
				}
				//Slime 6 attacking
				if(number == 6){
					if((tempObject.getX() > 1564 && tempObject.getX() < 1920) && (tempObject.getY() < 360 && tempObject.getY() > 80)){
						attacking = true;
					}else{
						attacking = false;
					}
				}
			}
		}
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.Slime){
				if(y > 1400 && tempObject.getDeath()){
					handler.removeObject(tempObject);
				}
			}
		}

		slimeLeft.runAnimation();
		slimeRight.runAnimation();
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
				slimeRight.drawAnimations(g,(int) x, (int) y - 13 , 92, 46);
			}
			if(facingLeft && !death){
				slimeLeft.drawAnimations(g,(int) x, (int) y - 13 , 92, 46);
			}
		}
		
	}
	public Rectangle getBounds() {
		return new Rectangle((int) x , (int) y -13 , 92, 46);	
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