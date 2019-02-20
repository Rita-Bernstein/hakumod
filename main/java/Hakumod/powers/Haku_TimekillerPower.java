package Hakumod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.IntangiblePower;



public class Haku_TimekillerPower extends AbstractPower {
	public static final String POWER_ID = "Haku_TimekillerPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	//public static TextureAtlas powerAltas = BlackRuseMod.getPowerTextureAtlas();
	public int OMAE_WA_MOU_SHINDEIRU = 999;
	
	public Haku_TimekillerPower(AbstractCreature owner, int amount) {
			this.name = NAME;
			this.ID = POWER_ID;
			this.owner = owner;
			this.amount = amount;
			this.isTurnBased = true;
			this.img = new Texture("Hakumod/img/powers/timekiller.png");
			updateDescription();
	}


	public void updateDescription() {
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
	}
	
	public void atEndOfRound()
	{
		if (this.amount <= 1 ) {
			AbstractDungeon.actionManager.addToBottom(
					new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(
							this.owner,
							this.owner,
							Haku_TimekillerPower.POWER_ID));
			
			//For Nemesis on Act 3.
			if (this.owner.hasPower(IntangiblePower.POWER_ID)) {   
	            AbstractDungeon.actionManager.addToBottom(
	            		new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, IntangiblePower.POWER_ID)
	            );
			}
			
			//For the other cases of Intangible
			if (this.owner.hasPower(IntangiblePlayerPower.POWER_ID)) {
	            AbstractDungeon.actionManager.addToBottom(
	            		new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, IntangiblePlayerPower.POWER_ID)
	            );
			}
			
			AbstractDungeon.actionManager.addToBottom(
					new com.megacrit.cardcrawl.actions.common.DamageAction(
							owner,
							new DamageInfo(owner, 
									OMAE_WA_MOU_SHINDEIRU,
									DamageType.HP_LOSS),
							AbstractGameAction.AttackEffect.FIRE));
		} else {
			AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ReducePowerAction(
					this.owner, 
					this.owner, 
					Haku_TimekillerPower.POWER_ID,
					1
			));
		}
		
	}
	
}
