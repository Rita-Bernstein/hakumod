package Hakumod.powers.player;

import Hakumod.patches.CustomTags;
import Hakumod.powers.abstracts.AbstractHakuPower;
import basemod.helpers.CardTags;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class MugenPower extends AbstractHakuPower {
    private static final String CLASS_NAME = MugenPower.class.getSimpleName();
    public static final String POWER_ID = makePowerID(CLASS_NAME);

    public MugenPower(AbstractCreature owner, int amount, boolean isSourceMonster) {
			super(owner, amount, CLASS_NAME);
			this.isTurnBased = true;
			updateDescription();
	}

	public void onDrawOrDiscard() {
		if (AbstractDungeon.player.hand.size() > 0) {
			AbstractCard cardAtTheTop = AbstractDungeon.player.hand.getTopCard();
			if ( (CardTags.hasTag(cardAtTheTop, CustomTags.SPECIAL)))
			{
				cardAtTheTop.setCostForTurn(0);
			}	
		}
	}

	public void atEndOfRound()
	{
		if (this.amount <= 1 ) {
			act(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
		} else {
			act(new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));
		}
	}
}
