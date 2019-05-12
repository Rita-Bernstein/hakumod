package Hakumod.cards.Hakumen.normal;

import Hakumod.cards.Hakumen.abstracts.HakuCustomCard;
import Hakumod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PandoraTears extends HakuCustomCard {
	public static final String ID = makeCardID(PandoraTears.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;

	private static final int COST = 2;
	private static final int BLOCK = 4;
	private static final int UPGRADE_PLUS_BLOCK = 1;
	
	public PandoraTears() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.SELF);
		this.baseBlock = BLOCK;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		//Removing the card from the calculation
		int handSize = p.hand.size() - 1;
		act(new GainBlockAction(p, p, this.block*handSize));
		act(new DiscardAction(p, p, handSize, false));
	}
	
	public AbstractCard makeCopy() {
		return new PandoraTears();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
			initializeDescription();
		}
	}
}
