package Hakumod.action;

import Hakumod.patches.CustomTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

public class FatalJudgeAction extends AbstractGameAction{
	
	AbstractCard card;
	AbstractPlayer player;
	int cost;
	
	public FatalJudgeAction(AbstractPlayer p, int cost)
	{
		this.duration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_XFAST;
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
		this.source = p;
		this.player = p;
		this.cost = cost;
	}
	
	
	public void update() 
	{	
		
		for (AbstractCard cardInHand: this.player.hand.group) {
			if (cardInHand.hasTag(CustomTags.COMBO) || cardInHand.hasTag(CustomTags.ENDER) || cardInHand.hasTag(CustomTags.STARTER)) {
				cardInHand.setCostForTurn(0);
				//AbstractDungeon.actionManager.addToBottom(new FatalJudgeAction(p, cardInHand, 0));	
			}
		}
		this.isDone = true;
	}
}
