package Hakumod.cards.Hakumen;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Hakumod.action.ParryAction;
import Hakumod.action.UtilsApplyEffect;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
import basemod.abstracts.CustomCard;

public class Haku_6D extends CustomCard{
	public static final String ID = "Haku_6D";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	//public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_6D.png";
	
	private static final int COST = 1;
	private static final int BLOCK = 4;
	private static final int UPGRADE_PLUS_BLOCK = 2;
	
	private static final int ATTACK_DMG = 6;
	private static final int UPGRADE_PLUS_DMG = 2;
	
	private static int BUFF = 1;
	
	public Haku_6D() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ENEMY);
		this.baseDamage = ATTACK_DMG;
		this.baseBlock = BLOCK;
		this.magicNumber = this.baseMagicNumber = BUFF;
		this.tags.add(CustomTags.PARRY);
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ParryAction(p, null, m, UtilsApplyEffect.BLOCK, this.block));		
		AbstractDungeon.actionManager.addToBottom(new ParryAction(p, null, m, UtilsApplyEffect.ATTACK, this.damage));
		AbstractDungeon.actionManager.addToBottom(new ParryAction(p, null, m, UtilsApplyEffect.MAGATAMA, this.magicNumber));
	}
	
	public AbstractCard makeCopy() {
		return new Haku_6D();
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
