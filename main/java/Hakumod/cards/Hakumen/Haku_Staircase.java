package Hakumod.cards.Hakumen;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
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
import Hakumod.patches.AbstractCardEnum;
//import Hakumod.powers.MagatamaPower;
import basemod.abstracts.CustomCard;


public class Haku_Staircase  extends CustomCard
{
	public static final String ID = "Haku_Staircase";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	//public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_Staircase.png";
	private static final int COST = 0;
	private static final int ATTACK_DMG = 1;
	private static final int UPGRADE_PLUS_DMG = 1;
	
	//private static int DEBUFF = 1;
	//private static int UPGRADE_DEBUFF = 1;

	
	public Haku_Staircase() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.ATTACK,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.ENEMY);
		// TODO Auto-generated constructor stub
		this.baseDamage = ATTACK_DMG;
		//this.magicNumber = this.baseMagicNumber = DEBUFF;
		this.exhaustOnUseOnce = true;
		/*
		modal = new ModalChoiceBuilder()
                .setCallback(this) // Sets callback of all the below options to this
                //.setColor(CardColor.RED) // Sets color of any following cards to red
                //.addOption("End with Agito.", CardTarget.ENEMY)
                .addOption(cardAgito)
                //.setColor(CardColor.GREEN) // Sets color of any following cards to green
                //.addOption("End with J.C", CardTarget.ENEMY)
                .addOption(cardJC)
                .create();*/
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			//upgradeMagicNumber(UPGRADE_DEBUFF);
			//this.rawDescription = UPG_DESCRIPTION;
			//initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    	AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    	AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    	AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));  	
    	
    	AbstractCard cardAgito = new Haku_Agito();
    	AbstractCard cardJC = new Haku_JC();
		/*cardAgito.setCostForTurn(0);
		cardJC.setCostForTurn(0);
		cardAgito.exhaustOnUseOnce = true;
		cardJC.exhaustOnUseOnce = true;
    	if (this.upgraded) {
    		cardAgito.upgrade();
    		cardJC.upgrade();
    	}*/
    	
    	ArrayList<AbstractCard> cardChoice = new ArrayList<AbstractCard>();
    	cardChoice.add(cardJC);
    	cardChoice.add(cardAgito);
    	
		AbstractDungeon.actionManager.addToBottom(
				new ChooseCardAction( cardChoice, this.upgraded, true, true, 0, false));
    }
	
	public AbstractCard makeCopy() {
		return new Haku_Staircase();
	}
	
	
}
