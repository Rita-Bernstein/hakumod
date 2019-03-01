package Hakumod.powers;

import Hakumod.cards.Hakumen.Haku_4C;
import Hakumod.orbs.Haku_VoidOrb;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;


public class Haku_CutPower extends AbstractPower {
	public static final String POWER_ID = "Haku_CutPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	
	public Haku_CutPower(AbstractCreature owner, int amount) {
			this.name = NAME;
			this.ID = POWER_ID;
			this.owner = owner;
			this.amount = amount;
			this.isTurnBased = true;
			this.img = new Texture("Hakumod/img/powers/explosive.png");
			updateDescription();
	}
	
	public void updateDescription() {
		this.description = (DESCRIPTIONS[0]);
	}

	/* (non-Javadoc)
	 * @see com.megacrit.cardcrawl.powers.AbstractPower#onPlayCard(com.megacrit.cardcrawl.cards.AbstractCard, com.megacrit.cardcrawl.monsters.AbstractMonster)
	 */
	@Override
	public void onPlayCard(AbstractCard card, AbstractMonster m) {
		// TODO Auto-generated method stub
		AbstractCard card4C = new Haku_4C().makeCopy();
		if (card.cardID == card4C.cardID) {
			AbstractDungeon.actionManager.addToBottom(new ChannelAction(new Haku_VoidOrb()));
		}
	}
	
}
