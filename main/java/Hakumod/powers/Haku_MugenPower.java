package Hakumod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
//import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Hakumod.patches.CustomTags;
import basemod.helpers.CardTags;


public class Haku_MugenPower extends AbstractPower {
	public static final String POWER_ID = "Haku_MugenPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	
	public Haku_MugenPower(AbstractCreature owner, int amount, boolean isSourceMonster) {
			this.name = NAME;
			this.ID = POWER_ID;
			this.owner = owner;
			this.amount = amount;
			this.isTurnBased = true;
			this.img = new Texture("Hakumod/img/powers/infiniteGreen.png");
			updateDescription();
	}
	
	public void stackPower(int stackAmount)
	{
		this.amount += stackAmount;
	}
	
	public void updateDescription() {
		this.description = (DESCRIPTIONS[0]);
	}
	
	
	public void onDrawOrDiscard() {
		if (AbstractDungeon.player.hand.size() > 0) {
			AbstractCard cardAtTheTop = AbstractDungeon.player.hand.getTopCard();
			if ( (CardTags.hasTag(cardAtTheTop, CustomTags.SPECIAL)))
			{
				cardAtTheTop.setCostForTurn(0);
			}	
		}
	}

	public void atEndOfRound()
	{
		if (this.amount <= 1 ) {
			AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
		} else {
			AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ReducePowerAction(this.owner, this.owner, POWER_ID, 1));
		}
	}
}
