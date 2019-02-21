package Hakumod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

public class UpgradeCardAction extends AbstractGameAction{
	
	AbstractCard card;
	
	public UpgradeCardAction(AbstractPlayer p, AbstractCard c)
	{
		this.duration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_XFAST;
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
		this.source = p;
		this.card = c;
	}
	
	
	public void update() 
	{	
		if (card.canUpgrade()){
			card.upgrade();
		}
		this.isDone = true;
	}
}
