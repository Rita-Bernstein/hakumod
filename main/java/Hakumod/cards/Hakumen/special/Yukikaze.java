package Hakumod.cards.Hakumen.special;

import Hakumod.action.ParryAction;
import Hakumod.action.UsingSpecialAction;
import Hakumod.action.UtilsApplyEffect;
import Hakumod.cards.Hakumen.abstracts.SpecialParryCard;
import Hakumod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Yukikaze extends SpecialParryCard {
	public static final String ID = makeCardID(Yukikaze.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;

	private static final int COST = 1;
	private static final int ATTACK_DMG = 20;
	private static final int UPGRADE_PLUS_DMG = 5;
	private static final int BLOCK = 12;
	private static final int UPGRADE_PLUS_BLOCK = 3;
	private static final int BUFF = 1;
	public final static int MAGATAMA_COST = 4;
	
	public Yukikaze() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ENEMY,
				MAGATAMA_COST);
		this.baseDamage = ATTACK_DMG;
		this.baseBlock = BLOCK;
		this.magicNumber = this.baseMagicNumber = BUFF;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		act( new UsingSpecialAction(p, MAGATAMA_COST));
		act(new ParryAction(p, null, m, UtilsApplyEffect.ATTACK, baseDamage));
		act(new ParryAction(p, null, m, UtilsApplyEffect.BUFFER, magicNumber));
	}
	
	public AbstractCard makeCopy() {
		return new Yukikaze();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
			upgradeDamage(UPGRADE_PLUS_DMG);
		}
	}
}
