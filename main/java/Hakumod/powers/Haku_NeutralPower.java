package Hakumod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
//import com.megacrit.cardcrawl.cards.DamageInfo;


public class Haku_NeutralPower extends AbstractPower {
	public static final String POWER_ID = "Haku_NeutralPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	
	public Haku_NeutralPower(AbstractCreature owner, int amount, boolean isSourceMonster) {
			this.name = NAME;
			this.ID = POWER_ID;
			this.owner = owner;
			this.amount = amount;
			this.isTurnBased = true;
			this.img = new Texture("Hakumod/img/powers/systems.png");
			updateDescription();
	}
	
	public void stackPower(int stackAmount)
	{
		this.amount += stackAmount;
	}
	
	public void updateDescription() {
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
	}
	
	public float modifyBlock(float blockAmount)
	{
		return blockAmount * (1.00F + 0.1F * this.amount);
	}
	
	public void atEndOfRound()
	{
		//if (!AbstractDungeon.player.hasPower("PoropicchoPower")) {
			if (this.amount <= 1 ) {
				AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
			} else {
				AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ReducePowerAction(this.owner, this.owner, POWER_ID, 1));
			}
		//}
	}
}
