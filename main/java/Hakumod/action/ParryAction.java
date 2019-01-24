package Hakumod.action;

import java.util.Arrays;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import Hakumod.powers.Haku_OffensePower;
import Hakumod.powers.Haku_NeutralPower;
import Hakumod.cards.Hakumen.Haku_BlackAndWhite;
import Hakumod.cards.Hakumen.Haku_ChildishMemories;
import Hakumod.cards.Hakumen.Haku_InJustice;
import Hakumod.powers.Haku_AwakeningPower;
import Hakumod.powers.Haku_DefensePower;

import Hakumod.relics.SixHeroes;

public class ParryAction extends AbstractGameAction{
	
	private AbstractPlayer player;
	private AbstractCard card;
	private AbstractMonster target;
	private String effect;
	private int magnitude;
		
	public ParryAction(AbstractPlayer p, AbstractCard c, AbstractMonster target , String effect, int magnitude)
	{
		this.player = p;
		this.card = c;
		this.target = target;
		this.target = target;
		this.effect = effect;
		this.magnitude = magnitude;
		this.duration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_XFAST;
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
	}
	
	@Override
	public void update() 
	{
		AbstractMonster.Intent[] arrayAttackIntents = {AbstractMonster.Intent.ATTACK, AbstractMonster.Intent.ATTACK_BUFF, AbstractMonster.Intent.ATTACK_DEBUFF, AbstractMonster.Intent.ATTACK_DEFEND};
		
		if (Arrays.asList(arrayAttackIntents).contains(this.target.intent) || player.hasPower(Haku_AwakeningPower.POWER_ID)){
			int bonus = 0;
			if (player.hasPower(Haku_AwakeningPower.POWER_ID) && (this.effect == "attack" || this.effect == "block") ) {bonus = player.getPower(Haku_AwakeningPower.POWER_ID).amount;}
			new UtilsApplyEffect(this.player, this.card, this.target, this.effect, this.magnitude+bonus);
			
			/*if (this.player.hasRelic("SixHeroes")){
				this.player.getRelic("SixHeroes").setCounter(this.player.getRelic("SixHeroes").counter + 1);
			}*/
		}
		
	this.isDone = true;
	}
}
