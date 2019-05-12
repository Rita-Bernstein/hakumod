package Hakumod.cards.Hakumen.special;

import Hakumod.action.UsingSpecialAction;
import Hakumod.cards.Hakumen.abstracts.SpecialCard;
import Hakumod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.vfx.combat.GoldenSlashEffect;

public class Akumetsu extends SpecialCard {
	public static final String ID = makeCardID(Akumetsu.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;

	private static final int COST = -1;
	private static final int ATTACK_DMG = 12;
	private static final int UPGRADE_PLUS_DMG = 4;
	private static final int INTANGIBLE = 1;
	private static final int MAGATAMA_COST = 8;
	
	public Akumetsu() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.RARE,
				AbstractCard.CardTarget.ENEMY,
				MAGATAMA_COST);
		this.baseDamage = ATTACK_DMG;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		act( new UsingSpecialAction(p, MAGATAMA_COST));
		
		int energyConsumed = this.energyOnUse;
		if (p.hasRelic(ChemicalX.ID)) {
			p.getRelic(ChemicalX.ID).flash();
			energyConsumed += 2;
		}
    	int AkumetsuDamage =  this.damage*energyConsumed;

		boolean hasUsedEffect = false;
		for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			if ((mo != null) && (!mo.isDeadOrEscaped())) {
				if (!hasUsedEffect) {
					act(new VFXAction(new GoldenSlashEffect(
							mo.hb.cX, mo.hb.cY, true)));
					act(new WaitAction(0.5F));
					hasUsedEffect = true;
				}

				act(new com.megacrit.cardcrawl.actions.common.DamageAction(mo,
						new DamageInfo(p, AkumetsuDamage, this.damageTypeForTurn),
						AbstractGameAction.AttackEffect.SLASH_HEAVY));
			}
		}
		act(
				new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, INTANGIBLE), INTANGIBLE));
    	act(new LoseEnergyAction(this.energyOnUse));
	}
	
	public AbstractCard makeCopy() {
		return new Akumetsu();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			initializeDescription();
		}
	}
}
