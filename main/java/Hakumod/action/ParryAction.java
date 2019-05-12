package Hakumod.action;

import Hakumod.powers.player.TheGatePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ParryAction extends AbstractGameAction{
	
	private AbstractPlayer player;
	private AbstractCard card;
	private AbstractMonster target;
	private String effect;
	private int magnitude;

	public ParryAction(AbstractPlayer p, AbstractCard c, AbstractMonster target , String effect, int magnitude)
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
		if (canUseParry.canUse(this.target)){
			int bonus = 0;
			if (player.hasPower(TheGatePower.POWER_ID) && (this.effect.equals(UtilsApplyEffect.ATTACK) || this.effect.equals(UtilsApplyEffect.ATTACK_ALL)) ) {
				bonus = (player.getPower(TheGatePower.POWER_ID).amount)*this.magnitude;
			}
			new UtilsApplyEffect(this.player, this.card, this.target, this.effect, this.magnitude+bonus);
		}
	this.isDone = true;
	}
}
