package Hakumod.powers.player;

import Hakumod.orbs.VoidOrb;
import Hakumod.powers.abstracts.AbstractHakuPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

public class YomotsuhirasakaPower extends AbstractHakuPower {
	private static final String CLASS_NAME = YomotsuhirasakaPower.class.getSimpleName();
	public static final String POWER_ID = makePowerID(CLASS_NAME);

	public YomotsuhirasakaPower(AbstractCreature owner, int amount) {
		super(owner, amount, CLASS_NAME);
		updateDescription();
	}
	
	@Override
	public void onChannel(AbstractOrb orb) {
		super.onChannel(orb);
		if (orb.ID.equals(VoidOrb.ID)){
			for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
    			if ((mo != null) && (!mo.isDeadOrEscaped())) {
    				damage(mo, AbstractDungeon.player, this.amount, DamageInfo.DamageType.HP_LOSS, AbstractGameAction.AttackEffect.FIRE);
				}
			}
		}
	}
}
