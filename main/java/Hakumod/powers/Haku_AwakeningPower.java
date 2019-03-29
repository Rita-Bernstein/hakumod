package Hakumod.powers;

import Hakumod.orbs.Haku_VoidOrb;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;


public class Haku_AwakeningPower extends AbstractPower {
	public static final String POWER_ID = "Haku_AwakeningPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

	private int turnCounter = 1;
	private static final int TURN_CAP = 2;

	public Haku_AwakeningPower(AbstractCreature owner, int amount) {
			this.name = NAME;
			this.ID = POWER_ID;
			this.owner = owner;
			this.amount = amount;
			this.isTurnBased = true;
			this.img = new Texture("Hakumod/img/powers/awakening.png");
			this.turnCounter = 1;
			updateDescription();
	}

	/* (non-Javadoc)
	 * @see com.megacrit.cardcrawl.powers.AbstractPower#onChannel(com.megacrit.cardcrawl.orbs.AbstractOrb)
	 */
	/*@Override
	public void onChannel(AbstractOrb orb) {
		// TODO Auto-generated method stub
		super.onChannel(orb);

		if (orb.ID == Haku_VoidOrb.ID){
			AbstractDungeon.actionManager.addToBottom(new GainBlockAction(owner, owner, this.amount));
		}

	}*/

	@Override
	public void atEndOfTurn(boolean isPlayer) {
		super.atEndOfTurn(isPlayer);
		this.turnCounter +=1;
		if (this.turnCounter >= TURN_CAP){
			for (int i = 0; i<this.amount; i++) {
				AbstractDungeon.actionManager.addToBottom(new ChannelAction(new Haku_VoidOrb()));
			}
			this.turnCounter = 0;
		}
	}

	public void updateDescription() {
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
	}
	
}
