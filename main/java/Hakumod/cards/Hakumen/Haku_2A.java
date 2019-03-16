package Hakumod.cards.Hakumen;

import Hakumod.cards.Hakumen.Utils.Haku_CustomCard;
import Hakumod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
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


public class Haku_2A extends Haku_CustomCard {

	public static final String ID = "Haku_2A";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	//public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_2A.png";
	private static final int COST = 0;
	private static final int ATTACK_DMG = 4;
	private static final int UPGRADE_PLUS_DMG = 2;
	//private static final int UPGRADED_COST = 0;
	//private static int DEBUFF = 1;
	//private static int UPGRADE_DEBUFF = 1;
	    
	public Haku_2A() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.ATTACK,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.COMMON, 
				AbstractCard.CardTarget.ENEMY);
		// TODO Auto-generated constructor stub
		this.baseDamage = ATTACK_DMG;
		//this.magicNumber = this.baseMagicNumber = DEBUFF;
		
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			//upgradeBaseCost(UPGRADED_COST);
			//upgradeMagicNumber(UPGRADE_DEBUFF);
			//this.rawDescription = UPG_DESCRIPTION;
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    	
    	/*AbstractCard card6A = new Haku_6A().makeCopy();
    	AbstractCard card2B = new Haku_2B().makeCopy();
    	AbstractCard card3C = new Haku_3C().makeCopy();
    	
    	ArrayList<AbstractCard> cardChoice = new ArrayList<AbstractCard>();
    	cardChoice.add(card6A);
    	cardChoice.add(card3C);
    	cardChoice.add(card2B);
    	
		AbstractDungeon.actionManager.addToBottom(
				new ChooseCardAction(cardChoice, this.upgraded, true, false, 0, false));
    	*/
    	//AbstractCard c = new Haku_6A().makeCopy();
    	//AbstractDungeon.actionManager.addToBottom(new ComboAction(c));
		for (AbstractCard cardInDeck:p.drawPile.group) {
			if (cardInDeck instanceof Haku_2A){
				p.hand.addToHand(cardInDeck);
				p.drawPile.removeCard(cardInDeck);
				p.hand.refreshHandLayout();
				break;
			}
		}
    	
    	/*if (this.upgraded) {
    		AbstractCard copy = new Haku_2A().makeCopy();
			AbstractDungeon.actionManager.addToTop(new MakeTempCardInDiscardAction(copy, 1));

		}*/
    }
	
	public AbstractCard makeCopy() {
		return new Haku_2A();
	}
	
	
}