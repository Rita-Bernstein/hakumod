package Hakumod.cards.Hakumen.special;

import Hakumod.action.UsingSpecialAction;
import Hakumod.cards.Hakumen.abstracts.SpecialCard;
import Hakumod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Zantetsu extends SpecialCard {

	public static final String ID = makeCardID(Zantetsu.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

	private static final int COST = 1;
	private static final int ATTACK_DMG = 18;
	private static final int UPGRADE_PLUS_DMG = 4;
	public final static int MAGATAMA_COST = 3;
	private static final int ENERGY_BONUS = 1;

	public Zantetsu() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.ATTACK,
				AbstractCardEnum.HAKUMEN_COLOR, 
				AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.ENEMY,
				MAGATAMA_COST);
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = ENERGY_BONUS;
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		act( new UsingSpecialAction(p, MAGATAMA_COST));
    	act(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SLASH_HEAVY));
    	act(new GainEnergyAction(this.magicNumber));
    }
	
	public AbstractCard makeCopy() {
		return new Zantetsu();
	}
}