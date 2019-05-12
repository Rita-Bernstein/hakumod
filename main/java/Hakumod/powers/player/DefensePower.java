package Hakumod.powers.player;

import Hakumod.powers.abstracts.AbstractHakuPower;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class DefensePower extends AbstractHakuPower {
	private static final String CLASS_NAME = DefensePower.class.getSimpleName();
	public static final String POWER_ID = makePowerID(CLASS_NAME);

	private static final float DEFENSE = 0.20F;
	
	public DefensePower(AbstractCreature owner, int amount, boolean isSourceMonster) {
		super(owner, amount, CLASS_NAME);
		this.isTurnBased = true;
		updateDescription();
			
		if (this.owner.hasPower(OffensePower.POWER_ID) ){
			act(new RemoveSpecificPowerAction(this.owner, this.owner, OffensePower.POWER_ID));
		}
	}
	
	public void stackPower(int stackAmount)
	{
		this.amount += stackAmount;
	}

	public float atDamageReceive(float damage, DamageInfo.DamageType type)
	{
		if (type == DamageInfo.DamageType.NORMAL)
		{
			return damage * (1.00F - DEFENSE);
		}
		return damage;
	}	
	
	public void atEndOfRound()
	{
		if (this.amount <= 1 ) {
			act(new RemoveSpecificPowerAction(this.owner, this.owner, DefensePower.POWER_ID));
		} else {
			act(new ReducePowerAction(this.owner, this.owner, DefensePower.POWER_ID, 1));
		}
		
	}
}
