package Hakumod.patches.music_patch;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.beyond.AwakenedOne;

import Hakumod.HakuInit;



@SpirePatch(cls="com.megacrit.cardcrawl.monsters.beyond.AwakenedOne", method="usePreBattleAction")
public class AwakenedOneBGMPatch
{
    @SpireInsertPatch(rloc=3)
    public static void Insert(AwakenedOne _inst)
    {
    	if (HakuInit.getUseMusic()) {
    		CardCrawlGame.music.silenceTempBgmInstantly();
    		AbstractDungeon.getCurrRoom().playBgmInstantly("BlackAndWhite.mp3");
    	}
    }
}
