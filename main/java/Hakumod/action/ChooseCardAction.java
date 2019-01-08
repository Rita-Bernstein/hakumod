package Hakumod.action;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import basemod.BaseMod;


public class ChooseCardAction extends AbstractGameAction{
	private AbstractPlayer player;
	private boolean toUpgrade;
	private ArrayList<AbstractCard> cards;
	private boolean toExhaust;
	private boolean setCost;
	private int cost;
	private boolean toRemoveFromDeck;
	
	public ChooseCardAction(ArrayList<AbstractCard> cards, boolean toUpgrade, boolean toExhaust, boolean setCost, int costForCombat, boolean toRemoveFromDeck){
		this.player = AbstractDungeon.player;
		setValues(this.player, AbstractDungeon.player, 1);
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
		this.duration = Settings.ACTION_DUR_MED;
		this.toUpgrade = toUpgrade;
		this.cards = cards;
		this.toExhaust = toExhaust;
		this.setCost = setCost;
		this.cost = costForCombat;
		this.toRemoveFromDeck = toRemoveFromDeck; 
	}
	
	public ChooseCardAction(ArrayList<AbstractCard> cards, boolean toUpgrade) {
		this(cards, toUpgrade, false, false, 0, false);
	}
	
	public void update(){
		CardGroup choiceOfCards;
		if (this.duration == Settings.ACTION_DUR_MED){
			choiceOfCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
			
			for (AbstractCard card : cards) {
				choiceOfCards.addToTop(card);
			}
			AbstractDungeon.gridSelectScreen.open(choiceOfCards, 1, "Choose one card", false);
			tickDuration();
			return;
		}
		if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0){
			for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards){
				c.unhover();
				
				if (this.toUpgrade) {
					c.upgrade();
				}
				if (this.toExhaust) {
					c.exhaustOnUseOnce = true;
				}
				if (this.setCost) {
					c.setCostForTurn(0);
					c.cost = cost;
					c.isCostModified = true;
				}
				
				if (this.player.hand.size() == BaseMod.MAX_HAND_SIZE){
					this.player.createHandIsFullDialog();
					this.player.discardPile.addToTop(c);
				}
				else{
					this.player.hand.addToHand(c);
				}
				
				if (this.toRemoveFromDeck) {
					c.lighten(false);
					this.player.drawPile.removeCard(c);
				}
				
				this.player.hand.refreshHandLayout();
				this.player.hand.applyPowers();
			}
			AbstractDungeon.gridSelectScreen.selectedCards.clear();
		}
		this.isDone = true;
		tickDuration();
	}
}

