package Hakumod.cards.Hakumen.normal;

import Hakumod.cards.Hakumen.abstracts.HakuCustomCard;
import Hakumod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Grab extends HakuCustomCard {

	public static final String ID = makeCardID(Grab.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;

	private static final int COST = 1;
	private static final int ATTACK_DMG = 7;
	private static final int UPGRADE_PLUS_DMG = 3;
	private static final int DAMAGE_BOOST = 5;
	    
	public Grab() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.ATTACK,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.ENEMY);
		this.baseDamage = ATTACK_DMG;
		this.exhaust = true;
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
    	int damageBoost = 0;
    	if (m.currentBlock > 0) {
    		act(new RemoveAllBlockAction(m, p));
    		damageBoost = DAMAGE_BOOST;
    	}
    	act(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, this.damage+damageBoost, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SMASH));
    }
	
	public AbstractCard makeCopy() {
		return new Grab();
	}
}