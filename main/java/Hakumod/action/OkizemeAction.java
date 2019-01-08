package Hakumod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
//import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Hakumod.cards.Hakumen.Haku_Renka;
import Hakumod.powers.Haku_NeutralPower;
import Hakumod.powers.Haku_OffensePower;
import Hakumod.powers.Haku_DefensePower;

import Hakumod.action.UtilsApplyEffect;


public class OkizemeAction extends AbstractGameAction{
	
	private AbstractPlayer player;
	private AbstractCard card;
	private AbstractMonster target;
	private String effect;
	private int magnitude;
	private final int block = 6;
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
		if (this.player.hasPower("Haku_ScienceFictionPower")) {
			this.magnitude = this.magnitude + this.player.getPower("Haku_ScienceFictionPower").amount;	
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