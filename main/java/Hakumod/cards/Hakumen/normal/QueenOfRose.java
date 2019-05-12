package Hakumod.cards.Hakumen.normal;

import Hakumod.cards.Hakumen.abstracts.HakuCustomCard;
import Hakumod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class QueenOfRose extends HakuCustomCard {

	public static final String ID = makeCardID(QueenOfRose.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

	private static final int COST = 1;
	private static final int DEBUFF = -1;
	private static final int BUFF = 3;
	private static final int UPGRADED_BUFF = 1;
	
	public QueenOfRose() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.POWER,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.SELF);
		this.magicNumber = this.baseMagicNumber = BUFF;
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADED_BUFF);
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		if (p.hasPower(ArtifactPower.POWER_ID) && (p.getPower(ArtifactPower.POWER_ID).amount >= 2)) {
			act(new ApplyPowerAction(p, p, new StrengthPower(p, DEBUFF), DEBUFF));
			act(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
		}
		else {
			act(new ApplyPowerAction(p, p, new StrengthPower(p, DEBUFF), DEBUFF));
			act(new ApplyPowerAction(p, p, new GainStrengthPower(p, this.magicNumber), this.magicNumber));
		}
    }
	
	public AbstractCard makeCopy() {
		return new QueenOfRose();
	}
}