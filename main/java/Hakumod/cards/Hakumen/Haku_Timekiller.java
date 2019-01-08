package Hakumod.cards.Hakumen;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
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
import com.megacrit.cardcrawl.powers.FadingPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import Hakumod.action.ComboAction;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.powers.Haku_MagatamaPower;
//import Hakumod.powers.MagatamaPower;
import basemod.abstracts.CustomCard;
//import basemod.helpers.BaseModTags;
//import basemod.helpers.CardTags;


public class Haku_Timekiller extends CustomCard{

	public static final String ID = "Haku_Timekiller";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	//public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_Timekiller.png";
	private static final int COST = 2;
	//private static final int ATTACK_DMG = 3;
	//private static final int UPGRADE_PLUS_DMG = 2;
	private static int DEBUFF = 5;
	private static int UPGRADE_DEBUFF = -1;
	
	private static int REDUCE = 2;
	
	
	public Haku_Timekiller() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.ATTACK,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.RARE,
				AbstractCard.CardTarget.ENEMY);
		// TODO Auto-generated constructor stub
		//this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = DEBUFF;
		this.exhaust = true;
		
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			//upgradeDamage(UPGRADE_PLUS_DMG);
			upgradeMagicNumber(UPGRADE_DEBUFF);
			//this.rawDescription = UPG_DESCRIPTION;
			//initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.FIRE));
   
    	if (m.hasPower("FadingPower")) {
    		AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ReducePowerAction(m, p, "FadingPower", REDUCE));
    	}
    	else {
    		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p,
    				new FadingPower(m, this.magicNumber), this.magicNumber));
    	}
    	

    }
	
	public AbstractCard makeCopy() {
		return new Haku_Timekiller();
	}
	
	
}