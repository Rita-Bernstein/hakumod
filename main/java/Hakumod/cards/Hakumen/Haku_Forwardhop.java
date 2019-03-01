package Hakumod.cards.Hakumen;

import Hakumod.patches.AbstractCardEnum;
import Hakumod.powers.Haku_OffensePower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
//import com.megacrit.cardcrawl.localization.CardStrings;
//import Hakumod.powers.MagatamaPower;


public class Haku_Forwardhop extends CustomCard{

	public static final String ID = "Haku_Forwardhop";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_Forwardhop.png";
	private static final int COST = 0;
	//private static final int ATTACK_DMG = 7;
	//private static final int UPGRADE_PLUS_DMG = 2;
	private static int BUFF = 1;
	private static int UPGRADE_BUFF = 1;
	    
	public Haku_Forwardhop() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.SELF);
		// TODO Auto-generated constructor stub
		//this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = BUFF;
		//CardTags.addTags(this, CustomTags.AIR);
		this.exhaust = true;
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			//this.isInnate = true;
			//upgradeDamage(UPGRADE_PLUS_DMG);
			upgradeMagicNumber(UPGRADE_BUFF);
			this.rawDescription = UPG_DESCRIPTION;
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	
    	/*AbstractCard randomCard = p.hand.getRandomCard(CardType.ATTACK, true);
    	
    	AbstractCard copy;
    	if (randomCard != null) {
    		copy = randomCard.makeCopy();
    		if (this.upgraded) {
        		copy.upgrade();
    		}
    		p.hand.addToHand(copy);
    	}*/
    	
    	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
				new Haku_OffensePower(p, this.magicNumber, false), this.magicNumber));
    	
    	AbstractCard backHop = new Haku_Backhop().makeCopy();
    	if (this.upgraded) {
    		backHop.upgrade();
    	}
    	//p.drawPile.addToRandomSpot(backHop);
    	AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(backHop, 1));
		
    	 
    	/*ArrayList<AbstractCard> drawPile = p.drawPile.group;
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
    		
    	}*/
    }
	
	public AbstractCard makeCopy() {
		return new Haku_Forwardhop();
	}
	
	
}