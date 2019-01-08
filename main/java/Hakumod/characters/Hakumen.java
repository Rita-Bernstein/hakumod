package Hakumod.characters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import Hakumod.HakuInit;
import Hakumod.cards.Hakumen.Haku_2A;
import Hakumod.cards.Hakumen.Haku_3C;
import Hakumod.cards.Hakumen.Haku_5B;
import Hakumod.cards.Hakumen.Haku_6B;
import Hakumod.cards.Hakumen.Haku_6C;
import Hakumod.cards.Hakumen.Haku_6D;
import Hakumod.cards.Hakumen.Haku_EA;
import Hakumod.cards.Hakumen.Haku_Enma;
import Hakumod.cards.Hakumen.Haku_JB;
import Hakumod.cards.Hakumen.Haku_JD;
import Hakumod.cards.Hakumen.Haku_Renka;
import Hakumod.cards.Hakumen.Haku_Zantetsu;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.HakuEnum;
import basemod.abstracts.CustomPlayer;

public class Hakumen extends CustomPlayer {
	//public static final Logger logger = LogManager.getLogger(HakuInit.class.getName());
	
    public static final int STARTING_HP = 76;
    public static final int MAX_HP = 76;
    public static final int STARTING_GOLD = 99;
    public static final int HAND_SIZE = 5;
    
	public static final int ENERGY_PER_TURN = 3; 
	public static final String HAKUMEN_MAIN = "Hakumod/img/char/main.png";
	public static final String HAKUMEN_SHOULDER_2 = "Hakumod/img/char/shoulder2.png"; 
    public static final String HAKUMEN_SHOULDER_1 = "Hakumod/img/char/shoulder1.png"; 
	public static final String HAKUMEN_CORPSE = "Hakumod/img/char/fallen.png"; 
	
    public static final String HAKUMEN_SKELETON_ATLAS = "Hakumod/img/char/HakuAnim.atlas";// Marisa_v0 / MarisaModel_v02
    public static final String HAKUMEN_SKELETON_JSON = "Hakumod/img/char/HakuAnim.json";
    public static final String HAKUMEN_ANIMATION = "Sprite";// Sprite / Idle
    
    public final String[][] STARTING_CARDS = 
    	{
    			{"Haku_Guren", "Haku_3C"},
    			{"Haku_6A", "Haku_6B"},
    			{"Haku_2B", "Haku_Renka"},
    			{"Haku_5C", "Haku_Zantetsu"},
    			{"Haku_Kishuu", "Haku_Enma"},
    			{"Haku_2D", "Haku_JD"},
    			{"Haku_2A", "Haku_2A"},
    			{"Haku_EA", "Haku_ActiveFlow"},
    			{"Haku_6C", "Haku_FC"},
    	};
    
    public final AbstractCard[] CARDS_COPY = 
    	{
    			new Haku_3C().makeCopy(),
    			new Haku_6B().makeCopy(),
    			new Haku_Renka().makeCopy(),
    			new Haku_Zantetsu().makeCopy(),
    			new Haku_Enma().makeCopy(),
    			new Haku_2A().makeCopy(),
    			new Haku_JD().makeCopy(),
    			new Haku_EA().makeCopy(),
    			new Haku_6C().makeCopy()
    	};	
    
	public static final String[] orbTextures = {
			"Hakumod/img/orb/layer1.png",
			"Hakumod/img/orb/layer2.png",
			"Hakumod/img/orb/layer3.png",
			"Hakumod/img/orb/layer4.png",
			"Hakumod/img/orb/layer5.png",
			"Hakumod/img/orb/layer6.png",
			"Hakumod/img/orb/layer1d.png",
			"Hakumod/img/orb/layer2d.png",
			"Hakumod/img/orb/layer3d.png",
			"Hakumod/img/orb/layer4d.png",
			"Hakumod/img/orb/layer5d.png",
	};
	
	public static final float[] layerSpeeds = {
			80.0F, 40.0F, -40.0F, 20.0F, 0.0F,
			16.0F, 8.0F, -8.0F, 5.0F, 0.0F,
	};

	private int startingCards;
    //public boolean hasPlayedAnAttack;
	public Hakumen (String name, int startingCards) {
		

		//public CustomPlayer(String name, PlayerClass playerClass, String[] orbTextures, String orbVfxPath, float[] layerSpeeds, AbstractAnimation animation)
		super(name, HakuEnum.HAKUMEN, orbTextures, "Hakumod/img/orb/vfx.png", layerSpeeds, null, null);
		//super(name, HakuEnum.HAKUMEN, null, name, name, name)
		this.masterMaxOrbs = 2;
		this.maxOrbs = 2;
		this.startingCards = startingCards;
		
		this.dialogX = (this.drawX + 20.0F * Settings.scale); // set location for text bubbles
		this.dialogY = (this.drawY + 240.0F * Settings.scale); // you can just copy these values
		
		//logger.info("Hakumod: Init character class");
		
		initializeClass(null,
						HAKUMEN_SHOULDER_2, 
						HAKUMEN_SHOULDER_1,
						HAKUMEN_CORPSE, 
						getLoadout(),
						20.0F, -10.0F, 220.0F, 290.0F,
						new EnergyManager(ENERGY_PER_TURN)
						);
		
		loadAnimation(HAKUMEN_SKELETON_ATLAS, HAKUMEN_SKELETON_JSON, 1.0F); 
		// if you're using modified versions of base game animations or made animations in spine make sure to include this bit and the following lines
		AnimationState.TrackEntry e = this.state.setAnimation(0, HAKUMEN_ANIMATION, true);
		e.setTime(e.getEndTime() * MathUtils.random());
	    e.setTimeScale(0.9F);
	}

	public ArrayList<String> getStartingDeck() {
		ArrayList<String> retVal = new ArrayList<>();
		retVal.add("Haku_4C");
		retVal.add("Haku_4C");
		retVal.add("Haku_4C");
		retVal.add("Haku_4C");
		retVal.add("Haku_Blocking");
		retVal.add("Haku_Blocking");
		retVal.add("Haku_Blocking");
		retVal.add("Haku_Blocking");
		
		retVal.add(STARTING_CARDS[this.startingCards][0]);
		retVal.add(STARTING_CARDS[this.startingCards][1]);
		
		return retVal;
	}
	
	public ArrayList<String> getStartingRelics() {
		ArrayList<String> retVal = new ArrayList<>();
		retVal.add("Haku_Susanoo");
		UnlockTracker.markRelicAsSeen("Haku_Susanoo");
		return retVal;
	}
	


	public CharSelectInfo getLoadout() {
		String stringTitle;
		String stringFlavor;

		stringTitle = "The White Void";
		stringFlavor = "One of the \"Six Heroes\" who defeated the Black Beast. NL What could his goal possibly be?";
		
		return new CharSelectInfo(stringTitle, stringFlavor,
								  STARTING_HP, MAX_HP, 0, STARTING_GOLD, HAND_SIZE,
								  this, getStartingRelics(), getStartingDeck(), false);
	}

	@Override
	public void doCharSelectScreenSelectEffect() {
		// TODO Auto-generated method stub
		CardCrawlGame.sound.playA("ATTACK_HEAVY", MathUtils.random(-0.2f, 0.2f));
		//CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, true);
		
	}

	@Override
	public int getAscensionMaxHPLoss() {
		// TODO Auto-generated method stub
		return 4;
	}


	@Override
	public Color getCardTrailColor() {
		// TODO Auto-generated method stub
		return Color.BLACK;
	}

	@Override
	public String getCustomModeCharacterButtonSoundKey() {
		// TODO Auto-generated method stub
		return "ATTACK_MAGIC_BEAM_SHORT";
	}

	@Override
	public BitmapFont getEnergyNumFont() {
		// TODO Auto-generated method stub
		 return FontHelper.energyNumFontBlue;
	}

	@Override
	public String getLocalizedCharacterName() {
		// TODO Auto-generated method stub
		return "Hakumen";
	}

	@Override
	public AbstractCard getStartCardForEvent() {
		// TODO Auto-generated method stub
		AbstractCard startCard = CARDS_COPY[this.startingCards];
		return startCard;
	}

	@Override
	public String getTitle(PlayerClass arg0) {
		// TODO Auto-generated method stub
		return "The White Void";
	}

	@Override
	public AbstractPlayer newInstance() {
		// TODO Auto-generated method stub
		int startingCards = 1;
		
		try {
	        final Properties defaults = new Properties();
	        defaults.setProperty("starting-cards", "1");
	        final SpireConfig config = new SpireConfig("HakuMod", "Common", defaults);
	        startingCards = config.getInt("starting-cards");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		AbstractPlayer haku = new Hakumen(this.name, startingCards);
		return haku;
	}

	@Override
	public CardColor getCardColor() {
		// TODO Auto-generated method stub
		return AbstractCardEnum.HAKUMEN_COLOR;
	}

	@Override
	public Color getCardRenderColor() {
		// TODO Auto-generated method stub
		return Color.SKY;
	}

	@Override
	public Color getSlashAttackColor() {
		// TODO Auto-generated method stub
		return Color.CLEAR;
	}

	@Override
	public AttackEffect[] getSpireHeartSlashEffect() {
		// TODO Auto-generated method stub
		AttackEffect[] effect = {AttackEffect.BLUNT_HEAVY, AttackEffect.SMASH}; 
		return effect;
	}

	@Override
	public String getSpireHeartText() {
		// TODO Auto-generated method stub
		return "You ready your blade.";
	}

	@Override
	public String getVampireText() {
		// TODO Auto-generated method stub
		return "A group of Rachel players approach you, they want to boast about being S tier and having access to an easy IOH. You can either join them to get a minor buff in the next patch or remind them that they no longer have access to pumpkin to make them go away.";
	}
	

	
}