package Hakumod.cards.Hakumen;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
//import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Hakumod.action.ComboAction;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
//import Hakumod.powers.MagatamaPower;
import basemod.abstracts.CustomCard;


public class Haku_2B extends CustomCard{

	public static final String ID = "Haku_2B";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	//public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_2B.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 6;
	private static final int UPGRADE_PLUS_DMG = 2;
	private static int BUFF = 2;
	//private static int UPGRADE_DEBUFF = 1;
	    
	public Haku_2B() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.ATTACK,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.ENEMY);
		// TODO Auto-generated constructor stub
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = BUFF;
		this.tags.add(CustomTags.COMBO);
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			//upgradeMagicNumber(UPGRADE_DEBUFF);
			//this.rawDescription = UPG_DESCRIPTION;
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    	
    	AbstractCard c = new Haku_Renka().makeCopy();
    	AbstractDungeon.actionManager.addToBottom(new ComboAction(c));
    	
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
    	
    	/*AbstractCard c;
    	c = new Haku_Guren().makeCopy();
    	AbstractDungeon.actionManager.addToBottom(new ComboAction(c));*/
    	
    	/*if (this.upgraded) {
    		//AbstractDungeon.actionManager.addToTop(
    		//		new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new MagatamaPower(AbstractDungeon.player,1),1));
        	AbstractDungeon.actionManager.addToTop(
        			new StarterAction(p, this, m, "neutral", this.magicNumber));
    	}*/
   
    }
	
	public AbstractCard makeCopy() {
		return new Haku_2B();
	}
	
	
}