package Hakumod.cards.Hakumen.normal;

import Hakumod.cards.Hakumen.abstracts.HakuCustomCard;
import Hakumod.cards.Hakumen.token.Staircase;
import Hakumod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class NegativeEdge extends HakuCustomCard {

	public static final String ID = makeCardID(NegativeEdge.class.getSimpleName());
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

	private static final int COST = 2;
	private static final int ATTACK_DMG = 7;
	//private static final int UPGRADE_PLUS_DMG = 2;

	public NegativeEdge() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.ATTACK,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ENEMY,
				new Staircase().makeCopy());
		this.baseDamage = ATTACK_DMG;
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			//upgradeDamage(UPGRADE_PLUS_DMG);
			this.rawDescription = UPG_DESCRIPTION;
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	act(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    	
    	AbstractCard c = new Staircase().makeCopy();
    	c.exhaustOnUseOnce = true;
    	if (this.upgraded) {c.upgrade();}
    	act(new MakeTempCardInHandAction(c, false));
    }
	
	public AbstractCard makeCopy() {
		return new NegativeEdge();
	}
}