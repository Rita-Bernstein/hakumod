package Hakumod.cards.Hakumen.fuumajin;

import Hakumod.cards.Hakumen.abstracts.HakuCustomCard;
import Hakumod.orbs.VoidOrb;
import Hakumod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AwakeningTheChaos extends HakuCustomCard {

	public static final String ID = makeCardID(AwakeningTheChaos.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

	private static final int BLOCK = 6;
	private static final int UPGRADED_BLOCK = 3;
	private static final int COST = 1;
	private static final int MAGNITUDE = 1;

	public AwakeningTheChaos() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.SELF);
		this.baseBlock = BLOCK;
		this.magicNumber = this.baseMagicNumber = MAGNITUDE;
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADED_BLOCK);
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	block(p, p, this.block);
    	act(new ChannelAction(new VoidOrb()));
	}
	
	public AbstractCard makeCopy() {
		return new AwakeningTheChaos();
	}
}