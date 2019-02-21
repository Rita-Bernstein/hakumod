package Hakumod.cards.Hakumen;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
//import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Hakumod.action.UpgradeHandAction;
import Hakumod.patches.AbstractCardEnum;
//import Hakumod.powers.MagatamaPower;
import basemod.abstracts.CustomCard;


public class Haku_FC extends CustomCard{

	public static final String ID = "Haku_FC";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_FC.png";
	private static final int COST = 0;
	//private static final int UPGRADED_COST = 0;
	//private static int DEBUFF = 1;
	//private static int UPGRADE_DEBUFF = 1;
	    
	public Haku_FC() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.SELF);
		// TODO Auto-generated constructor stub
		//this.magicNumber = this.baseMagicNumber = DEBUFF;
		this.exhaust = true;
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			//upgradeMagicNumber(UPGRADE_DEBUFF);
			//upgradeBaseCost(UPGRADED_COST);
			//this.exhaust = false;
			this.rawDescription = UPG_DESCRIPTION;
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	
    	if (this.upgraded) {
    		AbstractDungeon.actionManager.addToTop(new DrawCardAction(AbstractDungeon.player, 1));
    	}
		AbstractDungeon.actionManager.addToBottom(new UpgradeHandAction(AbstractDungeon.player));		
		
    }
	
	public AbstractCard makeCopy() {
		return new Haku_FC();
	}
	
	
}