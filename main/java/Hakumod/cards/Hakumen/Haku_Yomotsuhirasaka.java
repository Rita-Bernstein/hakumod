package Hakumod.cards.Hakumen;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
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
import Hakumod.orbs.VoidOrb;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.powers.Haku_ActiveFlowPower;
import Hakumod.powers.Haku_AwakeningPower;
import Hakumod.powers.Haku_YomotsuhirasakaPower;
import Hakumod.powers.Haku_OverdrivePower;
import Hakumod.powers.Haku_EmptySkyPower;
//import Hakumod.powers.MagatamaPower;
//import Hakumod.powers.MagatamaPower;
import basemod.abstracts.CustomCard;
//import basemod.helpers.BaseModTags;
//import basemod.helpers.CardTags;


public class Haku_Yomotsuhirasaka extends CustomCard{

	public static final String ID = "Haku_Yomotsuhirasaka";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	//public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_Yomotsuhirasaka.png";
	private static final int COST = 2;
	//private static final int UPGRADED_COST = 0;
	private static int STACK = 7;
	
	private static int CHANNEL = 1;
	private static int UPGRADED_CHANNEL = 1;
    
	
	public Haku_Yomotsuhirasaka() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.POWER,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.RARE,
				AbstractCard.CardTarget.SELF);
		// TODO Auto-generated constructor stub
		
		this.magicNumber = this.baseMagicNumber = CHANNEL;
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			//upgradeBaseCost(UPGRADED_COST);
			upgradeMagicNumber(UPGRADED_CHANNEL);
			//this.rawDescription = UPG_DESCRIPTION;
			//this.isInnate = true;
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new Haku_YomotsuhirasakaPower(p, STACK), STACK));
		
		for (int i=0;i<this.magicNumber;i++) {
			AbstractDungeon.actionManager.addToBottom(new ChannelAction(new VoidOrb()));
		}
    }
	
	public AbstractCard makeCopy() {
		return new Haku_Yomotsuhirasaka();
	}
}