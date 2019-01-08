package Hakumod.patches.music_patch;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.city.TheCollector;

import Hakumod.HakuInit;



@SpirePatch(cls="com.megacrit.cardcrawl.monsters.city.TheCollector", method="usePreBattleAction")
public class CollectorBGMPatch
{
    @SpireInsertPatch(rloc=3)
    public static void Insert(TheCollector _inst)
    {
    	if (HakuInit.getUseMusic()) {
    		CardCrawlGame.music.silenceTempBgmInstantly();
    		AbstractDungeon.getCurrRoom().playBgmInstantly("PlasticNight.mp3");
    	}
    }
}
