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
import com.megacrit.cardcrawl.powers.VulnerablePower;

//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
//import com.megacrit.cardcrawl.localization.CardStrings;

public class Haku_Taunt extends CustomCard{

	public static final String ID = "Haku_Taunt";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_Taunt.png";
	private static final int COST = 1;
	
	private static int DEBUFF = 2;
	private static int UPGRADE_DEBUFF = 1;
	
	public Haku_Taunt() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.ALL_ENEMY);
		// TODO Auto-generated constructor stub
		this.magicNumber = DEBUFF;
		this.baseMagicNumber = DEBUFF;
		//this.exhaust = true;
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			//this.exhaust = false;
			upgradeMagicNumber(UPGRADE_DEBUFF);
			this.rawDescription = UPG_DESCRIPTION;
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
				new VulnerablePower(p, this.magicNumber, false), (this.upgraded) ? 1 : this.magicNumber));
    
		for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			if ((mo != null) && (!mo.isDeadOrEscaped())) {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p,
						new VulnerablePower(mo, this.magicNumber, false), this.magicNumber));
			}
		}
    	
    }
	
	public AbstractCard makeCopy() {
		return new Haku_Taunt();
	}
	
	
	
}