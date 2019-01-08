package Hakumod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Hakumod.cards.Hakumen.Haku_Overdrive;


public class Haku_ActiveFlowPower extends AbstractPower {
	public static final String POWER_ID = "Haku_ActiveFlowPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	
	public Haku_ActiveFlowPower(AbstractCreature owner, int amount) {
			this.name = NAME;
			this.ID = POWER_ID;
			this.owner = owner;
			this.amount = amount;
			this.isTurnBased = true;
			this.img = new Texture("Hakumod/img/powers/corruption.png");
			updateDescription();
	}
	
	public void updateDescription() {
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
	}
	
	public float atDamageGive(float damage, DamageInfo.DamageType type)
	{
		if (type == DamageInfo.DamageType.NORMAL) {
			return (float) (damage * 1.1);
		}
		return damage;
	}
	
	public void atEndOfRound()
	{
		if (this.amount <= 1) {
			AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, "Haku_ActiveFlowPower"));
			
			AbstractCard cardOD = new Haku_Overdrive();
			//cardOD.setCostForTurn(0);
			AbstractDungeon.player.hand.addToHand(cardOD);
			
		} else {
			AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ReducePowerAction(this.owner, this.owner, "Haku_ActiveFlowPower", 1));
		}
	}
	
}