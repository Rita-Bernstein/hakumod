package Hakumod.patches.music_patch;

import com.badlogic.gdx.Gdx;

import Hakumod.HakuInit;
import Hakumod.patches.music_patch.*;
import com.badlogic.gdx.audio.Music;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.audio.TempMusic;

import java.util.HashMap;

@SpirePatch(cls="com.megacrit.cardcrawl.audio.TempMusic", method="getSong")
public class TempMusicPatch
{
    private static HashMap<String, String> map = new HashMap<String, String>();

    public static Music Postfix(Music res, TempMusic _inst, String key)
    {
        if (map.containsKey(key)) {
            return Gdx.audio.newMusic(Gdx.files.internal((String)map.get(key)));
        }
        return res;
    }

    private static String load(String filename)
    {
        return "audio/music/" + filename;
    }

    static
    {
    	if (HakuInit.getUseMusic()) {
    		map.put("BOSS_BOTTOM", load("Walpurgisnacht.mp3"));
        	map.put("MINDBLOOM", load("ThinRedLine.mp3"));
        	map.put("BOSS_ENDING", load("EndlessDespair.mp3"));
    	}
    }
}
