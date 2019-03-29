package Hakumod.cards.Hakumen;

import Hakumod.action.OkizemeAction;
import Hakumod.action.UtilsApplyEffect;
import Hakumod.cards.Hakumen.Utils.Haku_CustomCard;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
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


public class Haku_3C extends Haku_CustomCard {

	public static final String ID = "Haku_3C";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	//public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_3C.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 7;
	private static final int UPGRADE_PLUS_DMG = 2;
	private static int DEBUFF = 1;
	private static int UPGRADE_DEBUFF = 1;
	    
	
	public Haku_3C() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.ATTACK,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.ENEMY);
		// TODO Auto-generated constructor stub
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = DEBUFF;
		this.baseMagicNumber = DEBUFF;
		this.tags.add(CustomTags.ENDER);
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			upgradeMagicNumber(UPGRADE_DEBUFF);
			//this.rawDescription = UPG_DESCRIPTION;
			//initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    	
   
    	AbstractDungeon.actionManager.addToTop(
    			new OkizemeAction(p, this, m, UtilsApplyEffect.WEAK, this.magicNumber));
    		
    	
    }
	
	public AbstractCard makeCopy() {
		return new Haku_3C();
	}
	
	
}