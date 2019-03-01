package Hakumod.cards.Hakumen;

import Hakumod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FadingPower;

//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
//import com.megacrit.cardcrawl.localization.CardStrings;

public class Haku_Timeout extends CustomCard{

	public static final String ID = "Haku_Timeout";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	//public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Strike.png";
	private static final int COST = 0;
	
	private static int DEBUFF = 5;
	private static int UPGRADE_DEBUFF = -1;
	
	public Haku_Timeout() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.ATTACK,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.ENEMY);
		// TODO Auto-generated constructor stub
		this.magicNumber = DEBUFF;
		this.baseMagicNumber = DEBUFF;
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_DEBUFF);
	
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	if (m.hasPower("FasingPower")) {
    		m.getPower("FadingPower").amount -= 1;
    	}
    	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p,
				new FadingPower(m, this.magicNumber), this.magicNumber));
    }
	
	public AbstractCard makeCopy() {
		return new Haku_Timeout();
	}
	
	
	
}