package Hakumod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class ComboAction extends AbstractGameAction{
	
	private int STRENGTH_DEBUFF = -2;
	private AbstractPlayer p;
	private AbstractCard c;
	
	public ComboAction(AbstractCard card)
	{
		this.p = AbstractDungeon.player;
		this.c = card;
		this.duration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_XFAST;
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
	}
	
	@Override
	public void update() 
	{
		
		boolean boolHasInHand = false;
		
		for (AbstractCard cardInHand:p.hand.group) {
			if (cardInHand.cardID == this.c.cardID){
				cardInHand.setCostForTurn(0);
				if (this.p.cardInUse!=null) {if (this.p.cardInUse.upgraded && !cardInHand.upgraded) {cardInHand.upgrade();}}
				boolHasInHand = true;
				break;
			}
		}
		
		if (!boolHasInHand) {
			if (this.p.cardInUse!=null){
				if (this.p.cardInUse.upgraded){c.upgrade();}}

			AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, false));
			AbstractDungeon.actionManager.addToBottom(new GiveCardExhaustAction(c, AbstractDungeon.player.hand.group, true, true, false));

			if (p.hasPower(ArtifactPower.POWER_ID) && (p.getPower(ArtifactPower.POWER_ID).amount >= 2)) {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, STRENGTH_DEBUFF), STRENGTH_DEBUFF));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, -STRENGTH_DEBUFF), -STRENGTH_DEBUFF));
			}
			else {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, STRENGTH_DEBUFF), STRENGTH_DEBUFF));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new GainStrengthPower(p, -STRENGTH_DEBUFF), -STRENGTH_DEBUFF));
			}
		}
		
		
	this.isDone = true;
	}
}
