package Hakumod.cards.Hakumen.fuumajin;

import Hakumod.cards.Hakumen.abstracts.HakuCustomCard;
import Hakumod.orbs.VoidOrb;
import Hakumod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

public class ScienceFiction extends HakuCustomCard {

	public static final String ID = makeCardID(ScienceFiction.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;

	private static final int COST = 1;
	private static final int UPGRADED_COST = 0;
	private static final int MAGNITUDE = 1;
    
	public ScienceFiction() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.RARE,
				AbstractCard.CardTarget.SELF);
		this.magicNumber = this.baseMagicNumber = MAGNITUDE;
		this.exhaust = true;
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
    	int amountOfFuumajin = 0;
    	for (AbstractOrb orb : p.orbs) {
    		if (orb.ID.equals(VoidOrb.ID)) {
    			amountOfFuumajin++;
    		}
    	}
		act(new GainEnergyAction(amountOfFuumajin));
		act(new DrawCardAction(p, amountOfFuumajin));
    }
	
	public AbstractCard makeCopy() {
		return new ScienceFiction();
	}
}