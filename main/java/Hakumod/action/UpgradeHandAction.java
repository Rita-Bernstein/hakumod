package Hakumod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

public class UpgradeHandAction extends AbstractGameAction{
	

	private AbstractPlayer player;


	public UpgradeHandAction(AbstractPlayer p)
	{
		this.duration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_XFAST;
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
		this.source = p;
		this.player = p;
	}
	
	
	public void update() 
	{	
		for (AbstractCard cardInHand: this.player.hand.group) {
			if (cardInHand.canUpgrade()){
				cardInHand.upgrade();
			}
		}
		this.isDone = true;
	}
}
