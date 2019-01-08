package Hakumod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.powers.WeakPower;

import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import Hakumod.cards.Hakumen.Haku_ChildishMemories;
import Hakumod.cards.Hakumen.Haku_InJustice;
import Hakumod.cards.Hakumen.Haku_Walpurgisnacht;
import basemod.BaseMod;

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
	
	public void updateCard(AbstractCard c) {
		if (c instanceof Haku_Walpurgisnacht) {
			c.updateCost(-1);
		}
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
		
		if (this.p.hasPower("Haku_YomotsuhirasakaPower") && !boolHasInHand){
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
		}
		
		
		if (!boolHasInHand) {
			if (this.p.cardInUse!=null){
				if (this.p.cardInUse.upgraded){c.upgrade();}}
			AbstractDungeon.actionManager.addToTop(new MakeTempCardInHandAction(c, false));
			if (p.hasPower("Artifact")) { if (p.getPower("Artifact").amount >= 2) {AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 2), 2));}}
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, -2), -2));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new GainStrengthPower(p, 2), 2));
				//AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new LoseStrengthPower(p, -2), -2));
		}
		
		for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
			updateCard(c);	
		}
		
		for (AbstractCard c : AbstractDungeon.player.hand.group) {
			updateCard(c);	
		}
		
		for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
			updateCard(c);	
		}
		
		
	this.isDone = true;
	}
}