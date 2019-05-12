package Hakumod.powers.player;

import Hakumod.powers.abstracts.AbstractHakuPower;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class MagatamaPower extends AbstractHakuPower {
	private static final String CLASS_NAME = MagatamaPower.class.getSimpleName();
	public static final String POWER_ID = makePowerID(CLASS_NAME);

	public MagatamaPower(AbstractCreature owner, int amount) {
		super(owner, amount, CLASS_NAME);
		if (this.owner.hasPower(OverdrivePower.POWER_ID)) {
			this.amount += amount;
		}
		updateDescription();
	}
	
	public void stackPower(int stackAmount)
	{
		this.amount += stackAmount;
		if (this.owner.hasPower(OverdrivePower.POWER_ID)) {
			this.amount += stackAmount;
		}
		
		if (this.amount > 8) {
			this.amount = 8;
		}
	}
}
