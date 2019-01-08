package Hakumod.cards.Hakumen;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
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

public class Haku_InstantBarrier extends CustomCard{
	public static final String ID = "Haku_InstantBarrier";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_InstantBarrier.png";
	
	private static final int COST = 2;
	private static final int BLOCK = 8;
	private static final int UPGRADE_PLUS_BLOCK = 2;
	
	private static int BUFF = 1;
	private static int UPGRADE_BUFF = 1;
	 
	
	public Haku_InstantBarrier() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.SELF);

		this.baseBlock = BLOCK;
		this.magicNumber = this.baseMagicNumber = BUFF;
		//this.exhaust = true;
		//CardTags.addTags(this, CustomTags.PARRY);
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
		
		AbstractCard cardIB = new Haku_IB().makeCopy();
		AbstractCard cardBarrier = new Haku_Barrier().makeCopy();
	
		cardIB.exhaust = true;
		cardBarrier.exhaust = true;
		
		
    	AbstractCard [] cardToAdd = {
    			//new Haku_Kishuu().makeCopy(),
    			cardIB,
    			cardBarrier
    	};
	    
    	for (int i=0; i<this.magicNumber && i<cardToAdd.length; i++) {
    		cardToAdd[i].updateCost(-1);
    		//cardToAdd[i].isCostModified = true;
    		cardToAdd[i].exhaust = true;
    		//cardToAdd[i].exhaustOnUseOnce = true;
    		//cardToAdd[i].exhaustOnFire = true;
   			//AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(cardToAdd[i], 1, true, false));
    		p.drawPile.addToRandomSpot(cardToAdd[i]);
    	}
	}
	
	public AbstractCard makeCopy() {
		return new Haku_InstantBarrier();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
			this.rawDescription = UPG_DESCRIPTION;
			upgradeMagicNumber(UPGRADE_BUFF);
			initializeDescription();
		}
	}
}
