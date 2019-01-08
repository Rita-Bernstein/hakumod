package Hakumod;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;
import com.brashmonkey.spriter.Mainline.Key;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.RestartForChangesEffect;

import basemod.BaseMod;
import basemod.DevConsole;
import basemod.ModButton;
import basemod.ModLabel;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.abstracts.CustomPlayer;
import basemod.interfaces.OnStartBattleSubscriber;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.OnCardUseSubscriber;
import basemod.interfaces.OnPowersModifiedSubscriber;
import basemod.interfaces.PostBattleSubscriber;
import basemod.interfaces.PostDrawSubscriber;
import basemod.interfaces.PostDungeonInitializeSubscriber;
import basemod.interfaces.PostExhaustSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import Hakumod.cards.Hakumen.*;
import Hakumod.characters.Hakumen;

import Hakumod.patches.HakuEnum;
import Hakumod.patches.LibraryTypeEnum;
import Hakumod.patches.CustomTags;
import Hakumod.patches.AbstractCardEnum;

import Hakumod.powers.Haku_MagatamaPower;
import Hakumod.relics.*;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;

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

private InputProcessor oldInputProcessor;
private static int startingCards = 0; 
private static boolean useMusic = false;

final String MODNAME = "HakuMod";
final String AUTHOR = "The_undercover_beret";
final String DESCRIPTION = "Add Haku-men as a playable character.";

final String[] CARDS = 
{	
	"Guren / 3C (recommended)", 
	"6A / 6B", 
	"2B / Renka", 
	"5C / Zantetsu", 
	"Kishuu / Enma",
	"2D / J.D",
	"2A / 2A",
	"Exceed Accel / Active Flow",
	"6C / Fatal Counter"
};

final float LABEL_START_X = 350.0f;
final float LABEL_START_Y = 750.0f;

final float CARDS_TOGGLE_START_X = 400.0f;
final float CARDS_TOGGLE_START_Y = 700.0f;
final float CARDS_SPACE_STEP = -40.0f;

final float MUSIC_TOGGLE_START_X = 350.0f;
final float MUSIC_TOGGLE_START_Y = 300.0f;
final String TOGGLE_MUSIC_LABEL = "Enable custom boss music (Requires a restart to take effect, may be incompatible with other mods.)";


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
public static boolean getUseMusic() {
	return useMusic;
}

/**
 * @param useMusic the useMusic to set
 */
public static void setUseMusic(boolean useMusic) {
	HakuInit.useMusic = useMusic;
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
	UnlockTracker.unlockCard("Haku_4C");
	BaseMod.addCard(new Haku_Blocking());
	UnlockTracker.unlockCard("Haku_Blocking");
	BaseMod.addCard(new Haku_Guren());
	UnlockTracker.unlockCard("Haku_Guren");
	BaseMod.addCard(new Haku_3C());
	UnlockTracker.unlockCard("Haku_3C");
	
	BaseMod.addCard(new Haku_2B());
	UnlockTracker.unlockCard("Haku_2B");
	BaseMod.addCard(new Haku_5C());
	UnlockTracker.unlockCard("Haku_5C");
	BaseMod.addCard(new Haku_6A());
	UnlockTracker.unlockCard("Haku_6A");
	BaseMod.addCard(new Haku_6B());
	UnlockTracker.unlockCard("Haku_6B");
	BaseMod.addCard(new Haku_Enma());
	UnlockTracker.unlockCard("Haku_Enma");
	BaseMod.addCard(new Haku_Renka());
	UnlockTracker.unlockCard("Haku_Renka");
	BaseMod.addCard(new Haku_Zantetsu());
	UnlockTracker.unlockCard("Haku_Zantetsu");
	BaseMod.addCard(new Haku_Shippu());
	UnlockTracker.unlockCard("Haku_Shippu");
	
	BaseMod.addCard(new Haku_5D());
	UnlockTracker.unlockCard("Haku_5D");
	BaseMod.addCard(new Haku_2D());
	UnlockTracker.unlockCard("Haku_2D");
	BaseMod.addCard(new Haku_6D());
	UnlockTracker.unlockCard("Haku_6D");
	BaseMod.addCard(new Haku_JD());
	UnlockTracker.unlockCard("Haku_JD");
	BaseMod.addCard(new Haku_Yanagi());
	UnlockTracker.unlockCard("Haku_Yanagi");
	BaseMod.addCard(new Haku_Yukikaze());
	UnlockTracker.unlockCard("Haku_Yukikaze");
	
	BaseMod.addCard(new Haku_ActiveFlow());
	UnlockTracker.unlockCard("Haku_ActiveFlow");
	BaseMod.addCard(new Haku_Overdrive());
	UnlockTracker.unlockCard("Haku_Overdrive");
	BaseMod.addCard(new Haku_Burst());
	UnlockTracker.unlockCard("Haku_Burst");
	
	BaseMod.addCard(new Haku_5A());
	UnlockTracker.unlockCard("Haku_5A");
	BaseMod.addCard(new Haku_5B());
	UnlockTracker.unlockCard("Haku_5B");
	BaseMod.addCard(new Haku_2A());
	UnlockTracker.unlockCard("Haku_2A");
	BaseMod.addCard(new Haku_2C());
	UnlockTracker.unlockCard("Haku_2C");
	//BaseMod.addCard(new Haku_Staircase());
	//UnlockTracker.unlockCard("Haku_Staircase");
	BaseMod.addCard(new Haku_JC());
	UnlockTracker.unlockCard("Haku_JC");
	BaseMod.addCard(new Haku_Agito());
	UnlockTracker.unlockCard("Haku_Agito");
	
	BaseMod.addCard(new Haku_Barrier());
	UnlockTracker.unlockCard("Haku_Barrier");
	BaseMod.addCard(new Haku_IB());
	UnlockTracker.unlockCard("Haku_IB");
	BaseMod.addCard(new Haku_OS());
	UnlockTracker.unlockCard("Haku_OS");
	BaseMod.addCard(new Haku_Fuzzy());
	UnlockTracker.unlockCard("Haku_Fuzzy");
	
	BaseMod.addCard(new Haku_Kishuu());
	UnlockTracker.unlockCard("Haku_Kishuu");
	BaseMod.addCard(new Haku_CT());
	UnlockTracker.unlockCard("Haku_CT");
	
	BaseMod.addCard(new Haku_JA());
	UnlockTracker.unlockCard("Haku_JA");
	BaseMod.addCard(new Haku_JB());
	UnlockTracker.unlockCard("Haku_JB");
	BaseMod.addCard(new Haku_J2C());
	UnlockTracker.unlockCard("Haku_J2A");
	BaseMod.addCard(new Haku_J2A());
	UnlockTracker.unlockCard("Haku_J2C");
	BaseMod.addCard(new Haku_Hotaru());
	UnlockTracker.unlockCard("Haku_Hotaru");
	BaseMod.addCard(new Haku_Tsubaki());
	UnlockTracker.unlockCard("Haku_Tsubaki");
	BaseMod.addCard(new Haku_Mugen());
	UnlockTracker.unlockCard("Haku_Mugen");
	
	BaseMod.addCard(new Haku_CA());
	UnlockTracker.unlockCard("Haku_CA");
	BaseMod.addCard(new Haku_RC());
	UnlockTracker.unlockCard("Haku_RC");
	BaseMod.addCard(new Haku_Taunt());
	UnlockTracker.unlockCard("Haku_Taunt");
	
	BaseMod.addCard(new Haku_PassiveMeter());
	UnlockTracker.unlockCard("Haku_PassiveMeter");
	BaseMod.addCard(new Haku_EA());
	UnlockTracker.unlockCard("Haku_EA");
	BaseMod.addCard(new Haku_6C());
	UnlockTracker.unlockCard("Haku_6C");
	

	BaseMod.addCard(new Haku_Awakening());
	UnlockTracker.unlockCard("Haku_Awakening");
	BaseMod.addCard(new Haku_ScienceFiction());
	UnlockTracker.unlockCard("Haku_ScienceFiction");
	BaseMod.addCard(new Haku_Yomotsuhirasaka());
	UnlockTracker.unlockCard("Haku_Yomotsuhirasaka");
	BaseMod.addCard(new Haku_EmptySky());
	UnlockTracker.unlockCard("Haku_EmptySky");
	BaseMod.addCard(new Haku_TheTyrant());
	UnlockTracker.unlockCard("Haku_TheTyrant");
	
	BaseMod.addCard(new Haku_Akumetsu());
	UnlockTracker.unlockCard("Haku_Akumetsu");
	
	BaseMod.addCard(new Haku_HighJump());
	UnlockTracker.unlockCard("Haku_HighJump");
	BaseMod.addCard(new Haku_Grab());
	UnlockTracker.unlockCard("Haku_Grab");
	BaseMod.addCard(new Haku_Airgrab());
	UnlockTracker.unlockCard("Haku_Airgrab");
	BaseMod.addCard(new Haku_Airdash());
	UnlockTracker.unlockCard("Haku_Airdash");
	
	BaseMod.addCard(new Haku_GCOD());
	UnlockTracker.unlockCard("Haku_GCOD");
	BaseMod.addCard(new Haku_ODC());
	UnlockTracker.unlockCard("Haku_ODC");
	BaseMod.addCard(new Haku_InstantBarrier());
	UnlockTracker.unlockCard("Haku_InstantBarrier");
	//BaseMod.addCard(new Haku_Round2());
	//UnlockTracker.unlockCard("Haku_Round2");
	
	BaseMod.addCard(new Haku_Void());
	UnlockTracker.unlockCard("Haku_Void");
	/*BaseMod.addCard(new Haku_Backgrab());
	UnlockTracker.unlockCard("Haku_Backgrab");
	BaseMod.addCard(new Haku_Pinkgrab());
	UnlockTracker.unlockCard("Haku_Pinkgrab");*/
	
	BaseMod.addCard(new Haku_Backhop());
	UnlockTracker.unlockCard("Haku_Backhop");
	BaseMod.addCard(new Haku_Forwardhop());
	UnlockTracker.unlockCard("Haku_Forwardhop");
	
	/*BaseMod.addCard(new Haku_Clash());
	UnlockTracker.unlockCard("Haku_Clash");
	BaseMod.addCard(new Haku_Cut());
	UnlockTracker.unlockCard("Haku_Cut");*/
	BaseMod.addCard(new Haku_Walpurgisnacht());
	UnlockTracker.unlockCard("Haku_Walpurgisnacht");
	
	BaseMod.addCard(new Haku_FC());
	UnlockTracker.unlockCard("Haku_FC");
	BaseMod.addCard(new Haku_FatalJudge());
	UnlockTracker.unlockCard("Haku_FatalJudge");
	
	BaseMod.addCard(new Haku_Timekiller());
	UnlockTracker.unlockCard("Haku_Timekiller");
	BaseMod.addCard(new Haku_WhiteVoid());
	UnlockTracker.unlockCard("Haku_WhiteVoid");
	BaseMod.addCard(new Haku_ChildishMemories());
	UnlockTracker.unlockCard("Haku_ChildishMemories");
	BaseMod.addCard(new Haku_SixHeroes());
	UnlockTracker.unlockCard("Haku_SixHeroes");
	BaseMod.addCard(new Haku_Spellbook());
	UnlockTracker.unlockCard("Haku_Spellbook");
	BaseMod.addCard(new Haku_GodOfWar());
	UnlockTracker.unlockCard("Haku_GodOfWar");
	BaseMod.addCard(new Haku_Gate());
	UnlockTracker.unlockCard("Haku_Gate");
	BaseMod.addCard(new Haku_InJustice());
	UnlockTracker.unlockCard("Haku_InJustice");
	
	BaseMod.addCard(new Haku_BlackAndWhite());
	UnlockTracker.unlockCard("Haku_BlackAndWhite");
	BaseMod.addCard(new Haku_SwordOfDoom());
	UnlockTracker.unlockCard("Haku_SwordOfDoom");
	
	/*BaseMod.addCard(new Haku_NegativePenality());
	UnlockTracker.unlockCard("Haku_NegativePenality");
	BaseMod.addCard(new Haku_Timeout());
	UnlockTracker.unlockCard("Haku_Timeout");*/
	
}

public static void HakuConfig() {
	try {
        final Properties defaults = new Properties();
        defaults.setProperty("starting-cards", "0");
        defaults.setProperty("use-music", "false");
        final SpireConfig config = new SpireConfig("HakuMod", "Common", defaults);
        setStartingCards(config.getInt("starting-cards"));
        setUseMusic(config.getBool("use-music"));
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
	
	
	String stringKeyMagatama = "Energy used for special moves.";
	String stringKeySpecial = "#ySpecials consume #yMagatama(s) to activate their effect.";
	String stringKeyGatling = "- If you have that card in your hand, reduce its cost by 1 this turn. NL - If you don't, add that card into your hand but lose 2 #yStrengths until the end of the turn."; 
	String stringKeyStarter = "Apply the effect if your energy is at its maximum.";
	String stringKeyEnder = "Apply the effect if either: NL - You have 0 energy after playing that card. NL - You have 0 #yAttacks left in your hand.";
	String stringKeyParry = "Apply the effect if the enemy intends to attack.";
	String stringKeyNegate = "Apply the effect if the enemy intends to inflict a debuff.";
	
	String stringKeyDefense = "Creatures with Defense receive 15% less damage from #yAttacks for #b1 turn. NL Remove it if #yOffense is applied.";
	String stringKeyOffense = "Creatures with Offense deal 25% more damage with #yAttacks #b1 turn. NL Remove it if #yDefense is applied.";
	String stringKeyNeutral = "Creatures with Neutral gain 25% more blocks.";
	String stringKeyOverdrive = "Double the amount of #yMagatama gained for #1 turn.";
	String stringKeyActiveFlow = "Creatures with #yActive #yFlow deal 10% more damage with #yAttacks for #b1 turn. Add one #yOverdrive into your hand once you run out of #yActive #yFlow.";
	

	String stringKey6C = "1: Reduce this card's cost by 1. NL 2: Draw 1 card. NL 3: Gain 2 #yStrengths. NL 4: Upgrade all the cards in your hand.";
	String stringKeySpeech = "1: Gain 1 #yDexterity. NL 2: Gain 2 #yPlated #yArmor. NL 3: Gain 1 #yStrength. NL 4: Gain 1 #yThorns. NL 5: All enemies lose 1 #yStrength. NL 6: Draw 1 more card at the start of each turn. NL 7: Gain 1 additional [R] at the start of each turn. NL 8: #y???";
	
	String stringKeyFuumajin = "Gain 2 Blocks at the end of each turn. #yEvoke after 2 turns. NL #yEvoke: Gain 1 #ymagatama.";
	
	String stringKeyStaircase = "Attack - Cost 0 NL Deal 1 damage 4 times. NL Add 1 J.C or Agito to your hand, it costs 0 this turn. NL #yExhaust";
	String stringKey3C = "Attack - Cost 1 NL Deal 8 damage. NL Ender: Inflict 1 #yWeak.";
	String stringKey6B = "Attack - Cost 1 NL Deal 4 damage twice. NL Ender: Inflict 2 #yVulnerable.";
	String stringKeyEnma = "Attack - Cost 1 NL #ySpecial: 1 NL Deal 7 damage. NL Gain 7 #yBlock.";
	String stringKeyRenka = "Attack - Cost 1 NL #ySpecial: 2 NL Deal 7 damage two times. NL Draw 1 card.";
	String stringKeyZantetsu = "Attack - Cost 1 NL #ySpecial: 3 NL Deal 18 damage. NL Gain 1 #yEnergy."; 		
	
	BaseMod.addKeyword(new String[] {"Magatama", "magatama"}, stringKeyMagatama);
	BaseMod.addKeyword(new String[] {"Special", "special"}, stringKeySpecial);
	BaseMod.addKeyword(new String[] {"Gatling", "gatling"}, stringKeyGatling);
	
	BaseMod.addKeyword(new String[] {"Starter","starter"}, stringKeyStarter);
	BaseMod.addKeyword(new String[] {"Ender", "ender"}, stringKeyEnder);
	
	BaseMod.addKeyword(new String[] {"Parry", "parry"}, stringKeyParry);
	BaseMod.addKeyword(new String[] {"Negate", "negate"}, stringKeyNegate);
	
	BaseMod.addKeyword(new String[] {"Defense", "defense"}, stringKeyDefense);
	BaseMod.addKeyword(new String[] {"Offense", "offense"}, stringKeyOffense);
	BaseMod.addKeyword(new String[] {"Neutral", "neutral"}, stringKeyNeutral);
	BaseMod.addKeyword(new String[] {"Overdrive", "overdrive", "OD", "od"}, stringKeyOverdrive);
	BaseMod.addKeyword(new String[] {"AF", "af"}, stringKeyActiveFlow);
	//BaseMod.addKeyword(new String[] {"g.special","g.specials"}, stringKeyGroundSpecials);
	//BaseMod.addKeyword(new String[] {"a.attack","a.attacks"}, stringKeyAirAttacks);
	//BaseMod.addKeyword(new String[] {"Ground","ground"}, stringKeyGroundSpecials);
	//BaseMod.addKeyword(new String[] {"Air","air"}, stringKeyAirAttacks);
	BaseMod.addKeyword(new String[] {"Fuumajin","fuumajin"}, stringKeyFuumajin);
	BaseMod.addKeyword(new String[] {"Staircase","staircase"}, stringKeyStaircase);
	
	BaseMod.addKeyword(new String[] {"3C","3c"}, stringKey3C);
	BaseMod.addKeyword(new String[] {"6B","6b"}, stringKey6B);
	BaseMod.addKeyword(new String[] {"Enma","enma"}, stringKeyEnma);
	BaseMod.addKeyword(new String[] {"Renka","renka"}, stringKeyRenka);
	BaseMod.addKeyword(new String[] {"Zantetsu","zantetsu"}, stringKeyZantetsu);
	
	BaseMod.addKeyword(new String[] {"6c","6C"}, stringKey6C);
	BaseMod.addKeyword(new String[] {"Speech","speech"}, stringKeySpeech);
};
/*
public boolean hasPlayedAnAttack() {
	return hasPlayedAnAttack;
}

public void setHasPlayedAnAttack(boolean hasPlayedAnAttack) {
	this.hasPlayedAnAttack = hasPlayedAnAttack;
}
*/
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
	
	String relicStrings,cardStrings,powerStrings;
    	
    relicStrings = Gdx.files.internal("Hakumod/localization/Hakumod_Relics.json").readString(String.valueOf(StandardCharsets.UTF_8));
    BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
        
    cardStrings = Gdx.files.internal("Hakumod/localization/Hakumod_Cards.json").readString(String.valueOf(StandardCharsets.UTF_8));
    BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
        
    powerStrings = Gdx.files.internal("Hakumod/localization/Hakumod_Powers.json").readString(String.valueOf(StandardCharsets.UTF_8));
    BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);

}

@Override
public void receivePostInitialize() {
	// TODO Auto-generated method stub
	
	ModPanel settingsPanel = new ModPanel();

	final ModLabel labelStartingCards = new ModLabel("Select your 2 starting cards:", LABEL_START_X, LABEL_START_Y, settingsPanel, (me) -> {});
	settingsPanel.addUIElement(labelStartingCards);
    
    for (int i=0;i<CARDS.length;i++) {
    	final int index = i;
    	toggleCardSelection.add(new ModLabeledToggleButton(CARDS[i], CARDS_TOGGLE_START_X, CARDS_TOGGLE_START_Y + i*CARDS_SPACE_STEP, Settings.CREAM_COLOR, FontHelper.charDescFont, useMusic, settingsPanel, label -> {}, button -> {
    		try {
    			SpireConfig config = new SpireConfig(MODNAME, "Common");
    			setStartingCards(index);
    			config.setInt("starting-cards", index);
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
    
    final ModLabeledToggleButton toggleMusic = new ModLabeledToggleButton(TOGGLE_MUSIC_LABEL, MUSIC_TOGGLE_START_X, MUSIC_TOGGLE_START_Y , Settings.CREAM_COLOR, FontHelper.charDescFont, useMusic, settingsPanel, label -> {}, button -> {
		try {
			SpireConfig config = new SpireConfig(MODNAME, "Common");
			setUseMusic(button.enabled);
			config.setBool("use-music", button.enabled);
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
	
}

public void updateCardsToggle(int index) {
	for (int i=0;i<CARDS.length;i++) {
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
