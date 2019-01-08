package Hakumod.patches.music_patch;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.beyond.TimeEater;

import Hakumod.HakuInit;

@SpirePatch(cls="com.megacrit.cardcrawl.monsters.beyond.TimeEater", method="usePreBattleAction")
public class TimeEaterBGMPatch
{
    @SpireInsertPatch(rloc=10)
    public static void Insert(TimeEater _inst)
    {
    	if (HakuInit.getUseMusic()) {
            CardCrawlGame.music.silenceTempBgmInstantly();
            AbstractDungeon.getCurrRoom().playBgmInstantly("Yomotsuhirasaka.mp3");
        }
    }
}
