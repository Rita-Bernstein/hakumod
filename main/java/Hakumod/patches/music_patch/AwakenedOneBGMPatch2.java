package Hakumod.patches.music_patch;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.beyond.AwakenedOne;

import Hakumod.HakuInit;

@SpirePatch(cls="com.megacrit.cardcrawl.monsters.beyond.AwakenedOne", method="takeTurn")
public class AwakenedOneBGMPatch2
{
    @SpireInsertPatch(rloc=0)
    public static void Insert(AwakenedOne _inst)
    {
    	if (HakuInit.getUseMusic()) {
		    if (_inst.nextMove == 3) {
		        CardCrawlGame.music.silenceTempBgmInstantly();
		        AbstractDungeon.getCurrRoom().playBgmInstantly("Dissonance.mp3");
		    }
    	}

    }
}
