package Hakumod.cards.Hakumen.normal;

import Hakumod.cards.Hakumen.abstracts.HakuCustomCard;
import Hakumod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HeavySlash extends HakuCustomCard {
	public static final String ID = makeCardID(HeavySlash.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

	private static final int COST = 4;
	private static final int ATTACK_DMG = 10;
	private static final int UPGRADE_PLUS_DMG = 3;

	public HeavySlash() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.ATTACK, 
				AbstractCardEnum.HAKUMEN_COLOR, 
				AbstractCard.CardRarity.COMMON, 
				AbstractCard.CardTarget.ENEMY);
		this.baseDamage = ATTACK_DMG;
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			this.rawDescription = UPG_DESCRIPTION;
		}
	}
	@Override
	public void triggerOnCardPlayed(AbstractCard c) {
		if (c != this) {
			this.setCostForTurn(this.costForTurn - 1);
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			if ((mo != null) && (!mo.isDeadOrEscaped())) {
				act(new com.megacrit.cardcrawl.actions.common.DamageAction(mo,
						new DamageInfo(p, this.damage, this.damageTypeForTurn),
						AbstractGameAction.AttackEffect.SLASH_HEAVY));
			}
		}
    }

	public AbstractCard makeCopy() {
		return new HeavySlash();
	}	
}
