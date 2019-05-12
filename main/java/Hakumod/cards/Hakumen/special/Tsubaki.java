package Hakumod.cards.Hakumen.special;

import Hakumod.action.UsingSpecialAction;
import Hakumod.cards.Hakumen.abstracts.SpecialCard;
import Hakumod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
//import com.megacrit.cardcrawl.localization.CardStrings;


public class Tsubaki extends SpecialCard {

	public static final String ID = makeCardID(Tsubaki.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	private static final int COST = 0;
	private static final int ATTACK_DMG = 20;
	private static final int UPGRADE_PLUS_DMG = 4;
	private static int MAGATAMA_COST = 3;
	
	public Tsubaki() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.ATTACK, 
				AbstractCardEnum.HAKUMEN_COLOR, 
				AbstractCard.CardRarity.UNCOMMON, 
				AbstractCard.CardTarget.ENEMY, 
				MAGATAMA_COST);
		this.baseDamage = ATTACK_DMG;
		this.exhaustOnUseOnce = true;
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			this.rawDescription = UPG_DESCRIPTION;
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		act( new UsingSpecialAction(p, MAGATAMA_COST));
    	act(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SLASH_HEAVY));
    	AbstractCard cardToAdd = new Tsubaki().makeCopy();
    	if (this.upgraded) {cardToAdd.upgrade();}
    	p.drawPile.addToTop(cardToAdd);
    }
	
	public AbstractCard makeCopy() {
		return new Tsubaki();
	}
}