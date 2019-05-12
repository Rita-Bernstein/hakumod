package Hakumod.relics;

import Hakumod.relics.abstracts.AbstractHakuRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class ChronoPhantasma extends AbstractHakuRelic{
	public static final String CLASS_NAME = ChronoPhantasma.class.getSimpleName();
	public static final String RELIC_ID = makeRelicID(CLASS_NAME);
	private static final int HP_STACK = 15;

	public ChronoPhantasma() {
		super(CLASS_NAME, RelicTier.COMMON, LandingSound.MAGICAL);
	}
	
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    
    public AbstractRelic makeCopy() {
        return new ChronoPhantasma();
    }
    
	public void atBattleStart() {
		int hpMissing = AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth;
		int StrengthBonus = (int) ( (float) hpMissing / (float) HP_STACK);
		
		if (StrengthBonus > 0) {
			power(AbstractDungeon.player, AbstractDungeon.player,
					new StrengthPower(AbstractDungeon.player, StrengthBonus), StrengthBonus);
		}
	}
}
