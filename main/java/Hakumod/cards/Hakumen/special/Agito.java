package Hakumod.cards.Hakumen.special;

import Hakumod.action.OkizemeAction;
import Hakumod.action.StarterAction;
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

public class Agito extends SpecialCard {

	public static final String ID = makeCardID(Agito.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;

	private static final int COST = 1;
	private static final int ATTACK_DMG = 8;
	private static final int UPGRADE_PLUS_DMG = 3;
	private static final int BUFF = 2;
	public static final int MAGATAMA_COST = 1;
	
	public Agito() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.ATTACK,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.ENEMY,
				MAGATAMA_COST);
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = BUFF;
		this.tags.add(CustomTags.STARTER);
		this.tags.add(CustomTags.ENDER);
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		act(new UsingSpecialAction(p, MAGATAMA_COST));
		act(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
			new DamageInfo(p, this.damage, this.damageTypeForTurn),
			AbstractGameAction.AttackEffect.SLASH_VERTICAL));
		act(new StarterAction(p, this, m, UtilsApplyEffect.OFFENSE, this.magicNumber));

		act(new OkizemeAction(p, this, m, UtilsApplyEffect.OFFENSE, this.magicNumber));
    }
	
	public AbstractCard makeCopy() {
		return new Agito();
	}
}