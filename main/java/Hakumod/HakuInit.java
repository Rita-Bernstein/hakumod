package Hakumod;

import Hakumod.characters.Hakumen;
import Hakumod.monsters.Nu13;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.HakuEnum;
import Hakumod.powers.player.MagatamaPower;
import Hakumod.utils.*;
import basemod.BaseMod;
import basemod.ModLabel;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

@SpireInitializer
public class HakuInit implements PostExhaustSubscriber,
PostBattleSubscriber, PostDungeonInitializeSubscriber,
EditCharactersSubscriber, PostInitializeSubscriber, 
EditRelicsSubscriber, EditCardsSubscriber, EditStringsSubscriber, OnStartBattleSubscriber,
OnCardUseSubscriber, EditKeywordsSubscriber, OnPowersModifiedSubscriber, PostDrawSubscriber {

	private static int startingCards = 0;
	private static boolean useBosses = false;

	private static Properties DEFAULTS_CONFIG = new Properties();

	private ArrayList<ModLabeledToggleButton> toggleCardSelection = new ArrayList<ModLabeledToggleButton >();

	public static int getStartingCards() {
		return startingCards;
	}

	public static void setStartingCards(int startingCards) {
		HakuInit.startingCards = startingCards;
	}

	public static boolean getUseBosses() {
		return useBosses;
	}

	public static void setUseBosses(boolean useBosses) {
		HakuInit.useBosses = useBosses;
	}

	public HakuInit() {
	  BaseMod.subscribe(this);
	  BaseMod.addColor(AbstractCardEnum.HAKUMEN_COLOR,
				ModColor.getColor(), ModColor.getColor(), ModColor.getColor(), ModColor.getColor(), ModColor.getColor(), ModColor.getColor(), ModColor.getColor(),
				AssetsPath.makePath(AssetsPath.ATTACK_CC), AssetsPath.makePath(AssetsPath.SKILL_CC),
				AssetsPath.makePath(AssetsPath.POWER_CC), AssetsPath.makePath(AssetsPath.ENERGY_ORB_CC),
				AssetsPath.makePath(AssetsPath.ATTACK_CC_PORTRAIT), AssetsPath.makePath(AssetsPath.SKILL_CC_PORTRAIT),
				AssetsPath.makePath(AssetsPath.POWER_CC_PORTRAIT), AssetsPath.makePath(AssetsPath.ENERGY_ORB_CC_PORTRAIT));
	}

	public void receiveEditCharacters() {
		BaseMod.addCharacter(new Hakumen(CardCrawlGame.playerName, startingCards),
				AssetsPath.makePath(AssetsPath.MY_CHARACTER_BUTTON),
				AssetsPath.makePath(AssetsPath.HAKUMEN_PORTRAIT),
				HakuEnum.HAKUMEN);
	}

	public void receiveEditRelics() {
		AddRelics.addRelics();
	}

	public void receiveEditCards() {
		AddCards.addCards();
		}

	public void receiveEditPotions() {
		 AddPotions.addPotions();
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

	public void receivePostExhaust(AbstractCard c) {}

	public void receivePostBattle(AbstractRoom r) {}

	public void receivePostDungeonInitialize() {}

	public void receivePostDraw(AbstractCard arg0) {}

	@Override
	public void receiveEditKeywords() {
		AddKeywords.addKeywords();
	}

	public void receivePowersModified() {}

	public void receiveCardUsed(AbstractCard card) {}

	@Override
	public void receiveEditStrings() {
		EditStrings.editStrings();
	}

	public void receivePostInitialize() {
		ModPanel settingsPanel = new ModPanel();

		final ModLabel labelStartingCards = new ModLabel(CardCrawlGame.languagePack.getUIString(AssetsPath.makeID("ConfigTitle")).TEXT[0], Config.LABEL_START_X, Config.LABEL_START_Y, settingsPanel, (me) -> {});
		settingsPanel.addUIElement(labelStartingCards);

		for (int i=0;i<Config.LIST_STARTING_CARDS.length;i++) {
			final int index = i;
			toggleCardSelection.add(new ModLabeledToggleButton(Config.CARDS_DESC[i], Config.CARDS_TOGGLE_START_X, Config.CARDS_TOGGLE_START_Y + i*Config.CARDS_SPACE_STEP, Settings.CREAM_COLOR, FontHelper.charDescFont, useBosses, settingsPanel, label -> {}, button -> {
				try {
					SpireConfig config = new SpireConfig(ModDescription.MODNAME, "Common", DEFAULTS_CONFIG);
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

		updateCardsToggle(getStartingCards());

		final ModLabeledToggleButton toggleMusic = new ModLabeledToggleButton(Config.TOGGLE_BOSSES_LABEL, Config.BOSSES_TOGGLE_START_X, Config.BOSSES_TOGGLE_START_Y , Settings.CREAM_COLOR, FontHelper.charDescFont, useBosses, settingsPanel, label -> {}, button -> {
			try {
				SpireConfig config = new SpireConfig(ModDescription.MODNAME, "Common", DEFAULTS_CONFIG);
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

		Texture badgeTexture = new Texture(Gdx.files.internal(AssetsPath.makePath("HakuModBadge.png")));
		BaseMod.registerModBadge(badgeTexture, ModDescription.MODNAME, ModDescription.AUTHOR, ModDescription.DESCRIPTION, settingsPanel);

		receiveEditPotions()
		;
		receiveEditMonsters();
	}

	public void updateCardsToggle(int index) {
		for (int i=0;i<Config.CARDS_DESC.length;i++) {
			if (index != i) {
				toggleCardSelection.get(i).toggle.enabled = false;
			}
		}
	}

	public void receiveOnBattleStart(AbstractRoom arg0) {
		if (AbstractDungeon.player instanceof Hakumen) {
			AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
				new MagatamaPower(AbstractDungeon.player, 1)));
		}
	}
}
