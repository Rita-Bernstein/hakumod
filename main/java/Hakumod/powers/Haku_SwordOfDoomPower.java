package Hakumod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Hakumod.action.GetRandomCardAction;


public class Haku_SwordOfDoomPower extends AbstractPower {
	public static final String POWER_ID = "Haku_SwordOfDoomPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	
	public Haku_SwordOfDoomPower(AbstractCreature owner, int amount) {
			this.name = NAME;
			this.ID = POWER_ID;
			this.owner = owner;
			this.amount = amount;
			this.isTurnBased = true;
			this.img = new Texture("Hakumod/img/powers/sword_of_doom.png");
			updateDescription();
	}
	
	

	/* (non-Javadoc)
	 * @see com.megacrit.cardcrawl.powers.AbstractPower#atStartOfTurn()
	 */
	@Override
	public void atStartOfTurn() {
		// TODO Auto-generated method stub
		super.atStartOfTurn();
		
		AbstractCard randomCard;
		
		for (int i=0; i<this.amount;i++) {
			randomCard = new GetRandomCardAction().getRandomParry();
			
			randomCard.isEthereal = true;
			randomCard.exhaustOnUseOnce = true;
			randomCard.exhaust = true;
			
			AbstractDungeon.player.hand.addToHand(randomCard);
		}
	}



	public void updateDescription() {
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
	}
	
}
