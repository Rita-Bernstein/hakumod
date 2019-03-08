package Hakumod.cards.Hakumen;

import Hakumod.action.AddToHandAction;
import Hakumod.action.ChooseCardAction;
import Hakumod.cards.Hakumen.Utils.Haku_CustomCard;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
//import com.megacrit.cardcrawl.localization.CardStrings;
//import Hakumod.powers.MagatamaPower;
//import basemod.helpers.BaseModTags;
//import basemod.helpers.CardTags;


public class Haku_Spellbook extends Haku_CustomCard {

	public static final String ID = "Haku_Spellbook";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_Spellbook.png";
	private static final int COST = 0;
	//private int CARD_TO_ADD = 2;
	//private static final int ATTACK_DMG = 4;
	//private static final int UPGRADE_PLUS_DMG = 2;
	//private static int CARD_TO_DRAW = 3;
	//private static int UPGRADE_DEBUFF = 1;
	    
	public Haku_Spellbook() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.RARE, 
				AbstractCard.CardTarget.SELF);
		// TODO Auto-generated constructor stub
		//this.baseDamage = ATTACK_DMG;
		//this.magicNumber = this.baseMagicNumber = CARD_TO_DRAW;
		this.exhaust = true;
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			//upgradeDamage(UPGRADE_PLUS_DMG);
			//upgradeMagicNumber(UPGRADE_DEBUFF);
			this.rawDescription = UPG_DESCRIPTION;
			this.exhaust = false;
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	//@SuppressWarnings("unchecked")
		//ArrayList<AbstractCard> cardsInHand = Arrays.copyOf(p.hand.group, p.hand.group.size());
    	
		//Create a deep copy of the hand.
		
		/*ArrayList<AbstractCard> cardsInHand = new ArrayList<AbstractCard>();
		AbstractCard tempCard = null; 
		for (int i = 0; i < p.hand.group.size(); i++) {
		    cardsInHand.add(p.hand.group.get(i).makeStatEquivalentCopy());
		}
		
		for (AbstractCard cardInHand: cardsInHand) {
    		if (p.hand.contains(cardInHand)) {
    			p.hand.moveToExhaustPile(cardInHand);
    			p.hand.removeCard(cardInHand);
    		}
    	}
		p.hand.refreshHandLayout();*/
		
    	/*AbstractCard cardToAdd;
    	for (int i=0; i<CARD_TO_ADD; i++) {
    		cardToAdd = AbstractDungeon.returnTrulyRandomCard().makeStatEquivalentCopy();
    		cardToAdd.setCostForTurn(0);
	    	if (this.upgraded) {
	    		cardToAdd.upgrade();
	    	}
	    	p.hand.addToHand(cardToAdd);
    	}*/
    	
    	ArrayList<AbstractCard> drawPile = p.drawPile.group;
    	ArrayList<AbstractCard> arraySpecialinDraw = new ArrayList<AbstractCard>();
    	for (AbstractCard cardInDraw : drawPile) {
    		if (cardInDraw.hasTag(CustomTags.SPECIAL)) {
    			arraySpecialinDraw.add(cardInDraw);
    		}	
    	}
    	
    	if (arraySpecialinDraw.size() > 0) {
    		if (arraySpecialinDraw.size() == 1) {
    			AbstractDungeon.actionManager.addToBottom(
    					new AddToHandAction(p.drawPile, arraySpecialinDraw.get(0), true, false, false, false, 0));
    		}
    		else {
    			AbstractDungeon.actionManager.addToBottom(
    					new ChooseCardAction(arraySpecialinDraw, false));
    		}
    		
    	}
    	
    	
    }
	
	public AbstractCard makeCopy() {
		return new Haku_Spellbook();
	}
	
	
}