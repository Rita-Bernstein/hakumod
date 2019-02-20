package Hakumod.cards.Hakumen;

//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
//import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
//import Hakumod.powers.MagatamaPower;
import basemod.abstracts.CustomCard;


public class Haku_FatalJudge extends CustomCard{

	public static final String ID = "Haku_FatalJudge";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_FatalJudge.png";
	private static final int COST = 2;
	//private static final int UPGRADED_COST = 2;
	private static int BUFF = 3;
	private static int UPGRADE_BUFF = 1;
	    
	public Haku_FatalJudge() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.RARE,
				AbstractCard.CardTarget.SELF);
		// TODO Auto-generated constructor stub
		this.magicNumber = this.baseMagicNumber = BUFF;
		this.exhaust = true;
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			this.exhaust = false;
			upgradeMagicNumber(UPGRADE_BUFF);
			//upgradeBaseCost(UPGRADED_COST);
			this.rawDescription = UPG_DESCRIPTION;
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	//AbstractDungeon.actionManager.addToTop(new DrawCardAction(p, this.magicNumber));
		p.draw(this.magicNumber);
		//AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));	
     	
    	for (AbstractCard cardInHand: p.hand.group) {
			if (cardInHand.hasTag(CustomTags.COMBO) || cardInHand.hasTag(CustomTags.ENDER) || cardInHand.hasTag(CustomTags.STARTER)) {
				cardInHand.setCostForTurn(0);
				//AbstractDungeon.actionManager.addToBottom(new ReduceCostAction(uuid, baseBlock));	
			}
		}
    }
	
	public AbstractCard makeCopy() {
		return new Haku_FatalJudge();
	}
	
	
}