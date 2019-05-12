package Hakumod.cards.Hakumen.normal;

import Hakumod.cards.Hakumen.abstracts.HakuCustomCard;
import Hakumod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;

public class EndGazer extends HakuCustomCard {
	public static final String ID = makeCardID(EndGazer.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;

	private static final int COST = 1;
	private static final int BLOCK = 6;
	private static final int UPGRADE_BLOCK = 3;
	private static final int DRAW = 2;

	public EndGazer() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.SELF);

		this.baseBlock = BLOCK;
		this.magicNumber = this.baseMagicNumber = DRAW;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		act(new GainBlockAction(p, p, this.block));
		power(p, p,
				new DrawCardNextTurnPower(p, this.magicNumber), this.magicNumber);
	}
	
	public AbstractCard makeCopy() {
		return new EndGazer();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.upgradeBlock(UPGRADE_BLOCK);
			initializeDescription();
		}
	}
}
