package Hakumod.cards.Hakumen.parry;

import Hakumod.action.ParryAction;
import Hakumod.action.UtilsApplyEffect;
import Hakumod.cards.Hakumen.abstracts.ParryCard;
import Hakumod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.ChemicalX;

public class DeadAngle extends ParryCard {
	public static final String ID = makeCardID(DeadAngle.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;

	private static final int COST = -1;
	private static final int BLOCK = 5;
	private static final int UPGRADE_PLUS_BLOCK = 1;
	private static final int ATTACK_DMG = 5;
	private static final int UPGRADE_PLUS_DMG = 1;
	
	public DeadAngle() {
		super(ID, NAME, COST, RAW_DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ENEMY);
		this.baseBlock = BLOCK;
		this.baseDamage = ATTACK_DMG;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		int energyConsumed = this.energyOnUse;
		if (p.hasRelic(ChemicalX.ID)) {
			p.getRelic(ChemicalX.ID).flash();
			energyConsumed += 2;
		}
    	
    	int totalDamage =  this.damage*energyConsumed;
    	int totalBlock =  this.block*energyConsumed;

    	act(new ParryAction(p, null, m, UtilsApplyEffect.ATTACK, totalDamage));
		act(new ParryAction(p, null, m, UtilsApplyEffect.BLOCK, totalBlock));
		act(new LoseEnergyAction(this.energyOnUse));
	}
	
	public AbstractCard makeCopy() {
		return new DeadAngle();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
			upgradeDamage(UPGRADE_PLUS_DMG);
			initializeDescription();
		}
	}
}
