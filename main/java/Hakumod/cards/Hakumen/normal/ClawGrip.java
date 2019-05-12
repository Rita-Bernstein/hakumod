package Hakumod.cards.Hakumen.normal;

import Hakumod.cards.Hakumen.abstracts.HakuCustomCard;
import Hakumod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ClawGrip extends HakuCustomCard {

	public static final String ID = makeCardID(ClawGrip.class.getSimpleName());
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;

	private static final int COST = 0;
	private static final int ATTACK_DMG = 4;
	private static final int UPGRADE_PLUS_DMG = 2;

	public ClawGrip() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.ATTACK,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.COMMON, 
				AbstractCard.CardTarget.ENEMY);
		this.baseDamage = ATTACK_DMG;
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
    	act(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

    	for (AbstractCard cardInDeck:p.drawPile.group) {
			if (cardInDeck instanceof ClawGrip){
				p.hand.addToHand(cardInDeck);
				p.drawPile.removeCard(cardInDeck);
				p.hand.refreshHandLayout();
				break;
			}
		}
    }
	
	public AbstractCard makeCopy() {
		return new ClawGrip();
	}
	
	
}