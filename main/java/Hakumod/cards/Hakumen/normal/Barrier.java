package Hakumod.cards.Hakumen.normal;

import Hakumod.cards.Hakumen.abstracts.HakuCustomCard;
import Hakumod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Barrier extends HakuCustomCard {
	public static final String ID = makeCardID(Barrier.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;

	private static final int COST = 1;
	private static final int BLOCK = 3;
	private static final int UPGRADE_PLUS_BLOCK = 3;
	private static final int BUFF = 3;
	private static final int UPGRADE_BUFF = 3;
	
	public Barrier() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.SELF);
		this.baseBlock = BLOCK;
		this.magicNumber = this.baseMagicNumber = BUFF;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		act(new GainBlockAction(p, p, this.block));
	}

	public int getBlock() {
		return this.baseBlock + AbstractDungeon.player.cardsPlayedThisTurn;
	}

	@Override
	public void applyPowers() {
		this.block = getBlock();
		if (this.block > 0) {this.isBlockModified = true;}
	}

	@Override
	public void calculateCardDamage(AbstractMonster arg0) {
		this.block = getBlock();
		if (this.block > 0) {this.isBlockModified = true;}
	}

	@Override
	public void calculateDamageDisplay(AbstractMonster mo) {
		super.calculateDamageDisplay(mo);
	}

	public AbstractCard makeCopy() {
		return new Barrier();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
			upgradeMagicNumber(UPGRADE_BUFF);
			initializeDescription();
		}
	}
}
