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
import Hakumod.action.UsingSpecialAction;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
import Hakumod.powers.Haku_MagatamaPower;
import basemod.abstracts.CustomCard;
import basemod.helpers.CardTags;

public class Haku_CA extends CustomCard{
	public static final String ID = "Haku_CA";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_6A.png";
	
	private static final int COST = 1;
	private static int MAGATAMA_COST = 4;
	private static final int BLOCK = 12;
	private static final int UPGRADE_PLUS_BLOCK = 3;
	
	//private static int BUFF = 2;
	//private static int UPGRADE_BUFF = 1;
	 
	
	public Haku_CA() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ENEMY);

		this.baseBlock = BLOCK;
		//this.magicNumber = this.baseMagicNumber = BUFF;
		this.tags.add(CustomTags.PARRY);
		this.tags.add(CustomTags.SPECIAL);
		this.exhaust = true;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (new UsingSpecialAction(p, MAGATAMA_COST).canUseSpecialAction()) {
    		
    		AbstractDungeon.actionManager.addToBottom( new UsingSpecialAction(p, MAGATAMA_COST));
    		AbstractDungeon.actionManager.addToBottom(new ParryAction(p, null, m, "block", this.block));
    		AbstractDungeon.actionManager.addToBottom(new ParryAction(p, null, m, "cure_all", 1));
		}
	}
	
	public AbstractCard makeCopy() {
		return new Haku_CA();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
			//this.rawDescription = UPG_DESCRIPTION;
			//upgradeMagicNumber(UPGRADE_BUFF);
			//initializeDescription();
		}
	}
}