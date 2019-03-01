package Hakumod.relics;

import Hakumod.powers.Haku_MagatamaPower;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Haku_Susanoo extends CustomRelic{
    public static final String RELIC_ID = "Haku_Susanoo";
    private static final String IMG = "Hakumod/img/relics/Susanoo.png";
    private static final String IMG_OTL = "Hakumod/img/relics/outline/Susanoo_outline.png";

	private int intCardsRequired = 3;
    
	public Haku_Susanoo() {
		super(RELIC_ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.STARTER, LandingSound.MAGICAL);
		// TODO Auto-generated constructor stub
		this.counter = 0;
	}
	
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    
    public AbstractRelic makeCopy() {
        return new Haku_Susanoo();
    }
    
    public void onUseCard(AbstractCard card, UseCardAction action) {
    	
    	this.counter++;
    	if (this.counter >= intCardsRequired) {
    		this.counter = 0;
    		flash();
    		//HakuInit.logger.info("Hakumod: Charging 1 Magatama");
    		AbstractDungeon.actionManager.addToTop(
    				new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new Haku_MagatamaPower(AbstractDungeon.player,1),1));
    		AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    	}
	
    }
    
    public void onVictory()
    {
      this.counter = 0;
    }
}
