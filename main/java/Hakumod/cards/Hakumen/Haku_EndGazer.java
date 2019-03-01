package Hakumod.cards.Hakumen;

import Hakumod.action.OkizemeAction;
import Hakumod.action.UtilsApplyEffect;
import Hakumod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Haku_EndGazer extends CustomCard{
	public static final String ID = "Haku_EndGazer";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	//public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_EndGazer.png";
	
	private static final int COST = 1;
	/*private static final int LOSE_BLOCK = 4;*/

	private static final int BLOCK = 6;
	private static final int UPGRADE_BLOCK = 3;

	private static final int DRAW = 2;


	public Haku_EndGazer() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.SELF);

		this.baseBlock = BLOCK;
		//this.magicNumber = this.baseMagicNumber = LOSE_BLOCK;
		this.magicNumber = this.baseMagicNumber = DRAW;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		//Removing the card fro the calculation
		/*int currentBlock = p.currentBlock;
		if (currentBlock > -LOSE_BLOCK) {
			AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, -this.magicNumber));
		}
		else {
			AbstractDungeon.actionManager.addToBottom(new RemoveAllBlockAction(p, p));	
		}
		
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
				new NextTurnBlockPower(p, currentBlock), currentBlock));
		*/

		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));

		AbstractDungeon.actionManager.addToTop(
				new OkizemeAction(p, this, m, UtilsApplyEffect.NEXT_TURN_DRAW, this.magicNumber));
	}
	
	public AbstractCard makeCopy() {
		return new Haku_EndGazer();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.upgradeBlock(UPGRADE_BLOCK);
			//this.rawDescription = UPG_DESCRIPTION;
			initializeDescription();
		}
	}

}
