package Hakumod.relics;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import Hakumod.powers.Haku_MagatamaPower;
import basemod.abstracts.CustomRelic;

public class Haku_Susanoo2 extends CustomRelic{
    public static final String RELIC_ID = "Haku_Susanoo2";
    private static final String IMG = "Hakumod/img/relics/Susanoo2.png";
    private static final String IMG_OTL = "Hakumod/img/relics/outline/Susanoo2_outline.png";
    
    private int ENERGY_GAIN = 1;
    private int MAGATAMA_LOSS = 1;
    
	public Haku_Susanoo2() {
		super(RELIC_ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.BOSS, LandingSound.MAGICAL);
	}
	
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    
    public AbstractRelic makeCopy() {
        return new Haku_Susanoo2();
    }
    
    public void onEquip(){
        AbstractDungeon.player.energy.energyMaster += ENERGY_GAIN;
      }
      
      public void onUnequip(){
        AbstractDungeon.player.energy.energyMaster -= ENERGY_GAIN;
      }
    
	public void atTurnStart() {
		
		if (AbstractDungeon.player.hasPower(Haku_MagatamaPower.POWER_ID)) 
		{
			AbstractDungeon.player.getPower(Haku_MagatamaPower.POWER_ID).reducePower(this.MAGATAMA_LOSS);
			AbstractDungeon.player.getPower(Haku_MagatamaPower.POWER_ID).updateDescription();
			if (AbstractDungeon.player.getPower(Haku_MagatamaPower.POWER_ID).amount == 0) 
			{
				AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, Haku_MagatamaPower.POWER_ID));
			}
		}
	}
	
	
    
}
