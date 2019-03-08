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

//import com.megacrit.cardcrawl.powers.WeakPower;

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
	
	/*public void updateCard(AbstractCard c) {
		if (c instanceof Haku_Walpurgisnacht) {
			c.updateCost(-1);
		}
	}*/
	
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
		
		/*if (this.p.hasPower(Haku_YomotsuhirasakaPower.POWER_ID) && !boolHasInHand){
			for (AbstractCard cardInDeck:p.drawPile.group) {
				if (cardInDeck.cardID == this.c.cardID){
					cardInDeck.setCostForTurn(0);
					if (this.p.cardInUse!=null) {if (this.p.cardInUse.upgraded && !cardInDeck.upgraded) {cardInDeck.upgrade();}}
					if (p.hand.size() < BaseMod.MAX_HAND_SIZE) {
						cardInDeck = p.drawPile.getSpecificCard(cardInDeck);
				    	p.hand.addToHand(cardInDeck);	
				    	cardInDeck.lighten(false);
				    	p.drawPile.removeCard(cardInDeck);
				    	p.hand.refreshHandLayout();
				    	boolHasInHand = true;
			    	}
					break;
				}
			}
		}*/
		
		/*if (this.source.hasPower(Haku_TheTyrantPower.POWER_ID)) {
			int StrengthAmount = this.source.getPower(Haku_TheTyrantPower.POWER_ID).amount;
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.source, this.source,
					new StrengthPower(this.source, StrengthAmount), StrengthAmount));
		}*/
		
		if (!boolHasInHand) {
			if (this.p.cardInUse!=null){
				if (this.p.cardInUse.upgraded){c.upgrade();}}

			//Test
			c.exhaustOnUseOnce = true;
			c.isEthereal = true;

			AbstractDungeon.actionManager.addToTop(new MakeTempCardInHandAction(c, false));
			
			if (p.hasPower(ArtifactPower.POWER_ID) && (p.getPower(ArtifactPower.POWER_ID).amount >= 2)) {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, STRENGTH_DEBUFF), STRENGTH_DEBUFF));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, -STRENGTH_DEBUFF), -STRENGTH_DEBUFF));
			}
			else {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, STRENGTH_DEBUFF), STRENGTH_DEBUFF));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new GainStrengthPower(p, -STRENGTH_DEBUFF), -STRENGTH_DEBUFF));
			}
				//AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new LoseStrengthPower(p, -2), -2));
		}
		
		/*for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
			updateCard(c);	
		}
		
		for (AbstractCard c : AbstractDungeon.player.hand.group) {
			updateCard(c);	
		}
		
		for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
			updateCard(c);	
		}*/
		
		
	this.isDone = true;
	}
}
