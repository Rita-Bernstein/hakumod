package Hakumod.cards.Hakumen;

import Hakumod.cards.Hakumen.Utils.Haku_CustomCard;
import Hakumod.orbs.Haku_VoidOrb;
import Hakumod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
//import com.megacrit.cardcrawl.localization.CardStrings;
//import Hakumod.powers.Haku_SwordOfDoomPower;
//import Hakumod.powers.MagatamaPower;
//import Hakumod.powers.MagatamaPower;
//import basemod.helpers.BaseModTags;
//import basemod.helpers.CardTags;


public class Haku_SwordOfDoom extends Haku_CustomCard {

	public static final String ID = "Haku_SwordOfDoom";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	//public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_SwordOfDoom.png";
	private static final int COST = 1;
	private static final int UPGRADED_COST = 0;

	private static final int NEW_MAX_ORBS = 4;

	private static int MAGNITUDE = 1;
	//private static int UPGRADED_MAGNITUDE = 1;
    
	public Haku_SwordOfDoom() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.POWER,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.SELF);
		// TODO Auto-generated constructor stub
		
		this.magicNumber = this.baseMagicNumber = MAGNITUDE;
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			upgradeBaseCost(UPGRADED_COST);
			//upgradeMagicNumber(UPGRADED_MAGNITUDE);
			//this.rawDescription = UPG_DESCRIPTION;
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	int increaseOrbs = NEW_MAX_ORBS - p.maxOrbs;
    	AbstractDungeon.player.increaseMaxOrbSlots(increaseOrbs, false);
    	
		for (int i=0;i<this.magicNumber;i++) {
			AbstractDungeon.actionManager.addToBottom(new ChannelAction(new Haku_VoidOrb()));
		}
    }
	
	public AbstractCard makeCopy() {
		return new Haku_SwordOfDoom();
	}
}