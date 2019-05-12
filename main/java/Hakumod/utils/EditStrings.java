package Hakumod.utils;

import basemod.BaseMod;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.localization.*;

import java.nio.charset.StandardCharsets;

public class EditStrings {
    EditStrings(){ }

    public static void editStrings(){
        String relicStrings,cardStrings,powerStrings, potionStrings, monsterStrings, orbStrings, uiStrings, characterStrings;

        relicStrings = Gdx.files.internal(AssetsPath.localizationPath("Hakumod_Relics.json")).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);

        cardStrings = Gdx.files.internal(AssetsPath.localizationPath("Hakumod_Cards.json")).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);

        powerStrings = Gdx.files.internal(AssetsPath.localizationPath("Hakumod_Powers.json")).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);

        potionStrings = Gdx.files.internal(AssetsPath.localizationPath("Hakumod_Potions.json")).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PotionStrings.class, potionStrings);

        orbStrings = Gdx.files.internal(AssetsPath.localizationPath("Hakumod_Orbs.json")).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(OrbStrings.class, orbStrings);

        monsterStrings = Gdx.files.internal(AssetsPath.localizationPath("Hakumod_Monsters.json")).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(MonsterStrings.class, monsterStrings);

        uiStrings = Gdx.files.internal(AssetsPath.localizationPath("Hakumod_UI.json")).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(UIStrings.class, uiStrings);

        characterStrings = Gdx.files.internal(AssetsPath.localizationPath("Hakumod_Character.json")).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CharacterStrings.class, characterStrings);
    }
}
