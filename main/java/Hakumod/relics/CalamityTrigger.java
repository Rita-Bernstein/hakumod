package Hakumod.relics;

import Hakumod.relics.abstracts.AbstractHakuRelic;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class CalamityTrigger extends AbstractHakuRelic {
    public static final String CLASS_NAME = CalamityTrigger.class.getSimpleName();
    public static final String RELIC_ID = makeRelicID(CLASS_NAME);

    private int BLOCK = 4;

    public CalamityTrigger() {
		super(CLASS_NAME, RelicTier.UNCOMMON, LandingSound.MAGICAL);
	}
	
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    
    public AbstractRelic makeCopy() {
        return new CalamityTrigger();
    }
    
	public void onUseCard(AbstractCard card, UseCardAction action) {
    	if (card.isCostModifiedForTurn || card.isCostModified) {
    		block(AbstractDungeon.player, AbstractDungeon.player, BLOCK);
    	}
    }
}
