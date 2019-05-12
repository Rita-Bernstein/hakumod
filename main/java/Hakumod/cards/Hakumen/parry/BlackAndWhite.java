package Hakumod.cards.Hakumen.parry;

import Hakumod.cards.Hakumen.abstracts.HakuCustomCard;
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

public class BlackAndWhite extends HakuCustomCard {

	public static final String ID = makeCardID(BlackAndWhite.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	;
	private static final int COST = 1;
	private static final int ATTACK_DMG = 5;
	private static final int DAMAGE_BOOST = 2;
	private static final int UPGRADED_DAMAGE_BOOST = 1;
	    
	public BlackAndWhite() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.ATTACK,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.ENEMY);
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = DAMAGE_BOOST;
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADED_DAMAGE_BOOST);
			this.rawDescription = UPG_DESCRIPTION;
			initializeDescription();
		}
	}

	public int isBlackAndWhite(AbstractCard c) {
		if (c.hasTag(CustomTags.PARRY)) {
			return 1;
		}
		return 0;
	}

	public int getDamage() {
		int damageBoost = 0;
		for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
			damageBoost += (isBlackAndWhite(c) * this.magicNumber);
		}
		for (AbstractCard c : AbstractDungeon.player.hand.group) {
			damageBoost += (isBlackAndWhite(c) * this.magicNumber);
		}
		return ATTACK_DMG + damageBoost;
	}

	@Override
	public void applyPowers() {
		this.baseDamage = getDamage();
		super.applyPowers();
	}

	@Override
	public void calculateCardDamage(AbstractMonster mo) {
		this.baseDamage = getDamage();
		super.calculateCardDamage(mo);
	}

	@Override
	public void calculateDamageDisplay(AbstractMonster mo) {
		calculateCardDamage(mo);
		super.calculateDamageDisplay(mo);
	}

	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
     	act(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }
	
	public AbstractCard makeCopy() {
		return new BlackAndWhite();
	}
}
