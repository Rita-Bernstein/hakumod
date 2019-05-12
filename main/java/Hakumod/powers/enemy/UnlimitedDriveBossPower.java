package Hakumod.powers.enemy;

import Hakumod.powers.abstracts.AbstractHakuPower;
import com.megacrit.cardcrawl.core.AbstractCreature;


public class UnlimitedDriveBossPower extends AbstractHakuPower {
	private static final String CLASS_NAME = UnlimitedDriveBossPower.class.getSimpleName();
	public static final String POWER_ID = makePowerID(CLASS_NAME);

	public UnlimitedDriveBossPower(AbstractCreature owner, int amount) {
			super(owner, amount, CLASS_NAME);
			this.isTurnBased = true;
			updateDescription();
	}
}