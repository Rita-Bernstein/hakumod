package Hakumod.cards.Hakumen.normal;

import Hakumod.action.AddToHandAction;
import Hakumod.action.ChooseCardFromDeckAction;
import Hakumod.cards.Hakumen.abstracts.HakuCustomCard;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class StandUnrivaled extends HakuCustomCard {

	public static final String ID = makeCardID(StandUnrivaled.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

	private static final int COST = 0;

	public StandUnrivaled() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.RARE, 
				AbstractCard.CardTarget.SELF);
		this.exhaust = true;
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = UPG_DESCRIPTION;
			this.exhaust = false;
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	ArrayList<AbstractCard> drawPile = p.drawPile.group;
    	ArrayList<AbstractCard> arraySpecialinDraw = new ArrayList<AbstractCard>();
    	for (AbstractCard cardInDraw : drawPile) {
    		if (cardInDraw.hasTag(CustomTags.SPECIAL)) {
    			arraySpecialinDraw.add(cardInDraw);
    		}	
    	}
    	
    	if (arraySpecialinDraw.size() > 0) {
    		if (arraySpecialinDraw.size() == 1) {
    			act(
    					new AddToHandAction(p.drawPile, arraySpecialinDraw.get(0), true, false, false, false, 0));
    		}
    		else {
    			act(
    					new ChooseCardFromDeckAction(arraySpecialinDraw, false));
    		}
    	}
    }
	
	public AbstractCard makeCopy() {
		return new StandUnrivaled();
	}
	
	
}