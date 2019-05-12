package Hakumod.cards.Hakumen.token;

import Hakumod.action.ChooseCardFromDeckAction;
import Hakumod.cards.Hakumen.abstracts.HakuCustomCard;
import Hakumod.cards.Hakumen.ender.CrusadeSeraphim;
import Hakumod.cards.Hakumen.special.Agito;
import Hakumod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class Staircase extends HakuCustomCard
{
	public static final String ID = makeCardID(Staircase.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;

	private static final int COST = 0;
	private static final int ATTACK_DMG = 1;
	private static final int BUFF = 4;
	private static final int UPGRADE_BUFF = 1;
	
	public Staircase() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.ATTACK,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.ENEMY);
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = BUFF;
		this.exhaustOnUseOnce = true;
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_BUFF);
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		damage(m, p, this.damage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
		damage(m, p, this.damage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
		damage(m, p, this.damage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
		damage(m, p, this.damage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
    	if (this.upgraded) {
			damage(m, p, this.damage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
    	}
    	
    	AbstractCard cardAgito = new Agito();
    	AbstractCard cardJC = new CrusadeSeraphim();
    	
    	ArrayList<AbstractCard> cardChoice = new ArrayList<AbstractCard>();
    	cardChoice.add(cardJC);
    	cardChoice.add(cardAgito);
    	
		act(new ChooseCardFromDeckAction( cardChoice, this.upgraded, true, true, 0, false));
    }
	
	public AbstractCard makeCopy() {
		return new Staircase();
	}
}
