package Hakumod.utils;

import basemod.BaseMod;
import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;

import java.nio.charset.StandardCharsets;

public class AddKeywords {

    AddKeywords(){}

    public static void addKeywords(){
        Gson gson = new Gson();
        String json = Gdx.files.internal(AssetsPath.localizationPath("Hakumod_Keywords.json")).readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }
}
