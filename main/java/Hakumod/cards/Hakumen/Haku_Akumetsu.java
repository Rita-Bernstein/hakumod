package Hakumod.cards.Hakumen;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;

import Hakumod.action.NegateAction;
import Hakumod.action.ParryAction;
import Hakumod.action.UsingSpecialAction;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
import Hakumod.powers.Haku_MagatamaPower;
import basemod.abstracts.CustomCard;
import basemod.helpers.CardTags;

public class Haku_Akumetsu extends CustomCard{
	public static final String ID = "Haku_Akumetsu";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	//public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_Akumetsu.png";
	
	private static final int COST = -1;
	
	private static final int ATTACK_DMG = 25;
	private static final int UPGRADE_PLUS_DMG = 5;
	//private static final int UPG_COST = 2;
 
	public final int MAGATAMA_COST = 8;
	
	public Haku_Akumetsu() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.RARE,
				AbstractCard.CardTarget.ENEMY);
		
		this.tags.add(CustomTags.SPECIAL);
		this.tags.add(CustomTags.PARRY);
		this.baseDamage = ATTACK_DMG;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (new UsingSpecialAction(p, MAGATAMA_COST).canUseSpecialAction()) {
			AbstractDungeon.actionManager.addToBottom( new UsingSpecialAction(p, MAGATAMA_COST));
    		
			int energyConsumed = this.energyOnUse;
			if (p.hasRelic("Chemical X")) {
				p.getRelic("Chemical X").flash();
				energyConsumed += 2;
			}
			
	    	
	    	int AkumetsuDamage =  this.damage*energyConsumed;
	    	
	    	
	    	//AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, 1), 1));
	    	AbstractDungeon.actionManager.addToBottom(new ParryAction(p, null, m, "attack", AkumetsuDamage));
	    	AbstractDungeon.actionManager.addToBottom(new ParryAction(p, null, m, "intangible", 1));
	    	
	    	AbstractDungeon.actionManager.addToBottom(new LoseEnergyAction(this.energyOnUse));
		
		}
	}
	
	public AbstractCard makeCopy() {
		return new Haku_Akumetsu();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			//this.upgradeBaseCost(UPG_COST);
			upgradeDamage(UPGRADE_PLUS_DMG);
			initializeDescription();
		}
	}
}
