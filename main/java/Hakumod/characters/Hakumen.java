package Hakumod.characters;

import Hakumod.Config;
import Hakumod.cards.Hakumen.Haku_4C;
import Hakumod.cards.Hakumen.Haku_Blocking;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.HakuEnum;
import Hakumod.relics.Haku_Susanoo;
import basemod.abstracts.CustomPlayer;
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
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

public class Hakumen extends CustomPlayer {
	
	public static final String TITLE = "The White Void";
	public static final String FLAVOR = "One of the \"Six Heroes\" who defeated the Black Beast. NL What could his goal possibly be?";
	
    public static final int STARTING_HP = 76;
    public static final int MAX_HP = 76;
    public static final int MAX_HP_LOSS = 4;
    public static final int STARTING_GOLD = 99;
    public static final int HAND_SIZE = 5;
    public static final int MAX_ORBS = 0;
    public static final int MAX_ORBS_COMBAT = 2;

	public static final int ENERGY_PER_TURN = 3; 
	public static final String HAKUMEN_MAIN = "Hakumod/img/char/main.png";
	public static final String HAKUMEN_SHOULDER_2 = "Hakumod/img/char/shoulder2.png"; 
    public static final String HAKUMEN_SHOULDER_1 = "Hakumod/img/char/shoulder1.png"; 
	public static final String HAKUMEN_CORPSE = "Hakumod/img/char/fallen.png"; 
	
    public static final String HAKUMEN_SKELETON_ATLAS = "Hakumod/img/char/HakuAnim.atlas";
    public static final String HAKUMEN_SKELETON_JSON = "Hakumod/img/char/HakuAnim.json";
    public static final String HAKUMEN_ANIMATION = "Sprite";	
    
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
	
	public Hakumen (String name, int startingCards) {
		super(name, HakuEnum.HAKUMEN, orbTextures, "Hakumod/img/orb/vfx.png", layerSpeeds, null, null);
		this.startingCards = startingCards;
		
		this.dialogX = (this.drawX + 20.0F * Settings.scale); // set location for text bubbles
		this.dialogY = (this.drawY + 240.0F * Settings.scale); // you can just copy these values
		
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
	
	/* (non-Javadoc)
	 * @see com.megacrit.cardcrawl.characters.AbstractPlayer#increaseMaxOrbSlots(int, boolean)
	 */
	
	//Originally start with a max of 0 orb, then changed to 2 permanently once 1 is channeled.
	@Override
	public void increaseMaxOrbSlots(int arg0, boolean arg1) {
		// TODO Auto-generated method stub
		int increaseBy = arg0;
		if (arg0 == 1 && this.maxOrbs==0 && arg1) {increaseBy = MAX_ORBS_COMBAT; this.masterMaxOrbs = MAX_ORBS_COMBAT;}
		super.increaseMaxOrbSlots(increaseBy, arg1);
	}

	public ArrayList<String> getStartingDeck() {
		ArrayList<String> retVal = new ArrayList<>();
		retVal.add(Haku_4C.ID);
		retVal.add(Haku_4C.ID);
		retVal.add(Haku_4C.ID);
		retVal.add(Haku_4C.ID);
		retVal.add(Haku_Blocking.ID);
		retVal.add(Haku_Blocking.ID);
		retVal.add(Haku_Blocking.ID);
		retVal.add(Haku_Blocking.ID);
		
		retVal.add(Config.LIST_STARTING_CARDS[this.startingCards][0]);
		retVal.add(Config.LIST_STARTING_CARDS[this.startingCards][1]);
		
		return retVal;
	}
	
	public ArrayList<String> getStartingRelics() {
		ArrayList<String> retVal = new ArrayList<>();
		retVal.add(Haku_Susanoo.RELIC_ID);
		UnlockTracker.markRelicAsSeen(Haku_Susanoo.RELIC_ID);
		return retVal;
	}
	
	public CharSelectInfo getLoadout() {
		return new CharSelectInfo(TITLE, FLAVOR, STARTING_HP, MAX_HP, MAX_ORBS, STARTING_GOLD, HAND_SIZE,
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
		return MAX_HP_LOSS;
	}


	@Override
	public Color getCardTrailColor() {
		// TODO Auto-generated method stub
		return Color.BLACK.cpy();
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
		AbstractCard startCard = Config.CARDS_COPY[this.startingCards];
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
	        defaults.setProperty(Config.STARTING_CARDS, "0");
	        final SpireConfig config = new SpireConfig("HakuMod", "Common", defaults);
	        startingCards = config.getInt(Config.STARTING_CARDS);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		AbstractPlayer Haku = new Hakumen(this.name, startingCards);
		return Haku;
	}

	@Override
	public CardColor getCardColor() {
		// TODO Auto-generated method stub
		return AbstractCardEnum.HAKUMEN_COLOR;
	}

	@Override
	public Color getCardRenderColor() {
		// TODO Auto-generated method stub
		return Color.SKY.cpy();
	}

	@Override
	public Color getSlashAttackColor() {
		// TODO Auto-generated method stub
		return Color.CLEAR.cpy();
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