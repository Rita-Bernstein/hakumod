package Hakumod.cards.Hakumen;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;

import Hakumod.action.NegateAction;
import Hakumod.action.ParryAction;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
import Hakumod.powers.Haku_DefensePower;
import Hakumod.powers.Haku_MagatamaPower;
import basemod.abstracts.CustomCard;
import basemod.helpers.CardTags;

public class Haku_Round2 extends CustomCard{
	public static final String ID = "Haku_Round2";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Defend.png";
	
	private static final int COST = 0;
 
	
	public Haku_Round2() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.SELF);

		this.exhaust = true;
		this.tags.add(CardTags.HEALING);
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		int magatamaLost;
		
		if (p.hasPower("MagatamaPower")) {
			if (p.getPower("MagatamaPower").amount>1) {
				magatamaLost = p.getPower("MagatamaPower").amount-1;
				p.getPower("MagatamaPower").reducePower(magatamaLost);
				p.heal(magatamaLost);
			}
		}
		else {
			AbstractDungeon.actionManager.addToTop(
    				new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new Haku_MagatamaPower(AbstractDungeon.player,1),1));		
		}
		
		if (this.upgraded) {
			AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
		}
	}
	
	public AbstractCard makeCopy() {
		return new Haku_Round2();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = UPG_DESCRIPTION;
			//upgradeMagicNumber(UPGRADE_BUFF);
			//this.exhaust = false;
			initializeDescription();
		}
	}
}
