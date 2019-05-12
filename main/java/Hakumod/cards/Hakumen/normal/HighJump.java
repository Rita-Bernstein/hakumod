package Hakumod.cards.Hakumen.normal;

import Hakumod.cards.Hakumen.abstracts.HakuCustomCard;
import Hakumod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HighJump extends HakuCustomCard {
	public static final String ID = makeCardID(HighJump.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;

	private static final int COST = 1;
	private static final int BLOCK = 0;
	private static final int BUFF = 1;
	private static final int UPGRADE_BUFF = 1;
	    
	public HighJump() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.SKILL, 
				AbstractCardEnum.HAKUMEN_COLOR, 
				AbstractCard.CardRarity.UNCOMMON, 
				AbstractCard.CardTarget.SELF);
		this.baseBlock = BLOCK;
		this.magicNumber = this.baseMagicNumber = BUFF;
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
		act(new DrawCardAction(p, this.magicNumber));
    	int block = this.baseBlock + p.hand.size() + this.magicNumber;  
    	act(new GainBlockAction(p, p, block));
    }
	
	public AbstractCard makeCopy() {
		return new HighJump();
	}	
}
