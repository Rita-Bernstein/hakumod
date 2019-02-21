package Hakumod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

public class SetCardCostAction extends AbstractGameAction{
	
	AbstractCard card;
	int cost;
	
	public SetCardCostAction(AbstractPlayer p, AbstractCard c, int cost)
	{
		this.duration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_XFAST;
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
		this.source = p;
		this.card = c;
		this.cost = cost;
	}
	
	
	public void update() 
	{	
		card.setCostForTurn(0);
		this.isDone = true;
	}
}
