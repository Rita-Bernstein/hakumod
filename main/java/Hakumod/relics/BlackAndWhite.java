package Hakumod.relics;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import Hakumod.patches.CustomTags;
import basemod.abstracts.CustomRelic;
import basemod.helpers.CardTags;

public class BlackAndWhite extends CustomRelic{
    public static final String RELIC_ID = "BlackAndWhite";
    private static final String IMG = "Hakumod/img/relics/BlackAndWhite.png";
    private static final String IMG_OTL = "Hakumod/img/relics/outline/BlackAndWhite_outline.png";
    
	public BlackAndWhite() {
		super(RELIC_ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.COMMON, LandingSound.MAGICAL);
	}
	
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    
    public AbstractRelic makeCopy() {
        return new BlackAndWhite();
    }
    
    /*
	public void onMonsterDeath(AbstractMonster m) {
			if ((!m.hasPower("Minion") && (!m.halfDead))) {
				if (AbstractDungeon.player.maxHealth <= AbstractDungeon.player.currentHealth+HEAL) {
					AbstractDungeon.player.increaseMaxHp(AbstractDungeon.player.currentHealth+HEAL-AbstractDungeon.player.maxHealth, false);		
				}
				
				AbstractDungeon.player.heal(HEAL);
			}
		}
	}*/
}
