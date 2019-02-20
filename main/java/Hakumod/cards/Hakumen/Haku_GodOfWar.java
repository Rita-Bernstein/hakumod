package Hakumod.cards.Hakumen;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
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
import Hakumod.patches.CustomTags;
//import Hakumod.powers.MagatamaPower;
//import Hakumod.powers.MagatamaPower;
import basemod.abstracts.CustomCard;
//import basemod.helpers.BaseModTags;
//import basemod.helpers.CardTags;


public class Haku_GodOfWar extends CustomCard{

	public static final String ID = "Haku_GodOfWar";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_GodOfWar.png";
	
	private static final int ATTACK_DMG = 10;
	private static final int UPGRADE_PLUS_DMG = 3;
	
	private static final int COST = 1;
	//private static final int UPGRADED_COST = 0;
	

	private static int BUFF = 5;
	private static int UPGRADED_BUFF = 2;
    
	public Haku_GodOfWar() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.ATTACK,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.RARE,
				AbstractCard.CardTarget.ENEMY);
		// TODO Auto-generated constructor stub
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = BUFF;
		this.exhaust = true;
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			//upgradeBaseCost(UPGRADED_COST);
			upgradeDamage(UPGRADE_PLUS_DMG);
			upgradeMagicNumber(UPGRADED_BUFF);
			this.exhaust = false;
			//this.isInnate = true;
			this.rawDescription = UPG_DESCRIPTION;
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	/*AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Haku_EmptySkyPower(p, this.magicNumber), this.magicNumber));
    
    	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
				new StrengthPower(p, this.magicNumber), this.magicNumber));
    	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
				new DexterityPower(p, -3), -3));*/
    	
    	AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    	
		for (AbstractCard cardIsSpecial: p.hand.group) {
			if (cardIsSpecial.hasTag(CustomTags.SPECIAL)) {
				cardIsSpecial.setCostForTurn(0);
			}
		}
    	
    }
	
	public AbstractCard makeCopy() {
		return new Haku_GodOfWar();
	}
}