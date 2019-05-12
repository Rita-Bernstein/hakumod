package Hakumod.powers.player;

import Hakumod.powers.abstracts.AbstractHakuPower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class PassiveMeterPower extends AbstractHakuPower {
	private static final String CLASS_NAME = MugenPower.class.getSimpleName();
	public static final String POWER_ID = makePowerID(CLASS_NAME);

	public PassiveMeterPower(AbstractCreature owner, int amount) {
		super(owner, amount, CLASS_NAME);
		updateDescription();
	}

	public void atStartOfTurn() {
		super.atStartOfTurn();
		power(AbstractDungeon.player,AbstractDungeon.player,new MagatamaPower(AbstractDungeon.player,this.amount),this.amount);
	}
}
