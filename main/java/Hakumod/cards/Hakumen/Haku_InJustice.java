package Hakumod.cards.Hakumen;

import Hakumod.cards.Hakumen.Utils.Haku_CustomCard;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.powers.Haku_MagatamaPower;
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


public class Haku_InJustice extends Haku_CustomCard {

	public static final String ID = "Haku_InJustice";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	//public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_InJustice.png";
	private static final int COST = 6;
	private static final int UPGRADED_COST = 5;
	private static final int ATTACK_DMG = 40;
	private static final int UPGRADE_PLUS_DMG = 10;
	private static int BUFF = 4;
	//private static int UPGRADE_BUFF = 1;
	    
	
	public Haku_InJustice() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.ATTACK,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.RARE,
				AbstractCard.CardTarget.ENEMY);
		// TODO Auto-generated constructor stub
		this.baseDamage = ATTACK_DMG;
		this.magicNumber  = this.baseMagicNumber = BUFF;
		//this.baseMagicNumber = DEBUFF;
		//this.tags.add(CustomTags.AIR);
		//this.tags.add(CustomTags.ENDER);
		//this.exhaust = true;
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			//upgradeMagicNumber(UPGRADE_BUFF);
			upgradeBaseCost(UPGRADED_COST);
			//this.rawDescription = UPG_DESCRIPTION;
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

	    
    	AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    	
		AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p,
				new Haku_MagatamaPower(p, this.magicNumber), this.magicNumber));
		
    	/*AbstractDungeon.actionManager.addToTop(
    			new StarterAction(p, this, m, "neutral", this.magicNumber));*/
    	
    }
	
	public AbstractCard makeCopy() {
		return new Haku_InJustice();
	}
	
	
}