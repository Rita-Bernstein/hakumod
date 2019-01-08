package Hakumod.cards.Hakumen;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
//import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Hakumod.action.AddToHandAction;
import Hakumod.action.ChooseCardAction;
import Hakumod.action.ComboAction;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
import Hakumod.powers.Haku_MagatamaPower;
//import Hakumod.powers.MagatamaPower;
import basemod.abstracts.CustomCard;
//import basemod.helpers.BaseModTags;
//import basemod.helpers.CardTags;
import basemod.helpers.CardTags;


public class Haku_Backhop extends CustomCard{

	public static final String ID = "Haku_Backhop";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_Backhop.png";
	private static final int COST = 0;
	//private static final int UPG_COST = 1;
	//private static final int ATTACK_DMG = 7;
	//private static final int UPGRADE_PLUS_DMG = 2;
	//private static int DRAW = 2;
	//private static int UPGRADE_DEBUFF = 1;
	    
	public Haku_Backhop() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.UNCOMMON, 
				AbstractCard.CardTarget.SELF);
		// TODO Auto-generated constructor stub
		this.exhaust = true;
		//this.baseDamage = ATTACK_DMG;
		//this.magicNumber = this.baseMagicNumber = DRAW;
		//CardTags.addTags(this, CustomTags.AIR);
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			//this.isInnate = true;
			//upgradeDamage(UPGRADE_PLUS_DMG);
			//upgradeMagicNumber(UPGRADE_DEBUFF);
			this.rawDescription = UPG_DESCRIPTION;
			initializeDescription();
		}
	}
	
	public boolean hasANonBackHopSkill(CardGroup group) {
		for (AbstractCard c: group.group) {
			if (! (c instanceof Haku_Backhop) ) {
				return true;
			}	
		}
		return false;
	}
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	AbstractCard randomCard = new Haku_Backhop().makeCopy();
    	
    	CardGroup skillsInHand = p.hand.getSkills();
    		
    	if (hasANonBackHopSkill(skillsInHand)) {
    		while ((randomCard instanceof Haku_Backhop)) {
    			randomCard = p.hand.getRandomCard(CardType.SKILL, true);
    		}
	    	AbstractCard copy = randomCard.makeCopy();
	    	AbstractCard forwardHop = new Haku_Forwardhop().makeCopy();
	    	
	    	if (this.upgraded) {
	    		copy.upgrade();
	    		forwardHop.upgrade();
	    	}
	    	p.drawPile.addToRandomSpot(forwardHop);
	    	p.hand.addToHand(copy);
    	}
	    	/*ArrayList<AbstractCard> array4CinDraw = new ArrayList<AbstractCard>();
    	AbstractCard card4C = new Haku_4C().makeCopy();
    	for (AbstractCard cardInDraw : p.drawPile.group) {
    		if (cardInDraw.cardID == card4C.cardID) {
    			array4CinDraw.add(cardInDraw);
    		}
    	}
    	
    	if (array4CinDraw.size() > 0) {
    		if (array4CinDraw.size() == 1) {
    			AbstractDungeon.actionManager.addToBottom(
    					new AddToHandAction(p.drawPile, (AbstractCard) array4CinDraw.get(0), true, false, false, true, 0));
    		}
    		else {
    			AbstractDungeon.actionManager.addToBottom(
    					new ChooseCardAction(array4CinDraw, false, false, true, 0, true));
    		}
    		
    	}*/
    }
	
	public AbstractCard makeCopy() {
		return new Haku_Backhop();
	}
	
	
}