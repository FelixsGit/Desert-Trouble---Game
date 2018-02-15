package desertTrouble.Objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;

import desertTrouble.Window.Animation;
import desertTrouble.Window.BufferedImageLoader;
import desertTrouble.Window.Game;
import desertTrouble.Window.Game.STATE;
import desertTrouble.Window.Handler;
import desertTrouble.frameWorks.GameObject;
import desertTrouble.frameWorks.ObjectId;
import desertTrouble.frameWorks.Texture;


public class Player extends GameObject {
	
	private Handler handler;
	private Texture tex = Game.getInstance();
	private float gravity = 1.75f;
	private boolean canBeAttacked = true;
	private boolean justAttacked = false;
	private boolean justFell = false;
	private long timeWhenAttackedBySpikes;
	private long elapsedTime;
	public static int COINSCOLLECTED = 0;

	private Animation PlayerWalkLeft;
	private Animation PlayerWalkRight;
	private Animation PlayerDuckRight;
	private Animation PlayerJumpRight;
	private Animation PlayerStandRight;
	private Animation PlayerDuckLeft;
	private Animation PlayerJumpLeft;
	private Animation PlayerStandLeft;
	
	private Animation PlayerWalkLeftFlash;
	private Animation PlayerWalkRightFlash;
	private Animation PlayerDuckRightFlash;
	private Animation PlayerJumpRightFlash;
	private Animation PlayerStandRightFlash;
	private Animation PlayerDuckLeftFlash;
	private Animation PlayerJumpLeftFlash;
	private Animation PlayerStandLeftFlash;
	private Animation coinSpin;
	BufferedImageLoader loader = new BufferedImageLoader();
	
	private BufferedImage fullHearth = null;
	private BufferedImage emptyHearth = null;
	private BufferedImage greenKey = null;
	private BufferedImage blueKey = null;
	public static BufferedImage level = null;
	File coinPickUpSound = new File("res/CoinPickUpSound.wav");
	File buttonPushSound = new File("res/ButtonPushSound.wav");
	File pickUpKeySound = new File("res/PickUpKeySound.wav");
	File openDoorSound = new File("res/OpenDoorSound.wav");
	File loseHealthSound = new File("res/LoseHealthSound.wav");
	File flyAttackSound = new File("res/WaspAttackSound.wav");
	File flyDeathSound = new File("res/WaspDeathSound.wav");
	File levelCompleteSound = new File("res/LevelCompleteSound.wav");
	File footStepSound = new File("res/FootStepSound.wav");
	File GameMusic = new File("res/GameMusic.wav");
	File GameMusic2 = new File("res/GameMusic2.wav");
	File MenuMusic = new File("res/MenuMusic.wav");
	File BoosterSound = new File("res/BoosterSound.wav");
	File SlimeAttackSound = new File("res/SlimeAttackSound.wav");
	File SlimeDeathSound = new File("res/SlimeDeathSound.wav");
	File DefeatMusic = new File("res/DefeatMusic.wav");
	File VictoryMusic = new File("res/VictoryMusic.wav");
	
	private long timeWhenStepSoundWasMade;
	private long elapsedTimeStep;
	private boolean canPlayFootstepSound = true;
	
	private long timeWhenGameWasLost;
	private long elapsedSinceGameWasLost;
	private boolean defeat;
	
	private long timeWhenGameWasWon;
	private long elapsedSinceGameWasWon;
	private boolean victory;
	private boolean startedVictorySequence = false;
	
	
	private long timeWhenFlagWasTaken;
	private long elapsedTimeFlagTaken;
	private boolean moveToNextLevel = false;
	
	private long timeBetweenLevelStart;
	private long elapsedTimeBetweenLevel;
	private boolean startNextLevel = false;
	
	private long timeWhenBoosterWasTriggered;
	private long elapsedTimeSinceBoosterWasTriggered;
	private boolean readyToUseBooster = true;
	
	private int coinsAtLevelOne = 14;
	private int coinsAtLevelTwo = 14+25;

	public Player(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		
		////////////////////////////////
		
		PlayerWalkLeft = new Animation(1, tex.PlayerWalkLeft[0], tex.PlayerWalkLeft[1], tex.PlayerWalkLeft[2], tex.PlayerWalkLeft[3], tex.PlayerWalkLeft[4], 
			tex.PlayerWalkLeft[5], tex.PlayerWalkLeft[6], tex.PlayerWalkLeft[7], tex.PlayerWalkLeft[8], tex.PlayerWalkLeft[9], tex.PlayerWalkLeft[10]);
		
		PlayerWalkRight = new Animation(1, tex.PlayerWalkLeft[21], tex.PlayerWalkLeft[20], tex.PlayerWalkLeft[19], tex.PlayerWalkLeft[18], tex.PlayerWalkLeft[17], 
			tex.PlayerWalkLeft[16], tex.PlayerWalkLeft[15], tex.PlayerWalkLeft[14], tex.PlayerWalkLeft[13], tex.PlayerWalkLeft[12], tex.PlayerWalkLeft[11]);
		
		PlayerDuckRight = new Animation(1, tex.PlayerRightSprite[2]);
		PlayerJumpRight = new Animation(1, tex.PlayerRightSprite[0]);
		PlayerStandRight = new Animation(1, tex.PlayerRightSprite[1]);
		
		PlayerDuckLeft = new Animation(1, tex.PlayerLeftSprite[0]);
		PlayerJumpLeft = new Animation(1, tex.PlayerLeftSprite[2]);
		PlayerStandLeft = new Animation(1, tex.PlayerLeftSprite[1]);
		
		coinSpin = new Animation(1, tex.Ground[20]);
		
		//////////////////////////////////
	
		PlayerWalkLeftFlash = new Animation(1, tex.PlayerWalkLeftFlash[0], tex.PlayerWalkLeftFlash[1], tex.PlayerWalkLeftFlash[2], tex.PlayerWalkLeftFlash[3], 
				tex.PlayerWalkLeftFlash[4], tex.PlayerWalkLeftFlash[5], tex.PlayerWalkLeftFlash[6], tex.PlayerWalkLeftFlash[7], tex.PlayerWalkLeftFlash[8], 
				tex.PlayerWalkLeftFlash[9], tex.PlayerWalkLeftFlash[10]);
			
		PlayerWalkRightFlash = new Animation(1, tex.PlayerWalkLeftFlash[21], tex.PlayerWalkLeftFlash[20], tex.PlayerWalkLeftFlash[19], tex.PlayerWalkLeftFlash[18], 
				tex.PlayerWalkLeftFlash[17], tex.PlayerWalkLeftFlash[16], tex.PlayerWalkLeftFlash[15], tex.PlayerWalkLeftFlash[14], tex.PlayerWalkLeftFlash[13], 
				tex.PlayerWalkLeftFlash[12], tex.PlayerWalkLeftFlash[11]);
			
		PlayerDuckRightFlash = new Animation(1, tex.PlayerRightSpriteFlash[2]);
		PlayerJumpRightFlash = new Animation(1, tex.PlayerRightSpriteFlash[0]);
		PlayerStandRightFlash = new Animation(1, tex.PlayerRightSpriteFlash[1]);
			
		PlayerDuckLeftFlash = new Animation(1, tex.PlayerLeftSpriteFlash[0]);
		PlayerJumpLeftFlash = new Animation(1, tex.PlayerLeftSpriteFlash[2]);
		PlayerStandLeftFlash = new Animation(1, tex.PlayerLeftSpriteFlash[1]);
			
		//////////////////////////////////
		
		fullHearth = loader.loadImage("/FullHearth.png");
		emptyHearth = loader.loadImage("/EmptyHearth.png");
		greenKey = loader.loadImage("/GreenKey.png");
		blueKey = loader.loadImage("/BlueKey.png");
		
		//Max player health
		health = 5;
		
		//Starting facing
		facingRight = true;
		
		
	}

	public void tick(LinkedList<GameObject> object) {
		
		//footsteps timer
		elapsedTimeStep = (System.currentTimeMillis() - timeWhenStepSoundWasMade);
		if(elapsedTimeStep >= 300 ){
			canPlayFootstepSound= true;
		}
		if(canPlayFootstepSound && velY == 0 && velX != 0){
			timeWhenStepSoundWasMade = System.currentTimeMillis();
			Game.playSound(footStepSound, true);
			canPlayFootstepSound = false;
		}
		
		//Booster timer	
		if(!readyToUseBooster){		
			elapsedTimeSinceBoosterWasTriggered = (System.currentTimeMillis() - timeWhenBoosterWasTriggered) / 1000;
			if(elapsedTimeSinceBoosterWasTriggered >= 5){
				for(int i = 0; i < handler.object.size(); i++){
					GameObject tempObject = handler.object.get(i);
					if(tempObject.getId() == ObjectId.Booster){
						readyToUseBooster = true;
						tempObject.setBoosterPressed(false);
					}
				}
			}
		}
		
		//Enables Victory
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.Player && victory){
				tempObject.setDoBlackScreen(true);
				elapsedSinceGameWasWon = (System.currentTimeMillis() - timeWhenGameWasWon) / 1000;
				if(!startedVictorySequence){
					Game.playMusic(GameMusic2, false);
					startedVictorySequence = true;
				}
				if(elapsedSinceGameWasWon >= 3){
					Game.state = STATE.BLACKSCREEN;
				}
				if(elapsedSinceGameWasWon >= 5){
					Game.state = STATE.VICTORY;
					Game.playMusic(VictoryMusic, true);
					
				}
			}
		}
		
		//Check if player fell of the map
		if(y >= Game.HEIGHT + 50 && !defeat){
			justFell = true;
		}
		
		//Losing Health OR Dying
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if((tempObject.getId() == ObjectId.Player && !victory && justAttacked && !defeat) || (tempObject.getId() == ObjectId.Player && !victory && justFell && !defeat)){
				//Falling but no death
				if(justFell && health > 1){
					y = Game.HEIGHT- 250;
					x = 150;
					facingRight = true;
					health--;
					justAttacked = false;
					Game.playSound(loseHealthSound, true);
					justFell = false;
				}
				
				//Falling to death
				else if(justFell && health == 1){
					defeat = true;
					health --;
					justAttacked = false;
					if(Game.state == STATE.LEVELONE){
						Game.playMusic(GameMusic, false);
						tempObject.setDoBlackScreen(true);
						timeWhenGameWasLost = System.currentTimeMillis();
					}
					if(Game.state == STATE.LEVELTWO){
						Game.playMusic(GameMusic2, false);
						tempObject.setDoBlackScreen(true);
						timeWhenGameWasLost = System.currentTimeMillis();
					}
					justFell = false;
				}
				
				//Attacked but no death
				else if(justAttacked && health > 1){
					health--;
					justAttacked = false;
				}
				
				//Attacked and death
				else if(justAttacked && health == 1){
					health--;
					defeat = true;
					justAttacked = false;
					if(Game.state == STATE.LEVELONE){
						Game.playMusic(GameMusic, false);
						tempObject.setDoBlackScreen(true);
						timeWhenGameWasLost = System.currentTimeMillis();
					}
					if(Game.state == STATE.LEVELTWO){
						Game.playMusic(GameMusic2, false);
						tempObject.setDoBlackScreen(true);
						timeWhenGameWasLost = System.currentTimeMillis();
					}
				}		
			}
			
			//Checking defeat Timer
			if(tempObject.getId() == ObjectId.Player){
				if(tempObject.getDoBlackScreen() && !victory){
					elapsedSinceGameWasLost = (System.currentTimeMillis() - timeWhenGameWasLost) / 1000;
					if(elapsedSinceGameWasLost >= 3){
						Game.state = STATE.BLACKSCREEN;
					}
					if(elapsedSinceGameWasLost >= 5){
						Game.state = STATE.DEFEAT;
						Game.playMusic(DefeatMusic, true);
					}
				}
			}
		}
	
	
		//Facing left
		if(velX > 0){
			facingRight = true;
			facingLeft = false;
		}
		//Facing right
		if(velX < 0){
			facingLeft = true;
			facingRight = false;
		}
		
		//Allowes movement when not in between levels
		if(!moveToNextLevel){
			x += velX;
			y += velY;
		}

		//Check if falling
		if(velY > 0){
			falling = true;
		}
		
		//Gravity
		velY += gravity;

		// Max falling speed
		if(velY >= 15){
			velY = 15;
		}
		
		//Immune to dmg timer
		if(!canBeAttacked){
			elapsedTime = (System.currentTimeMillis() - timeWhenAttackedBySpikes) / 1000;
			if(elapsedTime >= 1.5 ){
				canBeAttacked = true;
			}
		}
		
		//end of level sound timer
		if(moveToNextLevel && !startNextLevel && (Game.state == STATE.LEVELONE || Game.state == STATE.LEVELTWO)){
			elapsedTimeFlagTaken = (System.currentTimeMillis() - timeWhenFlagWasTaken) / 1000;
			if(elapsedTimeFlagTaken >= 2.5){
				if(Game.state == STATE.LEVELONE){
					Game.playMusic(GameMusic2, true);
					Game.state = STATE.ENDOFLEVELSTATE;
					moveToNextLevel = false;
					startNextLevel = true;
					timeBetweenLevelStart = System.currentTimeMillis();
				}
				if(Game.state == STATE.LEVELTWO){
					Game.state = STATE.GAMEEND;
					Game.playMusic(MenuMusic, true);
					moveToNextLevel = false;
					startNextLevel = true;
					timeBetweenLevelStart = System.currentTimeMillis();
				}
			}
		}
		//blackscreen between levels timer
		if(startNextLevel){
			elapsedTimeBetweenLevel = (System.currentTimeMillis() - timeBetweenLevelStart) / 1000;
			if(elapsedTimeBetweenLevel >= 5){
				if(Game.state == STATE.GAMEEND){
					Game.state = STATE.GAMEEND;
					startNextLevel = false;
				}else{
					startNextLevel = false;
					Game.state = STATE.LEVELTWO;
					y = Game.HEIGHT- 250;
					x = 150;
				}			
			}
		}

		//Animations
		PlayerWalkLeft.runAnimation();
		PlayerWalkRight.runAnimation();
		PlayerDuckRight.runAnimation();
		PlayerJumpRight.runAnimation();
		PlayerStandRight.runAnimation();
		PlayerDuckLeft.runAnimation();
		PlayerJumpLeft.runAnimation();
		PlayerStandLeft.runAnimation();	
		PlayerWalkLeftFlash.runAnimation();
		PlayerWalkRightFlash.runAnimation();
		PlayerDuckRightFlash.runAnimation();
		PlayerJumpRightFlash.runAnimation();
		PlayerStandRightFlash.runAnimation();
		PlayerDuckLeftFlash.runAnimation();
		PlayerJumpLeftFlash.runAnimation();
		PlayerStandLeftFlash.runAnimation();	
		coinSpin.runAnimation();
		
		//Checks for collisons if player is alive
		if(!defeat){
			Collision();
		}
	}
	private void Collision(){
		
		//collision left wall
		if(x <= 0){
			x = 0;
			velX = 0;
		}
		//collision right wall
		if(x >= 6400){
			x = 6400;
			velX = 0;
		}
		
		//ground collision
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.FlatGroundBlock){
				if(tempObject.getBounds().intersects(getBoundsBottom())){
					velY = 0;
					jumping = false;
					y = tempObject.getY() - 97;
					falling = false;		
				}
				if(tempObject.getBounds().intersects(getBoundsTop())){	
					velY = 0;
					y = tempObject.getY() + 32;			
				}
				if(tempObject.getBounds().intersects(getBoundsLeft())){
					velX = 0;
					x = tempObject.getX() + 32;
				}
				if(tempObject.getBounds().intersects(getBoundsRight())){
					velX = 0;
					x = tempObject.getX() - 55 ;	
				}
			}
			
			//Moving platform collision
			if(tempObject.getId() == ObjectId.MovingPlatformOne){
				if(tempObject.getBounds().intersects(getBoundsBottom())){
					velY = 0;
					jumping = false;
					y = tempObject.getY() - 97;
					falling = false;
				}
				if(tempObject.getBounds().intersects(getBoundsTop())){	
					velY = 0;
					y = tempObject.getY() + 32;			
				}
				if(tempObject.getBounds().intersects(getBoundsLeft())){
					velX = 0;
					x = tempObject.getX() + 32;		
				}
				if(tempObject.getBounds().intersects(getBoundsRight())){
					velX = 0;
					x = tempObject.getX() - 55 ;	
				}
			}
			
			//Coin collsion
			if(tempObject.getId() == ObjectId.Coin){

				if((tempObject.getBounds().intersects(getBounds()) && tempObject.getVelY() >= 0) || (tempObject.getBounds().intersects(getBounds()) && tempObject.getVelY() == -0.5f )){
					handler.removeObject(tempObject);
					COINSCOLLECTED++;
					Game.playSound(coinPickUpSound, true);
				}
			}
			
			//button pressed collision
			if(tempObject.getId() == ObjectId.Buttons){
				if(tempObject.getBounds().intersects(getBounds()) && !tempObject.getGreenButtonPressed() && Game.state == STATE.LEVELONE){
					tempObject.setGreenButtonPressed(true);
					Game.playSound(buttonPushSound, true);
				}
				if(tempObject.getBounds().intersects(getBounds()) && !tempObject.getBlueButtonPressed() && Game.state == STATE.LEVELTWO){
					tempObject.setBlueButtonPressed(true);
					Game.playSound(buttonPushSound, true);
				}
			}
			
			//hit by spikes
			if(tempObject.getId() == ObjectId.Spikes && canBeAttacked && !defeat){
				if(tempObject.getBounds().intersects(getBounds())){
					canBeAttacked = false;
					timeWhenAttackedBySpikes = System.currentTimeMillis();
					Game.playSound(loseHealthSound, true);
					justAttacked = true;
				}
			}
			
			//pickup green key
			if(tempObject.getId() == ObjectId.Key && Game.state == STATE.LEVELONE){
				if(tempObject.getBounds().intersects(getBounds())){
					handler.removeObject(tempObject);
					gotGreenKey = true;
					Game.playSound(pickUpKeySound, true);
				}
			}
			
			//pickup blue key
			if(tempObject.getId() == ObjectId.Key && Game.state == STATE.LEVELTWO){
				if(tempObject.getBounds().intersects(getBounds())){
					handler.removeObject(tempObject);
					gotBlueKey = true;
					Game.playSound(pickUpKeySound, true);
				}
			}
			
			//Green Door Collision
			if(tempObject.getId() == ObjectId.Door && !gotGreenKey && Game.state == STATE.LEVELONE){
				if(tempObject.getBounds().intersects(getBoundsBottom())){
					velY = 0;
					jumping = false;
					y = tempObject.getY() - 97;
					falling = false;	
				}
				if(tempObject.getBounds().intersects(getBoundsTop())){	
					velY = 0;
					y = tempObject.getY() + 32;			
				}
				if(tempObject.getBounds().intersects(getBoundsLeft())){
					velX = 0;
					x = tempObject.getX() + 106;		
				}
				if(tempObject.getBounds().intersects(getBoundsRight())){
					velX = 0;
					x = tempObject.getX() - 85 ;	
				}
			}
			
			//Blue Door Collision
			if(tempObject.getId() == ObjectId.Door && !gotBlueKey && Game.state == STATE.LEVELTWO){
				if(tempObject.getBounds().intersects(getBoundsBottom())){
					velY = 0;
					jumping = false;
					y = tempObject.getY() - 97;
					falling = false;	
				}
				if(tempObject.getBounds().intersects(getBoundsTop())){	
					velY = 0;
					y = tempObject.getY() + 32;			
				}
				if(tempObject.getBounds().intersects(getBoundsLeft())){
					velX = 0;
					x = tempObject.getX() + 65;		
				}
				if(tempObject.getBounds().intersects(getBoundsRight())){
					velX = 0;
					x = tempObject.getX() - 85 ;	
				}
			}
			
			//Door Collision with key -- opening the green door
			if(Game.state == STATE.LEVELONE){
				if(tempObject.getId() == ObjectId.Door && gotGreenKey){
					if(tempObject.getBounds().intersects(getBounds())){
						tempObject.setOpenGreenDoor(true);
						handler.removeObject(tempObject);
						Game.playMusic(GameMusic, false);
						Game.playSound(openDoorSound, true);	
						gotGreenKey = false;
					}
				}
			}
			
			//Door Collision with key -- opening the Blue door
			if(Game.state == STATE.LEVELTWO){
				if(tempObject.getId() == ObjectId.Door && gotBlueKey){
					if(tempObject.getBounds().intersects(getBounds())){
						tempObject.setOpenBlueDoor(true);
						handler.removeObject(tempObject);
						Game.playMusic(GameMusic2, false);
						Game.playSound(openDoorSound, true);	
						gotBlueKey = false;
					}
				}
			}
			
			//attacked by fly
			if(tempObject.getId() == ObjectId.Fly && !defeat){
				if(tempObject.getBounds().intersects(getBounds()) && canBeAttacked && velY <= 1.76 && !tempObject.getDeath() && tempObject.getAttacking()){
					canBeAttacked = false;
					timeWhenAttackedBySpikes = System.currentTimeMillis();
					Game.playSound(flyAttackSound, true);	
					velY = -30;	
					justAttacked = true;
				}
			}
			
			//killing fly
			if(tempObject.getId() == ObjectId.Fly){
				if(tempObject.getBounds().intersects(getBoundsBottom()) && canBeAttacked && velY > 8 && !tempObject.getDeath()){
					tempObject.setDeath(true);
					Game.playSound(flyDeathSound, true);
				}
			}
			
			//attacked by slime
			if(tempObject.getId() == ObjectId.Slime && !defeat){
				if(tempObject.getBounds().intersects(getBounds()) && canBeAttacked && velY <= 1.76 && !tempObject.getDeath() && tempObject.getAttacking()){
					canBeAttacked = false;
					timeWhenAttackedBySpikes = System.currentTimeMillis();
					Game.playSound(SlimeAttackSound, true);	
					velY = -30;	
					justAttacked = true;
				}
			}
			
			//killing slime
			if(tempObject.getId() == ObjectId.Slime){
				if(tempObject.getBounds().intersects(getBoundsBottom()) && canBeAttacked && velY > 8 && !tempObject.getDeath()){
					tempObject.setDeath(true);
					Game.playSound(SlimeDeathSound, true);				
				}
			}
			
			//flag collision --> level 1 completed
			if(tempObject.getId() == ObjectId.Flag){
				if(tempObject.getBounds().intersects(getBounds()) && !moveToNextLevel && Game.state == STATE.LEVELONE){
					Game.playSound(levelCompleteSound, true);
					timeWhenFlagWasTaken = System.currentTimeMillis();
					moveToNextLevel = true;
				}
			}
			
			//flag collision --> level 2 completed
			if(tempObject.getId() == ObjectId.Flag && !victory){
				if(tempObject.getBounds().intersects(getBounds()) && !moveToNextLevel && Game.state == STATE.LEVELTWO){
					Game.playSound(levelCompleteSound, true);
					timeWhenGameWasWon = System.currentTimeMillis();
					x = tempObject.getVelX() + 90;
					victory = true;
				}

			}
			
			//Booster collision
			if(tempObject.getId() == ObjectId.Booster){
				if(tempObject.getBounds().intersects(getBoundsBottom()) && tempObject.getBoosterPressed() == false && readyToUseBooster && velY > -2){
					tempObject.setBoosterPressed(true);
					velY -= 55;
					timeWhenBoosterWasTriggered = System.currentTimeMillis();
					readyToUseBooster = false;
					Game.playSound(BoosterSound, true);
				}
			}
		}
	}

	public void render(Graphics g) {
		
		
		/*
		g.setColor(Color.blue);
		Graphics2D g2d = (Graphics2D) g;
		//g.fillRect((int)x,(int) y, 32, 64);
		g2d.draw(getBounds());
		g.setColor(Color.RED);
		*/
		
		Graphics2D g2d = (Graphics2D) g;
		
		//Normal form
		if(canBeAttacked){
			if(jumping && facingRight){
				PlayerJumpRight.drawAnimations(g,(int) x - 5, (int) y);
			}
			else if(jumping && facingLeft){
				PlayerJumpLeft.drawAnimations(g,(int) x - 5, (int) y);
			}
			else if(!jumping){
				if(velX > 0){
					PlayerWalkLeft.drawAnimations(g,(int) x - 5, (int) y);
				}
				if(velX < 0){
					PlayerWalkRight.drawAnimations(g,(int) x - 5, (int) y);
				}	
				if(velX == 0 && facingRight){
					PlayerStandRight.drawAnimations(g,(int) x - 5, (int) y);
				}	
				else if(velX == 0 && facingLeft){
					PlayerStandLeft.drawAnimations(g,(int) x - 5, (int) y);
				}	
			}
		}
		
		//immune to dmg -- just attacked form
		if(!canBeAttacked){
			if(jumping && facingRight){
				PlayerJumpRightFlash.drawAnimations(g,(int) x - 5, (int) y);
			}
			else if(jumping && facingLeft){
				PlayerJumpLeftFlash.drawAnimations(g,(int) x - 5, (int) y);
			}
			else if(!jumping){
				if(velX > 0){
					PlayerWalkLeftFlash.drawAnimations(g,(int) x - 5, (int) y);
				}
				if(velX < 0){
					PlayerWalkRightFlash.drawAnimations(g,(int) x - 5, (int) y);
				}	
				if(velX == 0 && facingRight){
					PlayerStandRightFlash.drawAnimations(g,(int) x - 5, (int) y);
				}	
				else if(velX == 0 && facingLeft){
					PlayerStandLeftFlash.drawAnimations(g,(int) x - 5, (int) y);
				}	
			}
		}
		
		//Health interface
		if(health == 5){
			g.drawImage(fullHearth, (int) x - 545, (int) 10, 32, 32, null);
			g.drawImage(fullHearth, (int) x - 545 + 40, (int) 10, 32, 32, null);
			g.drawImage(fullHearth, (int) x - 545+ 80, (int) 10, 32, 32, null);
			g.drawImage(fullHearth, (int) x - 545+ 120, (int) 10, 32, 32, null);
			g.drawImage(fullHearth, (int) x - 545+ 160, (int) 10, 32, 32, null);
		}
		if(health == 4){
			g.drawImage(fullHearth, (int) x - 545, (int) 10, 32, 32, null);
			g.drawImage(fullHearth, (int) x - 545 + 40, (int) 10, 32, 32, null);
			g.drawImage(fullHearth, (int) x - 545+ 80, (int) 10, 32, 32, null);
			g.drawImage(fullHearth, (int) x - 545+ 120, (int) 10, 32, 32, null);
			g.drawImage(emptyHearth, (int) x - 545+ 160, (int) 10, 32, 32, null);
		}
		if(health == 3){
			g.drawImage(fullHearth, (int) x - 545, (int) 10, 32, 32, null);
			g.drawImage(fullHearth, (int) x - 545 + 40, (int) 10, 32, 32, null);
			g.drawImage(fullHearth, (int) x - 545+ 80, (int) 10, 32, 32, null);
			g.drawImage(emptyHearth, (int) x - 545+ 120, (int) 10, 32, 32, null);
			g.drawImage(emptyHearth, (int) x - 545+ 160, (int) 10, 32, 32, null);
		}
		if(health == 2){
			g.drawImage(fullHearth, (int) x - 545, (int) 10, 32, 32, null);
			g.drawImage(fullHearth, (int) x - 545 + 40, (int) 10, 32, 32, null);
			g.drawImage(emptyHearth, (int) x - 545+ 80, (int) 10, 32, 32, null);
			g.drawImage(emptyHearth, (int) x - 545+ 120, (int) 10, 32, 32, null);
			g.drawImage(emptyHearth, (int) x - 545+ 160, (int) 10, 32, 32, null);
		}
		if(health == 1){
			g.drawImage(fullHearth, (int) x - 545, (int) 10, 32, 32, null);
			g.drawImage(emptyHearth, (int) x - 545 + 40, (int) 10, 32, 32, null);
			g.drawImage(emptyHearth, (int) x - 545+ 80, (int) 10, 32, 32, null);
			g.drawImage(emptyHearth, (int) x - 545+ 120, (int) 10, 32, 32, null);
			g.drawImage(emptyHearth, (int) x - 545+ 160, (int) 10, 32, 32, null);
		}
		if(health == 0){
			g.drawImage(emptyHearth, (int) x - 545, (int) 10, 32, 32, null);
			g.drawImage(emptyHearth, (int) x - 545 + 40, (int) 10, 32, 32, null);
			g.drawImage(emptyHearth, (int) x - 545+ 80, (int) 10, 32, 32, null);
			g.drawImage(emptyHearth, (int) x - 545+ 120, (int) 10, 32, 32, null);
			g.drawImage(emptyHearth, (int) x - 545+ 160, (int) 10, 32, 32, null);
		}
		
		//coins interface
		coinSpin.drawAnimations(g, (int) x + 620, (int) - 20, 100, 100);
		g.setColor(Color.YELLOW);
		Font fnt0 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 35);
		g2d.setFont(fnt0);
		if(Game.state == STATE.LEVELONE){
			if(gotGreenKey){
				g.drawImage(greenKey, (int) x - 545, (int) 50, 54, 59, null);
			}
			g.drawString(COINSCOLLECTED + "/" + coinsAtLevelOne  , (int) x + 550, (int) 46 );
		}
		if(Game.state == STATE.LEVELTWO){
			g.drawString(COINSCOLLECTED + "/" + coinsAtLevelTwo, (int) x + 550, (int) 46 );
			if(gotBlueKey){
				g.drawImage(blueKey, (int) x - 545, (int) 50, 54, 59, null);
			}
		}	
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 55, 97);	
	}
	
	public Rectangle getBoundsTop(){
		return new Rectangle((int) x + 8, (int) y, 55 - 16, 8);
		
	}
	public Rectangle getBoundsBottom(){
		return new Rectangle((int) x + 8, (int) y + 93, 55 - 16 , 4);
		
	}
	public Rectangle getBoundsLeft(){
		return new Rectangle((int) x  , (int) y + 8, 8, 82);	
		
	}
	public Rectangle getBoundsRight(){
		return new Rectangle((int) x + 47, (int) y + 8, 8, 82);	
	}
}