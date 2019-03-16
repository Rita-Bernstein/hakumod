package Hakumod.cards.Hakumen;

import Hakumod.action.OkizemeAction;
import Hakumod.action.StarterAction;
import Hakumod.action.UsingSpecialAction;
import Hakumod.action.UtilsApplyEffect;
import Hakumod.cards.Hakumen.Utils.Haku_Special;
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


public class Haku_Agito extends Haku_Special {

	public static final String ID = "Haku_Agito";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	//public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_Agito.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 8;
	private static final int UPGRADE_PLUS_DMG = 3;
	private static int BUFF = 2;
	//private static int UPGRADE_BUFF = 1;
	
	public static final int MAGATAMA_COST = 1;
	
	public Haku_Agito() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.ATTACK,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.ENEMY,
				MAGATAMA_COST);
		// TODO Auto-generated constructor stub
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = BUFF;
		//this.baseMagicNumber = DEBUFF;

		this.tags.add(CustomTags.SPECIAL);
		this.tags.add(CustomTags.STARTER);
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
    		
    		AbstractDungeon.actionManager.addToBottom( new UsingSpecialAction(p, MAGATAMA_COST));
    		AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    	
   
    		/*AbstractDungeon.actionManager.addToTop(
    			new OkizemeAction(p, this, m, "strength", this.magicNumber));*/
    		
        	/*AbstractDungeon.actionManager.addToTop(
        			new OkizemeAction(p, this, m, "dexterity", -this.magicNumber));*/
    		AbstractDungeon.actionManager.addToTop(
        			new StarterAction(p, this, m, UtilsApplyEffect.OFFENSE, this.magicNumber));
    	
    		AbstractDungeon.actionManager.addToTop(
        			new OkizemeAction(p, this, m, UtilsApplyEffect.OFFENSE, this.magicNumber));
    	
    }
	
	public AbstractCard makeCopy() {
		return new Haku_Agito();
	}
	
	
}