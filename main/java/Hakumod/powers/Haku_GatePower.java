package Hakumod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Hakumod.action.PlayCardAction;
import Hakumod.patches.CustomTags;


public class Haku_GatePower extends AbstractPower {
	public static final String POWER_ID = "Haku_GatePower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	//private int counter;
	
	public Haku_GatePower(AbstractCreature owner, int amount) {
			this.name = NAME;
			this.ID = POWER_ID;
			this.owner = owner;
			this.amount = amount;
			this.isTurnBased = true;
			this.img = new Texture("Hakumod/img/powers/placeholder.png");
			//this.counter = 0;
			updateDescription();
	}
	
	
	/* (non-Javadoc)
	 * @see com.megacrit.cardcrawl.powers.AbstractPower#onPlayCard(com.megacrit.cardcrawl.cards.AbstractCard, com.megacrit.cardcrawl.monsters.AbstractMonster)
	 */
	/*@Override
	public void onPlayCard(AbstractCard card, AbstractMonster m) {
		// TODO Auto-generated method stub
		super.onPlayCard(card, m);
		if (card.hasTag(CustomTags.PARRY) || card.hasTag(CustomTags.STARTER) || card.hasTag(CustomTags.COMBO) && this.counter<this.amount) {
			AbstractCard copy = card.makeCopy();
			copy.exhaustOnUseOnce = true;
			copy.setCostForTurn(0);
			AbstractDungeon.actionManager.addToBottom(new PlayCardAction(AbstractDungeon.player, copy, m));
			//AbstractDungeon.player.useCard(copy, m, 0);
		}
		this.counter++;
		
	}*/

	/* (non-Javadoc)
	 * @see com.megacrit.cardcrawl.powers.AbstractPower#atEndOfTurn(boolean)
	 */
	@Override
	public void atEndOfTurn(boolean isPlayer) {
		// TODO Auto-generated method stub
		super.atEndOfTurn(isPlayer);
		int amountBlocked = this.amount * AbstractDungeon.player.hand.size();
		AbstractDungeon.actionManager.addToBottom(
				new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, amountBlocked));
	}


	public void updateDescription() {
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
	}
	
}
