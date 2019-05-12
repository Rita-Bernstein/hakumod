package Hakumod.cards.Hakumen.special;

import Hakumod.action.UsingSpecialAction;
import Hakumod.cards.Hakumen.abstracts.SpecialCard;
import Hakumod.patches.AbstractCardEnum;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

public class Shippu extends SpecialCard {

	public static final String ID = makeCardID(Shippu.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;

	private static final int COST = -1;
	private static final int ATTACK_DMG = 10;
	private static final int UPGRADE_PLUS_DMG = 2;
	public final static int MAGATAMA_COST = 4;

	public Shippu() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.ATTACK,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ALL_ENEMY,
				MAGATAMA_COST);
		this.baseDamage = ATTACK_DMG;
		this.retain = true;
	}

	@Override
	public void atTurnStart() {
		super.atTurnStart();
		this.retain = true;
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {		
		act( new UsingSpecialAction(p, MAGATAMA_COST));
		
		int energyConsumed = this.energyOnUse;
		if (p.hasRelic(ChemicalX.ID)) {
			p.getRelic(ChemicalX.ID).flash();
			energyConsumed += 2;
		}
    	
    	int ShippuDamage =  this.damage*energyConsumed;
    	boolean hasUsedEffect = false;
    	
		for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			if ((mo != null) && (!mo.isDeadOrEscaped())) {
					if (!hasUsedEffect) {
						act(new VFXAction(new WeightyImpactEffect(
								mo.hb.cX, mo.hb.cY, Color.SKY.cpy())));
						act(new WaitAction(0.5F));
						hasUsedEffect = true;
					}
					
    				act(new com.megacrit.cardcrawl.actions.common.DamageAction(mo,
    						new DamageInfo(p, ShippuDamage, this.damageTypeForTurn),
    						AbstractGameAction.AttackEffect.SLASH_HEAVY));
			}
		}
		
		act(new LoseEnergyAction(this.energyOnUse));
    }

	public AbstractCard makeCopy() {
		return new Shippu();
	}
}