package Hakumod.cards.Hakumen;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
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

import Hakumod.action.ChooseCardAction;
import Hakumod.action.ComboAction;
import Hakumod.action.ParryAction;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
import Hakumod.powers.Haku_MagatamaPower;
//import Hakumod.powers.MagatamaPower;
import basemod.abstracts.CustomCard;
//import basemod.helpers.BaseModTags;
//import basemod.helpers.CardTags;
import basemod.helpers.CardTags;


public class Haku_J2C extends CustomCard{

	public static final String ID = "Haku_J2C";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_J2C.png";
	private static final int COST = 2;
	private static final int ATTACK_DMG = 11;
	private static final int UPGRADE_PLUS_DMG = 3;

	private static int BUFF = 1;
	private static int UPGRADE_BUFF = 1;
	    
	public Haku_J2C() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.ATTACK, 
				AbstractCardEnum.HAKUMEN_COLOR, 
				AbstractCard.CardRarity.COMMON, 
				AbstractCard.CardTarget.ENEMY);
		// TODO Auto-generated constructor stub
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = BUFF;
		this.tags.add(CustomTags.AIR);
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			upgradeMagicNumber(UPGRADE_BUFF);
			this.rawDescription = UPG_DESCRIPTION;
			//initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SLASH_HEAVY));
    	
    	AbstractCard card5A = new Haku_5A().makeCopy();
    	AbstractCard card3C = new Haku_3C().makeCopy();
    	AbstractCard cardAgito = new Haku_Agito().makeCopy();
    	
    	ArrayList<AbstractCard> cardChoice = new ArrayList<AbstractCard>();
    	cardChoice.add(card5A);
    	cardChoice.add(card3C);
    	cardChoice.add(cardAgito);
    	
		AbstractDungeon.actionManager.addToBottom(
				new ChooseCardAction(cardChoice, this.upgraded, true, true, 0, false));

    
        
    }       
	public AbstractCard makeCopy() {
		return new Haku_J2C();
	}	
}