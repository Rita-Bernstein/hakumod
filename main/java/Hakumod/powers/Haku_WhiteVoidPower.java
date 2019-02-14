package Hakumod.powers;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.ShoutAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import com.megacrit.cardcrawl.powers.DrawPower;
import com.megacrit.cardcrawl.powers.BerserkPower;;

public class Haku_WhiteVoidPower extends AbstractPower {
	public static final String POWER_ID = "Haku_WhiteVoidPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	

	//private int[] arrayBonusThreshold = { 2, 4, 6, 8 };
	private String[] arrayBonus = 
		{ 	
				"Gain 1 Dexterity.",
				"Gain 2 Plated Armor.", 
				"Gain 1 Strength.",
				"Gain 1 Thorns.",
				"All enemies lose 2 Strengths.",
				"Draw 1 more card at the start of each turn.",
				"Gain 1 additional [R] at the start of each turn.", 
				"???", 
		};
	
	private static final int DEXTERITY_BUFF = 1;
	private static final int PLATED_BUFF = 2;
	private static final int STRENGTH_BUFF = 1;
	private static final int THORNS_BUFF = 1;
	private static final int STRENGTH_DEBUFF = -2;
	
	private static final int DRAW_BUFF = 1;
	private static final int ENERGY_BUFF = 1;
	private static final int YOU_ARE_BROKEN = 99;
	
	private String[] DIALOG = 
		{
				"I am the white void.",
				"I am the cold steel.",
				"I am the just sword.",
				"With blade in hand...",
				"Shall I reap the sins from this world...",
				"And cleanse it in the fires of destruction!",
				"I am Haku-men",
				"The end has come!"
		};

	public Haku_WhiteVoidPower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		this.priority = 0;
		this.img = new Texture("Hakumod/img/powers/white_void.png");
		updateDescription();
		
		//AbstractDungeon.player.dialogX = (10.0F * Settings.scale); 
		//AbstractDungeon.player.dialogY = (127.0F * Settings.scale);

		if (!owner.hasPower(POWER_ID)) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
					new DexterityPower(AbstractDungeon.player, DEXTERITY_BUFF), DEXTERITY_BUFF));
			AbstractDungeon.effectList.add(new SpeechBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 2.0f,
					DIALOG[0], true));
		}
	}

	public void stackPower(int stackAmount) {
		this.amount += stackAmount;
		//AbstractDungeon.player.dialogX = (10.0F * Settings.scale); 
		//AbstractDungeon.player.dialogY = (-27.0F * Settings.scale);

		
		switch (this.amount) {
			
			//stackAmount isn't called the first time power is applied.
			case 1:
				//AbstractDungeon.actionManager.addToBottom(new TalkAction(AbstractDungeon.player, DIALOG[this.amount-1], DURATION, BUBBLE_DURATION));
				//AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
				//		new DexterityPower(AbstractDungeon.player, DEXTERITY_BUFF), DEXTERITY_BUFF));
				break;
			case 2:
				//AbstractDungeon.actionManager.addToBottom(new TalkAction(AbstractDungeon.player, DIALOG[this.amount-1], DURATION, BUBBLE_DURATION));
				AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
						new PlatedArmorPower(this.owner, PLATED_BUFF), PLATED_BUFF));
				break;
			case 3:
				//AbstractDungeon.actionManager.addToBottom(new TalkAction(AbstractDungeon.player, DIALOG[this.amount-1], DURATION, BUBBLE_DURATION));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
						new StrengthPower(AbstractDungeon.player, STRENGTH_BUFF), STRENGTH_BUFF));
				break;
			case 4:
				//AbstractDungeon.actionManager.addToBottom(new TalkAction(AbstractDungeon.player, DIALOG[this.amount-1], DURATION, BUBBLE_DURATION));
				AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
						new ThornsPower(this.owner, THORNS_BUFF), THORNS_BUFF));
				break;
			case 5:
				//AbstractDungeon.actionManager.addToBottom(new TalkAction(AbstractDungeon.player, DIALOG[this.amount-1], DURATION, BUBBLE_DURATION));
				
				for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
	    			if ((mo != null) && (!mo.isDeadOrEscaped())) {
				  
	    				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, AbstractDungeon.player, 
	    						new StrengthPower(mo, STRENGTH_DEBUFF), STRENGTH_DEBUFF));
	    			}
				}
				
				break;
			case 6:
				//AbstractDungeon.actionManager.addToBottom(new TalkAction(AbstractDungeon.player, DIALOG[this.amount-1], DURATION, BUBBLE_DURATION));
				AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
						new DrawPower(this.owner, DRAW_BUFF), DRAW_BUFF));
				break;
			case 7:
				//AbstractDungeon.actionManager.addToBottom(new ShoutAction(AbstractDungeon.player, DIALOG[this.amount-1], DURATION, BUBBLE_DURATION));
				AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
						new BerserkPower("White void", this.owner, ENERGY_BUFF), ENERGY_BUFF));
				break;
			default:
				//GG well played, thanks for the matches.
				AbstractDungeon.effectList.add(new SpeechBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 2.0f,
						DIALOG[0], true));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
						new StrengthPower(AbstractDungeon.player, YOU_ARE_BROKEN), YOU_ARE_BROKEN));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
						new DexterityPower(AbstractDungeon.player, YOU_ARE_BROKEN), YOU_ARE_BROKEN));
				   
				break;	
			/*default:
				break;*/
		}
		
		if (this.amount<=DIALOG.length) {
			AbstractDungeon.effectList.add(new SpeechBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 2.0f,
					DIALOG[this.amount-1], true));
		}
	}

	public void updateDescription() {
		String stringNextBonus = "None.";
		
		int bonusCap = (this.amount > arrayBonus.length-1) ?  arrayBonus.length-1 : this.amount;
		stringNextBonus = arrayBonus[bonusCap];
		
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + stringNextBonus);
	}
}
