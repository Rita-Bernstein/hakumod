package Hakumod.cards.Hakumen.parry;

import Hakumod.action.ParryAction;
import Hakumod.action.UsingSpecialAction;
import Hakumod.action.UtilsApplyEffect;
import Hakumod.cards.Hakumen.abstracts.SpecialCard;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Yanagi extends SpecialCard {
	public static final String ID = makeCardID(Yanagi.class.getSimpleName());
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;

	private static final int COST = 1;
	private static final int ATTACK_DMG = 11;
	private static final int UPGRADE_PLUS_DMG = 2;
	private static final int BLOCK = 5;
	private static final int UPGRADE_PLUS_BLOCK = 2;
	private static final int BUFF = 1;
	private static final int UPGRADE_BUFF = 1;
	public final static int MAGATAMA_COST = 2;
	
	public Yanagi() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.ATTACK,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ENEMY,
				MAGATAMA_COST);
		this.baseDamage = ATTACK_DMG;
		this.baseBlock = BLOCK;
		this.magicNumber = this.baseMagicNumber = BUFF;
		this.tags.add(CustomTags.PARRY);
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		act( new UsingSpecialAction(p, MAGATAMA_COST));
		act(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SHIELD));
		act(new ParryAction(p, null, m, UtilsApplyEffect.BLOCK, this.block));
		act(new ParryAction(p, null, m, UtilsApplyEffect.THORN, magicNumber));
	}
	
	public AbstractCard makeCopy() {
		return new Yanagi();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
			upgradeDamage(UPGRADE_PLUS_DMG);
			upgradeMagicNumber(UPGRADE_BUFF);
			initializeDescription();
		}
	}
}
