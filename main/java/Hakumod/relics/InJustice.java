package Hakumod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import Hakumod.patches.CustomTags;
import basemod.abstracts.CustomRelic;
import basemod.helpers.CardTags;

import Hakumod.action.ParryAction;

public class InJustice extends CustomRelic{
    public static final String RELIC_ID = "InJustice";
    private static final String IMG = "Hakumod/img/relics/InJustice.png";
    private static final String IMG_OTL = "Hakumod/img/relics/outline/InJustice_outline.png";
    
    private static int MAX = 5;
    
	public InJustice() {
		super(RELIC_ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.COMMON, LandingSound.MAGICAL);
		this.counter = 0;
	}
	
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    
    public AbstractRelic makeCopy() {
        return new InJustice();
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
    	if (CardTags.hasTag(card, CustomTags.COMBO)) {
    		this.counter += 1;
    		if (this.counter >= MAX) {
        		this.counter = 0;
        		AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
        	}
    	}
    }
}
