package Hakumod.cards.Hakumen;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
//import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Hakumod.action.ComboAction;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.powers.Haku_ActiveFlowPower;
import Hakumod.powers.Haku_OverdrivePower;
import Hakumod.powers.Haku_PassiveMeterPower;
//import Hakumod.powers.MagatamaPower;
//import Hakumod.powers.MagatamaPower;
import basemod.abstracts.CustomCard;
//import basemod.helpers.BaseModTags;
//import basemod.helpers.CardTags;

public class Haku_PassiveMeter extends CustomCard {

	public static final String ID = "Haku_PassiveMeter";

	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

	public static final String IMG_PATH = "Hakumod/img/cards/Haku_PassiveMeter.png";
	private static final int COST = 1;
	// private static final int UPG_COST = 0;

	public Haku_PassiveMeter() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, AbstractCard.CardType.POWER, AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
		// TODO Auto-generated constructor stub

		// this.magicNumber = this.baseMagicNumber = MAGNITUDE;
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			// upgradeBaseCost(UPG_COST);
			this.isInnate = true;
			this.rawDescription = UPG_DESCRIPTION;
			// upgradeMagicNumber(UPGRADED_MAGNITUDE);
			initializeDescription();
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {

		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Haku_PassiveMeterPower(p, 1), 1));

	}

	public AbstractCard makeCopy() {
		return new Haku_PassiveMeter();
	}

}