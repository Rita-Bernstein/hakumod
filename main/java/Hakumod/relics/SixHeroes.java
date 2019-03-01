package Hakumod.relics;

import Hakumod.patches.CustomTags;
import basemod.abstracts.CustomRelic;
import basemod.helpers.CardTags;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class SixHeroes extends CustomRelic{
    public static final String RELIC_ID = "SixHeroes";
    private static final String IMG = "Hakumod/img/relics/SixHeroes.png";
    private static final String IMG_OTL = "Hakumod/img/relics/outline/SixHeroes_outline.png";
    
    private static int MAX = 6;
    
	public SixHeroes() {
		super(RELIC_ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.RARE, LandingSound.MAGICAL);
		this.counter = 0;
	}
	
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    
    public AbstractRelic makeCopy() {
        return new SixHeroes();
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
    	if (CardTags.hasTag(card, CustomTags.PARRY)) {
    		this.counter += 1;
    		if (this.counter >= MAX) {
        		this.counter = 0;
        		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new IntangiblePlayerPower(AbstractDungeon.player, 1), 1));
        	}
    	}
    }
}
