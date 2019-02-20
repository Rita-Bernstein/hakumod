package Hakumod.cards.Hakumen;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
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

import Hakumod.patches.AbstractCardEnum;
import Hakumod.powers.Haku_MagatamaPower;
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
	private static final int BUFF = 8;
	private static final int UPGRADE_BUFF = 1;
	
	
	private static final int COST = 3;
	//private static final int UPGRADED_COST = 2;
	
	private static final int ATTACK_DMG = 0;
	//private static final int UPGRADE_PLUS_DMG = 2;
	    
	public Haku_Walpurgisnacht() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.ATTACK,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.RARE,
				AbstractCard.CardTarget.ENEMY);
		// TODO Auto-generated constructor stub
		this.magicNumber = this.baseMagicNumber = BUFF;
		this.baseDamage = ATTACK_DMG;
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			//upgradeBaseCost(UPGRADED_COST);
			upgradeMagicNumber(UPGRADE_BUFF);
			initializeDescription();
		}
	}
	
	/*@Override
	public void applyPowers() {
		int amountOfMagatama = 0;
		if (AbstractDungeon.player.hasPower(Haku_MagatamaPower.POWER_ID)) 
		{
			amountOfMagatama = AbstractDungeon.player.getPower(Haku_MagatamaPower.POWER_ID).amount;
		}
		this.damage = amountOfMagatama * this.magicNumber + this.baseDamage;
	 }*/
	
	/* (non-Javadoc)
	 * @see com.megacrit.cardcrawl.cards.AbstractCard#calculateDamageDisplay(com.megacrit.cardcrawl.monsters.AbstractMonster)
	 */
	@Override
	public void calculateDamageDisplay(AbstractMonster mo) {
		// TODO Auto-generated method stub
		calculateCardDamage(mo);
	}	

    /* (non-Javadoc)
	 * @see com.megacrit.cardcrawl.cards.AbstractCard#calculateCardDamage(com.megacrit.cardcrawl.monsters.AbstractMonster)
	 */
	@Override
	public void calculateCardDamage(AbstractMonster mo) {
		// TODO Auto-generated method stub
		int amountOfMagatama = 0;

		if (AbstractDungeon.player.hasPower(Haku_MagatamaPower.POWER_ID)) 
		{
			amountOfMagatama = AbstractDungeon.player.getPower(Haku_MagatamaPower.POWER_ID).amount;
		}
		this.damage = amountOfMagatama * this.magicNumber + this.baseDamage;
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
    	
    	/*int energyGained = 0;
    	if (p.hasPower(Haku_MagatamaPower.POWER_ID)) {
    		energyGained = p.getPower(Haku_MagatamaPower.POWER_ID).amount;
    	}
    	
    	AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, Haku_MagatamaPower.POWER_ID));
    	
    	AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(energyGained));	
    	//AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, this.magicNumber));
    	*/
		AbstractDungeon.actionManager.addToBottom(
		          new DamageAction(
		              m,
		              new DamageInfo(p, this.damage),
		              AbstractGameAction.AttackEffect.SLASH_HEAVY
		          )
		);
    	
    }
	
	public AbstractCard makeCopy() {
		return new Haku_Walpurgisnacht();
	}
	
	
}