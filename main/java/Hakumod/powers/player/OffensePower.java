package Hakumod.powers.player;

import Hakumod.powers.abstracts.AbstractHakuPower;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class OffensePower extends AbstractHakuPower {
	private static final String CLASS_NAME = OffensePower.class.getSimpleName();
	public static final String POWER_ID = makePowerID(CLASS_NAME);
	private static final float OFFENSE= 0.25f;

	public OffensePower(AbstractCreature owner, int amount, boolean isSourceMonster) {
		super(owner, amount, CLASS_NAME);
		this.isTurnBased = true;
		updateDescription();

		if (this.owner.hasPower(DefensePower.POWER_ID) ){
			act(new RemoveSpecificPowerAction(this.owner, this.owner, DefensePower.POWER_ID));
		}
	}

	public float atDamageGive(float damage, DamageInfo.DamageType type)
	{
		if (type == DamageInfo.DamageType.NORMAL) {
			return damage * (1.00F + OFFENSE);
		}
		return damage;
	}
	
	public void atEndOfRound()
	{

		if (this.amount <= 1 ) {
			act(new RemoveSpecificPowerAction(this.owner, this.owner, OffensePower.POWER_ID));
		} else {
			act(new ReducePowerAction(this.owner, this.owner, OffensePower.POWER_ID, 1));
		}
	}
}