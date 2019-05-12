package Hakumod.utils;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.helpers.CardHelper;

public class ModColor {
    private static final Color MOD_COLOR = CardHelper.getColor(55.0f, 47.0f, 63.0f);

    ModColor(){}

    public static Color getColor(){
        return MOD_COLOR;
    }
}
