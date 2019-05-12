package Hakumod.cards.Hakumen.normal;

import Hakumod.cards.Hakumen.abstracts.HakuCustomCard;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.powers.player.MagatamaPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Walpurgisnacht extends HakuCustomCard {

	public static final String ID = makeCardID(Walpurgisnacht.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;

	private static final int BUFF = 8;
	private static final int UPGRADE_BUFF = 1;
	private static final int COST = 3;
	private static final int ATTACK_DMG = 0;

	public Walpurgisnacht() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.ATTACK,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.RARE,
				AbstractCard.CardTarget.ENEMY);
		this.magicNumber = this.baseMagicNumber = BUFF;
		this.baseDamage = ATTACK_DMG;
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_BUFF);
			initializeDescription();
		}
	}
	
    public int getDamage() {
    	int amountOfMagatama = 0;
		if (AbstractDungeon.player.hasPower(MagatamaPower.POWER_ID)) {
			amountOfMagatama = AbstractDungeon.player.getPower(MagatamaPower.POWER_ID).amount;
		}
		return amountOfMagatama * this.magicNumber + ATTACK_DMG;
    }

	@Override
	public void applyPowers() {
		this.baseDamage = getDamage();
		super.applyPowers();
	 }
	
	@Override
	public void calculateDamageDisplay(AbstractMonster mo) {
		calculateCardDamage(mo);
		super.calculateDamageDisplay(mo);
	}	

    @Override
	public void calculateCardDamage(AbstractMonster mo) {
		this.baseDamage = getDamage();
		super.calculateCardDamage(mo);
	}

	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	damage(m, p, this.damage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HEAVY);
    }
	
	public AbstractCard makeCopy() {
		return new Walpurgisnacht();
	}
}