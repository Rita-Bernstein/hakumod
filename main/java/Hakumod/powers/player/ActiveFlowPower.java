package Hakumod.powers.player;

import Hakumod.cards.Hakumen.normal.Overdrive;
import Hakumod.powers.abstracts.AbstractHakuPower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ActiveFlowPower extends AbstractHakuPower {
	private static final String CLASS_NAME = ActiveFlowPower.class.getSimpleName();
	public static final String POWER_ID = makePowerID(CLASS_NAME);

	public ActiveFlowPower(AbstractCreature owner, int amount) {
			super(owner, amount, CLASS_NAME);
			this.isTurnBased = true;
			updateDescription();
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
			act(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, ActiveFlowPower.POWER_ID));
			
			AbstractCard cardOD = new Overdrive();
			AbstractDungeon.player.hand.addToHand(cardOD);
			
		} else {
			act(new com.megacrit.cardcrawl.actions.common.ReducePowerAction(this.owner, this.owner, ActiveFlowPower.POWER_ID, 1));
		}
	}
	
}