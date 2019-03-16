package Hakumod.cards.Hakumen;

import Hakumod.action.OkizemeAction;
import Hakumod.action.StarterAction;
import Hakumod.action.UtilsApplyEffect;
import Hakumod.cards.Hakumen.Utils.Haku_CustomCard;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.powers.Haku_ActiveFlowPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
//import com.megacrit.cardcrawl.localization.CardStrings;
//import Hakumod.powers.MagatamaPower;


public class Haku_EA extends Haku_CustomCard {

	public static final String ID = "Haku_EA";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	//public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_EA.png";
	private static final int COST = 0;
	private static final int ATTACK_DMG = 0;
	private static final int UPGRADE_PLUS_DMG = 2;
	private static final int BLOCK = 0;
	private static final int UPGRADE_PLUS_BLOCK = 2;
	//private static int DEBUFF = 1;
	//private static int UPGRADE_DEBUFF = 1;
	    
	public Haku_EA() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.ATTACK,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ENEMY);
		// TODO Auto-generated constructor stub
		this.baseDamage = ATTACK_DMG;
		this.baseBlock = BLOCK;
		//this.magicNumber = this.baseMagicNumber = DEBUFF;
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			upgradeBlock(UPGRADE_PLUS_BLOCK);
		}
	}

	public int getBlock(){
		return AbstractDungeon.player.hand.size() + this.baseBlock;
	}

	public int getDamage() {
		return  AbstractDungeon.player.cardsPlayedThisTurn + this.baseDamage;
	}
	//Display damage when the card is in the hand.
	@Override
	public void applyPowers() {
		this.damage = getDamage();
		this.block = getBlock();
		if (this.damage > 0) {this.isDamageModified = true;}
		if (this.block > 0) {this.isBlockModified = true;}
	}

	@Override
	public void calculateDamageDisplay(AbstractMonster mo) {
		// TODO Auto-generated method stub
		calculateCardDamage(mo);
	}

	//Display damage when the card is selected.
	@Override
	public void calculateCardDamage(AbstractMonster mo) {
		this.damage = getDamage();
		this.block = getBlock();
		if (this.damage > 0) {this.isDamageModified = true;}
		if (this.block > 0) {this.isBlockModified = true;}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

		AbstractDungeon.actionManager.addToBottom(new OkizemeAction(p, this, m, UtilsApplyEffect.ATTACK, this.damage));
		AbstractDungeon.actionManager.addToBottom(new StarterAction(p, this, m, UtilsApplyEffect.BLOCK, this.block));


		//int EAdamage = p.hasPower("ActiveFlowPower") ? this.damage*2 : this.damage ;
    	/*if (p.hasPower(Haku_ActiveFlowPower.POWER_ID)) {
	    	AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
					new DamageInfo(p, this.damage*2, this.damageTypeForTurn),
					AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    	}
    	else {
    		AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
					new DamageInfo(p, this.damage, this.damageTypeForTurn),
					AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Haku_ActiveFlowPower(p, 3), 3));
    	}*/
    }
	
	public AbstractCard makeCopy() {
		return new Haku_EA();
	}
	
	
}