package Hakumod.cards.Hakumen;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
//import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

//import Hakumod.action.ComboAction;
import Hakumod.action.UsingSpecialAction;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;

public class Haku_Shippu extends Haku_Special{

	public static final String ID = "Haku_Shippu";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	//public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_Shippu.png";
	private static final int COST = -1;
	private static final int ATTACK_DMG = 10;
	private static final int UPGRADE_PLUS_DMG = 2;
	
	public final static int MAGATAMA_COST = 4;
	
	/* (non-Javadoc)
	 * @see com.megacrit.cardcrawl.cards.AbstractCard#atTurnStart()
	 */
	@Override
	public void atTurnStart() {
		// TODO Auto-generated method stub
		super.atTurnStart();
		this.retain = true;
	}

	public Haku_Shippu() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.ATTACK,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ALL_ENEMY,
				MAGATAMA_COST);
		// TODO Auto-generated constructor stub
		this.baseDamage = ATTACK_DMG;
		this.tags.add(CustomTags.SPECIAL);
		this.retain = true;

	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
	
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {		
		AbstractDungeon.actionManager.addToBottom( new UsingSpecialAction(p, MAGATAMA_COST));
		
		int energyConsumed = this.energyOnUse;
		if (p.hasRelic("Chemical X")) {
			p.getRelic("Chemical X").flash();
			energyConsumed += 2;
		}
		
    	
    	int ShippuDamage =  this.damage*energyConsumed;
    	boolean hasUsedEffect = false;
    	
		for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {

			
			
			if ((mo != null) && (!mo.isDeadOrEscaped())) {
				
					//for (int i=0;i<energyConsumed;i++) {
					if (!hasUsedEffect) {
						AbstractDungeon.actionManager.addToBottom(new VFXAction(new WeightyImpactEffect(
								mo.hb.cX, mo.hb.cY, Color.SKY.cpy())));
						AbstractDungeon.actionManager.addToBottom(new WaitAction(0.5F));
						hasUsedEffect = true;
					}
					
    				AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(mo,
    						new DamageInfo(p, ShippuDamage, this.damageTypeForTurn),
    						AbstractGameAction.AttackEffect.SLASH_HEAVY));
				//}	
			}
		}
		
		AbstractDungeon.actionManager.addToBottom(new LoseEnergyAction(this.energyOnUse));
    }
	
	public AbstractCard makeCopy() {
		return new Haku_Shippu();
	}
	
	
	
}