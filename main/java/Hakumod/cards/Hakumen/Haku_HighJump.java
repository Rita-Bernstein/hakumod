package Hakumod.cards.Hakumen;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
//import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
//import Hakumod.powers.MagatamaPower;
import basemod.abstracts.CustomCard;


public class Haku_HighJump extends CustomCard{

	public static final String ID = "Haku_HighJump";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	//public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_HighJump.png";
	private static final int COST = 1;
	private static final int BLOCK = 0;
	//private static final int ATTACK_DMG = 3;
	//private static final int UPGRADE_PLUS_DMG = 2;
	private static int BUFF = 1;
	private static int UPGRADE_BUFF = 1;
	    
	public Haku_HighJump() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.SKILL, 
				AbstractCardEnum.HAKUMEN_COLOR, 
				AbstractCard.CardRarity.UNCOMMON, 
				AbstractCard.CardTarget.SELF);
		// TODO Auto-generated constructor stub
		//this.baseDamage = ATTACK_DMG;
		this.baseBlock = BLOCK;
		this.magicNumber = this.baseMagicNumber = BUFF;
		this.tags.add(CustomTags.AIR);
		//this.exhaust = true;
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			//upgradeDamage(UPGRADE_PLUS_DMG);
			upgradeMagicNumber(UPGRADE_BUFF);
			//this.exhaust = false;
			//this.rawDescription = UPG_DESCRIPTION;
			initializeDescription();
			
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
 
    	/*AbstractCard [] randomCard = {new Haku_HighJump().makeCopy(),
    			new Haku_JA().makeCopy(),
    			new Haku_JB().makeCopy(),
    			new Haku_J2C().makeCopy(),
    			new Haku_JC().makeCopy(),
    			new Haku_J2A().makeCopy(),
    			new Haku_JD().makeCopy(),
    			new Haku_Agito().makeCopy(),
    			new Haku_Hotaru().makeCopy(),
    			new Haku_Tsubaki().makeCopy()
    			}; 
    	
    	int randomNum;
    	for (int i=0;i<this.magicNumber;i++) {
    		randomNum = (int) Math.floor(Math.random()*randomCard.length);
    		AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(randomCard[randomNum], 1, true, false));
    	}
    	
    	AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
    	*/
    	
    	/*new UtilsApplyEffect(p, this, m, "neutral", this.magicNumber);
    	new UtilsApplyEffect(p, this, m, "offense", this.magicNumber);
    	new UtilsApplyEffect(p, this, m, "defense", this.magicNumber);*/
    	
    	/*int handSize = p.hand.size();
    	ArrayList<AbstractCard> tmpHand = p.hand.group;
    	for (AbstractCard c: tmpHand) {
    		p.hand.moveToDiscardPile(c);
    	}
    	
    	AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, handSize));
    	*/
    	
    	/*int cardsToDraw = p.hand.getSkills().size();
    	AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, cardsToDraw));*/
    	AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
    	
    	int block = this.baseBlock + p.hand.size() + this.magicNumber;  
    	AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
    	
    	
    }
	
	public AbstractCard makeCopy() {
		return new Haku_HighJump();
	}	
}
