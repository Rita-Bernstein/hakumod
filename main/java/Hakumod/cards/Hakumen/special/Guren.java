package Hakumod.cards.Hakumen.special;

import Hakumod.action.ComboAction;
import Hakumod.action.UsingSpecialAction;
import Hakumod.cards.Hakumen.abstracts.SpecialCard;
import Hakumod.cards.Hakumen.ender.Sweep;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Guren extends SpecialCard {

	public static final String ID = makeCardID(Guren.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;

	private static final int COST = 1;
	private static final int ATTACK_DMG = 9;
	private static final int UPGRADE_PLUS_DMG = 2;
	public final static int MAGATAMA_COST = 1;

	public Guren() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.ATTACK,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.ENEMY,
				new Sweep().makeCopy(),
				MAGATAMA_COST);
		this.baseDamage = ATTACK_DMG;
		this.tags.add(CustomTags.SPECIAL);
		this.tags.add(CustomTags.COMBO);
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
		act(new UsingSpecialAction(p, MAGATAMA_COST));
		damage(m, p, this.damage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    	AbstractCard c = new Sweep().makeCopy();
    	act(new ComboAction(c));
    }
	
	public AbstractCard makeCopy() {
		return new Guren();
	}
}