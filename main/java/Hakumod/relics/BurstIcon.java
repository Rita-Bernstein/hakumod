package Hakumod.relics;

import Hakumod.cards.Hakumen.token.Burst;
import Hakumod.relics.abstracts.AbstractHakuRelic;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class BurstIcon extends AbstractHakuRelic{
	public static final String CLASS_NAME = BurstIcon.class.getSimpleName();
	public static final String RELIC_ID = makeRelicID(CLASS_NAME);

	public BurstIcon() {
		super(CLASS_NAME, RelicTier.RARE, LandingSound.MAGICAL);
	}

	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}

	public AbstractRelic makeCopy() {
        return new BurstIcon();
    }

	public void atBattleStart() {
		AbstractCard cardBurst = new Burst().makeCopy();
		act(new MakeTempCardInDrawPileAction(cardBurst, 1, true, false));
	}
}
