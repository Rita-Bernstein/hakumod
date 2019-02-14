package Hakumod.cards.Hakumen;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
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

import Hakumod.action.ComboAction;
import Hakumod.action.UsingSpecialAction;
import Hakumod.action.UtilsApplyEffect;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
import basemod.abstracts.CustomCard;
//import basemod.helpers.BaseModTags;
//import basemod.helpers.CardTags;
import basemod.helpers.CardTags;

public class Haku_Kishuu extends Haku_Special{

	public static final String ID = "Haku_Kishuu";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	//public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_Kishuu.png";
	private static final int COST = 0;
	//private static final int ATTACK_DMG = 5;
	//private static final int UPGRADE_PLUS_DMG = 2;
	
	public final static int MAGATAMA_COST = 1;
	private static final int BLOCK = 4;
	private static final int UPGRADE_PLUS_BLOCK = 2;
	
	private static final int BUFF = 2;
	private static final int UPGRADE_BUFF = 1;
	
	public Haku_Kishuu() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.SELF,
				MAGATAMA_COST);
		// TODO Auto-generated constructor stub
		//this.baseDamage = ATTACK_DMG;
		this.baseBlock = BLOCK;
		this.magicNumber = this.baseMagicNumber = BUFF;
		this.tags.add(CustomTags.SPECIAL);
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			//upgradeDamage(UPGRADE_PLUS_DMG);
			upgradeBlock(UPGRADE_PLUS_BLOCK);
			upgradeMagicNumber(UPGRADE_BUFF);
			
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
			
		AbstractDungeon.actionManager.addToBottom( new UsingSpecialAction(p, MAGATAMA_COST));	
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
		
		//new UtilsApplyEffect(p, this, m, "defense", this.magicNumber);
		AbstractCard c = new Haku_Enma().makeCopy();
    	AbstractDungeon.actionManager.addToBottom(new ComboAction(c));
    	
    }
	
	public AbstractCard makeCopy() {
		return new Haku_Kishuu();
	}
}