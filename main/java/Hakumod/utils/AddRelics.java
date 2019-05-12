package Hakumod.utils;

import Hakumod.patches.AbstractCardEnum;
import Hakumod.relics.*;
import basemod.BaseMod;

public class AddRelics {
    AddRelics(){}

    public static void addRelics(){
        BaseMod.addRelicToCustomPool(new Susanoo(), AbstractCardEnum.HAKUMEN_COLOR);
        BaseMod.addRelicToCustomPool(new Susanoo2(), AbstractCardEnum.HAKUMEN_COLOR);
        BaseMod.addRelicToCustomPool(new BurstIcon(), AbstractCardEnum.HAKUMEN_COLOR);
        BaseMod.addRelicToCustomPool(new CalamityTrigger(), AbstractCardEnum.HAKUMEN_COLOR);
        BaseMod.addRelicToCustomPool(new ContinuumShift(), AbstractCardEnum.HAKUMEN_COLOR);
        BaseMod.addRelicToCustomPool(new ChronoPhantasma(), AbstractCardEnum.HAKUMEN_COLOR);
        BaseMod.addRelicToCustomPool(new CentralFiction(), AbstractCardEnum.HAKUMEN_COLOR);
    }
}
