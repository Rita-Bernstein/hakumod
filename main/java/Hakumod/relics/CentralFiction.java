package Hakumod.relics;

import Hakumod.relics.abstracts.AbstractHakuRelic;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class CentralFiction extends AbstractHakuRelic{
	public static final String CLASS_NAME = CentralFiction.class.getSimpleName();
	public static final String RELIC_ID = makeRelicID(CLASS_NAME);
	private static final int DRAW = 1;
    
	public CentralFiction() {
		super(CLASS_NAME, RelicTier.RARE, LandingSound.MAGICAL);
	}
	
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

	public AbstractRelic makeCopy() {
        return new CentralFiction();
    }

	@Override
	public void atTurnStartPostDraw() {
		super.atTurnStartPostDraw();
		act(new DrawCardAction(AbstractDungeon.player, DRAW));
	}
}
