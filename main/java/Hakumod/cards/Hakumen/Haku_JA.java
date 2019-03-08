package Hakumod.cards.Hakumen;

import Hakumod.cards.Hakumen.Utils.Haku_CustomCard;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
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


public class Haku_JA extends Haku_CustomCard {

	public static final String ID = "Haku_JA";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	//public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_JA.png";
	private static final int COST = 0;
	private static final int ATTACK_DMG = 3;
	private static final int UPGRADE_PLUS_DMG = 2;
	//private static int DEBUFF = 1;
	//private static int UPGRADE_DEBUFF = 1;
	    
	public Haku_JA() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.ATTACK, 
				AbstractCardEnum.HAKUMEN_COLOR, 
				AbstractCard.CardRarity.COMMON, 
				AbstractCard.CardTarget.ENEMY);
		// TODO Auto-generated constructor stub
		this.baseDamage = ATTACK_DMG;
		//this.magicNumber = this.baseMagicNumber = DEBUFF;
		this.tags.add(CustomTags.AIR);
		this.exhaust = true;
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			//this.exhaust = false;
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			//this.exhaust = false;
			//upgradeMagicNumber(UPGRADE_DEBUFF);
			//this.exhaust = false;
			//this.rawDescription = UPG_DESCRIPTION;
			initializeDescription();
			
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    	
    	ArrayList<AbstractCard> array0Cost = new ArrayList<AbstractCard>();
    	for (AbstractCard cardInHand : p.hand.group) {
    		if ( (cardInHand.cost == 0 || cardInHand.costForTurn == 0) && !(cardInHand instanceof Haku_JA) ) {
    			array0Cost.add(cardInHand);
    		}	
    	}
    	
    	if (array0Cost.size() > 0){
	    	AbstractCard cardToDuplicate = array0Cost.get( (int) Math.floor((Math.random() * array0Cost.size())) ); 
	    	AbstractCard copy = cardToDuplicate.makeStatEquivalentCopy();
	    	p.hand.addToHand(copy);
    	}
    	/*ArrayList<AbstractCard> arrayAirinDraw = new ArrayList<AbstractCard>();
    	for (AbstractCard cardInDraw : p.drawPile.group) {
    		if (cardInDraw.hasTag(CustomTags.AIR)) {
    			arrayAirinDraw.add(cardInDraw);
    		}
    	}
    	
    	if (arrayAirinDraw.size() > 0) {
    		if (arrayAirinDraw.size() == 1) {
    			AbstractDungeon.actionManager.addToBottom(
    					new AddToHandAction(p.drawPile, (AbstractCard) arrayAirinDraw.get(0), true, false, false, true, 0));
    		}
    		else {
    			AbstractDungeon.actionManager.addToBottom(
    					new ChooseCardAction(arrayAirinDraw, false, false, true, 0, true));
    		}
    		
    	}*/
    }
	
	public AbstractCard makeCopy() {
		return new Haku_JA();
	}	
}
