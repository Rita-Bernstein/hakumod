package Hakumod.action;

import Hakumod.powers.Haku_ScienceFictionPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class OkizemeAction extends AbstractGameAction{
	
	private AbstractPlayer player;
	private AbstractCard card;
	private AbstractMonster target;
	private String effect;
	private int magnitude;
	//private final int block = 6;
	public OkizemeAction(AbstractPlayer p, AbstractCard c, AbstractMonster target , String effect, int magnitude)
	{
		this.player = p;
		this.card = c;
		this.target = target;
		this.effect = effect;
		this.magnitude = magnitude;
		this.duration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_XFAST;
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
	}
	
	@Override
	public void update() 
	{

		/*if ( this.player.hand.getAttacks().size() == 0) {
			new UtilsApplyEffect(this.player, this.card, this.target, this.effect, this.magnitude+this.card.energyOnUse-this.card.costForTurn);
			
			if (this.player.hasPower("CameliaPower")) {AbstractDungeon.actionManager.addToTop(
					new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.block*this.player.getPower("CameliaPower").amount));
			}
			//AbstractDungeon.actionManager.addToBottom(new LoseEnergyAction(this.player.energy.energy));
		}*/
		if (this.player.hasPower(Haku_ScienceFictionPower.POWER_ID)) {
			this.magnitude = this.magnitude + this.player.getPower(Haku_ScienceFictionPower.POWER_ID).amount;	
		}
		if ( (this.card.energyOnUse - this.card.costForTurn <= 0) ||  this.player.hand.getAttacks().size() == 0 || this.player.hasPower("Haku_ScienceFictionPower")) {
			new UtilsApplyEffect(this.player, this.card, this.target, this.effect, this.magnitude);
			/*if (this.player.hasPower("CameliaPower")) {AbstractDungeon.actionManager.addToTop(
					new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.block*this.player.getPower("CameliaPower").amount));
			}*/
		}
		
	this.isDone = true;
	}
}
