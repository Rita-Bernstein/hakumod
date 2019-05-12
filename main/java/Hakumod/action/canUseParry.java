package Hakumod.action;

import Hakumod.powers.player.TheGatePower;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Arrays;

public class canUseParry {

    canUseParry(){

    }
    private static final AbstractMonster.Intent[] ARRAY_ATTACK_INTENTS = {AbstractMonster.Intent.ATTACK, AbstractMonster.Intent.ATTACK_BUFF, AbstractMonster.Intent.ATTACK_DEBUFF, AbstractMonster.Intent.ATTACK_DEFEND};

    public static boolean canUse(AbstractMonster target) {
        if (target == null){return true;}
        return (Arrays.asList(ARRAY_ATTACK_INTENTS).contains(target.intent) || AbstractDungeon.player.hasPower(TheGatePower.POWER_ID));
    }
}
