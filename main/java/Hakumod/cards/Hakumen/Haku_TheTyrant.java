package Hakumod.cards.Hakumen;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
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
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.DexterityPower;

import Hakumod.action.ComboAction;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.powers.Haku_ActiveFlowPower;
import Hakumod.powers.Haku_ScienceFictionPower;
import Hakumod.powers.Haku_AwakeningPower;
import Hakumod.powers.Haku_YomotsuhirasakaPower;
import Hakumod.powers.Haku_OverdrivePower;
import Hakumod.powers.Haku_EmptySkyPower;
import Hakumod.powers.Haku_TheTyrantPower;
//import Hakumod.powers.MagatamaPower;
//import Hakumod.powers.MagatamaPower;
import basemod.abstracts.CustomCard;
//import basemod.helpers.BaseModTags;
//import basemod.helpers.CardTags;


public class Haku_TheTyrant extends CustomCard{

	public static final String ID = "Haku_TheTyrant";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	//public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_TheTyrant.png";
	private static final int COST = 3;
	private static final int UPGRADED_COST = 2;

	private static int BUFF = 1;
	//private static int UPGRADED_BUFF = 1;
    
	public Haku_TheTyrant() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.POWER,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.RARE,
				AbstractCard.CardTarget.SELF);
		// TODO Auto-generated constructor stub
		
		this.magicNumber = this.baseMagicNumber = BUFF;
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			//upgradeMagicNumber(UPGRADED_BUFF);
			upgradeBaseCost(UPGRADED_COST);
			//this.rawDescription = UPG_DESCRIPTION;
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	
    	/*int energyConsumed = this.energyOnUse;
		if (p.hasRelic("Chemical X")) {
			p.getRelic("Chemical X").flash();
			energyConsumed += 2;
		}
		
    	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
				new DexterityPower(p, energyConsumed+this.magicNumber), energyConsumed+this.magicNumber));
    	
    	AbstractDungeon.actionManager.addToBottom(new LoseEnergyAction(this.energyOnUse));
        */
    	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Haku_TheTyrantPower(p, this.magicNumber), this.magicNumber));
    }
	
	public AbstractCard makeCopy() {
		return new Haku_TheTyrant();
	}
}