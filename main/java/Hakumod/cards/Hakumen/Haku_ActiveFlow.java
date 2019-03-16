package Hakumod.cards.Hakumen;

import Hakumod.cards.Hakumen.Utils.Haku_CustomCard;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.powers.Haku_ActiveFlowPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
//import com.megacrit.cardcrawl.localization.CardStrings;
//import Hakumod.powers.MagatamaPower;
//import Hakumod.powers.MagatamaPower;
//import basemod.helpers.BaseModTags;
//import basemod.helpers.CardTags;


public class Haku_ActiveFlow extends Haku_CustomCard {

	public static final String ID = "Haku_ActiveFlow";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_ActiveFlow.png";
	private static final int COST = 1;

	private static final int ENERGY = 2;
	//private static final int UPGRADED_COST = 0;

	private static int MAGNITUDE = 1;
	private static int UPGRADE_MAGNITUDE = 1;
	    
	public Haku_ActiveFlow() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.SELF);
		// TODO Auto-generated constructor stub
		//this.exhaust = true;
		this.magicNumber = this.baseMagicNumber = MAGNITUDE;
		this.exhaust = true;
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			//this.isInnate = true;
			this.rawDescription = UPG_DESCRIPTION;
			//upgradeBaseCost(UPGRADED_COST);
			upgradeMagicNumber(UPGRADE_MAGNITUDE);
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	
    	//AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, DRAW));
    	
    	//AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Haku_ActiveFlowPower(p, this.magicNumber), this.magicNumber));

		if (p.cardsPlayedThisTurn >= 4) {
			AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(ENERGY));
			//AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, this.magicNumber));
			AbstractDungeon.actionManager.addToBottom(
					new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
		}

    }
	
	public AbstractCard makeCopy() {
		return new Haku_ActiveFlow();
	}
	
	
}