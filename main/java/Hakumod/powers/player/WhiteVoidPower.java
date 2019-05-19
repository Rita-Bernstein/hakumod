package Hakumod.powers.player;

import Hakumod.cards.Hakumen.normal.WhiteVoid;
import Hakumod.powers.abstracts.AbstractHakuPower;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public class WhiteVoidPower extends AbstractHakuPower {
	private static final String CLASS_NAME = WhiteVoidPower.class.getSimpleName();
	public static final String POWER_ID = makePowerID(CLASS_NAME);

	private boolean playedThisTurn = false;
	private String[] arrayBonus =
		{ 	
				"Gain 1 Magatama.",
				"Gain 2 Plated Armor.", 
				"Gain 1 Strength.",
				"Gain 2 Thorns.",
				"All enemies lose 2 Strengths.",
				"Draw 1 more card at the start of each turn.",
				"Gain 1 additional [R] at the start of each turn.", 
				"???", 
		};
	
	private static final int MAGATAMA_BUFF = 1;
	private static final int PLATED_BUFF = 2;
	private static final int STRENGTH_BUFF = 1;
	private static final int THORNS_BUFF = 2;
	private static final int STRENGTH_DEBUFF = -2;
	
	private static final int DRAW_BUFF = 1;
	private static final int ENERGY_BUFF = 1;
	private static final int YOU_ARE_BROKEN = 10;
	
	private String[] DIALOG = 
		{
				"I am the white void.",
				"I am the cold steel.",
				"I am the just sword.",
				"With blade in hand...",
				"Shall I reap the sins from this world...",
				"And cleanse it in the fires of destruction!",
				"I am Haku-men.",
				"The end has come!"
		};

	public WhiteVoidPower(AbstractCreature owner, int amount) {
		super(owner, amount, CLASS_NAME);
		updateDescription();

		if (!owner.hasPower(POWER_ID) && this.amount < 2) {
			act(new TalkAction(true, DIALOG[0], 1.0F, 2.0F));
			
			power(AbstractDungeon.player, AbstractDungeon.player,
					new MagatamaPower(AbstractDungeon.player, MAGATAMA_BUFF), MAGATAMA_BUFF);
		}
		
		this.playedThisTurn = true;
	}

	@Override
	public void atEndOfRound() {
		if (this.playedThisTurn && this.amount < 8) {
			act(
					new MakeTempCardInDrawPileAction(new WhiteVoid(), 1, false, true, false)
			);
			this.playedThisTurn = false;
		}
	}



	public void stackPower(int stackAmount) {
		this.playedThisTurn = true;
		this.amount += stackAmount;

		if (this.amount<=DIALOG.length) {
			act(new TalkAction(true, DIALOG[this.amount-1], 1.0F, 2.0F));
		}
		
		switch (this.amount) {
			//stackAmount isn't called the first time power is applied.
			case 1:
				break;
			case 2:
				power(AbstractDungeon.player, AbstractDungeon.player,
						new PlatedArmorPower(this.owner, PLATED_BUFF), PLATED_BUFF);
				break;
			case 3:
				power(AbstractDungeon.player, AbstractDungeon.player,
						new StrengthPower(AbstractDungeon.player, STRENGTH_BUFF), STRENGTH_BUFF);
				break;
			case 4:
				power(AbstractDungeon.player, AbstractDungeon.player,
						new ThornsPower(this.owner, THORNS_BUFF), THORNS_BUFF);
				break;
			case 5:
				for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
	    			if ((mo != null) && (!mo.isDeadOrEscaped())) {
	    				power(mo, AbstractDungeon.player,
	    						new StrengthPower(mo, STRENGTH_DEBUFF), STRENGTH_DEBUFF);
	    			}
				}
				break;
			case 6:
				power(AbstractDungeon.player, AbstractDungeon.player,
						new DrawPower(this.owner, DRAW_BUFF), DRAW_BUFF);
				break;
			case 7:
				power(AbstractDungeon.player, AbstractDungeon.player,
						new BerserkPower("White void", this.owner, ENERGY_BUFF), ENERGY_BUFF);
				break;
			case 8:
				//GG well played, thanks for the matches.
				power(AbstractDungeon.player, AbstractDungeon.player,
						new StrengthPower(AbstractDungeon.player, YOU_ARE_BROKEN), YOU_ARE_BROKEN);
				power(AbstractDungeon.player, AbstractDungeon.player,
						new DexterityPower(AbstractDungeon.player, YOU_ARE_BROKEN), YOU_ARE_BROKEN);
				break;	
			default:
				break;
		}
	}

	public void updateDescription() {
		String[] powerDesc = languagePack.getPowerStrings(this.ID).DESCRIPTIONS;
		String stringNextBonus = "None.";
		
		int bonusCap = (this.amount > arrayBonus.length-1) ?  arrayBonus.length-1 : this.amount;
		stringNextBonus = arrayBonus[bonusCap];
		
		this.description = (powerDesc[0] + this.amount + powerDesc[1] + stringNextBonus);
	}
}
