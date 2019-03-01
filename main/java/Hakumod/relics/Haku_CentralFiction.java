package Hakumod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Haku_CentralFiction extends CustomRelic{
    public static final String RELIC_ID = "Haku_CentralFiction";
    private static final String IMG = "Hakumod/img/relics/CentralFiction.png";
    private static final String IMG_OTL = "Hakumod/img/relics/outline/CentralFiction_outline.png";
	private static final int DRAW = 1;
    
	public Haku_CentralFiction() {
		super(RELIC_ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.RARE, LandingSound.MAGICAL);
	}
	
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    
    
    
    /* (non-Javadoc)
	 * @see com.megacrit.cardcrawl.relics.AbstractRelic#obtain()
	 */
	public AbstractRelic makeCopy() {
        return new Haku_CentralFiction();
    }

	/* (non-Javadoc)
	 * @see com.megacrit.cardcrawl.relics.AbstractRelic#atTurnStartPostDraw()
	 */
	@Override
	public void atTurnStartPostDraw() {
		// TODO Auto-generated method stub
		super.atTurnStartPostDraw();
		
		AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, DRAW));
    	
	}
    
	
    
    /*public void onUseCard(AbstractCard card, UseCardAction action) {	
    	if ( card.hasTag(CustomTags.ENDER) 
    			|| (CardTags.hasTag(card, CustomTags.COMBO))  
    				|| (CardTags.hasTag(card, CustomTags.PARRY)) ){
    		card.magicNumber += 1; 
    	}
    }*/
}
