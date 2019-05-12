package Hakumod.orbs;

import Hakumod.orbs.abstracts.AbstractHakuOrb;
import Hakumod.powers.player.MagatamaPower;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

public class VoidOrb extends AbstractHakuOrb {
	private static final String CLASSNAME = VoidOrb.class.getSimpleName();
	public static final String ID = makeOrbID(CLASSNAME);

	private static final int BASE_PASSIVE_AMOUNT = 2;
	private static final int BASE_EVOKE_AMOUNT = 1;
	private static final int BLOCK = 2;
	private static final int MAGATAMA = 1;
	
	public VoidOrb() {
		super(CLASSNAME, BASE_PASSIVE_AMOUNT, BASE_EVOKE_AMOUNT);
	}

	@Override
	public AbstractOrb makeCopy() {
		return new VoidOrb();
	}

	@Override
	public void onEvoke() {
		power(AbstractDungeon.player,AbstractDungeon.player,new MagatamaPower(AbstractDungeon.player,MAGATAMA),MAGATAMA);
	}

	@Override
	public void onStartOfTurn() {
		super.onStartOfTurn();
		this.passiveAmount--;
		if (this.passiveAmount == 0) {
			act(new EvokeOrbAction(1));
		}
	}
	
	@Override
	public void onEndOfTurn() {
		block(AbstractDungeon.player, AbstractDungeon.player, BLOCK);
		super.onEndOfTurn();
	}

	@Override
	public void playChannelSFX() {}
}
