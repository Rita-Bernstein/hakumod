package Hakumod.powers.player;

import Hakumod.powers.abstracts.AbstractHakuPower;
import com.megacrit.cardcrawl.core.AbstractCreature;


public class SwordOfDoomPower extends AbstractHakuPower {
	private static final String CLASS_NAME = SwordOfDoomPower.class.getSimpleName();
	public static final String POWER_ID = makePowerID(CLASS_NAME);

	public SwordOfDoomPower(AbstractCreature owner, int amount) {
			super(owner, amount, CLASS_NAME);
			this.isTurnBased = true;
			updateDescription();
	}
}
