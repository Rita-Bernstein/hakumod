package Hakumod.powers;

import java.util.Arrays;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;


public class Haku_NegativePenalityPower extends AbstractPower {
	public static final String POWER_ID = "Haku_CameliaPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	
	public Haku_NegativePenalityPower(AbstractCreature owner, int amount) {
			this.name = NAME;
			this.ID = POWER_ID;
			this.owner = owner;
			this.amount = amount;
			this.isTurnBased = true;
			this.img = new Texture("Hakumod/img/powers/explosive.png");
			updateDescription();
	}
	
	
	/* (non-Javadoc)
	 * @see com.megacrit.cardcrawl.powers.AbstractPower#atStartOfTurn()
	 */
	@Override
	public void atStartOfTurn() {
		// TODO Auto-generated method stub
		//super.atStartOfTurn();
		AbstractMonster.Intent[] arrayAttackIntents = {AbstractMonster.Intent.ATTACK, AbstractMonster.Intent.ATTACK_BUFF, AbstractMonster.Intent.ATTACK_DEBUFF, AbstractMonster.Intent.ATTACK_DEFEND};
		
		for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			if ((mo != null) && (!mo.isDeadOrEscaped() && (!Arrays.asList(arrayAttackIntents).contains(mo.intent)))) {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, AbstractDungeon.player,
						new VulnerablePower(mo, 1, false), 1));
			}
		}
	}


	public void updateDescription() {
		this.description = (DESCRIPTIONS[0]);
	}
	
}
