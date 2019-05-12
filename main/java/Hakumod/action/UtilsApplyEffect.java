package Hakumod.action;

import Hakumod.orbs.VoidOrb;
import Hakumod.patches.CustomTags;
import Hakumod.powers.player.DefensePower;
import Hakumod.powers.player.MagatamaPower;
import Hakumod.powers.player.OffensePower;
import Hakumod.powers.player.OverdrivePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;

import java.util.ArrayList;

public class UtilsApplyEffect {

	private AbstractPlayer player;
	private AbstractCard card;
	private AbstractMonster target;
	private String effect;
	private int magnitude;
	
	public static final String ARTIFACT = "artifact";
	public static final String BUFFER = "buffer";
	public static final String INTANGIBLE = "intangible";
	public static final String ATTACK = "attack";
	public static final String ATTACK_ALL = "attack_all";
	public static final String BLOCK = "block";
	public static final String NEXT_TURN_BLOCK = "next_block";
	public static final String DRAW = "draw";
	public static final String NEXT_TURN_DRAW = "next_draw";
	public static final String OFFENSE = "offense";
	public static final String DEFENSE = "defense";
	public static final String OD = "od";
	public static final String CURE_ALL = "cure_all";
	public static final String PLATED = "plated";
	public static final String THORN = "thorn";
	public static final String MAGATAMA = "magatama";
	public static final String ENERGY = "energy";
	public static final String NEXT_TURN_ENERGY = "next_energy";
	public static final String REDUCE_SPECIAL_COST = "5C";
	public static final String TEMP_DEXTERITY = "blocking";
	public static final String STRENGTH ="strength";
	public static final String STRENGTH_FOR_TURN ="strength_turn";
	public static final String DEXTERITY ="dexterity";
	public static final String WEAK ="weak";
	public static final String VULNERABLE ="vulnerable";
	public static final String FUUMAJIN ="fuumajin";
	
	

	public UtilsApplyEffect(AbstractPlayer player, AbstractCard card, AbstractMonster target, String effect,
			int magnitude) {
		
		this.player = player;
		this.card = card;
		this.target = target;
		this.effect = effect;
		this.magnitude = magnitude;

		switch (this.effect) {
		
			case ARTIFACT:
				AbstractDungeon.actionManager.addToBottom(
						new ApplyPowerAction(this.player, this.player, new ArtifactPower(this.player, this.magnitude)));
				break;
			case BUFFER:
				AbstractDungeon.actionManager.addToBottom(
						new ApplyPowerAction(this.player, this.player, new BufferPower(this.player, this.magnitude)));
				break;
			case INTANGIBLE:
				AbstractDungeon.actionManager.addToBottom(
						new ApplyPowerAction(this.player, this.player, new IntangiblePlayerPower(this.player, this.magnitude), this.magnitude));
				break;
			case ATTACK:
				AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(
						this.target, new DamageInfo(this.player, this.magnitude, DamageType.HP_LOSS),
						AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
				break;

			case ATTACK_ALL:
				for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
					if ((mo != null) && (!mo.isDeadOrEscaped())) {

					AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(
							mo, new DamageInfo(this.player, this.magnitude),
							AbstractGameAction.AttackEffect.SLASH_DIAGONAL));

					}
				}
				break;

			case BLOCK:
				AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.player, this.player, this.magnitude));
				break;

			case NEXT_TURN_BLOCK:
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.player, this.player,
						new NextTurnBlockPower(this.player, this.magnitude), this.magnitude));
				break;

			case DRAW:
				AbstractDungeon.actionManager.addToBottom(new DrawCardAction(this.player, this.magnitude));
				break;
			case NEXT_TURN_DRAW:
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.player, this.player,
					new DrawCardNextTurnPower(this.player, this.magnitude), this.magnitude));
				break;
			case OFFENSE:
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.player, this.player,
						new OffensePower(this.player, this.magnitude, false), this.magnitude));
				break;

			case DEFENSE:
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.player, this.player,
						new DefensePower(this.player, this.magnitude, false), this.magnitude));
				break;
			case OD:
				AbstractDungeon.actionManager.addToBottom(
						new ApplyPowerAction(this.player, this.player, new OverdrivePower(this.player, this.magnitude), this.magnitude));
				break;

			case CURE_ALL:
				ArrayList<AbstractPower> listDebuffs = new ArrayList<AbstractPower>();

				for (AbstractPower pow : this.player.powers) {
					if (pow.type == AbstractPower.PowerType.DEBUFF && (pow.ID != GainStrengthPower.POWER_ID) ) {
						listDebuffs.add(pow);
					}
				}

				for (AbstractPower debuff : listDebuffs) {
					AbstractDungeon.actionManager.addToBottom(
							new RemoveSpecificPowerAction(this.player, this.player, debuff));
				}
				break;

			case PLATED:
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.player, this.player,
						new PlatedArmorPower(this.player, this.magnitude), this.magnitude));
				break;

			case THORN:
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.player, this.player,
						new ThornsPower(this.player, this.magnitude), this.magnitude));
				break;

			case MAGATAMA:
				AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
						new MagatamaPower(AbstractDungeon.player, this.magnitude), this.magnitude));
				break;

			case ENERGY:
				AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.magnitude));
				break;

			case NEXT_TURN_ENERGY:
				AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
						new EnergizedBluePower(AbstractDungeon.player, this.magnitude), this.magnitude));
				break;

			case REDUCE_SPECIAL_COST:
				for (AbstractCard cardIsSpecial: this.player.hand.group) {
					if (cardIsSpecial.hasTag(CustomTags.SPECIAL)) {
						cardIsSpecial.setCostForTurn(0);
						if (!this.card.upgraded) {break;}
					}
				}
				break;
			case TEMP_DEXTERITY:
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.player, this.player, new DexterityPower(this.player, this.magnitude), this.magnitude));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.player, this.player, new LoseDexterityPower(this.player, this.magnitude), this.magnitude));
				break;

			case STRENGTH:
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.player, this.player,
						new StrengthPower(this.player, this.magnitude), this.magnitude));
				break;

			case STRENGTH_FOR_TURN:
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.player, this.player, new StrengthPower(this.player, this.magnitude), this.magnitude));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.player, this.player, new LoseStrengthPower(this.player, this.magnitude), this.magnitude));
				break;
			case DEXTERITY:
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.player, this.player,
						new DexterityPower(this.player, this.magnitude), this.magnitude));
				break;
			case WEAK:
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.player,
						new WeakPower(this.target, this.magnitude, false), this.magnitude));
				break;
			case VULNERABLE:
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.player,
						new VulnerablePower(this.target, this.magnitude, false), this.magnitude));
				break;
			case FUUMAJIN:
				AbstractDungeon.actionManager.addToBottom(new ChannelAction(new VoidOrb()));
				break;
			default:
				break;
		}
	}

}