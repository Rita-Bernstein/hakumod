package Hakumod.cards.Hakumen.fuumajin;

import Hakumod.cards.Hakumen.abstracts.HakuCustomCard;
import Hakumod.orbs.VoidOrb;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.powers.player.YomotsuhirasakaPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Yomotsuhirasaka extends HakuCustomCard {

	public static final String ID = makeCardID(Yomotsuhirasaka.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;

	private static final int COST = 2;
	private static final int STACK = 5;
	private static final int CHANNEL = 1;
	private static final int UPGRADED_CHANNEL = 1;
	
	public Yomotsuhirasaka() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.POWER,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.SELF);
		this.magicNumber = this.baseMagicNumber = CHANNEL;
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADED_CHANNEL);
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		power(p, p, new YomotsuhirasakaPower(p, STACK), STACK);

		for (int i=0;i<this.magicNumber;i++) {
			act(new ChannelAction(new VoidOrb()));
		}
    }
	
	public AbstractCard makeCopy() {
		return new Yomotsuhirasaka();
	}
}