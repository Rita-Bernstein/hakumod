package Hakumod.cards.Hakumen.fuumajin;

import Hakumod.cards.Hakumen.abstracts.HakuCustomCard;
import Hakumod.orbs.VoidOrb;
import Hakumod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SwordOfDoom extends HakuCustomCard {

	public static final String ID = makeCardID(SwordOfDoom.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;

	private static final int COST = 1;
	private static final int UPGRADED_COST = 0;
	private static final int NEW_MAX_ORBS = 4;
	private static final int MAGNITUDE = 1;

	public SwordOfDoom() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.POWER,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.UNCOMMON,
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
    	int increaseOrbs = NEW_MAX_ORBS - p.maxOrbs;
    	AbstractDungeon.player.increaseMaxOrbSlots(increaseOrbs, false);
    	
		for (int i=0;i<this.magicNumber;i++) {
			act(new ChannelAction(new VoidOrb()));
		}
    }
	
	public AbstractCard makeCopy() {
		return new SwordOfDoom();
	}
}