package Hakumod.powers.player;

import Hakumod.patches.CustomTags;
import Hakumod.powers.abstracts.AbstractHakuPower;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class EmptySkyPower extends AbstractHakuPower {
	private static final String CLASS_NAME = EmptySkyPower.class.getSimpleName();
	public static final String POWER_ID = makePowerID(CLASS_NAME);

	public EmptySkyPower(AbstractCreature owner, int amount) {
		super(owner, amount, CLASS_NAME);
		updateDescription();
	}
	
	@Override
	public void onPlayCard(AbstractCard card, AbstractMonster m) {
		super.onPlayCard(card, m);
		if (card.hasTag(CustomTags.SPECIAL) || card.hasTag(CustomTags.ENDER) || card.hasTag(CustomTags.COMBO) || card.hasTag(CustomTags.STARTER)) {
			act(new DrawCardAction(AbstractDungeon.player, this.amount));
		}
		
	}
}
