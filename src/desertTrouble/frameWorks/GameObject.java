package desertTrouble.frameWorks;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.util.LinkedList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public abstract class GameObject {
	
	protected float x, y;
	protected float velX = 0 , velY = 0;
	protected ObjectId id;
	protected boolean death = false;
	protected boolean jumping = false;
	protected boolean facingRight = false;
	protected boolean facingLeft = true;
	protected boolean attacking = false;
	protected boolean faint = false;
	protected boolean toClose = false;
	protected boolean falling = false;
	protected Clip MusicClip;
	protected Clip SoundClip;
	protected boolean run = false;
	protected String type;
	protected boolean greenButtonPressed = false;
	protected boolean blueButtonPressed = false;
	protected int health;
	protected boolean gotGreenKey = false;
	protected boolean gotBlueKey = false;
	protected boolean greenDoorOpen = false;
	protected boolean blueDoorOpen = false;
	protected boolean openGreenDoor = false;
	protected boolean openBlueDoor = false;
	protected boolean boosterPressed = false;
	protected boolean canBePickedUp = false;
	protected boolean doBlackScreen = false;
	protected int number;
	
	public GameObject(float x, float y, ObjectId id){
		this.x = x;
		this.y = y;	
		this.id = id;
	}
	public abstract void tick(LinkedList<GameObject> object);
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	
	public boolean getDoBlackScreen(){
		return this.doBlackScreen;
	}
	public void setDoBlackScreen(boolean doBlackScreen){
		this.doBlackScreen = doBlackScreen;
	}
	public boolean getCanBePickedUp(){
		return this.canBePickedUp;
	}
	public void setCanBePickedUp(boolean canBePickedUp){
		this.canBePickedUp = canBePickedUp;
	}
	public boolean getBoosterPressed(){
		return boosterPressed;
	}
	public void setBoosterPressed(boolean boosterPressed){
		this.boosterPressed = boosterPressed;
	}
	public boolean getOpenGreenDoor(){
		return openGreenDoor;
	}
	public void setOpenGreenDoor(boolean openGreenDoor){
		this.openGreenDoor = openGreenDoor;
	}
	public boolean getGotGreenKey(){
		return gotGreenKey;
	}
	public void setGotGreenKey(boolean gotGreenKey){
		this.gotGreenKey = gotGreenKey;
	}
	
	public boolean getGreenDoorOpen(){
		return greenDoorOpen;
	}
	public void setGreenDoorOpen(boolean greenDoorOpen){
		this.greenDoorOpen = greenDoorOpen;
	}
	
	
	public boolean getOpenBlueDoor(){
		return openBlueDoor;
	}
	public void setOpenBlueDoor(boolean openBlueDoor){
		this.openBlueDoor = openBlueDoor;
	}
	public boolean getGotBlueKey(){
		return gotBlueKey;
	}
	public void setGotBlueKey(boolean gotBlueKey){
		this.gotBlueKey = gotBlueKey;
	}
	
	public boolean getBlueDoorOpen(){
		return blueDoorOpen;
	}
	public void setBlueDoorOpen(boolean blueDoorOpen){
		this.blueDoorOpen = blueDoorOpen;
	}
	public int getHealth(){
		return health;
	}
	public void setHealth(int health){
		this.health = health;
	}
	public boolean getGreenButtonPressed(){
		return greenButtonPressed;
	}
	public void setGreenButtonPressed(boolean greenButtonPressed){
		this.greenButtonPressed = greenButtonPressed;
	}
	public boolean getBlueButtonPressed(){
		return blueButtonPressed;
	}
	public void setBlueButtonPressed(boolean blueButtonPressed){
		this.blueButtonPressed = blueButtonPressed;
	}
	public String getType(){
		return type;
	}
	public void setType(String type){
		this.type = type;
	}
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	public void setX(float x){
		this.x = x;
	}
	public void setY(float y){
		this.y = y;
	}
	public float getVelX(){
		return velX;
	}
	public float getVelY(){
		return velY;
	}
	public void setVelX(float velX){
		this.velX = velX;
	}
	public void setVelY(float velY){
		this.velY = velY;
	}
	public ObjectId getId(){
		return id;
	}
	
	public boolean getDeath(){
		return death;
	}
	public void setDeath(boolean death){
		this.death = death;
	}
	
	public boolean getFalling(){
		return falling;
	}
	public void setFalling(boolean falling){
		this.falling = falling;
	}
	public boolean getJumping(){
		return jumping;
	}
	public void setJumping(boolean jumping){
		this.jumping = jumping;
	}
	public boolean getFacingRight(){
		return facingRight;
	}
	public void setFacingRight(boolean facingRight){
		this.facingRight = facingRight;
	}	
	public boolean getFacingLeft(){
		return facingLeft;
	}
	public void setFacingLeft(boolean facingLeft){
		this.facingLeft = facingLeft;
	}	

	public boolean getAttacking(){
		return attacking;
	}
	public void setAttacking(boolean attacking){
		this.attacking = attacking;
	}
	public boolean getFaint(){
		return faint;
	}
	public void setFaint(boolean faint){
		this.faint = faint;
	}
	public boolean getToClose(){
		return toClose;
	}
	public void setToClose(boolean toClose){
		this.toClose = toClose;
	}

	public void playSound(File Sound, boolean run){
		this.run = run;
		if(run){
			try{
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Sound);
				SoundClip = AudioSystem.getClip();
				SoundClip.open(audioInputStream);
				SoundClip.start();
				
			}catch(Exception e){
				
			}
		}
		else{
			SoundClip.stop();
		}
			
	}
	public void playMusic(File Sound, boolean run){
		this.run = run;
		if(run){
			try{
	
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Sound);
				MusicClip = AudioSystem.getClip();
				MusicClip.open(audioInputStream);
				MusicClip.start();
			
			}catch(Exception e){
				
			}
		}
		else
			MusicClip.stop();
	}	
}