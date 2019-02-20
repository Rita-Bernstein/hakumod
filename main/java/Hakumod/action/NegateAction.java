package Hakumod.action;

import java.util.Arrays;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class NegateAction extends AbstractGameAction{
	
	private AbstractPlayer player;
	private AbstractCard card;
	private AbstractMonster target;
	private String effect;
	private int magnitude;
	
	
	public NegateAction(AbstractPlayer p, AbstractCard c, AbstractMonster target , String effect, int magnitude)
	{
		this.player = p;
		this.card = c;
		this.target = target;
		this.target = target;
		this.effect = effect;
		this.magnitude = magnitude;
		this.duration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_XFAST;
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
	}
	
	@Override
	public void update() 
	{
		AbstractMonster.Intent[] arrayEffectIntents = {AbstractMonster.Intent.ATTACK_DEBUFF, AbstractMonster.Intent.DEBUFF, AbstractMonster.Intent.DEFEND_DEBUFF, AbstractMonster.Intent.STRONG_DEBUFF};
		
		if (Arrays.asList(arrayEffectIntents).contains(this.target.intent)){
			new UtilsApplyEffect(this.player, this.card, this.target, this.effect, this.magnitude);
		}
	this.isDone = true;
	}
}
