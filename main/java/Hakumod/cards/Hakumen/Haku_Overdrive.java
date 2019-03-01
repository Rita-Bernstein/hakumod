package Hakumod.cards.Hakumen;

import Hakumod.patches.AbstractCardEnum;
import Hakumod.powers.Haku_OverdrivePower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
//import com.megacrit.cardcrawl.localization.CardStrings;
//import Hakumod.powers.MagatamaPower;
//import Hakumod.powers.MagatamaPower;
//import basemod.helpers.BaseModTags;
//import basemod.helpers.CardTags;


public class Haku_Overdrive extends CustomCard{

	public static final String ID = "Haku_Overdrive";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_Overdrive.png";
	private static final int COST = 1;

	private static int MAGNITUDE = 2;
	//private static int UPGRADED_MAGNITUDE = 1;
	    
	public Haku_Overdrive() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.POWER,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.SELF);
		// TODO Auto-generated constructor stub
		
		this.magicNumber = this.baseMagicNumber = MAGNITUDE;
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			this.isInnate = true;
			this.rawDescription = UPG_DESCRIPTION;
			//upgradeMagicNumber(UPGRADED_MAGNITUDE);
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Haku_OverdrivePower(p, this.magicNumber), this.magicNumber));
    	AbstractCard cardEA = new Haku_EA().makeCopy();
    	AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(cardEA, 1, true, false));
    }
	
	public AbstractCard makeCopy() {
		return new Haku_Overdrive();
	}
}