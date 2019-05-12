package Hakumod.cards.Hakumen.parry;

import Hakumod.action.ParryAction;
import Hakumod.action.UtilsApplyEffect;
import Hakumod.cards.Hakumen.abstracts.ParryCard;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RejectGuard extends ParryCard {
	public static final String ID = makeCardID(RejectGuard.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	
	private static final int COST = 1;
	private static final int BLOCK = 6;
	private static final int UPGRADE_PLUS_BLOCK = 2;
	private static final int ATTACK_DMG = 4;
	private static final int UPGRADE_PLUS_DMG = 2;
	private static final int BUFF = 1;
	
	public RejectGuard() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.ENEMY);
		this.baseBlock = BLOCK;
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = BUFF;
		this.tags.add(CustomTags.PARRY);
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		act(new ParryAction(p, null, m, UtilsApplyEffect.BLOCK, this.block));
		act(new ParryAction(p, null, m, UtilsApplyEffect.ATTACK, this.damage));
		act(new ParryAction(p, null, m, UtilsApplyEffect.DRAW, this.magicNumber));
	}
	
	public AbstractCard makeCopy() {
		return new RejectGuard();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
			upgradeDamage(UPGRADE_PLUS_DMG);
			initializeDescription();
		}
	}
}
