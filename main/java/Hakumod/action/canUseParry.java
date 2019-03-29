package Hakumod.action;

import Hakumod.powers.Haku_GatePower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Arrays;

public class canUseParry {

    public canUseParry(){

    }
    private static final AbstractMonster.Intent[] ARRAY_ATTACK_INTENTS = {AbstractMonster.Intent.ATTACK, AbstractMonster.Intent.ATTACK_BUFF, AbstractMonster.Intent.ATTACK_DEBUFF, AbstractMonster.Intent.ATTACK_DEFEND};

    public static boolean canUse(AbstractMonster target) {
        if (target == null){return true;}
        if (Arrays.asList(ARRAY_ATTACK_INTENTS).contains(target.intent) || AbstractDungeon.player.hasPower(Haku_GatePower.POWER_ID)) {
            return true;
        }
        return false;
    }
}
