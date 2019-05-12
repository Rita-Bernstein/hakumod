package Hakumod.cards.Hakumen.special;

import Hakumod.action.ParryAction;
import Hakumod.action.UsingSpecialAction;
import Hakumod.action.UtilsApplyEffect;
import Hakumod.cards.Hakumen.abstracts.SpecialParryCard;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CounterAssault extends SpecialParryCard {
	public static final String ID = makeCardID(CounterAssault.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

	private static final int COST = 1;
	private static int MAGATAMA_COST = 4;
	private static final int BLOCK = 10;
	private static final int UPGRADE_PLUS_BLOCK = 3;

	
	public CounterAssault() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ENEMY,
				MAGATAMA_COST);

		this.baseBlock = BLOCK;
		this.tags.add(CustomTags.PARRY);
		this.tags.add(CustomTags.SPECIAL);
		this.exhaust = true;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
    	act( new UsingSpecialAction(p, MAGATAMA_COST));
    	act(new ParryAction(p, null, m, UtilsApplyEffect.BLOCK, this.block));
    	act(new ParryAction(p, null, m, UtilsApplyEffect.CURE_ALL, 1));
	}
	
	public AbstractCard makeCopy() {
		return new CounterAssault();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
			//this.rawDescription = UPG_DESCRIPTION;
			//upgradeMagicNumber(UPGRADE_BUFF);
			//initializeDescription();
		}
	}
}
