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
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import Hakumod.powers.Haku_OffensePower;
import Hakumod.powers.Haku_NeutralPower;
import Hakumod.cards.Hakumen.Haku_Renka;
import Hakumod.powers.Haku_DefensePower;

public class NegateAction extends AbstractGameAction{
	
	private AbstractPlayer player;
	private AbstractCard card;
	private AbstractMonster target;
	private String effect;
	private int magnitude;
	
	
	public NegateAction(AbstractPlayer p, AbstractCard c, AbstractMonster target , String effect, int magnitude)
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
		AbstractMonster.Intent[] arrayEffectIntents = {AbstractMonster.Intent.ATTACK_DEBUFF, AbstractMonster.Intent.DEBUFF, AbstractMonster.Intent.DEFEND_DEBUFF, AbstractMonster.Intent.STRONG_DEBUFF};
		
		if (Arrays.asList(arrayEffectIntents).contains(this.target.intent)){
			new UtilsApplyEffect(this.player, this.card, this.target, this.effect, this.magnitude);
		}
	this.isDone = true;
	}
}
