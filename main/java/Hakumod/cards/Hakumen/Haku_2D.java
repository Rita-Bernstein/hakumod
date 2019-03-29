package Hakumod.cards.Hakumen;

import Hakumod.action.ParryAction;
import Hakumod.action.UtilsApplyEffect;
import Hakumod.action.canUseParry;
import Hakumod.cards.Hakumen.Utils.Haku_CustomCard;
import Hakumod.cards.Hakumen.Utils.Haku_Parry;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Haku_2D extends Haku_Parry {
	public static final String ID = "Haku_2D";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	//public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_2D.png";
	
	private static final int COST = 1;
	private static final int BLOCK = 5;
	private static final int UPGRADE_PLUS_BLOCK = 2;
	
	private static final int ATTACK_DMG = 5;
	private static final int UPGRADE_PLUS_DMG = 2;
	
	/*private static int BUFF = 5;
	private static int UPGRADE_BUFF = 2;*/
	 
	
	public Haku_2D() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.ENEMY);
		
		this.baseDamage = ATTACK_DMG;
		this.baseBlock = BLOCK;
		//this.magicNumber = this.baseMagicNumber = BUFF;
		this.tags.add(CustomTags.PARRY);
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		//AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
		AbstractDungeon.actionManager.addToBottom(new ParryAction(p, null, m, UtilsApplyEffect.BLOCK, this.block));
		/*if (this.upgraded) {
			AbstractDungeon.actionManager.addToBottom(new ParryAction(p, null, m, "magatama", 1));
		}*/
		AbstractDungeon.actionManager.addToBottom(new ParryAction(p, null, m, UtilsApplyEffect.ATTACK, this.damage));
		AbstractDungeon.actionManager.addToBottom(new ParryAction(p, null, m, UtilsApplyEffect.NEXT_TURN_BLOCK, this.block));
	}


	
	public AbstractCard makeCopy() {
		return new Haku_2D();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
			upgradeDamage(UPGRADE_PLUS_DMG);
			//this.rawDescription = UPG_DESCRIPTION;
			//upgradeMagicNumber(UPGRADE_BUFF);
			initializeDescription();
		}
	}
}
