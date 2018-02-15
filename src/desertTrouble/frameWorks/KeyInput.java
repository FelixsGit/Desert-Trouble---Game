package desertTrouble.frameWorks;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import desertTrouble.Window.Game;
import desertTrouble.Window.Game.STATE;
import desertTrouble.Window.Handler;


public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	File footStepSound = new File("res/FootStepSound.wav");
	private File GameMusic = new File("res/GameMusic.wav");
	private File GameMusic2 = new File("res/GamemMusic2.wav");
	public static String level;


	
	public KeyInput(Handler handler){
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();

		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.Player){
				if(key == KeyEvent.VK_LEFT && !tempObject.getDoBlackScreen()){
					tempObject.setVelX(-7);
				}
				if(key == KeyEvent.VK_RIGHT && !tempObject.getDoBlackScreen()){

					tempObject.setVelX(7);
				}
				if(key == KeyEvent.VK_SPACE && !tempObject.getJumping() && !tempObject.getFalling() && !tempObject.getDoBlackScreen()){
					tempObject.setVelY(-30);
					tempObject.setJumping(true);
	
				}
				if(key == KeyEvent.VK_ESCAPE && Game.state == STATE.LEVELONE && !tempObject.getDoBlackScreen()){
					level = "one";
					Game.state = STATE.PAUSEDMENU;
				}
				if(key == KeyEvent.VK_ESCAPE && Game.state == STATE.LEVELTWO && !tempObject.getDoBlackScreen()){
					level = "two";
					Game.state = STATE.PAUSEDMENU;

				}
				/*
				if(key == KeyEvent.VK_W){
					tempObject.setVelY(-10);
				}
				if(key == KeyEvent.VK_S){
					tempObject.setVelY(10);
				}
				*/
			}
		}
	}
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.Player){
				if(key == KeyEvent.VK_RIGHT && !tempObject.getDoBlackScreen()){
				
					tempObject.setVelX(0);
				}
				if(key == KeyEvent.VK_LEFT && !tempObject.getDoBlackScreen()){
					
					tempObject.setVelX(0);
				}
				/*
				if(key == KeyEvent.VK_W){
				
					tempObject.setVelY(0);
				}
				if(key == KeyEvent.VK_S){
				
					tempObject.setVelY(0);
				}
				*/
			}
		}		
	}
}