package Hakumod.relics;

import java.util.ArrayList;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;

public class Haku_ContinuumShift extends CustomRelic{
    public static final String RELIC_ID = "Haku_ContinuumShift";
    private static final String IMG = "Hakumod/img/relics/ContinuumShift.png";
    private static final String IMG_OTL = "Hakumod/img/relics/outline/ContinuumShift_outline.png";
    
	public Haku_ContinuumShift() {
		super(RELIC_ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.RARE, LandingSound.MAGICAL);
	}

	/* (non-Javadoc)
	 * @see com.megacrit.cardcrawl.relics.AbstractRelic#onEnterRestRoom()
	 */
	@Override
	public void onEnterRestRoom() {
		// TODO Auto-generated method stub
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
        return new Haku_ContinuumShift();
    }
}
