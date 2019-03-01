package Hakumod.cards.Hakumen;

import Hakumod.action.UsingSpecialAction;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
import Hakumod.powers.Haku_MugenPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
//import com.megacrit.cardcrawl.localization.CardStrings;


public class Haku_Mugen extends Haku_Special{

	public static final String ID = "Haku_Mugen";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_Mugen.png";
	private static final int COST = 3;

	private static int CARD_TO_DRAW = 1;
	private static int MAGATAMA_COST = 8;
	
	public Haku_Mugen() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.POWER, 
				AbstractCardEnum.HAKUMEN_COLOR, 
				AbstractCard.CardRarity.RARE, 
				AbstractCard.CardTarget.SELF,
				MAGATAMA_COST);
		// TODO Auto-generated constructor stub
		this.tags.add(CustomTags.SPECIAL);
		this.magicNumber = this.baseMagicNumber = CARD_TO_DRAW;
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = UPG_DESCRIPTION;
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		
		AbstractDungeon.actionManager.addToBottom( new UsingSpecialAction(p, MAGATAMA_COST));
    	int count = 0;
    	int randomCardNum;
    	AbstractCard cardSpecial;
    	
    	if (this.upgraded) {count++;}
    	AbstractCard [] randomSpecialPool = {
    			//new Haku_Kishuu().makeCopy(),
    			new Haku_Enma().makeCopy(),
    			new Haku_Guren().makeCopy(),
    			new Haku_Renka().makeCopy(),
    			new Haku_Zantetsu().makeCopy(),
    			new Haku_Yukikaze().makeCopy(),
    			new Haku_Shippu().makeCopy(),
    			new Haku_Agito().makeCopy(),
    			new Haku_Hotaru().makeCopy(),
    			new Haku_Tsubaki().makeCopy()}; 
    	
    	@SuppressWarnings("unchecked")
		ArrayList<AbstractCard> cardsInHand = (ArrayList<AbstractCard>) p.hand.group.clone();
    	for (AbstractCard cardInHand : cardsInHand) {
    		if (! (cardInHand.hasTag(CustomTags.SPECIAL ))) {
    			cardInHand.moveToDiscardPile();
    			p.hand.removeCard(cardInHand);
    			count++;	
    			p.hand.refreshHandLayout();
    		}
    		else {
    			cardInHand.setCostForTurn(0);
    		}
    	}
    
    	for (int i=0; i<count; i++) {
    		if (p.hand.size() == 10) {break;}
    		randomCardNum = (int) Math.floor(Math.random()*randomSpecialPool.length);
    		cardSpecial = randomSpecialPool[randomCardNum].makeCopy();
    		cardSpecial.setCostForTurn(0);
    		p.hand.addToHand(cardSpecial);	
    		cardSpecial.lighten(false);
    		p.hand.refreshHandLayout();
    	}
    	
    	
    	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Haku_MugenPower(p, 1, false), 1));
    	
    }
	
	public AbstractCard makeCopy() {
		return new Haku_Mugen();
	}
	
	
}