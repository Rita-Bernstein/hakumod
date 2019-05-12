package Hakumod.powers.player;

import Hakumod.powers.abstracts.AbstractHakuPower;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;


public class OverdrivePower extends AbstractHakuPower {
	private static final String CLASS_NAME = OverdrivePower.class.getSimpleName();
	public static final String POWER_ID = makePowerID(CLASS_NAME);

	public OverdrivePower(AbstractCreature owner, int amount) {
			super(owner, amount, CLASS_NAME);
			this.isTurnBased = true;
			updateDescription();
	}

	public void atEndOfRound()
	{
		if (this.amount <= 1) {
			act(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
		} else {
			act(new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));
		}
	}
}
