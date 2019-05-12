package Hakumod.powers.abstracts;

import Hakumod.utils.AssetsPath;
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

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public class AbstractHakuPower extends AbstractPower {

    public AbstractHakuPower(AbstractCreature owner, int amount, String className){
        this.ID = makePowerID(className);
        this.name = languagePack.getPowerStrings(this.ID).NAME;
        this.owner = owner;
        this.amount = amount;
        this.img = ImageMaster.loadImage(makePowerPath(className));
    }

    public void updateDescription() {
        String[] powerDesc = languagePack.getPowerStrings(this.ID).DESCRIPTIONS;
        this.description = (powerDesc[0] + this.amount + powerDesc[1]);
    }

    public void stackPower(int stackAmount)
    {
        this.amount += stackAmount;
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

    public static String makePowerID(String powerName){ return AssetsPath.makeID(powerName); }

    public static String makePowerPath(String powerName){
        return AssetsPath.makePowerPath(powerName);
    }

    public String getID(){
        return this.ID;
    }
}
