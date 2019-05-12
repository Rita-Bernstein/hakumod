package Hakumod.cards.Hakumen.gatling;

import Hakumod.action.ComboAction;
import Hakumod.cards.Hakumen.abstracts.HakuCustomCard;
import Hakumod.cards.Hakumen.special.Renka;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SmartSteer extends HakuCustomCard {

	public static final String ID = makeCardID(SmartSteer.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;

	private static final int COST = 1;
	private static final int ATTACK_DMG = 6;
	private static final int UPGRADE_PLUS_DMG = 2;

	public SmartSteer() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.ATTACK,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.ENEMY,
				new Renka().makeCopy());
		this.baseDamage = ATTACK_DMG;
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
    	damage(m, p, this.damage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
    	AbstractCard c = new Renka().makeCopy();
    	AbstractDungeon.actionManager.addToBottom(new ComboAction(c));
    }
	
	public AbstractCard makeCopy() {
		return new SmartSteer();
	}
	
	
}