package Hakumod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Haku_CalamityTrigger extends CustomRelic{
    public static final String RELIC_ID = "Haku_CalamityTrigger";
    private static final String IMG = "Hakumod/img/relics/CalamityTrigger.png";
    private static final String IMG_OTL = "Hakumod/img/relics/outline/CalamityTrigger_outline.png";

    private int BLOCK = 4;
	public Haku_CalamityTrigger() {
		super(RELIC_ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.UNCOMMON, LandingSound.MAGICAL);
	}
	
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    
    public AbstractRelic makeCopy() {
        return new Haku_CalamityTrigger();
    }
    
	/* (non-Javadoc)
	 * @see com.megacrit.cardcrawl.relics.AbstractRelic#onCardDraw(com.megacrit.cardcrawl.cards.AbstractCard)
	 */
	/*@Override
	public void onCardDraw(AbstractCard drawnCard) {
		// TODO Auto-generated method stub
		super.onCardDraw(drawnCard);
		int cost;
		if (!this.hasDrawnCard) {
			cost = (int) Math.floor(Math.random()*3);
			drawnCard.setCostForTurn(cost);
			this.hasDrawnCard = true;
		}
	}*/

	/* (non-Javadoc)
	 * @see com.megacrit.cardcrawl.relics.AbstractRelic#onPlayerEndTurn()
	 */

	public void onUseCard(AbstractCard card, UseCardAction action) {
    	if (card.isCostModifiedForTurn || card.isCostModified) {
    		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, BLOCK));
    		
    	}
	
    }
    
}
