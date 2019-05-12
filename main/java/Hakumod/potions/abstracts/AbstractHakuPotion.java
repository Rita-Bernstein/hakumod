package Hakumod.potions.abstracts;

import Hakumod.utils.AssetsPath;
import basemod.abstracts.CustomPotion;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public abstract class AbstractHakuPotion extends CustomPotion {
    public AbstractHakuPotion(String name, String id, PotionRarity rarity, PotionSize size, PotionColor color) {
        super(name, id, rarity, size, color);
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
        act(new ApplyPowerAction(target, source, power, amount));
    }

    protected static String makePotionID(String potionName){
        return AssetsPath.makeID(potionName);
    }
}
