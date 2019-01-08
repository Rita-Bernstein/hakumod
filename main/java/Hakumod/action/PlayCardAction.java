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
import Hakumod.powers.Haku_DefensePower;

import Hakumod.relics.SixHeroes;

public class PlayCardAction extends AbstractGameAction{
	
	private AbstractPlayer player;
	private AbstractCard card;
	private AbstractMonster target;
	private String effect;
	private int magnitude;
		
	public PlayCardAction(AbstractPlayer p, AbstractCard c, AbstractMonster target)
	{
		this.player = p;
		this.card = c;
		this.target = target;
		this.duration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_XFAST;
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
	}
	
	@Override
	public void update() {
		this.player.useCard(this.card, this.target, 0);
		this.isDone = true;
	}
}
