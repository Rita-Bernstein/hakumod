package Hakumod.cards.Hakumen;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import Hakumod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

public class Haku_Fuzzy extends CustomCard{
	public static final String ID = "Haku_Fuzzy";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	//public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_Fuzzy.png";
	
	private static final int COST = 1;
	private static final int BLOCK = 5;
	private static final int UPGRADE_PLUS_BLOCK = 2;
	
	private static int DEBUFF = 1;
	private static int UPGRADE_DEBUFF = 1;
	 
	
	public Haku_Fuzzy() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.ENEMY);

		this.baseBlock = BLOCK;
		this.magicNumber = this.baseMagicNumber = DEBUFF;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
		//AbstractDungeon.actionManager.addToBottom(new NegateAction(p, null, m, "offense", 1));
		/*AbstractDungeon.actionManager.addToTop(
				new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,
						new OffensePower(AbstractDungeon.player,this.magicNumber, false),this.magicNumber)); 	
		*/
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new StrengthPower(m, -this.magicNumber), -this.magicNumber));
		if (!m.hasPower("Artifact")) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new GainStrengthPower(m, this.magicNumber), this.magicNumber));
		}
	}
	
	public AbstractCard makeCopy() {
		return new Haku_Fuzzy();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
			//this.rawDescription = UPG_DESCRIPTION;
			upgradeMagicNumber(UPGRADE_DEBUFF);
			initializeDescription();
		}
	}
}
