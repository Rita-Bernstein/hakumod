package Hakumod.patches.music_patch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.audio.Mp3.Music;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.audio.Sfx;
import com.megacrit.cardcrawl.audio.SoundMaster;
import com.megacrit.cardcrawl.audio.TempMusic;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.city.BronzeAutomaton;

import Hakumod.HakuInit;

@SpirePatch(cls="com.megacrit.cardcrawl.monsters.city.BronzeAutomaton", method="usePreBattleAction")
public class AutomatonBGMPatch
{
    @SpireInsertPatch(rloc=3)
    public static void Insert(BronzeAutomaton _inst)
    {
    	if (HakuInit.getUseMusic()) {
    		CardCrawlGame.music.silenceTempBgmInstantly();
        	AbstractDungeon.getCurrRoom().playBgmInstantly("ScienceFiction.mp3");
    	}
    }
}
