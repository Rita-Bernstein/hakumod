package Hakumod.powers;

import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
//import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
//import com.megacrit.cardcrawl.relics.AbstractRelic.LandingSound;
//import com.megacrit.cardcrawl.relics.AbstractRelic.RelicTier;

//import Hakumod.HakuInit;

public class Haku_MagatamaPower extends AbstractPower{
	public static final String POWER_ID = "Haku_MagatamaPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
			
	
	public Haku_MagatamaPower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		this.priority = 0;
		this.img = new Texture("Hakumod/img/powers/magatama.png");
		
		if (this.owner.hasPower("Haku_OverdrivePower")) {
			this.amount += amount;
		}
		updateDescription();
	}
	
	public void stackPower(int stackAmount)
	{
		this.amount += stackAmount;
		if (this.owner.hasPower("Haku_OverdrivePower")) {
			this.amount += stackAmount;
		}
		
		if (this.amount > 8) {
			this.amount = 8;
		}
	}
	
	public void updateDescription()
	{
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
	}
}
