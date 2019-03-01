package Hakumod.action;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


public class AddToHandAction extends AbstractGameAction{
	private AbstractPlayer player;
	private CardGroup cardGroup;
	private AbstractCard cardToAdd;
	private boolean toRemoveFromGroup;
	private boolean toUpgrade;
	private boolean toExhaust;
	private boolean toChangeCost;
	private int cost;
	
	public AddToHandAction(CardGroup cardGroup, AbstractCard cardToAdd, boolean toRemoveFromGroup, boolean toUpgrade, boolean toExhaust, boolean toChangeCost, int costForCombat){
		this.player = AbstractDungeon.player;
		setValues(this.player, AbstractDungeon.player, 1);
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
		this.duration = Settings.ACTION_DUR_MED;
		this.cardGroup = cardGroup;
		this.cardToAdd = cardToAdd;
		this.toRemoveFromGroup = toRemoveFromGroup;
		this.toUpgrade = toUpgrade;
		this.toChangeCost = toChangeCost;
		this.cost = costForCombat;
	}
	
	
	
	
	public void update(){
		if (this.toUpgrade) {
			cardToAdd.upgrade();
		}
		if (this.toExhaust) {
			cardToAdd.exhaustOnUseOnce = true;
		}
		if (this.toChangeCost) {
			cardToAdd.setCostForTurn(0);
			cardToAdd.cost = cost;
			cardToAdd.isCostModified = true;
		}
		
		if (player.hand.size() < BaseMod.MAX_HAND_SIZE) {
			player.hand.addToHand(cardToAdd);	
			cardToAdd.lighten(false);
		}
		else {
			player.createHandIsFullDialog();
			player.discardPile.addToTop(cardToAdd);
		}
		if (toRemoveFromGroup) {cardGroup.removeCard(cardToAdd);}
		player.hand.refreshHandLayout();
		this.isDone = true;
		tickDuration();
	}
}

