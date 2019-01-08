package Hakumod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
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
import Hakumod.cards.Hakumen.Haku_Burst;
import Hakumod.cards.Hakumen.Haku_Guren;
import Hakumod.cards.Hakumen.Haku_Overdrive;

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
