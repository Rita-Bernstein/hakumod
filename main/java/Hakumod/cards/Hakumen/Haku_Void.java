package Hakumod.cards.Hakumen;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Hakumod.action.NegateAction;
import Hakumod.action.ParryAction;
import Hakumod.orbs.Haku_VoidOrb;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
import Hakumod.powers.Haku_MagatamaPower;
import basemod.abstracts.CustomCard;
import basemod.helpers.CardTags;

public class Haku_Void extends CustomCard{
	public static final String ID = "Haku_Void";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	//public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_Fuumajin.png";
	
	private static final int COST = 0;
	
	private int BUFF = 1;
	private static int UPGRADE_BUFF = 1;
	
	public Haku_Void() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.SELF);
		this.magicNumber = this.baseMagicNumber = BUFF;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		//if (p.maxOrbs == 0) {AbstractDungeon.actionManager.addToBottom(new IncreaseMaxOrbAction(1));}
		for (int i=0;i<this.magicNumber;i++) {
			AbstractDungeon.actionManager.addToBottom(new ChannelAction(new Haku_VoidOrb()));
		}
	}
	public AbstractCard makeCopy() {
		return new Haku_Void();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			//this.isInnate = true;
			this.exhaust = false;
			//this.rawDescription = UPG_DESCRIPTION;
			upgradeMagicNumber(UPGRADE_BUFF);
			initializeDescription();
		}
	}
}
