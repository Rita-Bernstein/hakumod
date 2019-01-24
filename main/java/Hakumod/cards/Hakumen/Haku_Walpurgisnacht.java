package Hakumod.cards.Hakumen;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
//import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import Hakumod.patches.AbstractCardEnum;
//import Hakumod.powers.MagatamaPower;
//import Hakumod.powers.MagatamaPower;
import basemod.abstracts.CustomCard;
//import basemod.helpers.BaseModTags;
//import basemod.helpers.CardTags;


public class Haku_Walpurgisnacht extends CustomCard{

	public static final String ID = "Haku_Walpurgisnacht";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	//public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_Walpurgisnacht.png";
	private static final int BUFF = 3;
	private static final int COST = 3;
	private static final int UPGRADED_COST = 2;
	    
	public Haku_Walpurgisnacht() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.SKILL,
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
			upgradeBaseCost(UPGRADED_COST);
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	/*int i = 0;
    	ArrayList<AbstractPower> listPowers = new ArrayList<AbstractPower>();
    	
    	
    	for (AbstractPower pow : p.powers) {
			if (pow.type == AbstractPower.PowerType.BUFF || (this.upgraded && pow.type == AbstractPower.PowerType.DEBUFF)
					&& pow.ID != "MagatamaPower"
					&& pow.ID != "StrengthPower"
				) {
					listPowers.add(pow);
					i += (pow.amount > 0) ? pow.amount : -pow.amount;
				}
		}
		
		for (AbstractPower power : listPowers) {
			AbstractDungeon.actionManager.addToBottom(
					new RemoveSpecificPowerAction(p, p, power));
		}
    	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, i), i));
    	*/
    	
    	int energyGained = 0;
    	if (p.hasPower("Haku_MagatamaPower")) {
    		energyGained = p.getPower("Haku_MagatamaPower").amount;
    	}
    	
    	AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, "Haku_MagatamaPower"));
    	
    	AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(energyGained));	
    	//AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, this.magicNumber));
    	
    }
	
	public AbstractCard makeCopy() {
		return new Haku_Walpurgisnacht();
	}
	
	
}