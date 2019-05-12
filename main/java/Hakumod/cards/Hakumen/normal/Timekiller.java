package Hakumod.cards.Hakumen.normal;

import Hakumod.action.AddToHandAction;
import Hakumod.action.ChooseCardFromDeckAction;
import Hakumod.action.ChooseCardFromExhaustAction;
import Hakumod.cards.Hakumen.abstracts.HakuCustomCard;
import Hakumod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class Timekiller extends HakuCustomCard {
	public static final String ID = makeCardID(Timekiller.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;

	private static final int COST = 1;
	private static final int ATTACK_DMG = 5;
	private static final int UPGRADE_PLUS_DMG = 3;
	private static final int BUFF = 1;
	
	public Timekiller() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				CardType.ATTACK,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.RARE,
				AbstractCard.CardTarget.ENEMY);
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = BUFF;
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
    	act(new DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.FIRE));
    	//act(new TimekillerAction(this.magicNumber));

		ArrayList<AbstractCard> exhaustPile = p.exhaustPile.group;
		ArrayList<AbstractCard> arrayAttacksinExhaust = new ArrayList<AbstractCard>();
		for (AbstractCard c : exhaustPile) {
			if (c.type == AbstractCard.CardType.ATTACK) {
				arrayAttacksinExhaust.add(c);
			}
		}

		if (arrayAttacksinExhaust.size() > 0) {
			if (arrayAttacksinExhaust.size() == 1) {
				act(
						new AddToHandAction(p.drawPile, arrayAttacksinExhaust.get(0), true, false, true, true, 0));
			}
			else {
				act(
						new ChooseCardFromExhaustAction(arrayAttacksinExhaust, false, true, true, 0, true));
			}
		}
    }
	
	public AbstractCard makeCopy() {
		return new Timekiller();
	}
}