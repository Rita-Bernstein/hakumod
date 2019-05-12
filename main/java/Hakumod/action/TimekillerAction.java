package Hakumod.action;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


public class TimekillerAction extends AbstractGameAction{
    private int cardAmount;

    public TimekillerAction(int cardAmount){
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
        this.cardAmount = cardAmount;
    }

    private void addCardToHand(AbstractCard c){
        c.unhover();

        if (AbstractDungeon.player.hand.size() == BaseMod.MAX_HAND_SIZE){
            AbstractDungeon.player.createHandIsFullDialog();
            AbstractDungeon.player.discardPile.addToTop(c);
        }
        else{
            AbstractDungeon.player.hand.addToHand(c);
        }

        c.lighten(false);
        c.exhaustOnUseOnce = true;
        c.setCostForTurn(0);
        c.cost = 0;
        c.isCostModified = true;
        AbstractDungeon.player.exhaustPile.removeCard(c);

        AbstractDungeon.player.hand.refreshHandLayout();
        AbstractDungeon.player.hand.applyPowers();
    }

    public void update(){
        CardGroup choiceOfCards;
        if (this.duration == Settings.ACTION_DUR_MED){
            choiceOfCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

            for (AbstractCard card : AbstractDungeon.player.exhaustPile.group) {
                if (card.type == AbstractCard.CardType.ATTACK) {
                    choiceOfCards.addToTop(card);
                }
            }

            if (choiceOfCards.size() > 1) {
                AbstractDungeon.gridSelectScreen.open(choiceOfCards, this.cardAmount, "Choose 1 Attack", false);
            }

            else if (choiceOfCards.size() == 1){
                this.addCardToHand(choiceOfCards.group.get(0));
            }

            tickDuration();
            return;
        }

        if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0){
            for (AbstractCard card : AbstractDungeon.gridSelectScreen.selectedCards){
                this.addCardToHand(card);
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }
        this.isDone = true;
        tickDuration();
    }
}

