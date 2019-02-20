package Hakumod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class StarterAction extends AbstractGameAction{
	
	private AbstractPlayer player;
	private AbstractCard card;
	private AbstractMonster target;
	private String effect;
	private int magnitude;
	public boolean hasPlayedAnAttackThisTurn;
	
	public StarterAction(AbstractPlayer p, AbstractCard c, AbstractMonster target , String effect, int magnitude)
	{
		this.player = p;
		this.card = c;
		this.target = target;
		this.effect = effect;
		this.magnitude = magnitude;
		this.duration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_XFAST;
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
	}
	
	@Override
	public void update() 
	{
		if (this.card.energyOnUse >= AbstractDungeon.player.energy.energyMaster) {
			new UtilsApplyEffect(this.player, this.card, this.target, this.effect, this.magnitude);
		}
	this.isDone = true;
	}
}
