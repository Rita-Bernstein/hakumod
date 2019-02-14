package Hakumod.action;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.EnergizedBluePower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import Hakumod.cards.Hakumen.Haku_Renka;
import Hakumod.cards.Hakumen.Haku_Tsubaki;
import Hakumod.patches.CustomTags;
import Hakumod.cards.Hakumen.Haku_Enma;
import Hakumod.cards.Hakumen.Haku_J2A;
import Hakumod.cards.Hakumen.Haku_JB;
import Hakumod.powers.Haku_DefensePower;
import Hakumod.powers.Haku_MagatamaPower;
import Hakumod.powers.Haku_NeutralPower;
import Hakumod.powers.Haku_OffensePower;
import Hakumod.powers.Haku_OverdrivePower;

public class UtilsApplyEffect {

	private AbstractPlayer player;
	private AbstractCard card;
	private AbstractMonster target;
	private String effect;
	private int magnitude;

	public UtilsApplyEffect(AbstractPlayer player, AbstractCard card, AbstractMonster target, String effect,
			int magnitude) {
		this.player = player;
		this.card = card;
		this.target = target;
		this.effect = effect;
		this.magnitude = magnitude;

		switch (this.effect) {
		
		case "artifact":
			AbstractDungeon.actionManager.addToBottom(
					new ApplyPowerAction(this.player, this.player, new ArtifactPower(this.player, this.magnitude)));
			break;
		case "buffer":
			AbstractDungeon.actionManager.addToBottom(
					new ApplyPowerAction(this.player, this.player, new BufferPower(this.player, this.magnitude)));
			break;
		case "intangible":
			AbstractDungeon.actionManager.addToBottom(
					new ApplyPowerAction(this.player, this.player, new IntangiblePlayerPower(this.player, this.magnitude), this.magnitude));
			break;
		case "attack":
			AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(
					this.target, new DamageInfo(this.player, this.magnitude),
					AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
			break;

		case "attack_all":
			for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
    			if ((mo != null) && (!mo.isDeadOrEscaped())) {
			  
				AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(
						mo, new DamageInfo(this.player, this.magnitude),
						AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
				
    			}
			}
			break;
			
		case "block":
			AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.player, this.player, this.magnitude));
			break;
		
		case "next_block":
			//AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.player, this.player, this.magnitude));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.player, this.player,
					new NextTurnBlockPower(this.player, this.magnitude), this.magnitude));
			break;
		
		case "draw":
			//AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.player, this.player, this.magnitude));
			AbstractDungeon.actionManager.addToBottom(new DrawCardAction(this.player, this.magnitude));
			break;
			
		case "offense":
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.player, this.player,
					new Haku_OffensePower(this.player, this.magnitude, false), this.magnitude));
			break;

		case "neutral":
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.player, this.player,
					new Haku_NeutralPower(this.player, this.magnitude, false), this.magnitude));
			break;

		case "defense":
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.player, this.player,
					new Haku_DefensePower(this.player, this.magnitude, false), this.magnitude));
			break;
		case "od":
			AbstractDungeon.actionManager.addToBottom(
					new ApplyPowerAction(this.player, this.player, new Haku_OverdrivePower(this.player, this.magnitude), this.magnitude));
			break;

		case "cure_weak":
			if (this.player.hasPower("Weakened")) {
				AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ReducePowerAction(
						this.player, this.player, "Weakened", this.magnitude));
			}
			break;

		case "cure_frail":
			if (this.player.hasPower("Frail")) {
				AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ReducePowerAction(
						this.player, this.player, "Frail", this.magnitude));
			}
			break;

		case "cure_vulnerable":
			if (this.player.hasPower("Vulnerable")) {
				AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ReducePowerAction(
						this.player, this.player, "Vulnerable", this.magnitude));
			}
			break;

		case "cure_all":
			ArrayList<AbstractPower> listDebuffs = new ArrayList<AbstractPower>();

			for (AbstractPower pow : this.player.powers) {
				if (pow.type == AbstractPower.PowerType.DEBUFF) {
					listDebuffs.add(pow);
				}
			}
			
			for (AbstractPower debuff : listDebuffs) {
				AbstractDungeon.actionManager.addToBottom(
						new RemoveSpecificPowerAction(this.player, this.player, debuff));
			}
			break;

		case "plated":
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.player, this.player,
					new PlatedArmorPower(this.player, this.magnitude), this.magnitude));
			break;

		case "thorn":
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.player, this.player,
					new ThornsPower(this.player, this.magnitude), this.magnitude));
			break;

		case "magatama":
			AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
					new Haku_MagatamaPower(AbstractDungeon.player, this.magnitude), this.magnitude));
			break;

		case "energy":
			AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.magnitude));
			break;

		case "next_energy":
			AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
					new EnergizedBluePower(AbstractDungeon.player, this.magnitude), this.magnitude));
			break;
		
		case "5C":
			for (AbstractCard cardIsSpecial: this.player.hand.group) {
				if (cardIsSpecial.hasTag(CustomTags.SPECIAL)) {
					cardIsSpecial.setCostForTurn(0);
					if (!this.card.upgraded) {break;}
				}
			}
			break;
		case "blocking":
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.player, this.player, new DexterityPower(this.player, this.magnitude), this.magnitude));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.player, this.player, new LoseDexterityPower(this.player, this.magnitude), this.magnitude));
			break;
		case "enma":
			AbstractCard cardEnma = new Haku_Enma().makeCopy();
			//cardEnma.setCostForTurn(0);
			//cardEnma.exhaustOnUseOnce = true;
			//this.player.useCard(cardEnma, this.target, 0);
			AbstractDungeon.actionManager.addToBottom(new ComboAction(cardEnma));
			break;

		case "renka":
			AbstractCard cardRenka = new Haku_Renka().makeCopy();
			//cardRenka.setCostForTurn(0);
			//cardRenka.exhaustOnUseOnce = true;
			//this.player.useCard(cardRenka, this.target, 0);
			AbstractDungeon.actionManager.addToBottom(new ComboAction(cardRenka));
			break;

		case "jb":
			AbstractCard cardJB = new Haku_JB().makeCopy();
			//cardJB.setCostForTurn(0);
			//cardJB.exhaustOnUseOnce = true;
			//this.player.useCard(cardJB, this.target, 0);
			AbstractDungeon.actionManager.addToBottom(new ComboAction(cardJB));
			break;
		case "j2a":
			AbstractCard cardJ2A = new Haku_J2A().makeCopy();
			//cardJB.setCostForTurn(0);
			//cardJB.exhaustOnUseOnce = true;
			//this.player.useCard(cardJB, this.target, 0);
			AbstractDungeon.actionManager.addToBottom(new ComboAction(cardJ2A));
			break;
		case "top":
			AbstractCard cardTop = this.player.drawPile.getTopCard();
			cardTop.setCostForTurn(0);
			this.player.useCard(cardTop, this.target, 0);
			cardTop.lighten(false);
			this.player.drawPile.removeCard(cardTop);
			this.player.hand.refreshHandLayout();
			break;
		case "kill":
			this.target.die();
			break;
		case "strength":
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.player, this.player,
					new StrengthPower(this.player, this.magnitude), this.magnitude));
			break;
		case "dexterity":
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.player, this.player,
					new DexterityPower(this.player, this.magnitude), this.magnitude));
			break;
		case "weak":
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.player,
					new WeakPower(this.target, this.magnitude, false), this.magnitude));
			break;
		case "vulnerable":
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.player,
					new VulnerablePower(this.target, this.magnitude, false), this.magnitude));
			break;
		default:
			break;
		}
	}

}