package Hakumod.powers.player;

import Hakumod.powers.abstracts.AbstractHakuPower;
import com.megacrit.cardcrawl.core.AbstractCreature;


public class TheGatePower extends AbstractHakuPower {
	private static final String CLASS_NAME = TheGatePower.class.getSimpleName();
	public static final String POWER_ID = makePowerID(CLASS_NAME);

	public TheGatePower(AbstractCreature owner, int amount) {
		super(owner, amount, CLASS_NAME);
		updateDescription();
	}
}
