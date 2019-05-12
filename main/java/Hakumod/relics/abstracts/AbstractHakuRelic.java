package Hakumod.relics.abstracts;

import Hakumod.utils.AssetsPath;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class AbstractHakuRelic extends CustomRelic {

    public AbstractHakuRelic(String className, RelicTier tier, LandingSound sfx) {
        super(makeRelicID(className),
                ImageMaster.loadImage(makeRelicPath(className)),
                ImageMaster.loadImage(makeOutlinePath(className)),
                tier, sfx);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }


    public void act(AbstractGameAction action){
        AbstractDungeon.actionManager.addToBottom(action);
    }

    public void damage(AbstractCreature target, AbstractCreature source, int damage, DamageInfo.DamageType damageType, AbstractGameAction.AttackEffect attackEffect){
        act(new DamageAction(target, new DamageInfo(source, damage, damageType), attackEffect));
    }

    public void block(AbstractCreature target, AbstractCreature source, int block){
        act(new GainBlockAction(target, source, block));
    }

    public void power(AbstractCreature target, AbstractCreature source, AbstractPower power, int amount){
        act(
                new ApplyPowerAction(target, source, power, amount));
    }

    public static String makeRelicID(String relicName){ return AssetsPath.makeID(relicName); }

    public static String makeRelicPath(String relicName){
        return AssetsPath.makeRelicPath(relicName);
    }

    public static String makeOutlinePath(String relicName){
        return AssetsPath.makeOutlinePath(relicName);
    }
}
