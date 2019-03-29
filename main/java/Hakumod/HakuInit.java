package Hakumod;

import Hakumod.cards.Hakumen.*;
import Hakumod.characters.Hakumen;
import Hakumod.monsters.Nu13;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.HakuEnum;
import Hakumod.potions.Haku_FullPowerPotion;
import Hakumod.potions.Haku_HalfPowerPotion;
import Hakumod.potions.Haku_QuarterPowerPotion;
import Hakumod.powers.Haku_MagatamaPower;
import Hakumod.relics.*;
import basemod.BaseMod;
import basemod.ModLabel;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Properties;

//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
//import com.megacrit.cardcrawl.core.Settings;

@SpireInitializer
public class HakuInit implements PostExhaustSubscriber,
PostBattleSubscriber, PostDungeonInitializeSubscriber,
EditCharactersSubscriber, PostInitializeSubscriber, 
EditRelicsSubscriber, EditCardsSubscriber, EditStringsSubscriber, OnStartBattleSubscriber,
OnCardUseSubscriber, EditKeywordsSubscriber, OnPowersModifiedSubscriber, PostDrawSubscriber {

public static final Logger logger = LogManager.getLogger(HakuInit.class.getName());
 
private static final String ASSETS_FOLDER = "Hakumod/img";
private static final String ATTACK_CC = "512/Haku_bg_attack.png";
private static final String SKILL_CC = "512/Haku_bg_skill.png";
private static final String POWER_CC = "512/Haku_bg_power.png";
private static final String ENERGY_ORB_CC = "512/card_orb.png";
private static final String ATTACK_CC_PORTRAIT = "1024/Haku_bg_attack.png";
private static final String SKILL_CC_PORTRAIT = "1024/Haku_bg_skill.png";
private static final String POWER_CC_PORTRAIT = "1024/Haku_bg_power.png";
private static final String ENERGY_ORB_CC_PORTRAIT = "1024/card_orb.png";

private static final Color WHITE = CardHelper.getColor(125.0f, 125.05f, 125.0f);
private static final String MY_CHARACTER_BUTTON = "charSelect/HakuButton.png";
private static final String HAKUMEN_PORTRAIT = "charSelect/HakuPortraitBG.png";

private static int startingCards = 0; 
private static boolean useBosses = false;

final String MODNAME = "HakuMod";
final String AUTHOR = "The_undercover_beret";
final String DESCRIPTION = "Adds Haku-men as a playable character.";

private static Properties DEFAULTS_CONFIG = new Properties();

private ArrayList<ModLabeledToggleButton> toggleCardSelection = new ArrayList<ModLabeledToggleButton >();


public static int getStartingCards() {
	return startingCards;
}

public static void setStartingCards(int startingCards) {
	HakuInit.startingCards = startingCards;
}

/**
 * @return the useMusic
 */
public static boolean getUseBosses() {
	return useBosses;
}

public static void setUseBosses(boolean useBosses) {
	HakuInit.useBosses = useBosses;
}

//private boolean hasPlayedAnAttack = false;
/**
* Makes a full path for a resource path
* @param resource the resource, must *NOT* have a leading "/"
* @return the full path
*/
public static final String makePath(String resource) {
  return ASSETS_FOLDER + "/" + resource;
	}

public HakuInit() {
  BaseMod.subscribe(this);
  //BaseMod.subscribeToEditCharacters(this);
  BaseMod.addColor(AbstractCardEnum.HAKUMEN_COLOR,
		  	WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE,
    	    makePath(ATTACK_CC), makePath(SKILL_CC),
    	    makePath(POWER_CC), makePath(ENERGY_ORB_CC),
    	    makePath(ATTACK_CC_PORTRAIT), makePath(SKILL_CC_PORTRAIT),
    	    makePath(POWER_CC_PORTRAIT), makePath(ENERGY_ORB_CC_PORTRAIT));
}

public void receiveEditCharacters() {
	logger.info("Hakumod: Adding character");
    /*BaseMod.addCharacter(new Hakumen("Hakumen"), "The White Void", "character class string",
    		AbstractCardEnum.HAKUMEN_COLOR, "The White Void",
    		MY_CHARACTER_BUTTON , HAKUMEN_PORTRAIT,
    		HakuEnum.HAKUMEN);
    */
    BaseMod.addCharacter(new Hakumen(CardCrawlGame.playerName, startingCards),
    		makePath(MY_CHARACTER_BUTTON),
    		makePath(HAKUMEN_PORTRAIT),
    		HakuEnum.HAKUMEN);
}

public void receiveEditRelics() {
	logger.info("Hakumod: Adding relics.");
	
	BaseMod.addRelicToCustomPool(new Haku_Susanoo(), AbstractCardEnum.HAKUMEN_COLOR);
	BaseMod.addRelicToCustomPool(new Haku_Susanoo2(), AbstractCardEnum.HAKUMEN_COLOR);
	//BaseMod.addRelicToCustomPool(new BlackAndWhite(), AbstractCardEnum.HAKUMEN_COLOR);
	//BaseMod.addRelicToCustomPool(new SixHeroes(), AbstractCardEnum.HAKUMEN_COLOR);
	//BaseMod.addRelicToCustomPool(new InJustice(), AbstractCardEnum.HAKUMEN_COLOR);
	BaseMod.addRelicToCustomPool(new Haku_BurstIcon(), AbstractCardEnum.HAKUMEN_COLOR);
	//BaseMod.addRelicToCustomPool(new Haku_Roundstart(), AbstractCardEnum.HAKUMEN_COLOR);
	//BaseMod.addRelicToCustomPool(new Timer(), AbstractCardEnum.HAKUMEN_COLOR);
	BaseMod.addRelicToCustomPool(new Haku_CalamityTrigger(), AbstractCardEnum.HAKUMEN_COLOR);
	BaseMod.addRelicToCustomPool(new Haku_ContinuumShift(), AbstractCardEnum.HAKUMEN_COLOR);
	BaseMod.addRelicToCustomPool(new Haku_ChronoPhantasma(), AbstractCardEnum.HAKUMEN_COLOR);
	BaseMod.addRelicToCustomPool(new Haku_CentralFiction(), AbstractCardEnum.HAKUMEN_COLOR);

}

public void receiveEditCards() {
	logger.info("Hakumod: Adding cards.");

	//Starter:
	BaseMod.addCard(new Haku_4C());
	UnlockTracker.unlockCard(Haku_4C.ID);
	BaseMod.addCard(new Haku_Blocking());
	UnlockTracker.unlockCard(Haku_Blocking.ID);
	BaseMod.addCard(new Haku_Guren());
	UnlockTracker.unlockCard(Haku_Guren.ID);
	BaseMod.addCard(new Haku_3C());
	UnlockTracker.unlockCard(Haku_3C.ID);
	
	BaseMod.addCard(new Haku_2B());
	UnlockTracker.unlockCard(Haku_2B.ID);
	BaseMod.addCard(new Haku_5C());
	UnlockTracker.unlockCard(Haku_5C.ID);
	BaseMod.addCard(new Haku_6A());
	UnlockTracker.unlockCard(Haku_6A.ID);
	BaseMod.addCard(new Haku_6B());
	UnlockTracker.unlockCard(Haku_6B.ID);
	BaseMod.addCard(new Haku_Enma());
	UnlockTracker.unlockCard(Haku_Enma.ID);
	BaseMod.addCard(new Haku_Renka());
	UnlockTracker.unlockCard(Haku_Renka.ID);
	BaseMod.addCard(new Haku_Zantetsu());
	UnlockTracker.unlockCard(Haku_Zantetsu.ID);
	BaseMod.addCard(new Haku_Shippu());
	UnlockTracker.unlockCard(Haku_Shippu.ID);
	
	BaseMod.addCard(new Haku_5D());
	UnlockTracker.unlockCard(Haku_5D.ID);
	BaseMod.addCard(new Haku_2D());
	UnlockTracker.unlockCard(Haku_2D.ID);
	BaseMod.addCard(new Haku_6D());
	UnlockTracker.unlockCard(Haku_6D.ID);
	BaseMod.addCard(new Haku_JD());
	UnlockTracker.unlockCard(Haku_JD.ID);
	BaseMod.addCard(new Haku_Yanagi());
	UnlockTracker.unlockCard(Haku_Yanagi.ID);
	BaseMod.addCard(new Haku_Yukikaze());
	UnlockTracker.unlockCard(Haku_Yukikaze.ID);
	
	BaseMod.addCard(new Haku_ActiveFlow());
	UnlockTracker.unlockCard(Haku_ActiveFlow.ID);
	BaseMod.addCard(new Haku_Overdrive());
	UnlockTracker.unlockCard(Haku_Overdrive.ID);
	/*BaseMod.addCard(new Haku_Burst());
	UnlockTracker.unlockCard(Haku_Burst.ID);*/
	
	BaseMod.addCard(new Haku_5A());
	UnlockTracker.unlockCard(Haku_5A.ID);
	BaseMod.addCard(new Haku_5B());
	UnlockTracker.unlockCard(Haku_5B.ID);
	BaseMod.addCard(new Haku_2A());
	UnlockTracker.unlockCard(Haku_2A.ID);
	BaseMod.addCard(new Haku_2C());
	UnlockTracker.unlockCard(Haku_2C.ID);
	//BaseMod.addCard(new Haku_Staircase());
	//UnlockTracker.unlockCard("Haku_Staircase");
	BaseMod.addCard(new Haku_JC());
	UnlockTracker.unlockCard(Haku_JC.ID);
	BaseMod.addCard(new Haku_Agito());
	UnlockTracker.unlockCard(Haku_Agito.ID);
	
	BaseMod.addCard(new Haku_Barrier());
	UnlockTracker.unlockCard(Haku_Barrier.ID);
	BaseMod.addCard(new Haku_IB());
	UnlockTracker.unlockCard(Haku_IB.ID);
	BaseMod.addCard(new Haku_OS());
	UnlockTracker.unlockCard(Haku_OS.ID);
	BaseMod.addCard(new Haku_Fuzzy());
	UnlockTracker.unlockCard(Haku_Fuzzy.ID);
	
	BaseMod.addCard(new Haku_Kishuu());
	UnlockTracker.unlockCard(Haku_Kishuu.ID);
	BaseMod.addCard(new Haku_CT());
	UnlockTracker.unlockCard(Haku_CT.ID);
	
	BaseMod.addCard(new Haku_JA());
	UnlockTracker.unlockCard(Haku_JA.ID);
	BaseMod.addCard(new Haku_JB());
	UnlockTracker.unlockCard(Haku_JB.ID);
	BaseMod.addCard(new Haku_J2C());
	UnlockTracker.unlockCard(Haku_J2A.ID);
	BaseMod.addCard(new Haku_J2A());
	UnlockTracker.unlockCard(Haku_J2C.ID);
	BaseMod.addCard(new Haku_Hotaru());
	UnlockTracker.unlockCard(Haku_Hotaru.ID);
	BaseMod.addCard(new Haku_Tsubaki());
	UnlockTracker.unlockCard(Haku_Tsubaki.ID);
	BaseMod.addCard(new Haku_Mugen());
	UnlockTracker.unlockCard(Haku_Mugen.ID);
	
	BaseMod.addCard(new Haku_CA());
	UnlockTracker.unlockCard(Haku_CA.ID);
	BaseMod.addCard(new Haku_RC());
	UnlockTracker.unlockCard(Haku_RC.ID);
	BaseMod.addCard(new Haku_Taunt());
	UnlockTracker.unlockCard(Haku_Taunt.ID);
	
	BaseMod.addCard(new Haku_PassiveMeter());
	UnlockTracker.unlockCard(Haku_PassiveMeter.ID);
	BaseMod.addCard(new Haku_EA());
	UnlockTracker.unlockCard(Haku_EA.ID);
	BaseMod.addCard(new Haku_6C());
	UnlockTracker.unlockCard(Haku_6C.ID);
	

	BaseMod.addCard(new Haku_Awakening());
	UnlockTracker.unlockCard(Haku_Awakening.ID);
	BaseMod.addCard(new Haku_ScienceFiction());
	UnlockTracker.unlockCard(Haku_ScienceFiction.ID);
	BaseMod.addCard(new Haku_Yomotsuhirasaka());
	UnlockTracker.unlockCard(Haku_Yomotsuhirasaka.ID);
	BaseMod.addCard(new Haku_EmptySky());
	UnlockTracker.unlockCard(Haku_EmptySky.ID);
	BaseMod.addCard(new Haku_TheTyrant());
	UnlockTracker.unlockCard(Haku_TheTyrant.ID);
	
	BaseMod.addCard(new Haku_Akumetsu());
	UnlockTracker.unlockCard(Haku_Akumetsu.ID);
	
	BaseMod.addCard(new Haku_HighJump());
	UnlockTracker.unlockCard(Haku_HighJump.ID);
	BaseMod.addCard(new Haku_Grab());
	UnlockTracker.unlockCard(Haku_Grab.ID);
	BaseMod.addCard(new Haku_Airgrab());
	UnlockTracker.unlockCard(Haku_Airgrab.ID);
	BaseMod.addCard(new Haku_Airdash());
	UnlockTracker.unlockCard(Haku_Airdash.ID);
	
	/*BaseMod.addCard(new Haku_GCOD());
	UnlockTracker.unlockCard(Haku_GCOD.ID);*/
	/*BaseMod.addCard(new Haku_ODC());
	UnlockTracker.unlockCard(Haku_ODC.ID);*/
	//BaseMod.addCard(new Haku_InstantBarrier());
	//UnlockTracker.unlockCard(Haku_InstantBarrier.ID);
	//BaseMod.addCard(new Haku_Round2());
	//UnlockTracker.unlockCard("Haku_Round2");
	
	BaseMod.addCard(new Haku_Void());
	UnlockTracker.unlockCard(Haku_Void.ID);
	/*BaseMod.addCard(new Haku_Backgrab());
	UnlockTracker.unlockCard("Haku_Backgrab");
	BaseMod.addCard(new Haku_Pinkgrab());
	UnlockTracker.unlockCard("Haku_Pinkgrab");*/
	
	/*BaseMod.addCard(new Haku_Backhop());
	UnlockTracker.unlockCard(Haku_Backhop.ID);*/
	/*BaseMod.addCard(new Haku_Forwardhop());
	UnlockTracker.unlockCard(Haku_Forwardhop.ID);*/
	
	/*BaseMod.addCard(new Haku_Clash());
	UnlockTracker.unlockCard("Haku_Clash");
	BaseMod.addCard(new Haku_Cut());
	UnlockTracker.unlockCard("Haku_Cut");*/
	BaseMod.addCard(new Haku_Walpurgisnacht());
	UnlockTracker.unlockCard(Haku_Walpurgisnacht.ID);
	
	BaseMod.addCard(new Haku_FC());
	UnlockTracker.unlockCard(Haku_FC.ID);
	BaseMod.addCard(new Haku_FatalJudge());
	UnlockTracker.unlockCard(Haku_FatalJudge.ID);
	
	BaseMod.addCard(new Haku_Timekiller());
	UnlockTracker.unlockCard(Haku_Timekiller.ID);
	BaseMod.addCard(new Haku_WhiteVoid());
	UnlockTracker.unlockCard(Haku_WhiteVoid.ID);
	BaseMod.addCard(new Haku_ChildishMemories());
	UnlockTracker.unlockCard(Haku_ChildishMemories.ID);
	BaseMod.addCard(new Haku_SixHeroes());
	UnlockTracker.unlockCard(Haku_SixHeroes.ID);
	BaseMod.addCard(new Haku_Spellbook());
	UnlockTracker.unlockCard(Haku_Spellbook.ID);
	BaseMod.addCard(new Haku_GodOfWar());
	UnlockTracker.unlockCard(Haku_GodOfWar.ID);
	BaseMod.addCard(new Haku_Gate());
	UnlockTracker.unlockCard(Haku_Gate.ID);
	BaseMod.addCard(new Haku_InJustice());
	UnlockTracker.unlockCard(Haku_InJustice.ID);
	
	BaseMod.addCard(new Haku_BlackAndWhite());
	UnlockTracker.unlockCard(Haku_BlackAndWhite.ID);
	BaseMod.addCard(new Haku_SwordOfDoom());
	UnlockTracker.unlockCard(Haku_SwordOfDoom.ID);
	
	BaseMod.addCard(new Haku_QueenOfRose());
	UnlockTracker.unlockCard(Haku_QueenOfRose.ID);

	BaseMod.addCard(new Haku_PandoraTears());
	UnlockTracker.unlockCard(Haku_PandoraTears.ID);
	BaseMod.addCard(new Haku_EndGazer());
	UnlockTracker.unlockCard(Haku_EndGazer.ID);
	BaseMod.addCard(new Haku_Thin());
	UnlockTracker.unlockCard(Haku_Thin.ID);
	BaseMod.addCard(new Haku_Xmatic());
	UnlockTracker.unlockCard(Haku_Xmatic.ID);

	BaseMod.addCard(new Haku_PlasticNight());
	UnlockTracker.unlockCard(Haku_PlasticNight.ID);

	}

public void receiveEditPotions() {
	 BaseMod.addPotion(Haku_QuarterPowerPotion.class, Color.BLUE.cpy(), Color.BLUE.cpy(), null, Haku_QuarterPowerPotion.POTION_ID, HakuEnum.HAKUMEN);
	 BaseMod.addPotion(Haku_HalfPowerPotion.class, Color.WHITE.cpy(), Color.WHITE.cpy(), null, Haku_HalfPowerPotion.POTION_ID, HakuEnum.HAKUMEN);
	 BaseMod.addPotion(Haku_FullPowerPotion.class, Color.RED.cpy(), Color.RED.cpy(), null, Haku_FullPowerPotion.POTION_ID, HakuEnum.HAKUMEN);
}

public void receiveEditMonsters() {

		if (HakuInit.getUseBosses()) {
			BaseMod.addMonster(Nu13.ID, () -> new Nu13(Nu13.OFFSET_X, Nu13.OFFSET_Y));
		
		    BaseMod.addBoss(Exordium.ID, Nu13.ID,
		            Nu13.MAP_IMAGE,
		            Nu13.MAP_OUT);    
		}
}

public static void HakuConfig() {
	try {

		DEFAULTS_CONFIG.setProperty(Config.STARTING_CARDS, "0");
		DEFAULTS_CONFIG.setProperty(Config.USE_BOSSES, "false");
        final SpireConfig config = new SpireConfig("HakuMod", "Common", DEFAULTS_CONFIG);
        setStartingCards(config.getInt(Config.STARTING_CARDS));
        setUseBosses(config.getBool(Config.USE_BOSSES));
    } catch (IOException e) {
        e.printStackTrace();
    }
}

public static void initialize() {
	HakuConfig();
	new HakuInit();
}

@Override
public void receivePostExhaust(AbstractCard c) {
	// TODO Auto-generated method stub
}

@Override
public void receivePostBattle(AbstractRoom r) {
	// TODO Auto-generated method stub
}


@Override
public void receivePostDungeonInitialize() {
	// TODO Auto-generated method stub
}

@Override
public void receivePostDraw(AbstractCard arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void receiveEditKeywords() {
	logger.info("Hakumod: Adding keywords.");
	
    Gson gson = new Gson();
    String json = Gdx.files.internal("Hakumod/localization/Hakumod_Keywords.json").readString(String.valueOf(StandardCharsets.UTF_8));
    Keyword[] keywords = gson.fromJson(json, Keyword[].class);

    if (keywords != null) {
        for (Keyword keyword : keywords) {
            BaseMod.addKeyword(keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
        }
}
    
}

	@Override
public void receivePowersModified() {
	// TODO Auto-generated method stub
	
}



@Override
public void receiveCardUsed(AbstractCard card) {
	/*if (card.type == AbstractCard.CardType.ATTACK) {
		hasPlayedAnAttack = true;
	}*/
}

@Override
public void receiveEditStrings() {
	// TODO Auto-generated method stublogger.info("begin editing strings");
	logger.info("Hakumod: Editing strings.");
	
	String relicStrings,cardStrings,powerStrings, potionStrings, monsterStrings, orbStrings;
  	
    relicStrings = Gdx.files.internal("Hakumod/localization/Hakumod_Relics.json").readString(String.valueOf(StandardCharsets.UTF_8));
    BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
        
    cardStrings = Gdx.files.internal("Hakumod/localization/Hakumod_Cards.json").readString(String.valueOf(StandardCharsets.UTF_8));
    BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
        
    powerStrings = Gdx.files.internal("Hakumod/localization/Hakumod_Powers.json").readString(String.valueOf(StandardCharsets.UTF_8));
    BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);

    potionStrings = Gdx.files.internal("Hakumod/localization/Hakumod_Potions.json").readString(String.valueOf(StandardCharsets.UTF_8));
    BaseMod.loadCustomStrings(PotionStrings.class, potionStrings);
    
    orbStrings = Gdx.files.internal("Hakumod/localization/Hakumod_Orbs.json").readString(String.valueOf(StandardCharsets.UTF_8));
    BaseMod.loadCustomStrings(OrbStrings.class, orbStrings);
    
    monsterStrings = Gdx.files.internal("Hakumod/localization/Hakumod_Monsters.json").readString(String.valueOf(StandardCharsets.UTF_8));
    BaseMod.loadCustomStrings(MonsterStrings.class, monsterStrings);
}

@Override
public void receivePostInitialize() {
	// TODO Auto-generated method stub
	
	ModPanel settingsPanel = new ModPanel();

	final ModLabel labelStartingCards = new ModLabel("Select your 2 starting cards:", Config.LABEL_START_X, Config.LABEL_START_Y, settingsPanel, (me) -> {});
	settingsPanel.addUIElement(labelStartingCards);
    
    for (int i=0;i<Config.LIST_STARTING_CARDS.length;i++) {
    	final int index = i;
    	toggleCardSelection.add(new ModLabeledToggleButton(Config.CARDS_DESC[i], Config.CARDS_TOGGLE_START_X, Config.CARDS_TOGGLE_START_Y + i*Config.CARDS_SPACE_STEP, Settings.CREAM_COLOR, FontHelper.charDescFont, useBosses, settingsPanel, label -> {}, button -> {
    		try {
    			SpireConfig config = new SpireConfig(MODNAME, "Common", DEFAULTS_CONFIG);
    			setStartingCards(index);
    			config.setInt(Config.STARTING_CARDS, index);
                config.save();
                updateCardsToggle(index);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return;
    	}));
    	settingsPanel.addUIElement(toggleCardSelection.get(i));
    }
    
    //Initially, load the previous saved configuration.
	updateCardsToggle(getStartingCards());
    
    final ModLabeledToggleButton toggleMusic = new ModLabeledToggleButton(Config.TOGGLE_BOSSES_LABEL, Config.BOSSES_TOGGLE_START_X, Config.BOSSES_TOGGLE_START_Y , Settings.CREAM_COLOR, FontHelper.charDescFont, useBosses, settingsPanel, label -> {}, button -> {
		try {
			SpireConfig config = new SpireConfig(MODNAME, "Common", DEFAULTS_CONFIG);
			setUseBosses(button.enabled);
			config.setBool(Config.USE_BOSSES, button.enabled);
			config.save();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return;
    });
    settingsPanel.addUIElement(toggleMusic);
	
	Texture badgeTexture = new Texture(Gdx.files.internal(makePath("HakuModBadge.png")));
	BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);
	
	receiveEditPotions();
	receiveEditMonsters();
}

public void updateCardsToggle(int index) {
	for (int i=0;i<Config.CARDS_DESC.length;i++) {
		if (index != i) {
			toggleCardSelection.get(i).toggle.enabled = false;
		}
	}
}

@Override
public void receiveOnBattleStart(AbstractRoom arg0) {
	// TODO Auto-generated method stub
	if (AbstractDungeon.player instanceof Hakumen) {
		AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, 
			new Haku_MagatamaPower(AbstractDungeon.player, 1)));
	}
}

}
