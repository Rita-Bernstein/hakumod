package Hakumod.cards.Hakumen.normal;

import Hakumod.action.StarterAction;
import Hakumod.action.UtilsApplyEffect;
import Hakumod.cards.Hakumen.abstracts.HakuCustomCard;
import Hakumod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Blocking extends HakuCustomCard {
	public static final String ID = makeCardID(Blocking.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

	private static final int COST = 1;
	private static final int BLOCK = 5;
	private static final int UPGRADE_PLUS_BLOCK = 3;
	private static final int BUFF = 1;
	
	public Blocking() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.BASIC,
				AbstractCard.CardTarget.SELF);
		this.baseBlock = BLOCK;
		this.magicNumber = this.baseMagicNumber = BUFF;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		act(new GainBlockAction(p, p, this.block));
	
		/*if (this.upgraded) {
    		act(new StarterAction(p, this, m, UtilsApplyEffect.TEMP_DEXTERITY, this.magicNumber));
		}*/
	}
	
	public AbstractCard makeCopy() {
		return new Blocking();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
			//this.rawDescription = UPG_DESCRIPTION;
			initializeDescription();
		}
	}

	public boolean isDefend() {
		return true;
	}
}
