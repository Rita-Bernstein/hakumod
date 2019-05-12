package Hakumod.orbs.abstracts;

import Hakumod.utils.AssetsPath;
import basemod.abstracts.CustomOrb;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public abstract class AbstractHakuOrb extends CustomOrb {
    public AbstractHakuOrb(String className, int basePassiveAmount, int baseEvokeAmount){
        super(AssetsPath.makeOrbsPath(className),
                CardCrawlGame.languagePack.getOrbString(makeOrbID(className)).NAME,
                basePassiveAmount,
                baseEvokeAmount,
                CardCrawlGame.languagePack.getOrbString(makeOrbID(className)).DESCRIPTION[0],
                CardCrawlGame.languagePack.getOrbString(makeOrbID(className)).DESCRIPTION[1],
                AssetsPath.makeOrbsPath(className));
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

    protected static String makeOrbID(String cardName){return AssetsPath.makeID(cardName);}
}
