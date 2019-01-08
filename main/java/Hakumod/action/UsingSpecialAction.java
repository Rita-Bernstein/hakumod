package Hakumod.action;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ThornsPower;

import Hakumod.cards.Hakumen.Haku_ChildishMemories;
import Hakumod.cards.Hakumen.Haku_InJustice;

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
		if ( ((this.source.hasPower("Haku_MagatamaPower") && this.source.getPower("Haku_MagatamaPower").amount >= this.cost)) 
				|| this.source.hasPower("Haku_MugenPower"))
		{
			canUse = true;
		}
		return canUse;
	}
	
	public void updateCard(AbstractCard c) {
		if (c instanceof Haku_ChildishMemories) {
			c.baseMagicNumber++;
		}
		else if (c instanceof Haku_InJustice) {
			c.updateCost(-1);
		}
	}
	
	public void update() 
	{
		// TODO Auto-generated method stub
		if (this.canUseSpecialAction())
		{
			if (!this.source.hasPower("Haku_MugenPower")) {
				this.source.getPower("Haku_MagatamaPower").reducePower(this.cost);
				this.source.getPower("Haku_MagatamaPower").updateDescription();
				if (this.source.getPower("Haku_MagatamaPower").amount == 0) 
				{
					AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.source, this.source, "Haku_MagatamaPower"));
				}
			}
			if (AbstractDungeon.player.hasRelic("Haku_ContinuumShift") && this.cost > 2) {
				AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
			}
			
			if (this.source.hasPower("Haku_TheTyrantPower")) {
				int SparkAmount = this.source.getPower("Haku_TheTyrantPower").amount;
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.source, this.source,
						new ThornsPower(this.source, SparkAmount), SparkAmount));
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
