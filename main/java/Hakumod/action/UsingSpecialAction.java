package Hakumod.action;

import Hakumod.cards.Hakumen.normal.InJustice;
import Hakumod.characters.Hakumen;
import Hakumod.powers.player.MagatamaPower;
import Hakumod.powers.player.MugenPower;
import Hakumod.relics.ContinuumShift;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class UsingSpecialAction extends AbstractGameAction{
	
	private int cost;
	
	public UsingSpecialAction(AbstractPlayer p, int cost)
	{
		this.duration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_XFAST;
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
		this.source = p;
		this.cost = cost;
	}
	
	public boolean canUseSpecialAction()
	{
		boolean canUse = false;
		if ( ((this.source.hasPower(MagatamaPower.POWER_ID) && this.source.getPower(MagatamaPower.POWER_ID).amount >= this.cost))
				|| this.source.hasPower(MugenPower.POWER_ID) || !(AbstractDungeon.player instanceof Hakumen))
		{
			canUse = true;
		}
		return canUse;
	}
	
	private void updateCard(AbstractCard c) {
		if (c instanceof InJustice) {
			c.updateCost(-1);
		}
	}
	
	public void update() 
	{
		// TODO Auto-generated method stub
		if (this.canUseSpecialAction())
		{
			if (!this.source.hasPower(MugenPower.POWER_ID) && (AbstractDungeon.player instanceof Hakumen)) {
				this.source.getPower(MagatamaPower.POWER_ID).reducePower(this.cost);
				this.source.getPower(MagatamaPower.POWER_ID).updateDescription();
				if (this.source.getPower(MagatamaPower.POWER_ID).amount == 0)
				{
					AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.source, this.source, MagatamaPower.POWER_ID));
				}
			}
			if (AbstractDungeon.player.hasRelic(ContinuumShift.RELIC_ID) && this.cost > 2) {
				AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
			}

			for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
				updateCard(c);	
			}
			
			for (AbstractCard c : AbstractDungeon.player.hand.group) {
				updateCard(c);	
			}
			
			for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
				updateCard(c);	
			}
			
		}
	
	this.isDone = true;
	}
}
