package Hakumod.relics;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;

public class Haku_BarrierGauge extends CustomRelic{
    public static final String RELIC_ID = "BurstIcon";
    private static final String IMG = "Hakumod/img/relics/BurstIcon.png";
    private static final String IMG_OTL = "Hakumod/img/relics/outline/BurstIcon_outline.png";
    
	public Haku_BarrierGauge() {
		super(RELIC_ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.BOSS, LandingSound.MAGICAL);
		this.counter = 0;
	}
	
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    
    public AbstractRelic makeCopy() {
        return new Haku_BarrierGauge();
    }

	@Override
	public void onTrigger() {
		AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
	}



}
