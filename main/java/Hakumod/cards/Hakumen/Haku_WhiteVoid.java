package Hakumod.cards.Hakumen;

import Hakumod.patches.AbstractCardEnum;
import Hakumod.powers.Haku_WhiteVoidPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
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


public class Haku_WhiteVoid extends CustomCard{

	public static final String ID = "Haku_WhiteVoid";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	//public static final String EXT_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_WhiteVoid.png";
	private static final int COST = 1;
	//private static final int UPGRADED_COST = 0;
	private static int COUNTER = 1;
	//private static int UPGRADE_DEBUFF = 1;
	    
	public Haku_WhiteVoid() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.POWER,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.RARE,
				AbstractCard.CardTarget.SELF);
		// TODO Auto-generated constructor stub
		this.magicNumber = this.baseMagicNumber = COUNTER;
		this.isEthereal = true;
		//this.exhaust = true;
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			this.isInnate = true;
			//upgradeMagicNumber(UPGRADE_DEBUFF);
			//upgradeBaseCost(UPGRADED_COST);
			//this.exhaust = false;
			this.rawDescription = UPG_DESCRIPTION;
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Haku_WhiteVoidPower(p, this.magicNumber), this.magicNumber));
    	/*AbstractCard copy = new Haku_WhiteVoid();
    	if (this.upgraded) {
    		copy.upgrade();
    	}
    	p.drawPile.addToTop(copy);*/
    }
	
	public AbstractCard makeCopy() {
		return new Haku_WhiteVoid();
	}
	
	
}