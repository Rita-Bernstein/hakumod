package Hakumod.cards.Hakumen;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
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
import Hakumod.action.ParryAction;
import Hakumod.action.StarterAction;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
import Hakumod.powers.Haku_MagatamaPower;
//import Hakumod.powers.MagatamaPower;
import basemod.abstracts.CustomCard;
//import basemod.helpers.BaseModTags;
//import basemod.helpers.CardTags;
import basemod.helpers.CardTags;


public class Haku_Airdash extends CustomCard{

	public static final String ID = "Haku_Airdash";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	//public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

	public static final String IMG_PATH = "Hakumod/img/cards/Haku_Airdash.png";
	private static final int COST = 1;
	//private static final int UPGRADED_COST = 0;
	//private static final int ATTACK_DMG = 7;
	//private static final int UPGRADE_PLUS_DMG = 2;
	private static int DRAW = 2;
	private static int UPGRADE_DRAW = 1;
	
	private static int STARTER_EFF = 1;
	
	public Haku_Airdash() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.SELF);
		// TODO Auto-generated constructor stub
		//this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = DRAW;
		this.tags.add(CustomTags.AIR);
		//this.exhaust = true;
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			//this.exhaust = false;
			//upgradeBaseCost(UPGRADED_COST);
			//upgradeDamage(UPGRADE_PLUS_DMG);
			upgradeMagicNumber(UPGRADE_DRAW);
			//this.rawDescription = UPG_DESCRIPTION;
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	//int cardsToDraw = p.hand.getAttacks().size();
    	AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
    	
    	AbstractDungeon.actionManager.addToBottom(new StarterAction(p, this, m, "draw", STARTER_EFF));
		
    	/*AbstractCard card = null;
    	for (int i=0; i<this.magicNumber; i++) {
    		 card = p.drawPile.getTopCard();
    		 
    		 if (card.type == CardType.ATTACK) {
    			 if (card.costForTurn != 0) card.costForTurn--;
    			 card.isCostModifiedForTurn = true;
    		 }
    		 
    		 if (card.type == CardType.SKILL) {
    			 if (card.costForTurn != 0) card.costForTurn++;
    			 card.isCostModifiedForTurn = true;
    		 }
    		
    		 p.draw(1);
    	}*/
    }
	
	public AbstractCard makeCopy() {
		return new Haku_Airdash();
	}
	
	
}