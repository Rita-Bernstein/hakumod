package Hakumod.powers;

import Hakumod.orbs.Haku_VoidOrb;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;

//import com.badlogic.gdx.graphics.g2d.TextureAtlas;


public class Haku_YomotsuhirasakaPower extends AbstractPower {
	public static final String POWER_ID = "Haku_YomotsuhirasakaPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	//public static TextureAtlas powerAltas = BlackRuseMod.getPowerTextureAtlas();
	
	public Haku_YomotsuhirasakaPower(AbstractCreature owner, int amount) {
			this.name = NAME;
			this.ID = POWER_ID;
			this.owner = owner;
			this.amount = amount;
			this.isTurnBased = true;
			this.img = new Texture("Hakumod/img/powers/yomotsuhirasaka.png");
			updateDescription();
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.megacrit.cardcrawl.powers.AbstractPower#onChannel(com.megacrit.cardcrawl.orbs.AbstractOrb)
	 */
	@Override
	public void onChannel(AbstractOrb orb) {
		// TODO Auto-generated method stub
		super.onChannel(orb);
		
		if (orb.ID == Haku_VoidOrb.ID){
			
			for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
    			if ((mo != null) && (!mo.isDeadOrEscaped())) {
			  
				AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(
						mo, new DamageInfo(AbstractDungeon.player, this.amount, DamageInfo.DamageType.HP_LOSS),
						AbstractGameAction.AttackEffect.FIRE));
				
    			}
			}
		}
		
	}



	public void updateDescription() {
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
	}
	
}
