package Hakumod.cards.Hakumen;

import Hakumod.action.TimekillerAction;
import Hakumod.cards.Hakumen.Utils.Haku_CustomCard;
import Hakumod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
//import com.megacrit.cardcrawl.localization.CardStrings;
//import Hakumod.powers.MagatamaPower;
//import basemod.helpers.BaseModTags;
//import basemod.helpers.CardTags;


public class Haku_Timekiller extends Haku_CustomCard {

	public static final String ID = "Haku_Timekiller";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	//public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_Timekiller.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 5;
	private static final int UPGRADE_PLUS_DMG = 3;
	private static final int BUFF = 1;
	//private static final int UPGRADE_BUFF = 1;
	
	//private static int REDUCE = 2;
	
	
	public Haku_Timekiller() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				CardType.ATTACK,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.RARE,
				AbstractCard.CardTarget.ENEMY);
		// TODO Auto-generated constructor stub
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = BUFF;
		//this.exhaust = true;
		
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			//this.exhaust = false;
			//upgradeMagicNumber(UPGRADE_BUFF);
			//this.rawDescription = UPG_DESCRIPTION;
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.FIRE));

    	AbstractDungeon.actionManager.addToBottom(new TimekillerAction(this.magicNumber));

    }
	
	public AbstractCard makeCopy() {
		return new Haku_Timekiller();
	}
	
	
}