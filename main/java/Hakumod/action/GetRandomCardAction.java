package Hakumod.action;

import Hakumod.cards.Hakumen.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;

import java.util.ArrayList;


public class GetRandomCardAction extends AbstractGameAction{

	
	public GetRandomCardAction(){
		//this.player = AbstractDungeon.player;
		//setValues(this.player, AbstractDungeon.player, 1);
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
		this.duration = Settings.ACTION_DUR_MED;

	}
	public AbstractCard getRandomParry() {
    	ArrayList<AbstractCard> randomCard = new ArrayList<AbstractCard>();
    	randomCard.add(new Haku_5D().makeCopy());
    	randomCard.add(new Haku_2D().makeCopy());
    	randomCard.add(new Haku_6D().makeCopy());
    	randomCard.add(new Haku_JD().makeCopy());
    	randomCard.add(new Haku_Yanagi().makeCopy());
    	randomCard.add(new Haku_Yukikaze().makeCopy());
    	randomCard.add(new Haku_Akumetsu().makeCopy());
    	randomCard.add(new Haku_Hotaru().makeCopy());
    	randomCard.add(new Haku_CA().makeCopy());
    	randomCard.add(new Haku_GCOD().makeCopy());
    	
    	AbstractCard pickedCard = randomCard.get( (int) (Math.random()*randomCard.size()) );
    	
    	return pickedCard;
	}
	public void update(){
		tickDuration();
	}
}

