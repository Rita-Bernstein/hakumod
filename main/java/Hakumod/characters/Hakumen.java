package Hakumod.characters;

import Hakumod.cards.Hakumen.normal.Blocking;
import Hakumod.cards.Hakumen.normal.Strike;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.HakuEnum;
import Hakumod.relics.Susanoo;
import Hakumod.utils.Config;
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
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class Hakumen extends CustomPlayer {

	private static final CharacterStrings charStrings = CardCrawlGame.languagePack.getCharacterString("Hakumen");
	public static final String TITLE = charStrings.NAMES[0];
	public static final String FLAVOR = charStrings.TEXT[0];

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
		AnimationState.TrackEntry e = this.state.setAnimation(0, HAKUMEN_ANIMATION, true);
		e.setTime(e.getEndTime() * MathUtils.random());
	    e.setTimeScale(0.9F);
	}

	//Originally start with a max of 0 orb, then changed to 2 permanently once 1 is channeled.
	@Override
	public void increaseMaxOrbSlots(int arg0, boolean arg1) {
		int increaseBy = arg0;
		if (arg0 == 1 && this.maxOrbs==0 && arg1) {increaseBy = MAX_ORBS_COMBAT; this.masterMaxOrbs = MAX_ORBS_COMBAT;}
		super.increaseMaxOrbSlots(increaseBy, arg1);
	}

	public ArrayList<String> getStartingDeck() {
		ArrayList<String> retVal = new ArrayList<>();
		retVal.add(Strike.ID);
		retVal.add(Strike.ID);
		retVal.add(Strike.ID);
		retVal.add(Strike.ID);
		retVal.add(Blocking.ID);
		retVal.add(Blocking.ID);
		retVal.add(Blocking.ID);
		retVal.add(Blocking.ID);
		
		retVal.add(Config.LIST_STARTING_CARDS[this.startingCards][0]);
		retVal.add(Config.LIST_STARTING_CARDS[this.startingCards][1]);
		
		return retVal;
	}
	
	public ArrayList<String> getStartingRelics() {
		ArrayList<String> retVal = new ArrayList<>();
		retVal.add(Susanoo.RELIC_ID);
		UnlockTracker.markRelicAsSeen(Susanoo.RELIC_ID);
		return retVal;
	}
	
	public CharSelectInfo getLoadout() {
		return new CharSelectInfo(TITLE, FLAVOR, STARTING_HP, MAX_HP, MAX_ORBS, STARTING_GOLD, HAND_SIZE,
								  this, getStartingRelics(), getStartingDeck(), false);
	}

	@Override
	public void doCharSelectScreenSelectEffect() {
		CardCrawlGame.sound.playA("ATTACK_HEAVY", MathUtils.random(-0.2f, 0.2f));
	}

	@Override
	public int getAscensionMaxHPLoss() {
		return MAX_HP_LOSS;
	}


	@Override
	public Color getCardTrailColor() {
		return Color.BLACK.cpy();
	}

	@Override
	public String getCustomModeCharacterButtonSoundKey() {
		return "ATTACK_MAGIC_BEAM_SHORT";
	}

	@Override
	public BitmapFont getEnergyNumFont() {
		 return FontHelper.energyNumFontBlue;
	}

	@Override
	public String getLocalizedCharacterName() {
		return TITLE;
	}

	@Override
	public AbstractCard getStartCardForEvent() {
		AbstractCard startCard = Config.CARDS_COPY[this.startingCards];
		return startCard;
	}

	@Override
	public String getTitle(PlayerClass arg0) {
		return TITLE;
	}

	@Override
	public AbstractPlayer newInstance() {
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
		return AbstractCardEnum.HAKUMEN_COLOR;
	}

	@Override
	public Color getCardRenderColor() {
		return Color.SKY.cpy();
	}

	@Override
	public Color getSlashAttackColor() {
		return Color.CLEAR.cpy();
	}

	@Override
	public AttackEffect[] getSpireHeartSlashEffect() {
		AttackEffect[] effect = {AttackEffect.BLUNT_HEAVY, AttackEffect.SMASH};
		return effect;
	}

	@Override
	public String getSpireHeartText() {
		return charStrings.TEXT[1];
	}

	@Override
	public String getVampireText() {
		return charStrings.TEXT[2];
	}
}