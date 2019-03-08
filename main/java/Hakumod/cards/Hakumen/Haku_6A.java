package Hakumod.cards.Hakumen;

import Hakumod.action.ComboAction;
import Hakumod.cards.Hakumen.Utils.Haku_CustomCard;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
//import com.megacrit.cardcrawl.localization.CardStrings;
//import Hakumod.powers.MagatamaPower;
//import Hakumod.powers.MagatamaPower;


public class Haku_6A extends Haku_CustomCard {

	public static final String ID = "Haku_6A";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_6A.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 7;
	private static final int UPGRADE_PLUS_DMG = 2;
	//private static int BUFF = 2;
	//private static int UPGRADE_DEBUFF = 1;
	    
	public Haku_6A() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.ATTACK,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.ENEMY,
				new Haku_6B().makeCopy());
		// TODO Auto-generated constructor stub
		this.baseDamage = ATTACK_DMG;
		//this.magicNumber = this.baseMagicNumber = DEBUFF;
		this.tags.add(CustomTags.COMBO);
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			//upgradeMagicNumber(UPGRADE_DEBUFF);
			this.rawDescription = UPG_DESCRIPTION;
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SMASH));
    	
    	AbstractCard c = new Haku_6B().makeCopy();
    	/*if (this.upgraded) { 
    		AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, false));
    	}*/
    	
    	AbstractDungeon.actionManager.addToBottom(new ComboAction(c));
    	
    	if (this.upgraded) {
    		//AbstractDungeon.actionManager.addToTop(
    		//		new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new MagatamaPower(AbstractDungeon.player,1),1));
        	//AbstractDungeon.actionManager.addToTop(
        	//		new StarterAction(p, this, m, "offense", this.magicNumber));
    	}
    	
    	/*
   		for (AbstractCard cardIs6B: p.hand.group) {
   			if (cardIs6B.cardID == "Haku_6B" && cardIs6B.canUpgrade()) {
    			cardIs6B.upgrade();
    			cardIs6B.initializeDescription();
    			cardIs6B.superFlash();  		
    			if (!this.upgraded) {
    				break;
    			}
    		}
    	}*/
    }
	
	public AbstractCard makeCopy() {
		return new Haku_6A();
	}
	
	
}