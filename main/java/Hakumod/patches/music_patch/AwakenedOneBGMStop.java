package Hakumod.patches.music_patch;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.beyond.AwakenedOne;

import Hakumod.HakuInit;

//import Thmod.ThMod;


@SpirePatch(cls="com.megacrit.cardcrawl.monsters.beyond.AwakenedOne", method="damage")
public class AwakenedOneBGMStop {

    @SpireInsertPatch(rloc=11)
    public static void Insert(AwakenedOne _inst, DamageInfo info)
    {
    	if (HakuInit.getUseMusic()) {
            if ((_inst.currentHealth <= 0) && (!_inst.halfDead)) {
                CardCrawlGame.music.silenceTempBgmInstantly();
            }
        }
    }
}
