package Hakumod.utils;

import Hakumod.patches.HakuEnum;
import Hakumod.potions.FullPowerPotion;
import Hakumod.potions.HalfPowerPotion;
import Hakumod.potions.QuarterPowerPotion;
import basemod.BaseMod;
import com.badlogic.gdx.graphics.Color;

public class AddPotions {
    AddPotions(){}

    public static void addPotions(){
        BaseMod.addPotion(QuarterPowerPotion.class, Color.BLUE.cpy(), Color.BLUE.cpy(), null, QuarterPowerPotion.POTION_ID, HakuEnum.HAKUMEN);
        BaseMod.addPotion(HalfPowerPotion.class, Color.WHITE.cpy(), Color.WHITE.cpy(), null, HalfPowerPotion.POTION_ID, HakuEnum.HAKUMEN);
        BaseMod.addPotion(FullPowerPotion.class, Color.RED.cpy(), Color.RED.cpy(), null, FullPowerPotion.POTION_ID, HakuEnum.HAKUMEN);
    }
}
