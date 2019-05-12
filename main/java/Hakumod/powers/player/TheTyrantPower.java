package Hakumod.powers.player;

import Hakumod.patches.CustomTags;
import Hakumod.powers.abstracts.AbstractHakuPower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class TheTyrantPower extends AbstractHakuPower {
	private static final String CLASS_NAME = TheTyrantPower.class.getSimpleName();
	public static final String POWER_ID = makePowerID(CLASS_NAME);

	public TheTyrantPower(AbstractCreature owner, int amount) {
			super(owner, amount, CLASS_NAME);
			this.isTurnBased = true;
			updateDescription();
	}
	
	@Override
	public void onPlayCard(AbstractCard card, AbstractMonster m) {
		super.onPlayCard(card, m);
		if (card.hasTag(CustomTags.ENDER)) {
			power(m, AbstractDungeon.player,
					new WeakPower(m, this.amount, false), this.amount);
		}
		
		if (card.hasTag(CustomTags.COMBO)) {
			power(m, AbstractDungeon.player,
					new VulnerablePower(m, this.amount, false), this.amount);
		}
		
	}
}
