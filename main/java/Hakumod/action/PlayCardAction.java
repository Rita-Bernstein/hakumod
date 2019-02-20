package Hakumod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PlayCardAction extends AbstractGameAction{
	
	private AbstractPlayer player;
	private AbstractCard card;
	private AbstractMonster target;
	//private String effect;
	//private int magnitude;
		
	public PlayCardAction(AbstractPlayer p, AbstractCard c, AbstractMonster target)
	{
		this.player = p;
		this.card = c;
		this.target = target;
		this.duration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_XFAST;
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
	}
	
	@Override
	public void update() {
		this.player.useCard(this.card, this.target, 0);
		this.isDone = true;
	}
}
