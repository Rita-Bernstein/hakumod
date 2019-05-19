package Hakumod.utils;

import basemod.BaseMod;
import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;

import java.nio.charset.StandardCharsets;

import com.megacrit.cardcrawl.core.Settings;

public class AddKeywords {

    AddKeywords(){}

    public static void addKeywords(){
        String language;
        switch (Settings.language) {
            // case KOR:
            //     language = "kor";
            //     break;
            case ZHS:
                language = "zhs";
                break;
            // case ZHT:
            //     language = "zht";
            //     break;
            // case FRA:
            //     language = "fra";
            //     break;
            // case JPN:
            //     language = "jpn";
            //     break;
            default:
                language = "eng";
        }
        Gson gson = new Gson();
        String json = Gdx.files.internal(AssetsPath.localizationPath( language +"/Hakumod_Keywords.json")).readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }
}
