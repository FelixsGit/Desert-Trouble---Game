package desertTrouble.frameWorks;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import desertTrouble.Window.Game;
import desertTrouble.Window.Game.STATE;


public class MouseInput implements MouseListener{


	private File GameMusic = new File("res/GameMusic.wav");
	private File GameMusic2 = new File("res/GamemMusic2.wav");
	private File MenuMusic = new File("res/MenuMusic.wav");
	private File buttonClick = new File("res/ClickSound.wav");
	private File DefeatMusic = new File("res/DefeatMusic.wav");

	
	public void mouseClicked(MouseEvent e) {
		
		
	}

	
	public void mouseEntered(MouseEvent e) {
		
		
	}

	
	public void mouseExited(MouseEvent e) {
		
		
	}


	public void mousePressed(MouseEvent e) {
		
		/**
		 * MENU Text & buttons
		 */
		
		if(Game.state == STATE.MAINMENU){
			int xm = e.getX();
			int ym = e.getY();	
			//Pressing Play Button
			if(xm >= 306 && xm <= 394){
				if(ym >= 194 && ym <= 237 ){
					Game.playSound(buttonClick, true);
					Game.playMusic(MenuMusic, false);
					Game.playMusic(GameMusic, true);
					Game.state = STATE.LEVELONE;
				}
			}
			//Pressing Help Button
			if(xm >= 310 && xm <= 387){
				if(ym >= 256 && ym <= 299){
					Game.playSound(buttonClick, true);
					Game.state = STATE.HELPMENU;
				}
			}
			
		}
		
		if(Game.state == STATE.HELPMENU){
			int xm = e.getX();
			int ym = e.getY();	
			//Pressing back Button
			if(xm >= 1172 && xm <= 1262){
				if(ym >= 881 && ym <= 916){
					Game.playSound(buttonClick, true);
					Game.state = STATE.MAINMENU;
				}
			}
		}
		if(Game.state == STATE.VICTORY){
			int xm = e.getX();
			int ym = e.getY();	
			//Pressing menu Button
			if(xm >= 1172 && xm <= 1262){
				if(ym >= 881 && ym <= 916){
					Game.playSound(buttonClick, true);
					Game.playMusic(DefeatMusic, false);
					Game.playMusic(MenuMusic, true);
					Game.state = STATE.GAMEEND;
				}
			}
		}
		if(Game.state == STATE.DEFEAT){
			int xm = e.getX();
			int ym = e.getY();	
			//Pressing menu Button
			if(xm >= 1172 && xm <= 1262){
				if(ym >= 881 && ym <= 916){
					Game.playSound(buttonClick, true);
					Game.playMusic(DefeatMusic, false);
					Game.playMusic(MenuMusic, true);
					Game.state = STATE.GAMEEND;
				}
			}
		}
		if(Game.state == STATE.PAUSEDMENU && KeyInput.level.equals("one")){
			int xm = e.getX();
			int ym = e.getY();	
			//Pressing continue button
			if(xm >= 500 && xm <= 725){
				if(ym >= 366 && ym <= 422){
					Game.playSound(buttonClick, true);
					Game.state = STATE.LEVELONE;
					Game.pausedCheck = false;
				}
			}
			//Pressing give up button
			if(xm >= 510 && xm <= 718){
				if(ym >= 454 && ym <= 510){
					Game.playSound(buttonClick, true);
					Game.state = STATE.GAMEEND;
					Game.playMusic(GameMusic, false);
					Game.playMusic(MenuMusic, true);
				}
			}
		}
		if(Game.state == STATE.PAUSEDMENU && KeyInput.level.equals("two")){
			int xm = e.getX();
			int ym = e.getY();	
			//Pressing continue button
			if(xm >= 500 && xm <= 725){
				if(ym >= 366 && ym <= 422){
					Game.playSound(buttonClick, true);
					Game.state = STATE.LEVELTWO;
					Game.pausedCheck = false;
				}
			}
			//Pressing give up button
			if(xm >= 510 && xm <= 718){
				if(ym >= 454 && ym <= 510){
					Game.playSound(buttonClick, true);
					Game.state = STATE.GAMEEND;
					Game.playMusic(GameMusic2, false);
					Game.playMusic(MenuMusic, true);
				}
			}
		}
		
	}

	
	public void mouseReleased(MouseEvent e) {
	
		
	}

}
