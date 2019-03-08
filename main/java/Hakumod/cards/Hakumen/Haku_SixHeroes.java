package Hakumod.cards.Hakumen;

import Hakumod.cards.Hakumen.Utils.Haku_CustomCard;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.powers.Haku_MagatamaPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.ThornsPower;

//import com.megacrit.cardcrawl.actions.common.GainBlockAction;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
//import com.megacrit.cardcrawl.localization.CardStrings;
//import Hakumod.powers.MagatamaPower;


public class Haku_SixHeroes extends Haku_CustomCard {

	public static final String ID = "Haku_SixHeroes";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	//public static final String EXT_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_SixHeroes.png";
	private static final int COST = 3;
	//private static final int UPGRADED_COST = 0;
	private int DRAW = 1;
	private static int ENERGY = 1;
	private static int STRENGTH = 1;
	private static int DEXTERITY = 1;
	//private static int MAGATAMA = 1;
	private static int THORNS = 2;

	private int ARTIFACT = 1;
	private int BLOCK = 6;
	private int UPG_BLOCK = 3;
	
	private static int UPGRADE_BUFF = 1;
	    
	public Haku_SixHeroes() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.POWER,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.RARE,
				AbstractCard.CardTarget.SELF);
		// TODO Auto-generated constructor stub
		this.magicNumber = this.baseMagicNumber = DRAW;
		this.baseBlock = BLOCK;//this.exhaust = true;
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			//this.isInnate = true;
			upgradeMagicNumber(UPGRADE_BUFF);
			//upgradeBaseCost(UPGRADED_COST);
			this.upgradeBlock(UPG_BLOCK);
			this.rawDescription = UPG_DESCRIPTION;
			//this.exhaust = false;
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	
    	//AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, this.magicNumber));
    	AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(ENERGY));
    	AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
				new ArtifactPower(AbstractDungeon.player, ARTIFACT), ARTIFACT));
    	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
				new StrengthPower(p, this.magicNumber), this.magicNumber));
    	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
				new DexterityPower(p, DEXTERITY), DEXTERITY));
    	//AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
		AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
				new ThornsPower(AbstractDungeon.player, THORNS), THORNS));

		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
				new Haku_MagatamaPower(p, this.magicNumber), this.magicNumber));
    }
	
	public AbstractCard makeCopy() {
		return new Haku_SixHeroes();
	}
	
	
	
	
}