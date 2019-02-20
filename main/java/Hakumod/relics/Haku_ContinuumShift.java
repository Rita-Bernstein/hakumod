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
		super(RELIC_ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.UNCOMMON, LandingSound.MAGICAL);
	}
	
	
	/* (non-Javadoc)
	 * @see com.megacrit.cardcrawl.relics.AbstractRelic#onSmith()
	 */
	@Override
	public void onSmith() {
		// TODO Auto-generated method stub
		ArrayList<AbstractCard> upgradableCards = AbstractDungeon.player.masterDeck.getUpgradableCards().group;
		
		int indexRandomCard = (int) Math.floor(Math.random()*upgradableCards.size());
		AbstractCard randomCard = upgradableCards.get(indexRandomCard);
		randomCard.upgrade();
	}



	public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    
    public AbstractRelic makeCopy() {
        return new Haku_ContinuumShift();
    }
}
