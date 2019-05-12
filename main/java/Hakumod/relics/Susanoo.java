package Hakumod.relics;

import Hakumod.powers.player.MagatamaPower;
import Hakumod.relics.abstracts.AbstractHakuRelic;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Susanoo extends AbstractHakuRelic {
    public static final String CLASS_NAME = Susanoo.class.getSimpleName();
    public static final String RELIC_ID = makeRelicID(CLASS_NAME);

    private static final int CARDS_REQUIRED = 3;
    
	public Susanoo() {
		super(CLASS_NAME, RelicTier.STARTER, LandingSound.MAGICAL);
		this.counter = 0;
	}
	
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    
    public AbstractRelic makeCopy() {
        return new Susanoo();
    }
    
    public void onUseCard(AbstractCard card, UseCardAction action) {
    	this.counter++;
    	if (this.counter >= CARDS_REQUIRED) {
    		this.counter = 0;
    		flash();
    		power(AbstractDungeon.player,AbstractDungeon.player,new MagatamaPower(AbstractDungeon.player,1),1);
    		act(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    	}
    }
    
    public void onVictory()
    {
      this.counter = 0;
    }
}
