package Hakumod.cards.Hakumen.special;

import Hakumod.action.ComboAction;
import Hakumod.action.UsingSpecialAction;
import Hakumod.cards.Hakumen.abstracts.SpecialCard;
import Hakumod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Kishuu extends SpecialCard {

	public static final String ID = makeCardID(Kishuu.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;

	private static final int COST = 0;
	public final static int MAGATAMA_COST = 1;
	private static final int BLOCK = 4;
	private static final int UPGRADE_PLUS_BLOCK = 2;
	private static final int BUFF = 2;
	private static final int UPGRADE_BUFF = 1;
	
	public Kishuu() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.SELF,
				new Enma().makeCopy(),
				MAGATAMA_COST);
		this.baseBlock = BLOCK;
		this.magicNumber = this.baseMagicNumber = BUFF;
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
			upgradeMagicNumber(UPGRADE_BUFF);
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		act(new UsingSpecialAction(p, MAGATAMA_COST));
		act(new GainBlockAction(p, p, this.block));
		AbstractCard c = new Enma().makeCopy();
    	act(new ComboAction(c));
    }
	
	public AbstractCard makeCopy() {
		return new Kishuu();
	}
}