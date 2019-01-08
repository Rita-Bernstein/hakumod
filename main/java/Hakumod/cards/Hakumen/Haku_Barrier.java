package Hakumod.cards.Hakumen;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Hakumod.action.NegateAction;
import Hakumod.action.ParryAction;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
import Hakumod.powers.Haku_DefensePower;
import Hakumod.powers.Haku_MagatamaPower;
import Hakumod.powers.Haku_NeutralPower;
import basemod.abstracts.CustomCard;
import basemod.helpers.CardTags;

import com.megacrit.cardcrawl.powers.NextTurnBlockPower;

import com.megacrit.cardcrawl.powers.BlurPower;

public class Haku_Barrier extends CustomCard{
	public static final String ID = "Haku_Barrier";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	//public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_Barrier.png";
	
	private static final int COST = 1;
	private static final int BLOCK = 5;
	private static final int UPGRADE_PLUS_BLOCK = 2;
	
	private static int BUFF = 1;
	//private static int UPGRADE_BUFF = 2;
	 
	
	public Haku_Barrier() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.SELF);

		this.baseBlock = BLOCK;
		this.magicNumber = this.baseMagicNumber = BUFF;
		//CardTags.addTags(this, CustomTags.PARRY);
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		//AbstractDungeon.actionManager.addToBottom(new NegateAction(p, null, m, "defense", this.magicNumber));
		/*AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
				new BlurPower(p, this.magicNumber), this.magicNumber));*/
		
		int amountBlocked = 0;
		for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			if (!mo.isDead) {
				amountBlocked += this.block;
			}
		}
		
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, amountBlocked));
		
	}
	
	public AbstractCard makeCopy() {
		return new Haku_Barrier();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
			//this.rawDescription = UPG_DESCRIPTION;
			//upgradeMagicNumber(UPGRADE_BUFF);
			initializeDescription();
		}
	}
}
