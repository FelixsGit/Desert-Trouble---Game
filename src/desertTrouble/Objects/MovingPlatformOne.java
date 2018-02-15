package desertTrouble.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import desertTrouble.Window.BufferedImageLoader;
import desertTrouble.Window.Game;
import desertTrouble.Window.Game.STATE;
import desertTrouble.Window.Handler;
import desertTrouble.frameWorks.GameObject;
import desertTrouble.frameWorks.ObjectId;
import desertTrouble.frameWorks.Texture;

public class MovingPlatformOne extends GameObject {
	
	private Handler handler;
	private Texture tex = Game.getInstance();
	BufferedImageLoader loader = new BufferedImageLoader();
	private String type;
	private int number;

	
	public MovingPlatformOne(float x, float y, Handler handler, ObjectId id, String type, int number) {
		super(x, y, id);
		this.handler = handler;
		this.type = type;
		this.number = number;
		
		if(number == 1){
			velX = -3;
		}
		
	}

	public void tick(LinkedList<GameObject> object) {

		if(number == 1){
			if(x <= 950){
				velX = 3;
			}
			if(x >= 1350){
				velX = -3;

			}
			x += velX;
		}
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.Buttons){
				if(tempObject.getGreenButtonPressed() && Game.state == STATE.LEVELONE){
					falling = true;
				}
				if(tempObject.getBlueButtonPressed() && Game.state == STATE.LEVELTWO){
					falling = true;
				}
			}
		}
		
		if(number == 2 && falling){
			if(y <= 287){
				velY = 2;
			}
			if(y >= 287){
				velY = 0;
			}
			y += velY;
		}
		if(number == 2 && falling){
			if(y <= 287){
				velY = 2;
			}
			if(y >= 287){
				velY = 0;
			}
			y += velY;
		}
		if(number == 3 && falling){
			if(y > 200){
				velY = -0.5f;
			}
			if(y <= 200){
				velY = 0;
			}
			y += velY;
		}
	}

	public void render(Graphics g) {
	
		
		/*
		g.setColor(Color.green);
		g.fillRect((int)x,(int) y, 32, 32);
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.RED);
		g2d.draw(getBounds());
		
		
		/*
		if(type.equals("sand")){
			g.drawImage(tex.Ground[0], (int) x, (int) y, 32, 32, null);
		}
		if(type.equals("sandCenter")){
			g.drawImage(tex.Ground[1], (int) x, (int) y, 106, 32, null);
		}
		if(type.equals("sandCenter_rounded")){
			g.drawImage(tex.Ground[2], (int) x, (int) y, 32, 32, null);
		}
		if(type.equals("sandCliffLeft")){
			g.drawImage(tex.Ground[3], (int) x, (int) y, 32, 32, null);
		}
		if(type.equals("sandCliffLeftAlt")){
			g.drawImage(tex.Ground[4], (int) x, (int) y, 32, 32, null);
		}
		if(type.equals("sandCliffRight")){
			g.drawImage(tex.Ground[5], (int) x, (int) y, 32, 32, null);
		}
		if(type.equals("sandCliffRightAlt")){
			g.drawImage(tex.Ground[6], (int) x, (int) y, 32, 32, null);
		}
		if(type.equals("sandHalf")){
			g.drawImage(tex.Ground[7], (int) x, (int) y, 32, 32, null);
		}
		if(type.equals("sandHalfLeft")){
			g.drawImage(tex.Ground[8], (int) x, (int) y, 32, 32, null);
		}
		if(type.equals("sandHalfMid")){
			g.drawImage(tex.Ground[9], (int) x, (int) y, 32, 32, null);
		}
		if(type.equals("sandHalfRight")){
			g.drawImage(tex.Ground[10], (int) x, (int) y, 32, 32, null);
		}
		if(type.equals("sandHillLeft")){
			g.drawImage(tex.Ground[11], (int) x, (int) y, 32, 32, null);
		}
		if(type.equals("sandHillLeft2")){
			g.drawImage(tex.Ground[12], (int) x, (int) y, 32, 32, null);
		}
		if(type.equals("sandHillRight")){
			g.drawImage(tex.Ground[13], (int) x, (int) y, 32, 32, null);
		}
		if(type.equals("sandHillRight2")){
			g.drawImage(tex.Ground[14], (int) x, (int) y, 32, 32, null);
		}
		if(type.equals("sandLeft")){
			g.drawImage(tex.Ground[15], (int) x, (int) y, 32, 32, null);
		}
		if(type.equals("sandMid")){
			if(number == 1){
				g.drawImage(tex.Ground[16], (int) x, (int) y, 106, 32, null);
			}
			else{
				g.drawImage(tex.Ground[16], (int) x, (int) y, 32, 32, null);
			}
		}
		if(type.equals("sandRight")){
			g.drawImage(tex.Ground[17], (int) x, (int) y, 32, 32, null);
		}
		if(type.equals("sandLedgeLeft")){
			g.drawImage(tex.Ground[18], (int) x, (int) y, 32, 32, null);
		}
		if(type.equals("sandLedgeRight")){
			g.drawImage(tex.Ground[19], (int) x, (int) y, 32, 32, null);
		}
		*/
		
		if(type.equals("sandMid")){
			if(number == 1){
				g.drawImage(tex.Ground2[0], (int) x, (int) y, 106, 32, null);
			}
			else{
				g.drawImage(tex.Ground2[0], (int) x, (int) y, 32, 32, null);
			}
		}
		if(type.equals("sandCenter")){
			g.drawImage(tex.Ground2[1], (int) x, (int) y, 32, 32, null);
		}
		if(type.equals("sandBottomLeft")){
			g.drawImage(tex.Ground2[2], (int) x, (int) y, 32, 32, null);
		}
		if(type.equals("sandRight")){
			g.drawImage(tex.Ground2[3], (int) x, (int) y, 32, 32, null);
		}
		if(type.equals("sandBottomRight")){
			g.drawImage(tex.Ground2[4], (int) x, (int) y, 32, 32, null);
		}
		if(type.equals("sandLeft")){
			g.drawImage(tex.Ground2[5], (int) x, (int) y, 32, 32, null);
		}
		if(type.equals("sandBottom")){
			g.drawImage(tex.Ground2[6], (int) x, (int) y, 32, 32, null);
		}
		if(type.equals("sandSmallRight")){
			g.drawImage(tex.Ground2[7], (int) x, (int) y, 32, 32, null);
		}
		if(type.equals("sandSmallMid")){
			g.drawImage(tex.Ground2[8], (int) x, (int) y, 32, 32, null);
		}
		if(type.equals("sandSmallLeft")){
			g.drawImage(tex.Ground2[9], (int) x, (int) y, 32, 32, null);
		}
		
	}
	
	
	public Rectangle getBounds() {
		if(number == 1){
			return new Rectangle((int) x, (int) y, 106, 32);	
		}
		else{
			return new Rectangle((int) x, (int) y, 32, 32);	
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