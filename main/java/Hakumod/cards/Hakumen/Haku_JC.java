package Hakumod.cards.Hakumen;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
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

import Hakumod.action.OkizemeAction;
import Hakumod.action.StarterAction;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
//import Hakumod.powers.MagatamaPower;
import basemod.abstracts.CustomCard;
import basemod.helpers.CardTags;


public class Haku_JC extends CustomCard{

	public static final String ID = "Haku_JC";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	//public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_JC.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 8;
	private static final int UPGRADE_PLUS_DMG = 2;
	private static int BUFF = 1;
	//private static int UPGRADE_BUFF = 1;
	    
	
	public Haku_JC() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.ATTACK,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ENEMY);
		// TODO Auto-generated constructor stub
		this.baseDamage = ATTACK_DMG;
		this.magicNumber  = this.baseMagicNumber = BUFF;
		//this.baseMagicNumber = DEBUFF;
		this.tags.add(CustomTags.AIR);
		this.tags.add(CustomTags.ENDER);
		//this.exhaust = true;
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			//upgradeMagicNumber(UPGRADE_BUFF);
			//this.rawDescription = UPG_DESCRIPTION;
			//initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    	
   
    	/*AbstractDungeon.actionManager.addToTop(
    			new OkizemeAction(p, this, m, "dexterity", this.magicNumber));
    	
    	AbstractDungeon.actionManager.addToTop(
    			new OkizemeAction(p, this, m, "strength", -this.magicNumber));*/
    	
    	AbstractDungeon.actionManager.addToTop(
    			new OkizemeAction(p, this, m, "defense", this.magicNumber));
    	
    }
	
	public AbstractCard makeCopy() {
		return new Haku_JC();
	}
	
	
}