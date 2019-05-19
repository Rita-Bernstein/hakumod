package Hakumod.cards.Hakumen.parry;

import Hakumod.cards.Hakumen.abstracts.HakuCustomCard;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.powers.player.TheGatePower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TheGate extends HakuCustomCard {

	public static final String ID = makeCardID(TheGate.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

	private static final int COST = 2;
	private static final int UPGRADED_COST = 1;
	private static final int MAGNITUDE = 2;
    
	public TheGate() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.POWER,
				AbstractCardEnum.HAKUMEN_COLOR,
				CardRarity.UNCOMMON,
				AbstractCard.CardTarget.SELF);
		this.magicNumber = this.baseMagicNumber = MAGNITUDE;
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBaseCost(UPGRADED_COST);
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	power(p, p, new TheGatePower(p, this.magicNumber), this.magicNumber);
    }
	
	public AbstractCard makeCopy() {
		return new TheGate();
	}
}