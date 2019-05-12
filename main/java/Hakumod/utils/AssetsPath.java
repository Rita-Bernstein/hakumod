package Hakumod.utils;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.helpers.CardHelper;

public class AssetsPath {

    public static final String MOD_ID = "Haku:";
    private static final String ASSETS_FOLDER = "Hakumod/img";
    private static final String LOCALIZATION_FOLDER = "Hakumod/localization";
    private static final String CARDS_FOLDER = "Hakumod/img/cards";
    private static final String POWERS_FOLDER = "Hakumod/img/powers";
    private static final String ORBS_FOLDER = "Hakumod/img/orbs";
    private static final String RELIC_FOLDER = "Hakumod/img/relics";
    private static final String OUTLINE_FOLDER = "Hakumod/img/relics/outline";

    public static final String ATTACK_CC = "512/Haku_bg_attack.png";
    public static final String SKILL_CC = "512/Haku_bg_skill.png";
    public static final String POWER_CC = "512/Haku_bg_power.png";
    public static final String ENERGY_ORB_CC = "512/card_orb.png";
    public static final String ATTACK_CC_PORTRAIT = "1024/Haku_bg_attack.png";
    public static final String SKILL_CC_PORTRAIT = "1024/Haku_bg_skill.png";
    public static final String POWER_CC_PORTRAIT = "1024/Haku_bg_power.png";
    public static final String ENERGY_ORB_CC_PORTRAIT = "1024/card_orb.png";

    public static final Color WHITE = CardHelper.getColor(125.0f, 125.05f, 125.0f);
    public static final String MY_CHARACTER_BUTTON = "charSelect/HakuButton.png";
    public static final String HAKUMEN_PORTRAIT = "charSelect/HakuPortraitBG.png";

    AssetsPath(){}
    public static String makePath(String resource) {
        return ASSETS_FOLDER + "/" + resource;
    }

    public static String localizationPath(String resource) {
        return LOCALIZATION_FOLDER + "/" + resource;
    }

    public static String makeID(String resource){
        return MOD_ID + resource;
    }

    public static String makeCardPath(String cardName){
        return CARDS_FOLDER + "/" + cardName + ".png";
    }

    public static String makePowerPath(String powerName) {
        return POWERS_FOLDER + "/" + powerName + ".png";
    }
    public static String makeOrbsPath(String orbName) {
        return ORBS_FOLDER + "/" + orbName + ".png";
    }

    public static String makeRelicPath(String relicName) {
        return RELIC_FOLDER + "/" + relicName + ".png";
    }

    public static String makeOutlinePath(String outlineName) {
        return OUTLINE_FOLDER + "/" + outlineName + ".png";
    }
}
