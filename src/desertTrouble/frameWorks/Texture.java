package desertTrouble.frameWorks;

import java.awt.image.BufferedImage;

import desertTrouble.Window.BufferedImageLoader;


public class Texture {

	private SpriteSheet gs, gs2,pwls, prss, plss, pwlfs, prsfs,plsfs,fs,ss,flags;
	
	private BufferedImage Ground_sheet = null;
	private BufferedImage Ground_sheet2 = null;
	private BufferedImage PlayerWalkLeft_sheet = null;
	private BufferedImage PlayerWalkLeftFlash_sheet = null;
	private BufferedImage PlayerRightSprite_sheet = null;
	private BufferedImage PlayerRightSpriteFlash_sheet = null;
	private BufferedImage PlayerLeftSprite_sheet = null;
	private BufferedImage PlayerLeftSpriteFlash_sheet = null;
	private BufferedImage Fly_sheet = null;
	private BufferedImage Flag_sheet = null;
	private BufferedImage Slime_sheet = null;

	public BufferedImage[] Ground = new BufferedImage[29];
	public BufferedImage[] Ground2 = new BufferedImage[12];
	public BufferedImage[] PlayerWalkLeft = new BufferedImage[22];
	public BufferedImage[] PlayerWalkLeftFlash = new BufferedImage[22];
	public BufferedImage[] PlayerRightSprite = new BufferedImage[3];
	public BufferedImage[] PlayerRightSpriteFlash = new BufferedImage[3];
	public BufferedImage[] PlayerLeftSprite = new BufferedImage[3];
	public BufferedImage[] PlayerLeftSpriteFlash = new BufferedImage[3];
	public BufferedImage[] Fly = new BufferedImage[6];
	public BufferedImage[] Flag = new BufferedImage[4];
	public BufferedImage[] Slime = new BufferedImage[6];

	
	public Texture(){
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try{
			Ground_sheet  = loader.loadImage("/Ground_sheet.png");
			Ground_sheet2 = loader.loadImage("/Ground_sheet2.png");
			PlayerWalkLeft_sheet = loader.loadImage("/PlayerWalkLeft_sheet.png");
			PlayerWalkLeftFlash_sheet = loader.loadImage("/PlayerWalkLeftFlash_sheet.png");
			PlayerRightSprite_sheet = loader.loadImage("/PlayerRightSpriteSheet.png");
			PlayerRightSpriteFlash_sheet = loader.loadImage("/PlayerRightSpriteSheetFlash.png");
			PlayerLeftSprite_sheet = loader.loadImage("/PlayerLeftSpriteSheet.png");
			PlayerLeftSpriteFlash_sheet = loader.loadImage("/PlayerLeftSpriteSheetFlash.png");
			Fly_sheet = loader.loadImage("/Fly_sheet.png");
			Flag_sheet = loader.loadImage("/Flag_sheet.png");
			Slime_sheet = loader.loadImage("/Slime_sheet.png");
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		gs = new SpriteSheet(Ground_sheet);
		pwls = new SpriteSheet(PlayerWalkLeft_sheet);
		pwlfs = new SpriteSheet(PlayerWalkLeftFlash_sheet);
		prss = new SpriteSheet(PlayerRightSprite_sheet);
		prsfs = new SpriteSheet(PlayerRightSpriteFlash_sheet);
		plss = new SpriteSheet(PlayerLeftSprite_sheet);
		plsfs = new SpriteSheet(PlayerLeftSpriteFlash_sheet);
		gs2 = new SpriteSheet(Ground_sheet2);
		fs = new SpriteSheet(Fly_sheet);
		flags = new SpriteSheet(Flag_sheet);
		ss = new SpriteSheet(Slime_sheet);
		
		getTextures();
	}
	private void getTextures(){
		
		Ground[0] = gs.grabImage(1, 1, 70, 70); //sand
		Ground[1] = gs.grabImage(2, 1, 70, 70); //sand center
		Ground[2] = gs.grabImage(3, 1, 70, 70); //sand center rounded
		Ground[3] = gs.grabImage(4, 1, 70, 70); //sandCliffLeft
		Ground[4] = gs.grabImage(5, 1, 70, 70); //sandCliffLeftAlt
		Ground[5] = gs.grabImage(6, 1, 70, 70); //sandCliffRight
		Ground[6] = gs.grabImage(7, 1, 70, 70); //sandCliffRightAlt
		Ground[7] = gs.grabImage(8, 1, 70, 70); //sandHalf
		Ground[8] = gs.grabImage(9, 1, 70, 70); //sandHalfLeft
		Ground[9] = gs.grabImage(10, 1, 70, 70); //sandHalfMid
		Ground[10] = gs.grabImage(11, 1, 70, 70); //sandHalfRight
		Ground[11] = gs.grabImage(12, 1, 70, 70); //sandHillLeft
		Ground[12] = gs.grabImage(13, 1, 70, 70); //sandHillLeft2
		Ground[13] = gs.grabImage(14, 1, 70, 70); //SandHillRight
		Ground[14] = gs.grabImage(15, 1, 70, 70); //SandHillRight2
		Ground[15] = gs.grabImage(16, 1, 70, 70); //sandLeft
		Ground[16] = gs.grabImage(17, 1, 70, 70); //sandMid
		Ground[17] = gs.grabImage(18, 1, 70, 70); //sandRight
		Ground[18] = gs.grabImage(19, 1, 20, 16); //sandLedgleLeft
		Ground[19] = gs.grabImage(20, 1, 70, 70); //sandLedgeRight
		
		Ground2[0] = gs2.grabImage(1, 1, 128, 128); //sand mid
		Ground2[1] = gs2.grabImage(2, 1, 128, 128); //sand center
		Ground2[2] = gs2.grabImage(3, 1, 128, 128); //bottom left
		Ground2[3] = gs2.grabImage(4, 1, 128, 128); //sand right
		Ground2[4] = gs2.grabImage(5, 1, 128, 128); //bottom right
		Ground2[5] = gs2.grabImage(6, 1, 128, 128); //sand left
		Ground2[6] = gs2.grabImage(7, 1, 128, 128); //sand bottom
		Ground2[7] = gs2.grabImage(8, 1, 128, 128); //sand small right
		Ground2[8] = gs2.grabImage(9, 1, 128, 128); //sand small mid
		Ground2[9] = gs2.grabImage(10, 1, 128, 128); //sand small left
		Ground2[10] = gs2.grabImage(11, 1, 128, 128); //sandLeftSide
		Ground2[11] = gs2.grabImage(12, 1, 128, 128); //sandRightSide
		
		
		
		//coin
		Ground[20] = gs.grabImage(1, 2, 70, 70);
		//GreenButton not pressed
		Ground[21] = gs.grabImage(2, 2, 70, 70);
		//GreenButton pressed
		Ground[22] = gs.grabImage(3, 2, 70, 70);
		//Spikes right
		Ground[23] = gs.grabImage(4, 2, 70, 70);
		//Spikes left
		Ground[24] = gs.grabImage(5, 2, 70, 70);
		//BlueButton not pressed
		Ground[25] = gs.grabImage(6, 2, 70, 70);
		//BlueButton pressed
		Ground[26] = gs.grabImage(7, 2, 70, 70);
		//Booster notPressed
		Ground[27] = gs.grabImage(8, 2, 70, 70);
		//Booster pressed
		Ground[28] = gs.grabImage(9, 2, 70, 70);
		
		//walk left
		PlayerWalkLeft[0] = pwls.grabImage(1, 1, 72, 97);
		PlayerWalkLeft[1] = pwls.grabImage(2, 1, 72, 97);
		PlayerWalkLeft[2] = pwls.grabImage(3, 1, 72, 97);
		PlayerWalkLeft[3] = pwls.grabImage(4, 1, 72, 97);
		PlayerWalkLeft[4] = pwls.grabImage(5, 1, 72, 97);
		PlayerWalkLeft[5] = pwls.grabImage(6, 1, 72, 97);
		PlayerWalkLeft[6] = pwls.grabImage(7, 1, 72, 97);
		PlayerWalkLeft[7] = pwls.grabImage(8, 1, 72, 97);
		PlayerWalkLeft[8] = pwls.grabImage(9, 1, 72, 97);
		PlayerWalkLeft[9] = pwls.grabImage(10, 1, 72, 97);
		PlayerWalkLeft[10] = pwls.grabImage(11, 1, 72, 97);
		//walk right
		PlayerWalkLeft[11] = pwls.grabImage(1, 2, 72, 97);
		PlayerWalkLeft[12] = pwls.grabImage(2, 2, 72, 97);
		PlayerWalkLeft[13] = pwls.grabImage(3, 2, 72, 97);
		PlayerWalkLeft[14] = pwls.grabImage(4, 2, 72, 97);
		PlayerWalkLeft[15] = pwls.grabImage(5, 2, 72, 97);
		PlayerWalkLeft[16] = pwls.grabImage(6, 2, 72, 97);
		PlayerWalkLeft[17] = pwls.grabImage(7, 2, 72, 97);
		PlayerWalkLeft[18] = pwls.grabImage(8, 2, 72, 97);
		PlayerWalkLeft[19] = pwls.grabImage(9, 2, 72, 97);
		PlayerWalkLeft[20] = pwls.grabImage(10, 2, 72, 97);
		PlayerWalkLeft[21] = pwls.grabImage(11, 2, 72, 97);
		
		//Player right
		//jump
		PlayerRightSprite[0] = prss.grabImage(1, 1, 67, 94);
		//Player right 
		//stand 
		PlayerRightSprite[1] = prss.grabImage(2, 1, 67, 94);
		//Player right 
		//duck
		PlayerRightSprite[2] = prss.grabImage(3, 1, 67, 94);
		
		
		//Player left
		//duck
		PlayerLeftSprite[0] = plss.grabImage(1, 1, 67, 94);
		//Player left 
		//stand
		PlayerLeftSprite[1] = plss.grabImage(2, 1, 67, 94);
		//Player left 
		//jump
		PlayerLeftSprite[2] = plss.grabImage(3, 1, 67, 94);
		
		/**
		 * FLASHING PLAYER AFTER TAKING DMG
		 */
		
		//walk left	
		PlayerWalkLeftFlash[0] = pwlfs.grabImage(1, 1, 72, 97);
		PlayerWalkLeftFlash[1] = pwlfs.grabImage(2, 1, 72, 97);
		PlayerWalkLeftFlash[2] = pwlfs.grabImage(3, 1, 72, 97);
		PlayerWalkLeftFlash[3] = pwlfs.grabImage(4, 1, 72, 97);
		PlayerWalkLeftFlash[4] = pwlfs.grabImage(5, 1, 72, 97);
		PlayerWalkLeftFlash[5] = pwlfs.grabImage(6, 1, 72, 97);
		PlayerWalkLeftFlash[6] = pwlfs.grabImage(7, 1, 72, 97);
		PlayerWalkLeftFlash[7] = pwlfs.grabImage(8, 1, 72, 97);
		PlayerWalkLeftFlash[8] = pwlfs.grabImage(9, 1, 72, 97);
		PlayerWalkLeftFlash[9] = pwlfs.grabImage(10, 1, 72, 97);
		PlayerWalkLeftFlash[10] = pwlfs.grabImage(11, 1, 72, 97);
		//walk right
		PlayerWalkLeftFlash[11] = pwlfs.grabImage(1, 2, 72, 97);
		PlayerWalkLeftFlash[12] = pwlfs.grabImage(2, 2, 72, 97);
		PlayerWalkLeftFlash[13] = pwlfs.grabImage(3, 2, 72, 97);
		PlayerWalkLeftFlash[14] = pwlfs.grabImage(4, 2, 72, 97);
		PlayerWalkLeftFlash[15] = pwlfs.grabImage(5, 2, 72, 97);
		PlayerWalkLeftFlash[16] = pwlfs.grabImage(6, 2, 72, 97);
		PlayerWalkLeftFlash[17] = pwlfs.grabImage(7, 2, 72, 97);
		PlayerWalkLeftFlash[18] = pwlfs.grabImage(8, 2, 72, 97);
		PlayerWalkLeftFlash[19] = pwlfs.grabImage(9, 2, 72, 97);
		PlayerWalkLeftFlash[20] = pwlfs.grabImage(10, 2, 72, 97);
		PlayerWalkLeftFlash[21] = pwlfs.grabImage(11, 2, 72, 97);
		
		//Player right
		//jump
		PlayerRightSpriteFlash[0] = prsfs.grabImage(1, 1, 67, 94);
		//Player right 
		//stand 
		PlayerRightSpriteFlash[1] = prsfs.grabImage(2, 1, 67, 94);
		//Player right 
		//duck
		PlayerRightSpriteFlash[2] = prsfs.grabImage(3, 1, 67, 94);
		
		
		//Player left
		//duck
		PlayerLeftSpriteFlash[0] = plsfs.grabImage(1, 1, 67, 94);
		//Player left 
		//stand
		PlayerLeftSpriteFlash[1] = plsfs.grabImage(2, 1, 67, 94);
		//Player left 
		//jump
		PlayerLeftSpriteFlash[2] = plsfs.grabImage(3, 1, 67, 94);
		
		
		//flyLeftWalk
		Fly[0] = fs.grabImage(1, 1, 72, 36);
		Fly[1] = fs.grabImage(2, 1, 72, 36);
		
		//flyRightWalk
		Fly[2] = fs.grabImage(1, 2, 72, 36);
		Fly[3] = fs.grabImage(2, 2, 72, 36);
		
		//flyLeftDeath
		Fly[4] = fs.grabImage(1, 3, 72, 36);
		
		//flyRightDeath
		Fly[5] = fs.grabImage(2, 3, 72, 36);
		
		
		//SlimeLeftWalk
		Slime[0] = ss.grabImage(1, 1, 50, 32);
		Slime[1] = ss.grabImage(2, 1, 50, 32);
		
		//SlimeRightWalk
		Slime[4] = ss.grabImage(1, 2, 52, 32);
		Slime[5] = ss.grabImage(2, 2, 52, 32);
		
		//SlimeLeftDeath
		Slime[2] = ss.grabImage(1, 3, 52, 32);
		
		//SlimeRightDeath
		Slime[3] = ss.grabImage(3, 3, 52, 32);
		
		//FlagSheet
		Flag[0] = flags.grabImage(1, 1, 70, 148);
		Flag[1] = flags.grabImage(2, 1, 70, 148);
		Flag[2] = flags.grabImage(3, 1, 70, 148);
		Flag[3] = flags.grabImage(4, 1, 70, 148);
	}
}