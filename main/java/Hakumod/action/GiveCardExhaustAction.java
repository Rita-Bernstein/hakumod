package Hakumod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class GiveCardExhaustAction extends AbstractGameAction{

    private ArrayList<AbstractCard> cardList;
    private AbstractCard c;
    private boolean exhaust;
    private boolean ethereal;
    private boolean allCopy;


    public GiveCardExhaustAction(AbstractCard c, ArrayList<AbstractCard> cardList, boolean exhaust, boolean ethereal, boolean allCopy)
    {
        this.duration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_XFAST;
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.source = AbstractDungeon.player;
        this.c = c;
        this.exhaust = exhaust;
        this.ethereal = ethereal;
        this.allCopy = allCopy;
        this.cardList = cardList;
    }


    public void update()
    {
        for (AbstractCard cardInHand: this.cardList) {
            if (cardInHand.cardID == c.cardID){
                cardInHand.exhaustOnUseOnce = exhaust;
                cardInHand.isEthereal = ethereal;
                if (!this.allCopy){break;}
            }
        }
        this.isDone = true;
    }
}
