package Hakumod.relics;

import Hakumod.powers.player.MagatamaPower;
import Hakumod.relics.abstracts.AbstractHakuRelic;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Susanoo2 extends AbstractHakuRelic {
    public static final String CLASS_NAME = Susanoo2.class.getSimpleName();
    public static final String RELIC_ID = makeRelicID(CLASS_NAME);

    private static final int ENERGY_GAIN = 1;
    private static final int MAGATAMA_LOSS = 1;
    
	public Susanoo2() {
		super(CLASS_NAME, RelicTier.BOSS, LandingSound.MAGICAL);
	}
	
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    
    public AbstractRelic makeCopy() {
        return new Susanoo2();
    }
    
    public void onEquip(){
        AbstractDungeon.player.energy.energyMaster += ENERGY_GAIN;
      }

    public void onUnequip(){
        AbstractDungeon.player.energy.energyMaster -= ENERGY_GAIN;
    }

    public void atTurnStart() {
		
		if (AbstractDungeon.player.hasPower(MagatamaPower.POWER_ID))
		{
			AbstractDungeon.player.getPower(MagatamaPower.POWER_ID).reducePower(this.MAGATAMA_LOSS);
			AbstractDungeon.player.getPower(MagatamaPower.POWER_ID).updateDescription();
			if (AbstractDungeon.player.getPower(MagatamaPower.POWER_ID).amount == 0)
			{
				act(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, MagatamaPower.POWER_ID));
			}
		}
	}
	
	
    
}
