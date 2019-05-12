package Hakumod.relics;

import Hakumod.relics.abstracts.AbstractHakuRelic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

public class ContinuumShift extends AbstractHakuRelic{
	public static final String CLASS_NAME = ContinuumShift.class.getSimpleName();
	public static final String RELIC_ID = makeRelicID(CLASS_NAME);

	public ContinuumShift() {
		super(CLASS_NAME, RelicTier.RARE, LandingSound.MAGICAL);
	}

	@Override
	public void onEnterRestRoom() {
		super.onEnterRestRoom();
		ArrayList<AbstractCard> upgradableCards = AbstractDungeon.player.masterDeck.getUpgradableCards().group;
		if (upgradableCards.size()>0) {
			int indexRandomCard = AbstractDungeon.miscRng.random(0, upgradableCards.size()-1);
			AbstractCard randomCard = upgradableCards.get(indexRandomCard);
			randomCard.upgrade();
			randomCard.upgraded = true;
		}
	}

	public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    
    public AbstractRelic makeCopy() {
        return new ContinuumShift();
    }
}
