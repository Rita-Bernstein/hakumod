package Hakumod.relics;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import Hakumod.cards.Hakumen.Haku_Burst;
import basemod.abstracts.CustomRelic;

public class Haku_BurstIcon extends CustomRelic{
    public static final String RELIC_ID = "Haku_BurstIcon";
    private static final String IMG = "Hakumod/img/relics/BurstIcon.png";
    private static final String IMG_OTL = "Hakumod/img/relics/outline/BurstIcon_outline.png";
    
	public Haku_BurstIcon() {
		super(RELIC_ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.RARE, LandingSound.MAGICAL);
		//this.counter = 0;
	}
	
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    
    public AbstractRelic makeCopy() {
        return new Haku_BurstIcon();
    }

	public void atBattleStart() {
		AbstractCard cardBurst = new Haku_Burst().makeCopy();
		//AbstractCard cardOD = new Haku_Overdrive().makeCopy();
		
		cardBurst.cost = 0;
		cardBurst.isCostModified = true;
		cardBurst.setCostForTurn(0);
		
		/*cardOD.cost = 0;
		cardOD.isCostModified = true;
		cardOD.setCostForTurn(0);*/
		
		
		AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(cardBurst, 1, true, false));
		//AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(cardOD, 1, true, false));
	}

}
