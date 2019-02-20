package Hakumod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;


public class Haku_OverdrivePower extends AbstractPower {
	public static final String POWER_ID = "Haku_OverdrivePower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	//public static TextureAtlas powerAltas = BlackRuseMod.getPowerTextureAtlas();
	
	public Haku_OverdrivePower(AbstractCreature owner, int amount) {
			this.name = NAME;
			this.ID = POWER_ID;
			this.owner = owner;
			this.amount = amount;
			this.isTurnBased = true;
			this.img = new Texture("Hakumod/img/powers/overdrive.png");
			updateDescription();
	}
	
	public void updateDescription() {
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
	}
	
	public void atEndOfRound()
	{
		if (this.amount <= 1) {
			AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
		} else {
			AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ReducePowerAction(this.owner, this.owner, POWER_ID, 1));
		}
	}
}
