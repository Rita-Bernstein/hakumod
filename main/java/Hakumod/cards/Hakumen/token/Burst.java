package Hakumod.cards.Hakumen.token;

import Hakumod.cards.Hakumen.abstracts.HakuCustomCard;
import Hakumod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;

public class Burst extends HakuCustomCard {

	public static final String ID = makeCardID(Burst.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 0;

	public Burst() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.POWER,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.RARE,
				AbstractCard.CardTarget.SELF);
		}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	power(p, p, new IntangiblePlayerPower(p, 1), 1);
    }
	
	public AbstractCard makeCopy() {
		return new Burst();
	}
}