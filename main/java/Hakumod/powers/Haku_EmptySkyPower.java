package Hakumod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Hakumod.patches.CustomTags;


public class Haku_EmptySkyPower extends AbstractPower {
	public static final String POWER_ID = "Haku_EmptySkyPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	
	public Haku_EmptySkyPower(AbstractCreature owner, int amount) {
			this.name = NAME;
			this.ID = POWER_ID;
			this.owner = owner;
			this.amount = amount;
			this.isTurnBased = true;
			this.img = new Texture("Hakumod/img/powers/placeholder.png");
			updateDescription();
	}
	
	
	/* (non-Javadoc)
	 * @see com.megacrit.cardcrawl.powers.AbstractPower#onPlayCard(com.megacrit.cardcrawl.cards.AbstractCard, com.megacrit.cardcrawl.monsters.AbstractMonster)
	 */
	@Override
	public void onPlayCard(AbstractCard card, AbstractMonster m) {
		// TODO Auto-generated method stub
		super.onPlayCard(card, m);
		if (card.hasTag(CustomTags.SPECIAL) || card.hasTag(CustomTags.ENDER) || card.hasTag(CustomTags.COMBO) || card.hasTag(CustomTags.STARTER)) {
			AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
		}
		
	}


	public void updateDescription() {
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
	}
	
}
