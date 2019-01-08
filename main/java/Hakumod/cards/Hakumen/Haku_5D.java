package Hakumod.cards.Hakumen;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Hakumod.action.NegateAction;
import Hakumod.action.ParryAction;
import Hakumod.action.UsingSpecialAction;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
import Hakumod.powers.Haku_MagatamaPower;
import basemod.abstracts.CustomCard;
import basemod.helpers.CardTags;

public class Haku_5D extends CustomCard{
	public static final String ID = "Haku_5D";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	//public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_5D.png";
	
	private static final int COST = -1;
	private static final int BLOCK = 5;
	private static final int UPGRADE_PLUS_BLOCK = 2;
	
	private static final int ATTACK_DMG = 5;
	private static final int UPGRADE_PLUS_DMG = 2;
	
	/*private static int BUFF = 1;
	private static int UPGRADE_BUFF = 1;*/
	 
	
	public Haku_5D() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ENEMY);

		this.baseBlock = BLOCK;
		this.baseDamage = ATTACK_DMG;
		//this.magicNumber = this.baseMagicNumber = BUFF;
		this.tags.add(CustomTags.PARRY);
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		
		int energyConsumed = this.energyOnUse;
		if (p.hasRelic("Chemical X")) {
			p.getRelic("Chemical X").flash();
			energyConsumed += 2;
		}
		
    	
    	int totalDamage =  this.damage*energyConsumed;
    	int totalBlock =  this.block*energyConsumed;

    	AbstractDungeon.actionManager.addToBottom(new ParryAction(p, null, m, "attack", totalDamage));
		AbstractDungeon.actionManager.addToBottom(new ParryAction(p, null, m, "block", totalBlock));
		
		AbstractDungeon.actionManager.addToBottom(new LoseEnergyAction(this.energyOnUse));
	}
	
	public AbstractCard makeCopy() {
		return new Haku_5D();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
			upgradeDamage(UPGRADE_PLUS_DMG);
			//this.rawDescription = UPG_DESCRIPTION;
			//upgradeMagicNumber(UPGRADE_BUFF);
			initializeDescription();
		}
	}
}
