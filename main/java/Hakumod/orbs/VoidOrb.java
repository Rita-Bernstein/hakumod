package Hakumod.orbs;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

import Hakumod.powers.Haku_MagatamaPower;
import basemod.abstracts.CustomOrb;

public class VoidOrb extends CustomOrb{
	private static final String ID = "VoidOrb";
	private static final String NAME = "Void";
	private static final int basePassiveAmount = 2;
	private static final int baseEvokeAmount = 1;
	private static final String passiveDescription = "Gain 2 Blocks at the end of each turn. Evoke after 2 turns.";
	private static final String evokeDescription = "Gain 1 Magatama";
	private static final String imgPath = "Hakumod/img/orbs/fuumajin.png";
	private int block = 2;
	
	public VoidOrb() {
		super(ID, NAME, basePassiveAmount, baseEvokeAmount, passiveDescription, evokeDescription, imgPath);
		// TODO Auto-generated constructor stub
	}

	@Override
	public AbstractOrb makeCopy() {
		// TODO Auto-generated method stub
		return new VoidOrb();
	}

	@Override
	public void onEvoke() {
		// TODO Auto-generated method stub
		AbstractDungeon.actionManager.addToTop(
				new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new Haku_MagatamaPower(AbstractDungeon.player,1),1));
		
	}

	/* (non-Javadoc)
	 * @see com.megacrit.cardcrawl.orbs.AbstractOrb#onStartOfTurn()
	 */
	@Override
	public void onStartOfTurn() {
		// TODO Auto-generated method stub
		super.onStartOfTurn();
		 
		 this.passiveAmount--;
		 if (this.passiveAmount == 0) {
			 AbstractDungeon.actionManager.addToBottom(new EvokeOrbAction(1));
		 }
	}
	
	@Override
	public void onEndOfTurn() {
		// TODO Auto-generated method stub
		AbstractDungeon.actionManager.addToTop(
				new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.block));
		super.onEndOfTurn();
	}

	/* (non-Javadoc)
	 * @see com.megacrit.cardcrawl.orbs.AbstractOrb#onEndOfTurn()
	 */


	@Override
	public void playChannelSFX() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.megacrit.cardcrawl.orbs.AbstractOrb#onEndOfTurn()
	 */


	

}
